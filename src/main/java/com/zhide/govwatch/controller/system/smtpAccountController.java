package com.zhide.govwatch.controller.system;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IsmtpAccountService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.smtpAccount;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/systems/smtpAccount")
public class smtpAccountController {

    @Autowired
    IsmtpAccountService ismtpAccountService;

    @RequestMapping("/save")
    @ResponseBody
    public successResult save(HttpServletRequest request) {
        successResult result = new successResult();
        smtpAccount res = null;
        try {
            String Data = request.getParameter("Data");
            String UserName = request.getParameter("UserName");
            if (Strings.isEmpty(Data) == false) {
                smtpAccount sAccount = JSON.parseObject(Data, smtpAccount.class);
                res = ismtpAccountService.Save(sAccount, UserName);
                result.setData(res);
            } else throw new Exception("数据格式不正确！");
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
