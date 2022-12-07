package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPatentPermissionService {
    boolean save(List<String> shenqinghs) throws Exception;

    boolean remove(List<String> shenqinghs) throws Exception;
}
