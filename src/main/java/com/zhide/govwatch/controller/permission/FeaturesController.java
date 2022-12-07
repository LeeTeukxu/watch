package com.zhide.govwatch.controller.permission;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.iFunctionPermissionListService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.functionPermissionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission/features")
public class FeaturesController {

    @Autowired
    iFunctionPermissionListService ifunctionPermissionListService;

    @RequestMapping("/index")
    public String Index() {
        return "/permission/features/index";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public pageObject getAll(HttpServletRequest request) {
        pageObject result = new pageObject();
        LoginUserInfo Info = CompanyContext.get();
        try {
            result = ifunctionPermissionListService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public successResult Save(String Data) {
        successResult result = new successResult();
        try {
            List<functionPermissionList> items = JSON.parseArray(Data, functionPermissionList.class);
            ifunctionPermissionListService.SaveAll(items);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/remove")
    public successResult Remove(Integer FID) {
        successResult result = new successResult();
        try {
            boolean X = ifunctionPermissionListService.Remove(FID);
            result.setSuccess(X);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
