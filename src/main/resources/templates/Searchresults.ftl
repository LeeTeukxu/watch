<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
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
    <link rel="stylesheet" href="/css/tongyong.css"/>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!--下拉菜单strat-->
    <script src="/js/paging/js/adapter.js"></script> <!--rem适配js-->
    <link rel="stylesheet" href="/js/paging/css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="/js/paging/css/menu.css"> <!--主样式-->
    <script src="/js/paging/js/menu.js"></script> <!--控制js-->
    <!--下拉菜单end-->

    <link rel="stylesheet" href="/js/pagingone/css/style.css">
    <link rel="stylesheet" href="/js/pagingone/css/animate.css">
    <script type="text/javascript" src='/js/pagingone/js/paging.js'></script>
    <style>
        .nav-tabs > li {
            float: left;
            margin-bottom: -1px;
            width: 49.4%;
            text-align: center;
            background: #888888;
            /*font-size: 0.1vw;*/
            font-size: 13px;
        }

        .nav-tabs {
            border-bottom: none;
            width: 99%;
        }

        .nav-tabs > li a {
            color: white;
        }

        .nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover {
            color: white;
            cursor: default;
            background-color: #eb5a01;
            border: 1px solid #eb5a01;
            border-bottom-color: transparent;
        }

        .mini-checkbox-icon {
            margin-top: -3px;
        }

        .tool {
            width: 89%;
            margin-top: -759px;
            float: right;
            height: 37px;
            background: #eaeaea;
            margin-right: -14px;
        }

        .tool .toolul {
            margin-top: 7px;
            margin-left: -22px;
        }

        .tool .toolul li {
            float: left;
            list-style: none;
            padding-left: 42px;
            font-family: -webkit-body;
            font-size: 14px;
            margin-top: 2px;
        }
        .tool .toolul li:hover {
            cursor: pointer;
        }

        .like {
            color: #0097a0;
        }

        .block p {
            font-weight: bold;
        }

        .daili a {
            color: #0097a0;
        }

        .clearfix:after {
            content: "";
            clear: both;
            display: block;
        }

        .menu, .tab-box {
            width: 300px;
            height: 100%;
            margin: 0 auto;
            padding-top: 25px;
            background: #fff;
        }

        .menu ul li { /*height:45px; */
            line-height: 56px;
            border-bottom: 1px solid #eee;
            font-size: 15px;
        }

        .menu ul li span {
            display: block;
            height: 100%;
            width: 100%;
            position: relative;
            padding: 0 20px;
        }

        .menu ul li span i {
            display: block;
            height: 20px;
            width: 20px;
            background: url(/img/arr.png) no-repeat center center;
            background-size: 80% 80%;
            position: absolute;
            right: 20px;
            top: 50%;
            margin-top: -10px;
            transition: all 0.3s ease-out;
            transform: rotate(0deg);
        }

        .menu ul li span i.action {
            transition: all 0.3s ease-out;
            transform: rotate(180deg);
        }

        .menu ul li > dl {
            display: none;
            padding-left: 35px;
            background: #d8e7f5;
        }

        .menu ul li > dl a {
            display: block;
            height: 100%;
            width: 100%;
            font-size: 14px;
            color: #222;
            text-decoration: none;
        }

        .tab-box {
            width: 600px;
            margin-top: 40px;
        }

        .tab-box .tab {
            border-bottom: 1px solid #eee;
        }

        .tab-box .tab ul li {
            width: 33.3%;
            float: left;
            height: 55px;
            line-height: 55px;
            text-align: center;
        }

        .tab-box .tab ul li.action {
            background: #d8e7f5;
        }

        .tab-box .box {
            width: 100%;
            height: 200px;
        }

        .tab-box .box .item {
            display: none;
            padding: 15px;
            height: 200px;
        }

        .tab-box .box .item:first-child {
            display: block;
        }

        /*检索详细框*/
        .Ex searchsub {
            width: 993px;
            height: 87%;
            overflow-y: auto;
            z-index: 9999;
            position: absolute;
            top: 98px;
            left: 309px;
            border: solid 2px #ddd;
            border-bottom-color: #c7c4be;
            border-right-color: #c7c4be;
            background: white;
        }

        .Exsearchsub .exscolose {
            float: right;
            font-family: "SimSun", "宋体";
            font-size: 13px;
            background-color: #29282896;
            margin-right: 10px;
            margin-top: 6px;
            margin-bottom: 6px;
            border: 1px solid transparent;
            cursor: pointer;
            color: #fff;
        }

        .Exsearchsub .anniuzu {
            margin-top: 23px;
            margin-left: 20px;
        }

        .Exsearchsub .anniuzu .maxniun .btn-warning {
            height: 23px;
            line-height: 10px;
        }

        .Exsearchsub .anniuzu .option {
            width: 80%;
            float: left;
            margin-left: 116px;
            margin-top: -32px;
        }

        .Exsearchsub .anniuzu .option ul li {
            float: left;
            border: solid 1px #CCC;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            height: 23px;
            line-height: 22px;
            overflow: hidden;
            margin: 8px 10px 0 0;
            padding: 0px 5px;
            color: black;
            font-size: 14px;
            font-family: auto;
        }

        .Exsearchsub .anniuzu .option ul li span {
            margin-left: 5px;
            cursor: pointer;
        }

        .checkfx .xk {
            float: left;
            margin-left: 20px;
        }

        .FloatClear {
            width: 100%;
            clear: both;
            height: 0px;
            overflow: hidden;
        }

        .jiansuokuang {
            border: 1px solid #e4e4e4;
            width: 98.2%;
            background: white;
            color: #0C0C0C;
            margin-top: 10px;
            margin-left: 10px;
            height: auto !important;
            min-height: 60px;
        }

        .EXLogic {
            width: 96%;
            margin: auto;
            height: 30px;
            padding: 7px 0;
        }

        .EXLogic table {
            float: left;
            border-collapse: collapse;
        }

        .EXLogic table td {
            height: 26px;
            color: #333;
            border: solid 1px #DDD;
            cursor: pointer;
            text-align: center;
            width: 50px;
            padding: 2px 7px;
            font-size: 13px;
            font-family: auto;
        }

        .EXitemkey {
            width: 97%;
            margin: 0px auto;
            /*   margin-top: 44px;*/
            font-size: 13px;
            font-family: auto;
            font-weight: bold;
        }

        .EXitemkey .Exitem1 {
            float: left;
            width: 50%;
            line-height: 35px;
        }

        .EXitemkey .Exitem2 {
            float: right;
            width: 50%;
            line-height: 35px;
        }

        .EXRNa {
            width: 100%;
        }

        .EXitemkeyname {
            float: left;
            font-size: 11px;
            color: black;
        }

        .EXitemkeysub {
            color: #f46b21;
            margin-left: 30%;
            font-size: 11px;
            cursor: pointer;
            text-decoration: underline;
        }

        .EXitemkeytest {
            float: right;
            color: #AAA;
            margin-top: -48px;
            margin-right: 18%;
            width: 29%;
            font-size: 11px;
        }

        .xk p {
            font-size: 14px;
            color: black;
        }

        .index-cnbtn {
            background: url(/img/sico1.png) no-repeat 0 0;
            border: 0;
            cursor: pointer;
            width: 48px;
            height: 40px;
            margin-top: -6px;
        }

        /*检索历史*/
        .SList-screen2 {
            width: 98%;
            border-right: solid 1px #E1E1E1;
        }

        .SList-screen2 li {
            padding: 5px 2px;
            overflow: hidden;
            white-space: nowrap;
        }

        .SList-screen2 a, .SList-screen2 a:visited, .SList-screen2 a:hover {
            color: #888888;
            font-size: 7px;
        }
        .menu, .tab-box {
            width: 300px;
            height: 100%;
            margin: 0 auto;
            padding-top: 25px;
            background: #fff;
        }
        .menu ul li { /*height:45px; */
            line-height: 56px;
            border-bottom: 1px solid #eee;
            font-size: 15px;
        }
        .menu ul li span {
            display: block;
            height: 100%;
            width: 100%;
            position: relative;
            padding: 0 20px;
        }
        .menu ul li span i {
            display: block;
            height: 20px;
            width: 20px;
            background: url(/img/arr.png) no-repeat center center;
            background-size: 80% 80%;
            position: absolute;
            right: 20px;
            top: 50%;
            margin-top: -10px;
            transition: all 0.3s ease-out;
            transform: rotate(0deg);
        }
        .menu ul li span i.action {
            transition: all 0.3s ease-out;
            transform: rotate(180deg);
        }
        .menu ul li > dl {
            display: none;
            padding-left: 35px;
            background: #d8e7f5;
        }
        .menu ul li > dl a {
            display: block;
            height: 100%;
            width: 100%;
            font-size: 14px;
            color: #222;
            text-decoration: none;
        }
        .top-link ul li a:hover{
            font-size: 18px;
            font-weight:bold;
            color: rgb(0,78,161);
        }

    </style>
