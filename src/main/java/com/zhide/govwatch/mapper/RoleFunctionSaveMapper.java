package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleFunctionSaveMapper {
    @Select(value = "SELECT rtrim(ltrim(FunctionPermissionList.Name)) as Name FROM FunctionPermissionList " +
            "INNER JOIN tbRoleFunctionSave ON FunctionPermissionList.FID = tbRoleFunctionSave.FunID " +
            "WHERE (tbRoleFunctionSave.RoleID =#{RoleID}) AND (tbRoleFunctionSave.MenuID = #{MenuID})")
    List<String> getAllRoleFunctions(String MenuID, Integer  RoleID);

    @Select(value = "Select rtrim(ltrim(b.Name))  from tbPermissionItem a inner join FunctionPermissionList b on a" +
            ".FunID=b.FID  " +
            "Where a.MenuID=#{MenuID}")
    List<String> getAllFunctions(String MenuID);
    @Select(value = "SELECT distinct(replace(a.Name,' ','')) as Name  FROM  FunctionPermissionList a INNER JOIN tbRoleFunctionSave b ON a.FID = b.FunID inner join tbRoleClass c on b.RoleID=c.RoleID  Where RoleName=#{RoleName}")
    List<String> getAllFunctionsByRole(String RoleName);
}
