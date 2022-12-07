package com.zhide.govwatch.controller.watch;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IApplyListService;
import com.zhide.govwatch.model.LoginUserInfo;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/watch/applyList")
public class ApplyWatchListController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(ApplyWatchListController.class);
    @Autowired
    IApplyListService newService;

    @PostMapping("/clientFYJK")
    @ResponseBody
    public pageObject clientFYJK(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            Map<String, Object> params = newService.getParameters(request);
            result = newService.clientFYJK(params);
        } catch (Exception ax) {
            logger.info(ax.getMessage());
        }
        return result;
    }
}
