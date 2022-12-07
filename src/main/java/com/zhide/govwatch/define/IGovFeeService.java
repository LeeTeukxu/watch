package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IGovFeeService {
    pageObject getData(HttpServletRequest request) throws Exception;
    List<Map<String, Object>> getGovCount(Integer RoleID ,Integer UserID) throws Exception;
}