</head>
<body style="background: white;overflow: hidden;margin-top: -12px">
<include src="">
<br>
<div class="seltxt"
     style="width: 98.7%;height: 44px;margin-left: 10px;border: solid 1px #CCC;margin-bottom: 11px;">
    <form style="margin-top: 7px;">
        <div class="input-group">
            <input type="text" class="" placeholder="检索结果" value="${word!}" id="txtWord"
                   style="height:40px;margin-top: -6px; font-size: 20px;width: 99%;margin-left: 10px;">
            <input class="mini-hidden" id="hidPara" name="hidPara">
            <input class="mini-hidden" id="hidSearchType" name="hidSearchType">
            <span class="input-group-btn">
           <button type="button" class="btn btn-warning" id="huo"
                   style=""
                   onclick="fmck()">语法检索</button>
           <button class="index-cnbtn" type="button" onclick="querySimple();"></button>
          </span>
        </div><!-- /input-group -->
    </form>

    <div class="Exsearchsub" id="Exsearchsub" style="display: none;background: white;position: absolute;left: 292px;top:11px;width: 902px;height: 642px;overflow: hidden auto;border: 3px solid #cacacac7;">
        <div class="exscolose" onclick="Exsearclose()">关闭</div>
        <div class="anniuzu">
<#--        <div class="anniuzu">-->
<#--            <div class="maxniun">-->
<#--                <button type="button" class="btn btn-warning" onclick="Xzjs()">选择检索范围</button>-->
<#--            </div>-->
<#--            <div class="option">-->
<#--                <ul>-->
<#--                    <li>中国发明申请<span onclick="searchFC(this)">X</span></li>-->
<#--                    <li>中国发明申请<span onclick="searchFC(this)">X</span></li>-->
<#--                    <li>中国发明申请<span onclick="searchFC(this)">X</span></li>-->
<#--                    <li>中国发明申请<span onclick="searchFC(this)">X</span></li>-->
<#--                    <li>中国发明申请<span onclick="searchFC(this)">X</span></li>-->
<#--                </ul>-->
<#--            </div>-->

