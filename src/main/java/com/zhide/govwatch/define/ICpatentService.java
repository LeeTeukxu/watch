package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ICpatentService {
    pageObject GetData(Map<String, Object> parameters);
    Map<String, Object> getParameters(HttpServletRequest request) throws Exception;
}
