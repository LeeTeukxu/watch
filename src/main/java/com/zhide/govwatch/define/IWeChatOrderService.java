package com.zhide.govwatch.define;

import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.model.tbOrderList;
import com.zhide.govwatch.model.view_OrderDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IWeChatOrderService {
    tbOrderList Save(String SHENQINGHS, String Amounts, String FEENAMES, String JIAOFEIRS) throws Exception;
    pageObject getData(HttpServletRequest request) throws Exception;
    List<Map<String, Object>> getBasics(HttpServletRequest request) throws Exception;
    pageObject getOrderDetailsData(String OrderNo, HttpServletRequest request) throws Exception;
    pageObject getPaymentOrderDetailsData(String OrderNo, HttpServletRequest request) throws Exception;
    void UpdateDjStateAndPayState(String OrderNo, tbOrderList orderList) throws Exception;
    tbOrderList GetOrderPayState(String OrderNo) throws Exception;
    void ComplateDJ(List<Integer> IDS) throws Exception;
    boolean Remove(List<Integer> IDS) throws Exception;
    List<Map<String, Object>> getStatistic(HttpServletRequest request) throws Exception;
    String ResetOrderNo(String OrderNo) throws Exception;
    float GetAfterPriceTotal(view_OrderDetails view_orderDetails, String SHENQINGH, String FEENAME);
}
