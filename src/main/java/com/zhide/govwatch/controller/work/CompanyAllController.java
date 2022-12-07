package com.zhide.govwatch.controller.work;

import com.zhide.govwatch.common.PageableUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ICompanyAllService;
import com.zhide.govwatch.model.companyAllMongo;
import com.zhide.govwatch.model.techCompanyMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompanyAllController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月30日 15:46
 **/
@RequestMapping("/companyall")
@Controller
public class CompanyAllController {
    @Autowired
    MongoTemplate pMongoRep;
    @Autowired
    ICompanyAllService companyAllService;

    @RequestMapping("/index")
    public String Index() {
        return "/work/allCompany/index";
    }

    @RequestMapping("/getImportResult")
    @ResponseBody
    public pageObject getImportResult(String collectionName, HttpServletRequest request) {
        pageObject object = new pageObject();
        try {
            Pageable pageable = PageableUtils.create(request);
            Query query = new Query();
            query.with(pageable);
            List<companyAllMongo> datas = pMongoRep.find(query, companyAllMongo.class, collectionName);
            long total = pMongoRep.count(new Query(), collectionName);
            object.setTotal(total);
            object.setData(datas);
        } catch (Exception ax) {
            object.raiseException(ax);
        }
        return object;
    }

    @RequestMapping("/getTechResult")
    @ResponseBody
    public pageObject getTechResult(String collectionName, HttpServletRequest request) {
        pageObject object = new pageObject();
        try {
            Pageable pageable = PageableUtils.create(request);
            Query query = new Query();
            query.with(pageable);
            List<techCompanyMongo> datas = pMongoRep.find(query, techCompanyMongo.class, collectionName);
            long total = pMongoRep.count(new Query(), collectionName);
            object.setTotal(total);
            object.setData(datas);
        } catch (Exception ax) {
            object.raiseException(ax);
        }
        return object;
    }

    @RequestMapping("/importData")
    public String ImportData() {
        return "/work/allCompany/ImportData";
    }

    @RequestMapping("/importTech")
    public String ImportTech() {
        return "/work/allCompany/ImportTech";
    }

    @RequestMapping("/importAll")
    @ResponseBody
    public successResult importAll(String Mode, String collectionName) {
        successResult result = new successResult();
        try {
            Map<String, Object> OO = companyAllService.ImportAll(collectionName);
            result.setData(OO);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/importAll1")
    @ResponseBody
    public successResult importAll1(String collectionName) {
        successResult result = new successResult();
        try {
            Map<String, Object> OO = companyAllService.ImportAll1(collectionName);
            result.setData(OO);
        } catch (Exception ax) {

            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getData")
    @ResponseBody
    public pageObject getData(HttpServletRequest request) {
        pageObject result = null;
        try {
            result = companyAllService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }
}
