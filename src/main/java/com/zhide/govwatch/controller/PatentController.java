package com.zhide.govwatch.controller;

import com.zhide.govwatch.common.PageableUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPatentService;
import com.zhide.govwatch.model.patentMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PantentController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月06日 15:54
 **/
@Controller
@RequestMapping("/patent")
public class PatentController {
    @Autowired
    IPatentService pService;
    @Autowired
    MongoTemplate pMongoRep;
    @RequestMapping("/index")
    public String Index(Map<String,Object> model){
        return "/patent/index";
    }
    @ResponseBody
    @RequestMapping("/getData")
    public pageObject getData(HttpServletRequest request) {
        pageObject result = null;
        try {
            Map<String, Object> params = pService.getParameters(request);
            result = pService.getData(params);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }
    @RequestMapping("/ImportPantentData")
    public String ImportPantentData(String mode,String  type, Map<String, Object> map) {
        map.put("mode", mode);
        map.put("type",type);
        return "/patent/ImportPatentData";
    }
    @RequestMapping("/getImportResult")
    @ResponseBody
    public pageObject getImportResult(String collectionName,HttpServletRequest request){
        pageObject object=new pageObject();
        try {
            Pageable pageable = PageableUtils.create(request);
            Query query=new Query();
            query.with(pageable);
            List<patentMongo> datas = pMongoRep.find(query, patentMongo.class,collectionName);
            long total=pMongoRep.count(new Query(),collectionName);
            object.setTotal(total);
            object.setData(datas);
        }catch(Exception ax){
            object.raiseException(ax);
        }
        return object;
    }
    @RequestMapping("/importAll")
    @ResponseBody
    public successResult importAll(String Mode,String collectionName){
        successResult result=new successResult();
        try {
            pService.importAll(Mode,collectionName);
        }
        catch(Exception ax){
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getByShenqinghsIn")
    @ResponseBody
    public pageObject getByShenqinghsIn( HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            Map<String, Object> params = pService.getParameters(request);
            result = pService.getByShenqinghsIn(params);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }
}
