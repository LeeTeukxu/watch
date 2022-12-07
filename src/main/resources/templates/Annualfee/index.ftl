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
     .leftmeul{background:#ffffff;width: 160px;height: 105%;}
     .nftitle h1{font-size: 14px;padding: 15px 10px;font-weight: bold;color: black;}
     .jkxx ul{/*margin-left: 11px;*/}
     .jkxx ul li{list-style: none;line-height: 34px;}
     .jkxx .Select{background:rgb(202,222,255);}
     .jkxx .Select a{color: rgb(43,128,227);font-family: initial;}
     .jkxx ul li:hover,.jkxx ul li a:hover{background: rgb(202,222,255);color:rgb(43,128,227); }
     .jkxx ul li a{text-decoration: none;font-size: 14px;color: black;display: block;margin-left: 14px;font-family: initial;}

    </style>
</head>
<body style="overflow: hidden;background: #f3f3f4;">
<iframe style="width: 100%;border: none;overflow: hidden;height: 77px;" src="/searchResult/menu"></iframe>
<!--主体-->
<div class="row" style="margin-top: -15px;">
    <div class="col-md-1" style="height: 855px;">
        <div class="leftmeul">
            <div class="nftitle"><h1>年费监控管理</h1></div>
            <div class="jkxx">
                <ul>
                    <li class="Select"><a href="javascript:void(0)" onclick="tz(1)">概况</a></li>
                    <li><a href="javascript:void(0)" onclick="tz(2)">添加专利</a></li>
                    <li><a href="javascript:void(0)" onclick="tz(3)">年费监控</a></li>
                    <li><a href="javascript:void(0)" onclick="tz(4)">年费提醒</a></li>
                    <li><a href="javascript:void(0)">年费对照表</a></li>
                    <li><a href="javascript:void(0)" onclick="tz(5)">专利管理</a></li>
                    <li><a href="javascript:void(0)">提醒方式设置</a></li>
                    <li><a href="javascript:void(0)">会员购买服务</a></li>
                </ul>
            </div>
            <div class="nftitle"><h1>年费代理管理</h1></div>
            <div class="jkxx">
                <ul>
                    <li><a href="javascript:void(0)">年费代理管理介绍</a></li>
                    <li><a href="javascript:void(0)" onclick="tz(9)" target="_blank" class="drop">专利联系人</a></li>
                    <li><a href="javascript:void(0)">邮件预警记录</a></li>
            <li><a href="javascript:void(0)" onclick="tz(11)">发送邮箱设定</a></li>
        </ul>
    </div>
    </div>
   </div>

    <div class="col-md-11">
        <iframe src="/searchResult/AnnualfeeGaikuang" id="Fupageiframe" style="overflow: hidden;width: 109%;height: 855px;border: none;margin-left: 5px;"></iframe>
    </div>
</div>


<#--
<div class="home-bottom">
    版权所有：知名未来科技有限公司&nbsp;&nbsp;
    Copyright All Rights Reserved. 2021 Button Creative Agency&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">湘ICP备20007640号-1</a>&nbsp;&nbsp;
    <a href="javascript:void(0)" target="_blank">增值电信业务经营许可证：苏B2-20150515</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    <!--<a href="javascript:void(0)" target="_blank">润桐资讯</a><&ndash;&gt;<!--&nbsp;-&nbsp;帮助中心&nbsp;-&nbsp;&ndash;&gt;
    <a href="javascript:void(0)" target="_blank">数据范围</a>&nbsp;-&nbsp;<a href=".javascript:void(0)" target="_blank">免责声明</a>
</div>
-->

<script type="text/javascript">
function tz(obj) {
  if (obj==1){
      document.getElementById("Fupageiframe").src="/searchResult/AnnualfeeGaikuang";
  }else if(obj==2){
      document.getElementById("Fupageiframe").src="/searchResult/Addpatent";
  } else if(obj==3){
      document.getElementById("Fupageiframe").src="/searchResult/AnnualfeeMonitorlist";
  }else  if(obj==4) {
      document.getElementById("Fupageiframe").src = "/searchResult/Annualfeereminder";
  }else if (obj==5){
      document.getElementById("Fupageiframe").src = "/cpatent/index";
  }else if (obj==9){
      document.getElementById("Fupageiframe").src = "/PatentContact/index";
  }else  if(obj==11){
      document.getElementById("Fupageiframe").src="/searchResult/Mailboxbinding";
  }
}
</script>
</body>
</html>
