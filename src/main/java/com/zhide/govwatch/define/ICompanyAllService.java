package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName: ICompanyAllService
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月31日 11:46
 **/
public interface ICompanyAllService {
    Map<String, Object> ImportAll(String CollectionName) throws Exception;

    Map<String, Object> ImportAll1(String CollectionName) throws Exception;

    pageObject getData(HttpServletRequest request) throws Exception;
}
