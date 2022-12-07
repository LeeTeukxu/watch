package com.zhide.govwatch.define;

import com.zhide.govwatch.model.tbGovFee;

import java.util.List;

public interface ItbGovFeeService {
    boolean save(List<String> shenqinghs) throws Exception;

    List<tbGovFee> getGovFeeByAppNoToMap(String AppNo);
}
