package com.zhide.govwatch.service;

import com.alibaba.excel.util.CollectionUtils;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.zhide.govwatch.common.RabbitUtil;
import com.zhide.govwatch.common.allRabbitMQQueueNames;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPhoneOrderService;
import com.zhide.govwatch.define.ISocketIOService;
import com.zhide.govwatch.imple.WeChatOrderServiceImpl;
import com.zhide.govwatch.model.WxCusInfoBean;
import com.zhide.govwatch.model.getOrderCode;
import com.zhide.govwatch.model.tbOrderList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class SocketIOServiceImpl implements ISocketIOService {
    Logger logger = LoggerFactory.getLogger(SocketIOServiceImpl.class);

    @Autowired
    IPhoneOrderService phoneOrderService;
    @Autowired
    RabbitUtil rabbitUtil;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate template;
    @Autowired
    SocketIOServer socketIOServer;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    allRabbitMQQueueNames allMessage;
    @Autowired
    WeChatOrderServiceImpl weChatOrderService;
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
    /**
     * 存放已连接的客户端
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * 自定义事件`push_data_event`,用于服务端与客户端通信
     */
    private static final String PUSH_DATA_EVENT = "push_data_event";
    private static final String PUSH_DISCONNECT = "disconnect";

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     */
    @PostConstruct
    private void autoStartup() {
//        logger.info("Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动");
//        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }


    @Override
    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            System.out.println("************ 客户端： " + getIpByClient(client) + " 已连接 ************");
            // 自定义事件`connected` -> 与客户端通信  （也可以使用内置事件，如：Socket.EVENT_CONNECT）
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.put(userId, client);
            }
            client.sendEvent("connected", "你成功连接上了哦...");
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String clientIp = getIpByClient(client);
            System.out.println(clientIp + " *********************** " + "客户端已断开连接");
            client.sendEvent("disconnect", "客户端已断开连接...");
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.remove(userId);
                client.disconnect();
            }
        });

        // 自定义事件`client_info_event` -> 监听客户端消息
        socketIOServer.addEventListener(PUSH_DATA_EVENT, String.class, (client, data, ackSender) -> {
            // 客户端推送`client_info_event`事件时，onData接受数据，这里是string类型的json数据，还可以为Byte[],object其他类型
            String clientIp = getIpByClient(client);
            System.out.println(clientIp + " ************ 客户端：" + data);
        });

        // 启动服务
        socketIOServer.start();

        // broadcast: 默认是向所有的socket连接进行广播，但是不包括发送者自身，如果自己也打算接收消息的话，需要给自己单独发送。
        new Thread(() -> {
            int i = 0;

        }).start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

