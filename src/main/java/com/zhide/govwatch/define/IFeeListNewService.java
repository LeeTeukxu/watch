package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.ComboboxItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IFeeListNewService {
    pageObject getData(HttpServletRequest request) throws Exception;

    List<ComboboxItem> getFeeItems();

    List<ComboboxItem> getZLItems();

    boolean addPayForList(List<Integer> feeItems);

    void ChangeXMoney(List<Integer> IDS, double Money);

    void ChangePayForStatus(List<Integer> IDS, String Status);

    void ChangeSXMoney(List<Integer> IDS, double Money);
}
