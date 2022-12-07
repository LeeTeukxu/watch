package com.zhide.govwatch.define;


import com.zhide.govwatch.model.tbLoginUser;
import com.zhide.govwatch.model.v_LoginUser;

import java.util.List;

public interface ItbLoginUserService {
    tbLoginUser save(tbLoginUser user) throws Exception;
    tbLoginUser getById(int userId);
    List<tbLoginUser> getAll();
    v_LoginUser findByCode(String Code);
}