//    @Override
//    public successResult pushMessageToUser(String userId, String msgContent) {
//        successResult r = new successResult();
//        try {
//            SocketIOClient client = clientMap.get(userId);
//            if (client != null) {
//                String messageId = String.valueOf(UUID.randomUUID());
//                String result = "wait";
//                String messageData = result;
//                String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                Map<String, Object> map = new HashMap<>();
//                map.put("messageId", messageId);
//                map.put("messageData", messageData);
//                map.put("createTime", createTime);
//                map.put("orderNo", msgContent);
////                rabbitUtil.send(allMessage.WeChatScanInfo(), map);
////                redisTemplate.opsForValue().set(msgContent,"1",3, TimeUnit.MINUTES);
////                while (redisTemplate.opsForValue().get(msgContent) != null) {
////                    try {
////                        // 每3秒发送一次广播消息
////                        Thread.sleep(3000);
////                        if (redisTemplate.opsForValue().get(msgContent) != null && redisTemplate.opsForValue().get(msgContent).equals("2")) {
////                            rabbitUtil.send("WxChatScanInfoMQ", map);
////                            socketIOServer.getBroadcastOperations().sendEvent(PUSH_DATA_EVENT, msgContent);
////                            return;
////                        }
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                }
////                if (redisTemplate.opsForValue().get(msgContent) == null || redisTemplate.opsForValue().get(msgContent).equals("1")) {
////                    //付款超时处理队列
////                    redisTemplate.opsForValue().set(msgContent,"discon", 5, TimeUnit.MINUTES);
////                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "付款超时" + new Date());
////                    return;
////                }
//                redisTemplate.opsForValue().set(msgContent, "1", 3, TimeUnit.MINUTES);
//                while (redisTemplate.opsForValue().get(msgContent) != null) {
//                    try {
//                        Thread.sleep(3000);
//                        String WeChatScanResult = GetWeChatScanResult(msgContent);
//                        if (WeChatScanResult.equals("success")) {
//                            redisTemplate.opsForValue().set(msgContent,"2");
//                        }
//                        if (redisTemplate.opsForValue().get(msgContent) != null && redisTemplate.opsForValue().get(msgContent).equals("2")) {
//                            SaveRecord(msgContent);
//                            socketIOServer.getBroadcastOperations().sendEvent(PUSH_DATA_EVENT, userId);
//                            r.setData(userId);
//                            r.setMessage("success");
//                            return r;
//                        }
//                    }catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (redisTemplate.opsForValue().get(msgContent) == null || redisTemplate.opsForValue().get(msgContent).equals("1")) {
//                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "付款超时" + new Date());
//                    r.setData(userId);
//                    r.setMessage("fail");
//                    return r;
//                }
//            }
//        }catch (Exception ax) {
//            ax.printStackTrace();
//        }
//        return r;
//    }

    @Override
    public successResult pushMessageToUser(String userId, String msgContent) {
        successResult result = new successResult();
        result.setSuccess(false);
        try {
            String messageId = String.valueOf(UUID.randomUUID());
            String messageData = "wait";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>();
            map.put("messageId", messageId);
            map.put("messageData", messageData);
            map.put("createTime", createTime);
            map.put("orderNo", msgContent);
//                rabbitUtil.send(allMessage.WeChatScanInfo(), map);
//                redisTemplate.opsForValue().set(msgContent,"1",3, TimeUnit.MINUTES);
//                while (redisTemplate.opsForValue().get(msgContent) != null) {
//                    try {
//                        // 每3秒发送一次广播消息
//                        Thread.sleep(3000);
//                        if (redisTemplate.opsForValue().get(msgContent) != null && redisTemplate.opsForValue().get(msgContent).equals("2")) {
//                            rabbitUtil.send("WxChatScanInfoMQ", map);
//                            socketIOServer.getBroadcastOperations().sendEvent(PUSH_DATA_EVENT, msgContent);
//                            return;
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (redisTemplate.opsForValue().get(msgContent) == null || redisTemplate.opsForValue().get(msgContent).equals("1")) {
//                    //付款超时处理队列
//                    redisTemplate.opsForValue().set(msgContent,"discon", 5, TimeUnit.MINUTES);
//                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "付款超时" + new Date());
//                    return;
//                }
            logger.info("时间：" + new Date() + "订单号：" + msgContent + "是否失效：" + redisTemplate.opsForValue().getOperations().getExpire(msgContent));
            if (redisTemplate.opsForValue().getOperations().getExpire(msgContent) > 0) {
                try {
                    String WeChatScanResult = GetWeChatScanResult(msgContent);
                    if (WeChatScanResult.equals("success")) {
                        redisTemplate.opsForValue().set(msgContent,"2");
                    }
                    if (redisTemplate.opsForValue().get(msgContent) != null && redisTemplate.opsForValue().get(msgContent).equals("2")) {
                        SaveRecord(msgContent);
                        logger.info("扫码成功");
                        result.setData(userId);
                        result.setMessage("success");
                        result.setSuccess(true);
                        return result;
                    }
                }catch (Exception e) {
                    result.raiseException(e);
                }
            }else {
                logger.info("用户：" + userId + "的订单：" + msgContent + "二维码过期");
                result.setData(userId);
                result.setMessage("fail");
                result.setSuccess(true);
                return result;
            }
        }catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @Override
    public void Sned(String msgContent) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "success";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        map.put("orderNo", msgContent);
        redisTemplate.opsForValue().set(msgContent, "2");
//        rabbitUtil.send(allMessage.WeChatScanInfo(), map);
//        socketIOServer.getBroadcastOperations().sendEvent(PUSH_DATA_EVENT, msgContent);
//        redisTemplate.opsForValue().set(msgContent, "2");
    }

    public String GetWeChatScanResult(String msgContent){
        String result = "";
        try {
            getOrderCode getOrderCode = new getOrderCode();
            getOrderCode.setAppId(AppId);
            getOrderCode.setMchId(MchId);
            getOrderCode.setOrderId(msgContent);
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

    /**
     * 获取客户端url中的userId参数（这里根据个人需求和客户端对应修改即可）
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getParamsByClient(SocketIOClient client) {
        // 获取客户端url参数（这里的userId是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("loginUserId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

    /**
     * 获取连接的客户端ip地址
     *
     * @param client: 客户端
     * @return: java.lang.String
     */
    private String getIpByClient(SocketIOClient client) {
        String sa = client.getRemoteAddress().toString();
        String clientIp = sa.substring(1, sa.indexOf(":"));
        return clientIp;
    }

    @Transactional(rollbackFor = Exception.class)
    public void SaveRecord(String msgContent) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            getOrderCode getOrderCode = new getOrderCode();
            getOrderCode.setAppId(AppId);
            getOrderCode.setMchId(MchId);
            getOrderCode.setOrderId(msgContent);
            getOrderCode.setNonce_str(UUID.randomUUID().toString().replaceAll("-",""));
            getOrderCode.setApiKey(ApiKey);
            Map<String, Object> map = phoneOrderService.getWeChatScanResult(getOrderCode);

            if (map.get("trade_state_desc").equals("支付成功")) {
                String Device_Info = (String) map.get("device_info "); //微信支付分配的终端设备号
                String Result_Code = (String) map.get("result_code"); //支付结果
                String Bank_Type = (String) map.get("bank_type"); //银行类型
                String time_end = (String) map.get("time_end");
                Date sd = simpleDateFormat.parse(time_end);
                Date Time_End = sd; //支付完成时间
                String openid = (String) map.get("openid");
                String transaction_id = (String) map.get("transaction_id"); //微信支付订单号

                //更新订单的支付状态和费用的缴费状态
                tbOrderList orderList = new tbOrderList();
                orderList.setDjState(2);
                orderList.setPayState(2);
                orderList.setDevice_Info(Device_Info);
                orderList.setResult_Code(Result_Code);
                orderList.setOpenId(openid);
                orderList.setBank_Type(Bank_Type);
                orderList.setTime_End(Time_End);
                orderList.setTransaction_Id(transaction_id);
                weChatOrderService.UpdateDjStateAndPayState(msgContent, orderList);
            }else if (map.get("trade_state_desc").equals("支付失败")){
                tbOrderList orderList = new tbOrderList();
                String Err_Code_Des = (String)map.get("err_code_des"); //错误描述
                orderList.setDjState(1);
                orderList.setPayState(3);
                orderList.setErr_Code_Des(Err_Code_Des);
                weChatOrderService.UpdateDjStateAndPayState(msgContent,orderList);
            }
            redisTemplate.delete(msgContent);
        }catch (Exception ax) {
            ax.printStackTrace();
        }
    }
}
