<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
    <script type="text/javascript" src="/js/clipboard.min.js"></script>
    <script type="text/javascript">
        var users = {};
        var users2 = {};
        for (i in users) {
            users2[users[i]] = i;
        }
        var querFields = [
            {id: 'All', text: '全部属性'},
            {id: 'SHENQINGRXM', text: '专利申请人'},
            {id: 'SHENQINGH', text: '专利申请号'},
            {id: 'FAMINGMC', text: '发明名称'},
            {id: 'LAWSTATUS', text: '专利状态'},
            {id: 'SHENQINGLX', text: '专利类型'},
            {id: 'FAMINGRXM', text: '发明人'},
            {id: 'DAILIJGMC', text: '代理机构'},
            {id: 'CLIENTNAME', text: '所属客户'},
            {id: 'ADDRESS', text: '申请人地址'}
        ];
        var types = [{id: '发明公布', text: '发明公布'}, {id: '实用新型', text: '实用新型'}, {id: '外观设计', text: '外观设计'}];
        var ss = "";
    </script>
    <style type="text/css">
        .info1top ul {
            margin-top: -23px;
            list-style: none;
            margin-left: 20px;
        }

        .info1top ul li {
            float: left;
            margin-left: 8%;
            height: 41px;
            margin-top: -24px;
            padding-top: 9px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1593px) {
            .info1top ul li {
                margin-left: 8%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info1top ul li {
                margin-left: 8%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info1top ul li {
                margin-left: 8%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info1top ul li {
                margin-left: 8%;
            }
        }

        .info1top ul li:hover {
            background-color: rgb(203, 238, 242)
        }

        .clicked {
            background-color: rgba(241, 112, 46, 0.84);
        }

        .unclick {
            background-color: rgb(203, 238, 242)
        }

        .info1top ul li a {
            margin-top: 5px;
        }

        .info1top ul li a span {
            color: rgb(0, 159, 205);
            font-size: 15px
        }

        .info1top ul li a h4 {
            display: inline;
            color: rgb(1, 160, 202)
        }

        .info1bottom ul {
            margin-top: -23px;
            list-style: none;
            margin-left: 20px;
        }

        .info1bottom ul li {
            float: left;
            margin-left: 10%;
            height: 41px;
            margin-top: -9px;
            padding-top: 9px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1593px) {
            .info1bottom ul li {
                margin-left: 7%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info1bottom ul li {
                margin-left: 3.5%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info1bottom ul li {
                margin-left: 2%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info1bottom ul li {
                margin-left: 1.2%;
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
            font-size: 15px
        }

        .info1bottom ul li a h4 {
            display: inline;
            color: rgb(1, 160, 202)
        }

        .info3top ul {
            margin-top: -23px;
            list-style: none;
            margin-left: 20px;
        }

        .info3top ul li {
            float: left;
            margin-left: 10%;
            height: 41px;
            margin-top: -9px;
            padding-top: 9px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1593px) {
            .info3top ul li {
                margin-left: 7%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info3top ul li {
                margin-left: 3.5%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info3top ul li {
                margin-left: 2%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info3top ul li {
                margin-left: 1.2%;
            }
        }

        .info3top ul li:hover {
            background-color: rgb(214, 212, 251)
        }

        .unclick1 {
            background-color: rgb(214, 212, 251)
        }

        .info3top ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info3top ul li a h4 {
            display: inline;
            color: rgb(52, 101, 232)
        }

        .info3bottom ul {
            margin-top: -23px;
            list-style: none;
            margin-left: 20px;
        }

        .info3bottom ul li {
            float: left;
            margin-left: 10%;
            height: 41px;
            margin-top: -9px;
            padding-top: 9px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1593px) {
            .info3bottom ul li {
                margin-left: 7%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info3bottom ul li {
                margin-left: 3.5%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info3bottom ul li {
                margin-left: 2%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info3bottom ul li {
                margin-left: 1.2%;
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

        @media screen and (max-width: 1170px) {
            .info1bottom ul li {
                margin-left: 2%;
            }

            .info2bottom ul li {
                margin-left: 1%;
            }

            .info3bottom ul li {
                margin-left: 0.5%;
            }
        }

        .sqf * {
            display: inline-block;
            vertical-align: middle;
        }

        .mini-panel-header {
            background: rgba(27, 26, 26, 0.85);
            color: rgb(255, 255, 255);
            height: 34px;
            padding: 5px;
        }

    </style>
    <div class="mini-layout" style="width:100%;height:100%">
        <div region="north" height="68px" bodystyle="border:none;overflow: hidden;" splitSize="0" showHeader="false">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <div class="mini-clearfix ">
                            <div class="mini-col-12">
                                <div id="info1"
                                     style="height:55px;background:rgb(226,250,252);border-radius: 5px;border:1px solid rgb(190,226,240);">
                                    <div class="info1top" style="padding-left: 100px;">
                                        <h3 class="sqf"
                                            style="color: rgb(3,154,209);margin-left:-90px;margin-top:17px;font-weight:bold;">
                                            <img style="vertical-align: middle;" src="/appImages/jk.png">&nbsp;专利综合信息
                                        </h3>
                                        <ul>
                                            <li class="Jdlcli orig" id="J0">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(0)">
                                                    <span id="J1span">全部</span>
                                                    <h4 class="x0">0</h4>
                                                </a>
                                            </li>
                                            <li class="Jdlcli orig" id="J1">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(1)">
                                                    <span id="J2span">审中</span>
                                                    <h4 class="x1">0</h4>
                                                </a>
                                            </li>
                                            <li class="Jdlcli orig" id="J2">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(2)">
                                                    <span id="J3span">有权</span>
                                                    <h4 class="x2">0</h4>
                                                </a>
                                            </li>
                                            <li class="Jdlcli orig" id="J3">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(3)">
                                                    <span id="J4span">年费滞纳恢复期</span>
                                                    <h4 class="x3">0</h4>
                                                </a>
                                            </li>
                                            <li class="Jdlcli orig" id="J4">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(4)">
                                                    <span id="J5span">专利失效</span>
                                                    <h4 class="x4">0</h4>
                                                </a>
                                            </li>
                                            <li class="Jdlcli orig" id="J5">
                                                <a style="text-decoration:none" target="_self" href="javascript:void(0)"
                                                   onclick="changeQuery(5)">
                                                    <span id="J5span">其他</span>
                                                    <h4 class="x5">0</h4>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </tr>
                </table>
            </div>
        </div>
        <div region="center">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            <a class="mini-button mini-button-info" onclick="doExport" id="PatentInfoBrowse_Export">导出Excel</a>
                            <a class="mini-button mini-button-info" onclick="updtinformation"
                               id="PatentInfoBrowse_Edit">修改信息</a>
                            <span class="separator"></span>
                            <a class="mini-button mini-button-primary" onclick="doTask" id="PatentInfoBrowse_doTask">采集专利信息</a>
                            <a class="mini-button mini-button-success" onclick="showTaskMain"
                               id="PatentInfoBrowse_showTaskMain">查看采集进度</a>
                        </td>
                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field PatentInfoBrowse_Query" id="comField"
                                 style="width:100px" data="querFields" value="All" id="Field"></div>
                            <input class="mini-textbox Query_Field PatentInfoBrowse_Query" style="width:150px"
                                   id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();" id="PatentInfoBrowse_Query">模糊搜索</a>
                            <a class="mini-button mini-button-danger Query_Field" id="PatentInfoBrowse_Reset"
                               onclick="reset()">重置</a>
                            <a class="mini-button" id="PatentInfoBrowse_HighQuery" iconCls="panel-expand"
                               onclick="expand">高级查询</a>

                        </td>
                    </tr>
                </table>
                <div id="p1" style="border:1px solid #909aa6;border-top:0;height:140px;padding:5px;display:none">
                    <table style="width:100%;" id="highQueryForm">
                        <tr>
                            <td style="width:6%;padding-left:10px;">申请日期：</td>
                            <td style="width:15%;">
                                <input name="SHENQINGR" class="mini-datepicker" datatype="date" dateformat="yyyy-MM-dd"
                                       data-oper="GE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">到：</td>
                            <td style="width:15%;">
                                <input name="SHENQINGR" data-oper="LE" class="mini-datepicker" datatype="date"
                                       dateformat="yyyy-MM-dd" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">专利名称：</td>
                            <td style="width:15%;">
                                <input name="FAMINGMC" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">专利号：</td>
                            <td style="width:15%;">
                                <input name="SHENQINGH" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">专利类型：</td>
                            <td style="width:15%;"><input name="SHENQINGLX" class="mini-combobox" data-oper="EQ"
                                                          style="width:100%" data="types"/></td>
                            <td style="width:6%;padding-left:10px;">专利状态：</td>
                            <td style="width:15%;">
                                <input class="mini-combobox" name="LAWSTATUS"
                                       data-oper="EQ" style="width:100%" url="/main/patent/getLawStatus"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">专利申请人：</td>
                            <td style="width:15%;"><input name="SHENQINGRXM" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"
                                /></td>
                            <td style="width:6%;">
                                发明人地址：
                            </td>
                            <td style="width:15%;"><input name="ADDRESS" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"/></td>
                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">省：</td>
                            <td style="width:15%;">
                                <input name="ProvinceID" class="mini-treeselect" idField="id" parentField="pid"
                                       textField="name" multiSelect="false"
                                       url="/area/getProvinces" allowInput="true" valueFromSelect="true"
                                       showFolderCheckBox="true" onvaluechanged="provinceChanged"
                                       multiSelect="false" style="width:100%" data-oper="EQ" value="430000"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">市：</td>
                            <td style="width:15%;">
                                <input name="CityID" class="mini-treeselect" idField="id" parentField="pid"
                                       textField="name" multiSelect="true" autoload="true"
                                       url="/area/getCitys?IDS=430000" allowInput="true" valueFromSelect="true"
                                       showFolderCheckBox="false" autoload="false" onvaluechanged="citychanged"
                                       multiSelect="false" style="width:100%" data-oper="EQ"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">县：</td>
                            <td style="width:15%;">
                                <input name="CountyID" class="mini-treeselect" idField="id" parentField="pid"
                                       textField="name" multiSelect="true" autoload="false"
                                       url="/area/getCountys" allowInput="true" valueFromSelect="true"
                                       showFolderCheckBox="false"
                                       multiSelect="false" style="width:100%" data-oper="EQ"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">客户名称：</td>
                            <td style="width:15%;">
                                <input name="ClientName" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                                <a class="mini-button mini-button-success PatentInfoBrowse_Query"
                                   onclick="doHightSearch"
                                   style="width:120px">搜索</a>
                                <a class="mini-button mini-button-danger PatentInfoBrowse_Query" onclick="expand"
                                   style="margin-left:30px;width:120px">收起</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-fit" id="fitt">
                <div class="mini-datagrid" id="datagrid1" style="width:100%;height:100%"
                     url="/main/patent/getData"
                     allowCellEdit="true" allowCellSelect="true" sizelist="[5,10,20,50,100,200]"
                     onrowclick="onRowClick" showPager="true" frozenStartColumn="0" frozenEndColumn="8"
                     oncellbeginedit="beforeEdit" oncellcommitedit="afterEdit"
                     autoload="true" multiSelect="true" pageSize="20"
                     sortField="SHENQINGR" sortOrder="desc" ondrawcell="onRenderLX" onload="afterload"
                     oncelldblclick="cellDblClick">
                    <div property="columns">
                        <div type="indexcolumn" style="width:80px"></div>
                        <div type="checkcolumn"></div>
                        <div width="60" field="DE" align="center" headerAlign="center"></div>
                        <div field="Action" width="60" headerAlign="center" align="center">备注</div>
                        <div field="SHENQINGRXM" width="200" headerAlign="center" allowsort="true" align="center">
                            专利申请人
                        </div>
                        <div field="SHENQINGH" width="130" headerAlign="center" allowsort="true"
                             align="center">专利申请号
                        </div>
                        <div field="SHENQINGLX" width="70" headerAlign="center" allowsort="true"
                             align="center">专利类型
                        </div>
                        <div field="FAMINGMC" width="200" headeralign="center" allowsort="true"
                             renderer="onZhanlihaoZhuangtai" align="center">专利名称
                        </div>
                        <div field="SHENQINGR" width="100" headerAlign="center" Align="center" dataType="date"
                             dateFormat="yyyy-MM-dd" allowsort="true">申请日期
                        </div>
                        <div field="LAWSTATUS" width="100" headerAlign="center" allowsort="true" align="center">专利状态
                        </div>
                        <div field="FAMINGRXM" width="200" headerAlign="center" allowsort="true" align="center">发明人
                        </div>
                        <#--                        <div field="GONKAIHAO" width="80" headerAlign="center" allowsort="true">公开公告号</div>-->
                        <#--                        <div field="GONKAIR" width="100" headerAlign="center" allowsort="true" dataType="date"-->
                        <#--                             dateFormat="yyyy-MM-dd"  align="center">公开公告日</div>-->
                        <div field="ProvinceName" headerAlign="center" allowsort="true" align="center">省</div>
                        <div field="CityName" headerAlign="center" allowsort="true" align="center">市</div>
                        <div field="CountyName" headerAlign="center" allowsort="true" align="center">县(区)</div>
                        <div field="DAILIJGMC" width="200" headerAlign="center" align="center"
                             allowsort="true">代理机构
                        </div>
                        <div field="DAILIRXM" width="80" headerAlign="center" align="center" allowsort="true">代理人</div>
                        <div field="ClientName" width="150" headerAlign="center" align="center">客户名称</div>

                        <div field="ADDRESS" width="200" headerAlign="center" align="center">申请人地址</div>
                        <div field="LASTUPDATETIME" width="100" headerAlign="center" Align="center" dataType="date"
                             dateFormat="yyyy-MM-dd" allowsort="true">更新日期
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form method="post" action="/work/patentInfo/exportExcel" style="display:none" id="exportExcelForm">
        <input type="hidden" name="Data" id="exportExcelData"/>
    </form>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get('#datagrid1');
        var txtQuery = mini.get('#QueryText');
        var comField = mini.get('#comField');
        var cmdQuery = mini.get('#PatentInfoBrowse_Query');
        var cmdDownload = mini.get('PatentInfoBrowse_Download');
        var cmdChangeJS = mini.get('PatentInfoBrowse_ChangeJS');
        var QueryState = 0;
        var RoleName = '${RoleName}';

        var fit = mini.get('fitt');
        var tip = new mini.ToolTip();
        $(function () {
            $('#p1').hide();
            fit.setHeight('100%');
            fit.doLayout();

            if (RoleName == "企业用户") {
                changeQuery(0)
            } else changeQuery(3);
        });

        function updtinformation() {
            var ck = mini.get('datagrid1');
            var sqh = ck.getSelected().SHENQINGH;
            var fmmc = ck.getSelected().FAMINGMC;
            mini.open({
                url: "/main/patent/getpatentinfo?SHENQINGH=" + sqh,
                showModal: true,
                width: 1000,
                height: 600,
                allowResize: false,
                title: "正在修改 >> " + fmmc,
                ondestroy: function (action) {
                    if (action == "ok") {
                        alert("修改成功");
                        grid.reload();
                    }
                }
            })
        }

        var provCon = mini.getbyName('ProvinceID');
        var cityCon = mini.getbyName('CityID');
        var countyCon = mini.getbyName('CountyID');

        function provinceChanged(e) {
            var ids = provCon.getValue();
            cityCon.load('/area/getCitys?IDS=' + ids);
        }

        function citychanged(e) {
            var ids = cityCon.getValue();
            countyCon.load('/area/getCountys?IDS=' + ids);
        }

        function expand(e) {
            e = e || {};
            var btn = e.sender;
            if (!btn) {
                btn = mini.get('#PatentInfoBrowse_HighQuery');
            }
            var display = $('#p1').css('display');
            if (display == "none") {
                btn.setIconCls("icon-collapse");
                btn.setText("隐藏");
                $('#p1').css('display', "block");
                txtQuery.hide();
                comField.hide();
                cmdQuery.hide();
            } else {
                btn.setIconCls("icon-expand");
                btn.setText("高级查询");
                $('#p1').css('display', "none");
                txtQuery.show();
                comField.show();
                cmdQuery.show();
            }
            fit.setHeight('100%');
            fit.doLayout();
        }

        function onRendererXS(e) {
            var value = e.value;
            if (value) return value;
            if (e.record.YW) return e.record.YW;
            return '';
        }

        function onRenderLX(e) {
            var field = e.field;
            if (field == "SHENQINGLX") {
                var val = parseInt(e.value);
                var textVal = "";
                for (var i = 0; i < types.length; i++) {
                    var tt = types[i];
                    if (tt.id == val) {
                        textVal = tt.text;
                        break;
                    }
                }
                switch (val) {
                    case 0: {
                        e.cellHtml = "<span style='color:red'>" + textVal + "</style>";
                        break;
                    }
                    case 1: {
                        e.cellHtml = "<span style='color:black'>" + textVal + "</style>";
                        break;
                    }
                    case 2: {
                        e.cellHtml = "<span style='color:blue'>" + textVal + "</style>";
                        break;
                    }
                }
            } else if (field == "SHENQINGR") {
                var val = e.value;
                if (val) {
                    if (typeof (val) == "object") {
                        e.cellHtml = mini.formatDate(val, 'yyyy-MM-dd');
                    } else if (typeof (val) == "string") {
                        var ds = val.split(' ');
                        e.cellHtml = $.trim(ds[0]);
                    }
                }
            } else if (field == "Action") {
                var record = e.record;
                var memo = record["MEMO"];
                var editMemo = parseInt(record["EDITMEMO"] || 0);
                var text = ((memo == null || memo == "") ? "<span style='color:green;text-align:center'>添加</span>" : "<span " +
                    "style='color:blue'>修改</span>");
                if (editMemo == 0) {
                    if (memo) text = "<span style='color:gay;text-align:center'>查看</span>";
                }
                e.cellHtml = '<a href="javascript:void(0)"  data-placement="bottomleft"  code="' + record["SHENQINGH"] + '" class="showCellTooltip" onclick="ShowMemo(' + "'" + record["SHENQINGH"] + "'," + "'" + record["FAMINGMC"] + "'" + ')">' + text + '</a>';
            } else if (field == "ClientName") {
                var val = e.value;
                if (val) {
                    var clientId = e.record["ClientID"];
                    if (clientId) {
                        e.cellHtml = '<a href="javascript:void(0)" onclick="showClient(' + "'" + clientId + "'" + ')">' + val + '</a>';
                    } else e.cellHtml = val;
                }
            } else if (field == "DE") {
                var iid = e.record._id;
                e.cellHtml = '<a href="javascript:void(0)" onclick="detail(' + "'" + iid + "'" + ')">详情..</a>';
            }
        }

        function showClient(clientId) {
            mini.open({
                url: '/clientInfo/browse?Type=1&ClientID=' + clientId,
                width: '100%',
                height: '100%',
                title: '浏览客户资料',
                showModal: true,
                ondestroy: function () {

                }
            });
            window.parent.doResize();
        }

        function ShowMemo(id, title) {
            var rows = grid.getSelecteds();
            var cs = [];
            if (rows.length > 1) {
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    cs.push(row["SHENQINGH"]);
                }
                id = cs.join(',');
                title = "多条专利批量添加";
            }
            mini.open({
                url: '/work/addMemo/index?ID=' + id,
                showModal: true,
                width: 1000,
                height: 600,
                title: "【" + title + "】备注信息",
                onDestroy: function () {
                    grid.reload();
                }
            });
        }

        function afterload() {
            tip.set({
                target: document,
                selector: '.showCellTooltip',
                onbeforeopen: function (e) {
                    e.cancel = false;
                },
                onopen: function (e) {
                    var el = e.element;
                    if (el) {
                        var code = $(el).attr('code');
                        var rows = grid.getData();
                        var row = grid.findRow(function (row) {
                            if (row["SHENQINGH"] == code) return true;
                        });
                        if (row) {
                            var memo = row["MEMO"];
                            if (memo) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row["MEMO"] + '</td></tr></table>');
                            }
                        }
                    }
                }
            });
            try {
                updateStateNumbers();
                grid.doLayout();
                grid.setShowPager(true);
            } catch (e) {

            }
        }

        function onCustomDialog(e) {
            var btnEdit = this;
            mini.open({
                url: "/work/clientInfo/query?multiselect=1",
                title: "选择客户",
                width: 1000,
                height: 400,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            var form = new mini.Form("#editform");
                            var cnames = [];
                            for (var i = 0; i < data.length; i++) {
                                cnames.push(data[i].Name);
                            }
                            if (cnames) {
                                form.setData({'KH': cnames.join(',')}, false);
                                showResult();
                            }
                        }
                    }
                }
            });
        }


        function doExport() {
            var excel = new excelData(grid);
            excel.export("专利综合信息.xls");
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
            if (curState) {
                var obj = {
                    field: 'State',
                    value: curState,
                    oper: 'EQ'
                };
                result.push(obj);
            }
            arg["High"] = mini.encode(result);
            grid.load(arg);
        }

        function reset() {
            var form = new mini.Form('#highQueryForm');
            form.reset();
            txtQuery.setValue(null);
            comField.setValue('All');
            doQuery();
        }

        function doQuery(state) {
            var arg = {};
            var bs = [];
            var cs = [];
            var word = txtQuery.getValue();
            var field = comField.getValue();
            if (word) {
                if (field == "All") {
                    var datas = comField.getData();
                    for (var i = 0; i < datas.length; i++) {
                        var d = datas[i];
                        var f = d.id;
                        if (f == "All") continue;
                        if (f == "KH" || f == "LC" || f == "XS" || f == "DL" || f == "BH") f = "NEIBUBH";
                        var kWork = f + '=' + word;
                        if (cs.indexOf(kWork) == -1) {
                            var op = {field: f, oper: 'LIKE', value: word};
                            cs.push(op);
                        }
                    }
                } else {
                    if (field == "KH" || field == "LC" || field == "XS" || field == "DL") field = "NEIBUBH";
                    var op = {field: field, oper: 'LIKE', value: word};
                    cs.push(op);
                }
            }
            if (cs.length > 0) arg["Query"] = mini.encode(cs);
            if (!state) {
                if (curState) state = curState;
            }
            if (state) {
                var sstt = parseInt(state || 0);
                if (sstt > 0) {
                    var p = {
                        field: 'State',
                        value: state,
                        oper: 'EQ'
                    };
                    var gg = [p];
                    arg["High"] = mini.encode(gg);
                } else {
                    var p = {
                        field: 'State',
                        value: QueryState,
                        oper: 'EQ'
                    };
                    var gg = [p];
                    arg["High"] = mini.encode(gg);
                }
            }
            grid.load(arg);
        }


        function onRowClick(e) {
            var row = e.record;
            var tzsPath = row["UploadPath"];
            var jsr = row["JS"]
            if (tzsPath) {
                cmdDownload.show();
            } else {
                cmdDownload.hide();
            }
            // if (jsr) {
            //     cmdChangeJS.show();
            // } else cmdChangeJS.hide();
        }


        function beforeEdit(e) {
            var field = e.field;
            var record = e.record;
            var shenqingh = record["SHENQINGH"] || "";
            if (!shenqingh) {
                e.cancel = true;
                return;
            }

            if (field) {
                if (field == "Memo") e.cancel = false; else e.cancel = true;
            } else e.cancel = true;
        }

        function afterEdit(e) {
            var record = e.record;
            var val = e.value;
            var shenqingh = record["SHENQINGH"] || "";
            var msgId = mini.loading('正在保存信息.......', '保存提示');
            $.post('/work/patentInfo/updateMemo', {Memo: val, SHENQINGH: shenqingh}, function (result) {
                mini.hideMessageBox(msgId);
                if (result.success) {
                    grid.acceptRecord(record);
                } else {
                    mini.showTips({
                        content: result.message || "保存失败!",
                        x: 'right',
                        y: 'buttom'
                    });
                }
            });

        }

        function cellDblClick(e) {
            var row = grid.getSelected();
            var shenqingh = row["SHENQINGH"];
            mini.open({
                url: '/searchResult/Patentdetails?SHENQINGH=' + shenqingh,
                width: '100%',
                height: '99%',
                title: '专利详细信息'
            })
        }


        function detail(id) {
            var row = grid.getRowByUID(id);
            var shenqingh = row["SHENQINGH"];
            mini.open({
                url: '/searchResult/Patentdetails?SHENQINGH=' + shenqingh,
                width: '100%',
                height: '99%',
                title: '专利详细信息'
            })
        }

        function updateStateNumbers() {
            var key = (new Date()).getTime();
            var url = '/main/patent/getPatentTotal?Key=' + key;
            $.getJSON(url, {}, function (result) {
                if (result.success) {
                    var states = result.data || {};
                    for (var i = 0; i < states.length; i++) {
                        var state = states[i];
                        var con = $('.' + state.name);
                        if (con.length > 0) {
                            con.text(state.num);
                        }
                    }
                }
            });
        }

        var curState = null;

        function changeQuery(state) {
            var con = $('.x' + state);
            var cons = $('.Jdlcli');
            for (var i = 0; i < cons.length; i++) {
                var cx = $(cons[i]);
                cx.removeClass('clicked');
                $('#J' + i).css({"background-color": "rgb(226,250,252)"});
            }
            $(con).parents('.Jdlcli').addClass('clicked');
            $('#J' + state).css({"background-color": "rgba(247, 140, 24, 0.85)"});
            curState = parseInt(state || 0);
            if (curState == 0) curState = 0;
            QueryState = state;
            doQuery(state);
        }

        function doTask() {
            var rows = mini.clone(grid.getSelecteds());
            if (rows.length == 0) {
                var result = grid.getResultObject();
                var total = parseInt(result.total);
                if (total > 50000) {
                    mini.alert('当前记录集合大于五万条，建议缩小数据范围以后再进行采集操作!');
                    return;
                }
            }
            mini.open({
                url: '/main/patent/addTask',
                width: '80%',
                height: '70%',
                title: '采集官费数据',
                showModal: true,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    if (rows.length == 0) {
                        var url = grid.getUrl();
                        var param = grid.getLoadParams();
                        iframe.contentWindow.setDataSource(url, param);
                    } else {
                        iframe.contentWindow.setRows(rows);
                    }
                },
                ondestroy: function (act) {
                    if (act == 'ok') {
                        grid.reload();
                    }
                }
            });
        }

        function showTask() {
            mini.open({
                url: '/main/patent/showTask',
                width: '90%',
                height: '80%',
                title: '查看采集进度',
                showModal: true
            })
        }

        function showTaskMain() {
            mini.open({
                url: '/main/patent/showTaskMain',
                width: 1200,
                height: '60%',
                title: '查看采集进度',
                showModal: true
            })
        }
    </script>
</@layout>