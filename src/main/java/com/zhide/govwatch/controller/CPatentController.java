package com.zhide.govwatch.controller;

import com.zhide.govwatch.common.PageableUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ICpatentService;
import com.zhide.govwatch.model.patentMongo;
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
 * @ClassName: CPantentController
 * @Author: JYM
 * @*TODO:
 * @CreateTime: 2022年2月13日 16:06
 **/
@Controller
@RequestMapping("/cpatent")
public class CPatentController {
    @Autowired
    ICpatentService pService;
    @Autowired
    MongoTemplate pMongoRep;
    @RequestMapping("/index")
    public String Index(Map<String,Object> model){
        return "/cpatent/index";
    }
    @ResponseBody
    @RequestMapping("/getData")
    public pageObject getData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            Map<String, Object> params = pService.getParameters(request);
            result = pService.GetData(params);
        } catch (Exception ax) {
            result.raiseException(ax);
            ax.printStackTrace();
        }
        return result;
    }
}
