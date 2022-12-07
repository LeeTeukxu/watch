<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
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

    <style>
        .zljk-sipo-t {
            width: 100%;
            text-align: center;
            font-size: 20px;
            color: #333;
            padding: 20px 0 0 0;
        }
        .zljk-gk-list2 {
            width: 100%;
            border-left: 15px solid #FFF;
            border-right: 15px solid #FFF;
        }
        .zljk-gk-list2 th {
            border-bottom: solid 1px #e7e7e7;
            padding: 10px 5px;
        }
        .zljk-sipo-t {
            width: 100%;
            text-align: center;
            font-size: 20px;
            color: #333;
            padding: 20px 0 0 0;
        }
        .zljk-gk-list2 td {
            border-bottom: solid 1px #e7e7e7;
            padding: 7px 5px;
            background: white;
        }
    </style>
</head>
<body style="overflow-x: hidden;overflow-y: auto; background: white;">
<div class="row">
    <div class="col-md-12">
        <div class="zljk-sipo-t">未缴费信息</div>
        <table class="ColmunContent1  zljk-gk-list2" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr>
                    <th style="text-align:center">费用种类<input id="sipoappno" type="hidden"></th>
                    <th width="100" style="text-align:center">应缴金额</th>
                    <th width="150" style="text-align:center">缴费截止日</th>
                </tr>
                <#list WJS as WJ>
                    <tr class="bd1">
                        <td>${WJ.costName!}</td>
                        <td align="center">${WJ.amount!}</td>
                        <td align="center">${(WJ.limitDate?string["yyyy.MM.dd"])!}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <div class="zljk-sipo-t">已缴费信息</div>
        <table class="ColmunContent1  zljk-gk-list2" border="0" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <th style="text-align:center">费用种类</th>
                <th width="100" style="text-align:center">应缴金额</th>
                <th width="120" style="text-align:center">缴费日期</th>
                <th width="400" style="text-align:center">缴费人姓名</th>
                <th width="200" style="text-align:center">收据号</th>
            </tr>
            <#list YJS as YJ>
            <tr class="bd1">
                <td>${YJ.costName}</td>
                <td align="center">${YJ.amount!}</td>
                <td align="center">${(YJ.govPayDate?string["yyyy.MM.dd"])!}</td>
                <td align="center">${YJ.payer!}</td>
                <td align="center">${YJ.receiptNo!}</td>
            </tr>
            </#list>
            </tbody></table>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
</script>
</body>

</html>
