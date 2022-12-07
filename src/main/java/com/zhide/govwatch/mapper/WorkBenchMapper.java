package com.zhide.govwatch.mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkBenchMapper
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月28日 10:27
 **/
public interface WorkBenchMapper {
    List<Map<String, Object>> getAddFee(Map<String, Object> params);
    List<Map<String,Object>> getPatent(Map<String,Object> params);
    List<Map<String,Object>> getRecentFee(Map<String,Object> params);
}
