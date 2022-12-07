<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>当前 - 年费提醒列表</title>
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
        .pagetitle{border-bottom: solid 1px #dbdee5;height: 44px;width: 99%;margin: 0px auto;}
        .pagetitle p{margin-top: 19px;margin-left: 10px;font-family: initial;font-size: 16px;}
        .pagetitle span{border: 3px solid #00a0e9;height: 10px;width: 10px;}

        .kuang{display: flex;flex-wrap:nowrap;margin-left: 17px;margin-top: 10px}
        .kuang .kuangxq{width: 24%;background: white;height: 150px;border-radius: 10px}
        .mini-textbox{overflow: visible;height: 26px;width: 56%;}
        .btn{background: #3398dc;height: 28px;line-height: 10px;border: none;}
    </style>
</head>
<body style="overflow: hidden;background: white;">
<div class="row">
    <div class="col-md-11">
        <div class="clumsbg" id="clumsbg" style="overflow-x: hidden;overflow-y: auto;height: 855px;background: #f3f3f4;margin-left: -2px;">
            <div class="pagetitle">
                <p><span></span>&nbsp; 当前 - 年费提醒列表</p>
            </div>
        </div>
    </div>
    <div class="col-md-11">
        <div style="/*background: white;*/float: left;margin-top: -736px;margin-left: 0.9%;width: 98%;border-radius: 5px;">
            <div style="float: right;margin-top: -42px;">
                申请号&nbsp;<input class="mini-textbox">&nbsp;
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false">检索</button>
            </div>
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:250px;margin-left: 17px;"
                 url="">
                <div property="columns">
                    <#--<div type="indexcolumn"></div>-->
                    <div field="ApplicationNo" width="50" headerAlign="center" allowSort="true">申请号</div>
                    <div field="cost" width="50" headerAlign="center" allowSort="true">费用名称</div>
                    <div field="Pay" width="50" headerAlign="center" allowSort="true">应缴年费</div>
                    <div field="latefee" width="50" headerAlign="center" allowSort="true">滞纳金</div>
                    <div field="Deadline" width="50" headerAlign="center" allowSort="true">缴费截止日</div>
                    <div field="tixing" width="100" headerAlign="center" allowSort="true">提醒内容</div>
                    <div field="tixpignci" width="50" headerAlign="center" allowSort="true">提醒频次</div>
                    <div field="jsemali" width="50" headerAlign="center" allowSort="true">接收邮箱</div>
                    <div field="fsemali" width="100" headerAlign="center" allowSort="true">发送邮箱</div>
                    <div field="filingdate" width="50" headerAlign="center" allowSort="true">发送日期</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
function tz() {
    var Fupageiframe = parent.document.getElementById("Fupageiframe");
    Fupageiframe.src="/searchResult/AnnualfeeMonitorlist";
}
</script>
</body>
</html>
