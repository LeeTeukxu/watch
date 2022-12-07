<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript">
        var types = [{id: '发明公布', text: '发明公布'}, {id: '实用新型', text: '实用新型'}, {id: '外观设计', text: '外观设计'}];
        var YearWatch = [
            { id: 0, text: '监控中' },
            { id: 3, text: '放弃监控' },
            { id: 4, text: '待缴费' },
            { id: 5, text: '完成缴费' }
        ];
    </script>
    <style>
        .Zlyw * {
            display: inline-block;
            vertical-align: middle;
        }

        .Zlyw {
            padding-top: -30px;
        }

        .Zlyw img {
            width: 20px;
        }

        .Zlyw h5 {
            color: rgb(0, 159, 205);
            font-size: 19px
        }

        .Zlywtop {
            display: inline-block;
            width: 500px;
            float: left;
            margin-top: -64px;
            margin-left: 125px;
            height: 32px;
        }

        .Zlywtop ul {
            margin-top: -12px;
            margin-right: -20px;
        }

        .Zlywtop ul li {
            float: left;
          /*  margin-left: 1.5%;*/
            height: 45px;
            padding-top: 11px;
            /*width: 90px;*/
            border-radius: 5px;
            margin-top: 9.5px;
            text-align: center;
            list-style: none;
            /**/
        }

        @media screen and (max-width: 1655px) {
            .Zlywtop ul li {
                margin-left: -16px;
            }
        }

        @media screen and (max-width: 1557px) {
            .Zlywtop ul li {
                margin-left: -30px;
            }
        }


        @media screen and (max-width: 1235px) {
            .Zlywtop ul li {
             /*   margin-left: 1%;*/
            }

            .Zlywtop {
                margin-left: 130px;
            }
        }

        .Zlywtop ul li:hover {
            background-color: rgba(247, 140, 24, 0.85);
        }

        .Zlywtop ul li a {
            color: rgb(1, 160, 202);
        }

        .Zlywtop ul li a span {
            /*font-size: 16px;*/
            font-size: 1vw;
        }

        .Zlywtop ul li a h4 {
            display: inline;
            /*font-size: 16px;*/
            font-size: 0.8vw;
        }

        .info1bottom ul {
            margin-top: 10px;
        }

        .info1bottom ul li {
            float: left;
            margin-left: 7%;
            height: 35px;
            margin-top: -8px;
           /* width: 156px;*/
            padding-top: 5px;
            border-radius: 5px;
            text-align: center;
            font-size: 20px;
            list-style: none;
        }

        @media screen and (max-width: 1593px) {
            .info1bottom ul li {
                margin-left: 9%;
            }
        }

        @media screen and (max-width: 1340px) {
            .info1bottom ul li {
                margin-left: 8%;
            }
        }

        @media screen and (max-width: 1235px) {
            .info1bottom ul li {
                margin-left: 7%;
            }
        }

        .info1bottom ul li:hover {
            background-color: rgba(247, 140, 24, 0.85)
        }

        .info1bottom ul li a {
            margin-top: 5px;
        }

        .info1bottom ul li a span {
            color: rgb(1, 160, 202);
            font-size: 19px;

        }

        .info1bottom ul li a h4 {
            display: inline;
            color: rgb(0, 159, 205);
        }

        .info2bottom {
            margin-top: 10px;
        }

        .info2bottom ul li {
            float: left;
            margin-left: 6%;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (max-width: 1761px) {
            .info2bottom ul li {
                margin-left: 6%;
            }
        }

        @media screen and (max-width: 1550px) {
            .info2bottom ul li {
                margin-left: 3.8%;
            }
        }

        @media screen and (max-width: 1350px) {
            .info2bottom ul li {
                margin-left: 3%;
            }
        }

        @media screen and (max-width: 1250px) {
            .info2bottom ul li {
                margin-left: 2.2%;
            }
        }

        /*@media screen and (max-width:1869px){.info2bottom ul li {margin-left:2%;}  }
        @media screen and (max-width:1798px){.info2bottom ul li {margin-left:1.7%;}  }
        @media screen and (max-width:1696px){.info2bottom ul li {margin-left:1.5%;}  }
        @media screen and (max-width:1673px){.info2bottom ul li {margin-left:1%;}  }
        @media screen and (max-width:1617px){.info2bottom ul li {margin-left:0.5%;}  }*/

        .info2bottom ul li:hover {
            background-color: rgb(216, 228, 250)
        }

        .info2bottom ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info2bottom ul li a h4 {
            display: inline;
            color: rgb(51, 97, 232)
        }

        .info3bottom {
            margin-top: 10px;
        }

        .info3bottom ul li {
            float: left;
            margin-left: 6%;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (min-width: 1480px) and (max-width: 1593px) {
            .info3bottom ul li {
                margin-left: 6.5%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info3bottom ul li {
                margin-left: 5.3%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info3bottom ul li {
                margin-left: 4.4%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info3bottom ul li {
                margin-left: 2.5%;
            }
        }

        .info3bottom ul li:hover {
            background-color: rgb(214, 212, 251)
        }

        .info3bottom ul li a span {
            color: rgb(53, 102, 231);
            font-size: 15px
        }

        .info3bottom ul li a h4 {
            display: inline;
            color: rgb(52, 101, 232)
        }


        .info4bottom {
            margin-top: 10px;
        }

        .info4bottom ul li {
            float: left;
            margin-left: 8%;
            width: 100px;
            height: 35px;
            margin-top: -6px;
            padding-top: 6px;
            border-radius: 5px;
        }

        @media screen and (min-width: 1480px) and (max-width: 1593px) {
            .info4bottom ul li {
                margin-left: 6.5%;
            }
        }

        @media screen and (max-width: 1480px) {
            .info4bottom ul li {
                margin-left: 5.3%;
            }
        }

        @media screen and (max-width: 1374px) {
            .info4bottom ul li {
                margin-left: 4.4%;
            }
        }

        @media screen and (max-width: 1233px) {
            .info4bottom ul li {
                margin-left: 2.5%;
            }
        }

        .info4bottom ul li:hover {
            background-color: rgb(53, 102, 231);
        }

        .info4bottom ul li a span {
            color: rgb(1, 160, 202);
            font-size: 15px
        }

        .info4bottom ul li a h4 {
            display: inline;
            color: rgb(0, 159, 205)
        }


        @media screen and (max-width: 1170px) {
            .info1bottom ul li {
                margin-left: 10%;
            }

            .info2bottom ul li {
                margin-left: 1%;
            }

            .info3bottom ul li {
                margin-left: 0.5%;
            }

            .info4bottom ul li {
                margin-left: 0.5%;
            }
        }
        .addckbackground{background-color: rgba(247, 140, 24, 0.85);}
    </style>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>
  <#--  <div  style="overflow: auto;height: 100%">-->
    <div class="container" style="padding:0px">
        <div class="mini-clearfix ">
<#--            <div class="mini-col-2">-->
<#--                <div id="info0" style="height:80px;background-color: rgb(63,87,131);overflow: hidden;border-radius:3px;-->
<#--            -moz-box-shadow: 2px 2px 10px rgb(63,87,131);-webkit-box-shadow: 2px 2px 10px rgb(63,87,131);-shadow:2px 2px 10px rgb(63,87,131);">-->
<#--                    <div class="file-list">-->
<#--                        <div class="Zlyw" style="margin-left: 18px;">-->
<#--                            <img src="/appImages/zongheicon.png" alt="专利官费信息">-->
<#--                            <h5>官费信息</h5>-->
<#--                        </div>-->

<#--                        <div class="Zlywtop">-->
<#--                            <ul>-->
<#--                                <li class="ZLywli" id="Z1" onclick=""-->
<#--                                    style="">-->
<#--                                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('ALL',this)">-->
<#--                                        <span>全部</span>&nbsp;-->
<#--                                        <h4 class="x0">0</h4>-->
<#--                                    </a>-->
<#--                                </li>-->
<#--                            </ul>-->

<#--                        </div>-->
<#--                    </div>-->
<#--                </div>-->
<#--            </div>-->
            <div class="mini-col-9">
                <div id="info1"
                     style="height: 80px;background: rgb(226,250,252);border: 1px solid rgb(190, 226, 240);border-radius: 5px;">
                    <div class="info1top"
                         style="width:100%;height: 30px;border-bottom: 1px solid rgb(214,239,243);text-align: center;">
                        <h2 style="color:rgb(0, 159, 205);margin-left: 5px;margin-top: 5px;">待缴官费</h2>
                    </div>
                    <div class="info1bottom" style="width: 100%;height: 55px">
                        <ul>
                            <li class="Jdlcli " id="J5" onclick="">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('WJALL',this)">
                                    <h4 id="J1h4">全部</h4>
                                    <span id="J1span" class="x4">0</span>
                                </a>
                            </li>
                            <li class="Jdlcli" id="J1" onclick="">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('ZeroToThirty',this)">
                                    <h4 id="J1h4">0-30天</h4>
                                    <span id="J1span" class="x1">0</span>
                                </a>
                            </li>
                            <li class="Jdlcli" id="J2" onclick="">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('ThirtyToNinety',this)">
                                    <h4 id="J1h4">30-90天</h4>
                                    <span id="J1span" class="x2">0</span>
                                </a>
                            </li>
                            <li class="Jdlcli" id="J4" onclick="">
                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('Invalid',this)">
                                    <h4 id="J1h4">缴费过期</h4>
                                    <span id="J1span" class="x3">0</span>
                                </a>
                            </li>
<#--                            <li class="Jdlcli" id="J3" onclick="">-->
<#--                                <a style="text-decoration:none" target="_self" href="javascript:void(0)" >-->
<#--                                    <h4 id="J1h4">自定义天数：<input type="text" class="mini-textbox" id="Days"></h4>-->
<#--                                </a>-->
<#--                            </li>-->
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mini-col-3">
                <div id="info0" style="height:80px;background-color:rgb(226, 250, 252);overflow: hidden;border-radius:3px;
            -moz-box-shadow: 2px 2px 10px rgb(63,87,131);-webkit-box-shadow: 2px 2px 10px rgb(63,87,131);-shadow:2px 2px 10px rgb(63,87,131);">
                    <div class="file-list">
                        <div class="Zlyw" style="margin-left: 18px;">
                            <img src="/appImages/zongheicon.png" alt="官费信息">
                            <h5>已缴官费</h5>
                        </div>

                        <div class="Zlywtop">
                            <ul>
                                <li class="ZLywli" id="Z1">
                                    <a style="text-decoration:none" target="_self" href="javascript:void(0)" onclick="ChangeQuery('YJALL',this)">
                                        <span></span>&nbsp;
                                        <h4 class="x5" style="font-size: 20px">0</h4>
                                    </a>
                                </li>
                            </ul>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mini-layout" style="width: 100%;overflow:hidden;height: 88%;border:1px solid white;" >
        <div region="center" style="overflow:hidden">
            <div class="mini-toolbar">
                <table style="width:100%">
                    <tr>
                        <td style="width:95%">
                            <a class="mini-button mini-button-info" id="GovFee_Export"
                               onclick="doExport">导出Excel</a>
<#--                            自定义天数：<span class="mini-textbox Query_Field PatentInfoBrowse_Query" id="Days" vtype="int" style="border-width: 0px; width: 50px; padding: 0px;">-->
<#--                                <span class="mini-textbox-border mini-corner-all">-->
<#--                                    <input type="text" class="mini-textbox" id="Days" >-->
<#--                                </span>-->
<#--                                <input type="hidden">-->
<#--                            </span>-->
<#--                            <a class="mini-button mini-button-success" onclick="ChangeQuery('Customize',this)" id="GovFee_Create">确定</a>-->
                            <a class="mini-button mini-button-success" onclick="order()" id="GovFee_GenerateOrders" style="color: red">一键缴费</a>
                        </td>

                        <td style="white-space:nowrap;">
                            <div class="mini-combobox Query_Field GovFee_Query" id="comField" style="width:100px"
                                 data="[{id:'All',text:'全部属性'},{id:'FEENAME',text:'费用名称'},{id:'SHENQINGH',text:'专利号'},
                                 {id:'FAMINGMC',text:'发明名称'},{id:'SHENQINGRXM',text:'申请人姓名'},{id:'SHENQINGLX',text:'专利类型'}]" value="All"
                                 id="Field"></div>

                            <input class="mini-textbox Query_Field GovFee_Query" style="width:150px"
                                   id="QueryText"/>
                            <a class="mini-button mini-button-success" onclick="doQuery();" id="GovFee_Query">模糊搜索</a>
                            <a class="mini-button mini-button-danger" id="GovFee_Reset" onclick="reset">重置</a>
                            <a class="mini-button" id="GovFee_HighQuery" iconCls="icon-expand"
                               onclick="expand">高级查询</a>
                        </td>
                    </tr>
                </table>
                <div id="p1" style="border:1px solid #909aa6;border-top:0;height:110px;padding:5px;display:none">
                    <table style="width:100%;" id="highQueryForm">
                        <tr>
                            <td style="width:6%;padding-left:10px;">缴纳期限：</td>
                            <td style="width:15%;"><input name="JIAOFEIR" class="mini-datepicker" data-oper="GE"
                                                          style="width:100%"/></td>
                            <td style="width:6%;padding-left:10px;">到：</td>
                            <td style="width:15%;"><input name="JIAOFEIR" class="mini-datepicker" data-oper="LE"
                                                          style="width:100%"/></td>

                            <td style="width:6%;padding-left:10px;">费用项目</td>
                            <td style="width:15%;"><input name="FEENAME" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"
                            /></td>
                            <td style="width:6%;padding-left:10px;">金额：</td>
                            <td style="width:15%;"><input name="MONEY" class="mini-textbox" data-oper="EQ"
                                                          style="width:100%"/></td>
                        </tr>
                        <tr>
                            <td style="width:6%;padding-left:10px;">专利编号：</td>
                            <td style="width:15%;"><input name="SHENQINGH" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"
                            /></td>
                            <td style="width:6%;padding-left:10px;">发明名称：</td>
                            <td style="width:15%;"><input name="FAMINGMC" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"/>
                            </td>
                            <td style="width:6%;padding-left:10px;">专利类型：</td>
                            <td style="width:15%;"><input name="SHENQINGLX" class="mini-combobox" data="types"
                                                          data-oper="EQ" style="width:100%"/></td>
                            <td style="width:6%;padding-left:10px;">专利申请人：</td>
                            <td style="width:15%;"><input name="SHENQINGRXM" class="mini-textbox" data-oper="LIKE"
                                                          style="width:100%"/></td>
                        </tr>
                        <tr>
                            <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                                <a class="mini-button mini-button-success GovFee_Query" onclick="doHightSearch"
                                   style="width:120px">搜索</a>
                                <a class="mini-button mini-button-danger GovFee_Query" onclick="expand" style="margin-left:30px;
                                width:120px">收起</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="mini-fit" id="fitt">
                <div id="GovFee_datagrid" class="mini-datagrid" style="width: 100%;height:100%"
                     frozenstartcolumn="0" frozenendcolumn="8" sortorder="asc" sortfield="JIAOFEIR"
                     ondrawcell="onDraw" allowresize="true" url="/work/govFee/getData" multiselect="true"
                     pagesize="20" sizelist="[5,10,20,50,100]" autoload="false" onload="afterload">
                    <div property="columns">
                        <div type="indexcolumn"></div>
                        <div type="checkcolumn"></div>
                        <div field="Action" width="70" headerAlign="center" align="center">备注信息</div>
                        <div field="State" width="90" headerAlign="center" align="center">专利订单状态</div>
                        <div field="JIAOFEIR" name="JIAOFEIR" width="100" allowsort="true" headeralign="center" align="center" dataType="date" dateFormat="yyyy-MM-dd">
                            最后缴费日
                        </div>
                        <div field="FEENAME" name="FEENAME" width="140" allowsort="true" headeralign="center"  align="center">
                            费用项目
                        </div>
                        <div field="FEEPRICE" name="MONEY" width="80" allowsort="true"
                             headeralign="center" align="center" align="right" dataType="float">
                            费减前金额
                        </div>
                        <div field="MONEY" name="MONEY" width="90" allowsort="true"
                             headeralign="center" align="center" align="right" dataType="float">
                            费减后金额
                        </div>
                        <div field="AFTERPRICE" name="MONEY" width="90" allowsort="true"
                             headeralign="center" align="center" align="right" dataType="float">
                            节省金额
                        </div>
                        <div field="SHENQINGH" name="shenqingh" width="120" allowsort="true" headeralign="center" align="center"
                             renderer="onZhanlihao">
                            专利申请号
                        </div>
                        <div field="FAMINGMC" name="FAMINGMC" width="200" allowsort="true" headeralign="center" align="center">
                            专利名称
                        </div>
                        <div field="SHENQINGLX" width="80" headerAlign="center" align="center" allowsort="true">
                            专利类型
                        </div>
                        <div field="SHENQINGR" name="shenqingr" width="80" allowsort="true" headeralign="center" align="center"
                             datatype="date" dateformat="yyyy-MM-dd">
                            申请日
                        </div>
                        <div field="LAWSTATUS" width="80" allowsort="true" headeralign="center" align="center">
                            专利法律状态
                        </div>
                        <div field="SHENQINGRXM" width="200" allowsort="true" headeralign="center" align="center">
                            专利申请人
                        </div>
                        <div field="FAMINGRXM" width="130" allowsort="true" headeralign="center" align="center">
                            专利发明人
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<#--    </div>-->
<script type="text/javascript">
    mini.parse();
    var fit = mini.get('fitt');
    var tip = new mini.ToolTip();
    var grid = mini.get('GovFee_datagrid');
    var txtQuery = mini.get('#QueryText');
    var comField = mini.get('#comField');
    var cmdQuery=mini.get('#GovFee_Query');
    var QueryState = "WJALL";
    $(function () {
        $('#p1').hide();
        fit.setHeight('100%');
        fit.doLayout();
        var RoleName = '${RoleName}';
        if (RoleName == "企业用户") {
            ChangeQuery('WJALL', "J5")
        }else ChangeQuery('ZeroToThirty',"J1");
        // document.getElementById("J1").classList.add("addckbackground");
        // mini.get('Days').on('valuechanged', function(e) {
        //     ChangeQuery('Customize',this)
        // });
    });

    function expand(e) {
        var btn = e.sender;
        var display = $('#p1').css('display');
        if (display == "none") {
            btn.setIconCls("icon-collapse");
            btn.setText("隐藏");
            $('#p1').css('display', "block");
            cmdQuery.hide();
            txtQuery.hide();
            comField.hide();
        } else {
            btn.setIconCls("icon-expand");
            btn.setText("高级查询");
            $('#p1').css('display', "none");
            cmdQuery.show();
            txtQuery.show();
            comField.show();
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
                    var obj = {field: field.getName(), value: field.getValue(), oper: field.attributes["data-oper"]};
                    result.push(obj);
                }
            }
        }
        arg["High"] = mini.encode(result);
        arg["State"] = QueryState;
        grid.load(arg);
    }

    function doQuery(code, state) {
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
                    if (f == "KH" || f == "LC" || f == "XS" || f == "DL") f = "NEIBUBH";
                    var kWork = f + '=' + word;
                    if (cs.indexOf(kWork) == -1) {
                        var op = {field: f, oper: 'LIKE', value: word};
                        cs.push(op);
                    }
                }
            } else {
                if (field == "KH" || field == "LC" || field == "XS" || field == "DL") field = "NEIBUBH";
                var op = {field: field, oper: 'LIKE', value: word};
                cs.push(op);
            }
        }
        if (cs.length > 0) arg["Query"] = mini.encode(cs);
        arg["State"] = QueryState;
        grid.load(arg);
    }

    function doExport() {
        var excel = new excelData(grid);
        excel.export("待缴费费用项目列表.xls");
    }


    function onDraw(e) {
        var field = e.field;
        var record = e.record;
        if (field == "KH") {
            var clientId = record["KHID"];
            var val = e.value;
            if (clientId) {
                e.cellHtml = '<a href="#" onclick="showClient(' + "'" + clientId + "'" + ')">' + val + '</a>';
            } else e.cellHtml = val;
        }
        else if (field == "Generate") {
           var val=parseInt(record[field]);
           if(val==1) e.cellHtml="否"; else e.cellHtml="是";
        }
        else if (field == "Action") {
            var record = e.record;
            var memo = record["MEMO"];
            var editMemo = parseInt(record["EDITMEMO"] || 0);
            var text = ((memo == null || memo == "") ? "<span style='color:green;text-align:center'>添加</span>" : "<span " +
                "style='color:blue'>修改</span>");
            if (editMemo == 0) {
                if (memo) text = "<span style='color:gay;text-align:center'>查看</span>";
            }
            e.cellHtml = '<a href="#"  data-placement="bottomleft"  code="' + record["SHENQINGH"] + '" class="showCellTooltip" onclick="ShowMemo(' + "'" + record["SHENQINGH"] + "'," + "'" + record["FEENAME"] + "'" + ')">' + text + '</a>';
        }else if (field == "State") {
            var record = e.record;
            var djstate = record["DJSTATE"];
            var paystate = record["PSTATE"];
            var djtext = ((djstate == null || djstate == "") ? "" : '<span style="color: #337ab7">' + djstate + '</span>');
            var paytext = ((paystate == null || paystate == "") ? "" : '<span style="color: #d9534f">' + paystate + '</span>');
            var text = paytext + "&nbsp;&nbsp;&nbsp;" + djtext;
            e.cellHtml = text;
        }else if (field == "AFTERPRICE") {
            var record = e.record;
            var FEEPRICE = record["FEEPRICE"];
            var MONEY = record["MONEY"];
            var AFTERPRICETEXT = ((FEEPRICE == null || FEEPRICE == "") ? "" : '<span style="color: #337ab7">' + (FEEPRICE-MONEY) + '</span>');
            e.cellHtml = AFTERPRICETEXT;
        }else if (field == "FEEPRICE") {
            var record = e.record;
            var FEEPRICE = record["FEEPRICE"];
            var FEEPRICETEXT = ((FEEPRICE == null || FEEPRICE == "" || FEEPRICE == 0 ? "" : FEEPRICE));
            e.cellHtml = FEEPRICETEXT;
        }
    }
    function reset() {
        var form = new mini.Form('#highQueryForm');
        form.reset();
        mini.get('#QueryText').setValue(null);
        comField.setValue('All');
        doQuery();
    }
    function afterload(e) {
        try {
            updateStateNumbers();
            grid.doLayout();
            grid.setShowPager(true);


            var param=grid.getLoadParams() || {};
            var col=grid.getColumn('JIAOFEIR');
            if(col){
                var state=param["State"] || "ALL";
                var obb={};
                if(state=="YJALL"){
                    obb.header="缴费日期";
                } else obb.header="最后缴费日";
                grid.updateColumn(col,obb);
            }
        } catch (e) {

        }
        tip.set({
            target: document,
            selector: '.showCellTooltip',
            onbeforeopen: function (e) {
                e.cancel = false;
            },
            onopen: function (e) {
                var el = e.element;
                if (el) {
                    var hCode = $(el).attr('code');
                    if (hCode) {
                        if(!grid)grid=mini.get('GovFee_datagrid');
                        var rows = grid.getData();
                        var row = grid.findRow(function (row) {
                            if (row["SHENQINGH"] == hCode) return true;
                        });
                        if (row) {
                            var memo = row["MEMO"];
                            if (memo) {
                                tip.setContent('<table style="width:400px;height:auto;table-layout:fixed;word-wrap:break-word;word-break:break-all;text-align:left;vertical-align: text-top; "><tr><td>' + row["MEMO"] + '</td></tr></table>');
                            }
                        }
                    }
                }
            }
        });
    }

    function ShowMemo(id, FEENAME) {
            mini.open({
            url: '/work/govFee/addMemo?ID=' + id + '&FEENAME=' + encodeURIComponent(FEENAME),
            showModal: true,
            width: 800,
            height: 400,
            title: "【" + FEENAME + "】的备注信息",
            onDestroy: function () {
                grid.reload();
            }
        });
        // window.parent.doResize();
    }
    function showClient(clientId) {
        mini.open({
            url:'/work/clientInfo/browse?Type=1&ClientID='+clientId,
            width:'100%',
            height:'100%',
            title:'浏览客户资料',
            showModal:true,
            ondestroy:function(){

            }
        });
        window.parent.doResize();
    }
    function doHightSearch(){
        var arg = {};
        var form=new mini.Form('#highQueryForm');
        var fields=form.getFields();
        var result=[];
        for(var i=0;i<fields.length;i++){
            var field=fields[i];
            var val=field.getValue();
            if(val!=null && val!=undefined)
            {
                if(val!='')
                {
                    var obj={field:field.getName(),value:field.getValue(),oper:field.attributes["data-oper"]};
                    result.push(obj);
                }
            }
        }
        arg["High"]=mini.encode(result);
        arg["State"] = QueryState;
        grid.load(arg);
    }
    function ChangeQuery(State,obj) {
        var btnGenerate = mini.get('GovFee_GenerateOrders');
        if (State == "YJALL") {
            btnGenerate.hide();
        }else btnGenerate.show();
        var grid = mini.get('#GovFee_datagrid');
        var arg = {};
        QueryState = State;
        arg["State"] = State;
        // arg["Code"] = mini.get('#Days').getValue();
        grid.load(arg);
        $("li").removeClass();
        if (obj=="J1" || obj == "J5"){
         document.getElementById(obj).classList.add("addckbackground");
        }else {
         obj.parentNode.className='addckbackground';
        }
    }

    function updateStateNumbers() {
        var key = (new Date()).getTime();
        var url = '/work/govFee/getGovCount?Key=' + key;
        $.getJSON(url, {}, function (result) {
            if (result.success) {
                var states = result.data || {};
                for (var i = 0; i < states.length; i++) {
                    var state = states[i];
                    var con = $('.' + state.name);
                    if (con.length > 0) {
                        con.text(state.num);
                    }
                }
            }
        });
    }
    function order() {
        var rows = grid.getSelecteds();
        var IDS = [];
        var shenqinghs = [];
        var Amounts = [];
        var feenames = [];
        var jiaofeirs = [];
        for (var i = 0; i < rows.length; i++) {
            if (rows[i]["DJSTATE"] != "") {
                mini.alert("您选中的官费信息已生成订单，请勿重复缴费！");
                return;
            }
            // IDS.push(rows[i]["GOVID"]);
            shenqinghs.push(rows[i]["SHENQINGH"]);
            Amounts.push(rows[i]["MONEY"]);
            feenames.push(rows[i]["FEENAME"]);
            jiaofeirs.push(rows[i]["JIAOFEIR"]);
        }
        if (Amounts.length == 0) {
            mini.alert('请选择要生成的订单!');
            return;
        }

        mini.confirm('确认要生成订单?', '系统提示', function (act) {
            if (act === 'ok') {
                // var url="/WeChatOrder/orderlistparameter?IDS=" + encodeURIComponent(IDS) + "&Amounts=" + encodeURIComponent(Amounts);
                var url = "/WeChatOrder/orderpayment?SHENQINGHS=" + encodeURIComponent(shenqinghs) + "&Amounts=" + encodeURIComponent(Amounts) + "&FEENAMES=" + encodeURIComponent(feenames) + "&JIAOFEIRS=" + jiaofeirs;
                mini.open({
                    url: url,
                    title: "官费订单",
                    width: "100%",
                    height: "100%",
                    onload: function () {

                    },
                    ondestroy: function (action) {
                        //刷新表格
                        grid.reload();
                    }
                });
            }
        })
    }

</script>
</@layout>