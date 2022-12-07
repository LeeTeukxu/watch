<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>官费监控</title>
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
<#--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>-->



    <style>
        .Zlyw * {
            display: inline-block;
            vertical-align: middle;
        }

        .Zlyw {
            padding-top: -30px;
        }

        .Zlyw img {
            width: 20px;
        }

        .Zlyw h5 {
            color: #ffffff;
            font-size: 19px
        }

        .Zlywtop {
            display: inline-block;
            width: 500px;
            float: left;
            margin-top: -32px;
            margin-left: 170px;
            height: 32px;
        }

        .Zlywtop ul {
            margin-top: -12px;
            margin-right: -20px;
        }

        .Zlywtop ul li {
            float: left;
            margin-left: 1.5%;
            height: 45px;
            padding-top: 11px;
            /*width: 90px;*/
            border-radius: 5px;
            margin-top: 9.5px;
            text-align: center;
            /**/
        }

        @media screen and (max-width: 1593px) {
            .Zlywtop ul li {
                margin-left: 4.5%;
            }
        }

        @media screen and (max-width: 1340px) {
            .Zlywtop ul li {
                margin-left: 4%;
            }
        }


        @media screen and (max-width: 1235px) {
            .Zlywtop ul li {
                margin-left: 1%;
            }

            .Zlywtop {
                margin-left: 130px;
            }
        }

        .Zlywtop ul li:hover {
            background-color: rgb(74, 106, 157);
        }

        .Zlywtop ul li a {
            color: white;
        }

        .Zlywtop ul li a span {
            font-size: 19px;
        }

        .Zlywtop ul li a h4 {
            display: inline;
            font-size: 19px;
        }

        .info1bottom ul {
            margin-top: 10px;
        }

        .info1bottom ul li {
            float: left;
            margin-left: 10%;
            height: 35px;
            margin-top: -6px;
            width: 156px;
            padding-top: 5px;
            border-radius: 5px;
            text-align: center;
            font-size: 20px;
        }

        @media screen and (max-width: 1593px) {
            .info1bottom ul li {
                margin-left: 12%;
            }
        }

        @media screen and (max-width: 1340px) {
            .info1bottom ul li {
                margin-left: 11%;
            }
        }

        @media screen and (max-width: 1235px) {
            .info1bottom ul li {
                margin-left: 11%;
            }
        }

        .info1bottom ul li:hover {
            background-color: rgb(203, 238, 242)
        }

        .info1bottom ul li a {
            margin-top: 5px;
        }

        .info1bottom ul li a span {
            color: rgb(0, 159, 205);
            font-size: 19px
        }

        .info1bottom ul li a h4 {
            display: inline;
            color: rgb(1, 160, 202)
        }

        .info2bottom {
            margin-top: 10px;
        }

        .info2bottom ul li {
            float: left;
            margin-left: 6%;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1761px) {
            .info2bottom ul li {
                margin-left: 6%;
            }
        }

        @media screen and (max-width: 1550px) {
            .info2bottom ul li {
                margin-left: 3.8%;
            }
        }

        @media screen and (max-width: 1350px) {
            .info2bottom ul li {
                margin-left: 3%;
            }
        }

        @media screen and (max-width: 1250px) {
            .info2bottom ul li {
                margin-left: 2.2%;
            }
        }

        /*@media screen and (max-width:1869px){.info2bottom ul li {margin-left:2%;}  }
        @media screen and (max-width:1798px){.info2bottom ul li {margin-left:1.7%;}  }
        @media screen and (max-width:1696px){.info2bottom ul li {margin-left:1.5%;}  }
        @media screen and (max-width:1673px){.info2bottom ul li {margin-left:1%;}  }
        @media screen and (max-width:1617px){.info2bottom ul li {margin-left:0.5%;}  }*/

        .info2bottom ul li:hover {
            background-color: rgb(216, 228, 250)
        }

        .info2bottom ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info2bottom ul li a h4 {
            display: inline;
            color: rgb(51, 97, 232)
        }

        .info3bottom {
            margin-top: 10px;
        }

        .info3bottom ul li {
            float: left;
            margin-left: 6%;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (min-width: 1480px) and (max-width: 1593px) {
            .info3bottom ul li {
                margin-left: 6.5%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info3bottom ul li {
                margin-left: 5.3%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info3bottom ul li {
                margin-left: 4.4%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info3bottom ul li {
                margin-left: 2.5%;
            }
        }

        .info3bottom ul li:hover {
            background-color: rgb(214, 212, 251)
        }

        .info3bottom ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info3bottom ul li a h4 {
            display: inline;
            color: rgb(52, 101, 232)
        }


        .info4bottom {
            margin-top: 10px;
        }

        .info4bottom ul li {
            float: left;
            margin-left: 8%;
            width: 100px;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (min-width: 1480px) and (max-width: 1593px) {
            .info4bottom ul li {
                margin-left: 6.5%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info4bottom ul li {
                margin-left: 5.3%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info4bottom ul li {
                margin-left: 4.4%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info4bottom ul li {
                margin-left: 2.5%;
            }
        }

        .info4bottom ul li:hover {
            background-color: rgb(53, 102, 231);
        }

        .info4bottom ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info4bottom ul li a h4 {
            display: inline;
            color: rgb(52, 101, 232)
        }


        @media screen and (max-width: 1170px) {
            .info1bottom ul li {
                margin-left: 10%;
            }

            .info2bottom ul li {
                margin-left: 1%;
            }

            .info3bottom ul li {
                margin-left: 0.5%;
            }

            .info4bottom ul li {
                margin-left: 0.5%;
            }
        }
    </style>
</head>
<body style="overflow: hidden; background: #f3f3f4;">
<div class="container" style="padding:0px">
    <div class="mini-clearfix ">
        <div class="mini-col-2">
            <div id="info0" style="height:80px;background-color: rgb(63,87,131);overflow: hidden;border-radius:3px;
            -moz-box-shadow: 2px 2px 10px rgb(63,87,131);-webkit-box-shadow: 2px 2px 10px rgb(63,87,131);-shadow:2px 2px 10px rgb(63,87,131);">
                <div class="file-list">
                    <div class="Zlyw" style="margin-left: 18px;margin-top: 26px;">
                        <img src="/appImages/zongheicon.png" alt="专利业务综合信息">
                        <h5>业务汇总</h5>
                    </div>

                    <div class="Zlywtop">
                        <ul>
                            <li class="ZLywli" id="Z1" onclick=""
                                style="background-color: rgba(247, 140, 24, 0.85);">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                    <span>全部</span>&nbsp;
                                    <h4 class="x9">${ALL}</h4>
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        <div class="mini-col-8">
            <div id="info1"
                 style="height:80px;background:rgb(226,250,252);border:1px solid rgb(190,226,240);border-radius: 5px;">
                <div class="info1top"
                     style="width:100%;height: 30px;border-bottom: 1px solid rgb(214,239,243);text-align: center;">
                    <h2 style="color: rgb(3,154,209);margin-left: 5px;margin-top: 5px;">待缴</h2>
                </div>
                <div class="info1bottom" style="width: 100%;height: 55px">
                    <ul>
                        <li class="Jdlcli" id="J1" onclick="">
                            <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                <h4 id="J1h4" class="x1">0-30天</h4>
                                <span id="J1span">${ZeroToThirty}</span>
                            </a>
                        </li>
                        <li class="Jdlcli" id="J2" onclick="">
                            <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                <h4 id="J1h4" class="x1">30-90天</h4>
                                <span id="J1span">${ThirtyToNinety}</span>
                            </a>
                        </li>
                        <li class="Jdlcli" id="J4" onclick="">
                            <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                <h4 id="J1h4" class="x1">失效</h4>
                                <span id="J1span">${Invalid}</span>
                            </a>
                        </li>
                        <li class="Jdlcli" id="J5" onclick="">
                            <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                <h4 id="J1h4" class="x1">全部</h4>
                                <span id="J1span">${WJALL}</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="mini-col-2">
            <div id="info0" style="height:80px;background-color:rgba(18, 57, 117, 0.71);overflow: hidden;border-radius:3px;
            -moz-box-shadow: 2px 2px 10px rgb(63,87,131);-webkit-box-shadow: 2px 2px 10px rgb(63,87,131);-shadow:2px 2px 10px rgb(63,87,131);">
                <div class="file-list">
                    <div class="Zlyw" style="margin-left: 18px;margin-top: 26px;">
                        <img src="/appImages/zongheicon.png" alt="专利业务综合信息">
                        <h5>已缴</h5>
                    </div>

                    <div class="Zlywtop">
                        <ul>
                            <li class="ZLywli" id="Z1" onclick="" style="background-color: rgba(247, 140, 24, 0.85);">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery()">
                                    <span>已缴</span>&nbsp;
                                    <h4 class="x9">${YJALL}</h4>
                                </a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
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
