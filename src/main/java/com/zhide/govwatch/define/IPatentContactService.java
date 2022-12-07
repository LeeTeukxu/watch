package com.zhide.govwatch.define;

import com.alibaba.fastjson.JSONArray;
import com.zhide.govwatch.model.tbLoginUser;
import com.zhide.govwatch.model.tbPatentContact;

public interface IPatentContactService {

    boolean save(JSONArray jsonArray) throws Exception;
}
