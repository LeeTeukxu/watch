package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.gov;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderListMapper {
    List<Map<String,Object>> getData(Map<String, Object> params);
    List<Map<String,Object>> getBasicsData(Map<String, Object> params);
    List<Map<String,Object>> getStatistic(Map<String, Object> params);
}
