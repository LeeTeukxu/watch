package com.zhide.govwatch.define;

import java.util.List;

public interface IRoleFunctionService {
    List<String> GetAllRoleFunctions(Integer RoleID, String MenuID);

    List<String> GetAllFunctions(String MenuID);

    List<String>GetAllFunctionsByRole(String RoleName);
}
