package com.zhide.govwatch.define;

import com.zhide.govwatch.model.TreeListItem;
import com.zhide.govwatch.model.tbRoleClass;

import java.util.List;

public interface ItbRoleClassService {
    List<tbRoleClass> getAll();

    List<TreeListItem> getAllCanuseItems(boolean canUse);

    boolean saveAll(List<tbRoleClass> items);

    boolean removeAll(List<Integer> ids);
}
