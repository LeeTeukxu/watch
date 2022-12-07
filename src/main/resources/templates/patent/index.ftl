<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/bigDataExport.js"></script>
    <script type="text/javascript" src="/js/clipboard.min.js"></script>
    <script type="text/javascript">
        var users ={};
        var deps = {};
        var users2 = {};
        var deps2 = {};
        for (i in users) {
            users2[users[i]] = i;
        }
        for (j in deps) {
            deps2[deps[j]] = j;
        }
        var querFields = [
            {id: 'All', text: '全部属性'},
            {id: 'FAMINGMC', text: '专利名称'},
            {id: 'SHENQINGH', text: '专利号'},
            {id: 'LAWSTATUS', text: '专利状态'},
            {id: 'SHENQINGRXM', text: '专利申请人'}
        ];
        var flzt=[
            {id:'专利权维持',text:'专利权维持'},
            {id:'未缴年费专利权终止，等恢复',text:'未缴年费专利权终止，等恢复'},
            {id:'未缴年费终止失效',text:'未缴年费终止失效'},
            {id:'等年费滞纳金',text:'等年费滞纳金'}
        ];
        var types = [{id: '发明公布', text: '发明公布'}, {id: '实用新型', text: '实用新型'}, {id: '外观设计', text: '外观设计'}];
    </script>
    <div class="mini-layout" style="width:100%;height:100%">
        <div region="center">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            专利类型：<input class="mini-combobox" data="types" id="zlType" value="发明公布"/>
                            <a class="mini-button mini-button-info PatentInfoBrowse_Import" onclick="doRenImport"
                            >导入润桐专利</a>
                            <a class="mini-button mini-button-info PatentInfoBrowse_Import" onclick="doYiImport"
                            >导入意愿专利</a>
                            <a class="mini-button mini-button-info PatentInfoBrowse_Import" onclick="doGuanImport"
                            >导入官方发明</a>
                            <span class="separator"></span>
                            <a class="mini-button mini-button-info" onclick="doExport" id="PatentInfoBrowse_Export">导出Excel</a>

                            <a class="mini-button mini-button-info" onclick="doDownload" id="PatentInfoBrowse_Download">专利信息模板下载</a>
                        </td>
                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field PatentInfoBrowse_Query" id="comField"
                                 style="width:100px" data="querFields" value="All" id="Field"></div>
                            <input class="mini-textbox Query_Field PatentInfoBrowse_Query" style="width:150px"
                                   id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();" id="PatentInfoBrowse_Query">模糊搜索</a>
                            <a class="mini-button mini-button-danger Query_Field" id="PatentInfoBrowse_Reset"
                               onclick="reset()">重置</a>
                            <a class="mini-button" id="PatentInfoBrowse_HighQuery" iconCls="icon-expand"
                               onclick="expand">高级查询</a>

                        </td>
                    </tr>
                </table>
                <div id="p1" style="border:1px solid #909aa6;border-top:0;height:150px;padding:5px;display:none">
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
                            <td style="width:15%;"><input name="ANJIANYWZT" class="mini-combobox" data-oper="EQ"
                                                          style="width:100%" data="flzt" valueFromSelect="true"/></td>
                            <td style="width:6%;padding-left:10px;">专利申请人：</td>
                            <td style="width:15%;"><input name="SHENQINGRXM" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"/></td>
                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">所属市：</td>
                            <td style="width:15%">
                                <input name="CityID" id="CityID" data-oper="EQ" style="width:100%" class="mini-combobox" allowInput="true"
                                       url="" style="width:100%" onvaluechanged="showPark"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">所属园区：</td>
                            <td style="width:15%">
                                <input name="ParkID" id="ParkID" data-oper="EQ" style="width:100%" allowInput="true" class="mini-combobox"
                                       url="" style="width:100%" />
                            </td>
                            <td style="width:6%;padding-left:10px;">企业名称：</td>
                            <td style="width:15%">
                                <input name="KHName" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                                <a class="mini-button mini-button-success" onclick="doHightSearch" style="width:120px">搜索</a>
                                <a class="mini-button mini-button-danger" onclick="expand"
                                   style="margin-left:30px;width:120px">收起</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-fit" id="fitt">
                <div class="mini-datagrid" id="datagrid1" style="width:100%;height:100%"
                     url="/patent/getData?LongTime=1"
                     allowCellEdit="true" allowCellSelect="true" sizelist="[5,10,20,50,100,200]"
                     onrowclick="onRowClick"
                     oncellbeginedit="beforeEdit" oncellcommitedit="afterEdit"
                     autoload="true"  multiSelect="true" pageSize="20"
                     sortField="SHENQINGR" sortOrder="desc" ondrawcell="onRenderLX" onload="afterload"
                     oncelldblclick="cellDblClick">
                    <div property="columns">
                        <div type="indexcolumn" style="width:80px"></div>
                        <div type="checkcolumn"></div>
                        <div field="MEMO" width="200"   headerAlign="center" align="center">摘要</div>
                        <div field="SHENQINGRXM" width="200" headerAlign="center" allowsort="true" align="center">专利申请人</div>
                        <div field="SHENQINGH" width="120" headerAlign="center" allowsort="true" align="center">专利申请号</div>
                        <div field="SHENQINGLX" width="80" headerAlign="center" allowsort="true" align="center">专利类型</div>
                        <div field="FAMINGMC" width="200" headeralign="center" allowsort="true"
                             renderer="onZhanlihaoZhuangtai" align="center">专利名称
                        </div>
                        <div field="SHENQINGR" width="110" headerAlign="center" Align="center" dataType="date"
                             dateFormat="yyyy-MM-dd" allowsort="true">申请日期
                        </div>
                        <div field="LAWSTATUS" width="100" headerAlign="center" allowsort="true" align="center">专利状态
                        </div>
                        <div field="FAMINGRXM"  width="150" headerAlign="center" allowsort="true"
                             align="center">发明人</div>
                        <div field="GONKAIHAO" width="80" headerAlign="center" allowsort="true">公开公告号</div>
                        <div field="GONKAIR" width="100" headerAlign="center" allowsort="true" dataType="date"
                             dateFormat="yyyy-MM-dd"  align="center">公开公告日</div>
                        <div field="DAILIJGMC" width="200" headerAlign="center" align="center"
                             allowsort="true">代理机构</div>
                        <div field="DAILIRXM" width="80"  headerAlign="center" align="center" allowsort="true">代理人</div>
                        <div field="ADDRESS" width="200"   headerAlign="center" align="center">申请人地址</div>
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

    <script type="text/javascript" src="/js/common/ImportManage.js"></script>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get('#datagrid1');
        var txtQuery = mini.get('#QueryText');
        var comField = mini.get('#comField');
        var cmdDownload = mini.get('PatentInfoBrowse_Download');
        var cmdChangeJS = mini.get('PatentInfoBrowse_ChangeJS');

        var fit = mini.get('fitt');
        var tip = new mini.ToolTip();
        $(function () {
            $('#p1').hide();
            fit.setHeight('100%');
            fit.doLayout();
            $('.PatentInfoBrowse_Export').css({'display':''});

        });

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

            } else {
                btn.setIconCls("icon-expand");
                btn.setText("高级查询");
                $('#p1').css('display', "none");
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
            if (field == "SHENQINGR") {
                var val = e.value;
                if (val) {
                    if (typeof(val) == "object") {
                        e.cellHtml = mini.formatDate(val, 'yyyy-MM-dd');
                    }
                    else if (typeof(val) == "string") {
                        var ds = val.split(' ');
                        e.cellHtml = $.trim(ds[0]);
                    }
                }
            }
            else if (field == "Action") {
                var record = e.record;
                var memo = record["MEMO"];
                var editMemo = parseInt(record["EDITMEMO"] || 0);
                var text = ((memo == null || memo == "") ? "<span style='color:green;text-align:center'>添加</span>" : "<span " +
                        "style='color:blue'>修改</span>");
                if (editMemo == 0) {
                    if (memo) text = "<span style='color:gay;text-align:center'>查看</span>";
                }
                e.cellHtml = '<a href="#"  data-placement="bottomleft"  code="' + record["SHENQINGH"] + '" class="showCellTooltip" onclick="ShowMemo(' + "'" + record["SHENQINGH"] + "'," + "'" + record["FAMINGMC"] + "'" + ')">' + text + '</a>';
            }
            else if (field == "KH") {
                var val = e.value;
                if (val) {
                    var clientId = e.record["KHID"];
                    if (clientId) {
                        e.cellHtml = '<a href="#" onclick="showClient(' + "'" + clientId + "'" + ')">' + val + '</a>';
                    } else e.cellHtml = val;
                }
            }
            else if(field=="BH"){
                var val=e.value;
                if(val){
                    var casesId=e.record["CasesID"];
                    if(casesId){
                        e.cellHtml = '<a href="#" onclick="editCases(' + "'" + casesId + "'" + ')">' + val + '</a>';
                    }
                }
            }
        }
        function showClient(clientId) {
            mini.open({
                url:'/clientInfo/browse?Type=1&ClientID='+clientId,
                width:'100%',
                height:'100%',
                title:'浏览客户资料',
                showModal:true,
                ondestroy:function(){

                }
            });
            window.parent.doResize();
        }
        function ShowMemo(id, title) {
            mini.open({
                url: '/work/addMemo/index?ID=' + id,
                showModal: true,
                width: 1000,
                height: 500,
                title: "【" + title + "】的备注信息",
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
        }

        function cancelRow(){
            var editWindow = mini.get("editWindow");
            if(editWindow)editWindow.show();
        }
        function onCustomDialog(e) {
            var btnEdit = this;
            var parkCombo = mini.get("Park");
            var id = parkCombo.getValue();
            mini.open({
                url: "/clientInfo/query?Park=" + id,
                title: "选择客户",
                width: 1000,
                height: 400,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            var len = count(data);
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
        function count(o){
            var t = typeof o;
            if(t == 'string'){
                return o.length;
            }else if(t == 'object'){
                var n = 0;
                for(var i in o){
                    n++;
                }
                return n;
            }
            return false;
        }
        function saveNBBH() {
            var rows = grid.getSelecteds();
            var cs = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var sq = row["SHENQINGH"];
                if (sq) cs.push(sq);
            }
            var url = '/work/patentInfo/changeValue';
            var code = mini.get('ipNbbh').getValue();
            var arg = {IDS: cs.join(","), value: code, field: "NEIBUBH"};
            $.post(url, arg, function (r) {
                if (r.success) {
                    mini.alert('选择记录的内部编号设置成功。');
                    var editWindow = mini.get("editWindow");
                    editWindow.hide();
                    grid.reload();
                } else {
                    var msg = r.message || "设置失败，请稍候重试。";
                    mini.alert(msg);
                }
            });
        }

        function showResult() {
            var form = new mini.Form("#editform");
            var datas = mini.clone(form.getData());
            var ps = [];
            for (var p in datas) {
                if (p && datas[p]) {
                    if (p == "KH") {
                        var g = p + ":" + datas[p];
                        ps.push(g);
                    }
                    else {
                        var values = datas[p].split(',')
                        var texts = [];
                        for (var n = 0; n < values.length; n++) {
                            var val = values[n];
                            var text = deps2[val];
                            texts.push(text);
                        }
                        var g = p + ":" + texts.join(',');
                        ps.push(g);
                    }
                }
            }
            mini.get('ipNbbh').setValue(ps.join(';'));
            onCityChanged();
        }

        function doExport() {
            var excel = new bigDataExport({
                grid:grid,
                postUrl:'/work/patentInfo/export',
                getNumberUrl:'/work/patentInfo/getNumber'
            });
            excel.export("专利综合信息.xls");
        }
        var myArg={};
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

        function reset() {
            mini.get('#QueryText').setValue(null);
            var form = new mini.Form('#highQueryForm');
            form.reset();
            doQuery();
        }

        function doQuery() {
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
                        if (f == "KH" || f == "LC" || f == "XS" || f == "DL" || f=="BH") f = "NEIBUBH";
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
            myArg=arg;
            grid.load(arg);
        }



        function onRowClick(e) {
            // var row = e.record;
            // var tzsPath = row["UploadPath"];
            // var jsr = row["JS"]
            // if (tzsPath) {
            //     cmdDownload.show();
            // } else {
            //     cmdDownload.hide();
            // }
            // if (jsr) {
            //     cmdChangeJS.show();
            // } else cmdChangeJS.hide();
        }

        function download() {
            var rows = grid.getSelecteds();
            var code = null;
            var name = null;
            if (rows.length == 1) {
                var row = rows[0];
                if (row) {
                    code = row["SHENQINGH"];
                    name = row["FAMINGMC"] + '.zip';
                }
            }
            else {
                var codes = [];
                for (var i = 0; i < rows.length; i++) {
                    var row = rows[i];
                    var code = row["SHENQINGH"];
                    codes.push(code);
                }
                code = codes.join(',');
                name = rows.length + '个专利申请文件打包下载.zip';
            }
            var url = '/work/patentInfo/download?Code=' + code + '&FileName=' + encodeURI(name);
            $.fileDownload(url, {
                httpMethod: 'POST',
                successCallback:function(xurl){

                },
                failCallback: function (html, xurl) {
                    mini.alert('下载错误:' + html, '系统提示');
                }
            });
            return false;
        }
        function doRenImport() {
            var zlType=mini.get('#zlType').getValue();
            var con ={};
            con['Title'] = '专利信息导入';
            con['openUrl'] = '/patent/ImportPantentData?mode=Run&type='+zlType;
            con['dataGrid'] = grid;
            con['mode'] = 'data';
            ImportManage(con);
        }
        function doYiImport() {
            var zlType=mini.get('#zlType').getValue();
            var con ={};
            con['Title'] = '专利信息导入';
            con['openUrl'] = '/patent/ImportPantentData?mode=Yi&type='+zlType;
            con['dataGrid'] = grid;
            con['mode'] = 'data';
            ImportManage(con);
        }

        function doGuanImport() {
            var zlType=mini.get('#zlType').getValue();
            var con ={};
            con['Title'] = '专利信息导入';
            con['openUrl'] = '/patent/ImportPantentData?mode=Guan&type='+zlType;
            con['dataGrid'] = grid;
            con['mode'] = 'data';
            ImportManage(con);
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
            $.post('/work/patentInfo/updateMemo',{Memo:val,SHENQINGH:shenqingh},function(result){
                mini.hideMessageBox(msgId);
                if(result.success){
                    grid.acceptRecord(record);
                } else {
                    mini.showTips({
                        content:result.message || "保存失败!",
                        x:'right',
                        y:'buttom'
                    });
                }
            });

        }
        function cellDblClick(e) {

        }
        function cancelRow(){
            var win=mini.get('editWindow');
            if(win)win.hide();
        }
        function doExportCode(){
            var rows=grid.getData();
            var cs=[];
            for(var i=0;i<rows.length;i++){
                var row=rows[i];
                var code=row["SHENQINGH"];
                if(code) cs.push(code);
            }
            var uid=mini.prompt(cs.length+'个专利编号列表','导出提示',function(action,value){},true);
            var win = mini.getbyUID(uid);
            win.setWidth(600);
            win.setHeight(400);
            $(win.el).find("textarea,input").width(550).height(240).val(cs.join(','));
            win.doLayout();
        }
        function onCityChanged() {
            var cityCombo = mini.get("City");
            var parkCombo = mini.get("Park");
            var id = cityCombo.getValue();

            var url = "/clientInfo/getParkByCity?PID=" + id;
            parkCombo.setUrl(url);
        }

        function doDownload() {
            var url = '/work/patentInfo/downloadTemplate?FileName=' + encodeURI("专利信息导入模板下载.xls");
            $.fileDownload(url,{
                httpMethod:'POST',
                failCallback:function (html,xurl) {
                    mini.alert('下载错误:' + html, '系统提示')
                }
            });
            return false;
        }

        function showPark(){
            var cityCombo = mini.get("CityID");
            var parkCombo = mini.get("ParkID");

            var id = cityCombo.getValue();

            if(id != "") {
                var url = "/clientInfo/getParkByCity?PID=" + id;
                parkCombo.setUrl(url);
            }
        }
    </script>
</@layout>