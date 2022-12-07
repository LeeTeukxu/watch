package com.zhide.govwatch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ViewPatentMapper {
    List<Map<String, Object>> getData(Map<String, Object> params);

    List<Map<String, Object>> getQuickData(Map<String, Object> params);

    List<String> getQuickCode(Map<String, Object> params);

    Integer getQuickCount(Map<String, Object> params);

    List<Map<String, Object>> getByShenqinghsIn(Map<String, Object> params);

    List<Map<String, Object>> getPatentTotal(String RoleName, Integer UserID);

    @Select(value = "Select Distinct LawStatus from patent  where Length(NULLIF(LAWSTATUS,''))>1 Order by LAWSTATUS ")
    List<String> getLawStatus();

    List<Map<String, Object>> getShowTask(Map<String, Object> params);

    List<Map<String, Object>> getUpdateMain(Map<String, Object> params);
}
