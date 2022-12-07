package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.gov;
import com.zhide.govwatch.model.view_goypay;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GovPayMapper {
    List<Map<String,Object>> getData(Map<String, Object> params);
    List<Map<String,Object>> getQuickData(Map<String,Object> params);
    int  getGovCount(Map<String, Object> params);
    List<view_goypay>getAll(Integer Begin,Integer End);
}
