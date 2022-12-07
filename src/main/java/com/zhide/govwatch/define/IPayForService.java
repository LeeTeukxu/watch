package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPayForService {
    int getCount(String RoleName,Integer UserID) throws Exception;
    pageObject getData(HttpServletRequest request) throws Exception;
}
