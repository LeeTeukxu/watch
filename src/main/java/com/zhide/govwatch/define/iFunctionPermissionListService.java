package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.functionPermissionList;
import com.zhide.govwatch.model.tbRoleClass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface iFunctionPermissionListService {
    pageObject getData(HttpServletRequest request) throws Exception;

    boolean SaveAll(List<functionPermissionList> items);

    boolean Remove(Integer FID);
}
