package com.zhide.govwatch.define;

import com.zhide.govwatch.model.ComboboxItem;

import java.util.List;

public interface ITZSService {
    List<ComboboxItem> getItemsByCode(String[] codes);
}
