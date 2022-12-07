<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>专利综合信息</title>
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
        .info1top ul {
            margin-top: -23px;
            list-style: none;
            margin-left: 20px;
        }
        .info1top ul li {
            float: left;
            margin-left: 8%;
            height: 41px;
            margin-top: -11px;
            padding-top: 9px;
            border-radius: 5px;
        }
        .info1top ul li a {
            margin-top: 5px;
        }
        .info1top ul li a span {
            color: rgb(0, 159, 205);
            font-size: 15px;
        }
        .info1top ul li a h4 {
            display: inline;
            color: rgb(1, 160, 202);
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
<body style="overflow-y: auto;overflow-x: hidden; background: #f3f3f4;">
<div class="row" >
<div class="col-md-12">
    <div id="info1" style="height:55px;background:rgb(226,250,252);border-radius: 5px;border:1px solid rgb(190,226,240);">
        <div class="info1top" style="padding-left: 100px;">
            <h3 class="sqf" style="color: rgb(3,154,209);margin-left:-90px;margin-top:17px;font-weight:bold;">
                <img style="vertical-align: middle;" src="/appImages/jk.png">&nbsp;专利综合信息
            </h3>
            <ul>
                <li class="Jdlcli orig clicked" id="J0" style="background-color: rgba(247, 140, 24, 0.85);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(0)">
                        <span id="J1span">全部</span>
                        <h4 class="x0">2892</h4>
                    </a>
                </li>
                <li class="Jdlcli orig" id="J1" style="background-color: rgb(226, 250, 252);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(1)">
                        <span id="J2span">审中</span>
                        <h4 class="x1">807</h4>
                    </a>
                </li>
                <li class="Jdlcli orig" id="J2" style="background-color: rgb(226, 250, 252);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(2)">
                        <span id="J3span">有权</span>
                        <h4 class="x2">1581</h4>
                    </a>
                </li>
                <li class="Jdlcli orig" id="J3" style="background-color: rgb(226, 250, 252);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(3)">
                        <span id="J4span">年费滞纳恢复期</span>
                        <h4 class="x3">271</h4>
                    </a>
                </li>
                <li class="Jdlcli orig" id="J4" style="background-color: rgb(226, 250, 252);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(4)">
                        <span id="J5span">专利失效</span>
                        <h4 class="x4">32</h4>
                    </a>
                </li>
                <li class="Jdlcli orig" id="J5" style="background-color: rgb(226, 250, 252);">
                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="changeQuery(5)">
                        <span id="J5span">其他</span>
                        <h4 class="x5">201</h4>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="mini-toolbar">
        <table style="width:100%">
            <tbody><tr>
                <td style="width:95%">
                    <a class="mini-button mini-state-default mini-corner-all mini-button-info" href="javascript:void(0)" id="PatentInfoBrowse_Export" style=""><span class="mini-button-inner "><span class="mini-button-text">导出Excel</span></span></a>
                </td>
                <td style="white-space:nowrap;">
                    <span class="mini-buttonedit mini-combobox mini-popupedit Query_Field PatentInfoBrowse_Query" id="comField" style="border-width: 0px; width: 100px; padding: 0px;"><span class="mini-buttonedit-border mini-corner-all"><input type="text" class="mini-buttonedit-input" autocomplete="off" placeholder="" readonly="" id="comField$text"><span class="mini-buttonedit-buttons"><span class="mini-buttonedit-close mini-icon" name="close"></span><span title="" name="trigger" class="mini-buttonedit-button mini-buttonedit-trigger" onmouseover="l0o0(this,'mini-buttonedit-button-hover');" onmouseout="O1O0OO(this,'mini-buttonedit-button-hover');"><span class="mini-buttonedit-icon mini-icon mini-iconfont "></span></span></span></span><input name="" type="hidden" id="comField$value" value="All"></span>
                    <span class="mini-textbox Query_Field PatentInfoBrowse_Query" id="QueryText" style="border-width: 0px; width: 150px; padding: 0px;"><span class="mini-textbox-border mini-corner-all"><input type="text" class="mini-textbox-input" autocomplete="off" placeholder="" id="QueryText$text"></span><input type="hidden"></span>
                    <a class="mini-button mini-state-default mini-corner-all mini-button-success" href="javascript:void(0)" id="PatentInfoBrowse_Query" style=""><span class="mini-button-inner "><span class="mini-button-text">模糊搜索</span></span></a>
                    <a class="mini-button mini-state-default mini-corner-all mini-button-danger Query_Field" href="javascript:void(0)" id="PatentInfoBrowse_Reset" style=""><span class="mini-button-inner "><span class="mini-button-text">重置</span></span></a>
                    <a class="mini-button mini-state-default mini-corner-all" href="javascript:void(0)" id="PatentInfoBrowse_HighQuery" style=""><span class="mini-button-inner  mini-button-icon-text "><span class="mini-button-icon mini-icon mini-iconfont panel-expand" style=""></span><span class="mini-button-text">高级查询</span></span></a>

                </td>
            </tr>
            </tbody></table>
        <div id="p1" style="border:1px solid #909aa6;border-top:0;height:100px;padding:5px;display:none">
            <table style="width:100%;" id="highQueryForm">
                <tbody><tr>
                    <td style="width:6%;padding-left:10px;">申请日期：</td>
                    <td style="width:15%;">
                        <span class="mini-buttonedit mini-datepicker mini-popupedit" data-oper="GE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-buttonedit-border mini-corner-all"><input type="text" class="mini-buttonedit-input" autocomplete="off" placeholder=""><span class="mini-buttonedit-buttons"><span class="mini-buttonedit-close mini-icon" name="close"></span><span title="" name="trigger" class="mini-buttonedit-button mini-buttonedit-trigger" onmouseover="l0o0(this,'mini-buttonedit-button-hover');" onmouseout="O1O0OO(this,'mini-buttonedit-button-hover');"><span class="mini-buttonedit-icon mini-icon mini-iconfont "></span></span></span></span><input name="SHENQINGR" type="hidden"></span>
                    </td>
                    <td style="width:6%;padding-left:10px;">到：</td>
                    <td style="width:15%;">
                        <span class="mini-buttonedit mini-datepicker mini-popupedit" data-oper="LE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-buttonedit-border mini-corner-all"><input type="text" class="mini-buttonedit-input" autocomplete="off" placeholder=""><span class="mini-buttonedit-buttons"><span class="mini-buttonedit-close mini-icon" name="close"></span><span title="" name="trigger" class="mini-buttonedit-button mini-buttonedit-trigger" onmouseover="l0o0(this,'mini-buttonedit-button-hover');" onmouseout="O1O0OO(this,'mini-buttonedit-button-hover');"><span class="mini-buttonedit-icon mini-icon mini-iconfont "></span></span></span></span><input name="SHENQINGR" type="hidden"></span>
                    </td>
                    <td style="width:6%;padding-left:10px;">专利名称：</td>
                    <td style="width:15%;">
                        <span class="mini-textbox" data-oper="LIKE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-textbox-border mini-corner-all"><input type="text" class="mini-textbox-input" autocomplete="off" placeholder="" name="FAMINGMC"></span><input type="hidden"></span>
                    </td>
                    <td style="width:6%;padding-left:10px;">专利号：</td>
                    <td style="width:15%;">
                        <span class="mini-textbox" data-oper="LIKE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-textbox-border mini-corner-all"><input type="text" class="mini-textbox-input" autocomplete="off" placeholder="" name="SHENQINGH"></span><input type="hidden"></span>
                    </td>
                </tr>
                <tr>
                    <td style="width:6%;padding-left:10px;">专利类型：</td>
                    <td style="width:15%;"><span class="mini-buttonedit mini-combobox mini-popupedit" data-oper="EQ" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-buttonedit-border mini-corner-all"><input type="text" class="mini-buttonedit-input" autocomplete="off" placeholder="" readonly=""><span class="mini-buttonedit-buttons"><span class="mini-buttonedit-close mini-icon" name="close"></span><span title="" name="trigger" class="mini-buttonedit-button mini-buttonedit-trigger" onmouseover="l0o0(this,'mini-buttonedit-button-hover');" onmouseout="O1O0OO(this,'mini-buttonedit-button-hover');"><span class="mini-buttonedit-icon mini-icon mini-iconfont "></span></span></span></span><input name="SHENQINGLX" type="hidden" value=""></span></td>
                    <td style="width:6%;padding-left:10px;">专利状态：</td>
                    <td style="width:15%;">
                        <span class="mini-buttonedit mini-combobox mini-popupedit" data-oper="EQ" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-buttonedit-border mini-corner-all"><input type="text" class="mini-buttonedit-input" autocomplete="off" placeholder="" readonly=""><span class="mini-buttonedit-buttons"><span class="mini-buttonedit-close mini-icon" name="close"></span><span title="" name="trigger" class="mini-buttonedit-button mini-buttonedit-trigger" onmouseover="l0o0(this,'mini-buttonedit-button-hover');" onmouseout="O1O0OO(this,'mini-buttonedit-button-hover');"><span class="mini-buttonedit-icon mini-icon mini-iconfont "></span></span></span></span><input name="ANJIANYWZT" type="hidden" value=""></span>
                    </td>
                    <td style="width:6%;padding-left:10px;">专利申请人：</td>
                    <td style="width:15%;"><span class="mini-textbox" data-oper="LIKE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-textbox-border mini-corner-all"><input type="text" class="mini-textbox-input" autocomplete="off" placeholder="" name="SHENQINGRXM"></span><input type="hidden"></span></td>
                    <td style="width:6%;" title="归属客户/销售维护人/代理责任人/流程责任人">
                        内部编号：
                    </td>
                    <td style="width:15%;"><span class="mini-textbox" data-oper="LIKE" style="border-width: 0px; width: 100%; padding: 0px;"><span class="mini-textbox-border mini-corner-all"><input type="text" class="mini-textbox-input" autocomplete="off" placeholder="" name="NEIBUBH"></span><input type="hidden"></span></td>
                </tr>
                <tr>
                    <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                        <a class="mini-button mini-state-default mini-corner-all mini-button-success" href="javascript:void(0)" style="width: 120px;"><span class="mini-button-inner "><span class="mini-button-text">搜索</span></span></a>
                        <a class="mini-button mini-state-default mini-corner-all mini-button-danger" href="javascript:void(0)" style="margin-left: 30px; width: 120px;"><span class="mini-button-inner "><span class="mini-button-text">收起</span></span></a>
                    </td>
                </tr>
                </tbody></table>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:700px;"
         allowresize="true" url="" multiselect="true"
         pagesize="5" sizelist="[5,10,20,50,100,150,200]" sortfield="CreateTime" sortorder="desc"
         autoload="true" >
        <div property="columns">
            <div type="indexcolumn" headerAlign="center"></div>
            <div field="SHENQINGH" width="50" headerAlign="center" align="center" allowSort="true">备注信息</div>
            <div field="FAMINGMC" width="50" headerAlign="center" align="center" allowSort="true">专利申请人</div>
            <div field="SHENQINGR" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">专利申请号</div>
            <div field="CostName" width="50" headerAlign="center" align="center" allowSort="true">专利类型</div>
            <div field="Amount" width="50" headerAlign="center" align="center" allowSort="true">专利名称</div>
            <div field="LimitDate" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日期</div>
            <div field="famingr" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">专利状态</div>
            <div field="CostName" width="50" headerAlign="center" align="center" allowSort="true">发明人</div>
            <div field="Amount" width="50" headerAlign="center" align="center" allowSort="true">代理机构</div>
            <div field="CostName" width="50" headerAlign="center" align="center" allowSort="true">签名代理师</div>
            <div field="Amount" width="50" headerAlign="center" align="center" allowSort="true">更新时间</div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
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
