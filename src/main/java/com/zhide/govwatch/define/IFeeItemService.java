package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.ComboboxItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IFeeItemService {
    pageObject getData(HttpServletRequest request) throws Exception;

    List<ComboboxItem> getFeeItems();

    List<ComboboxItem> getZLItems();

    boolean ChangePayMark(List<Integer> IDS, int PayState);

    boolean AddToFeeList(List<Integer> IDS, String JFQD);

    boolean ChangeJiaoDuiMoney(List<Integer> IDS, String Money);
}
