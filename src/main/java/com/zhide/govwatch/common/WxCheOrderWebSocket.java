package com.zhide.govwatch.common;

import com.zhide.govwatch.model.tbOrderList;
import com.zhide.govwatch.repository.tbOrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.server.PathParam;

@ServerEndpoint("/ws/asset/{OrderNo}")
@Component
public class WxCheOrderWebSocket {
    private static int onlineCount = 0;
    private Session session;
    private String OrderNo = "";
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WxCheOrderWebSocket> webSocket = new CopyOnWriteArraySet<WxCheOrderWebSocket>();
    @Autowired
    WxChatScanResultThread thread1;
    Thread thread;

    @OnOpen
    public void onOpen(@PathParam("OrderNo") String OrderNo, Session session){
        this.OrderNo = OrderNo;
        this.session = session;

        thread1 = new WxChatScanResultThread();
        thread1.setOrderNo(OrderNo);
        thread = new Thread(thread1);

        addOnlineCount();
        webSocket.add(this);
        thread.start();
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        thread1.StopMe();
        webSocket.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        try {
            SendMessage();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static synchronized void addOnlineCount(){
        WxCheOrderWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount(){
        WxCheOrderWebSocket.onlineCount--;
    }

    public void SendMessage() throws IOException {
        for (WxCheOrderWebSocket item : webSocket){
            item.session.getBasicRemote().sendText("2");
        }
    }
}
//this.session.getBasicRemote().sendText(findOne.get().getPayState().toString());
