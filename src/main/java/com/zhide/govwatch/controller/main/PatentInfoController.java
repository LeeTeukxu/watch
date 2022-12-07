package com.zhide.govwatch.controller.main;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPatentService;
import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.machine;
import com.zhide.govwatch.model.patent;
import com.zhide.govwatch.repository.machineRepository;
import com.zhide.govwatch.repository.patentupdateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: PatentInfoController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月24日 9:41
 **/
@Controller
@RequestMapping("/main/patent")
public class PatentInfoController {
    @Autowired
    IPatentService pService;
    @Autowired
    patentupdateRepository pUpRep;
    @Autowired
    machineRepository machineRep;

    Logger logger = LoggerFactory.getLogger(PatentInfoController.class);

    @RequestMapping("/index")
    public String Index(Map<String, Object> model) {
        model.put("Users", JSON.toJSONString(new ArrayList<>()));
        LoginUserInfo Info = CompanyContext.get();
        model.put("RoleName", Info.getRoleName());
        model.put("TZSStatus", JSON.toJSONString(new ArrayList<>()));
        return "/main/patent/index";
    }

    @RequestMapping("/addTask")
    public String AddTask(Map<String, Object> model) {
        return "/main/patent/addTask";
    }

    @RequestMapping("/showTask")
    public String ShowTask(String No, Map<String, Object> model) {
        model.put("Percent", pUpRep.getPercent(No));
        model.put("No", No);
        return "/main/patent/showTask";
    }

    @RequestMapping("/showTaskMain")
    public String ShowTaskMain() {
        return "/main/patent/showTaskMain";
    }

    @ResponseBody
    @RequestMapping("/getData")
    public pageObject getData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            Map<String, Object> params = pService.getParameters(request);
            result = pService.getData(params);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getShowTask")
    public pageObject GetShowTask(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = pService.getShowTask(request);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getUpdateMain")
    public pageObject getUpdateMain(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = pService.getUpdateMain(request);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getPatentTotal")
    @ResponseBody
    public successResult getPatentTotal() {
        successResult result = new successResult();
        try {
            LoginUserInfo Info = CompanyContext.get();
            List<Map<String, Object>> OO = pService.getPatentTotal(Info.getUserId(), Info.getRoleName());
            result.setData(OO);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/getLawStatus")
    public List<ComboboxItem> getLawStatus() {
        List<ComboboxItem> items = new ArrayList<>();
        try {
            List<String> Codes = pService.getLawStatus();
            for (int i = 0; i < Codes.size(); i++) {
                String Code = Codes.get(i);
                ComboboxItem item = new ComboboxItem();
                item.setId(Code);
                item.setText(Code);
                items.add(item);
            }
            ComboboxItem first = new ComboboxItem();
            first.setText("全部");
            first.setId("");
            items.add(0, first);
        } catch (Exception ax) {

        }
        return items;
    }

    @RequestMapping("/getpatentinfo")
    public String getpatentinfo(HttpServletRequest request, Map<String, Object> model) {
        String SHENQINGH = request.getParameter("SHENQINGH");
        patent patentinfo = pService.getsqh(SHENQINGH);
        model.put("SHENQINGH", patentinfo.getShenqingh());
        model.put("ProvinceName", patentinfo.getProvinceName());
        model.put("CityName", patentinfo.getCityName());
        model.put("CountyName", patentinfo.getCountyName());
        model.put("Dailijgmc", patentinfo.getDailijgmc());
        model.put("ClientId", patentinfo.getClientId());
        model.put("ClientName", patentinfo.getClientName());
        model.put("Address", patentinfo.getAddress());

        model.put("Famingmc", patentinfo.getFamingmc());
        model.put("Prishenqingrxm", patentinfo.getPrishenqingrxm());
        return "/main/patent/updateinfo";
    }

    @RequestMapping("/updateinfopate")
    public successResult updateinfopate(HttpServletRequest request) {
        successResult result = new successResult();
        patent uppateninfo = pService.uppateninfo(request.getParameter("SHENQINGH"), request.getParameter(
                        "ProvinceName"), request.getParameter("CityName"),
                request.getParameter("CountyName"), request.getParameter("DAILIJGMC"),
                request.getParameter("ADDRESS"), Integer.parseInt(request.getParameter("ClientID").toString()),
                request.getParameter("ClientName"));
        result.setData(uppateninfo);
        return result;
    }

    @RequestMapping("/getMachine")
    @ResponseBody
    public List<machine> getMachines() {
        return machineRep.findAll();
    }

    @RequestMapping("/addOne")
    @ResponseBody
    public successResult AddOne(String machines, HttpServletRequest request) {
        successResult result = new successResult();
        try {
            logger.info("get Machines:" + machines);
            Map<String, Object> params = pService.getParameters(request);
            String[] Ms = machines.split(",");
            List<String> Codes = pService.getQuickCode(params);
            pService.SaveAll(Ms, Codes);

        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/addTwo")
    @ResponseBody
    public successResult AddTwo(String machines, String Data, HttpServletRequest request) {
        successResult result = new successResult();
        try {
            List<String> Codes = Arrays.stream(Data.split(",")).collect(Collectors.toList());
            String[] Ms = machines.split(",");
            pService.SaveAll(Ms, Codes);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/removeTask")
    @ResponseBody
    public successResult RemoveTask(String No) {
        successResult result = new successResult();
        try {
            pService.RemoveTask(No);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

}
