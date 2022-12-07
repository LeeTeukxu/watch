<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>订单支付</title>
    <link rel="stylesheet" href="/js/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/boot.js"></script>
    <script type="text/javascript" src="/js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/left-side-menu.css">
    <link rel="stylesheet" type="text/css" href="/font/iconfont.css">
    <script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="/js/left-side-menu.js"></script>
    <link rel="stylesheet" href="/css/tongyong.css" />

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--下拉菜单strat-->
    <script src="/js/paging/js/adapter.js"></script> <!--rem适配js-->

    <link rel="stylesheet" href="/js/paging/css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="/js/paging/css/menu.css"> <!--主样式-->
    <script src="/js/paging/js/menu.js"></script> <!--控制js-->
    <!--下拉菜单end-->

    <!--二维码图片生成strat-->
    <script src="https://cdn.bootcss.com/jquery/1.5.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
    <!--二维码图片生成end-->

    <script src="https://cdn.bootcss.com/socket.io/2.2.0/socket.io.js"></script>

    <#--jquery倒计时strat-->
    <!--[if lt IE 9]>
    <script>
        document.createElement('figure');
        document.createElement('figcaption');
    </script>
    <![endif]-->
    <script type="text/javascript" src="/js/timeto/js/jquery.time-to.js"></script>
    <link href="/js/timeto/css/timeTo.css" type="text/css" rel="stylesheet"/>
    <style>
        pre {
            margin-bottom: 10px;
            padding-left: 10px;
            border-left: 3px #DDD solid;
        }
    </style>
    <#--jquery倒计时strat-->

    <style>
        .titlebj {
            width: 100%;
            text-align: center;
            height: 60px;
            border-bottom: 1px solid #6666664d;
        }
        .titlebj h2 {
            padding-top: 18px;
            font-weight: bold;
            font-size: 20px;
            font-family: auto;
        }
        .table tr{text-align: center;}
        .table th{text-align: center;}

        .substitute_news {
            padding: 15px 20px;
            border-top: 1px solid #e6e6e6;
            border-bottom: 1px solid #eaeaea;
        }
        .order_pay_left {
            float: left;
        }
        .money_all span, .order_pay_left span {
            font-size: 14px;
            font-weight: bold;
            vertical-align: middle;
        }
        .money_all span, .order_pay_left span {
            font-size: 14px;
            font-weight: bold;
            vertical-align: middle;
        }
        .sub_feiyong {
            color: red;
        }
        .money_all span, .order_pay_left span {
            font-size: 14px;
            font-weight: bold;
            vertical-align: middle;
        }
        .sub_paddleft {
            padding-left: 20px;
        }
        .money_all {
            float: right;
            display: inline-block;
            font-weight: bold;
        }
        .sub_btn {
            margin: 15px 0;
            padding: 0 20px;
        }
        .substitute_back {
            float: right;
            display: inline-block;
            background: #3284EB;
            border: 1px #3284EB solid;
            /* padding: 10px 18px; */
            /* border-radius: 5px; */
            color: #FFF;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            letter-spacing: 2px;
            /* margin-right: 40px; */
            height: 35px;
            line-height: 35px;
            width: 150px;
            text-align: center;
            margin-top: 10px;
            background: #00B395;
            border: 1px #00B395 solid;
            color: #FFF;
        }
        .mini-button{width: 74px;}
    </style>
</head>
<body style="overflow-y: auto;overflow-x: hidden; background: #fff;">

