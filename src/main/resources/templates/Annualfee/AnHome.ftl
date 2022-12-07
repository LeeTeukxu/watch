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
    <#--    <link rel="stylesheet" type="text/css" href="/css/left-side-menu.css">-->
    <link rel="stylesheet" type="text/css" href="/font/iconfont.css">
    <#--    <script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script>-->
    <#--    <script type="text/javascript" src="/js/left-side-menu.js"></script>-->
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

    <style>
        .drop{
            cursor: pointer;
        }
        .b1L {
            float: left;
            height: 260px;
            background: #fff;
            border-radius: 21px;
            margin: 5px;
            width: 100%;
            overflow: hidden;
            margin-top: 17px;
        }

        .box-title {
            font-family: 微軟正黑體;
            color: black;
            border-bottom: 1px solid #eee;
            font-weight: bold;
            padding: 10px;
        }

        .qustnav {
            overflow: hidden;
            margin-top: 37px;
        }

        .qustnav a {
            float: left;
            display: block;
            font-size: 15px;
            color: #666;
            text-decoration: none;
            width: 11%;
            text-align: center;
        }

        .qsnimg {
            width: 62px;
            height: 62px;
            background: #eaf2ff;
            margin: 10px auto;
            text-align: center;
            /* line-height: 63px; */
            padding-top: 22px;
            border-radius: 50%;
        }

        .qsnimg img {
            margin-top: -4px;
        }

        .b1R {
            float: left;
            height: 260px;
            background: #fff;
            border-radius: 20px;
            margin: 5px -9px;
            width: 99%;
            overflow: hidden;
            margin-top: 17px;
        }

        .z1 {
            background-size: 100%;
            width: 318px;
            /* margin: 0px auto; */
            margin-left: 10px;
            background: url(/appImages/line.png) no-repeat #fff 7px 10px;
            border-radius: 5px;
            background-size: 20px 100%;
            margin-top: 27px;
            color: #666;
        }

        .huanjielist {
            overflow: hidden;
            /* margin-top: -13px; */
            margin-bottom: 16px;
        }

        .huanjielistL {
            text-align: center;
            float: left;
            width: 11%;
            margin-top: 0;
        }

        .huanjielistR {
            float: left;
            width: 85%;
            background: url(/appImages/huanjiebotom.png) no-repeat;
            background-size: contain;
            /* height: 138px; */
            padding-left: 0;
            margin-top: -3px;
            /* background: #eee; */
            padding-bottom: 0;
            font-size: 16px;
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

        .top-link ul li a:hover {
            font-size: 18px;
            font-weight: bold;
            color: rgb(0, 78, 161);
        }
        .anniu{ padding: 10px 20px;
            border-radius: 20px;
            color: #009A67;
            font-weight: 500;}
        @media screen and  (max-width: 1560px) {
            .top-link {padding-left: 100px;overflow: hidden;float: left;margin-top: 14px;}
            .top-link li, .top-link li a {
                padding: 0 10px;
            }
        }

        @media screen and  (max-width: 1314px) {
            .top-link {padding-left: 100px;overflow: hidden;float: left;margin-top: 14px;}
            .top-link li, .top-link li a {
                padding: 0 7px;
            }
        }
    </style>
</head>
<body style="overflow-x: hidden; background: #f3f3f4;">
<div class="Meul_list">
    <div class="top-logo">
        <a href="javascript:void(0)">
            <img src="/img/logo.png" style="height: 35px;">
        </a>
    </div>
    <div class="top-login">
        <ul style="margin-top: -15px;">
            <#if HasLogin=0>
                <li class="login"><a id="toploginsrc" onclick="doLogin();">登录</a></li>
            </#if>
            <li><a href="#" class="anniu" onclick="" style="background-color: #f0f3e0;color: #009A67;">修改密码</a></li>
            <li><a href="#" class="anniu" onclick="exitSystem()" style="background-color: #fbbeb07a;color: #f75f5f;">退出</a></li>
        </ul>
    </div>
    <div class="top-link">
        <ul>
<#--            <#if roleName!='企业用户'>-->
                <li style="font-size:16px"><a url="/workBench" target="#" class="drop">系统首页</a></li>
                <#if HasLogin=1>
                    <#if roleName=='系统管理员'>
                        <li style="font-size:16px"><a url="/admin" target="#" class="drop">后台管理</a></li>
                    </#if>
                </#if>
                <#if roleName != '企业用户'>
                <li style="font-size:16px"><a url="/clientInfo/index?pageName=ClientBrowse&MenuID=105" target="#"
                                              class="drop">申请人管理</a></li>
                </#if>
                <li style="font-size:16px"><a url="/main/patent/index?pageName=PatentInfoBrowse&MenuID=91" target="#"
                                              class="drop">专利管理</a></li>
                <#if roleName != '企业用户'>
                <li style="font-size:16px"><a url="/searchResult/index" target="#" class="drop">专利检索</a></li>
                </#if>
                <li style="font-size:16px"><a url="/work/govFee/wait?pageName=GovFee&MenuID=164" target="#"
                                              class="drop">官费管理</a></li>
                <li style="font-size:16px"><a url="/WeChatOrder/orderlist?pageName=OrderList&MenuID=176" target="#"
                                              class="drop">订单管理</a></li>
                <li style="font-size:16px"><a url="/work/ticket/index?pageName=Ticket&MenuID=178" target="#" class="drop">电子票据管理</a></li>
<#--            </#if>-->
        </ul>
    </div>
</div>
<div class="mini-fit" style="width:100%;height:100%">
    <div class="mini-tabs" activeIndex="0" id="tab1" style="width:100%;height:99%;background-color: #EAF2FF;
        padding:0px" ontabload="closeLoading();" ontabdestroy="onDestroy" bodyStyle="padding:0px;background:#eef1f5;overflow:auto;">
        <div title="系统首页" url="/workBench"></div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var tabHash = {};
    var tab1 = mini.get('tab1');
    var layer = null;
    layui.use(['layer'], function () {
        layer = layui.layer;
    });
    $(function(){
        tabHash["系统首页"]=tab1.getActiveTab();
    })
    function onDestroy(e) {
        var tab = e.tab;
        var title = tab.title;
        var findTab = tabHash[title];
        if (findTab) {
            delete tabHash[title];
        }
    }

    function closeLoading() {
        mini.unmask(document.body);
    }

    function addParent(url, title) {
        if (url && title) {
            addChildTab(title, url);
        }
    }

    function addChildTab(title, url) {
        var childTab = tabHash[title];
        if (!childTab) {
            childTab = {
                url: url,
                title: title,
                showCloseButton: true,
                onload: function () {
                    closeLoading();
                },
                bodyStyle: "height:99%;padding-top:0px"
            };
            childTab = tab1.addTab(childTab);
            tabHash[title] = childTab;
        } else {
            closeLoading();
            var bodyEl = tab1.getTabIFrameEl(childTab);
            if (bodyEl) {
                var content = bodyEl.contentWindow;
                if (content) {
                    var fun = content.refreshData;
                    if (fun) {
                        try {
                            fun();
                        } catch (e) {

                        }
                    }
                }
            }
        }
        tab1.activeTab(childTab);
    }

    function exitSystem() {
        mini.confirm('您即将退出本系统，所有未保存的数据将会丢失，是否继续进行退出操作？', '退出系统', function (act) {
            if (act == 'ok') {
                $.post('/logout', {}, function (result) {
                    window.location = '/login.html';
                });
            }
        });
    }

    $(function () {
        $('a.drop').click(function () {
            var dom=$(this);
            var url=dom.attr("url");
            var title=dom.text();
            if(url && title){
                addChildTab(title,url);
            }
            return false;
        })
    })
    function showImages(result) {
        var gg = JSON.parse(result);
        layer.photos({
            photos: gg,
            area: '600px',
            shade: [1, '#000'],
            anim: 5,
            offset: 'auto',
            imageChange: function () {
                changeImage(0);
            },
            success: function () {
                changeImage(1);
            }
        });
    }

    function changeImage(delta) {
        var imagep = $(".layui-layer-phimg").parent().parent();
        var image = $(".layui-layer-phimg").parent();
        var h = image.height();
        if(h<50)h=400;
        var w = image.width();
        if (delta > 0) {
            if (h < (window.innerHeight + 500)) {
                h = h * 1.1;
                w = w * 1.1;
            }
        } else if (delta < 0) {
            if (h > 100) {
                h = h * 0.95;
                w = w * 0.95;
            }
        }
        else if(delta==0){
            if(h<window.innerHeight){
                h = h * 0.95;
                w = w * 0.95;
            }
        }
        imagep.css('margin-top', 0);
        imagep.css("top", (window.innerHeight - h) / 2);
        imagep.css("top", (window.innerHeight - h) / 2);
        image.height(h);
        image.width(w);
        imagep.height(h);
        imagep.width(w);
    }

    $(document).on("mousewheel DOMMouseScroll", ".layui-layer-phimg img", function (e) {
        var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) || // chrome & ie
            (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1)); // firefox
        changeImage(delta);
    });
</script>
</body>
</html>
