package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SearMapper {
    List<Map<String, Object>> GetTopMonitor(Map<String, Object> params);

    List<Map<String, Object>> GetAllMonitor(Map<String, Object> params);
}
