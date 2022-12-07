<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=1">
    <title>知服帮专利大数据系统</title>
    <title></title>
    <meta name="keywords" content="专利查询,专利年费监控,专利在线缴费,专利法律状态监控"/>
    <meta name="description" content="专利查询,专利年费监控,专利在线缴费,专利法律状态监控"/>
    <link rel="stylesheet" href="/js/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <script type="text/javascript" src="/js/boot.js"></script>
    <!-- Jquery Js -->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="/css/tongyong.css"/>
    <style>
        .login-top {
            width: 980px;
            margin: auto;
            padding: 10px 0;
        }

        .login-top ul li {
            list-style: none;
        }

        .login-toplogo p {
            float: left;
            font-size: 26px;
            color: #4b4b4b;
            line-height: 70px;
            padding-right: 20px;
        }

        .login-topmsg {
            color: #999;
            background: url(/img/ico.png) no-repeat center left;
            text-indent: 20px;
            line-height: 151px;
        }

        /*登录框*/
        .login-base {
            background: #f5d154 url(/img/bg.png) no-repeat center 30px;
            height: 470px;
            margin-top: -47px;
        }

        .login-form1 {
            width: 980px;
            margin: auto;
            overflow: hidden;
        }

        .login-form {
            width: 346px;
            overflow: hidden;
            float: right;
            margin: 57px 0 0 0;
            background: #FFF;
        }

        .login-msg1 {
            color: #999;
            background: url(/img/ico.png) no-repeat 30px center #fff8f0;
            line-height: 38px;
            text-indent: 50px;
        }

        .login-msg2 {
            color: #e4393c;
            line-height: 55px;
            text-indent: 24px;
            font-size: 22px;
            border-bottom: solid 1px #f4f4f4;
        }

        .login-box1 {
            width: 304px;
            height: 38px;
            /*border: solid 1px #bdbdbd;*/
            overflow: hidden;
            margin: auto;
        }

        .login-msg3 {
            line-height: 40px;
            text-align: right;
            color: #656565;
            height: 40px;
            overflow: hidden;
            width: 304px;
            margin: auto;
        }

        .login-btn1 {
            width: 304px;
            margin: auto;
        }

        .login-btn1 input {
            width: 304px;
            height: 34px;
            background: #ff5b1e;
            font-size: 14px;
            font-weight: bold;
            color: #FFF;
            margin: auto;
            border: 0;
            cursor: pointer;
        }

        .login-wxqq {
            background: #fcfcfc;
            border-top: solid 1px #f4f4f4;
            height: 50px;
            margin-top: 10px;
        }

        .login-wxqq ul {
            height: 50px;
            overflow: hidden;
            width: 304px;
            margin: auto;
        }

        .login-wxqq .login-wjmm {
            text-indent: 0px;
            color: #666;
            float: left;
            height: 48px;
            line-height: 48px;
        }

        .login-wxqq .login-qq a, .login-wxqq .login-qq a:visited, .login-wxqq .login-wx a, .login-wxqq .login-wx a:visited, .login-wxqq .login-wjmm a, .login-wxqq .login-wjmm a:visited {
            color: #666;
            text-decoration: none;
        }

        .login-wxqq .login-wx {
            /*background: url(/img/loginwx.png) no-repeat left center;*/
            text-indent: 25px;
            color: #666;
            float: left;
            height: 48px;
            line-height: 48px;
        }

        .login-wxqq .login-reg {
            color: #b9451c;
            font-size: 14px;
            float: right;
            line-height: 48px;
            background: url(/img/reg.png) no-repeat left center;
            text-indent: 20px;
            height: 50px;
        }

        .login-wxqq .login-reg a, .login-wxqq .login-reg a:visited, .login-wxqq .login-reg a:hover {
            color: #b9451c;
            text-decoration: none;
        }

        .login-box1 li.login-box1box {
            height: 38px;
            /*padding-left: 10px;*/
        }

        .login-box1 ul li input {
            /*   float: left;
               margin-top: -2px;
               margin-left: -11px;
               width: 117.2%;
               height: 37px;*/
            width: 114%;
            margin-left: -6px;
            height: 36px;
        }

        .login-wxqq ul li {
            list-style: none;
        }

        .login-box1 ul li {
            list-style: none;
        }

        .mini-textbox-border {
            width: 97%;
            height: 36px;
        }

        .mini-textbox {
            width: 97%;
            height: 36px;
        }

    </style>
</head>
<body style="overflow: hidden;">
<div class="login-top">
    <ul>
        <li class="login-toplogo">
            <p><img src="img/logo.png" height="70"></p>
            <p>欢迎登录</p>
        </li>
        <li class="login-topmsg">为保障您的账户安全和正常使用，请尽快完成手机号验证！</li>
    </ul>
</div>

<div class="login-base">
    <div class="login-form1">
        <div class="login-form">
            <div class="login-msg1">完善个人信息，享受更多特权。</div>
            <div class="login-msg2">知服帮账户登录</div>

            <form action="/login" method="POST">
                <div class="login-box1" style="margin-top:18px;">
                    <ul>
                        <li class="login-box1img"></li>
                        <li class="login-box1box">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1"></span>
                                <input type="text" name="username" id="username" class="form-control"
                                       placeholder="邮箱/手机号码" aria-describedby="basic-addon1">
                            </div>
                    </ul>
                </div>
                <div class="login-box1" style="margin-top:20px;">
                    <ul>
                        <li class="login-box1img1"></li>
                        <li class="login-box1box">
                            <div class="input-group">
                                <span class="input-group-addon" id="basic-addon1">
                                    <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                                </span>
                                <input type="password" name="password" id="password" class="form-control"
                                       placeholder="密码"
                                       aria-describedby="basic-addon1">
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="login-msg3">
                    <span style="float:left;color:#F00" id="Sloginmsg"></span>

                </div>
                <div class="login-btn1"><input type="submit" value="登  录"></div>
                <div class="login-wxqq">
                    <ul>
                        <li class="login-wjmm" style="float: left;"><a href="javascript:void(0)"
                                                                       style="color: #656565;font-size: 15px;">忘记密码</a>
                        </li>
                        <li style="color:#d4cdd5;padding:0 10px;float:left;line-height:48px;"></li>
                        <li class="login-wx"></li>
                        <li class="login-reg" style="float: right;/*margin-right: 38px;*/"><a href="javascript:void(0)"
                                                                                              style="font-size: 15px;">立即注册</a>
                        </li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="home-bottom">
    版权所有：知名未来科技有限公司&nbsp;&nbsp;
    Copyright All Rights Reserved. 2021 Button Creative Agency&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">湘ICP备20007640号-1</a>&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">增值电信业务经营许可证：苏B2-20150515</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">数据范围</a>&nbsp;-&nbsp;<a href=".javascript:void(0)"
                                                                         target="_blank">免责声明</a>
</div>
</body>
</html>
