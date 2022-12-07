package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepListMapper {
    @Select(value = "SELECT DepID AS ID,UPPER(REPLACE(Name,' ','')) AS Name FROM tbDepList")
    List<Map<String,Object>> getAllByNameAndID();


}
