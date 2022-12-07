package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.HttpUtil;
import com.zhide.govwatch.common.PayCommonUtil;
import com.zhide.govwatch.common.PayConfigUtil;
import com.zhide.govwatch.common.XMLUtil4jdom;
import com.zhide.govwatch.define.IPhoneOrderService;
import com.zhide.govwatch.model.WxCusInfoBean;
import com.zhide.govwatch.model.getOrderCode;
import com.zhide.govwatch.model.tbWxScanResult;
import com.zhide.govwatch.repository.tbWxScanResultRepository;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class PhoneOrderServiceImpl implements IPhoneOrderService {

    @Autowired
    tbWxScanResultRepository wxScanResultRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> doUnifiedOrder(WxCusInfoBean wxCusInfoBean, Map<String, Object> param, HttpServletRequest request) throws Exception {

        String appid = wxCusInfoBean.getAppId();
        String mch_id = wxCusInfoBean.getMchId();
        String key = wxCusInfoBean.getApiKey();
        String trade_type = "NATIVE";
        String spbill_create_ip = PayCommonUtil.getIpAddress(request); // 获取发起电脑 ip;
        String notify_url = PayConfigUtil.NOTIFY_URL;
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        String order_price = (String)param.get("order_price");
        String body = (String)param.get("body");
        String out_trade_no = (String)param.get("out_trade_no");

        String attach = (String)param.get("attach");

        SortedMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", order_price);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("attach", attach);

        //签名
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);
        // 微信支付接口传输数据使用xml方式进行的，此处将参数装换为xml
        // map --> xml
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXML = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);
        Optional<tbWxScanResult> findOne = wxScanResultRepository.findAllByOrderNo(out_trade_no);
        //保存请求微信二维码XML到数据库
        tbWxScanResult wxScanResult = new tbWxScanResult();
        if (findOne.isPresent()){
            wxScanResult = findOne.get();
        }
        wxScanResult.setOrderNo(out_trade_no);
        wxScanResult.setRequestCodeXML(resXML);
        wxScanResultRepository.save(wxScanResult);

        try {
            return XMLUtil4jdom.doXMLParse(resXML);
        }catch (JDOMException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getWeChatScanResult(getOrderCode getOrderCode) {
        String appid = getOrderCode.getAppId();
        String mch_id = getOrderCode.getMchId();
        String orderId = getOrderCode.getOrderId();
        String nonce_str = getOrderCode.getNonce_str();
        String sign = "";
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no", orderId);
        sign = PayCommonUtil.createSign("UTF-8", packageParams, getOrderCode.getApiKey());
        packageParams.put("sign", sign);
        String requestXML  = PayCommonUtil.getRequestXml(packageParams);
//        System.out.println("---------- Request XML: " + requestXML);
        String resXML = HttpUtil.postData(PayConfigUtil.WXORDER_URL, requestXML);
//        System.out.println("---------- Response XML: " + resXML);

        // xml --> map
        try {
            return XMLUtil4jdom.doXMLParse(resXML);
        }catch (JDOMException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
