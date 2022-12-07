<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
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
            {id: 1, text: '企业'},
            {id: 2, text: '机关'},
            {id: 3, text: '学校'},
            {id: 4, text: '个人'},
            {id: 5, text: '其它'}
        ];
    </script>
    <div class="mini-layout" style="width: 100%; height: 100%;overflow:hidden">
        <div region="center" style="overflow:hidden">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            <a class="mini-button" id="CompanyBrowse_Add" iconcls="icon-add" onclick="addCompany" plain="true">新增</a>
                            <a class="mini-button" id="CompanyBrowse_Edit" iconcls="icon-edit" onclick="editCompany" plain="true">修改</a>
                            <a class="mini-button" id="CompanyBrowse_Remove" iconcls="icon-remove" onclick="remove" plain="true">删除</a>
                            <a class="mini-button" id="CompanyBrowse_Browse" iconcls="icon-new" onclick="browseCompany" plain="true">浏览</a>
                            <span class="separator"></span>
                        </td>
                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field CompanyBrowse_Query" id="comField" style="width:100px" data="[{id:'All',
                            text:'全部属性'},
                            {id:'Name',text:'公司名称'},{id:'Address',text:'地址'},{id:'LinkPhone',text:'联系方式'},{id:'LinkMan',text:'联系人'},
                            {id:'Memo',text:'备注信息'}]" value="All" id="Field"></div>
                            <input class="mini-textbox Query_Field CompanyBrowse_Query" style="width:150px" id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();"
                               id="CompanyBrowse_Query">模糊搜索</a>
                            <a class="mini-button mini-button-danger"  id="CompanyBrowse_Reset"
                               onclick="reset">重置条件</a>
                            <a class="mini-button mini-button-info"  id="CompanyBrowse_HighQuery"
                               onclick="expand">高级查询</a>
                        </td>
                    </tr>
                </table>
                <div id="p1" style="border:1px solid #909aa6;border-top:0;height:100px;padding:5px;display:none">
                    <table style="width:100%;" id="highQueryForm">
                        <tr>
                            <td style="width:6%;padding-left:10px;">公司类型：</td>
                            <td style="width:15%;">
                                <input name="Type" data-oper="EQ" style="width:100%" class="mini-combobox"
                                       data="types"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">公司名称：</td>
                            <td style="width:15%;">
                                <input name="Name" data-oper="LIKE" class="mini-textbox" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">地址：</td>
                            <td style="width:15%;">
                                <input name="Address" data-oper="LIKE" class="mini-textbox" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">联系方式：</td>
                            <td style="width:15%;">
                                <input name="LinkPhone" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">创建时间：</td>
                            <td style="width:15%;">
                                <input name="SignDate" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">到：</td>
                            <td style="width:15%;">
                                <input name="SignDate" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">备注：</td>
                            <td style="width:15%;">
                                <input name="Memo" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                                <button class="mini-button mini-button-success"style="width:120px" onclick="doHightSearch()">搜索</button>
                                <button class="mini-button mini-button-danger"style="width:120px" onclick="expand()">收起</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-fit" id="fitt">
                <div id="CompanyBrowse_datagrid" class="mini-datagrid" style="width: 100%; height: 100%;"
                     onrowdblclick="editCompany" showLoading="true"
                     allowresize="true" url="/work/company/getData" multiselect="true"
                     pagesize="30" sizelist="[5,10,20,30,50,100,150,200]" sortfield="SignDate" sortorder="desc"
                     autoload="true">
                    <div property="columns">
                        <div type="indexcolumn" ></div>
                        <div type="checkcolumn"></div>
                        <div field="Type" name="Type" width="80" headeralign="center" type="comboboxcolumn"
                             allowsort="true" align="center">
                            公司类型
                            <div property="editor" class="mini-combobox" data="[{id:1,text:'企业'},{id:2,
                        text:'机关'},{id:3,text:'学校'},{id:4,text:'个人'},{id:5,text:'其它'}]"></div>
                        </div>
                        <div field="Name" name="Name" width="250" headeralign="center" allowsort="true" align="center">
                            公司名称
                        </div>
                        <div field="Address" name="Address" width="100" headeralign="center" align="center" allowsort="true">
                            地址
                        </div>
                        <div field="LinkPhone" name="LinkPhone" width="100" headeralign="center" align="center" allowsort="true">
                            联系方式
                        </div>
                        <div field="LinkMan" width="120" headeralign="center" allowsort="true" align="center">联系人</div>
                        <div field="CreateMan" width="80" headeralign="center" type="treeselectcolumn" allowsort="true" align="center">
                            创建人
                            <input property="editor" class="mini-treeselect" valueField="FID" parentField="PID"
                                   textField="Name" url="/system/dep/getAllLoginUsersByDep" id="CreateMan" style="width:
                               98%;" required="true" resultAsTree="false"/>
                        </div>
                        <div field="SignDate" width="120" headeralign="center" align="center" allowsort="true"
                             dataType="date" dateFormat="yyyy-MM-dd">创建时间</div>
                        <div field="Memo" width="200" headeralign="center" allowsort="true" align="center">备注</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/common/ImportManage.js"></script>
    <script type="text/javascript">
        mini.parse();
        var txtQuery = mini.get('#QueryText');
        var comField = mini.get('#comField');
        var cmdQuery=mini.get('#CompanyBrowse_Query');
        $(function () {
            $('#p1').hide();
        });
        var grid = mini.get('CompanyBrowse_datagrid');
        var fit = mini.get('fitt');
        var RegisterPath="";

        function expand(e) {
            var btn = e.sender;
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
        function addCompany(){
            mini.open({
                url:'/work/company/add',
                width:'100%',
                height:'100%',
                title:'新增公司资料',
                showModal:true,
                ondestroy:function(){
                    grid.reload();
                }
            });
        }
        function editCompany(){
            var row=grid.getSelected();
            if(!row){
                mini.alert('请选择要编辑的公司信息!');
                return ;
            }
            var companyId=row["ID"];
            mini.open({
                url:'/work/company/edit?CompanyID='+companyId,
                width:'100%',
                height:'100%',
                title:'编辑公司资料',
                showModal:true,
                ondestroy:function(){
                    grid.reload();
                }
            });
        }
        function browseCompany(){
            var row=grid.getSelected();
            if(!row){
                mini.alert('请选择要浏览的客户信息!');
                return ;
            }
            var companyId=row["ID"];
            mini.open({
                url:'/work/company/browse?CompanyID='+companyId,
                width:'100%',
                height:'100%',
                title:'编辑客户资料',
                showModal:true,
                ondestroy:function(){

                }
            });
        }

        function remove() {
            var rows = grid.getSelecteds();
            var ids = [];
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i]["ID"]);
            }
            if (ids.length == 0) {
                mini.alert('请选择要删除的记录!');
                return;
            }
            mini.confirm('确认要删除公司信息数据?', '删除提示', function (act) {
                if (act === 'ok') {
                    var url="/work/company/remove";
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
        function reset(){
            var form=new mini.Form('#highQueryForm');
            form.reset();
            txtQuery.setValue(null);
            comField.setValue('All');
            doHightSearch();
        }
    </script>
</@layout>