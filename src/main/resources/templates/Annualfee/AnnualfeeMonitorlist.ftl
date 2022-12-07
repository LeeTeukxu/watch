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

    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
    <script type="text/javascript" src="/js/common/complexExcelExport.js?v=${version}"></script>

    <style>
        .pagetitle{border-bottom: solid 1px #dbdee5;height: 44px;width: 99%;margin: 0px auto;}
        .pagetitle p{margin-top: 19px;margin-left: 10px;font-family: initial;}
        .pagetitle span{border: 3px solid #00a0e9;height: 10px;width: 10px;}
        .mini-textbox{overflow: visible;height: 26px;width: 5%;}
        .mini-buttonedit {
            overflow: visible;
            height: 26px;
            width: 6%;
        }
     .btn{background: #3398dc;height: 28px;line-height: 10px;border: none;}
     .bz{margin-top: 13px;font-size: 12px;color: black;font-weight: bold;}
    </style>
</head>
<body style="overflow: hidden;background: white;">
<div class="row">
    <div class="col-md-11">
        <div class="clumsbg" style="overflow-x: hidden;overflow-y: auto;height: 855px;background: #f3f3f4;margin-left: -2px;">
            <div class="pagetitle">
                <p><span></span>&nbsp; 当前 - 年费监控列表</p>
            </div>
        </div>
    </div>
    <div class="col-md-11">
        <div style="float: right;width: 97%;margin-top: -785px;color: black">
            <form id="highQueryForm">
                名称<input class="mini-textbox" name="FAMINGMC" class="mini-textbox" data-oper="LIKE">
                申请号<input class="mini-textbox" name="SHENQINGH" class="mini-textbox" data-oper="LIKE">
                专利权人<input class="mini-textbox" name="SHENQINGRXM" class="mini-textbox" data-oper="LIKE">
                申请日<input class="mini-datepicker" datatype="date" dateformat="yyyy-MM-dd"
                          data-oper="GE" name="SHENQINGR"><input class="mini-datepicker" datatype="date" dateformat="yyyy-MM-dd"
                                                                                    data-oper="LE" name="SHENQINGR">
                缴费截止日<input class="mini-datepicker" datatype="date" dateformat="yyyy-MM-dd"
                            data-oper="GE" name="LimitDate"><input class="mini-datepicker" datatype="date" dateformat="yyyy-MM-dd"
                                                           data-oper="LE" name="LimitDate">
                年费状态<input class="mini-combobox">
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="doHightSearch()">检索</button>
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="doExport()">结果导出</button>
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="Annual_Add()">添加监控专利</button>
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="doExportYear()">导出代缴费清单</button>
                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="Annual_Remove()">勾选删除专利</button>
            </form>
            <div class="bz"><span>共 3 件有效专利，已监控 2 件，需缴年费 690 元。可监控 5 条，已使用 3 条，剩余 2 条可使用。</span></div>
        </div>
    </div>
    <div class="col-md-11">
    <div style="float: left;margin-top: -716px;margin-left: 1.9%;width: 96%;">
        <div id="datagrid1" class="mini-datagrid" style="width:98%;height: 670px;margin-left: 17px;overflow:auto hidden;"
             allowresize="true" url="/Annual/getMonitor" multiselect="true"
             pagesize="20" sizelist="[5,10,20,50,100,150,200]" sortfield="CreateTime" sortorder="desc"
             autoload="true" onDrawCell="onDraw">
            <div property="columns">
                <div type="checkcolumn"></div>
                <div type="indexcolumn" headerAlign="center">序号</div>
                <div field="SHENQINGH" width="50" headerAlign="center" align="center" allowSort="true">申请号</div>
                <div field="FAMINGMC" width="50" headerAlign="center" align="center" allowSort="true">名称</div>
                <div field="SHENQINGR" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                <div field="SHENQINGRXM" width="50" headerAlign="center" align="center" allowSort="true">专利权人</div>
                <div field="FAMINGRXM" width="50" headerAlign="center" align="center" allowSort="true">发明人</div>
                <div field="CostName" width="50" headerAlign="center" align="center" allowSort="true">费用名称</div>
                <div field="Amount" width="50" headerAlign="center" align="center" allowSort="true">应缴纳年费</div>
                <div field="LateFee" width="50" headerAlign="center" align="center" allowSort="true">滞纳金</div>
                <div field="LimitDate" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">官方缴费截止日</div>
                <div field="PayState" width="50" headerAlign="center" align="center" allowSort="true">年费状态</div>
                <div field="GovCreateTime" width="50" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">系统监控日</div>
                <div field="Operation" width="50" headerAlign="center" align="center" allowSort="true">操作</div>
            </div>
        </div>
    </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid = mini.get('datagrid1');
    function Annual_Add() {
        mini.open({
            url:'/searchResult/Addpatent',
            width:'60%',
            height:880,
            title:'新增专利监控',
            showModal:true,
            ondestroy:function(){
                grid.reload();
            }
        });
    }

    function Annual_Remove() {
        var rows = grid.getSelecteds();
        var shenqinghs = [];
        for (var i = 0; i < rows.length; i++) {
            shenqinghs.push(rows[i]["SHENQINGH"]);
        }
        if (shenqinghs.length == 0) {
            mini.alert('请选择要删除的记录!');
            return;
        }
        mini.confirm('确认要删除已监控的专利数据?', '删除提示', function (act) {
            if (act === 'ok') {
                var url="/Annual/remove";
                $.ajax({
                    contentType:'application/json',
                    method:'post',
                    url:url,
                    data:mini.encode(shenqinghs),
                    success:function (r) {
                        if (r['success']) {
                            mini.alert("删除成功！",'删除专利监控',function () {
                                grid.reload();
                            });
                        }
                        else mini.alert("删除失败！");
                    },
                    failure:function (error) {
                        alert(error);
                    }
                })
            }
        });
    }

    function doHightSearch() {
        var arg = {};
        var form = new mini.Form('#highQueryForm');
        var fields = form.getFields();
        var result = [];
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var val = field.getValue();
            if (val != null && val != undefined) {
                if (val != '') {
                    var obj = {
                        field: field.getName(),
                        value: field.getValue(),
                        oper: field.attributes["data-oper"]
                    };
                    result.push(obj);
                }
            }
        }
        arg["High"] = mini.encode(result);
        myArg=arg;
        grid.load(arg);
    }

    function doExport() {
        var excel = new excelData(grid);
        excel.addEvent('beforeGetData', function (grid, rows) {
            return grid.getSelecteds();
        })
        excel.export("年费监控记录.xls");
    }

    function onDraw(e) {
        var field = e.field;
        var record = e.record;
        if (field == "Operation"){
            var shenqingh =record["SHENQINGH"];
            e.cellHtml = '<a href="javascript:void(0)" onclick="SingleRemove(' + "'" + shenqingh + "'" +')">删除</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="payxx(' + "'" + shenqingh + "'" +')">缴费信息</a>'
        }
    }
    function payxx(shenqingh) {
        mini.open({
        url:"/searchResult/Paymentinformation?shenqingh=" + shenqingh,
        title:"缴费信息",
        width:"40%",
        height:"70%"
        })
    }

    function SingleRemove(shenqingh) {
        var shenqinghs = [shenqingh];
        mini.confirm('确认要删除已监控的专利数据?', '删除提示', function (act) {
            if (act === 'ok') {
                var url="/Annual/remove";
                $.ajax({
                    contentType:'application/json',
                    method:'post',
                    url:url,
                    data:mini.encode(shenqinghs),
                    success:function (r) {
                        if (r['success']) {
                            mini.alert("删除成功！",'删除专利监控',function () {
                                grid.reload();
                            });
                        }
                        else mini.alert("删除失败！");
                    },
                    failure:function (error) {
                        alert(error);
                    }
                })
            }
        });
    }
    function doExportYear() {
        var excel = new complexExcelData(grid);
        excel.setSheetName("Sheet0");
        excel.export('OneYear', '专利年费代缴费清单.xls');
    }
</script>
</body>
</html>
