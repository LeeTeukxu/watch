package com.zhide.govwatch.common;

public class PayConfigUtil {
    // 支付成功回调接口，我们在WcChatController中编写的回调接口的公网地址
    public static final String NOTIFY_URL = "https://govfee.zfbip.com/WeChat/wxnotify"; //扫码成功后回调地址。待定;

    public static final String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //微信查询地址
    public static final String WXORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
}
