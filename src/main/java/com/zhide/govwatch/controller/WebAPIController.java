package com.zhide.govwatch.controller;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.PatentRepository;
import com.zhide.govwatch.repository.discodeRepository;
import com.zhide.govwatch.repository.v_LoginUserRepository;
import com.zhide.govwatch.service.AcceptErrorService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: WebAPIController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月16日 16:44
 **/
@RestController
@RequestMapping("/WebAPI")
public class WebAPIController {
    @Autowired
    PatentRepository ptInfoRep;
    @Autowired
    StringRedisTemplate redisUtils;
    @Autowired
    LoginUserErrorCounter errorCounter;
    @Autowired
    v_LoginUserRepository vLoginRep;
    @Autowired
    PatentRepository pRep;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    Validator validator;
    @Autowired
    RabbitUtil rabbitUtil;
    @Autowired
    allRabbitMQQueueNames allMessage;
    @Autowired
    AcceptErrorService acceptService;
    @Autowired
    discodeRepository codeRep;
    Logger logger = LoggerFactory.getLogger(WebAPIController.class);

    @ResponseBody
    @PostMapping("/acceptGovFee")
    public successResult AcceptGovFee(String Data) {
        successResult result = new successResult();
        try {
            if (StringUtils.isEmpty(Data) == true) throw new IllegalArgumentException("提交的官费更新数据不能为空!");
            List<patentGovFee> items = JSON.parseArray(Data, patentGovFee.class);
            if (items.size() > 0) {
                List<patentGovFee> feeItems = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    patentGovFee feeItem = items.get(i);
                    Set<ConstraintViolation<patentGovFee>> errors = validator.validate(feeItem);
                    if (errors.size() > 0) {
                        for (ConstraintViolation<patentGovFee> error : errors) {
                            acceptService.addOne("年费数据", error.getMessage(), Data);
                            break;
                        }
                    } else feeItems.add(feeItem);
                }
                if (feeItems.size() > 0) rabbitUtil.send(allMessage.govFeeItemAccepted(), feeItems);
            } else throw new IllegalArgumentException("提交的数据中不包含官费更新数据!");
        } catch (Exception ax) {
            logger.info("接收："+Data+"出错");
            result.raiseException(ax);
            result.setSuccess(true);
            ax.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/acceptPatentInfo")
    public successResult AcceptGovPatentInfo(String Data) {
        String ListKey = "PatentPushList";
        successResult result = new successResult();
        try {
            if (StringUtils.isEmpty(Data)) throw new IllegalArgumentException("更新专利信息内容不能为空!");
            GovPatentInfo PInfo = JSON.parseObject(Data, GovPatentInfo.class);
            Set<ConstraintViolation<GovPatentInfo>> errors = validator.validate(PInfo);
            if (errors.size() > 0) {
                for (ConstraintViolation<GovPatentInfo> error : errors) {
                    acceptService.addOne("专利数据", error.getMessage(), Data);
                    break;
                }
            } else {
                PInfo.setProcessTime(new Date());
                redisUtils.opsForList().leftPush(ListKey, JSON.toJSONString(PInfo));
                //rabbitUtil.send(allMessage.pantentItemAccepted(), PInfo);
            }
        } catch (Exception ax) {
            logger.info("接收："+Data+"出错");
            result.raiseException(ax);
            result.setSuccess(true);
            ax.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/Login")
    @ResponseBody
    public successResult login(String username, String password, String token) {
        successResult Res = new successResult(false);
        try {

            username = username.trim();
            password = password.trim();

            errorCounter.setAccount(username);
            errorCounter.setMaxTimes(10);
            if (errorCounter.getTimes() >= 10) {
                errorCounter.lockUser();
                throw new Exception(username + "登录错误超过十次，已被锁定30分钟。");
            }
            HashMap<String, Object> O = new HashMap<>();
            v_LoginUser loginUser = vLoginRep.findAllByLoginCode(username);
            if (loginUser == null) {
                errorCounter.addOne();
                throw new Exception(username + "设置不完整，请检查该用户的部门或权限设置是否正确！");
            }
            if (loginUser.isCanLogin() == false) {
                errorCounter.addOne();
                throw new Exception(username + "已被限制登录系统。");
            }

            BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder(5);
            if (Encoder.matches(password, loginUser.getPassword()) == false) {
                errorCounter.addOne();
                throw new Exception(username + "登录时密码不正确!");
            }

            SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String VX = CompanyTokenUtils.getOrCreateToken(username);
            O.put("Token", VX);
            O.put("EndTime", simple.format(DateUtils.addDays(new Date(), 10)));

            Res.setData(O);
            //logger.info(username + "登录成功!");
        } catch (Exception ax) {
            Res.setSuccess(false);
            //Res.raiseException(ax);
        }
        return Res;
    }

    @RequestMapping("/getAllPantentCode")
    @ResponseBody
    @Transactional
    public successResult getAllPantentCode(HttpServletRequest request) {
        successResult result = new successResult();
        Long T1 = System.currentTimeMillis();
        try {
            List<String> res = new ArrayList<>();
            LoginUserInfo Info = CompanyContext.get();
            String Account = Info.getUserName();

            String ZeroKey = Account + "::PatentCode::IsZero";
            if (redisUtils.hasKey(ZeroKey) == true) {
                result.setData(res);
                return result;
            }
            int pageIndex = 0;
            String sKey = "AllCodePageIndex";
            if (redisTemplate.hasKey(sKey)) {
                pageIndex = Integer.parseInt(redisTemplate.opsForValue().get(sKey));
            }
            if(pRep.countAllByLawstatusIsNull()>500){
                res=pRep.getAllCodesByLawStatus(pageIndex*50);
                logger.info("先获取法律状态为空的记录集。");
            } else{
                res = pRep.getAllCodesByLastUpdateTime(pageIndex*50);
                logger.info("按更新的先后顺序进行更新");
            }

            if (res.size() == 0) {
                redisUtils.opsForValue().set(ZeroKey, LocalDateTime.now().toString(), 1, TimeUnit.HOURS);
            } else {
                pageIndex = pageIndex + 1;
                redisTemplate.opsForValue().set(sKey, Integer.toString(pageIndex), 1, TimeUnit.HOURS);
            }
            logger.info("getAllPantentCode执行到分页查询，当前是第:" + Integer.toString(pageIndex) + "页。");
            if(res.size()>0){
                String IP= request.getRemoteAddr();
                List<discode> codes=new ArrayList<>();
                for(int i=0;i<res.size();i++){
                    String code=res.get(i);
                    discode d=new discode();
                    d.setCode(code);
                    d.setIp(IP);
                    d.setCreateTim(new Date());
                    codes.add(d);
                }
                codeRep.saveAll(codes);
            }
            result.setData(res);
        } catch (Exception ax) {
            result.raiseException(ax);
            logger.info("getAllPantentCode查询数据异常:" + ax.getMessage());
        }
        Long T2 = System.currentTimeMillis();
        logger.info("getAllPantentCode执行用时:" + Long.toString(T2 - T1));
        return result;
    }
}
