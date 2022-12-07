package com.zhide.govwatch.define;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IWorkBenchService {
    List<Map<String,Object>> getAddFee(HttpServletRequest request) throws Exception;
    List<Map<String,Object>> getPatent(HttpServletRequest request) throws Exception;
    List<Map<String,Object>> getRecentFee(HttpServletRequest request) throws Exception;
}
