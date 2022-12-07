package com.zhide.govwatch.define;

import com.zhide.govwatch.common.successResult;

public interface ISocketIOService {

    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息给指定客户端
     *
     * @param userId:     客户端唯一标识
     * @param msgContent: 消息内容
     */
    successResult pushMessageToUser(String userId, String msgContent);
    void Sned(String msgContent);
}
