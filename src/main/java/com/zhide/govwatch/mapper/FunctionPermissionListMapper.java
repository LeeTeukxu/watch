package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FunctionPermissionListMapper {

    List<Map<String, Object>> getData(Map<String, Object> params);
}
