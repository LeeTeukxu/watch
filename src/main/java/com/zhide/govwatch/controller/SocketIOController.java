package com.zhide.govwatch.controller;

import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.define.ISocketIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/SocketIO")
public class SocketIOController {
    @Autowired
    ISocketIOService socketIOService;

    Logger logger = LoggerFactory.getLogger(SocketIOController.class);

    @RequestMapping("/pushMessageToUser")
    @ResponseBody
    public successResult PushMessageToUser(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            String OrderNo = request.getParameter("OrderNo");
            String UserID = request.getParameter("UserID");
            result = socketIOService.pushMessageToUser(UserID, OrderNo);
        }catch (Exception ax) {
            result.raiseException(ax);
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping("/send")
    @ResponseBody
    public successResult Send(HttpServletRequest request) {
        successResult result = new successResult();
        try {
            String OrderNo = request.getParameter("OrderNo");
            socketIOService.Sned(OrderNo);
        }catch (Exception ax) {
            result.raiseException(ax);
            result.setSuccess(false);
        }
        return result;
    }
}