<#--            <div class="checkfx" id="checkfx" style="display: none;" title="未展开">-->
<#--                    <div class="xk">-->
<#--                    <p><input class="mini-checkbox">中国</p>-->
<#--                    <p>-->
<#--                        <input class="mini-checkbox">中国发明申请-->
<#--                        <input class="mini-checkbox">中国实用新型-->
<#--                        <input class="mini-checkbox">中国外观设计-->
<#--                        <input class="mini-checkbox">中国发明授权-->
<#--                    </p>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
        <div class="FloatClear" style="border-bottom:solid 1px #ddd;margin-top: 13px;"></div>
        <div class="EXLogic">
            <table border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td class="QueryOper">AND</td>
                    <td class="QueryOper">OR</td>
                    <td class="QueryOper">NOT</td>
                    <td class="QueryOper">(</td>
                    <td class="QueryOper">)</td>
                </tr>
                </tbody>
            </table>
            <div class="FloatClear"></div>
        </div>
        <textarea class="jiansuokuang" id="expressObj"></textarea>
        <div class="jiansuoanniu" style="text-align: center;margin-top: 3px;">
            <button type="button" style="width: 12%;" class="btn btn-success" onclick="search()">检索</button>
            &nbsp;&nbsp;&nbsp;
            <button type="button" style="width: 12%;" class="btn btn-default" onclick="clearQuery()">清空</button>
        </div>
        <div class="EXitemkey">
            <div class="Exitem1">
                <div class="EXRNa">
                    <p class="EXitemkeyname">申请号</p>
                    <p class="EXitemkeysub" field="shenqingh">SQH</p>
                    <p class="EXitemkeytest">2018102883290</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">主法律状态</p>
                    <p class="EXitemkeysub" field="lawstatus">ZFRZ</p>
                    <p class="EXitemkeytest">
                        <input class="mini-combobox" id="lawstatus" name="lawstatus"
                               valuefield="text" url="/searchResult/getDistinctLAWSTATUS"/>
                    </p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">公开（公告）号</p>
                    <p class="EXitemkeysub" field="gonkaihao">GKH</p>
                    <p class="EXitemkeytest">CN108775045A</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">申请（专利权）人</p>
                    <p class="EXitemkeysub" field="prishenqingrxm">SQRXM</p>
                    <p class="EXitemkeytest">天津中德应用技术大学</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">当前申请（专利权）人</p>
                    <p class="EXitemkeysub" field="shenqingrxm">DQSQRXM</p>
                    <p class="EXitemkeytest">天津中德应用技术大学</p>
                </div>

                <div class="EXRNa">
                    <p class="EXitemkeyname">发明（设计）人</p>
                    <p class="EXitemkeysub" field="famingrxm">FMRXM</p>
                    <p class="EXitemkeytest">田金颖</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">分类号</p>
                    <p class="EXitemkeysub" field="pic">PIC</p>
                    <p class="EXitemkeytest">E03B7/07</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">摘要</p>
                    <p class="EXitemkeysub" field="memo">ZY</p>
                    <p class="EXitemkeytest">供水专用变频器</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">名称,摘要</p>
                    <p class="EXitemkeysub">FMC,ZY</p>
                    <p class="EXitemkeytest">供水系统</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">专利代理机构</p>
                    <p class="EXitemkeysub" field="dailijgmc">DLJGM</p>
                    <p class="EXitemkeytest">三利</p>
                </div>
            </div>
            <div class="Exitem2">
                <div class="EXRNa">
                    <p class="EXitemkeyname">申请日</p>
                    <p class="EXitemkeysub" field="shenqingr" type="date">SQR</p>
                    <p class="EXitemkeytest">
                        <input class="mini-datepicker" id="SQRBEGIN">
                        <input class="mini-datepicker" id="SQREND">
                    </p>
                </div>
                <div class="EXRNa" style="margin-top: 48px;">
                    <p class="EXitemkeyname">辅法律状态</p>
                    <p class="EXitemkeysub" field="seclawstatus">FFRZ</p>
                    <p class="EXitemkeytest">
                        <input class="mini-combobox" id="seclawstatus" name="seclawstatus"
                               valuefield="text" url="/searchResult/getDistinctSECLAWSTATUS"/>
                    </p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">公开（公告）日</p>
                    <p class="EXitemkeysub" field="gonkair">GKR</p>
                    <p class="EXitemkeytest">
                        <input class="mini-datepicker" id="GKRBEGIN">
                        <input class="mini-datepicker" id="GKREND">
                    </p>
                </div>
                <div class="EXRNa" style="margin-top: 48px;">
                    <p class="EXitemkeyname">地址</p>
                    <p class="EXitemkeysub" field="address">ADD</p>
                    <p class="EXitemkeytest">海河教育园区</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">主分类号</p>
                    <p class="EXitemkeysub" field="pipc">ZPIC</p>
                    <p class="EXitemkeytest">E03B7/07</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">名称</p>
                    <p class="EXitemkeysub" field="famingmc">FMMC</p>
                    <p class="EXitemkeytest">恒压循环供水系统</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">优先权</p>
                    <p class="EXitemkeysub" field="youxianq">YXQ</p>
                    <p class="EXitemkeytest">102013226131</p>
                </div>
                <div class="EXRNa">
                    <p class="EXitemkeyname">代理人</p>
                    <p class="EXitemkeysub" field="dailirxm">DLRXM</p>
                    <p class="EXitemkeytest">张三</p>
                </div>

            </div>
        </div>
    </div>
