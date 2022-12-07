package com.zhide.govwatch.common;

import com.zhide.govwatch.model.tbOrderList;
import com.zhide.govwatch.repository.tbOrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@Component
public class WxChatScanResultThread implements Runnable {
    @Autowired
    private tbOrderListRepository iorderListRepository;
    private static tbOrderListRepository orderListRepository;
    String OrderNo = "";

    private boolean StopMe = true;

    public void setOrderNo(String OrderNo){
        this.OrderNo = OrderNo;
    }

    @PostConstruct
    public void Init(){
        orderListRepository = iorderListRepository;
    }
    public void StopMe(){
        StopMe = false;
    }

    public void run() {
        WxCheOrderWebSocket webSocket = new WxCheOrderWebSocket();
        while (StopMe) {
            try {
                Optional<tbOrderList> findOne = orderListRepository.findAllByOrderNo(OrderNo);
                if (findOne.get().getPayState() == 2) {
                    webSocket.onMessage("2");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }catch (IOException ax){
                ax.printStackTrace();
            }
        }
    }
}
