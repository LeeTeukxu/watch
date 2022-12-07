<#include "/shared/layout.ftl">
<@layout>
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <div class="mini-toolbar" id="mainToolbar" style="text-align:right;padding-right:25px;">
        <a class="mini-button" iconcls="icon-save" onclick="doSave()">保存</a>
    </div>
    <form id="mainForm">
        <table style="width: 100%; height: 100%" class="layui-table">
            <tr>
                <td>登录帐号：</td>
                <td>
                    <input class="mini-textbox" id="loginCode"  name="loginCode" style="width: 100%;"
                           autocomplete="off" required="true" vtype="maxLenth:11,int"/>
                    <input class="mini-hidden" name="userId"/>
                </td>
                <td>登录密码：</td>
                <td>
                    <input class="mini-password" id="xPass"  name="xPass" style="width: 100%;"  autocomplete="off"
                           required="true" vtype="minLength:8"/>
                </td>
            </tr>
            <tr>
                <td>用户角色：</td>
                <td>
                    <input class="mini-treeselect" id="roleId" name="roleId" style="width: 100%;" required="true"
                           valueField="FID" parentField="PID" textField="Name" valueFromSelect="true"
                           resultAsTree="false" url="/loginUser/getRoles" expandOnload="true"/>
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
            <tr>
                <td>所属公司：</td>
                <td>
                    <input class="mini-treeselect" id="companyId" valueField="FID" parentField="PID" textField="Name"
                           name="companyId"  valueFromSelect="true" expandOnload="true" url="/loginUser/getCompanyList"
                           style="width: 100%;" resultAsTree="false" required="true" onvaluechanged="onCompanyChange"/>
                </td>
                <td>所属部门：</td>
                <td>
                    <input class="mini-treeselect" id="depId" name="depId"  valueField="FID" parentField="PID"
                           textField="Name" valueFromSelect="true" style="width: 100%;"
                           url="/loginUser/getDepList?CompanyID=${CompanyID}" expandOnload="true"
                           required="true"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="3">
                    <textarea style="height:80px;width:100%" name="memo" class="mini-textarea"></textarea>
                </td>
            </tr>
        </table>
    </form>
    <script type="text/javascript">
        mini.parse();
        var mode = "${Mode}";
        var mainForm=new mini.Form('#mainForm');
        var conDep=mini.get('#depId');
        var data=${Data};
        function doSave(){
            mainForm.validate();
            if(mainForm.isValid()){
                function g(){
                    var data =mainForm.getData();
                    var url='/loginUser/save';
                    $.post(url,{Data:mini.encode(data)},function(result){
                        if(result.success){
                            mini.alert('保存成功!','系统提示',function(){
                                CloseOwnerWindow('ok');
                            });
                        }else mini.alert(result.message || "保存失败，请稍候重试!");
                    })
                }
                g();
            } else mini.alert('登录用户信息录入不完整，不能进行保存。');
        }
        function onCompanyChange(e){
            var companyId=parseInt(e.value || 0);
            conDep.load('/loginUser/getDepList?CompanyID='+companyId);
        }
        function setData(){
            mainForm.setData(data);
        }
    </script>
</@layout>