</div>

<form id="Parmeterform" action="/" method="post" >
<div style="width: 12%;height: 759px;">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#home" data-toggle="tab">筛选项</a></li>
        <li><a href="#profile" data-toggle="tab">检索历史</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="home">
            <div id="menu">
                <!--显示菜单-->
                <div id="open">
                    <div class="navBox">
                        <ul>
                            <li>
                                <h2 class="obtain" id="shenqingr">申请日<i></i></h2>
                                <div class="secondary" id="shenqingrcon">
                                </div>
                            </li>
                            <li>
                                <h2 class="obtain" id="shenqingrxm">申请(权利)人<i></i></h2>
                                <div class="secondary" id="shenqingrxmcon">
                                </div>
                            </li>
                            <li>
                                <h2 class="obtain" id="shenqinglx">申请类型<i></i></h2>
                                <div class="secondary" id="shenqinglxcon">
                                </div>
                            </li>
                            <li>
                                <h2 class="obtain" id="countyname">城市<i></i></h2>
                                <div class="secondary" id="countynamecon">
                                </div>
                            </li>
                            <li>
                                <h2 class="obtain" id="dailijgmc">代理机构<i></i></h2>
                                <div class="secondary" id="dailijgmccon">
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="profile" style="">
            <div class="SList-screen2" style="">
                <ul id="historyContainer">

                </ul>
            </div>
        </div>
    </div>
