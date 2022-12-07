<#include "/shared/layout.ftl">
<@layout>
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
                            <a class="mini-button" id="tbDepList_Save" iconcls="icon-save" onclick="Save" plain="true">保存</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="mini-fit">
                <div id="tbDepList_treegrid" class="mini-treegrid" style="width:100%;height:100%"
                     treeColumn="sn" idField="id" parentField="pid" resultAsTree="false" editNextOnEnterKey="true"
                     allowCellValidate="true" url="/area/getData"
                     editNextRowCell="true" allowCellEdit="true" allowCellSelect="true" showTreeIcon="true" ondrawcell="renderCheck"
                     ondrop="ondrop" allowdrag="true" allowdrop="true" allowleafdropin="true" sortField="depId" sortOrder="asc"
                     expandOnload="false">
                    <div property="columns">
                        <div type="indexcolumn"></div>
                        <div id="sn" name="sn" field="sn" width="100" headeralign="center" vtype="required">编号
                            <input property="editor" class="mini-textbox" style=" width:100%"/>
                        </div>
                        <div name="name" field="name" width="200" headeralign="center" vtype="required">行政区划名称
                            <input property="editor" class="mini-textbox"/>
                        </div>
                        <div name="memo" field="memo" width="150" headeralign="center">备注
                            <input property="editor" class="mini-textarea"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/common/ImportManage.js"></script>
    <script type="text/javascript">
        mini.parse();
        var IsUsable = [{id: true, text: '可用'}, {id: false, text: '不可用'}];
        var treeGrid = null, TypeID = null, leftTree = null, CompanyID = null;
        $(function () {
            mini.parse();
            treeGrid = mini.get("tbDepList_treegrid");
            leftTree = mini.get('LeftTree');
        });

        function onLoad(e) {

        }

        function Refresh() {
            treeGrid.reload();
        }

        function addRoot() {
            var row = treeGrid.addNode({canUse: "true"});
            treeGrid.validateRow(row);
        }

        function addChild() {
            var node = treeGrid.getSelectedNode();
            if (!node) {
                mini.alert("请选择父节点!");
                return;
            }
            var pId = node.depId;
            var row = treeGrid.addNode({pid: pId, canUse: "true"}, 'add', node);
            treeGrid.validateRow(row);
        }

        function Save() {
            treeGrid.validate();
            var rows = treeGrid.getChanges();
            if (rows.length > 0) {
                function g() {
                    var iid = mini.loading('正在保存数据......');
                    var url = '/system/dep/saveAll?CompanyID=' + CompanyID;
                    $.ajax({
                        contentType: 'application/json',
                        method: 'post',
                        url: url,
                        data: mini.encode(rows),
                        success: function (r) {
                            mini.hideMessageBox(iid);
                            if (r['success']) {
                                mini.alert("办公室资料保存成功", "系统提示", function () {
                                    treeGrid.reload();
                                });
                            }
                            else mini.alert("保存失败，请稍后再试...");
                        },
                        failure: function (error) {
                            mini.hideMessageBox(iid);
                            mini.alert(error);
                        }
                    });
                }

                if (treeGrid.isValid() == true) {
                    g();
                } else mini.alert('数据录入不完整，不能进行保存!');
            } else mini.alert('没有可保存内容!');
        }

        function Delete() {
            var nodes = treeGrid.getSelecteds();
            if (nodes.length <= 0) {
                mini.alert('请选择要删除的数据！');
                return;
            }
            mini.confirm("确定要删除选中办公室数据吗？", "系统提示", function (action) {
                if (action == "ok") g(nodes);
            });

            function g(nodes) {
                var ids = [];
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i]["depId"]);
                }
                var iid = mini.loading('正在删除数据.....');
                var url = "/system/dep/removeAll";
                $.ajax({
                    contentType: 'application/json',
                    method: 'post',
                    url: url,
                    data: mini.encode(ids),
                    success: function (r) {
                        mini.hideMessageBox(iid);
                        if (r['success']) {
                            mini.alert("选择的办公室已删除成功！", '删除提示', function () {
                                treeGrid.reload();
                            });

                        } else mini.alert(r.message || "选择的办公室删除失败！");
                    },
                    failure: function (error) {
                        mini.hideMessageBox(iid);
                        mini.alert(error);
                    }
                });
            }
        }

        function renderCheck(a, b, c) {
            var val = a.value;
            var field = a.field;
            if (a.field == "canUse") {
                if (val == true || val == "true")
                    a.cellHtml = '<div style="color:green;text-align:center">可用</div>';
                else
                    a.cellHtml = '<div style="color:red;text-align:center">不可用</div>';
            }
        }

        function Search() {
            var SearchValue = mini.get('tbDepList_SearchValue').getValue();
            var p = new gridFilterHelper();
            p.setMatch("Name", SearchValue);
            treeGrid.load({"FilterParam": mini.encode(p.getResult())});
        }

        function Reset() {
            var value = mini.get("tbDepList_SearchValue");
            value.setValue("");
            Refresh();
        }

        function onClear() {
            treeGrid.deselectAll();
        }

        function lockGrid() {
            treeGrid.setAllowCellEdit(false);
        }

        function unLockGrid() {
            treeGrid.setAllowCellEdit(true);
        }

        function beforeOpen(e) {
            e.htmlEvent.preventDefault();
            var menu = e.sender;
            var node = treeGrid.getSelectedNode();
            var items = menu.getItems();
            for (var i = 0; i < items.length; i++) {
                var n = items[i];
                var name = n["name"] || "";
                var fun = (name == "Root" ? (node ? "hide" : "show") : (node ? "show" : "hide"));
                n[fun]();
            }
        }

        function ondrop(sender) {
            var nodes = sender.dragNodes || [];
            for (var i = 0; i < nodes.length; i++) {
                var node = nodes[i];
                var parentNode = treeGrid.getParentNode(node);
                var parentId = parentNode["depId"] || 0;
                node.pid = parentId;
            }
        }

        function addBefore() {
            var node = treeGrid.getSelectedNode();
            var pId = 0;
            if (node) pId = node.pid;
            treeGrid.addNode({pid: pId, canUse: "true"}, 'after', node);
        }

        function addAfter() {
            var node = treeGrid.getSelectedNode();
            var pId = 0;
            if (node) {
                pId = node.pid;
                treeGrid.expandPath(node);
            }
            treeGrid.addNode({pid: pId, canUse: "true"}, 'after', node);
        }

        function add() {
            var node = treeGrid.getSelectedNode();
            if (!node) {
                mini.alert("请选择父节点!");
                return;
            }
            var pId = node.depId;
            treeGrid.addNode({pid: pId, canUse: "true"}, 'add', node);
        }

        function expandAll() {
            treeGrid.expandAll();
        }

        function nodeClick() {
            var node=leftTree.getSelectedNode();
            if(node) {
                 var companyId=node.FID;
                 CompanyID = node.FID;
                 treeGrid.setUrl("/system/dep/getAll?CompanyID=" + companyId);
            }
        }
    </script>
</@layout>