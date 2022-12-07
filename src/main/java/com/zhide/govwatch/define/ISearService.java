package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ISearService {
    pageObject GetTopMonitor(HttpServletRequest request) throws Exception;

    pageObject GetAllMonitor(HttpServletRequest request) throws Exception;

    pageObject GetByShenqinghsIn(HttpServletRequest request) throws Exception;
}
