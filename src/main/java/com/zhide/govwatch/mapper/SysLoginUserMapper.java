package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysLoginUserMapper {
    @Select(value = "Select RoleName  as Name,UserID as ID from v_loginUser")
    List<Map<String, Object>> getAllByIDAndName();
    List<Map<String,Object>> getData(Map<String,Object> args);
    @Select(value = "Select * from dbo.GetUserTreeByRole(${RoleID})")
    List<Map<String, Object>> getAllUserByRole(Integer RoleID);
}
