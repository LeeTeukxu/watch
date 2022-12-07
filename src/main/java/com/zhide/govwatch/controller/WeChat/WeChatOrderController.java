package com.zhide.govwatch.controller.WeChat;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.WeChatScanInfoAckReceiver;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.IWeChatOrderService;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbOrderDetail;
import com.zhide.govwatch.model.tbOrderList;
import com.zhide.govwatch.model.view_OrderDetails;
import com.zhide.govwatch.repository.tbOrderDetailRepository;
import com.zhide.govwatch.repository.view_OrderDetailsRepository;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/WeChatOrder")
public class WeChatOrderController {
    private final Logger logger = LoggerFactory.getLogger(WeChatOrderController.class);

    @Autowired
    IWeChatOrderService weChatOrderService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    tbOrderDetailRepository orderDetailRepository;
    @Autowired
    view_OrderDetailsRepository view_orderDetailsRepository;

    @RequestMapping("orderlist")
    public String OrderList(Map<String, Object> model, HttpServletRequest request) throws Exception{
        LoginUserInfo Info = CompanyContext.get();
        List<Map<String, Object>> list = weChatOrderService.getBasics(request);
        model.put("ALL", list.stream().count());
        model.put("DZF", list.stream().filter(f -> Integer.parseInt(f.get("PayState").toString()) == 1).count());
        model.put("DJZ", list.stream().filter(f -> Integer.parseInt(f.get("DjState").toString()) == 2).count());
        model.put("DJWC", list.stream().filter(f -> Integer.parseInt(f.get("DjState").toString()) == 3).count());
        model.put("RoleName", Info.getRoleName());
        return "Orderlist";
    }

    @RequestMapping("orderdetails")
    public String OrderDetails(String OrderNo, HttpServletRequest request, Map<String, Object> model){
//        pageObject result = new pageObject();
//        try {
//            result = weChatOrderService.getOrderDetailsData(OrderNo, request);
//        }catch (Exception ax){
//            result.raiseException(ax);
//        }
        model.put("OrderNo", OrderNo);
        List<view_OrderDetails> listOrderDetails = view_orderDetailsRepository.getAllByOrderNo(OrderNo);
        model.put("GovFeeCount", listOrderDetails.size());
        float GovFeeTotal = 0;
        float AfterPriceTotal = 0;
        for (int i=0;i<listOrderDetails.size();i++){
            view_OrderDetails orderDetail = listOrderDetails.get(i);
            GovFeeTotal += orderDetail.getAmount();
            float total = weChatOrderService.GetAfterPriceTotal(orderDetail, orderDetail.getAppNo(), orderDetail.getFEENAME());
            AfterPriceTotal += total;
        }
        model.put("GovFeeTotal",GovFeeTotal);
        model.put("AfterPriceTotal", AfterPriceTotal);
        model.put("ServiceChargeTotal",listOrderDetails.size() * 100);
        model.put("AllGovFeeTotal", GovFeeTotal + (listOrderDetails.size() * 100));
        return "Orderdetails";
    }

