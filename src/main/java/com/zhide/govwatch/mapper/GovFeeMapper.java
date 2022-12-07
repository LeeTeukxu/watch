package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.gov;
import com.zhide.govwatch.model.view_govfee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface GovFeeMapper {
    List<Map<String,Object>> getData(Map<String, Object> params);
    List<Map<String,Object>> getGovCount(Map<String, Object> params);

    List<Map<String,Object>> getQuickData(Map<String,Object> params);
    int getQuickCount(Map<String,Object> params);
    List<view_govfee> getAll(Integer Begin,Integer End);
}
