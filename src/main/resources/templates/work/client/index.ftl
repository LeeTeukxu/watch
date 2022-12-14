<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <style type="text/css">
        .panel-expand {
            background-image: url(/js/miniui/themes/icons/collapse.gif);
        }

        .panel-collapse {
            background-image: url(/js/miniui/themes/icons/collapse.gif);
        }
    </style>
    <script type="text/javascript">
        var types = [
            {id: 1, text: '工矿企业'},
            {id: 3, text: '事业单位'},
            {id: 4, text: '大专院校'},
            {id: 2, text: '个人'}
        ];
        var techTypes = [
            {id: 1, text: '高新企业'},
            {id: 2, text: '潜在高新企业'},
            {id: 3, text: '非高企'}
        ];
    </script>
    <div class="mini-layout" style="width: 100%; height: 100%;overflow:hidden">
        <div region="center" style="overflow:hidden">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            <a class="mini-button" id="ClientBrowse_Add" iconcls="icon-add" onclick="addClient"
                               plain="true">新增</a>
                            <a class="mini-button" id="ClientBrowse_Edit" iconcls="icon-edit" onclick="editClient"
                               plain="true">修改</a>
                            <a class="mini-button" id="ClientBrowse_Remove" iconcls="icon-remove" onclick="remove"
                               plain="true">删除</a>
                            <a class="mini-button" id="ClientBrowse_Browse" iconcls="icon-new" onclick="browseClient"
                               plain="true">浏览</a>
                            <span class="separator"></span>
                            <a class="mini-button" plain="true" iconcls="icon-folderopen" id="ClientBrowse_Import"
                               onclick="doImport">导入客户</a>
                            <a class="mini-button" id="ClientBrowse_Export" iconcls="icon-xls" plain="true"
                               onclick="doExport">导出Excel</a>
                            <a class="mini-button" iconcls="icon-xls" plain="true"
                               onclick="exportRecord">导出企业专利档案</a>
                            <span class="separator"></span>
                        </td>
                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field ClientBrowse_Query" id="comField"
                                 style="width:100px"
                                 data="[{id:'All',text:'全部属性'},{id:'Name',text:'客户名称'},{id:'Email',text:'电子邮件'},{id:'CreditCode',text:'信用代码'},{id:'SignManName',text:'登记人'},{id:'Memo',text:'备注信息'}]"
                                 value="All"></div>
                            <input class="mini-textbox Query_Field ClientBrowse_Query" style="width:150px"
                                   id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();"
                               id="ClientBrowse_Query">模糊搜索</a>
                            <a class="mini-button mini-button-danger" id="ClientBrowse_Reset"
                               onclick="reset">重置条件</a>
                            <a class="mini-button mini-button-info" id="ClientBrowse_HighQuery"
                               onclick="expand">高级查询</a>
                        </td>
                    </tr>
                </table>
                <div id="p1" style="border:1px solid #909aa6;border-top:0;height:150px;padding:5px;display:none">
                    <table style="width:100%;" id="highQueryForm">
                        <tr>
                            <td style="width:6%;padding-left:10px;">登记时间：</td>
                            <td style="width:15%;">
                                <input name="SignDate" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">到：</td>
                            <td style="width:15%;">
                                <input name="SignDate" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">信用代码：</td>
                            <td style="width:15%;">
                                <input name="CreditCode" data-oper="LIKE" style="width:100%" class="mini-textbox"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">地址：</td>
                            <td style="width:15%;">
                                <input name="Address" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>

                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">登录邮箱：</td>
                            <td style="width:15%;">
                                <input name="OrgCode" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">客户名称：</td>
                            <td style="width:15%;">
                                <input name="Name" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">登记人：</td>
                            <td style="width:15%;">
                                <input name="SignManName" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">备注：</td>
                            <td style="width:15%;">
                                <input name="Memo" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="width:6%;padding-left:10px;">联系人：</td>
                            <td style="width:15%;">
                                <input name="LinkMan" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">高企申报情况：</td>
                            <td style="width:15%;">
                                <input name="CompanyType" class="mini-combobox" data="techTypes" data-oper="EQ"
                                       style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">高企认定年度：</td>
                            <td style="width:15%;">
                                <input name="TechYear" class="mini-combobox" data="[{id:'',text:'无'},{id:2019,
                                text:'2019'},{id:2020, text:'2020'},{id:2021, text:'2021'},{id:2022,text:'2022'},{id:2023,text:'2023'},
                                {id:2024,text:'2025'},{id:2026,text:'2026'},{id:2027,text:'2027'},{id:2028,text:'2028'},
                                {id:2029,text:'2029'},{id:2030,text:'2030'}]"
                                       data-oper="EQ" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">高企证书编号：</td>
                            <td style="width:15%;">
                                <input name="TechNo" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                                <button class="mini-button mini-button-success ClientBrowse_Query" style="width:120px"
                                        onclick="doHightSearch()">搜索
                                </button>
                                &nbsp;&nbsp; &nbsp;
                                <button class="mini-button mini-button-danger ClientBrowse_Query" style="width:120px"
                                        id="CmdExpand" onclick="expand()">收起
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-fit" id="fitt">
                <div id="ClientBrowse_datagrid" class="mini-datagrid" style="width: 100%; height: 100%;"
                     onrowdblclick="editClient" showLoading="true"
                     allowresize="true" url="/clientInfo/getData" multiselect="true"
                     pagesize="20" sizelist="[5,10,20,30,50,100,150,200]" sortfield="SignDate" sortorder="desc"
                     autoload="true">
                    <div property="columns">
                        <div type="indexcolumn" width="30" ></div>
                        <div type="checkcolumn" width="30"></div>
                        <div field="LastTime" width="140" headerAlign="center" allowsort="true"
                             dataType="date" dateFormat="yyyy-MM-dd" align="center">最近费用截止时间
                        </div>
                        <div field="ZL" name="Name" width="140" headeralign="center" allowsort="true"
                             align="center">
                            是否有滞纳期专利
                        </div>
                        <div field="Name" name="Name" width="250" headeralign="center" allowsort="true" align="center">
                            客户名称
                        </div>
                        <div field="CreditCode" name="Name" width="200" headeralign="center" allowsort="true"
                             align="center">信用代码
                        </div>
                        <div field="CompanyType" width="120" headerAlign="center" type="comboboxcolumn"
                             allowsort="true" align="center">高企申报情况
                            <div property="editor" class="mini-combobox" data="techTypes"></div>
                        </div>
                        <div field="TechYear" width="100" headerAlign="center" allowsort="true"
                             align="center">高企认定年度
                        </div>
                        <div field="TechNo" width="150" headerAlign="center" allowsort="true" align="center">高企证书编号
                        </div>
                        <div field="LinkMan" name="Name" width="100" headeralign="center" allowsort="true"
                             align="center">
                            联系人
                        </div>
                        <div field="LinkPhone" name="Name" width="100" headeralign="center" allowsort="true"
                             align="center">
                            联系方式
                        </div>

                        <div field="Email" width="120" headeralign="center" allowsort="true" align="center">登录邮箱</div>
                        <div field="SignMan" width="80" headeralign="center" type="treeselectcolumn" allowsort="true" align="center">
                            登记人
                            <input property="editor" class="mini-treeselect" valueField="FID" parentField="PID"
                                   textField="Name" url="/system/dep/getAllLoginUsersByDep" id="SignMan" style="width:
                               98%;" required="true" resultAsTree="false"/>
                        </div>
                        <div field="Address" name="Name" width="300" headeralign="center" allowsort="true"
                             align="center">
                            联系人地址
                        </div>
                        <div field="SignDate" width="120" headeralign="center" allowsort="true" dataType="date"
                             dateFormat="yyyy-MM-dd" align="center">登记日期
                        </div>
                        <div field="FollowDate" width="100" headerAlign="center" allowsort="true"
                             dataType="date" dateFormat="yyyy-MM-dd" align="center">最新跟进时间
                        </div>
                        <div field="Memo" width="200" headeralign="center" allowsort="true" align="center">备注</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form method="post" action="/clientInfo/exportExcel" style="display:none" id="exportExcelForm">
        <input type="hidden" name="Data" id="exportExcelData"/>
    </form>
    <script type="text/javascript" src="/js/common/ImportManage.js"></script>
    <script type="text/javascript">
        mini.parse();
        var txtQuery = mini.get('#QueryText');
        var comField = mini.get('#comField');
        var cmdQuery = mini.get('#ClientBrowse_Query');
        $(function () {
            $('#p1').hide();
            grid = mini.get('ClientBrowse_datagrid');
        });
        var grid = mini.get('ClientBrowse_datagrid');
        var fit = mini.get('fitt')
        var RegisterPath = "";

        function expand(e) {
            e = e || {};
            var btn = e.sender;
            if (!btn) {
                btn = mini.get('#ClientBrowse_HighQuery');
            }
            var display = $('#p1').css('display');
            if (display == "none") {
                btn.setIconCls("icon-collapse");
                btn.setText("隐藏");
                $('#p1').css('display', "block");
                cmdQuery.hide();
                comField.hide();
                txtQuery.hide();
            } else {
                btn.setIconCls("icon-expand");
                btn.setText("高级查询");
                $('#p1').css('display', "none");
                cmdQuery.show();
                comField.show();
                txtQuery.show();
            }
            fit.setHeight('100%');
            fit.doLayout();
        }

        function doShow() {
            mini.open({
                url: "/Client/YClientReport/Index",
                width: '100%',
                height: '100%',
                showModal: true,
                title: '数据统计'
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
            grid.load(arg);
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
                        var kWork = f + '=' + word;
                        if (cs.indexOf(kWork) == -1) {
                            var op = {field: f, oper: 'LIKE', value: word};
                            cs.push(op);
                        }
                    }
                } else {
                    var op = {field: field, oper: 'LIKE', value: word};
                    cs.push(op);
                }
            }
            if (cs.length > 0) arg["Query"] = mini.encode(cs);
            grid.load(arg);
        }
        function addClient(){
            mini.open({
                url:'/clientInfo/add',
                width:'100%',
                height:'100%',
                title:'新增客户资料',
                showModal:true,
                ondestroy:function(){
                    grid.reload();
                }
            });
        }
        function editClient(){
            grid = mini.get('ClientBrowse_datagrid');
            var row=grid.getSelected();
            if(!row){
                mini.alert('请选择要编辑的客户信息!');
                return ;
            }
            var clientId=row["ClientID"];
            mini.open({
                url:'/clientInfo/edit?ClientID='+clientId,
                width:'100%',
                height:'99%',
                title:'编辑客户资料',
                showModal:true,
                ondestroy:function(){
                    grid.reload();
                }
            });
        }
        function browseClient(){
            grid = mini.get('ClientBrowse_datagrid');
            var row=grid.getSelected();
            if(!row){
                mini.alert('请选择要浏览的客户信息!');
                return ;
            }
            var clientId=row["ClientID"];
            mini.open({
                url:'/clientInfo/browse?ClientID='+clientId,
                width:'100%',
                height:'99%',
                title:'查看客户资料',
                showModal:true,
                ondestroy:function(){

                }
            });
        }

        function remove() {
            grid = mini.get('ClientBrowse_datagrid');
            var rows = grid.getSelecteds();
            var ids = [];
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i]["ClientID"]);
            }
            if (ids.length == 0) {
                mini.alert('请选择要删除的记录!');
                return;
            }
            mini.confirm('确认要删除客户信息数据?', '删除提示', function (act) {
                if (act == 'ok') {
                    var url="/clientInfo/remove";
                    $.post(url,{IDS:mini.encode(ids)},function(r){
                        if (r['success']) {
                            mini.alert("删除成功！",'删除提示',function () {
                                grid.reload();
                            });

                        } else mini.alert("删除失败！");
                    });
                }
            });
        }

        function doExport(){
            var excel=new excelData(grid);
            excel.addEvent('beforeGetData',function (grid,rows) {
                return grid.getSelecteds();
            })
            excel.export("客户信息.xls");
        }

        function doImport() {
            var con ={};
            con['Title'] = '客户信息导入';
            con['TemplateUrl'] = '/static/template/客户导入模板.xls';
            con['openUrl'] = '/clientInfo/ImportClientData?Code=client&FileName='+encodeURIComponent('客户信息导入模板.xls');
            con['dataGrid'] = grid;
            con['mode'] = 'data';
            ImportManage(con);
        }

        function reset() {
            var form = new mini.Form('#highQueryForm');
            form.reset();
            txtQuery.setValue(null);
            comField.setValue('All');
            doHightSearch();
        }

        function exportRecord() {
            var row = grid.getSelected();
            if (row) {
                var clientId = row["ClientID"];
                $.fileDownload('/clientInfo/exportPatentRecord?ClientID=' + clientId, {
                    httpMethod: 'POST',
                    prepareCallback: function (url) {

                    },
                    failCallback: function (html, url) {
                        mini.alert('下载错误:' + html, '系统提示');
                    }
                });
            } else mini.alert('请选择要导出的企业记录!');
        }
    </script>
</@layout>