</div>
</form>
<div style="width: 2px;float: left;margin-top: -759px;overflow-x: hidden;overflow-y: auto;height: 88.6%;background: #EEE;margin-left: 11.7%;" class="leftPanel"></div>
<div class="tool">
    <ul class="toolul">
   <#--     <li><input class="mini-checkbox" name="checkbox"  onvaluechanged="onValueChanged">全选</li>-->
        <#--<li><span id="ms" class="glyphicon glyphicon-pushpin" aria-hidden="true"></span>&nbsp;模式切换</li>-->
        <li id="jxu1" onclick='SearchSortOrder("shenqingr","DESC")'><span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;申请日降序</li>
        <li id="sxu2" onclick='SearchSortOrder("shenqingr","ASC")'><span class="glyphicon glyphicon-export" aria-hidden="true"></span>&nbsp;申请日升序</li>
        <li id="SQRjxu1" onclick='SearchSortOrder("shenqingrxm","DESC")'><span class="glyphicon glyphicon-import" aria-hidden="true"></span>&nbsp;申请人降序</li>
        <li id="SQRsxu2" onclick='SearchSortOrder("shenqingrxm","ASC")'><span class="glyphicon glyphicon-export" aria-hidden="true"></span>&nbsp;申请人升序</li>
        <#--<li id="nfjk" onclick="AddToGov()"><span class="glyphicon glyphicon-circle-arrow-up" aria-hidden="true" ></span>&nbsp;添加年费监控</li>-->
    </ul>
    <ul style="text-align: right;margin-right: 24px;">
        <span id="timeSpan" style="font-size: 12px;color:green"></span>
    </ul>
</div>

<div class="clumsbg" style="width: 89%;float: right;margin-top: -723px;margin-right: -15px;overflow: hidden auto;">
    <div id="resultBox"></div>
    <div id="error" style="width: 100%;text-align: center;line-height: 300px;font-size: 24px;font-weight: bold;">
        无匹配结果，请重新输入查询条件！
    </div>
    <div id="paging">
        <div class="first">首页</div>
        <div class="prev">上一页</div>
        <ul class="list"></ul>
        <div class="go">
            <input type="text" placeholder="Goto">
            <button>跳转页</button>
        </div>·
        <div class="next">下一页</div>
        <div class="last">末页</div>
    </div>
</div>

