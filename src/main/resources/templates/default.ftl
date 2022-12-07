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
    <style>
        html {
            font-size: 62.5%;
        }

        body {
            font: normal 100% Arial, sans-serif;
            font-size: 14px;
            font-size: 1.4rem;
            width: 100%;
            height: 100%;
            overflow: hidden;
            background: rgb(246, 246, 246);
        }

        .scroll {

            position: relative;
            width: 100%;
            height: 27px;
            line-height: 25px;
            font-size: 15px;
            white-space: nowrap;
            overflow: hidden;
            background: rgb(255, 249, 239);
            text-align: center;
        }

        .scroll span {
            display: inline-block; /*inline样式不能使用动画*/
            animation: scroll 15s linear infinite;
        }

        .scroll span img {
            margin-top: 5px;
        }

        .scroll span:after {
            position: absolute;
            left: 100%;
            content: attr(data-text);
            margin-left: 4em;
        }

        @keyframes scroll {
            from {
                transform: translateX(0);
            }
            to {
                transform: translateX(calc(-100% - 4em)); /*总长再加上margin-left*/
            }
        }

        .wxts {
            color: rgb(246, 156, 34);
        }

        .IPRDB {
            color: blue;
        }

        .Contentbody {
            display: inline;
            width: 100%;
        }

        .westleft {
            margin-top: 10px;
            width: 13%;
            float: left;
        }

        .centerright {
            margin-right: 15px;
            margin-top: 10px;
            width: 82%;
            float: right;
            border: 1px solid;
        }

        .mini-layout-region-header .mini-tools, .mini-layout-proxy .mini-tools {
            top: -1px;
            right: 12px;
        }
        .mini-layout-region-header, .mini-layout-proxy{height: 32px;line-height: 32px;display: none;}
        .mini-outlookbar-border{background:#3b3e47;}
        .mini-outlookbar .mini-outlookbar-icon{margin-top: 10px;}
        .mini-outlookbar .mini-outlookbar-groupTitle{margin-top: 10px;margin-left: 5px}
        .mini-outlookbar-groupHeader .mini-tools{margin-top:8px}
        .mini-outlookbar-group{border-bottom: none}
        .mini-layout-region-body {height: 900px;}
        .ziitem{height: 100%;width: 100%;}
        .ziitem li{width: 100%;height: 40px;padding-top: 10px;padding-left: 50px;}
        .ziitem li:hover,.ziitem li:hover>a{background: rgb(110,128,156);color: white;}
        .ziitem li>a:hover{
            color: #FFF;
            background: #6e809c;
        }
        .ziitem li a{color: #b2b8be;}
    </style>
</head>
<body>

<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
<#--    <div title="湖南省知识产权综合服务平台发明专利监控平台"  headerStyle="height: 0px;border-bottom: #fff;" style="color:rgb(246, 156, 34); " region="north" height="27"  >-->
<#--        <!--上方滚动字幕&ndash;&gt;-->
<#--        <div class="scroll">-->
<#--   <span data-text="">-->
<#--  	 <img src="/img/lb.png" width="13px;"/>-->
<#--  	 <b class="wxts">${loginUser.userName}:</b>欢迎来到<b class="IPRDB">湖南省知识产权综合服务平台</b>发明专利监控平台-->
<#--   </span>-->
<#--            <div style="float:right;margin-right: 50px;padding-top: 3px">-->
<#--                <a href="#" title="修改密码" onclick="changePassword();"><img src="/img/pwd.png" width="20" />修改密码</a>-->
<#--                <a href="#" title="登出系统" onclick="exitSystem()"><img src="/img/de.png" width="20"/>退出系统</a>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->
    <div region="west" width="270" style="border:none; height: 100%" showheader="false" showspliticon="true" showCloseButton="false" showCollapseButton="false"
         showCollapseButton="false" oncollapse="onhide" showSplit="true" showProxy="false">
        <div id="outlookbar1" class="mini-outlookbar"  style="width:100%;height:100%;" activeIndex="0"  autoCollapse="true">
            <#if roots??>
                <#list roots as root>
                    <div title="${root.title}" iconCls="iconfont icon-zhuanli" bodyStyle="background:rgb(47,50,57)"
                         headerStyle="color:#b2b8be;font-size:15px;font-weight:normal;background: #3b3e47;height:50px;border-bottom:rgb(42,44,51,1);">
                        <ul class="ziitem">
                            <#list childMenus as childMenu>
                                <#if childMenu.pid==root.fid>
                                    <li> <a class="menuItem" href="#" url='${childMenu.url}'>${childMenu.title}</a></li>
                                </#if>
                            </#list>
                        </ul>
                    </div>
                </#list>
            </#if>
        </div>
    </div>


    <div region="center" id="bodycenter" style="height: 100%;">
        <div class="mini-tabs" id="mainTab" activeIndex="0" id="tab1" bodyStyle="height:100%;" style="width:100%;
        height:100%;background-color: #EAF2FF;"
             ontabload="closeLoading();" ontabdestroy="onDestroy">
            <div title="我的工作台"></div>
        </div>
    </div>
</div>
<div class="mini-window" title="修改登录密码" id="changePasswordWindow" style="width:600px;height:250px">
    <table class="layui-table" style="width:96%;height:100%" cellpadding="5px" id="changeForm">
        <tr>
            <td style="width:150px;text-align: center">原登录密码:</td>
            <td>
                <input class="mini-password" style="width:100%" name="oldPassword" required="true"/>
            </td>
        </tr>
        <tr>
            <td style="width:150px;text-align: center">新登录密码:</td>
            <td>
                <input class="mini-password" style="width:100%" name="newPassword" required="true"/>
            </td>
        </tr>
        <tr>
            <td style="width:150px;text-align: center">确认密码:</td>
            <td>
                <input class="mini-password" style="width:100%" name="confirmPassword" required="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">
                <button class="mini-button mini-button-primary" onclick="confirmChange();">确认修改</button>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="mini-button mini-button-danger" onclick="disposeChange();">关闭退出</button>
            </td>
        </tr>
    </table>
</div>

<script type="text/javascript">
    mini.parse();
    var tab1 = mini.get('tab1');
    var layer = null;
    layui.use(['layer'], function () {
        layer = layui.layer;
    });
    $(function () {
        document.getElementById("north").style.cssText="height: 27px;border-bottom: none;";
        document.getElementById("west").style.display="";
    })
    var tabHash = {};
    var titleHash = {};
    var mainTab=mini.get('mainTab');
    $('.menuItem').click(function(){
        var url=$(this).attr('url');
        var title=$(this).text();
        mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '正在加载页面...'
        });
        var pageName = getPageName(url);
        if (!pageName) pageName = title;
        var childTab = tabHash[pageName];
        if(!childTab){
            childTab=mainTab.addTab({
                title:title,
                url:url,
                showCloseButton:true,
                onload: function () {
                    closeLoading();
                }
            });
            tabHash[pageName] = childTab;
            titleHash[title] = pageName;
        } else {
            closeLoading();
            var bodyEl=mainTab.getTabIFrameEl(childTab);
            if(bodyEl)
            {
                var content=bodyEl.contentWindow;
                if(content){
                    var fun=content.refreshData;
                    if(fun)fun();
                }
            }
        }
        mainTab.activeTab(childTab);
    })

    function changeStyle(cls) {
        var outlookbar1 = mini.get("outlookbar1");
        outlookbar1.removeCls("mini-outlookbar-view2");
        outlookbar1.removeCls("mini-outlookbar-view3");
        outlookbar1.addCls(cls);
        outlookbar1.doLayout();
    }
    function closeLoading() {
        mini.unmask(document.body);
    }
    function getPageName(url) {
        if(!url)url='';
        var us = url.split('?');
        if(us.length>1){
            var vs = us[1].split('&');
            for (var i = 0; i < vs.length; i++) {
                var v = vs[i];
                if (v.toString().indexOf("MenuID") > -1) {
                    return v.split('=')[1];
                }
            }
        }
        return "";
    }
    function onDestroy(e) {
        var tab = e.tab;
        var title = tab.title;
        var pageName = titleHash[title];
        if (pageName) {
            var findTab = tabHash[pageName];
            if (findTab) {
                delete tabHash[pageName];
                delete titleHash[title];
            }
        }
    }


    var changeForm = null;
    var win1 = mini.get('#changePasswordWindow');

    function changePassword() {
        win1.show();
        changeForm = new mini.Form('#changeForm');
        changeForm.reset();
    }

    function disposeChange() {
        win1.hide();
    }


    function exitSystem(){
        mini.confirm('确认要退出系统，未保存的数据都将丢失，是否继续?','退出提示',function(result){
            if(result=='ok'){
                window.location.href='/logout';
                window.location.href='/login.html';
            }
        });
    }
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
                changeImage(0);
            }
        });
    }
    function changeImage(delta) {
        var imagep = $(".layui-layer-phimg").parent().parent();
        var image = $(".layui-layer-phimg").parent();
        var h = image.height();
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
    function doResize() {
        if (myBrowser() == "Chrome") {
            $('#tab1').css({"padding-top": "10px"});
            setTimeout(function () {
                $('#tab1').css({"padding-top": "10px"});
            }, 500);
            setTimeout(function () {
                $('#tab1').css({"padding-top": "10px"});
            }, 500);
        }
        tab1.doLayout();
    }
    function myBrowser() {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
        var isIE = userAgent.indexOf("compatible") > -1
            && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
        var isEdge = userAgent.indexOf("Edge") > -1; //判断是否IE的Edge浏览器
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
        var isSafari = userAgent.indexOf("Safari") > -1
            && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器
        var isChrome = userAgent.indexOf("Chrome") > -1
            && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器

        if (isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if (fIEVersion == 7) {
                return "IE7";
            } else if (fIEVersion == 8) {
                return "IE8";
            } else if (fIEVersion == 9) {
                return "IE9";
            } else if (fIEVersion == 10) {
                return "IE10";
            } else if (fIEVersion == 11) {
                return "IE11";
            } else {
                return "0";
            }//IE版本过低
            return "IE";
        }
        if (isOpera) {
            return "Opera";
        }
        if (isEdge) {
            return "Edge";
        }
        if (isFF) {
            return "FF";
        }
        if (isSafari) {
            return "Safari";
        }
        if (isChrome) {
            return "Chrome";
        }
    }
</script>
</body>
</html>
