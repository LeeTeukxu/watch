package com.zhide.govwatch.controller.PatentContact;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPatentContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/PatentContact")
public class PatentContactController {
    @Autowired
    IPatentContactService iPatentContactService;

    @RequestMapping("/index")
    public String Index(){
        return "/PatentContact/index";
    }

    @RequestMapping("/save")
    @ResponseBody
    public successResult Save(HttpServletRequest request){
        successResult result = new successResult();
        try {
            String Mail = request.getParameter("Mail");
            JSONArray jsonArray = (JSONArray) JSONArray.parse(Mail);
            iPatentContactService.save(jsonArray);
        }catch (Exception ax){
            ax.printStackTrace();
            result.setSuccess(false);
            result.setMessage(ax.getMessage());
        }
        return result;
    }
}
