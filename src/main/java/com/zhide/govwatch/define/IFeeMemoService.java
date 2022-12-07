package com.zhide.govwatch.define;

import com.zhide.govwatch.model.feeMemo;
import com.zhide.govwatch.model.followRecord;

import java.util.List;

public interface IFeeMemoService {
    boolean SaveAll(List<feeMemo> memoList);
    List<feeMemo> GetData(String SHENQINGH, String Type);
    boolean DeleteByID(int ID);
    void SaveImageFollowRecord(feeMemo record) throws Exception;
    List<String> getImages(String MID) throws Exception;
}