<div class="row" >
    <div class="col-md-12">
        <div class="titlebj">
            <h2>专利缴费订单</h2>
        </div>
        <table class="table table-bordered" style="margin-top: 10px;">
            <thead style="border: none;background: #f2f2f2;">
            <tr>
                <th>专利号</th>
                <th>专利名称</th>
                <th>费用名称</th>
                <th>费减前金额</th>
                <th>费减后金额</th>
                <th>节省金额</th>
                <th>截止日期</th>
                <th>状态</th>
                <th>代缴状态</th>
            </tr>
            </thead>
            <tbody>
            <#list OrderDetails as OrderDetail>
                <tr>
                    <td>${OrderDetail.appNo!}</td>
                    <td>${OrderDetail.FAMINGMC!}</td>
                    <td>${OrderDetail.FEENAME!}</td>
                    <td>${OrderDetail.FEEPRICE}</td>
                    <td>${OrderDetail.amount!}</td>
                    <td>
                        <#if OrderDetail.AFTERPRICE != 0>
                            ${OrderDetail.AFTERPRICE}
                        </#if>
                    </td>
                    <td>${OrderDetail.limitDate?string["yyyy.MM.dd"]}</td>
                    <td>
                        <#if OrderDetail.payState == 1>
                            待支付
                        </#if>
                        <#if OrderDetail.payState == 2>
                            支付成功
                        </#if>
                        <#if OrderDetail.payState == 3>
                            支付失败
                        </#if>
                    </td>
                    <td>
                        <#if OrderDetail.payState == 1>
                            <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">未代缴</div>
                        <#elseIf OrderDetail.djState = 2>
                            <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴中</div>
                        <#elseIf OrderDetail.djState = 3>
                            <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴完成</div>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <div class="substitute_news clearfix">
            <div class="order_pay_left">
                <span>代缴专利：<span class="sub_feiyong">${GovFeeCount}件</span></span>
                <span class="sub_paddleft"><span>代缴费用：</span><span class="sub_feiyong">${GovFeeTotal}元</span><span>（官费）</span>
                </span> <span class="sub_paddleft"><span>服务费：</span><span class="sub_feiyong">${ServiceChargeTotal}元</span></span>
                <span class="sub_paddleft"><span>费用代缴单价：</span><span class="sub_feiyong">100元 / 件</span></span>
                </span> <span class="sub_paddleft"><span>共计节省金额：</span><span class="sub_feiyong">${AfterPriceTotal}元</span></span>
            </div> <span class="money_all"><span>费用总计：</span><span class="sub_feiyong">${AllGovFeeTotal}元</span>&nbsp;&nbsp;<button type="button" id="GoPay" class="btn btn-success" data-toggle="modal" data-target="#myModal" onclick="showPay()">去支付</button></span>

        </div>


        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="ClearAllInterval()"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">订单支付</h4>
                    </div>
                    <div class="modal-body">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist" style="border: none;width: 35%;margin: 0px auto;" id="WeChatIcon">
                            <li role="presentation" class="active" style="text-align: center;">
                                <a href="#home" aria-controls="home" role="tab" data-toggle="tab" style="border: none">
                                    <img src="/img/wxzflogo.jpg" style="width: 170px;">
                                </a></li>
                            <#--            <li role="presentation"><a href="#profile" style="border: none" aria-controls="profile" role="tab" data-toggle="tab">
                                                <img src="/img/zfbzflogo.jpg" style="width: 170px;margin-top: 10px;">
                                            </a></li>-->
                        </ul>
                        <div id="countdown-1" style="display:inline"></div><div style="display: inline">秒后到期</div>
                        <!-- Tab panes -->
                        <div class="tab-content" id="WeChatCodePic">
                            <div role="tabpanel" class="tab-pane active" id="WeChatPayCode" style="text-align: center;">
                                <div style="background: #333232a3; width: 265px;height: 256px;text-align:center;margin: auto;left: 0;/*top: 390px;*/right: 0;bottom: 0;    position: absolute;z-index: 999;display: none" id="SuccessTips">
                                    <img src="/img/zfcg.png" style="width: 130px;margin: 41px auto;">
                                    <p style="color: #fff;margin-top: -50px;font-weight: bold;font-family: initial;font-size: 23px;">支付成功</p>
                                </div>
                            </div>
                            <#--            <div role="tabpanel" class="tab-pane" id="profile">

                                        </div>-->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <#--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="close" onclick="ClearAllInterval()">关闭</button>
                        <button id="btn" onclick="send()">发送</button>
                    </div>
                </div>
            </div>
        </div>
        <#--        <div class="sub_btn clearfix">
                    <div style="float: right;">
                    <span class="substitute_back">支付</span>
                    </div>
                </div>-->
    </div>
