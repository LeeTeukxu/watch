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
     * ???????????????????????????
     */
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    /**
     * ???????????????`push_data_event`,?????????????????????????????????
     */
    private static final String PUSH_DATA_EVENT = "push_data_event";
    private static final String PUSH_DISCONNECT = "disconnect";

    /**
     * Spring IoC??????????????????????????????SocketIOServiceImpl Bean????????????
     */
    @PostConstruct
    private void autoStartup() {
//        logger.info("Spring IoC??????????????????????????????SocketIOServiceImpl Bean????????????");
//        start();
    }

    /**
     * Spring IoC???????????????SocketIOServiceImpl Bean????????????,??????????????????????????????????????????
     */
    @PreDestroy
    private void autoStop() {
        stop();
    }


    @Override
    public void start() {
        // ?????????????????????
        socketIOServer.addConnectListener(client -> {
            System.out.println("************ ???????????? " + getIpByClient(client) + " ????????? ************");
            // ???????????????`connected` -> ??????????????????  ???????????????????????????????????????Socket.EVENT_CONNECT???
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.put(userId, client);
            }
            client.sendEvent("connected", "????????????????????????...");
        });

        // ???????????????????????????
        socketIOServer.addDisconnectListener(client -> {
            String clientIp = getIpByClient(client);
            System.out.println(clientIp + " *********************** " + "????????????????????????");
            client.sendEvent("disconnect", "????????????????????????...");
            String userId = getParamsByClient(client);
            if (userId != null) {
                clientMap.remove(userId);
                client.disconnect();
            }
        });

        // ???????????????`client_info_event` -> ?????????????????????
        socketIOServer.addEventListener(PUSH_DATA_EVENT, String.class, (client, data, ackSender) -> {
            // ???????????????`client_info_event`????????????onData????????????????????????string?????????json?????????????????????Byte[],object????????????
            String clientIp = getIpByClient(client);
            System.out.println(clientIp + " ************ ????????????" + data);
        });

        // ????????????
        socketIOServer.start();

        // broadcast: ?????????????????????socket??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
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
////                        // ???3???????????????????????????
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
////                    //????????????????????????
////                    redisTemplate.opsForValue().set(msgContent,"discon", 5, TimeUnit.MINUTES);
////                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "????????????" + new Date());
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
//                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "????????????" + new Date());
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
//                        // ???3???????????????????????????
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
//                    //????????????????????????
//                    redisTemplate.opsForValue().set(msgContent,"discon", 5, TimeUnit.MINUTES);
//                    socketIOServer.getBroadcastOperations().sendEvent(PUSH_DISCONNECT, "????????????" + new Date());
//                    return;
//                }
            logger.info("?????????" + new Date() + "????????????" + msgContent + "???????????????" + redisTemplate.opsForValue().getOperations().getExpire(msgContent));
            if (redisTemplate.opsForValue().getOperations().getExpire(msgContent) > 0) {
                try {
                    String WeChatScanResult = GetWeChatScanResult(msgContent);
                    if (WeChatScanResult.equals("success")) {
                        redisTemplate.opsForValue().set(msgContent,"2");
                    }
                    if (redisTemplate.opsForValue().get(msgContent) != null && redisTemplate.opsForValue().get(msgContent).equals("2")) {
                        SaveRecord(msgContent);
                        logger.info("????????????");
                        result.setData(userId);
                        result.setMessage("success");
                        result.setSuccess(true);
                        return result;
                    }
                }catch (Exception e) {
                    result.raiseException(e);
                }
            }else {
                logger.info("?????????" + userId + "????????????" + msgContent + "???????????????");
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
                if (map.get("trade_state_desc").equals("????????????")) {
                    result = "success";
                } else if (map.get("trade_state_desc").equals("????????????")) {
                    result = "fail";
                } else result = "wait";
            }else result = "wait";
        }catch (Exception ax){
            ax.printStackTrace();
        }
        return result;
    }

    /**
     * ???????????????url??????userId??????????????????????????????????????????????????????????????????
     *
     * @param client: ?????????
     * @return: java.lang.String
     */
    private String getParamsByClient(SocketIOClient client) {
        // ???????????????url??????????????????userId??????????????????
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> userIdList = params.get("loginUserId");
        if (!CollectionUtils.isEmpty(userIdList)) {
            return userIdList.get(0);
        }
        return null;
    }

    /**
     * ????????????????????????ip??????
     *
     * @param client: ?????????
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

            if (map.get("trade_state_desc").equals("????????????")) {
                String Device_Info = (String) map.get("device_info "); //????????????????????????????????????
                String Result_Code = (String) map.get("result_code"); //????????????
                String Bank_Type = (String) map.get("bank_type"); //????????????
                String time_end = (String) map.get("time_end");
                Date sd = simpleDateFormat.parse(time_end);
                Date Time_End = sd; //??????????????????
                String openid = (String) map.get("openid");
                String transaction_id = (String) map.get("transaction_id"); //?????????????????????

                //???????????????????????????????????????????????????
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
            }else if (map.get("trade_state_desc").equals("????????????")){
                tbOrderList orderList = new tbOrderList();
                String Err_Code_Des = (String)map.get("err_code_des"); //????????????
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
