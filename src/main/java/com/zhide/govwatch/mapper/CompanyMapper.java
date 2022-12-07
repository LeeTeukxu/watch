package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CompanyMapper {

    List<Map<String, Object>> getPageData(Map<String, Object> params);

    List<Map<String, Object>> getData(Map<String, Object> params);
}
