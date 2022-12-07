package com.zhide.govwatch.common;

import org.springframework.stereotype.Component;

/**
 * @ClassName: allMessageNames
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年01月05日 15:56
 **/
@Component
public class allRabbitMQQueueNames {
    public String patentInfoChanged() {
        return "patentInfoChanged";
    }
    public String govFeeItemAccepted(){
        return "govFeeItemAccepted";
    }
    public String pantentItemAccepted(){
        return "pantentItemAccepted";
    }
    public String WeChatScanInfo() { return "WeChatScanInfo"; }
    public String WeChatScanInfoChanged() {
        return "weChatScanInfoChanged";
    }

    public String babysitter() {
        return "babysitter";
    }

    public String WxChatScanInfoMQ() {
        return "WxChatScanInfoMQ";
    }
    public String WxChatScanInfoMQChanged() { return "WxChatScanInfoChanged"; }
}
