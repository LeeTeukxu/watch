package com.zhide.govwatch.controller.WeChat;

import com.zhide.govwatch.GovwatchApplication;
import com.zhide.govwatch.common.PayCommonUtil;
import com.zhide.govwatch.common.RabbitUtil;
import com.zhide.govwatch.common.XMLUtil4jdom;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IPhoneOrderService;
import com.zhide.govwatch.define.IWeChatOrderService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.tbWxScanResultRepository;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: WeChatController
 * @Author: JYM
 * @*TODO: 生成微信支付二维码控制器
 * @CreateTime: 2022年3月4日 14:22
 **/

@Controller
@RequestMapping("/WeChat")
public class WeChatController {
    private final Logger logger = LoggerFactory.getLogger(WeChatController.class);
    @Autowired
    IPhoneOrderService phoneOrderService;
    @Autowired
    IWeChatOrderService weChatOrderService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    tbWxScanResultRepository wxScanResultRepository;
    @Autowired
    RabbitUtil rabbitUtil;

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

    @RequestMapping("/getPayWxqrcode")
    @ResponseBody
    public successResult GetPayWxQrcode(HttpServletRequest request) throws Exception{
        successResult result = new successResult();
        try {
            WxCusInfoBean wxCusInfoBean = new WxCusInfoBean();
            wxCusInfoBean.setAppId(AppId);
            wxCusInfoBean.setApiKey(ApiKey);
            wxCusInfoBean.setMchId(MchId);

            String GovFeeCount = request.getParameter("GovFeeCount");
            String AllGovFeeTotal = request.getParameter("AllGovFeeTotal");
            String OrderNo = request.getParameter("OrderNo");
            //由于订单号不能重复，所以每次生成二维码时重新生成订单号并更新数据库
            OrderNo = weChatOrderService.ResetOrderNo(OrderNo);

            wxCusInfoBean.setOrderId(OrderNo);
//        wxCusInfoBean.setPayAmount(new BigDecimal(AllGovFeeTotal.replaceAll(",","")));
            wxCusInfoBean.setPayAmount(new BigDecimal("0.01"));
            wxCusInfoBean.setBody(GovFeeCount + "件专利缴费");

            BigDecimal fen = wxCusInfoBean.getPayAmount().multiply(new BigDecimal(100));
            fen = fen.setScale(0, BigDecimal.ROUND_HALF_UP);
            String order_price = fen.toString();

            // 微信支交易订单号，不能重复
            String out_trade_no = wxCusInfoBean.getOrderId();

            // 组装参数
            Map<String, Object> param = new HashMap<>();
            param.put("order_price", order_price);
            param.put("body", wxCusInfoBean.getBody());
            param.put("out_trade_no", out_trade_no);
            param.put("attach", wxCusInfoBean.getOrderId());

            // 生成微信支付二维码链接
            Map<String, String> map = phoneOrderService.doUnifiedOrder(wxCusInfoBean, param, request);
            URLCode urlCode = new URLCode();
            if ("FAIL".equals(map.get("return_code")) == true) {
                urlCode.setErorr("error");
                result.setData(urlCode);
                result.setSuccess(false);
            } else {
                String urlcode = map.get("code_url");
                String prepay_id = map.get("prepay_id");
                String nonce_str = map.get("nonce_str");
                urlCode.setPrepayId(prepay_id);
                urlCode.setUrlCode(urlcode);
                urlCode.setNonce_str(nonce_str);
                urlCode.setSign(map.get("sign"));
                result.setData(urlCode);
            }
            result.setMessage(OrderNo);
            redisTemplate.opsForValue().set(OrderNo, "1", 10, TimeUnit.MINUTES);
        }catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    //POST扫码后微信自动回调
    @RequestMapping( "/wxnotify")
    public String wxnotify(HttpServletRequest request) throws Exception{
        String successXML = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";


        String failXML = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        logger.info("进入扫码后微信自动回调函数");
        try {
            //读取参数
            String transaction_id = "";
            InputStream inputStream;
            StringBuffer sb = new StringBuffer();
            inputStream = request.getInputStream();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();

            Map<String, String> m;
            m = XMLUtil4jdom.doXMLParse(sb.toString());

            //过滤空 设置 TreeMap
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            Iterator it = m.keySet().iterator();
            while (it.hasNext()) {
                String parameter = (String) it.next();
                String parameterValue = m.get(parameter);
                logger.info("parameter：" + parameter);
                logger.info("parameterValue" + parameterValue);

                String v = "";
                if (null != parameterValue) {
                    v = parameterValue.trim();
                }
                packageParams.put(parameter, v);
                String key = "";
                if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
                    //------------------------------
                    //处理业务开始
                    //------------------------------

                    logger.info("微信自动回调方法：" + packageParams.get("result_code"));
                    if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                        //支付成功
                        //执行业务逻辑
                        String mch_id = (String) packageParams.get("mch_id"); //返回的商户号
                        String openid = (String) packageParams.get("openid"); //返回的用户标识
                        String is_subscribe = (String) packageParams.get("is_subscribe"); //返回是否关注公众账号
                        String out_trade_no = (String) packageParams.get("out_trade_no "); //返回商户订单号
                        logger.info("返回商户订单号：" + out_trade_no);

                        BigDecimal total_fee = new BigDecimal((String) packageParams.get("total_fee")); //返回的订单金额
                        transaction_id = (String) packageParams.get("transaction_id"); //返回微信支付订单号

                        logger.info("微信订单号=" + transaction_id);

                        //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                        String messageId = String.valueOf(UUID.randomUUID());
//                        String messageData = "success";
//                        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("messageId", messageId);
//                        map.put("messageData", messageData);
//                        map.put("createTime", createTime);
//                        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
//                            @Override
//                            public Message postProcessMessage(Message message) throws AmqpException {
//                                message.getMessageProperties().setExpiration("300000");
//                                message.getMessageProperties().setContentEncoding("UTF-8");
//                                return message;
//                            }
//                        };
//                        rabbitTemplate.convertAndSend("weChatScanInfoChanged", map, messagePostProcessor);
                        //支付成功后跳转到WeChatScanInfoAckReceiver的onMessage方法手动处理消费队列信息
//                        rabbitUtil.publish("weChatScanInfoChanged", map);
                        redisTemplate.opsForValue().set(out_trade_no,"2");
                        return successXML;
                    } else {
                        String out_trade_no = (String) packageParams.get("out_trade_no "); //返回商户订单号
                        String Err_Code_Des = (String) packageParams.get("err_code_des"); //错误描述
                        //保存扫描错误信息XML到数据库
                        tbWxScanResult wxScanResult = new tbWxScanResult();
                        Optional<tbWxScanResult> findOne = wxScanResultRepository.findAllByOrderNo(out_trade_no);
                        if (findOne.isPresent()) {
                            wxScanResult = findOne.get();
                        }
                        wxScanResult.setOrderNo(out_trade_no);
                        wxScanResult.setScanResultXML(sb.toString());
                        wxScanResultRepository.save(wxScanResult);

                        return failXML;
                    }
//                    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//                    out.write(resXml.getBytes());
//                    out.flush();
//                    out.close();
                }
            }
        } catch (Exception ax) {
            logger.info("微信回调错误信息：" + ax.getMessage());
            return failXML;
        }
        return successXML;
    }

    @RequestMapping("getWeChatScanResult")
    @ResponseBody
    public successResult GetWeChatScanResult(HttpServletRequest request){
        successResult result = new successResult();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            String OrderNo = request.getParameter("OrderNo");
            getOrderCode getOrderCode = new getOrderCode();
            getOrderCode.setAppId(AppId);
            getOrderCode.setMchId(MchId);
            getOrderCode.setOrderId(OrderNo);
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
                weChatOrderService.UpdateDjStateAndPayState(OrderNo, orderList);

//                redisTemplate.opsForValue().set(OrderNo, "2", 30, TimeUnit.MINUTES);
            }else if (map.get("trade_state_desc").equals("支付失败")){
                tbOrderList orderList = new tbOrderList();
                String Err_Code_Des = (String)map.get("err_code_des"); //错误描述
                orderList.setDjState(1);
                orderList.setPayState(3);
                orderList.setErr_Code_Des(Err_Code_Des);
                weChatOrderService.UpdateDjStateAndPayState(OrderNo,orderList);

                result.setSuccess(false);
//                redisTemplate.opsForValue().set(OrderNo,"3", 30, TimeUnit.MINUTES);
            }
            redisTemplate.delete(OrderNo);
        }catch (Exception ax){
            ax.printStackTrace();
        }
        return result;
    }
}