<script>
    mini.parse();//
    var pageSize = 5, pageIndex = 0, sortField = "shenqingr", sortOrder = "asc";
    var searchPage = "${SearchPage}";
    var Fields = ["memo", "famingmc","address"];
    var indpa = "";
    $(function () {
        if (searchPage == "TableSearch"){
            var strFinal = "${Final}";
            doSearch(strFinal,pageSize,pageIndex,sortField,sortOrder);
            mini.get("hidPara").setValue(strFinal);
            mini.get("hidSearchType").setValue("TableSearch");
        }else {
            var par = "";
            var word = "${word}";
            for (var i=0;i<Fields.length;i++){
                if (i+1<Fields.length){
                    par += Fields[i] + "=" + word + " OR ";
                }else if (i+1==Fields.length){
                    par += Fields[i] + "=" + word;
                }
            }
        }

        if(window.navigator.userAgent.indexOf("MSIE 7.0")>=1){undefined
            //如果浏览器为Firefox
            document.getElementById("huo").style.cssText="margin-top: -3px;height: 32px;margin-right: 9px;border-radius: 7px;line-height: 11px;";
        }
        else if(window.navigator.userAgent.indexOf("Firefox")>=1){undefined
            //如果浏览器为Firefox
            document.getElementById("huo").style.cssText="margin-top: -3px;height: 32px;margin-right: 9px;border-radius: 7px;line-height: 11px;";
        }else{
            //如果浏览器为其它
            document.getElementById("huo").style.cssText="margin-top: -40px;height: 32px;margin-right: 9px;border-radius: 7px;line-height: 11px;";
        }
    });

    $(document).ready(function(){
        var windowHeight = $(window).height();
        if($(".clumsbg").height() < windowHeight){
            $(".clumsbg").height(windowHeight-100);
        }
    });


    function querySimple() {
        var word = $('#txtWord').val();
        mini.get("hidSearchType").setValue("Simple");
        mini.get("hidPara").setValue(word);
        if (word) doQuery(pageSize, pageIndex, sortField, sortOrder);
    }

    var queryTextObj = $('#expressObj');
    
    function SearchSortOrder(searchSortField, searchSortOrder) {
        if (searchPage != "TableSearch" && searchPage != "") {
            doQuery(pageSize, pageIndex, searchSortField, searchSortOrder);
        }else doSearch("${Final}",pageSize, pageIndex, searchSortField, searchSortOrder);
    }

    function doQuery(pageSize, pageIndex, sortField, sortOrder) {
        var word = $('#txtWord').val();
        if (word) {
            var url = '/searchResult/query?pageSize=' + pageSize + '&pageIndex=' + pageIndex + '&sortOrder=' + sortOrder
                + '&sortField=' + sortField;
            $.post(url,{words: word},function (result) {
                if (result.success) {
                    refreshHistory();
                    var data = result.data || [];
                    var total = parseInt(result.total);
                    $('#resultBox').empty();
                    $('#resultBox').append(data);
                    //已添加监控的查询结果禁用复选框
                    $(".clumsbg").find("span").each(function () {
                        var cla = $(this).attr("class");
                        if (cla == "typeanniu1"){
                            var id = $(this).attr("id");
                            var shenqingh = $(this).attr("id").replace("sta","");
                            var txt = $("#" + id).text();
                            if (txt == "已添加监控"){
                                $("#" + shenqingh).attr("disabled","false");
                            }
                        }
                    });
                    $('#paging').paging({
                        nowPage: pageIndex + 1,
                        allPages: Math.ceil(total / pageSize),
                        displayPage: 7,
                        numbers: total,
                        pageChanged:function (pageNum){
                            pageIndex = pageNum - 1;
                            // if (word)doQuery(pageSize, pageIndex, sortField, sortOrder);
                            //筛选后翻页需带筛选条件组合查询
                            doQuery( pageSize, pageIndex, sortField, sortOrder);
                        }
                    });
                  /*  $('.clumsbg').height($('.leftPanel').height() - $('.tool').height() - 35+60);*/
                    if (total > 0) {
                        $('#error').hide();
                        $('#timeSpan').text("共检索到:" + total + '条记录,用时:' + result.time + '毫  秒');
                        $('#paging').show();
                    } else {
                        $('#error').show();
                        $('#timeSpan').text("");
                        $('#paging').hide();
                    }
                }
            })
        }
    }

    function doSearch(word, pageSize, pageIndex, sortField, sortOrder) {
        if (searchPage != "TableSearch" && searchPage != "") {
            word = $('#txtWord').val();
        }
        var url = '/searchResult/search?pageSize=' + pageSize + '&pageIndex=' + pageIndex + '&sortOrder=' + sortOrder
            + '&sortField=' + sortField;
        $.post(url, {words: word}, function (result) {
            if (result.success) {
                refreshHistory();
                var data = result.data || [];
                var total = parseInt(result.total);
                $('#resultBox').empty();
                $('#resultBox').append(data);
                $('#paging').paging({
                    nowPage: pageIndex + 1,
                    allPages: Math.ceil(total / pageSize),
                    displayPage: 7,
                    numbers: total,
                    pageChanged: function (pageNum) {
                        pageIndex = pageNum - 1;
                        if(indpa == ""){
                            indpa = word;
                        }
                        doSearch(indpa, pageSize, pageIndex, sortField, sortOrder);
                    }
                });
              /*  $('.clumsbg').height($('.leftPanel').height() - $('.tool').height() - 35 + 150);*/
                if (total > 0) {
                    $('#error').hide();
                    $('#timeSpan').text("共检索到:" + total + '条记录,用时:' + result.time + '毫秒');
                    $('#paging').show();
                } else {
                    $('#error').show();
                    $('#timeSpan').text("");
                    $('#paging').hide();
                }
            }
        })
    }

    $(function () {
        $('#paging').hide();
        var word = $('#txtWord').val();
        if (word) doQuery(pageSize, pageIndex, sortField, sortOrder); else $('#paging').hide();
        $('.EXitemkeysub').click(function () {
            var obj = $(this);
            var field = obj.text();
            var nowText = $.trim(queryTextObj.val() || "");
            if (nowText.endsWith("=")) {
                field = " AND " + field + " ";
            }
            if (field == "SQR"){
                var SQRBEGIN = mini.get("SQRBEGIN").getValue();
                var SQREND = mini.get("SQREND").getValue();
                nowText = nowText + field + "={" + DateParse(SQRBEGIN) + "," + DateParse(SQREND) + "}";
            }else if (field == "GKR"){
                var GKRBEGIN = mini.get("GKRBEGIN").getValue();
                var GKREND = mini.get("GKREND").getValue();
                nowText = nowText + field + "={" + DateParse(GKRBEGIN) + "," + DateParse(GKREND) + "}";
            }else if (field == "ZFRZ"){
                var ZFRZ = mini.get("lawstatus").getValue();
                nowText = nowText + field + "=" + ZFRZ;
            }else if (field == "FFRZ"){
                var FFRZ = mini.get("FFRZ").getValue();
                nowText = nowText + field + "=" + FFRZ;
            }
            else nowText = nowText + field + "=";
            queryTextObj.val(nowText);
        });

        $('.QueryOper').click(function () {
            var obj = $(this);
            var field = obj.text();
            if (field == "AND" || field == "NOT" || field == "OR") {
                field = " " + field + " ";
            }
            insertAtCursor(queryTextObj[0], field);
        });
        refreshHistory();
    });

    function DateParse(dates) {
        var year = dates.getFullYear();
        var month = dates.getMonth() + 1;
        var date = dates.getDate();
        var datetime = year + '-' + (month > 9?(month + "") : ("0" + month))+ '-' + (date>9?(date + "") : ("0" + date));
        return datetime;
    }

    function Xzjs() {
        var checkfxtitle = document.getElementById("checkfx").title;
        if (checkfxtitle == "未展开") {
            document.getElementById("checkfx").style.display = "";
            document.getElementById("checkfx").title = "已展开";
        } else if (checkfxtitle == "已展开") {
            document.getElementById("checkfx").style.display = "none";
            document.getElementById("checkfx").title = "未展开";
        }
    }

    function fmck() {
        $("#Exsearchsub").show();
/*        document.getElementById("ms").style.cssText="display:none";
        document.getElementById("nfjk").style.cssText="display:none";*/
/*        if(document.getElementById("jxu1").style.display==""){
            document.getElementById("jxu1").style.display="none";
        }else {
            document.getElementById("jxu2").style.display="none";
        }*/
        document.getElementById("sxu2").style.display="none";
        document.getElementById("SQRjxu1").style.display="none";
        document.getElementById("SQRsxu2").style.display="none";
    }

    function Exsearclose() {
       /* $("#Exsearchsub").show();
        document.getElementById("ms").style.display="";
        document.getElementById("nfjk").style.display="";
       document.getElementById("jxu1").style.display="";*/
        document.getElementById("sxu2").style.display="";
        document.getElementById("SQRjxu1").style.display="";
        document.getElementById("SQRsxu2").style.display="";
        $("#Exsearchsub").hide();
    }

    function insertAtCursor(myField, myValue) {
        //IE 浏览器
        if (document.selection) {
            myField.focus();
            sel = document.selection.createRange();
            sel.text = myValue;
            sel.select();
        }
        //FireFox、Chrome等
        else if (myField.selectionStart || myField.selectionStart == '0') {
            var startPos = myField.selectionStart;
            var endPos = myField.selectionEnd;
            // 保存滚动条
            var restoreTop = myField.scrollTop;
            myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
            if (restoreTop > 0) {
                myField.scrollTop = restoreTop;
            }
            myField.focus();
            myField.selectionStart = startPos + myValue.length;
            myField.selectionEnd = startPos + myValue.length;
        } else {
            myField.value += myValue;
            myField.focus();
        }
    }

    function search() {
        var text = queryTextObj.val();
        if (!text) return;
        var hash = getFieldHash();
        for (var field in hash) {
            text = text.replaceAll(field, hash[field]);
        }
        mini.get("hidPara").setValue(text);
        mini.get("hidSearchType").setValue("TableSearch");
        doSearch(text, pageSize, pageIndex, sortField, sortOrder);
    }

    function getFieldHash() {
        var res = {};
        var cons = $('.EXitemkeysub');
        for (var i = 0; i < cons.length; i++) {
            var con = $(cons[i]);
            var field = con.attr('field');
            var old = con.text();
            if (old && field) {
                res[old] = field;
            }
        }
        return res;
    }

    function clearQuery() {
        queryTextObj.val("");
    }

