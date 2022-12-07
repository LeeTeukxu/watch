package com.zhide.govwatch.controller.work;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ItbCompanyService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbCompany;
import com.zhide.govwatch.repository.tbCompanyRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/work/company")
public class CompanyController {
    @Autowired
    ItbCompanyService companyService;

    @Autowired
    tbCompanyRepository companyRepository;

    @RequestMapping("/index")
    public String Index(Map<String, Object> model) {
        model.put("Mode", "Add");
        return "/work/company/index";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public pageObject getData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = companyService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/add")
    public String Add(Map<String, Object> model) {
        model.put("Mode", "Add");
        model.put("LoadData", "{}");
        model.put("AllCompany", JSON.toJSONString(new ArrayList<>()));
        return "/work/company/edit";
    }

    @RequestMapping("/browse")
    public String Browse(int CompanyID, Map<String, Object> model) {
        model.put("Mode", "look");
        model.put("AllCompany", "{}");
        Optional<tbCompany> findtb = companyRepository.findById(CompanyID);
        if (findtb.isPresent()) {
            model.put("LoadData", JSON.toJSONString(findtb.get()));
        } else model.put("LoadData", "{}");
        return "/work/company/edit";
    }

    @RequestMapping("/edit")
    public String Edit(int CompanyID, Map<String, Object> model) {
        model.put("CompanyID", Integer.toString(CompanyID));
        model.put("Mode", "Edit");
        model.put("AllCompany", "{}");
        Optional<tbCompany> findtb = companyRepository.findById(CompanyID);
        if (findtb.isPresent()) {
            model.put("LoadData", JSON.toJSONString(findtb.get()));
        } else model.put("LoadData", "{}");
        return "/work/company/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public successResult save(HttpServletRequest request) {
        successResult result = new successResult();
        tbCompany company = null;
        try {
            String companys = request.getParameter("company");
            String mode = request.getParameter("mode");
            if (Strings.isEmpty(companys) == false) {
                company = JSON.parseObject(companys, tbCompany.class);
                company = companyService.Save(company,mode);
                result.setData(company);
            } else throw new Exception("数据格式不正确！");
        } catch (Exception ax) {

            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public successResult remove(String IDS) {
        successResult result = new successResult();
        try {
            List<String> ids = JSON.parseArray(IDS, String.class);
            companyService.remove(ids);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
