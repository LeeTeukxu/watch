package com.zhide.govwatch.mapper;

import java.util.List;
import java.util.Map;

public interface TicketsMapper {
    List<Map<String, Object>> getData(Map<String, Object> params);
}
