package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.tbLoginUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ILoginUserService {
    Map<String, Integer> getAllByNameAndID();

    Map<Integer, String> getAllByIDAndName();

    pageObject getData(HttpServletRequest request) throws Exception;

    pageObject getMineData(HttpServletRequest request) throws Exception;

    boolean save(tbLoginUser login) throws Exception;

    tbLoginUser SaveMine(tbLoginUser login) throws Exception;

    void RemoveUser(Integer UserID) throws Exception;

    public String CreateCode(String BaseUrl, Integer UserID) throws Exception;
}
