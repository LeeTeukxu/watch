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
<#--    <script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script>-->
<#--    <script type="text/javascript" src="/js/left-side-menu.js"></script>-->
    <link rel="stylesheet" href="css/tongyong.css"/>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <style>
        .index-base{
            margin: 0px auto;
            margin-top: 100px;
            background: url(/img/homebg.png)no-repeat;
            height: 600px;
            width: 65%;
        }

        .index-searchbox {
            width: 53%;
            overflow: hidden;
            margin-top: 213px;
            /* margin-left: 216px; */
            margin: 87px auto;
        }
        .index-searchbox .index-box {
            /*border: solid 1px #a5a5a5;*/
            border-right: 0;
            width: 90%;
            height: 48px;
            margin-left: -36px;
        }

        .index-searchbox li {
            list-style: none;
        }

        .index-searchbox .index-box input {
            border: 0;
            height: 51px;
            margin: 2px 0px 0px 36px;
            font-family: 'Microsoft YaHei';
            font-size: 14px;
            width: 100.7%;
            text-indent: 15px;
            border: solid 1px #a5a5a5;
            border-radius: 4px;
            border-right: none;
            background: url(/img/sellogo.png) no-repeat;
            background-position: 10px;
            text-indent: 54px;
            background-color: white;
        }
        .index-cnbtn {
            background: url(/img/cnbtn.png) no-repeat 0 0;
            border: 0;
            cursor: pointer;
            width: 66px;
            height: 51px;
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
<body style="overflow: hidden;background: white;">

<div class="Meul_list">
    <div class="top-logo">
        <a href="javascript:void(0)">
            <img src="/img/logo.png" style="height: 35px;">
        </a>
    </div>
    <div class="top-login">

        <ul style="margin-top: -5px;">
            <#if HasLogin=0>
                <li class="login"><a id="toploginsrc" onclick="doLogin();">登录</a></li>
            </#if>
            <li><a href="#" onclick="">修改密码</a></li>
            <li><a href="#" onclick="exitSystem()">退出</a></li>
        </ul>
    </div>
    <div class="top-link">
        <ul>
            <li></li>
            <#if HasLogin=1>
                <#if roleName=='系统管理员'>
                    <li style="font-size:16px"><a href="/admin" target="_blank" class="drop">后台管理</a></li>
                </#if>
            </#if>
            <li style="font-size:16px"><a href="/searchResult/index" target="_blank" class="drop">专利检索</a></li>
            <#--<li style="font-size:16px"><a href="/searchResult/Tableretrieval" target="_blank" class="drop">表格检索</a></li>-->
            <li style="font-size:16px"><a href="/index" target="_blank" class="drop">官费管理</a></li>

            <li style="font-size:16px"><a href="" target="_blank" class="drop">专利交易</a></li>
        </ul>
    </div>
</div>

<#--<div style="margin: 0px auto;text-align: center;margin-top: 100px;"><img src="/img/logow.png"></div>-->
<#--<div style="margin: 0px auto;text-align: center;margin-top: 100px;">
    <img src="/img/homebg.png">
</div>-->
<div class="index-base" style="margin:0px auto;margin-top: 100px;background: url('/img/homebg.png')">

    <div class="index-searchbox">
        
        <div style="height: 222px;text-align: center;">
            <img src="/img/logopg.png" style="margin-top: 50px;">
        </div>
        <div class="checkboxall" style="font-family: auto;color: #ababab;">
            <input type="checkbox" value="中国发明" checked="checked"/>&nbsp;&nbsp;中国发明&nbsp;&nbsp;
            <input type="checkbox" value="中国实用新型" checked="checked"/>&nbsp;&nbsp;中国实用新型&nbsp;&nbsp;
            <input type="checkbox" value="中国外观设计" checked="checked"/>&nbsp;&nbsp;中国外观设计&nbsp;&nbsp;
            <input type="checkbox" value="中国发明"/>&nbsp;&nbsp;中国发明授权&nbsp;&nbsp;
        </div>
        <br>
        <div>
            <ul>
                <li class="index-box">
                    <input id="searchWord" placeholder="专利信息快速检索" />
                </li>
                <li style="float: right;margin-top: -46px;"><button class="index-cnbtn" onclick="return tz()"></button></li>
            </ul>
        </div>
    </div>
</div>
<!---->
<div class="home-bottom">
    版权所有：知名未来科技有限公司&nbsp;&nbsp;
    Copyright All Rights Reserved. 2021 Button Creative Agency&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">湘ICP备20007640号-1</a>&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">增值电信业务经营许可证：苏B2-20150515</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">数据范围</a>&nbsp;-&nbsp;<a href="javascript:void(0)" target="_blank">免责声明</a>
</div>
<script>
    //
    function tz() {
        <#if HasLogin=0>
            window.location.href = "/login.html";
        <#else>
        var word=$('#searchWord').val();
        window.location.href='/searchResult/index?word='+word+'&searchPage=IndexSearch';
        </#if>
        return true;
    }
</script>
</body>
</html>
