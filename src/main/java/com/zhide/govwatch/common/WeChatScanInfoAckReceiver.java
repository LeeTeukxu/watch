package com.zhide.govwatch.common;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zhide.govwatch.GovwatchApplication;
import com.zhide.govwatch.define.IPhoneOrderService;
import com.zhide.govwatch.model.WxCusInfoBean;
import com.zhide.govwatch.model.getOrderCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class WeChatScanInfoAckReceiver implements ChannelAwareMessageListener {
    private final Logger logger = LoggerFactory.getLogger(WeChatScanInfoAckReceiver.class);
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private WxCusInfoBean ibean;
    private static WxCusInfoBean bean;

    private static String AppId;
    private static String ApiKey;
    private static String MchId;
    @PostConstruct
    public void Init(){
        bean = ibean;
        AppId = bean.getAppId();
        ApiKey = bean.getApiKey();
        MchId = bean.getMchId();
    }

    @Autowired
    IPhoneOrderService phoneOrderService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
//        //手动处理消费队列并修改redis的状态为2(当前端监测pushMessageToUser的redis为2时代表扫码成功)
////        logger.info("消息队列开始监控");
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
//            String msg = new String(message.getBody(), Charset.defaultCharset());
//            String result = mapToString(msg, "messageData");
//            String OrderNo = mapToString(msg, "orderNo");
//            String WeChatScanResult = GetWeChatScanResult(OrderNo);
//            if (WeChatScanResult.equals("success")) {
////            if (redisTemplate.opsForValue().get(OrderNo) != null && redisTemplate.opsForValue().get(OrderNo).equals("2")) {
//                //消费成功，确认消息
//                redisTemplate.opsForValue().set(OrderNo, "2");
//                channel.basicAck(deliveryTag, false);
////                logger.info("消息队列手动确认成功");
////                Thread.sleep(10000);
//            }else if ( redisTemplate.opsForValue().get(OrderNo) != null && redisTemplate.opsForValue().get(OrderNo).equals("discon")) {
//                channel.basicAck(deliveryTag, false);
//            }else {
//                channel.basicNack(deliveryTag, false, true); //nack返回false，并重新回到队列
////                logger.info("消息重新提交队列");
////                Thread.sleep(10000);
//            }
//        } catch (Exception e) {
//            channel.basicReject(deliveryTag, false);
//            e.printStackTrace();
//        }
    }

    @RabbitHandler
    public void process(Map testMessage, Channel channel) {
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }

    //{key=value,key=value,key=value} 格式转换成map
    private String mapToString(String str, String key) {
        Object obj = JSON.parse(str);
        Map map = (Map)obj;
        return map.get(key).toString();
    }



    public String GetWeChatScanResult(String OrderNo){
        String result = "";
        try {
            getOrderCode getOrderCode = new getOrderCode();
            getOrderCode.setAppId(AppId);
            getOrderCode.setMchId(MchId);
            getOrderCode.setOrderId(OrderNo);
            getOrderCode.setNonce_str(UUID.randomUUID().toString().replaceAll("-",""));
            getOrderCode.setApiKey(ApiKey);
            Map<String, Object> map = phoneOrderService.getWeChatScanResult(getOrderCode);

            if (map.size() > 0) {
                if (map.get("trade_state_desc").equals("支付成功")) {
                    result = "success";
                } else if (map.get("trade_state_desc").equals("支付失败")) {
                    result = "fail";
                } else result = "wait";
            }else result = "wait";
        }catch (Exception ax){
            ax.printStackTrace();
        }
        return result;
    }
}
