<#include "/shared/layout.ftl">
<@layout>
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <script type="text/javascript">
        var PayStates = [
            { id: 1, text: '待支付'},
            { id: 2, text: '支付成功'},
            { id: 3, text: '支付失败'},
        ];

        var DjStates = [
            { id: 1, text: '未支付'},
            { id: 2, text: '代缴中'},
            { id: 3, text: '代缴完成'},
        ];
    </script>
    <div style="height: 100%;overflow: hidden;">
        <div class="mini-toolbar" id="minitoolbar">
            <table style="width:100%">
                <tr>
                    <td style="width:95%">
                        <a class="mini-button" id="OrderList_Remove" iconcls="icon-remove" onclick="Remove" plain="true">删除</a>
                        <a class="mini-button" id="OrderList_WxChatOrderCompleteDJ" iconcls="icon-ok" onclick="ComplateDJ" plain="true">完成代缴</a>
                        <a class="mini-button" id="OrderList_Statistic" iconcls="icon-collapse" onclick="Statistic" plain="true">统计</a>
                    </td>

                    <td style="white-space:nowrap;">
                        <div class="mini-combobox Query_Field OrderList_Query" id="comField" style="width:100px"
                             data="[{id:'All',text:'全部属性'},{id:'OrderNo',text:'订单号'},{id:'AppNo',text:'专利号'},{id:'PayUser',text:'付款人'},{id:'Bank_Type',text:'付款银行'},
                                     {id:'ClientName',text:'客户名称'},{id:'FAMINGMC',text:'专利名称'},{id:'FEENAME',text:'费用名称'},{id:'PState',text:'支付状态'},{id:'DState',text:'代缴状态'}]" value="All"
                             id="Field"></div>
                        <input class="mini-textbox Query_Field OrderList_Query" style="width:150px"
                               id="QueryText"/>
                        <a class="mini-button mini-button-success" onclick="doQuery('OrderList_datagrid','comField','QueryText');" id="OrderList_Query">模糊搜索</a>
                        <a class="mini-button mini-button-danger" id="OrderList_Reset" onclick="reset('highQueryForm','OrderList_datagrid','QueryText','comField')">重置</a>
                        <a class="mini-button" id="OrderList_HighQuery" iconCls="icon-expand"
                           onclick="expand('OrderList_HighQuery','QueryText','comField','p1','fitt')">高级查询</a>
                    </td>
                </tr>
            </table>
            <div id="p1" style="border:1px solid #909aa6;border-top:0;height:172px;padding:5px;display:none">
                <table style="width:100%;" id="highQueryForm">
                    <tr>
                        <td style="width:6%;padding-left:10px;">订单号：</td>
                        <td style="width:15%;">
                            <input name="OrderNo" data-oper="LIKE" style="width:100%"  class="mini-textbox"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">订单时间：</td>
                        <td style="width:15%;">
                            <input name="OrderTime" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">到：</td>
                        <td style="width:15%;">
                            <input name="OrderTime" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">专利号：</td>
                        <td style="width:15%;">
                            <input name="AppNo" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:6%;padding-left:10px;">代缴官费：</td>
                        <td style="width:15%;">
                            <input name="Amount" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">服务费：</td>
                        <td style="width:15%;">
                            <input name="ServiceCharge" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">代缴金额(总)：</td>
                        <td style="width:15%;">
                            <input name="OrderAmountTotal" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">客户名称：</td>
                        <td style="width:15%;">
                            <input name="ClientName" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:6%;padding-left:10px;">专利名称：</td>
                        <td style="width:15%;">
                            <input name="FAMINGMC" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">费用名称：</td>
                        <td style="width:15%;">
                            <input name="FEENAME" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">应缴金额：</td>
                        <td style="width:15%;">
                            <input name="DetAmount" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">付款银行：</td>
                        <td style="width:15%;">
                            <input name="Bank_Type" class="mini-textbox" data-oper="LIKE" style="width:100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:6%;padding-left:10px;">缴费截止日期：</td>
                        <td style="width:15%;">
                            <input name="LimitDate" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">到：</td>
                        <td style="width:15%;">
                            <input name="LimitDate" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">支付完成日期：</td>
                        <td style="width:15%;">
                            <input name="Time_End" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                        </td>
                        <td style="width:6%;padding-left:10px;">到：</td>
                        <td style="width:15%;">
                            <input name="Time_End" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:6%;padding-left:10px;">支付状态：</td>
                        <td style="width:15%;"><input class="mini-combobox" data="PayStates"  name="PayState" data-oper="EQ"
                                                      style="width:100%" /></td>
                        <td style="width:6%;padding-left:10px;">代缴状态：</td>
                        <td style="width:15%;"><input class="mini-combobox" data="DjStates"  name="DjState" data-oper="EQ"
                                                      style="width:100%" /></td>
                    </tr>
                    <tr>
                        <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                            <button class="mini-button mini-button-success OrderList_Query" style="width:120px"
                                    onclick="doHightSearch('OrderList_datagrid','highQueryForm')">搜索
                            </button>
                            <button class="mini-button mini-button-danger OrderList_Query" style="width:120px"
                                    onclick="expand('OrderList_HighQuery','QueryText','comField','p1','fitt')">收起
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="mini-fit">
            <div id="tabs1" class="mini-tabs" style="width:100%;height:97%;"
                 bodystyle="padding:0;border:0;border-bottom: 1px solid rgb(210, 210, 210);" activeindex="0" >
                <div name="AllTab" title="全部订单" id="AllTab">
                    <div class="mini-fit">
                        <div id="OrderList_datagridOne" class="mini-datagrid" style="width:100%;height:100%;"
                             allowresize="true" frozenstartcolumn="0" frozenendcolumn="5" url="/WeChatOrder/getData?ListType=ALL" multiselect="true"
                             pagesize="10" sizelist="[5,10,20,50,100,150,200]" sortfield="OrderTime" sortorder="desc"
                             autoload="true" ondrawcell="draw" onload="afterload">
                            <div property="columns">
                                <div type="checkcolumn"></div>
                                <div type="indexcolumn"></div>
                                <div field="OrderNo" width="140" headerAlign="center" align="center" allowSort="true">订单号</div>
                                <div field="OrderTime" width="170" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">订单时间</div>
                                <div field="DetailNum" width="100" headeralign="center" align="center">缴费数量</div>
                                <div field="OrderDetail" width="100" headeralign="center" align="center">订单详情</div>
                                <#--                                <div field="AllCLIENTNAME" width="150" headerAlign="center" align="center" allowSort="true">客户名称</div>-->
                                <#--                                <div field="AllFMMC" width="150" headerAlign="center" align="center" allowSort="true">专利名称</div>-->
                                <#--                                <div field="AllFEENAME" width="150" headerAlign="center" align="center" allowSort="true">费用名称</div>-->
                                <div field="Amount" width="20" headerAlign="center" align="center">代缴官费</div>
                                <div field="ServiceCharge" width="20" headerAlign="center" align="center" allowSort="true">服务费</div>
                                <div field="OrderAmountTotal" width="20" headerAlign="center" align="center" allowSort="true">代缴金额(总计)</div>
                                <div field="AfterPriceTotal" width="20" headerAlign="center" align="center" allowSort="true">共计节省金额</div>
                                <div field="UserID" width="30" headerAlign="center" align="center" type="treeselectcolumn">付款人
                                    <input property="editor" class="mini-treeselect" url="/system/dep/getAllLoginUsersByDep"
                                           textField="Name" valueField="FID" parentField="PID"/>
                                </div>
                                <div field="Bank_Type" width="30" headerAlign="center" align="center" allowSort="true">付款银行</div>
                                <div field="Time_End" width="30" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">支付完成日期</div>
                                <div field="State" width="20" headerAlign="center" align="center" allowSort="true">订单状态</div>
                                <div field="Oper" name="Oper" width="20" headerAlign="center" align="center" >操作</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div name="DZFTab" title="待支付" id="DZFTab">
                    <div class="mini-fit">
                        <div id="OrderList_datagridTwo" class="mini-datagrid" style="width:100%;height:100%;"
                             allowresize="true" url="" multiselect="true" frozenstartcolumn="0" frozenendcolumn="6"
                             pagesize="10" sizelist="[5,10,20,50,100,150,200]" sortfield="OrderTime" sortorder="desc"
                             autoload="false" ondrawcell="draw" onload="afterload">
                            <div property="columns">
                                <div type="checkcolumn"></div>
                                <div type="indexcolumn"></div>
                                <div field="OrderNo" width="170" headerAlign="center" align="center" allowSort="true">订单号</div>
                                <div field="OrderTime" width="200" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">订单时间</div>
                                <div field="DetailNum" width="120" headeralign="center" align="center">缴费数量</div>
                                <div field="OrderDetail" width="100" headeralign="center" align="center">订单详情</div>
                                <#--                                <div field="AllCLIENTNAME" width="150" headerAlign="center" align="center" allowSort="true">客户名称</div>-->
                                <#--                                <div field="AllFMMC" width="150" headerAlign="center" align="center" allowSort="true">专利名称</div>-->
                                <#--                                <div field="AllFEENAME" width="150" headerAlign="center" align="center" allowSort="true">费用名称</div>-->
                                <div field="Amount" width="100" headerAlign="center" align="center">代缴官费</div>
                                <div field="ServiceCharge" width="30" headerAlign="center" align="center" allowSort="true">服务费</div>
                                <div field="OrderAmountTotal" width="30" headerAlign="center" align="center" allowSort="true">代缴金额(总计)</div>
                                <div field="AfterPriceTotal" width="20" headerAlign="center" align="center" allowSort="true">共计节省金额</div>
                                <div field="State" width="50" headerAlign="center" align="center" allowSort="true">订单状态</div>
                                <div field="Oper" name="Oper" width="50" headerAlign="center" align="center" >操作</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div name="DJZTab" title="代缴中" id="DJZTab">
                    <div class="mini-fit">
                        <div id="OrderList_datagridThree" class="mini-datagrid" style="width:100%;height:100%;"
                             allowresize="true" frozenstartcolumn="0" frozenendcolumn="5" multiselect="true"
                             pagesize="10" sizelist="[5,10,20,50,100,150,200]" sortfield="OrderTime" sortorder="desc"
                             autoload="true" ondrawcell="draw" onload="afterload">
                            <div property="columns">
                                <div type="checkcolumn"></div>
                                <div type="indexcolumn"></div>
                                <div field="OrderNo" width="140" headerAlign="center" align="center" allowSort="true">订单号</div>
                                <div field="OrderTime" width="170" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">订单时间</div>
                                <div field="DetailNum" width="100" headeralign="center" align="center">缴费数量</div>
                                <div field="OrderDetail" width="100" headeralign="center" align="center">订单详情</div>
                                <#--                                <div field="AllCLIENTNAME" width="150" headerAlign="center" align="center" allowSort="true">客户名称</div>-->
                                <#--                                <div field="AllFMMC" width="150" headerAlign="center" align="center" allowSort="true">专利名称</div>-->
                                <#--                                <div field="AllFEENAME" width="150" headerAlign="center" align="center" allowSort="true">费用名称</div>-->
                                <div field="Amount" width="20" headerAlign="center" align="center">代缴官费</div>
                                <div field="ServiceCharge" width="20" headerAlign="center" align="center" allowSort="true">服务费</div>
                                <div field="OrderAmountTotal" width="20" headerAlign="center" align="center" allowSort="true">代缴金额(总计)</div>
                                <div field="AfterPriceTotal" width="20" headerAlign="center" align="center" allowSort="true">共计节省金额</div>
                                <div field="UserID" width="30" headerAlign="center" align="center" type="treeselectcolumn">付款人
                                    <input property="editor" class="mini-treeselect" url="/system/dep/getAllLoginUsersByDep"
                                           textField="Name" valueField="FID" parentField="PID"/>
                                </div>
                                <div field="Bank_Type" width="30" headerAlign="center" align="center" allowSort="true">付款银行</div>
                                <div field="Time_End" width="30" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">支付完成日期</div>
                                <div field="State" width="20" headerAlign="center" align="center" allowSort="true">订单状态</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div name="DJWCTab" title="代缴完成$" id="DJWCTab">
                    <div class="mini-fit">
                        <div id="OrderList_datagridFour" class="mini-datagrid" style="width:100%;height:100%;"
                             allowresize="true" frozenstartcolumn="0" frozenendcolumn="5" multiselect="true"
                             pagesize="10" sizelist="[5,10,20,50,100,150,200]" sortfield="OrderTime" sortorder="desc"
                             autoload="true" ondrawcell="draw" onload="afterload">
                            <div property="columns">
                                <div type="checkcolumn"></div>
                                <div type="indexcolumn"></div>
                                <div field="OrderNo" width="140" headerAlign="center" align="center" allowSort="true">订单号</div>
                                <div field="OrderTime" width="170" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">订单时间</div>
                                <div field="DetailNum" width="100" headeralign="center" align="center">缴费数量</div>
                                <div field="OrderDetail" width="100" headeralign="center" align="center">订单详情</div>
                                <#--                                <div field="AllCLIENTNAME" width="150" headerAlign="center" align="center" allowSort="true">客户名称</div>-->
                                <#--                                <div field="AllFMMC" width="150" headerAlign="center" align="center" allowSort="true">专利名称</div>-->
                                <#--                                <div field="AllFEENAME" width="150" headerAlign="center" align="center" allowSort="true">费用名称</div>-->
                                <div field="Amount" width="20" headerAlign="center" align="center">代缴官费</div>
                                <div field="ServiceCharge" width="20" headerAlign="center" align="center" allowSort="true">服务费</div>
                                <div field="OrderAmountTotal" width="20" headerAlign="center" align="center" allowSort="true">代缴金额(总计)</div>
                                <div field="AfterPriceTotal" width="20" headerAlign="center" align="center" allowSort="true">共计节省金额</div>
                                <div field="UserID" width="30" headerAlign="center" align="center" type="treeselectcolumn">付款人
                                    <input property="editor" class="mini-treeselect" url="/system/dep/getAllLoginUsersByDep"
                                           textField="Name" valueField="FID" parentField="PID"/>
                                </div>
                                <div field="Bank_Type" width="30" headerAlign="center" align="center" allowSort="true">付款银行</div>
                                <div field="Time_End" width="30" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">支付完成日期</div>
                                <div field="State" width="20" headerAlign="center" align="center" allowSort="true">订单状态</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        mini.parse();
        var TabIndexs = "One";
        var RoleName = "${RoleName}";
        var FeeWaitNum = "";
        var tip = new mini.ToolTip();

        var grid1 = mini.get('OrderList_datagridOne');
        var grid2 = mini.get('OrderList_datagridTwo');
        var grid3 = mini.get('OrderList_datagridThree');
        var grid4 = mini.get('OrderList_datagridFour');
        $(function () {
            var tabs = mini.get("tabs1");
            var btnRemove = mini.get("OrderList_Remove");
            var btnComplete = mini.get("OrderList_WxChatOrderCompleteDJ")
            btnComplete.hide();
            tabs.on("activechanged", function (e) {
                currentTabTitle = e.tab.title;
                if (currentTabTitle == "全部订单${ALL}") {
                    btnRemove.show();
                    btnComplete.hide();
                }else if (currentTabTitle == "待支付${DZF}") {
                    TabIndexs = "Two";
                    btnRemove.show();
                    btnComplete.hide();
                    var grid = mini.get('OrderList_datagridTwo');


                    if (grid.getUrl() == '') {
                        grid.setUrl('/WeChatOrder/getData');
                        grid.load({ListType: "DZF"});
                    }
                }else if (currentTabTitle == "代缴中${DJZ}") {
                    TabIndexs = "Three";
                    btnRemove.hide();
                    if (RoleName == "财务人员" || RoleName == "系统管理员") {
                        btnComplete.show();
                    }else btnComplete.hide();
                    var grid = mini.get('OrderList_datagridThree');
                    if (grid.getUrl() == '') {
                        grid.setUrl('/WeChatOrder/getData');
                        grid.load({ListType: "DJZ"});
                    }
                }else if (currentTabTitle == "代缴完成${DJWC}") {
                    TabIndexs = "Four";
                    btnRemove.hide();
                    btnComplete.hide();
                    var grid = mini.get('OrderList_datagridFour');
                    if (grid.getUrl() == '') {
                        grid.setUrl('/WeChatOrder/getData');
                        grid.load({ListType: "DJWC"});
                    }
                }
            });
            tabs.show();
            setTabTitle(${ALL}, ${DZF}, ${DJZ}, ${DJWC});
        });
        function draw(e) {
            var record = e.record;
            var field = e.field;
            if (field == "Oper") {
                var PayState = record["PayState"];
                var html = "";
                if (PayState == "1" || PayState == "3"){
                    // e.cellHtml = '<button type="button" class="btn btn-primary" onclick=ViewOrderDetail("'+record["OrderNo"]+'")>订单详情</button>&nbsp;<button type="button" class="btn btn-danger" onclick=OrderPayment("'+record["OrderNo"]+'")>去支付</button>';
                    e.cellHtml = '<a href="#" style="color: #d9534f" onclick=OrderPayment("'+record["OrderNo"]+'")>去支付</a>'
                }
            }
            if (field == "State") {
                var DjState = record["DjState"];
                var PayState = record["PayState"];
                var payStateHtml = "";
                var djStatesHtml = "";

                if (PayState == "1"){
                    payStateHtml = '<span style="color: #337ab7">待支付&nbsp;</span>';
                }else if(PayState == "2") {
                    payStateHtml = '<span style="color: #337ab7">支付成功&nbsp;</span>';
                }else if (PayState == "3") {
                    payStateHtml = '<span style="color: #337ab7">支付失败&nbsp;</span>';
                }

                if (PayState == "1"){
                    djStatesHtml = '<span style="color: #d9534f">未代缴</span>';
                }else if (DjState == "2"){
                    djStatesHtml = '<span style="color: #d9534f">代缴中</span>';
                }else if (DjState == "3"){
                    djStatesHtml = '<span style="color: #d9534f">代缴完成</span>';
                }

                e.cellHtml = payStateHtml + djStatesHtml;
            }
            if (field == "DetailNum") {
                var DetailNum = record["DetailNum"];
                e.cellHtml = DetailNum + "条缴费信息"
            }
            if (field == "OrderDetail") {
                e.cellHtml = '<a href="javascript:void(0)" style="color: #337ab7" onclick=ViewOrderDetail("'+record["OrderNo"]+'")>查看</a>';
            }
            if (field == "AfterPriceTotal") {
                var AfterPriceTotal = record["AfterPriceTotal"];
                if (AfterPriceTotal != 0) {
                    e.cellHtml = '<a href="javascript:void(0)"  data-placement="bottomleft"  code="' + record["OrderNo"] + '" class="showCellTooltip" style="color: #337ab7">' + AfterPriceTotal + '</a>';
                }else e.cellHtml = '';
            }
        }

        function ViewOrderDetail(OrderNo) {
            mini.open({
                url: '/WeChatOrder/orderdetails?OrderNo=' + OrderNo,
                showModal: true,
                width: '100%',
                height: '100%',
                allowResize:false,
                title: "订单明细",
                onDestroy: function () {

                }
            });
        }

        function OrderPayment(OrderNo) {
            var url = "/WeChatOrder/orderpayment?OrderNo=" + OrderNo;
            mini.open({
                url: url,
                title: "官费订单",
                width: "100%",
                height: "100%",
                onload: function () {

                },
                ondestroy: function (action) {
                    //刷新表格
                    var argDZF = {};
                    var argDJZ = {};
                    var argDJWC = {};
                    argDZF["ListType"] = "DZF";
                    argDJZ["ListType"] = "DJZ";
                    argDJWC["ListType"] = "DJWC"
                    ResetGrid({}, argDZF, argDJZ, argDJWC);
                    ReloadGovFeeWaitNum({});
                }
            });
        }

        function ComplateDJ() {
            var grids = mini.get("OrderList_datagrid" + TabIndexs);
            var rows = grids.getSelecteds();
            var IDS = [];
            for (var i=0;i<rows.length;i++){
                IDS.push(rows[i]["OrderListID"]);
            }
            if (IDS.length == 0){
                mini.alert("请选择要完成代缴的官费订单！");
                return;
            }
            mini.confirm("确认已完成代缴?", "系统提示", function (act) {
                if (act === 'ok') {
                    var url = "/WeChatOrder/completeDJ";
                    $.ajax({
                        contentType:'application/json',
                        method:'post',
                        url:url,
                        data:mini.encode(IDS),
                        success:function (r) {
                            if (r['success']) {
                                mini.alert("完成代缴成功！",'订单完成代缴',function () {
                                    var argDZF = {};
                                    var argDJZ = {};
                                    var argDJWC = {};
                                    argDZF["ListType"] = "DZF";
                                    argDJZ["ListType"] = "DJZ";
                                    argDJWC["ListType"] = "DJWC"
                                    ResetGrid({}, argDZF, argDJZ, argDJWC);
                                    ReloadGovFeeWaitNum({});
                                });
                            }
                            else mini.alert("完成代缴失败！");
                        },
                        failure:function (error) {
                            mini.alert(error);
                        }
                    })
                }
            })
        }
        function Remove() {
            var grids = mini.get("OrderList_datagrid" + TabIndexs)
            var rows = grids.getSelecteds();
            var IDS = [];
            for (var i=0;i<rows.length;i++){
                if (rows[i]["PayState"] == 2){
                    mini.alert("已支付完成的订单无法删除！");
                    return;
                }
                IDS.push(rows[i]["OrderListID"]);
            }
            if (IDS.length == 0){
                mini.alert("请选择要删除的官费订单！");
                return;
            }
            mini.confirm("确认删除?", "系统提示", function (act) {
                if (act === 'ok') {
                    var url = "/WeChatOrder/remove";
                    $.ajax({
                        contentType:'application/json',
                        method:'post',
                        url:url,
                        data:mini.encode(IDS),
                        success:function (r) {
                            if (r['success']) {
                                mini.alert("删除成功！",'删除订单',function () {
                                    var argDZF = {};
                                    var argDJZ = {};
                                    var argDJWC = {};
                                    argDZF["ListType"] = "DZF";
                                    argDJZ["ListType"] = "DJZ";
                                    argDJWC["ListType"] = "DJWC"
                                    ResetGrid({}, argDZF, argDJZ, argDJWC);
                                    ReloadGovFeeWaitNum({});
                                });
                            }
                            else mini.alert("删除失败！");
                        },
                        failure:function (error) {
                            alert(error);
                        }
                    })
                }
            })
        }

        function expand(OrderList_HighQuery,QueryText,comField,P,fits) {
            var txtQuery = mini.get(QueryText);
            var comField = mini.get(comField);
            var btn = mini.get(OrderList_HighQuery);
            var display = $('#' + P).css('display');
            if (display == "none") {
                btn.setIconCls("icon-collapse");
                btn.setText("隐藏");
                $('#' + P).css('display', "block");
                // btn.hide();
                comField.hide();
                txtQuery.hide();
            } else {
                btn.setIconCls("icon-expand");
                btn.setText("高级查询");
                $('#' + P).css('display', "none");
                // btn.show();
                comField.show();
                txtQuery.show();
            }
            // var fit = mini.get(fits + TabIndexs);
            // fit.setHeight('100%');
            // fit.doLayout();
        }

        function doHightSearch(grids, highQueryForm) {
            var arg = {};
            var argDZF = {};
            var argDJZ = {};
            var argDJWC = {};
            var form = new mini.Form('#' + highQueryForm);
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
            argDZF["High"] = mini.encode(result);
            argDJZ["High"] = mini.encode(result);
            argDJWC["High"] = mini.encode(result);
            argDZF["ListType"] = "DZF";
            argDJZ["ListType"] = "DJZ";
            argDJWC["ListType"] = "DJWC"
            // var grid = mini.get(grids + TabIndexs);
            // grid.load(arg);

            ResetGrid(arg, argDZF, argDJZ, argDJWC);
            ReloadGovFeeWaitNum(arg);
        }

        function doQuery(grids,comFields,QueryTexts) {
            var txtQuery = mini.get('#' + QueryTexts);
            var comField = mini.get('#' + comFields);
            var arg = {};
            var argDZF = {};
            var argDJZ = {};
            var argDJWC = {};
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
            if (cs.length > 0) {
                arg["Query"] = mini.encode(cs);
                argDZF["Query"] = mini.encode(cs);
                argDJZ["Query"] = mini.encode(cs);
                argDJWC["Query"] = mini.encode(cs);
                argDZF["ListType"] = "DZF";
                argDJZ["ListType"] = "DJZ";
                argDJWC["ListType"] = "DJWC"

                ResetGrid(arg, argDZF, argDJZ, argDJWC);
                ReloadGovFeeWaitNum(arg);
            }
        }
        function reset(highQueryForms,datagrids,QueryText,comField){
            var form=new mini.Form('#' + highQueryForms);
            form.reset();
            var txtQuery = mini.get("#" + QueryText);
            var comField = mini.get("#" + comField);
            txtQuery.setValue(null);
            comField.setValue('All');
            doHightSearch(datagrids + TabIndexs, highQueryForms);
        }
        function Statistic() {
            mini.open({
                url:"/WeChatOrder/statistic",
                title:"统计信息",
                width:"80%",
                height:"70%"
            })
        }
        function ResetGrid(arg, argDZF, argDJZ, argDJWC) {
            grid1.load(arg);

            grid2.setUrl('/WeChatOrder/getData');
            grid2.load(argDZF);

            grid3.setUrl('/WeChatOrder/getData');
            grid3.load(argDJZ);

            grid4.setUrl('/WeChatOrder/getData');
            grid4.load(argDJWC);
        }
        function setTabTitle(AllTabNum, DZFTabNum, DJZTabNum, DJWCTabNum) {
            var tabs = mini.get("tabs1");
            tabs.updateTab("AllTab", {title: '全部订单' + AllTabNum});
            tabs.updateTab("DZFTab", {title: '待支付' + DZFTabNum});
            tabs.updateTab("DJZTab", {title: '代缴中' + DJZTabNum});
            tabs.updateTab("DJWCTab", {title: '代缴完成' + DJWCTabNum});
        }
        function ReloadGovFeeWaitNum(arg) {
            var url = "/WeChatOrder/reloadGovFeeWaitNum";
            $.post(url, arg, function (result) {
                var res = mini.decode(result);
                if (res['success']) {
                    FeeWaitNum = result.data || {};
                    setTabTitle(FeeWaitNum.ALL, FeeWaitNum.DZF, FeeWaitNum.DJZ, FeeWaitNum.DJWC);
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
                    debugger;
                    var el = e.element;
                    if (el) {
                        var code = $(el).attr('code');
                        var rows = grid1.getData();
                        var row1 = grid1.findRow(function (row1) {
                            if (row1["OrderNo"] == code) return true;
                        });
                        var row2 = grid2.findRow(function (row2) {
                            if (row2["OrderNo"] == code) return true;
                        });
                        var row3 = grid3.findRow(function (row3) {
                            if (row3["OrderNo"] == code) return true;
                        });
                        var row4 = grid4.findRow(function (row4) {
                            if (row4["OrderNo"] == code) return true;
                        });
                        if (row1) {
                            var Depict1 = row1["Depict"];
                            if (Depict1) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row1["Depict"] + '</td></tr></table>');
                            }
                        }
                        if (row2) {
                            var Depict2 = row2["Depict"];
                            if (Depict2) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row2["Depict"] + '</td></tr></table>');
                            }
                        }
                        if (row3) {
                            var Depict3 = row3["Depict"];
                            if (Depict3) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row3["Depict"] + '</td></tr></table>');
                            }
                        }
                        if (row4) {
                            var Depict4 = row4["Depict"];
                            if (Depict4) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row4["Depict"] + '</td></tr></table>');
                            }
                        }
                    }
                }
            });
        }
    </script>
</@layout>