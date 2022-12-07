<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="scripts/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/boot.js"></script>
    <script type="text/javascript" src="/js/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/left-side-menu.css">
    <link rel="stylesheet" type="text/css" href="/font/iconfont.css">
    <script type="text/javascript" src="/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="/js/left-side-menu.js"></script>
    <link rel="stylesheet" href="/css/tongyong.css" />

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--下拉菜单strat-->
    <script src="/js/paging/js/adapter.js"></script> <!--rem适配js-->

    <link rel="stylesheet" href="/js/paging/css/base.css"> <!--初始化文件-->
    <link rel="stylesheet" href="/js/paging/css/menu.css"> <!--主样式-->
    <script src="/js/paging/js/menu.js"></script> <!--控制js-->
    <!--下拉菜单end-->

    <style>
        .pagetitle{border-bottom: solid 1px #dbdee5;height: 44px;width: 99%;margin: 0px auto;}
        .pagetitle p{margin-top: 19px;margin-left: 10px;font-family: initial;}
        .pagetitle span{border: 3px solid #00a0e9;height: 10px;width: 10px;}
        .bz{margin-top: 13px;font-size: 12px;color: black;font-weight: bold;}
        .zljk-tjzl-ins {
            font-size: 14px;
            padding: 15px 10px 30px 10px;
            font-weight: bold;
            color: rgb(255,158,1);
        }
        .nav{width: 98%;margin: 0px auto;}
        .tab-content{width: 98%;margin: 0px auto;}
        .AddAppnoTA {
            height: 130px;
            width: 40%;
            outline: none;
            border-radius: 5px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            border: solid 1px #e7e7e7;
            padding: 5px;
            margin: auto;
        }
        .sqx{margin: 16px 23px 19px;text-align: center;}
        .sqx p{font-family:'Microsoft YaHei';font-size:14px;color:#ff6a00;padding:10px 0}
        .jiansuo{width: 100%;}


        .index-base {
            width: 670px;
            margin: auto;
            margin-top: 51px;

        }
        .index-searchbox {
            width: 100%;
            overflow: hidden;
        }
        .index-searchbox .index-box {
            /*    border: solid 1px #a5a5a5;*/
            border-right: 0;
            width: 600px;
            height: 48px;
            margin-left: -36px;
        }
        .index-searchbox li{list-style: none;}

        .index-searchbox .index-box input {
            border: 0;
            height: 45px;
            margin: 2px 0px 0px 36px;
            font-family: 'Microsoft YaHei';
            font-size: 14px;
            width: 100.7%;
            text-indent: 15px;
            /*border: 1px solid #;*/
            border: solid 1px #a5a5a5;
        }
        .index-cnbtn {
            background: #3398dc;
            color: #FFF;
            border: 0;
            cursor: pointer;
            width: 66px;
            height: 45px;
            text-align: center;
            font-weight: 600;
        }
    </style>
</head>
<body style="overflow: hidden;background: white;">
<div class="row">
    <div class="col-md-12">
        <div class="clumsbg" style="overflow-x: hidden;overflow-y: auto;height: 855px;background: #f3f3f4;margin-left: -2px;">
            <div class="pagetitle">
                <p><span></span>&nbsp; 当前 - 添加监控专利</p>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div style="float: right;width: 98%;margin-top: -785px;background: white;margin-right: 19px;border-radius: 5px;">
            <div class="zljk-tjzl-ins">有四种添加专利的方式 ：1、添加申请号 2、添加权利人 3、添加权利人地址 4、添加代理机构</div>
            <form>
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#shenqxiang" data-toggle="tab">添加申请项</a></li>
                    <li><a href="#quanliren" data-toggle="tab">添加权利人</a></li>
                    <li><a href="#dizhi" data-toggle="tab">添加权利人地址</a></li>
                    <li><a href="#dailijigou" data-toggle="tab">添加代理机构</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="shenqxiang">
                        <div class="sqx">
                        <textarea id="applistOne" type="text" class="AddAppnoTA"></textarea>
                        <p>注意：一行一个申请号，可以添加多行。请勿一行多个申请号！</p>
                        <p>可监控 5 条，其中免费 5 条，购买 0 条，已使用 3 条，剩余 2 条可使用。</p>
                        <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="Search('One')">检索</button>
                        </div>
                        <div class="jiansuo">
                            <div class="bz">
                                <span>专利列表共 1 件专利，未检索到专利 0 件，检索到专利 1 件，其中 0 件无权专利，其中 1 件未监控，其中 0 件已监控</span>
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" style="height: 30px;line-height: 10px;" onclick="AddAnnual('One')">一键监控</button>
                            </div><br>
                                <div id="datagridOne" class="mini-datagrid" style="width:92%;height:250px;margin-left: 0px;" multiselect="true"
                                     pagesize="20" sizelist="[5,10,20,50,100,150,200]" sortfield="CREATETIME" sortorder="desc"
                                     autoload="true" onDrawCell="onDraw" allowresize="true">
                                    <div property="columns">
                                        <div type="checkcolumn" width="10"></div>
                                        <div type="indexcolumn" width="10" headerAlign="center">序号</div>
                                        <div field="shenqingh" width="20" headerAlign="center" allowSort="true" align="center">申请号</div>
                                        <div field="famingmc" width="50" headerAlign="center" allowSort="true" align="center">发明名称</div>
                                        <div field="shenqingr" width="10" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                                        <div field="shenqingrxm" width="50" headerAlign="center" allowSort="true" align="center">专利权人</div>
                                        <div field="dailirxm" width="10" headerAlign="center" allowSort="true" align="center">发明人</div>
                                        <div field="dailijgmc" width="10" headerAlign="center" allowSort="true" align="center">代理机构</div>
                                        <div field="dailijgmc" width="10" headerAlign="center" allowSort="true" align="center">代理机构</div>
                                        <div field="jkstatus" width="10" headerAlign="center" align="center" allowSort="true">监控状态</div>
                                        <div field="Operation" width="10" headerAlign="center" align="center">操作</div>
                                    </div>
                                </div>
                        </div>
                        <br>
                    </div>
                    <div class="tab-pane" id="quanliren">
                        <div class="index-base">
                            <div class="checkboxall">
                                <input type="checkbox"  value="中国发明"/>中国发明
                                <input type="checkbox"  value="中国实用新型"/>中国实用新型
                                <input type="checkbox"  value="中国外观设计"/>中国外观设计
                                <input type="checkbox"  value="中国发明"/>中国发明授权
                            </div>
                            <div class="index-searchbox">
                                <form>
                                    <ul>
                                        <li class="index-box"><input id="cnsearchboxTwo" placeholder="请输入权利人名称"></li>
                                        <li style="float: right;margin-top: -46px;"><input  class="index-cnbtn" value="检索" onclick="Search('Two')" ></li>
                                    </ul>
                                </form>
                            </div>
                        </div><br>
                        <div class="jiansuo">
                            <div class="bz">
                                <span>专利列表共 1 件专利，未检索到专利 0 件，检索到专利 1 件，其中 0 件无权专利，其中 1 件未监控，其中 0 件已监控</span>
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" style="height: 30px;line-height: 10px;" onclick="AddAnnual('Two')">一键监控</button>
                            </div><br>
                            <div id="datagridTwo" class="mini-datagrid" style="width:92%;height:250px;margin-left: 0px;" multiselect="true"
                                 pagesize="20" sizelist="[5,10,20,50,100,150,200]" sortfield="shenqingr" sortorder="desc"
                                 autoload="true" onDrawCell="onDraw" allowresize="true">
                                <div property="columns">
                                    <div type="checkcolumn" width="10"></div>
                                    <div type="indexcolumn" width="10" headerAlign="center">序号</div>
                                    <div field="shenqingh" width="20" headerAlign="center" allowSort="true" align="center">申请号</div>
                                    <div field="famingmc" width="50" headerAlign="center" allowSort="true" align="center">发明名称</div>
                                    <div field="shenqingr" width="10" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                                    <div field="shenqingrxm" width="50" headerAlign="center" allowSort="true" align="center">专利权人</div>
                                    <div field="dailirxm" width="10" headerAlign="center" allowSort="true" align="center">发明人</div>
                                    <div field="dailijgmc" width="10" headerAlign="center" allowSort="true" align="center">代理机构</div>
                                    <div field="jkstatus" width="10" headerAlign="center" align="center" allowSort="true">监控状态</div>
                                    <div field="Operation" width="10" headerAlign="center" align="center">操作</div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="tab-pane" id="dizhi">
                        <div class="index-base">
                            <div class="checkboxall">
                                <input type="checkbox"  value="中国发明"/>中国发明
                                <input type="checkbox"  value="中国实用新型"/>中国实用新型
                                <input type="checkbox"  value="中国外观设计"/>中国外观设计
                                <input type="checkbox"  value="中国发明"/>中国发明授权
                            </div>
                            <div class="index-searchbox">
                                <form>
                                    <ul>
                                        <li class="index-box"><input id="cnsearchboxThree" placeholder="请输入权利人地址"></li>
                                        <li style="float: right;margin-top: -46px;"><input class="index-cnbtn" value="检索" onclick="Search('Three')" ></li>
                                    </ul>
                                </form>
                            </div>
                        </div><br>
                        <div class="jiansuo">
                            <div class="bz">
                                <span>专利列表共 1 件专利，未检索到专利 0 件，检索到专利 1 件，其中 0 件无权专利，其中 1 件未监控，其中 0 件已监控</span>
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" style="height: 30px;line-height: 10px;" onclick="AddAnnual('Three')">一键监控</button>
                            </div><br>
                            <div id="datagridThree" class="mini-datagrid" style="width:92%;height:250px;margin-left: 0px;" multiSelect="true"
                                 pagesize="20" sizelist="[5,10,20,50,100,150,200]" sortfield="shenqingr" sortorder="desc"
                                 autoload="true" onDrawCell="onDraw" allowresize="true">
                                <div property="columns">
                                    <div type="checkcolumn" width="10"></div>
                                    <div type="indexcolumn" width="10" headerAlign="center">序号</div>
                                    <div field="shenqingh" width="20" headerAlign="center" allowSort="true" align="center">申请号</div>
                                    <div field="famingmc" width="50" headerAlign="center" allowSort="true" align="center">发明名称</div>
                                    <div field="shenqingr" width="10" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                                    <div field="shenqingrxm" width="50" headerAlign="center" allowSort="true" align="center">专利权人</div>
                                    <div field="dailirxm" width="10" headerAlign="center" allowSort="true" align="center">发明人</div>
                                    <div field="dailijgmc" width="10" headerAlign="center" allowSort="true" align="center">代理机构</div>
                                    <div field="jkstatus" width="10" headerAlign="center" align="center" allowSort="true">监控状态</div>
                                    <div field="Operation" width="10" headerAlign="center" align="center">操作</div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="tab-pane" id="dailijigou">
                        <div class="index-base">
                            <div class="checkboxall">
                                <input type="checkbox"  value="中国发明"/>中国发明
                                <input type="checkbox"  value="中国实用新型"/>中国实用新型
                                <input type="checkbox"  value="中国外观设计"/>中国外观设计
                                <input type="checkbox"  value="中国发明"/>中国发明授权
                            </div>
                            <div class="index-searchbox">
                                <form>
                                    <ul>
                                        <li class="index-box"><input id="cnsearchboxFour" placeholder="请输入代理机构名称"></li>
                                        <li style="float: right;margin-top: -46px;"><input class="index-cnbtn"  value="检索" onclick="Search('Four')" ></li>
                                    </ul>
                                </form>
                            </div>
                        </div><br>
                        <div class="jiansuo">
                            <div class="bz">
                                <span>专利列表共 1 件专利，未检索到专利 0 件，检索到专利 1 件，其中 0 件无权专利，其中 1 件未监控，其中 0 件已监控</span>
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" style="height: 30px;line-height: 10px;" onclick="AddAnnual('Four')">一键监控</button>
                            </div><br>
                            <div id="datagridFour" class="mini-datagrid" style="width:92%;height:250px;margin-left: 0px;" multiSelect="true"
                                 pagesize="20" sizelist="[5,10,20,50,100,150,200]" sortfield="shenqingr" sortorder="desc"
                                 autoload="true" onDrawCell="onDraw" allowresize="true">
                                <div property="columns">
                                    <div type="checkcolumn" width="10"></div>
                                    <div type="indexcolumn" width="10" headerAlign="center">序号</div>
                                    <div field="shenqingh" width="20" headerAlign="center" allowSort="true" align="center">申请号</div>
                                    <div field="famingmc" width="50" headerAlign="center" allowSort="true" align="center">发明名称</div>
                                    <div field="shenqingr" width="10" dataType="date" dateFormat="yyyy-MM-dd" headerAlign="center" align="center">申请日</div>
                                    <div field="shenqingrxm" width="50" headerAlign="center" allowSort="true" align="center">专利权人</div>
                                    <div field="dailirxm" width="10" headerAlign="center" allowSort="true" align="center">发明人</div>
                                    <div field="dailijgmc" width="10" headerAlign="center" allowSort="true" align="center">代理机构</div>
                                    <div field="jkstatus" width="10" headerAlign="center" align="center" allowSort="true">监控状态</div>
                                    <div field="Operation" width="10" headerAlign="center" align="center">操作</div>
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridOne = null,gridTwo = null,gridThree = null,gridThree = null;
    $(function () {
        gridOne = mini.get("datagridOne");
        gridTwo = mini.get("datagridTwo");
        gridThree = mini.get("datagridThree");
        gridFour = mini.get("datagridFour");
    });
    function Search(num){
        var Filelds = "";
        var word = "";
        if (num == "One"){
            Filelds = "shenqingh";
            var shenqinghs = $("#applistOne").val();
            word = shenqinghs.split(/[\s\n]/);
            var url = '/Annual/getByShenqinghsIn?Filelds='+ Filelds +'&word=' + word;
            gridOne.setUrl(url);
            gridOne.reload();
        }else if (num == "Two"){
            Filelds = "shenqingrxm";
            word = $("#cnsearchboxTwo").val();
            var url = '/Annual/query?Filelds='+ Filelds +'&word=' + word;
            gridTwo.setUrl(url);
            gridTwo.reload();
        }else if (num == "Three"){
            Filelds = "address";
            word = $("#cnsearchboxThree").val();
            var url = '/Annual/query?Filelds='+ Filelds +'&word=' + word;
            gridThree.setUrl(url);
            gridThree.reload();
        }else if (num == "Four"){
            Filelds = "dailijgmc";
            word = $("#cnsearchboxFour").val();
            var url = '/Annual/query?Filelds='+ Filelds +'&word=' + word;
            gridFour.setUrl(url);
            gridFour.reload();
        }
    }

    function onDraw(e) {
        var field = e.field;
        var record = e.record;
        if (field == "Operation"){
            var jkstatus = record["jkstatus"];
            var shenqingh =record["shenqingh"];
            if (jkstatus == "未监控"){
                e.cellHtml = '<a href="javascript:void(0)" onclick="AddSingleAnnual(' + "'" + shenqingh + "'" +')">监控</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="SingleRemove(' + "'" + shenqingh + "'" +')">查看</a>'
            }else {
                e.cellHtml = '<a href="javascript:void(0)" onclick="SingleRemove(' + "'" + shenqingh + "'" +')">查看</a>'
            }
        }
        if(field == "shenqingr"){
            var shenqingr = record["shenqingr"];
            var value = new Date(shenqingr);
            e.cellHtml = mini.formatDate(value,"yyyy-MM-dd");
        }
    }
    
    function AddSingleAnnual(shenqingh) {
        var shenqinghs = [shenqingh];
        mini.confirm("确定监控所选的专利吗？","系统提示",function (action) {
            if (action == "ok"){
                var url = "/Annual/addannual";
                $.ajax({
                    contentType:'application/json',
                    method:'post',
                    url:url,
                    data:mini.encode(shenqinghs),
                    success:function (r) {
                        if (r['success']) {
                            mini.alert("监控专利成功！",'添加专利监控',function () {
                                Search("One");
                                Search("Two");
                                Search("Three");
                                Search("Four");
                            });
                        }
                        else mini.alert("监控专利失败！");
                    },
                    failure:function (error) {
                        alert(error);
                    }
                })
            }
        });
    }

    function AddAnnual(num) {
        var shenqinghs = [];
        switch (num) {
            case "One":
                var rows = gridOne.getSelecteds();
                for (var i = 0; i < rows.length; i++) {
                    shenqinghs.push(rows[i]["shenqingh"]);
                }
                if (shenqinghs.length == 0) {
                    mini.alert('请选择要监控的记录!');
                    return;
                }
                break;
            case "Two":
                var rows = gridTwo.getSelecteds();
                for (var i = 0; i < rows.length; i++) {
                    shenqinghs.push(rows[i]["shenqingh"]);
                }
                if (shenqinghs.length == 0) {
                    mini.alert('请选择要监控的记录!');
                    return;
                }
                break;
            case "Three":
                var rows = gridThree.getSelecteds();
                for (var i = 0; i < rows.length; i++) {
                    shenqinghs.push(rows[i]["shenqingh"]);
                }
                if (shenqinghs.length == 0) {
                    mini.alert('请选择要监控的记录!');
                    return;
                }
                break;
            case "Four":
                var rows = gridFour.getSelecteds();
                for (var i = 0; i < rows.length; i++) {
                    shenqinghs.push(rows[i]["shenqingh"]);
                }
                if (shenqinghs.length == 0) {
                    mini.alert('请选择要监控的记录!');
                    return;
                }
                break;
        }
        mini.confirm("确定监控所选的专利吗？","系统提示",function (action) {
            if (action == "ok"){
                var url = "/Annual/addannual";
                $.ajax({
                    contentType:'application/json',
                    method:'post',
                    url:url,
                    data:mini.encode(shenqinghs),
                    success:function (r) {
                        if (r['success']) {
                            mini.alert("监控专利成功！",'添加专利监控',function () {
                                Search("One");
                                Search("Two");
                                Search("Three");
                                Search("Four");
                            });
                        }
                        else mini.alert("监控专利失败！");
                    },
                    failure:function (error) {
                        alert(error);
                    }
                })
            }
        });
    }
</script>
</body>
</html>
