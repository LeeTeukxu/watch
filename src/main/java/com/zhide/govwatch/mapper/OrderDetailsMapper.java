package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.view_OrderDetails;

import java.util.List;
import java.util.Map;


public interface OrderDetailsMapper {
    List<view_OrderDetails> getData(Map<String, Object> params);
}
