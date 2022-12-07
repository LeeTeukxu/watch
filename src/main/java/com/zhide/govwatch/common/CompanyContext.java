package com.zhide.govwatch.common;

import com.zhide.govwatch.model.LoginUserInfo;
import org.springframework.stereotype.Component;

@Component
public class CompanyContext {
    private static ThreadLocal<LoginUserInfo> currentTenant = new ThreadLocal<>();
    public static  LoginUserInfo get(){
        return currentTenant.get();
    }
    public static  void set(LoginUserInfo info){
        currentTenant.set(info);
    }
    public static void clear(){
        currentTenant.set(null);
    }
}
