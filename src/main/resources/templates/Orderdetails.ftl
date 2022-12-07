<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>订单详情</title>
    <link rel="stylesheet" href="scripts/miniui/themes/default/miniui.css"/>
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
        .sub_feiyong {
            color: red;
        }
    </style>
</head>
<body style="overflow-y: auto;overflow-x: hidden; background: #fff;">

<div class="row" >
    <div class="col-md-12">
       <div class="titlebj">
         <h2>专利缴费订单详情</h2>
       </div>
        <button type="button" class="btn btn-success">导出</button>
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:700px;"
             allowresize="true" url="/WeChatOrder/getOrderDetailData?OrderNo=${OrderNo}" multiselect="true"
             pagesize="10" sizelist="[5,10,20,50,100,150,200]" sortfield="LimitDate" sortorder="desc"
             autoload="true" onDrawCell="onDraw" allowresize="true">
            <div property="columns">
                <div type="indexcolumn" headerAlign="center"></div>
                <div field="appNo" width="50" headerAlign="center" align="center" allowSort="true">专利号</div>
                <div field="clientName" width="50" headerAlign="center" align="center" allowSort="true">客户名称</div>
                <div field="famingmc" width="50" headerAlign="center" align="center" allowSort="true">专利名称</div>
                <div field="feename" width="50" headerAlign="center" align="center" allowSort="true">费用名称</div>
                <div field="feeprice" width="50" headerAlign="center" align="center" allowSort="true">费减前金额</div>
                <div field="amount" width="50" headerAlign="center" align="center" allowSort="true">费减后金额</div>
                <div field="afterprice" width="50" headerAlign="center" align="center" allowSort="true">节省金额</div>
                <div field="limitDate" width="60" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd">缴费截止日期</div>
<#--                <div field="time_End" width="60" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">支付完成日期</div>-->
<#--                <div field="bank_Type" width="50" headerAlign="center" align="center" allowSort="true">支付银行</div>-->
<#--                <div field="transaction_Id" width="120" headerAlign="center" align="center" allowSort="true">支付银行</div>-->
<#--                <div field="payState" name="Oper" width="50" headerAlign="center" align="center" >支付状态</div>-->
<#--                <div field="djState" name="Oper" width="50" headerAlign="center" align="center" >缴费状态</div>-->
            </div>
        </div>
        <div class="substitute_news clearfix">
            <div class="order_pay_left">
                <span>代缴专利：<span class="sub_feiyong">${GovFeeCount}件</span></span>
                <span class="sub_paddleft"><span>代缴费用：</span><span class="sub_feiyong">${GovFeeTotal}元</span><span>（官费）</span>
                </span> <span class="sub_paddleft"><span>服务费：</span><span class="sub_feiyong">${ServiceChargeTotal}元</span></span>
                <span class="sub_paddleft"><span>费用代缴单价：</span><span class="sub_feiyong">100元 / 件</span></span>
                </span> <span class="sub_paddleft"><span>共计节省金额：</span><span class="sub_feiyong">${AfterPriceTotal}元</span></span>
            </div>

        </div>
<#--        <table class="table table-bordered" style="margin-top: 10px;">-->
<#--            <thead style="border: none;background: #f2f2f2;">-->
<#--            <tr>-->
<#--                <th>专利号</th>-->
<#--                <th>客户名称</th>-->
<#--                <th>专利名称</th>-->
<#--                <th>费用名称</th>-->
<#--                <th>应缴金额</th>-->
<#--                <th>截止日期</th>-->
<#--                <th>支付时间</th>-->
<#--                <th>支付银行</th>-->
<#--                <th>微信支付订单号</th>-->
<#--                <th>状态</th>-->
<#--                <th>代缴状态</th>-->
<#--            </tr>-->
<#--            </thead>-->
<#--            <tbody>-->
<#--            <#list OrderDetails as OrderDetail>-->
<#--            <tr>-->
<#--                <td>${OrderDetail.appNo!}</td>-->
<#--                <td>${OrderDetail.clientName}</td>-->
<#--                <td>${OrderDetail.FAMINGMC!}</td>-->
<#--                <td>${OrderDetail.FEENAME!}</td>-->
<#--                <td>${OrderDetail.amount!}</td>-->
<#--                <td>${(OrderDetail.limitDate?string["yyyy.MM.dd"])!}</td>-->
<#--                <td>${(OrderDetail.time_End?string["yyyy.MM.dd"])!}</td>-->
<#--                <td>${OrderDetail.bank_Type!}</td>-->
<#--                <td>${OrderDetail.transaction_Id!}</td>-->
<#--                <td>-->
<#--                    <#if OrderDetail.payState == 1>-->
<#--                        待支付-->
<#--                    </#if>-->
<#--                    <#if OrderDetail.payState == 2>-->
<#--                        支付成功-->
<#--                    </#if>-->
<#--                    <#if OrderDetail.payState == 3>-->
<#--                        支付失败-->
<#--                    </#if>-->
<#--                </td>-->
<#--                <td>-->
<#--                    <#if OrderDetail.payState == 1>-->
<#--                        <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">未代缴</div>-->
<#--                    <#elseIf OrderDetail.djState = 2>-->
<#--                        <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴中</div>-->
<#--                    <#elseIf OrderDetail.djState = 3>-->
<#--                        <div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴完成</div>-->
<#--                    </#if>-->
<#--                </td>-->
<#--            </tr>-->
<#--            </#list>-->
<#--            </tbody>-->
<#--        </table>-->
    </div>
</div>

<script type="text/javascript">
    mini.parse();
    $(function () {
        var str = document.getElementsByTagName("td");
        for (var i=0;i<str.length;i++){
            if (str[i].innerText.length>=20){
                var wow = str[i].innerText.substr(0,20) + '...' ;
                str[i].innerText=wow;
            }
        }
    })

    function onDraw(e) {
        var field = e.field;
        var record = e.record;
        if (field == "payState"){
            var PayState = record["payState"];
            if (PayState == 1){
                e.cellHtml = "待支付";
            }else if (PayState == 2){
                e.cellHtml = "支付成功";
            }else if (PayState == 3){
                e.cellHtml = "支付失败";
            }
        }
        if (field == "djState"){
            var PayState = record["payState"];
            var DjState = record["djState"];
            if (PayState == 1){
                e.cellHtml = '<div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">未代缴</div>'
            }else if(DjState ==  2){
                e.cellHtml = '<div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴中</div>'
            }else if (DjState == 3){
                e.cellHtml = '<div style="border-radius: 5px;background: red;color: white;width: 73px;margin: 0px auto;text-align: center;font-size: 14px;font-family: auto;">代缴完成</div>'
            }
        }
        if (field == "afterprice") {
            var afterprice = record["afterprice"];
            if (afterprice != "0") {
                e.cellHtml = '<span style="color: #337ab7">' + afterprice + '</span>'
            }else e.cellHtml = '';
        }
    }
</script>
</body>
</html>
