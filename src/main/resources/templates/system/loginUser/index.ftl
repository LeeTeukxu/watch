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
        <div region="west" title="公司列表" width="260">
            <ul class="mini-tree" idField="FID" parentField="PID" textField="Name" resultAsTree="false"
                url="/loginUser/getCompanyList" expandOnload="true" onnodeclick="nodeClick" id="tree1"></ul>
        </div>
        <div region="center" style="overflow:hidden">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            <a class="mini-button" id="tbLoginUser_Add" iconcls="icon-add" onclick="addCompany" plain="true">新增</a>
                            <a class="mini-button" id="tbLoginUser_Edit" iconcls="icon-edit" onclick="editCompany" plain="true">修改</a>
                            <span class="separator"></span>
                            <a class="mini-button" id="tbLoginUser_Remove" iconcls="icon-remove" onclick="remove" plain="true">删除</a>
                        </td>
                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field tbLoginUser_Query" id="comField" style="width:100px" data="[{id:'All',
                            text:'全部属性'}, {id:'userName',text:'用户姓名'},{id:'loginCode',text:'登录帐号'},{id:'companyName',text:'公司名称'},
                            {id:'roleName',text:'角色名称'}]" value="All" id="Field"></div>
                            <input class="mini-textbox Query_Field tbLoginUser_Query" style="width:150px" id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();"
                               id="tbLoginUser_Query">模糊搜索</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit" id="fitt">
                <div id="grid1" class="mini-datagrid" style="width: 100%; height: 100%;"
                     onrowdblclick="editCompany" showLoading="true"
                     allowresize="true" url="/loginUser/getData" multiselect="true"
                     pagesize="20"  sortfield="createTime" sortorder="desc" autoload="false">
                    <div property="columns">
                        <div type="indexcolumn" ></div>
                        <div type="checkcolumn"></div>
                        <div field="LoginCode" width="160" headeralign="center" allowsort="true"
                             align="center">登录帐号
                        </div>
                        <div field="UserName"  width="150" headeralign="center" align="center" allowsort="true">
                            用户姓名
                        </div>
                        <div field="RoleName" width="120" headeralign="center" allowsort="true"
                             align="center">角色名称</div>
                        <div field="CompanyName" width="200" headeralign="center" allowsort="true"
                             align="center">公司名称</div>
                        <div field="CreateMan" width="80" headeralign="center" type="treeselectcolumn"
                             allowsort="true" align="center">创建人
                            <input property="editor" class="mini-treeselect" valueField="FID" parentField="PID"
                                   textField="Name" url="/system/dep/getAllLoginUsersByDep" id="CreateMan" style="width:
                               98%;" required="true" resultAsTree="false"/>
                        </div>
                        <div field="LastLoginTime"  headeralign="center" allowsort="true" align="center"
                             dataType="date" dateFormat="yyyy-MM-dd HH:mm" width="150">最后登录时间</div>
                        <div field="LoginCount" headeralign="center" allowsort="true" align="center">登录次数</div>
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
        var cmdQuery=mini.get('#tbLoginUser_Query');
        var grid = mini.get('grid1');
        var tree=mini.get('tree1');
        function nodeClick(e){
            var node=e.node;
            if(tree.isLeaf(node)){
                var id=node.FID ;
                if(id)grid.load({CompanyID:id});
            }
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
                url:'/loginUser/add',
                width:'60%',
                height:'60%',
                title:'新增登录帐号',
                showModal:true,
                ondestroy:function(){
                    grid.reload();
                }
            });
        }
        function editCompany(){
            var row=grid.getSelected();
            if(!row){
                mini.alert('请选择要编辑的用户信息!');
                return ;
            }
            var UserID=row["UserID"];
            mini.open({
                url:'/loginUser/edit?UserID='+UserID,
                width:'60%',
                height:'60%',
                title:'编辑用户资料',
                showModal:true,
                onload:function(){
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.setData();
                },
                ondestroy:function(){
                    grid.reload();
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
    </script>
</@layout>