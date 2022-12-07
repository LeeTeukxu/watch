package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CPatentMapper {
    List<Map<String, Object>> GetData(Map<String, Object> params);
}
