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


    <!--下拉菜单strat-->
    <script src="/js/paging/js/adapter.js"></script> <!--rem适配js-->

    <link rel="stylesheet" href="/js/paging/css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="/js/paging/css/menu.css"> <!--主样式-->
    <script src="/js/paging/js/menu.js"></script> <!--控制js-->
    <!--下拉菜单end-->

    <style>
     .pagetitle{height: 25px;width: 99%;margin: 0px auto;}
     .pagetitle p{margin-top: 19px;margin-left: 10px;font-family: initial;}
     .pagetitle span{border: 1px solid #00a0e9;height: 10px;width: 10px;}

     .kuang{display: flex;flex-wrap:nowrap;margin-left: 17px;margin-top: 10px}
     .kuang .kuangxq{width: 19%;background: white;height: 150px;border-radius: 10px;box-shadow: -3px -1px 20px 2px #4240401c;}
     .zljk-p1 {
         padding: 10px 15px;
         font-size: 18px;
         font-family: 'Microsoft YaHei';
         color: #333;
         white-space: nowrap;
         overflow: hidden;
     }
     .zljk-div1 {
         padding: 30px 15px;
         font-family: 'Microsoft YaHei';
         font-size: 14px;
         color: #333;
         position: relative;
     }
     .zljk-div1 span {
         font-size: 30px;
         padding: 0 5px;
         color: #ff6a00;
         font-family: auto;
     }
     .zljk-div1sub {
         float: left;
         width: 49%;
         position: absolute;
         font-family: inherit;
     }
     .jiazai{text-align: center;margin-top: 4px;height: 31px;}
     .jiazai a{text-decoration: none;color: rgb(104,164,234);font-size: 14px;font-weight: 700;}
     .jnqtitle{height: 30px;margin-top: 10px;margin-left: 16px;color: black;font-size: 18px;font-family: auto;}
    </style>
</head>
<body style="overflow: hidden;background: white;">

<div class="row">
    <div class="col-md-11" style="background: #f3f3f4;height: 8px;"></div>
    <div class="col-md-11">
        <div class="clumsbg" id="clumsbg" style="overflow-x: hidden;overflow-y: auto;height: 228px;background: #ffffff;margin-left: -2px;">
            <div class="pagetitle">
                <p><span></span>&nbsp; 年费监控汇总</p>
            </div>
            <div class="kuang">
                <div class="kuangxq" style="border-left: 3px solid rgb(3,104,220);">
                    <p class="zljk-p1" style="color: rgb(3,104,220)">年费监控总量</p>
                    <div class="zljk-div1">
                        <span style="color: rgb(3,104,220)">${PatentInfoPermissionCount}</span>件
                        <div style="position:absolute;right:20px;top:10px;font-size:16px;font-family: initial;">
                            发明专利 ${FMCount} 件<br>实用新型 ${SYCount} 件<br>外观设计 ${WGCount} 件
                        </div>
                    </div>
                </div >
                <div style="width: 3%;"></div>
                <div class="kuangxq" style="border-left: 3px solid rgb(255,127,102);">
                    <div class="zljk-gk-item">
                        <p class="zljk-p1" style="color:rgb(255,127,102);">预计今年年费</p>
                        <div class="zljk-div1">
                            <span style="color:rgb(255,127,102);">${GovFeeCount}</span>元
                        </div>
                    </div>
                </div>
                <div style="width: 3%;"></div>
                <div class="kuangxq" style="border-left: 3px solid rgb(54,198,44);">
                    <div class="zljk-gk-item">
                        <p class="zljk-p1" style="color:rgb(54,198,44);">急需缴纳年费</p>
                        <div class="zljk-div1">
                            <span>&nbsp;</span>
                            <div class="zljk-div1sub" style="top:10px">90天内 <span style="color:rgb(54,198,44);">${nine}</span> 件</div>
                            <div class="zljk-div1sub" style="top:10px;left:50%">60天内 <span style="color:rgb(54,198,44);">${six}</span> 件</div>
                            <div class="zljk-div1sub" style="top:40px">30天内 <span style="color:rgb(54,198,44);">${three}</span> 件</div>
                            <div class="zljk-div1sub" style="top:40px;left:50%">15天内 <span style="color:rgb(54,198,44);">${fifty}</span> 件</div>
                        </div>
                    </div>
                </div>
                <div style="width: 3%;"></div>
                <div class="kuangxq" style="border-left: 3px solid rgb(199,98,244);">
                    <div class="zljk-gk-item">
                        <p class="zljk-p1" style="color:rgb(199,98,244);">已滞纳专利</p>
                        <div class="zljk-div1">
                            <span style="color:rgb(199,98,244);">${ZNJ}</span>件
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span style="color:rgb(199,98,244);">0</span>元
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="col-md-11" style="background: #f3f3f4;height: 8px;"></div>

    <div class="col-md-11">
        <div style="background: white;float: left;margin-left: 0.9%;width: 98%;border-radius: 5px;">
            <div class="jnqtitle"><h2>需缴纳年费列表（前5条）：</h2></div>
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:250px;margin-left: 17px;"
                 allowresize="true" url="/Annual/getTopMonitor" multiselect="true"
                 pagesize="5" sizelist="[5,10,20,50,100,150,200]" sortfield="CreateTime" sortorder="desc"
                 autoload="true" >
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center">序号</div>
                    <div field="SHENQINGH" width="50" headerAlign="center" align="center" allowSort="true">申请号</div>
                    <div field="FAMINGMC" width="50" headerAlign="center" align="center" allowSort="true">名称</div>
                    <div field="SHENQINGR" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                    <div field="CostName" width="50" headerAlign="center" align="center" allowSort="true">费用名称</div>
                    <div field="Amount" width="50" headerAlign="center" align="center" allowSort="true">应缴纳年费</div>
                    <div field="LimitDate" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">官方缴费截止日</div>
                </div>
            </div>
            <div class="jiazai"><a href="javascript:void(0)" onclick="tz()" >加载更多</a></div>
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
