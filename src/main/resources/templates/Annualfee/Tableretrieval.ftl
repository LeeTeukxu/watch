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

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>




    <style>


        .menu, .tab-box {
            width: 300px;
            height: 100%;
            margin: 0 auto;
            padding-top: 25px;
            background: #fff;
        }

        .menu ul li { /*height:45px; */
            line-height: 56px;
            border-bottom: 1px solid #eee;
            font-size: 15px;
        }

        .menu ul li span {
            display: block;
            height: 100%;
            width: 100%;
            position: relative;
            padding: 0 20px;
        }

        .menu ul li span i {
            display: block;
            height: 20px;
            width: 20px;
            background: url(/img/arr.png) no-repeat center center;
            background-size: 80% 80%;
            position: absolute;
            right: 20px;
            top: 50%;
            margin-top: -10px;
            transition: all 0.3s ease-out;
            transform: rotate(0deg);
        }

        .menu ul li span i.action {
            transition: all 0.3s ease-out;
            transform: rotate(180deg);
        }

        .menu ul li > dl {
            display: none;
            padding-left: 35px;
            background: #d8e7f5;
        }

        .menu ul li > dl a {
            display: block;
            height: 100%;
            width: 100%;
            font-size: 14px;
            color: #222;
            text-decoration: none;
        }

        .tab-box {
            width: 600px;
            margin-top: 40px;
        }

        .tab-box .tab {
            border-bottom: 1px solid #eee;
        }

        .tab-box .tab ul li {
            width: 33.3%;
            float: left;
            height: 55px;
            line-height: 55px;
            text-align: center;
        }

        .tab-box .tab ul li.action {
            background: #d8e7f5;
        }

        .tab-box .box {
            width: 100%;
            height: 200px;
        }

        .tab-box .box .item {
            display: none;
            padding: 15px;
            height: 200px;
        }

        .tab-box .box .item:first-child {
            display: block;
        }

        /*检索详细框*/
        .Ex searchsub {
            width: 993px;
            height: 87%;
            overflow-y: auto;
            z-index: 9999;
            position: absolute;
            top: 98px;
            left: 309px;
            border: solid 2px #ddd;
            border-bottom-color: #c7c4be;
            border-right-color: #c7c4be;
            background: white;
        }

        .Exsearchsub .exscolose {
            float: right;
            font-family: "SimSun", "宋体";
            font-size: 13px;
            color: #0C0C0C;
            margin-right: 10px;
            margin-top: 10px;
        }

        .Exsearchsub .anniuzu {
            margin-top: 23px;
            margin-left: 20px;
        }

        .Exsearchsub .anniuzu .maxniun .btn-warning {
            height: 23px;
            line-height: 10px;
        }

        .Exsearchsub .anniuzu .option {
            width: 84%;
            float: left;
            margin-left: 116px;
            margin-top: -32px;
        }

        .Exsearchsub .anniuzu .option ul li {
            float: left;
            border: solid 1px #CCC;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            height: 23px;
            line-height: 22px;
            overflow: hidden;
            margin: 8px 10px 0 0;
            padding: 0px 5px;
            color: black;
            font-size: 14px;
            font-family: auto;
        }

        .Exsearchsub .anniuzu .option ul li span {
            margin-left: 5px;
            cursor: pointer;
        }

        .checkfx .xk {
            float: left;
            margin-left: 20px;
        }

        .FloatClear {
            width: 100%;
            clear: both;
            height: 0px;
            overflow: hidden;
        }

        .jiansuokuang {
            border: 1px solid #e4e4e4;
            width: 98.2%;
            background: white;
            color: #0C0C0C;
            margin-top: 10px;
            margin-left: 10px;
            height: auto !important;
            min-height: 100px;
        }

        .EXLogic {
            width: 96%;
            margin: auto;
            height: 30px;
            padding: 18px 0;
        }

        .EXLogic table {
            float: left;
            border-collapse: collapse;
        }

        .EXLogic table td {
            height: 26px;
            color: #333;
            border: solid 1px #DDD;
            cursor: pointer;
            text-align: center;
            width: 50px;
            padding: 2px 7px;
            font-size: 13px;
            font-family: auto;
        }

        .EXitemkey {
            width: 97%;
            margin: 0px auto;
            margin-top: 44px;
            font-size: 13px;
            font-family: auto;
            font-weight: bold;
        }

        .EXitemkey .Exitem1 {
            float: left;
            width: 50%;
            line-height: 38px;
        }

        .EXitemkey .Exitem2 {
            float: right;
            width: 50%;
            line-height: 38px;
        }

        .EXRNa {
            width: 100%;
        }

        .EXitemkeyname {
            float: left;
            font-size: 11px;
            color: black;
        }

        .EXitemkeysub {
            color: #f46b21;
            margin-left: 30%;
            font-size: 11px;
            cursor: pointer;
            text-decoration: underline;
        }

        .EXitemkeytest {
            float: right;
            color: #AAA;
            margin-top: -49px;
            margin-right: 18%;
            width: 29%;
            font-size: 11px;
        }

        .xk p {
            font-size: 14px;
            color: black;
        }

  /*文本框*/
        .searchF-keylist {
            padding: 10px 0;
        }
        .searchF-keyitem {
            width: 100%;
            margin: 10px auto;
        }
        .searchF-keyitem td {
            border: 0;
            border-collapse: collapse;
            white-space: nowrap;
        }
        .searchF-keyitem .boder1 {
            border: solid 1px #c7c4be;
            border-collapse: collapse;
        /*    white-space: initial;*/
        }
        .searchF-keyitem .select1 {
             width: 100%;
             height: 28px;
             border: 0;
             font-family: 'Microsoft YaHei';
         }
        .searchF-keyitem .input1 {
            width: 98%;
            height: 28px;
            border: 0;
            margin: auto;
            text-indent: 10px;
        }
        .mini-buttonedit-border{border: none;padding-top: 3px;}
    </style>
