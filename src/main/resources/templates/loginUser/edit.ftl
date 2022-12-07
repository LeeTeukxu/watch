<#include "/shared/layout.ftl">
<@layout>
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <script type="text/javascript" src="/js/clipboard.min.js"></script>
    <div class="mini-toolbar" id="mainToolbar" style="text-align:right;padding-right:25px;">
        <a class="mini-button" iconcls="icon-save" onclick="doSave()">保存</a>
    </div>
    <form id="mainForm">
        <table style="width: 100%; height: 100%" class="layui-table">
            <tr>
                <td>登录帐号：</td>
                <td>
                    <input class="mini-textbox" id="loginCode" name="loginCode" style="width: 100%;"
                           autocomplete="off" required="true" vtype="maxLenth:11,int"/>
                    <input class="mini-hidden" name="userId"/>
                    <input class="mini-hidden" name="myCode"/>
                </td>
                <td>登录密码：</td>
                <td>
                    <input class="mini-password" id="xPass" name="xPass" style="width: 100%;" autocomplete="off"
                           required="true" vtype="minLength:8"/>
                </td>
            </tr>
            <tr>
                <td>用户角色：</td>
                <td>
                    <input class="mini-treeselect" id="roleId" name="roleId" style="width: 100%;" required="true"
                           valueField="FID" parentField="PID" textField="Name" valueFromSelect="true"
                           resultAsTree="false" url="/myUser/getRoles" expandOnload="true"
                           onvaluechanged="onRoleChanged"/>
                </td>
                <td>是否登录：</td>
                <td>
                    <input class="mini-combobox" id="canLogin" name="canLogin" style="width: 100%;" required="true"
                           data="[{id:1,text:'可登录'},{id:0,text:'不可登录'}]" value="1"/>
                </td>
            </tr>
            <tr>
                <td>用户姓名：</td>
                <td>
                    <input class="mini-textbox" id="userName" name="userName" style="width: 100%;" required="true"/>
                </td>
                <td>电子邮箱：</td>
                <td>
                    <input class="mini-textbox" id="email" name="email" style="width: 100%;"/>
                </td>
            </tr>
            <tr id="myRow" style="display: <#if Mode=="Add">none</#if>">
                <td>邀请地址：</td>
                <td>
                    <input class="mini-textbox" id="myAddress" name="myAddress" style="width:70%;"
                           enabled="false"/>&nbsp;&nbsp;
                    <button class="mini-button" iconCls="icon-add" id="cmdCreate" onclick="createCode()">生成</button>
                    <button class="mini-button clip" iconCls="icon-cut" id="cmdCopy" onclick="copyCode()"
                            data-clipboard-target="#myAddress">复制
                    </button>
                </td>
                <td>负责区域：</td>
                <td>
                    <input class="mini-popupedit" name="areaId" style="width:100%;" textField="name"
                           valueField="id" popupWidth="400"
                           popup="#gridPanel" onhidepopup="onManHide" onshowpopup="onManShow" allowInput="false"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3">
                    <textarea style="height:380px;width:100%" name="memo" class="mini-textarea"></textarea>
                </td>
            </tr>
        </table>
    </form>
    <div id="gridPanel" class="mini-panel" title="选择人员" iconCls="icon-add" style="width:100%;height:300px;"
         visible="false"
         showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0" borderStyle="border:0">
        <div property="toolbar" style="padding:5px;padding-left:8px;">
            <input id="keyText" class="mini-textbox" style="width:80%;" emptyText="录入关键字，按回车键进行查询!" onenter="doQuery"/>
            <a class="mini-button" onclick="onClear()">重置</a>
        </div>
        <ul class="mini-tree" idField="id" parentField="pid" textField="name" name="areaId" id="areaTree"
            url="/area/getData" valueFromSelect="true" showCheckBox="true" resultAsTree="false"
            checkRecursive="false" autoCheckParent="false"
            multiSelect="true" style="width: 100%;"></ul>
    </div>
    <script type="text/javascript">
        mini.parse();
        var mode = "${Mode}";
        var mainForm = new mini.Form('#mainForm');
        var conDep = mini.get('#depId');
        var data = ${Data};
        var textCon = mini.get('#keyText');
        var tree = mini.get('#areaTree');
        var clipboard = new Clipboard('.clip', {
            target: function () {
                return mini.get('#myAddress').getEl()
            }
        });

        clipboard.on('success', function (e) {
            mini.showTips({content: '复制成功'});
        });

        clipboard.on('error', function (e) {
            mini.showTips(e);
        });

        function doSave() {
            mainForm.validate();
            if (mainForm.isValid()) {
                function g() {
                    var data = mainForm.getData();
                    var url = '/myUser/save';
                    $.post(url, {Data: mini.encode(data)}, function (result) {
                        if (result.success) {
                            mini.alert('保存成功!', '系统提示', function () {
                                var data = result.data || {};
                                data.xPass = data.xpass;
                                if (data.canLogin == true) data.canLogin = 1; else data.canLogin = 0;
                                mainForm.setData(data);
                                if (!data.myCode) {
                                    mini.get('#cmdCreate').show();
                                    mini.get('#cmdCopy').hide();
                                } else {
                                    mini.get('#cmdCreate').hide();
                                    mini.get('#cmdCopy').show();
                                }
                                $('#myRow').show();
                                mini.get('#myAddress').setWidth('81%');
                            });
                        } else mini.alert(result.message || "保存失败，请稍候重试!");
                    })
                }

                g();
            } else mini.alert('登录用户信息录入不完整，不能进行保存。');
        }

        function setData() {
            data.xPass = data.xpass;
            if (data.canLogin == true) data.canLogin = 1; else data.canLogin = 0;
            mainForm.setData(data);
            $('#myRow').show();
            if (data.myCode) {
                mini.get('#cmdCreate').hide();
                mini.get('#cmdCopy').show();
                mini.get('#myAddress').setWidth('81%');
            } else {
                mini.get('#cmdCreate').show();
                mini.get('#cmdCopy').hide();
                mini.get('#myAddress').setWidth('81%');
            }
            var areaCon = mini.getbyName('areaId');
            if (areaCon) {
                areaCon.setValue(data.areaId);
                areaCon.setText(data.areaName);
            }
        }

        function createCode() {
            var userId = mini.getbyName('userId').getValue();
            var url = '/myUser/createCode';
            $.post(url, {UserID: userId}, function (result) {
                if (result.success) {
                    mini.alert('你的邀请码已生成成功，企业用户可以使用邀请地址，用企业信用代码做为用户名，123456做为密码登录系统!', '邀请码生成成功', function () {
                        mini.get('#cmdCreate').hide();
                        mini.get('#myAddress').setValue(result.data);
                        mini.get('#myAddress').setWidth('81%');
                        mini.get('#cmdCopy').show();
                    });
                } else {
                    mini.alert(result.message || "生成失败，请稍候重试!");
                }
            })
        }

        function copyCode() {

        }

        function onManHide(e) {
            var areaCon = mini.getbyName('areaId');
            tree.clearFilter();
            var nodes = [];
            tree.cascadeChild(null, function (node) {
                var bb = tree.isCheckedNode(node);
                if (bb == true) nodes.push(node);
            });
            var ids = [];
            var texts = [];
            for (var i = 0; i < nodes.length; i++) {
                var node = nodes[i];
                texts.push(node.name);
                ids.push(node.id);
            }
            if (ids.length > 0) {
                areaCon.setValue(ids.join(','));
                areaCon.setText(texts.join(','));
            } else {
                areaCon.setValue(null);
                areaCon.setText(null);
            }
        }

        function onManShow(e) {
            var dd = (e.sender.getValue() || "");
            var cs = dd.split(',');

            tree.cascadeChild(null, function (node) {
                var name = node.name || "";
                var id = (node.id || "0").toString();
                var firstIndex = cs.indexOf(name);
                if (firstIndex < 0) firstIndex = cs.indexOf(id);
                if (firstIndex > -1) tree.checkNode(node); else tree.uncheckNode(node);
            });
        }

        function doQuery() {
            var text = textCon.getValue();
            if (text) {
                tree.filter(function (node) {
                    return (node.name || "").toString().indexOf(text) > -1;
                });
            } else tree.clearFilter();
        }

        function onClear() {
            textCon.setValue(null);
            tree.clearFilter();
        }

        function onRoleChanged(e) {
            var areaCon = mini.getbyName('areaId');
            var roleId = parseInt(e.value);
            if (roleId == 6) {
                areaCon.setEnabled(false);
                areaCon.setValue(null);
                areaCon.setText(null);
            } else {
                areaCon.setEnabled(true);
            }
        }
    </script>
</@layout>