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

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <style>
        .layout-body {
            min-width: 1px;
            min-height: 1px;
            background-color: #ffffff;
            color: #404040;
            font-size: 14px;
        }
        .S_duibi_title {
            background: url(/img/ico_duibi1.gif) no-repeat left center;
            text-indent: 15px;
            font-size: 14px;
            padding: 10px 0;
        }
        .S_duibi_msg {
            width: 100%;
            border-collapse: collapse;
        }
        .S_duibi_msg td {
            padding: 7px 5px;
        }
        .nowarp{
            font-family: fantasy;
            font-weight: bold;
        }
        .nrsize{color: #272525;font-family: initial;}
    </style>

</head>
<body style="overflow: hidden;background: white;">
<div class="panel layout-panel layout-panel-west panel-htop easyui-fluid" style="width: 100%; left: 0px; top: 39px;">
    <div class="dbmsgkk1 panel-body panel-body-noheader layout-body" data-options="region:'west',split:false" style="padding: 10px; width: 50%;height: 1000px;border-right: 10px solid #eaeaea;margin-top: -6px;" title="">
        <input id="ipinfoappno" type="hidden" value="CN202111523521.1">
        <h1 class="S_duibi_title">著录项</h1>
        <table class="S_duibi_msg" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td colspan="4" class="blod">
               ${datas[0].famingmc!}
                </td>
            </tr>
        <tr>
                <td width="15%" class="nowarp bold">申请号</td>
                <td width="50%" class="nrsize"> ${datas[0].shenqingh!}</td>
                <td width="15%" class="nowarp bold">申请日</td>
                <td width="50%" class="nrsize"> ${datas[0].shenqingr!}</td>
            </tr>
      <tr>
                <td class="nowarp bold">公开（公告）号</td>
                <td class="nrsize">${datas[0].gonkaihao!}</td>
                <td class="nowarp bold">公开（公告）日</td>
                <td class="nrsize">${datas[0].gonkair!}</td>
            </tr>
            <tr>
                <td class="nowarp bold">申请（专利权）人</td>
                <td class="nrsize"></td>
                <td class="nowarp bold">发明人</td>
                <td class="nrsize">${datas[0].famingrxm!}</td>
            </tr>
            <tr>
                <td class="nowarp bold">主分类号</td>
                <td class="nrsize">T-18</td>
                <td class="nowarp bold">分类号</td>
                <td class="nrsize">T-18</td>
            </tr>
            <tr>
                <td class="nowarp bold">地址</td>
                <td class="nrsize">${datas[0].priaddress!}</td>
                <td class="nowarp bold">国省代码</td>
                <td class="nrsize"></td>
            </tr>
            <tr>
                <td class="nowarp bold">代理机构</td>
                <td class="nrsize">${datas[0].dailijgmc!}</td>
                <td class="nowarp bold">代理人</td>
                <td class="nrsize">${datas[0].dailirxm!}</td>
            </tr>
            </tbody></table>
        <h1  class="S_duibi_title nowarp">摘要</h1>
        <div class="nrsize" style="width:100%;line-height:200%;text-indent:50px;">
           ${datas[0].memo!}
        </div>
    </div>
    <div class="dbmsgkk1 panel-body panel-body-noheader layout-body" data-options="region:'west',split:false" style="padding: 10px; width: 50%;height: 1000px;margin-top: -1000px;float: right;" title="">
        <input id="ipinfoappno" type="hidden" value="CN202111523521.1">
        <h1 class="S_duibi_title">著录项</h1>
        <#if datas?? && (datas?size >= 2) >
            <table class="S_duibi_msg" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td colspan="4" class="blod">
                        ${datas[1].famingmc!}
                    </td>
                </tr>
                <tr>
                    <td width="15%" class="nowarp bold">申请号</td>
                    <td width="50%" class="nrsize"> ${datas[1].shenqingh!}</td>
                    <td width="15%" class="nowarp bold">申请日</td>
                    <td width="50%" class="nrsize"> ${datas[1].shenqingr!}</td>
                </tr>
                <tr>
                    <td class="nowarp bold">公开（公告）号</td>
                    <td class="nrsize">${datas[1].gonkaihao!}</td>
                    <td class="nowarp bold">公开（公告）日</td>
                    <td class="nrsize">${datas[1].gonkair!}</td>
                </tr>
                <tr>
                    <td class="nowarp bold">申请（专利权）人</td>
                    <td class="nrsize"></td>
                    <td class="nowarp bold">发明人</td>
                    <td class="nrsize">${datas[1].famingrxm!}</td>
                </tr>
                <tr>
                    <td class="nowarp bold">主分类号</td>
                    <td class="nrsize">T-18</td>
                    <td class="nowarp bold">分类号</td>
                    <td class="nrsize">T-18</td>
                </tr>
                <tr>
                    <td class="nowarp bold">地址</td>
                    <td class="nrsize">${datas[1].priaddress!}</td>
                    <td class="nowarp bold">国省代码</td>
                    <td class="nrsize"></td>
                </tr>
                <tr>
                    <td class="nowarp bold">代理机构</td>
                    <td class="nrsize">${datas[1].dailijgmc!}</td>
                    <td class="nowarp bold">代理人</td>
                    <td class="nrsize">${datas[1].dailirxm!}</td>
                </tr>
                </tbody>
            </table>
            <h1 class="S_duibi_title nowarp">摘要</h1>
            <div class="nrsize" style="width:100%;line-height:200%;text-indent:50px;">
                ${datas[1].memo!}
            </div>
        <#else>
        <h1 style="text-align: center;color: black">查询的申请号未搜索到！</h1>
        </#if>
    </div>
</div>
<script>

</script>
</body>
</html>
