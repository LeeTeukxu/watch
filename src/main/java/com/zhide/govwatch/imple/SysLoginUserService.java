package com.zhide.govwatch.imple;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.ILoginUserService;
import com.zhide.govwatch.mapper.SysLoginUserMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.patentarea;
import com.zhide.govwatch.model.sqlParameter;
import com.zhide.govwatch.model.tbLoginUser;
import com.zhide.govwatch.repository.patentareaRepository;
import com.zhide.govwatch.repository.tbLoginUserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SysLoginUserService implements ILoginUserService {
    @Autowired
    SysLoginUserMapper userMapper;
    @Autowired
    tbLoginUserRepository userRep;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public Map<String, Integer> getAllByNameAndID() {
        Map<String, Integer> result = new HashMap<>();
        List<Map<String, Object>> rows = userMapper.getAllByIDAndName();
        rows.stream().forEach(f -> {
            String Name = f.get("Name").toString();
            Integer ID = Integer.parseInt(f.get("ID").toString());
            if (result.containsKey(Name) == false) {
                result.put(Name, ID);
            }
        });
        return result;
    }

    @Override
    public Map<Integer, String> getAllByIDAndName() {
        Map<Integer, String> result = new HashMap<>();
        List<Map<String, Object>> rows = userMapper.getAllByIDAndName();
        rows.stream().forEach(f -> {
            Integer ID = Integer.parseInt(f.get("ID").toString());
            if (result.containsKey(ID) == false) {
                String Name = f.get("Name").toString();
                result.put(ID, Name);
            }
        });
        return result;
    }

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject object=new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> Datas = userMapper.getData(params);
        Integer Total = 0;
        if (Datas.size() > 0) {
            Total = Integer.parseInt(Datas.get(0).get("_Total").toString());
        }
        object.setData(Datas);
        object.setTotal(Total);
        return object;
    }

    @Override
    public pageObject getMineData(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> params = getMineParameters(request);
        List<Map<String, Object>> Datas = userMapper.getData(params);
        Integer Total = 0;
        if (Datas.size() > 0) {
            Total = Integer.parseInt(Datas.get(0).get("_Total").toString());
        }
        object.setData(Datas);
        object.setTotal(Total);
        return object;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(tbLoginUser login) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        Integer ID = login.getUserId();
        if (ID == null) ID = 0;
        Optional<tbLoginUser> findUsers = userRep.findById(ID);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
        if (findUsers.isPresent()) {
            tbLoginUser user= findUsers.get();
            if(user.getXPass().equals(login.getXPass())==false){
                user.setXPass(login.getXPass());
                user.setPassword(encoder.encode(login.getXPass()));
            }
            user.setRoleId(login.getRoleId());
            user.setCompanyId(login.getCompanyId());
            user.setUserName(login.getUserName());
            user.setCanLogin(login.getCanLogin());
            user.setDepId(login.getDepId());
            user.setLastUpdateTime(new Date());
            user.setLastUpdateMan(Info.getUserId());
            user.setEmail(login.getEmail());
            userRep.save(user);
        } else {
            login.setPassword(encoder.encode(login.getXPass()));
            login.setCreateTime(new Date());
            login.setCreateMan(Info.getUserId());
            userRep.save(login);
        }
        redisUtils.clearAll("LoginUser");
        return true;
    }

    @Autowired
    patentareaRepository areaRep;

    @Override
    @Transactional
    public tbLoginUser SaveMine(tbLoginUser login) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        Integer ID = login.getUserId();
        tbLoginUser res = null;
        if (ID == null) ID = 0;
        if (ID == 0) {
//            String userName = login.getUserName();
//            List<tbLoginUser> users = userRep.findAllByUserName(login.getUserName());
//            if (users.size() > 0) throw new Exception("用户名称:" + login.getUserName() + "已存在,请核对后再创建用户!");


            String loginCode = login.getLoginCode();
            Optional<tbLoginUser> users1 = userRep.findAllByLoginCode(loginCode);
            if (users1.isPresent()) throw new Exception("登录帐号:" + login.getLoginCode() + "已存在,请核对后再创建用户!");
        }
        Optional<tbLoginUser> findUsers = userRep.findById(ID);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
        if (findUsers.isPresent()) {
            tbLoginUser user = findUsers.get();
            if (user.getXPass().equals(login.getXPass()) == false) {
                user.setXPass(login.getXPass());
                user.setPassword(encoder.encode(login.getXPass()));
            }
            user.setRoleId(login.getRoleId());
            user.setCompanyId(Info.getCompanyId());
            user.setUserName(login.getUserName());
            user.setCanLogin(login.getCanLogin());
            user.setDepId(Info.getDepId());
            user.setLastUpdateTime(new Date());
            user.setLastUpdateMan(Info.getUserId());
            user.setEmail(login.getEmail());
            user.setPid(Info.getUserId());
            res = userRep.save(user);
        } else {
            login.setCompanyId(Info.getCompanyId());
            login.setDepId(Info.getDepId());
            login.setPassword(encoder.encode(login.getXPass()));
            login.setXPass(login.getXPass());
            login.setCreateTime(new Date());
            login.setCreateMan(Info.getUserId());
            login.setPid(Info.getUserId());
            res = userRep.save(login);
        }
        if (StringUtils.isEmpty(login.getAreaId()) == false) {
            String[] IDS = login.getAreaId().split(",");
            if (IDS.length > 0) {
                areaRep.deleteAllByUserId(res.getUserId());
                List<patentarea> ps = new ArrayList<>();
                for (int i = 0; i < IDS.length; i++) {
                    Integer IID = Integer.parseInt(IDS[i]);
                    patentarea area = new patentarea();
                    area.setUserId(res.getUserId());
                    area.setAreaId(IID);
                    area.setCreateTime(new Date());
                    area.setCreateMan(Info.getUserId());
                    ps.add(area);
                }
                areaRep.saveAll(ps);
            }
        }
        ///企业用户删除专利区域设置
        if (res.getRoleId() == 6) {
            areaRep.deleteAllByUserId(res.getUserId());
        }
        redisUtils.clearAll("LoginUser");
        return res;
    }
    @Override
    @Transactional
    public void RemoveUser(Integer UserID) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        Integer LogPID = Info.getUserId();
        String RoleName = Info.getRoleName();
        Optional<tbLoginUser> findUsers = userRep.findById(UserID);
        if (findUsers.isPresent()) {
            tbLoginUser user = findUsers.get();
            if (RoleName != "系统管理员") {
                Integer PID = user.getPid();
                if (PID.equals(LogPID) == false) throw new Exception("你没有删除：" + user.getUserName() + "的权限!");
            }
            userRep.delete(user);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String CreateCode(String BaseUrl, Integer UserID) throws Exception {
        Optional<tbLoginUser> findUsers = userRep.findById(UserID);
        if (findUsers.isPresent()) {
            tbLoginUser user = findUsers.get();
            if (StringUtils.isEmpty(user.getMyCode()) == false) {
                throw new Exception("邀请地址生成后不能更改!");
            }
            String CC = UUID.randomUUID().toString().toLowerCase();
            String DD = CC.substring(CC.length() - 6);
            String Code = "xx" + DD;
            while (true) {
                Optional<tbLoginUser> kkUsers = userRep.findFirstByMyCode(Code);
                if (kkUsers.isPresent() == false) break;
            }
            user.setMyCode(Code);
            user.setMyAddress(BaseUrl + "/" + Code);
            userRep.save(user);
            return user.getMyAddress();
        } else return "";
    }

    Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "CreateTime";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex == 0 ? pageIndex * pageSize : pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        String CText=request.getParameter("CompanyID");
        if(StringUtils.isEmpty(CText)==false) {
            params.put("CompanyID", Integer.parseInt(CText));
        }
        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        } else params.put("andItems", new ArrayList<>());
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    Map<String, Object> getMineParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "CreateTime";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex == 0 ? pageIndex * pageSize : pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        String CText = request.getParameter("CompanyID");
        if (StringUtils.isEmpty(CText) == false) {
            params.put("CompanyID", Integer.parseInt(CText));
        }

        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        } else params.put("andItems", new ArrayList<>());
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
            params.put("PID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public successResult removeUser(Integer UserID) {
        successResult result = new successResult();
        try {

        } catch (Exception ax) {

        }
        return result;
    }
}