</head>
<body style="overflow: hidden;background: white;">
<iframe style="width: 100%;border: none;overflow: hidden;height: 35px;" src="/searchResult/menu"></iframe>

<div class="Exsearchsub" id="Exsearchsub" style="background: white;position: absolute;left: 13px;top: 63px;width: 99%;height: 813px;overflow: hidden auto;border: 1px solid #cacacac7;">
    <div class="exscolose" onclick="Exsearclose()">关闭</div>
    <div class="anniuzu">
        <div class="maxniun">
            <button type="button" class="btn btn-warning" onclick="Xzjs()">选择检索范围</button>
        </div>
        <div class="option">
            <ul>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
                <li>中国发明申请<span onclick="searchFC(this)">X</span></li>
            </ul>
        </div>

        <div class="checkfx" id="checkfx" style="display: none;" title="未展开">
            <div class="xk">
                <p><input class="mini-checkbox">中国</p>
                <p>
                    <input class="mini-checkbox">中国发明申请
                    <input class="mini-checkbox">中国实用新型
                    <input class="mini-checkbox">中国外观设计
                    <input class="mini-checkbox">中国发明授权
                </p>
            </div>
        </div>
    </div>
    <br>
    <div class="FloatClear" style="border-bottom:solid 1px #ddd;margin-top: 10px;"></div>

    <form>
        <div class="searchF-keylist" id="MainDiv">
            <table class="searchF-keyitem" border="0" cellspacing="0" cellpadding="0" id="tab1">
                <tbody><tr>
                    <td width="80" align="center">&nbsp;</td>
                    <td class="boder1" width="140" align="center">
                        <select class="select1" id="Field1"></select>
                    </td>
                    <td class="boder1" align="left">
                        <input class="input1" type="text" id="content1">
                    </td>
                </tr>
                </tbody></table>
            <table class="searchF-keyitem" border="0" cellspacing="0" cellpadding="0" id="tab2">
                <tbody><tr>
                    <td width="80" align="center" style="border: 1px solid #9e9a9a;">
                        <select class="select1" style="width: 80px" id="TJ1"></select>
                    </td>
                    <td class="boder1" width="140" align="center">
                        <select class="select1" id="Field2"></select>
                    </td>
                    <td class="boder1" align="left">
                     <input type="date" id="date-A2">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        TO
                        <input type="date" id="date-B2">
                    </td>
                </tr>
                </tbody></table>
               <div class="tianjiaaniu" style="margin-left: 80px;" id="AddDiv">
                   <button type="button" class="btn btn-default" aria-label="Left Align" style="width: 109px;" onclick="AppendTab()">
                       <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                       添加
                   </button>
               </div>
        </div>
    </form>

    <div class="jiansuoanniu" style="text-align: center;    margin-top: 10px;">
        <button type="button" style="width: 12%;" class="btn btn-success" onclick="Search()">检索</button>
        &nbsp;&nbsp;&nbsp;
        <button type="button" style="width: 12%;" class="btn btn-default">重置</button>
    </div>
