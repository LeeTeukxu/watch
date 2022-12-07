package com.zhide.govwatch.mapper;

import com.zhide.govwatch.model.view_govfee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface tbGovFeeMapper {
    @Delete("DELETE FROM tbgovfee WHERE AppNo=#{AppNo}")
    int delGovFee(String AppNo);

    @Select(value = "SELECT * FROM view_govfee WHERE SHENQINGH=#{SHENQINGH} AND MONEY=${MONEY} AND FEENAME=#{FEENAME} AND JIAOFEIR=#{JIAOFEIR}")
    Optional<view_govfee> getGovFeeBySHENQINGHMONEYFEENAMEJIAOFEIR(String SHENQINGH, String MONEY, String FEENAME, String JIAOFEIR);
}
