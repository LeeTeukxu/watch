package com.zhide.govwatch.define;

import com.zhide.govwatch.model.ComboboxItem;
import com.zhide.govwatch.model.TreeListItem;

import java.util.List;

public interface ItbDictDataService {
    List<TreeListItem> getAllByDtID(int dtId);

    List<ComboboxItem> getByDtId(int dtId);

    List<ComboboxItem> getByDtIdAndCanUseTrue(int dtId);
}