</div>
<script>
    mini.parse();
    var ListField = ["申请号","申请日","主法律状态","辅法律状态","公开（公告）号","公开（公告）日","申请人","地址","当前申请人","主分类号","发明人","名称","分类号","优先权","摘要","代理人","名称，摘要","代理机构"];
    var ListFieldKeyVal = {"申请号":"shenqingh","申请日":"shenqingr","公开（公告）号":"gonkaihao","公开（公告）日":"gonkair","名称":"famingmc","摘要":"memo","名称，摘要":"FMC,ZY",
        "申请人":"prishenqingrxm","当前申请人":"shenqingrxm","地址":"address","发明人":"famingrxm",
        "代理机构":"dailijgmc","代理人":"dailirxm","主分类号":"pipc","分类号":"pic","主法律状态":"lawstatus","优先权":"youxianq"};
    var RQ = "申请日,公开（公告）日";
    var ListTJ = ["AND","OR","NOT"];
    var ListFLZT = ["全部","审中","有权","无权"];

    $(function () {
        AddFieldOption();
        AddTJOption();
        SelectChange();
        $("#Field2").val("申请日");
    });
    function AddFieldOption() {
        var FieldId = "";
        var tabNum = $('#MainDiv').children().length - 1;
        for (var i=1;i<=tabNum;i++) {
            $("select[id='Field" + i +"']").each(function () {
                FieldId = $(this)[0];
                var addFieldOption = function (FieldId, txt, val) {
                    FieldId.add(new Option(txt, val));
                };
                for (var i = 0; i < ListField.length; i++) {
                    addFieldOption(FieldId, ListField[i], ListField[i]);
                }
            });
        }
    }
    function AddTJOption() {
        var tabNum = $('#MainDiv').children().length - 2;
        var TJId = "";
        for (var i=1;i<=tabNum;i++) {
            $("select[id='TJ" + i +"']").each(function () {
                TJId = $(this)[0];
                var addTJOption = function (TJId, txt, val) {
                    TJId.add(new Option(txt, val));
                };
                for (var i = 0; i < ListTJ.length; i++) {
                    addTJOption(TJId, ListTJ[i], ListTJ[i]);
                }
            });
        }
    }
    function AddFLZTOption() {
        var tabNum = $('#MainDiv').children().length-1;
        var FLZTId = "";
        for (var i=1;i<=tabNum;i++) {
            $("select[id='FLZT" + i +"']").each(function () {
                FLZTId = $(this)[0];
                var addFLZTOption = function (FLZTId, txt, val) {
                    FLZTId.add(new Option(txt, val));
                };
                for (var i = 0; i < ListFLZT.length; i++) {
                    addFLZTOption(FLZTId, ListFLZT[i], ListFLZT[i]);
                }
            });
        }
    }
    function Xzjs() {
        var checkfxtitle = document.getElementById("checkfx").title;
        if (checkfxtitle == "未展开") {
            document.getElementById("checkfx").style.display = "";
            document.getElementById("checkfx").title = "已展开";
        } else if (checkfxtitle == "已展开") {
            document.getElementById("checkfx").style.display = "none";
            document.getElementById("checkfx").title = "未展开";
        }
    }
    function SelectChange() {
        var tab = $('#MainDiv').children();
        for (var i=1;i<=tab.length-1;i++) {
            $("#tab" + i +" tr").each(function () {
                var selectId = "";
                $(this).children("td").each(function (j) {
                    if (selectId != ""){
                        var content = $("#tab" + i + " tr").children("td").eq(2);
                        var currentNum = i;
                        $("#" + selectId).change(function () {
                            content.html("");
                            if (RQ.indexOf($("#" + selectId).val()) > -1){
                                content.html('<input type="date" id="date-A' + currentNum + '">' +
                                    '                        &nbsp;&nbsp;&nbsp;&nbsp;' +
                                    '                        TO' +
                                    '                        <input type="date" id="date-B' + currentNum + '">');
                            }else if ($("#" + selectId).val() == "主法律状态"){
                                content.html('<select class="select1" style="width: 100%" id="FLZT' + currentNum + '"></select>');
                                var FLZTId = document.getElementById("FLZT" + currentNum);
                                var addFLZTOption = function (FLZTId, txt, val) {
                                    FLZTId.add(new Option(txt, val));
                                }

                                for (var i=0; i < ListFLZT.length; i++){
                                    addFLZTOption(FLZTId, ListFLZT[i], ListFLZT[i]);
                                }
                            }
                            else {
                                content.html('<input class="input1" type="text" id="content' + currentNum + '">');
                            }
                        });
                    }

                    $(this).children().each(function () {
                        if ($(this)[0].nodeName == "SELECT" && $(this)[0].id.indexOf("Field") > -1){
                            selectId = $(this)[0].id;
                        }
                    });
                })
            });
        }
    }
    function fmck() {
        document.getElementById("Exsearchsub").style.display = "";
    }

    function Exsearclose() {
        document.getElementById("Exsearchsub").style.display = "none";
    }
    function AppendTab() {
        var tabNum = $('#MainDiv').children().length-1;
        var strTab = '<table class="searchF-keyitem" border="0" cellspacing="0" cellpadding="0" id="tab' + parseInt(tabNum + 1) +'">' +
            '                <tbody><tr>' +
            '                    <td width="80" align="center" style="border: 1px solid #9e9a9a;">' +
            '                        <select class="select1" style="width: 80px" id="TJ' + parseInt(tabNum) +'"></select>' +
            '                    </td>' +
            '                    <td class="boder1" width="140" align="center">' +
            '                        <select class="select1" id="Field' + parseInt(tabNum + 1) +'"></select>' +
            '                    </td>' +
            '                    <td class="boder1" align="left">' +
            '                     <input class="input1" type="text" id="content' + parseInt(tabNum + 1) +'">'+
            '                    </td>' +
            '                </tr>' +
            '                </tbody></table>' +
            '<div class="tianjiaaniu" style="margin-left: 80px;" id="AddDiv">' +
            '                   <button type="button" class="btn btn-default" aria-label="Left Align" style="width: 109px;" onclick="AppendTab()">' +
            '                       <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>' +
            '                       添加' +
            '                   </button>' +
            '               </div>';
        $("#MainDiv #AddDiv").remove();
        $("#MainDiv").append(strTab);
        var FieldId = document.getElementById("Field" + parseInt(tabNum + 1));
        var TJId = document.getElementById("TJ" + parseInt(tabNum));

        var addFieldOption = function (FieldId, txt, val) {
            FieldId.add(new Option(txt, val));
        };
        for (var i = 0; i < ListField.length; i++) {
            addFieldOption(FieldId, ListField[i], ListField[i]);
        }

        var addTJOption = function (TJId, txt, val) {
            TJId.add(new Option(txt, val));
        };

        for (var i = 0; i < ListTJ.length; i++) {
            addTJOption(TJId, ListTJ[i], ListTJ[i]);
        }

        SelectChange();
    }

    function Search() {
        var tab = $('#MainDiv').children();
        var lists = new Array();
        for (var i=1;i<=tab.length-1;i++) {
            $("#tab" + i +" tr").each(function () {
                var list = new Array();
                $(this).children().each(function (j) {
                    var ch = $(this).children();
                    if (ch.length > 0){
                        if (j == 0){
                            list.push($("#" + ch[0].id).val())
                        }
                    }else list.push("");
                    if (j == 1){
                        $.each(ListFieldKeyVal,function (k,v) {
                            if (k == $("#" + ch[0].id).val()){
                                list.push(v);
                            }
                        })
                    }else if (j == 2){
                        if (ch.length > 1){
                            var dateval = "";
                            for (var i=0;i<ch.length;i++){
                                dateval += $("#" + ch[i].id).val() + ",";
                            }
                            dateval = dateval.substring(0,dateval.length - 1);
                            list.push("{" + dateval + "}");
                        }else list.push($("#" + ch[0].id).val());
                    }
                });
                lists.push(list);
            });
        }
        if (lists.length > 0){
            var strfinal = "";
            var strAND = "";
            var strOR = "";
            var strNOT = "";
            var strIndex = "";
            var andIndex = 0;
            var orIndex = 0;
            var notIndex = 0;
            strIndex = lists[0][1] + "=" + lists[0][2];
            for (var i = 1; i < lists.length; i++) {
                if (lists[i][0] == "AND") {
                    andIndex++;
                    strAND += " " + lists[i][0] + " " + lists[i][1] + "=" + lists[i][2];
                } else if (lists[i][0] == "OR") {
                    orIndex++;
                    strOR += " " + lists[i][0] + " " + lists[i][1] + "=" + lists[i][2];
                } else if (lists[i][0] == "NOT") {
                    notIndex++;
                    strNOT += " " + lists[i][0] + " " + lists[i][1] + "=" + lists[i][2];
                }
            }

            if (lists.length <= 2){
                //如果只有两个查询条件，不需要加括号
                strfinal = lists[0][1] + "=" + lists[0][2] + " " + lists[1][0] + " " + lists[1][1] + "=" + lists[1][2];
            }else {
                if (strNOT == "") {
                    strfinal = "(" + strIndex + strAND + ")" + strOR;
                } else strfinal = "((" + strIndex + strAND + ") " + strOR + ")" + strNOT;
            }
            window.location.href='/searchResult/tabsearchindex?strFinal='+encodeURIComponent(strfinal)+'&searchPage=TableSearch';
        }
    }

    function doSearch(word,pageSize, pageIndex, sortField, sortOrder){
        var url = '/searchResult/search?pageSize=' + pageSize + '&pageIndex=' + pageIndex + '&sortOrder=' + sortOrder
            + '&sortField=' + sortField;
        $.post(url, {words: word}, function (result) {
            if (result.success) {
                var data = result.data || [];
                var total = parseInt(result.total);
                $('#resultBox').empty();
                $('#resultBox').append(data);
                $('#paging').paging({
                    nowPage: pageIndex + 1,
                    allPages: Math.ceil(total / pageSize),
                    displayPage: 7,
                    numbers: total,
                    pageChanged: function (pageNum) {
                        pageIndex = pageNum - 1;
                        doSearch(word,pageSize, pageIndex, sortField, sortOrder);
                    }
                });
                $('.clumsbg').height($('.leftPanel').height() - $('.tool').height()-35);
                if (total > 0) {
                    $('#error').hide();
                    $('#timeSpan').text("共检索到:" + total + '条记录,用时:' + result.time + '毫秒');
                    $('#paging').show();
                } else {
                    $('#error').show();
                    $('#timeSpan').text("");
                    $('#paging').hide();
                }
            }
        })
    }
</script>
</body>
</html>
