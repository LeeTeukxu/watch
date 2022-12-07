package com.zhide.govwatch.define;


import com.zhide.govwatch.model.smtpAccount;

public interface IsmtpAccountService {
    smtpAccount Save(smtpAccount sAccount, String UserName) throws Exception;
}
