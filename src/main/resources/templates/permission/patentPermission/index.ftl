<#include "/shared/layout.ftl">
<@layout>
    <div class="mini-toolbar" id="tbRoleClass_Toolbar">
        <a class="mini-button" iconcls="icon-save" onclick="save" plain="true" id="tbRoleClass_Save">保存</a>
    </div>
    <div class="mini-fit">
        <div id="tbRoleClass_treegrid" class="mini-treegrid" style="width: 100%; height: 100%;"
             allowresize="true" url="/patentPermission/getData" showtreeicon="true" treecolumn="sn"
             idfield="userId" parentfield="pid" resultastree="false"
             allowcelledit="true" allowcellselect="true"  autoload="true" expandOnload="true">
            <div property="columns">
                <div type="indexcolumn"></div>
                <div name="sn" field="sn" width="20%" headeralign="center">编号</div>
                <div name="userName" field="userName" width="40%" headeralign="center">用户名称</div>
                <div field="areaIds" type="treeselectcolumn" width="40%" headeralign="center">负责区域
                    <input property="editor" class="mini-treeselect"  idField="id" parentField="pid" textField="name"
                           url="/area/getData" allowInput="true" valueFromSelect="true" showFolderCheckBox="true"
                           multiSelect="true"/>
                </div>
            </div>
        </div>
    </div>
</@layout>
<@js>
    <script type="text/javascript">
        var treeGrid = null;
        $(function () {
            mini.parse();
            treeGrid = mini.get('tbRoleClass_treegrid');
        });
        function save() {
            var rows =mini.clone(treeGrid.getAllChildNodes());
            var url = '/patentPermission/saveAll';
            $.post(url,{Data:mini.encode(rows)},function(result){
                if(result.success){
                    mini.alert('设置保存成功!');
                } else mini.alert(result.message || "保存失败，请稍候重试!");
            })
        }
    </script>
</@js>