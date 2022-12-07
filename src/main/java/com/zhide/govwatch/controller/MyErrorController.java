package com.zhide.govwatch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: MyErrorController
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月16日 16:37
 **/
@Controller
public class MyErrorController implements ErrorController {
    Logger log= LoggerFactory.getLogger(MyErrorController.class);
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        String Url= request.getHeader("referer");
        //log.info("访问:"+Url+"无效，进入异常跳转");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return Integer.toString(statusCode);
    }
}
