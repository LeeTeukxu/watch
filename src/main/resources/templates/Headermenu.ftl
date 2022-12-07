<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="/js/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/boot.js"></script>
    <link rel="stylesheet" href="/css/tongyong.css"/>
    <link rel="stylesheet" href="/js/pagingone/css/style.css">
    <link rel="stylesheet" href="/js/pagingone/css/animate.css">

    <style>
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
        @media screen and  (max-width: 1560px) {
            .top-link {padding-left: 100px;overflow: hidden;float: left;margin-top: 14px;}
            .top-link li, .top-link li a {
                padding: 0 10px;
            }
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
<body style="overflow: hidden;">
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
            <#else>
                <li class="login"><a href="#">您好，${username!}！</a></li>
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
            <li style="font-size:16px"><a href="/searchResult/index" target="_self" class="drop">专利检索</a></li>
<#--            <li style="font-size:16px"><a href="/searchResult/Tableretrieval" target="_blank" class="drop">表格检索</a></li>-->
            <li style="font-size:16px"><a href="/index" target="_self" class="drop">官费管理</a></li>

            <li style="font-size:16px"><a href="" target="_blank" class="drop">专利交易</a></li>
        </ul>
    </div>

</div>
<script>
    //
    function doLogin(){
        top.location.href="/login.html";
    }
    function loginOut(){
        top.location.href="/logout";
        top.location.href="/login.html";
    }
    function exitSystem(){
        mini.confirm('确认要退出系统，未保存的数据都将丢失，是否继续?','退出提示',function(result){
            if(result=='ok'){
                window.location.href='/logout';
                window.location.href='/login.html';
            }
        });
    }

</script>
</body>
</html>
