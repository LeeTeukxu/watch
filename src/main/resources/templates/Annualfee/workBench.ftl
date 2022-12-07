<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="/js/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/boot.js"></script>
    <script type="text/javascript" src="/js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/left-side-menu.css">
    <link rel="stylesheet" type="text/css" href="/font/iconfont.css">
    <script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="/js/left-side-menu.js"></script>
    <link rel="stylesheet" href="/css/tongyong.css"/>

    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--下拉菜单strat-->
    <script src="/js/paging/js/adapter.js"></script> <!--rem适配js-->

    <link rel="stylesheet" href="/js/paging/css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="/js/paging/css/menu.css"> <!--主样式-->
    <script src="/js/paging/js/menu.js"></script> <!--控制js-->
    <!--下拉菜单end-->

    <style>
        body {
            background: #f9f9f9;
            overflow-x: hidden;
            height: 1422px;
        }

        .b1L {
            float: left;
            height: 260px;
            background: #fff;
            border-radius: 21px;
            margin: 5px;
            width: 100%;
            overflow: hidden;
            margin-top: 17px;
        }

        .box-title {
            font-family: unset;
            color: black;
            /*border-bottom: 1px solid #eee;*/
            font-weight: bold;
            padding: 10px;
        }

        .qustnav {
            overflow: hidden;
            margin-top: 37px;
        }

        .qustnav a {
            float: left;
            display: block;
            font-size: 15px;
            color: #666;
            text-decoration: none;
            width: 11%;
            text-align: center;
        }

        .qsnimg {
            width: 62px;
            height: 62px;
            background: #eaf2ff;
            margin: 10px auto;
            text-align: center;
            /* line-height: 63px; */
            padding-top: 22px;
            border-radius: 50%;
        }

        .qsnimg img {
            margin-top: -4px;
        }

        .b1R {
            float: left;
            height: 260px;
            background: #fff;
            border-radius: 20px;
            margin: 5px -9px;
            width: 99%;
            overflow: hidden;
            margin-top: 17px;
        }

        .z1 {
            background-size: 100%;
            width: 318px;
            /*margin: 0px auto;*/
            margin-left: 10px;
            background: url(/appImages/line.png) no-repeat #fff 7px 10px;
            border-radius: 5px;
            background-size: 20px 100%;
            margin-top: 27px;
            color: #666;
        }

        .huanjielist {
            overflow: hidden;
            /* margin-top: -13px; */
            margin-bottom: 16px;
        }

        .huanjielistL {
            text-align: center;
            float: left;
            width: 11%;
            margin-top: 0;
        }

        .huanjielistR {
            float: left;
            width: 85%;
            /*background: url(/appImages/huanjiebotom.png) no-repeat;*/
            background-size: contain;
            /* height: 138px; */
            padding-left: 0;
            margin-top: -3px;
            /* background: #eee; */
            padding-bottom: 0;
            font-size: 16px;
        }

        .qsnimg img {
            width: 28px;
        }

        .mini-grid-rows-view {
            overflow: hidden auto;
        }

        /*       .mini-grid-table tr td{background: rgba(43, 225, 255, 0.42);}*/
        .bk {
            background-color: peru;
        }

    </style>