    @RequestMapping("getOrderDetailData")
    @ResponseBody
    public pageObject GetOrderDetailData(HttpServletRequest request){
        pageObject result = new pageObject();
        try {
            String OrderNo = request.getParameter("OrderNo");
            result = weChatOrderService.getOrderDetailsData(OrderNo, request);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        return result;
    }


    @RequestMapping("orderpayment")
    public String OrderPayment(String SHENQINGHS, String Amounts, String FEENAMES, String JIAOFEIRS, String OrderNo, Map<String, Object> model, HttpServletRequest request) throws Exception{
        LoginUserInfo Info = CompanyContext.get();
        if (OrderNo == null) {
            tbOrderList orderList = weChatOrderService.Save(SHENQINGHS, Amounts, FEENAMES, JIAOFEIRS);
            OrderNo = orderList.getOrderNo();
        }
        pageObject result = new pageObject();
        try {
            result = weChatOrderService.getPaymentOrderDetailsData(OrderNo, request);
        }catch (Exception ax){
            result.raiseException(ax);
        }
        model.put("OrderDetails", result.getData());
        List<view_OrderDetails> listOrderDetails = (List<view_OrderDetails>)result.getData();
        model.put("GovFeeCount", listOrderDetails.size());
        float GovFeeTotal = 0;
        float AfterPriceTotal = 0;
        for (int i=0;i<listOrderDetails.size();i++){
            GovFeeTotal += listOrderDetails.get(i).getAmount();
            AfterPriceTotal += listOrderDetails.get(i).getAFTERPRICE();
        }
        model.put("GovFeeTotal",GovFeeTotal);
        model.put("AfterPriceTotal", AfterPriceTotal);
        model.put("ServiceChargeTotal",listOrderDetails.size() * 100);
        model.put("AllGovFeeTotal", GovFeeTotal + (listOrderDetails.size() * 100));
        model.put("OrderNo", OrderNo);
        model.put("UserID", Info.getUserId());
        return "Orderpayment";
    }

    @RequestMapping("/orderlistparameter")
    public String OrderListParameter(String SHENQINGHS, String Amounts, String FEENAMES, String JIAOFEIRS) throws Exception{
        weChatOrderService.Save(SHENQINGHS, Amounts,FEENAMES,JIAOFEIRS);
        return "Orderlist";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public pageObject GeData(HttpServletRequest request) {
        pageObject result = new pageObject();
        try {
            result = weChatOrderService.getData(request);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getOrderPayState")
    @ResponseBody
    public successResult GetOrderPayState(HttpServletRequest request){
        successResult result = new successResult();
        try {
            String OrderNo = request.getParameter("OrderNo");
            tbOrderList orderList = weChatOrderService.GetOrderPayState(OrderNo);
            result.setData(orderList);
        }catch (Exception ax){
            result.raiseException(ax);
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/completeDJ")
    @ResponseBody
    public successResult CompleteDJ(@RequestBody List<Integer> IDS,HttpServletRequest request){
        successResult result = new successResult();
        try {
            weChatOrderService.ComplateDJ(IDS);
        }catch (Exception ax){
            result.setSuccess(false);
            result.setMessage(ax.getMessage());
        }
        return result;
    }

    @RequestMapping("/remove")
    @ResponseBody
    public successResult Remove(@RequestBody List<Integer> IDS,HttpServletRequest request){
        successResult result = new successResult();
        try {
            weChatOrderService.Remove(IDS);
        }catch (Exception ax){
            result.setSuccess(false);
            result.setMessage(ax.getMessage());
        }
        return result;
    }

    @RequestMapping("/statistic")
    public String Statistic(Map<String, Object> map) {
        return "/Statistic";
    }

    @RequestMapping("/getStatistic")
    @ResponseBody
    public successResult GetStatistic(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            List<Map<String, Object>> Statistics = weChatOrderService.getStatistic(request);
            result.setData(Statistics);
        }catch (Exception ax) {
            result.setSuccess(false);
            result.setMessage(ax.getMessage());
        }
        return result;
    }

    @RequestMapping("/reloadGovFeeWaitNum")
    @ResponseBody
    public successResult ReloadGovFeeWaitNum(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            List<Map<String, Object>> list = weChatOrderService.getBasics(request);
            Map<String, Object> map = new HashMap<>();
            map.put("ALL", list.stream().count());
            map.put("DZF", list.stream().filter(f -> Integer.parseInt(f.get("PayState").toString()) == 1).count());
            map.put("DJZ", list.stream().filter(f -> Integer.parseInt(f.get("DjState").toString()) == 2).count());
            map.put("DJWC", list.stream().filter(f -> Integer.parseInt(f.get("DjState").toString()) == 3).count());
            result.setData(map);
        }catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