</div>
<#--<script type="text/javascript" src="/js/WebSocket.js"></script>-->
<#--<button id="btn" onclick="send()">发送</button>-->
<script type="text/javascript">
    mini.parse();
    var websocket;
    var socket;
    var GovFeeCount = ${GovFeeCount};
    var AllGovFeeTotal = "${AllGovFeeTotal}";
    var OrderNo = "${OrderNo}";
    var UserID = "${UserID}";
    var times = "";
    var ScanTimes = "";
    function ClearAllInterval() {
        clearInterval(ScanTimes);
    }
    $(function () {

    });

    function showPay() {
        $("#WeChatPayCode").empty();
        GetPayWxQrCode();
    }

    function GetPayWxQrCode() {
        // connect();
        var arg = {GovFeeCount: GovFeeCount, AllGovFeeTotal: AllGovFeeTotal, OrderNo: OrderNo};
        var url = "/WeChat/getPayWxqrcode";
        $.post(url, arg, function (result) {
            var res = mini.decode(result);
            if (res['success']) {
                var returnData = result.data || {};
                OrderNo = result.message || "";
                var urlCode = returnData.urlCode;
                jQuery('#WeChatPayCode').qrcode({
                    text: urlCode
                });
                timer();
                getWeChatScanInfo();
                // PushMessageToUser(OrderNo);
            }else {
            }
        })
    }

    function connect() {
        //socket.emit:服务端发送事件
        //socket.on:客户端监听
        var opts = {
            query: 'loginUserId=' + UserID + "&OrderNo=" + OrderNo
        };
        //发布到正式服务器要用域名：https://govfee.zfbip.com
        socket = io.connect('127.0.0.1:9099', opts);
        socket.on('connect', function () {
            console.log("连接成功");
        });
        socket.on('push_data_event', function (data) {
            if (data == UserID) {
                // GetWeChatScanResult(OrderNo);
                $("#myModal").hide();
                $(".modal-backdrop").hide();
                mini.showMessageBox({
                    title: "支付提醒",
                    buttons: ["官费管理", "订单管理"],
                    message: "支付成功！请选择需要跳转的模块",
                    callback: function (action) {
                        CloseWindow("ok");
                        if (action == "官费管理") {
                            window.parent.addParent("/work/govFee/wait", "官费管理");
                        } else {
                            window.parent.addParent("/WeChatOrder/orderlist", "订单管理");
                        }
                    }
                });
            }
        });

        socket.on('disconnect', function () {
            //断开
            $("#WeChatPayCode").empty();
            // $("#myModal").hide();
            $("#close").click();
            mini.alert("付款码已过期，请重新付款", "系统提示", function (r) {
                if (r == 'ok') {
                    $("#GoPay").click();
                }
            });
        });
    }

    function timer() {
        $('#countdown-1').timeTo(600, function(){
            $('#countdown-1').timeTo('reset');
        });
    }

    function getWeChatScanInfo() {
        ScanTimes = setInterval(function () {
            PushMessageToUser();
        }, 1000);
    }

    function PushMessageToUser() {
        var url = "/SocketIO/pushMessageToUser";
        var arg = {UserID: UserID,OrderNo: OrderNo};
        $.post(url, arg, function (result) {
            var res = mini.decode(result);
            if (res['success'] == true) {
                var returnData = result.data || {};
                var returnMessage = result.message || "";
                if (returnData == UserID && returnMessage == "success") {
                    ClearAllInterval();
                    $("#myModal").hide();
                    $(".modal-backdrop").hide();
                    mini.showMessageBox({
                        title: "支付提醒",
                        buttons: ["官费管理", "订单管理"],
                        message: "支付成功！请选择需要跳转的模块",
                        callback: function (action) {
                            CloseWindow("ok");
                            if (action == "官费管理") {
                                window.parent.addParent("/work/govFee/wait", "官费管理");
                            } else {
                                window.parent.addParent("/WeChatOrder/orderlist", "订单管理");
                            }
                        }
                    });
                }else if (returnData == UserID && returnMessage == "fail") {
                    //断开
                    $("#WeChatPayCode").empty();
                    // $("#myModal").hide();
                    $("#close").click();
                    mini.alert("付款码已过期，请重新付款", "系统提示", function (r) {
                        if (r == 'ok') {
                            $("#GoPay").click();
                        }
                    });
                }
            }
        })
    }

    // function GetOrderPayState(OrderNo) {
    //     var flag = true;
    //     if (flag){
    //         var timer = setInterval(function () {
    //             if (flag){
    //                 GetWeChatScanResult(OrderNo);
    //                 var url = "/WeChatOrder/getOrderPayState";
    //                 var arg = {OrderNo:OrderNo};
    //                 $.post(url, arg, function (result) {
    //                     var res = mini.decode(result);
    //                     if (res['success']) {
    //                         var returnData = result.data || {};
    //                         if (returnData.payState == 2){
    //                             flag = false;
    //                             $("#myModal").hide();
    //                             $(".modal-backdrop").hide();
    //                             mini.showMessageBox({
    //                                 title: "支付提醒",
    //                                 buttons: ["官费管理","订单管理"],
    //                                 message: "支付成功！请选择需要跳转的模块",
    //                                 callback: function (action) {
    //                                     CloseWindow("ok");
    //                                     if (action == "官费管理"){
    //                                         window.parent.addParent("/work/govFee/wait","官费管理");
    //                                     }else {
    //                                         window.parent.addParent("/WeChatOrder/orderlist","订单管理");
    //                                     }
    //                                 }
    //                             });
    //                             $(".mini-state-default").css("width","75px");
    //                         }
    //                     }else mini.alert("支付失败，请重新扫码支付！");
    //                 })
    //             }else{
    //                 clearInterval(timer);
    //             }
    //         },1000)
    //     }

    // var host = document.location.host;
    // if(typeof(WebSocket) == "undefined") {
    //     console.log("您的浏览器不支持WebSocket");
    // }else{
    //     var socketUrl='ws://' + host + '/ws/asset/' + OrderNo;
    //     if(websocket!=null){
    //         websocket.close();
    //         websocket=null;
    //     }
    //     websocket = new WebSocket(socketUrl);
    //
    //     //连接发生错误的回调方法
    //     websocket.onerror = function () {
    //         console.log("WebSocket连接错误");
    //     };
    //
    //     //连接成功建立的回调方法
    //     websocket.onopen = function () {
    //         console.log("WebSocket连接成功");
    //         // websocket.send(OrderNo);
    //     };
    //
    //     //接收到消息的回调方法
    //     websocket.onmessage = function (event) {
    //         if (event.data == 2){
    //             // alert("扫描成功!");
    //             websocket.close();
    //         }
    //         // websocket.close();
    //     };
    //
    //     //连接关闭的回调方法
    //     websocket.onclose = function () {
    //         console.log("WebSocket连接关闭");
    //     };
    //
    //     //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    //     window.onbeforeunload = function () {
    //         closeWebSocket();
    //     };
    //
    //     //关闭WebSocket连接
    //     function closeWebSocket() {
    //         websocket.close();
    //     }
    // }
    // }
    // function GetOrderPayState(OrderNo) {
    //     var url = "/WeChatOrder/getOrderPayState";
    //     var arg = {OrderNo: OrderNo};
    //     $.post(url, arg, function (result) {
    //         var res = mini.decode(result);
    //         if (res['success']) {
    //             var returnData = result.data || {};
    //             if (returnData.payState == 2) {
    //                 GetWeChatScanResult(OrderNo);
    //                 $("#myModal").hide();
    //                 $(".modal-backdrop").hide();
    //                 mini.showMessageBox({
    //                     title: "支付提醒",
    //                     buttons: ["官费管理", "订单管理"],
    //                     message: "支付成功！请选择需要跳转的模块",
    //                     callback: function (action) {
    //                         CloseWindow("ok");
    //                         if (action == "官费管理") {
    //                             window.parent.addParent("/work/govFee/wait", "官费管理");
    //                         } else {
    //                             window.parent.addParent("/WeChatOrder/orderlist", "订单管理");
    //                         }
    //                     }
    //                 });
    //                 $(".mini-state-default").css("width", "75px");
    //             }
    //         } else mini.alert("支付失败，请重新扫码支付！");
    //     })
    // }

    function GetWeChatScanResult(OrderNo) {
        var url = "/WeChat/getWeChatScanResult";
        var arg = {OrderNo: OrderNo};
        $.post(url, arg, function (result) {
            var res = mini.decode(result);
            if (result.success) {
                $("#myModal").hide();
                $(".modal-backdrop").hide();
                mini.showMessageBox({
                    title: "支付提醒",
                    buttons: ["官费管理", "订单管理"],
                    message: "支付成功！请选择需要跳转的模块",
                    callback: function (action) {
                        CloseWindow("ok");
                        if (action == "官费管理") {
                            window.parent.addParent("/work/govFee/wait", "官费管理");
                        } else {
                            window.parent.addParent("/WeChatOrder/orderlist", "订单管理");
                        }
                    }
                });
                $(".mini-state-default").css("width", "75px");
            }else mini.alert("支付失败，请重新扫码支付！");
        })
    }

    function send() {
        // var url = "/SocketIO/send";
        // var arg = {OrderNo: OrderNo};
        // $.post(url, arg, function (result) {
        //     var res = mini.decode(result);
        //     if (res['success']) {
        //         var returnData = result.data || {};
        //     };
        // })
        // PushMessageToUser();
    }
    // clearInterval(settime);
    // var settime = setInterval(function(){
    //     showtime();
    // },1000);

    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }
</script>
</body>
</html>
