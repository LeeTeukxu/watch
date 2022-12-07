package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.view_patentMemo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface patentMemoMapper {
    List<view_patentMemo> getAllByIds(List<String> IDS);
}
