package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.view_patentMemo;

import java.util.List;

public interface FeeItemMemoMapper {
    List<view_patentMemo> getAllByIds(String Type, List<String> IDS);
}
