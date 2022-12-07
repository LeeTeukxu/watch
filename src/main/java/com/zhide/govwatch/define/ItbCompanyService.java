package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.TreeNode;
import com.zhide.govwatch.model.tbCompany;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ItbCompanyService {
    pageObject getData(HttpServletRequest request) throws Exception;
    tbCompany Save(tbCompany company, String mode) throws Exception;
    boolean remove(List<String> ids);
    List<TreeNode> getCompanyList() throws Exception;
}
