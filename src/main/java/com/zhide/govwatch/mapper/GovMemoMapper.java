package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.feeMemo;
import com.zhide.govwatch.model.v_GovFeeInfoMemo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GovMemoMapper {

    List<v_GovFeeInfoMemo> getAllBySHENQINGHAndFEENAME(@Param("SHENQINGH") List<String> SHENQINGH, @Param("FEENAME") List<String> FEENAME);
}
