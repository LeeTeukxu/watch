<#include "/shared/layout.ftl">
<@layout>
    <div class="mini-toolbar" id="tbRoleClass_Toolbar">
        <a class="mini-button" iconCls="icon-add" onclick="Features_Add" id="Features_Add">增加</a>
        <a class="mini-button" iconcls="icon-save" onclick="Features_Save" plain="true" id="Features_Save">保存</a>
        <a class="mini-button" iconcls="icon-remove" plain="true" onclick="Features_Remove" id="Features_Remove">删除</a>
    </div>
    <div class="mini-fit">
        <div class="mini-fit" style="width: 100%;" id="fitt">
            <div class="mini-datagrid" id="grid1" style="width:100%;height:100%" allowcellselect="true" allowcelledit="true"
                 url="/permission/features/getAll" allowCellWrap="true" sortField="FID" sortOrder="asc" oncellbeginedit="beforeEdit" allowCellWrap="true"
                 pagesize="50" sizelist="[5,10,20,50,100,150,200]" autoload="true" onselect="onSelectRow">
                <div property="columns">
                    <div type="indexcolumn"></div>
                    <div field="Name" width="300"  align="center" headerAlign="center">
                        功能标识
                        <div property="editor" class="mini-textarea" style="width:100%;height:60px"></div>
                    </div>
                    <div field="Title" width="300"  align="center" headerAlign="center">
                        功能名称
                        <div property="editor" class="mini-textarea" style="width:100%;height:60px"></div>
                    </div>
                    <div name="CanUse" field="CanUse" type="comboboxcolumn" width="120" headeralign="center">是否可用
                        <input property="editor" class="mini-combobox" data="IsUsable"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@layout>
<@js>
    <script type="text/javascript">
        mini.parse();
        var IsUsable = [{ id: true, text: '可用' }, { id: false, text: '不可用'}];
        var grid = mini.get('grid1');

        function Features_Add() {
            grid.addRow({CanUse:"true"});
        }

        function Features_Save() {
            var rows = grid.getChanges();
            var rs=[];
            for (var i = 0; i < rows.length; i++) {
                var row=mini.clone(rows[i]);
                delete row._id;
                delete row._uid;
                delete row._state;
                rs.push(row);
            }
            var url = '/permission/features/save';
            $.post(url, { Data: mini.encode(rs) }, function (r) {
                if (r.success) {
                    mini.alert('保存成功。');
                    grid.reload();
                }
                else {
                    var msg = r.message || "保存失败";
                    mini.alert(msg);
                }
            });
        }

        function Features_Remove() {
            var changes = grid.getChanges();
            if (changes.length > 0) {
                alert('删除数据之前，请先保存更改。');
                return;
            }
            var row = grid.getSelected();
            if (row) {
                var fu = function () {
                    var fid = row["FID"];
                    var url = '/permission/features/remove';
                    $.post(url, {FID: fid }, function (r) {
                        if (r.success) {
                            mini.alert('选择记录删除成功!');
                            grid.reload();
                        }
                        else {
                            var msg = r.message || "删除失败，请稍候重试。";
                            mini.alert(msg);
                        }
                    });
                };
                mini.confirm('是否确认删除选择的记录。', '删除提示', function (r) {
                    if (r == "ok") fu();
                });
            }
        }
        function beforeEdit(e) {

        }
        function onSelectRow() {

        }
    </script>
</@js>
