package com.zhide.govwatch.controller.system;

import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IElasticsearchService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @ClassName: ElasticsearchController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月23日 9:31
 **/
@Controller
@RequestMapping("/elasticsearch")
public class ElasticsearchController {
    @Autowired
    IElasticsearchService elService;
    @RequestMapping("/index")
    public String Index(){
        return "/system/elasticsearch/index";
    }
    @RequestMapping("/rebuildIndex")
    @ResponseBody
    public successResult RebuildIndex(){
        successResult result=new successResult();
        try {
            String X= elService.RebuildIndex();
            result.setData(X);
        }
        catch(Exception ax){
            result.raiseException(ax);
        }
        return result;
    }


    @RequestMapping("/deleteIndex")
    @ResponseBody
    public successResult DeleteIndex(){
        successResult result=new successResult();
        try {
            String X= elService.DeleteIndex();
            result.setData(X);
        }
        catch(Exception ax){
            result.raiseException(ax);
        }
        return result;
    }
}
