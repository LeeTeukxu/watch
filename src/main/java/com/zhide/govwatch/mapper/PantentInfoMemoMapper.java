package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.view_patentMemo;

import java.util.List;

public interface PantentInfoMemoMapper {
    List<view_patentMemo> getAllByIds(List<String> IDS);
}
