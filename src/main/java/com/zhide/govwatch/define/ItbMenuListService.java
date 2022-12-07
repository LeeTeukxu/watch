package com.zhide.govwatch.define;

import com.zhide.govwatch.model.tbMenuList;

import java.io.UnsupportedEncodingException;
import java.util.List;


public interface ItbMenuListService {
    List<tbMenuList> getAll();

    List<tbMenuList> getAllByCanUse() throws UnsupportedEncodingException, Exception;

    List<tbMenuList> saveAll(List<tbMenuList> tbMenuLists);

    List<tbMenuList> getVisibleMenus(int roleId);

    boolean removeOne(List<Integer> Ids);
}
