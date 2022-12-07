package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.config.CacheableTtl;
import com.zhide.govwatch.model.patent;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IPatentService {
    Map<String, String> GetLoginUserHash();

    Map<String, String> GetDepListHash();

    List<String> getQuickCode(Map<String, Object> params);

    pageObject getData(Map<String, Object> parameters);

    pageObject getByShenqinghsIn(Map<String, Object> parameters);

    Map<String, Object> getParameters(HttpServletRequest request) throws Exception;

    void importAll(String Mode, String CollectionName) throws Exception;

    List<String> getLawStatus();

    @CacheableTtl(value = "getPatentTotal", ttl = 600)
    List<Map<String, Object>> getPatentTotal(int UserID, String RoleName);

    patent getsqh(String SHENQINGH);

    patent uppateninfo(String SHENQINGH, String ProvinceName, String CityName, String CountyName, String DAILIJGMC,
            String ADDRESS, Integer ClientID, String ClientName);

    void SaveAll(String[] Machines, List<String> Codes);

    pageObject getShowTask(HttpServletRequest request) throws Exception;

    pageObject getUpdateMain(HttpServletRequest request) throws Exception;

    void RemoveTask(String No) throws Exception;
}