</head>
<div class="row" style="margin-top: -16px;">
    <div style="margin-top: 16px" class="col-md-9">
        <div class="b1L" style="height: 237px;">
            <h3 class="box-title">常用快捷栏</h3>
            <div class="qustnav">
                <#if loginUser.roleName='系统管理员'>
                    <a class="kli" href="#" url="/admin">
                        <p class="qsnimg"><img src="/img/htai.png"></p>
                        <p class="qsnname">后台管理</p>
                    </a>
                </#if>
                <a class="kli" href="#" url="/clientInfo/index?MenuID=105&pageName=ClientBrowse">
                    <p class="qsnimg"><img src="/img/kehuw.png"></p>
                    <p class="qsnname">申请人管理</p>
                </a>
                <a class="kli" href="#" url="/main/patent/index?MenuID=91&pageName=PatentInfoBrowse">
                    <p class="qsnimg"><img src="/img/zhuanli.png"></p>
                    <p class="qsnname">专利管理</p>
                </a>
                <a class="kli" href="#" url="/searchResult/index">
                    <p class="qsnimg"><img src="/img/zhuanlijiansuo.png"></p>
                    <p class="qsnname">专利检索</p>
                </a>
                <a class="kli" href="#" url="/work/govFee/wait?pageName=GovFee&MenuID=164">
                    <p class="qsnimg"><img src="/img/guanfei.png"></p>
                    <p class="qsnname">官费管理</p>
                </a>
                <a class="kli" href="#" url="/WeChatOrder/orderlist">
                    <p class="qsnimg"><img src="/img/dingdan.png"></p>
                    <p class="qsnname">订单管理</p>
                </a>
                <#if loginUser.roleName?index_of("管理员") &gt;0>
                    <a class="kli" href="#" url="/myUser/index">
                        <p class="qsnimg"><img src="/img/kehuw.png"></p>
                        <p class="qsnname">用户管理</p>
                    </a>
                </#if>
            </div>
        </div>
    </div>
    <div style="margin-top: 16px" class="col-md-3">
        <div class="b1R" style="height: 237px;">
            <h3 class="box-title">登录信息</h3>
            <div class="z1">
                <div class="huanjielist">
                    <div class="huanjielistL">
                        <img src="/appImages/point.png">
                    </div>
                    <div class="huanjielistR">
                        公司名称:${loginUser.companyName}
                    </div>
                </div>
                <div class="huanjielist">
                    <div class="huanjielistL">
                        <img src="/appImages/point.png">
                    </div>
                    <div class="huanjielistR">
                        所属部门:${loginUser.depName}
                    </div>
                </div>
                <div class="huanjielist">
                    <div class="huanjielistL">
                        <img src="/appImages/point.png">
                    </div>
                    <div class="huanjielistR">
                        登&nbsp;&nbsp;录&nbsp;&nbsp;人:${loginUser.userName}
                    </div>
                </div>
                <div class="huanjielist">
                    <div class="huanjielistL">
                        <img src="/appImages/point.png">
                    </div>
                    <div class="huanjielistR">
                        用户角色:${loginUser.roleName}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="b1" style="height:360px">
            <div class="b1L" style="width:99%;height:360px">
                <h3 class="box-title">&nbsp;近期待缴官费&nbsp;&nbsp;
                    <div style="margin-left: 59px;display: inline;">
                        <a href="#" id="waitNow" class="btn btn-info  btn-sm" style="margin-left:200px" onclick="return wait30();">30天以内</a> &nbsp;
                        <a href="#" id="waitPre" class="btn btn-secondary btn-sm" style="margin-left:20px;
                            color: #fff;background-color: #5a6268;" onclick="return wait90();">三个月以内</a> &nbsp;
                        <a href="#" id="exportExcel" class="btn btn-primary btn-sm" style="margin-left:80px;"
                           onclick="return doExport();">导出Excel</a>
                    </div>
                    <div style="display: inline;float:right">
                        <div class="mini-combobox Query_Field Browse_Query" id="comFieldWaitGovFee" style="width:100px"
                             data="[{id:'All',
                            text:'全部属性'},
                            {id:'ClientName',text:'客户名称'},{id:'SHENQINGH',text:'专利号'},
                            {id:'FAMINGMC',text:'专利名称'},{id:'SHENQINGLX',text:'专利类型'},{id:'FEENAME',text:'费用名称'}]"
                             value="All" id="Field"></div>
                        <input class="mini-textbox Query_Field Browse_Query" style="width:150px"
                               id="QueryTextWaitGovFee"/>
                        <a class="mini-button mini-button-success"
                           onclick="doQuery('grid2','QueryTextWaitGovFee','comFieldWaitGovFee','waitgovfee');"
                           id="ClientBrowse_Query">模糊搜索</a>
                        <a class="mini-button mini-button-danger" id="Browse_Reset"
                           onclick="reset('QueryTextWaitGovFee','comFieldWaitGovFee')">重置条件</a>
                    </div>
                </h3>
                <div id="grid2" class="mini-datagrid" bodyStyle="border:none;SCROLLBAR-FACE-COLOR:red;"
                     style="width:98%;height:300px;margin-left: 17px;border: none;overflow: hidden"
                     allowresize="true" url="/workBench/getRecentFee" multiselect="true"
                     pagesize="8" sizelist="[5,10,20,50,100,200,500,800,1000,1500,1800,2000]" sortfield="DIFFDATE"
                     sortorder="asc"
                     autoload="true">
                    <div property="columns">
                        <div type="indexcolumn" headerAlign="center">序号</div>
                        <div field="ClientName" width="120" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">客户名称
                        </div>
                        <div field="SHENQINGH" width="100" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">专利号
                        </div>
                        <div field="FAMINGMC" width="150" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">专利名称
                        </div>
                        <div field="SHENQINGLX" width="50" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">专利类型
                        </div>
                        <div field="SHENQINGR" width="50" dataType="date" dateFormat="yyyy-MM-dd"
                             headerAlign="center" align="center" allowSort="true">申请日期
                        </div>
                        <div field="FEENAME" width="100" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">费用名称
                        </div>
                        <div field="MONEY" width="50" headerAlign="center" align="center"
                             allowSort="true" allowSort="true">金额
                        </div>
                        <div field="JIAOFEIR" width="50" dataType="date" dateFormat="yyyy-MM-dd"
                             headerAlign="center" align="center" allowSort="true">缴费截止日
                        </div>
                        <div field="DIFFDATE" width="50" headerAlign="center" align="center" allowSort="true">剩余天数</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="b1" style="height:360px">
            <div class="b1L" style="width:99%;height:360px">
                <h3 class="box-title">&nbsp;专利信息更新
                    <div style="margin-left: 74px;display: inline;">
                        <a href="#" id="pantentNow" class="btn btn-info  btn-sm" style="margin-left:100px"
                           onclick="return patentNow();">今天更新</a>
                        <a href="#" id="pantentPre" class="btn btn-secondary btn-sm" style="margin-left:20px;
                                color: #fff;background-color: #5a6268;" onclick="return patentPre();">昨天更新</a>
                    </div>
                    <div  style="display: inline;float:right">
                        <div class="mini-combobox Query_Field Browse_Query" id="comFieldPatent" style="width:100px"
                             data="[{id:'All',
                            text:'全部属性'},
                            {id:'SHENQINGRXM',text:'专利申请人'},{id:'SHENQINGH',text:'专利申请号'},
                            {id:'SHENQINGLX',text:'专利类型'},{id:'FAMINGMC',text:'专利名称'},{id:'LAWSTATUS',text:'专利状态'},{id:'FAMINGRXM',text:'发明人'},{id:'DAILIJGMC',text:'代理机构'}]"
                             value="All" id="Field"></div>
                        <input class="mini-textbox Query_Field Browse_Query" style="width:150px" id="QueryTextPatent"/>
                        <a class="mini-button mini-button-success"
                           onclick="doQuery('grid0','QueryTextPatent','comFieldPatent','patent');"
                           id="ClientBrowse_Query">模糊搜索</a>
                        <a class="mini-button mini-button-danger" id="Browse_Reset"
                           onclick="reset('QueryTextPatent','comFieldPatent')">重置条件</a>
                    </div>
                </h3>
                <div id="grid0" class="mini-datagrid" style="width:98%;height:300px;margin-left: 17px;"
                     allowresize="true" url="/workBench/getPatent" multiselect="true"
                     pagesize="8" sizelist="[5,10,20,50,100,150,200]" sortfield="CreateTime" sortorder="desc"
                     autoload="true">
                    <div property="columns">
                        <div type="indexcolumn" headerAlign="center">序号</div>
                        <div field="SHENQINGRXM" width="120" headerAlign="center" align="center"
                             allowSort="true">专利申请人
                        </div>
                        <div field="SHENQINGH" width="80" headerAlign="center" align="center"
                             allowSort="true">专利申请号
                        </div>
                        <div field="SHENQINGLX" width="50" headerAlign="center" align="center">专利类型</div>
                        <div field="FAMINGMC" width="150" headerAlign="center" align="center"
                             allowSort="true">专利名称
                        </div>
                        <div field="SHENQINGR" width="50" headerAlign="center" align="center"
                             allowSort="true" dataType="date" dateFormat="yyyy-MM-dd">申请日期
                        </div>
                        <div field="LAWSTATUS" width="100" headerAlign="center" align="center">专利状态</div>
                        <div field="FAMINGRXM" width="120" headerAlign="center" align="center">发明人</div>
                        <div field="DAILIJGMC" width="150" headerAlign="center" align="center">代理机构</div>
                        <div field="LASTUPDATETIME" width="100" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss"
                             headerAlign="center" align="center">更新时间
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="b1" style="height:360px">
            <div class="b1L" style="width:99%;height:360px">
                <h3 class="box-title">&nbsp;官费监控更新
                    <div style="margin-left: 74px;display: inline;">
                        <a href="#" id="addFeeNow" class="btn btn-info  btn-sm" style="margin-left:100px"
                           onclick="return addFeeNow();">今天更新</a>
                        <a href="#" id="addFeePre" class="btn btn-secondary btn-sm" style="margin-left:20px;
                            color: #fff;background-color: #5a6268;" onclick="return addFeePre();">昨天更新</a>
                    </div>
                    <div  style="display: inline;float:right">
                        <div class="mini-combobox Query_Field Browse_Query" id="comFieldWatch" style="width:100px"
                             data="[{id:'All',
                            text:'全部属性'},
                            {id:'SHENQINGRXM',text:'专利申请人'},{id:'SHENQINGH',text:'专利号'},
                            {id:'FAMINGMC',text:'专利名称'},{id:'SHENQINGLX',text:'专利类型'},{id:'FEENAME',text:'费用名称'}]"
                             value="All" id="Field"></div>
                        <input class="mini-textbox Query_Field Browse_Query" style="width:150px"
                               id="QueryTextPatentWatch"/>
                        <a class="mini-button mini-button-success"
                           onclick="doQuery('grid1','QueryTextPatentWatch','comFieldWatch','watch');"
                           id="ClientBrowse_Query">模糊搜索</a>
                        <a class="mini-button mini-button-danger" id="Browse_Reset"
                           onclick="reset('QueryTextPatentWatch','comFieldWatch')">重置条件</a>
                    </div>
                </h3>
                <div id="grid1" class="mini-datagrid" style="width:98%;height:300px;margin-left: 17px;"
                     allowresize="true" url="/workBench/getAddFee" multiselect="true"
                     pagesize="8" sizelist="[5,10,20,50,100,150,200]" sortfield="CreateTime" sortorder="desc"
                     autoload="true">
                    <div property="columns">
                        <div type="indexcolumn" headerAlign="center">序号</div>
                        <div field="SHENQINGRXM" width="120" headerAlign="center" align="center"
                             allowSort="true">专利申请人
                        </div>
                        <div field="SHENQINGH" width="100" headerAlign="center" align="center"
                             allowSort="true">专利号
                        </div>
                        <div field="FAMINGMC" width="150" headerAlign="center" align="center"
                             allowSort="true">专利名称
                        </div>
                        <div field="SHENQINGLX" width="50" headerAlign="center" align="center"
                             allowSort="true">专利类型
                        </div>
                        <div field="SHENQINGR" width="50" dataType="date" dateFormat="yyyy-MM-dd"
                             headerAlign="center" align="center">申请日期
                        </div>
                        <div field="FEENAME" width="100" headerAlign="center" align="center"
                             allowSort="true">费用名称
                        </div>
                        <div field="MONEY" width="50" headerAlign="center" align="center"
                             allowSort="true">金额
                        </div>
                        <div field="JIAOFEIR" width="50" dataType="date" dateFormat="yyyy-MM-dd"
                             headerAlign="center" align="center">缴费截止日
                        </div>
                        <div field="CREATETIME" width="100" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss"
                             headerAlign="center" align="center">添加时间
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid0 = mini.get('grid0');
    var grid1 = mini.get('grid1');
    var grid2 = mini.get('grid2');
    var param = {};
    $(function () {
        $('.kli').on('click', function () {
            var obj = $(this);
            var url = obj.attr('url');
            var title = obj.find('.qsnname').text();
            window.parent.addParent(url, title);
        });
    });

    $(document).ready(function () {
        // var trs = grid2.getElementsByClassName('mini-grid-cell');
        // alert(trs.length);
        // for (var i = 0; i < trs.length; i++) {
        //     trs[i].classList.add("bk");
        // }
    });


    function doExport() {
        var excel = new excelData(grid2);
        excel.export("待缴费费用项目列表.xls");
    }

    function patentNow() {
        grid0.load({'Date': 'Now'});
        param = {};
        param['Date'] = 'Now';
        document.getElementById("pantentPre").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("pantentNow").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;//
    }

    function patentPre() {
        grid0.load({'Date': 'Pre'});
        param = {};
        param['Date'] = 'Pre';
        document.getElementById("pantentNow").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("pantentPre").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;
    }

    function addFeeNow() {
        grid1.load({'Date': 'Now'});
        param = {};
        param['Date'] = 'Now';
        document.getElementById("addFeePre").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("addFeeNow").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;
    }

    function addFeePre() {
        grid1.load({'Date': 'Pre'});
        param = {};
        param['Date'] = "Pre";
        document.getElementById("addFeeNow").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("addFeePre").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;
    }

    function wait30() {
        grid2.load({'minDays': 0, 'maxDays': 30});
        param = {};
        param['minDays'] = 0;
        param['maxDays'] = 30;
        document.getElementById("waitPre").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("waitNow").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;
    }

    function wait90() {
        grid2.load({'minDays': 30, 'maxDays': 90});
        param = {};
        param['minDays'] = 30;
        param['maxDays'] = 90;
        document.getElementById("waitNow").style.cssText = "color: #fff;background-color: #5a6268;border-color: #5a6268;";
        document.getElementById("waitPre").style.cssText = "color: #fff;background-color: #31b0d5;border-color: #269abc;";
        return false;
    }

    patentNow();
    addFeeNow();
    wait30();

    function doQuery(grid, QueryText, comField, type) {
        var grids = mini.get(grid);
        var arg = {};
        var bs = [];
        var cs = [];
        var txtQuery = mini.get('#' + QueryText);
        var comField = mini.get('#' + comField);
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
        if (type == "waitgovfee") {
            arg["minDays"] = param['minDays'];
            arg["maxDays"] = param['maxDays'];
        } else {
            arg["Date"] = param['Date'];
        }
        grids.load(arg);
    }

    function reset(QueryText, comField) {
        var txtQuery = mini.get('#' + QueryText);
        var comField = mini.get('#' + comField);
        txtQuery.setValue(null);
        comField.setValue('All');
    }
</script>
</body>
</html>
