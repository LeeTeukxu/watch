package com.zhide.govwatch.define;

import com.zhide.govwatch.model.WxCusInfoBean;
import com.zhide.govwatch.model.getOrderCode;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IPhoneOrderService {
    Map<String, String> doUnifiedOrder(WxCusInfoBean wxCusInfoBean, Map<String, Object> param, HttpServletRequest request) throws Exception;
    Map<String, Object> getWeChatScanResult(getOrderCode getOrderCode);
}
