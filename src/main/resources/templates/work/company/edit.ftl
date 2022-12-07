<#include "/shared/layout.ftl">
<@layout>
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
    <script type="text/javascript" src="/js/common/complexExcelExport.js"></script>
    <script type="text/javascript" src="/js/common/downLoadDJFQDExcel.js"></script>
    <script type="text/javascript">
        var types = [
            {id: 1, text: '企业'},
            {id: 2, text: '机关'},
            {id: 3, text: '学校'},
            {id: 4, text: '个人'},
            {id: 5, text: '其它'}
        ];
    </script>
    <div class="mini-toolbar" id="mainToolbar" style="text-align:right;padding-right:25px;">
        <a class="mini-button" iconcls="icon-save" id="CompanyEdit_Save">保存</a>
    </div>
    <form id="companyForm" action="/" method="post" style="display: none">
        <table style="width: 100%; height: 100%" class="layui-table">
            <tr>
                <td style="padding-left:25px;position: relative;" title="保存后不能更改！"><span
                            class="mini-button-icon mini-iconfont icon-help "></span>公司名称：
                </td>
                <td>
                    <input class="mini-textbox" id="name" name="name" required="true" style="width: 100%;"/>
                    <input class="mini-hidden" id="id" name="id"/>
                    <input class="mini-hidden" id="createMan" name="createMan"/>
                    <input class="mini-hidden" id="createTime" name="createTime"/>
                </td>
                <td>公司类型：</td>
                <td>
                    <input class="mini-combobox" id="type" name="type" style="width: 100%;" value="1" data="types"
                           required="true"/>
                </td>
                <td>地址：</td>
                <td>
                    <input class="mini-textbox" id="address" name="address" style="width: 100%;"/>
                </td>
            </tr>
            <tr>
                <td>联系方式：</td>
                <td>
                    <input class="mini-textbox" id="linkPhone" name="linkPhone" style="width: 100%;"/>
                </td>
                <td>联系人：</td>
                <td>
                    <input class="mini-textbox" id="linkMan" name="linkMan" style="width: 100%;"/>
                </td>
            </tr>
            <tr>
                <td>备注</td>
                <td colspan="7">
                    <textarea class="mini-textarea" style="width:100%;height:50px" name="memo"></textarea>
                </td>
            </tr>
        </table>
    </form>
    <script type="text/javascript" src="/js/work/feeItem/FeeCommon.js"></script>
    <script type="text/javascript">

        mini.parse();
        var mode = "${Mode}";
        var AllCompany = ${AllCompany};

        var formData = ${LoadData};
        var toolbar = mini.get('#mainToolbar');

        $(function () {
            var form = new mini.Form('#companyForm');
            $("#CompanyEdit_Save").click(function () {
                var Data = form.getData();
                form.validate();
                if (form.isValid()) {
                    if (mode == "Add") {
                        var CompanyName = mini.get('name').getValue() || '';
                        for (var p in AllCompany) {
                            if (CompanyName == AllCompany[p].Name) {
                                mini.alert('公司名称已存在，清选择编辑公司或者重新输入公司名称！');
                                return;
                            }
                        }
                    }
                    form.loading("保存中......");
                    var arg = {
                        mode: mode,
                        company: mini.encode(Data)
                    };

                    $.post("/work/company/save", arg,
                        function (result) {
                            var res = mini.decode(result);
                            if (res['success']) {
                                mini.alert('公司信息保存成功', '系统提示', function () {
                                    var returnData = result.data || {};
                                    form.setData(returnData);
                                });
                            } else {
                                mini.alert(res.message || "保存失败，请稍候重试!");
                            }
                            form.unmask();
                        }
                    );
                }
            });
           $('#companyForm').show();
        });

        if (mode == "look") {
            $("#mainToolbar").remove();
            var mainForm = new mini.Form('#companyForm');
            mainForm.setEnabled(false);
        }
        if (mode == "Edit") {
            // mini.get("name").disable();
            var form = new mini.Form('#companyForm');
            formData.signDate = new Date(formData.signDate);
            formData.createTime = new Date(formData.createTime);
            form.setData(formData);
        } else {
            if (formData != null && formData != undefined) {
                var form = new mini.Form('#companyForm');
                formData.Type = 1;
                form.setData(formData);
            }
        }
    </script>
</@layout>