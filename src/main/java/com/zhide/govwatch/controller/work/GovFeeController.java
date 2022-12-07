package com.zhide.govwatch.controller.work;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IFeeMemoService;
import com.zhide.govwatch.define.IGovFeeService;
import com.zhide.govwatch.define.IPayForService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.feeMemo;
import com.zhide.govwatch.model.gov;
import com.zhide.govwatch.repository.patentareaRepository;
import com.zhide.govwatch.repository.view_govPayRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GovFeeController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年02月07日 22:49
 **/
@Controller
@RequestMapping("/work/govFee")
public class GovFeeController {
    @Autowired
    IGovFeeService govService;
    @Autowired
    IPayForService payService;

    @Autowired
    IFeeMemoService feeMemoService;

    @Autowired
    MongoTemplate mongoRep;

    @Autowired
    view_govPayRepository view_govPayRepository;
    @Autowired
    patentareaRepository patentareaRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(GovFeeController.class);

    @RequestMapping("/wait")
    public String Wait(Map<String, Object> model, HttpServletRequest request) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        model.put("RoleName", Info.getRoleName());
        return "/work/govFee/wait";
    }

    @RequestMapping("/Officialmonitoring")
    public String Officialmonitoring(HttpServletRequest request, Map<String, Object> model) throws Exception {
        return "/work/govFee/Officialmonitoring";
    }

    private List<String> GetDateDiff(List<gov> listGov) {
        List<String> list = new ArrayList<>();
        listGov.stream().filter(x -> x.getPayState().equals("未缴费")).forEach(f -> {
            try {
                if (f.getDIFFDATE().indexOf("-") > -1) {
                    list.add("Invalid");
                } else if (Integer.parseInt(f.getDIFFDATE()) >= 0 && Integer.parseInt(f.getDIFFDATE()) <= 30) {
                    list.add("ZeroToThirty");
                } else if (Integer.parseInt(f.getDIFFDATE()) >= 30 && Integer.parseInt(f.getDIFFDATE()) <= 90) {
                    list.add("ThirtyToNinety");
                }
            } catch (Exception ax) {
                ax.printStackTrace();
            }
        });
        return list;
    }

    @RequestMapping("/payFor")
    public String PayFor(Map<String, Object> model) {

        LoginUserInfo Info = CompanyContext.get();
        model.put("RoleName", Info.getRoleName());
        return "/work/govFee/payfor";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public pageObject GeData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            String State = request.getParameter("State");
            if (StringUtils.isEmpty(State)) State = "All";
            if (State.equals("YJALL")) {
                result = payService.getData(request);
            } else result = govService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/addMemo")
    public String AddMemo(String ID, String FEENAME, Map<String, Object> model) {
        model.put("ID", ID);
        model.put("FEENAME", FEENAME);
        return "/work/govFee/addMemo";
    }

    @ResponseBody
    @RequestMapping("/saveMemo")
    public successResult saveMemo(String Data) {
        successResult result = new successResult();
        try {
            List<feeMemo> feeMemos = JSON.parseArray(Data, feeMemo.class);
            feeMemoService.SaveAll(feeMemos);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getFeeMemo")
    public List<feeMemo> getFeeMemo(String SHENQINGH, String FEENAME) {
        List<feeMemo> res = new ArrayList<>();
        if (SHENQINGH.indexOf(",") > -1) {

        } else {
            res = feeMemoService.GetData(SHENQINGH, FEENAME);
        }
        return res;
    }

    @ResponseBody
    @RequestMapping("/deleteByID")
    public successResult Delete(int ID) {
        successResult result = new successResult();
        try {
            feeMemoService.DeleteByID(ID);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/saveImageFollow")
    public successResult SaveImageFollowRecord(String Data) {
        successResult result = new successResult();
        try {
            feeMemo record = JSON.parseObject(Data, feeMemo.class);
            feeMemoService.SaveImageFollowRecord(record);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getImages")
    public Map<String, Object> getAllImages(String MID) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<Map<String, Object>> OO = new ArrayList<>();
            List<String> Files = feeMemoService.getImages(MID);
            if (Files.size() > 0) {
                res.put("status", 1);
                res.put("start", 0);
                for (int n = 0; n < Files.size(); n++) {
                    String ssText = Files.get(n);
                    String[] ssx = ssText.split("\\|");
                    if (ssx.length > 1) {
                        String Src = ssx[0];
                        String memo = StringUtils.trim(ssx[1]);
                        if (StringUtils.isEmpty(memo)) memo = "第" + (n + 1) + "个图片";
                        Map<String, Object> OX = new HashMap<>();
                        OX.put("src", Src);
                        OX.put("thumb", "");
                        OX.put("alt", memo);
                        OO.add(OX);
                    }
                }
            }
            res.put("data", OO);
            if (OO.size() == 0) throw new Exception("没有可查看的通知书附件。");
        } catch (Exception ax) {
            res.put("status", 0);
            res.put("message", ax.getMessage());
        }
        return res;
    }

    @RequestMapping("/getGovCount")
    @ResponseBody
    public successResult getGovCount(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            LoginUserInfo Info = CompanyContext.get();
            List<Map<String, Object>> OO = govService.getGovCount(Info.getRoleId(), Info.getUserId());
            result.setData(OO);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