/*    function Lift(obj) {
        if (obj == 1) {
            document.getElementById("jxu1").style.display = "none";
            document.getElementById("sxu2").style.display = "";
        } else {
            document.getElementById("jxu1").style.display = "";
            document.getElementById("sxu2").style.display = "none";
        }
    }*/
    function refreshHistory(){
        var url='/searchResult/getHistory';
        $.getJSON(url,{},function(result){
            if(result.success){
                var con=$('#historyContainer');
                con.empty();
                var datas=result.data ||[];
                for(var i=0;i<datas.length;i++){
                    var data=datas[i];
                    var html='<li style="text-align:center"><a href="#"' + 'onclick="doHistory()">'+data+'</a></li>';
                    con.append(html);
                }
            }
        })
    }
    function doHistory(){
        var dd=$(event.target || event.srcElement);
        var text=dd.text();
        if(text.indexOf('=')>-1){
            queryTextObj.val(text);
            doSearch(text,pageSize,0,sortField,sortOrder);
        }
        else
        {
            $('#txtWord').val(text);
            doQuery(pageSize,0,sortField,sortOrder);
        }
        event.stopPropagation();
        return false;
    }

    function windowtz(obj) {
      location.href="/searchResult/Patentdetails?SHENQINGH="+obj;
    }

    function onValueChanged(e) {
        var checked = this.getChecked();
        if (checked ==  true) {
            $(".clumsbg input[type='checkbox']").each(function(){this.checked=true;});
        }else $(".clumsbg input[type='checkbox']").each(function(){this.checked=false;});
    }
    
    function AddToGov() {
        var arr = [];
        $('.clumsbg input[type="checkbox"]:checked').each(function () {
            arr.push($(this).attr("tag"));
        });
        if (arr.length == 0){
            mini.alert("请选择需要监控年费的专利！");
            return;
        }
        var url = "/Annual/addannual";
        $.ajax({
            contentType:'application/json',
            method:'post',
            url:url,
            data:mini.encode(arr),
            success:function (r) {
                if (r['success']) {
                    mini.alert("监控专利成功！");
                    for (var i=0;i<arr.length;i++){
                        var shenqingh = arr[i];
                        $('#'+shenqingh+'sta').text("已添加监控");
                        $(".clumsbg input[type='checkbox']").each(function(){
                            var sectionshenqingh = $(this).attr("tag");
                            if (shenqingh == sectionshenqingh){
                                this.checked=false;
                                $(this).attr("disabled",true);
                            }
                        });
                    }
                }
                else mini.alert("监控专利失败！");
            },
            failure:function (error) {
                alert(error);
            }
        })
    }
</script>
</body>
</html>
