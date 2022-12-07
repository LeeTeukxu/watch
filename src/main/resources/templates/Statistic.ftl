<#include "/shared/layout.ftl">
<@layout>
    <link rel="stylesheet" href="/js/layui/css/layui.css" media="all"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/css/ionicons.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery.mCustomScrollbar.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/simple-line-icons.css"/>
    <link href="/css/index.css?Key=${version}" rel="stylesheet" type="text/css"/>
    <script src="/js/jquery.min.js"></script>
<#--    <script src="/js/jquery-ui.min.js"></script>-->
    <script type="text/javascript" src="/js/boot.js"></script>
    <script type="text/javascript" src="/js/common/excelExport.js?v=${version}"></script>
    <script type="text/javascript">
        var Statistics = [
            { id: 1, text: '订单时间'},
            { id: 2, text: '支付完成日期'}
        ];
    </script>
    <style type="text/css">
        .KJul {
            width: 160%;
            text-align: left
        }

        .KJul li {
            list-style: none;
            float: left;
            padding-left: 2%;
            padding-right: 3%;
            padding-top: 30px;
            padding-bottom: 30px;
            text-align: left;
        }

        .KJul li a {
            color: #7a7575;
        }

        .KJul li span {
            position: relative;
            top: 8px;
        }

        .KJul li img {
            width: 80px;
        }
    </style>
    <div style="height: 100%;overflow: hidden;">
        <div id="p1" style="border-top:0;height:72px;padding:5px;">
            <table style="width:100%;" id="highQueryForm">
                <tr id="trOrderTime">
                    <td style="width:6%;padding-left:10px;">统计类型：</td>
                    <td style="width:15%;">
                        <input class="mini-combobox" data="Statistics" onvaluechanged="onTypeChange" data-oper="EQ" style="width:100%" />
                    </td>
                    <td id="tdOrderTimeOne" style="width:15%;">
                        <input name="OrderTime" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                    </td>
                    <td id="tdOrderTimeTwo" style="width:6%;padding-left:10px;">到：</td>
                    <td id="tdOrderTimeThree" style="width:15%;">
                        <input name="OrderTime" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                    </td>
                    <td id="tdTimeEndOne" style="width:15%;">
                        <input name="Time_End" class="mini-datepicker" data-oper="GE" style="width:100%"/>
                    </td>
                    <td id="tdTimeEndTwo" style="width:6%;padding-left:10px;">到：</td>
                    <td id="tdTimeEndThree" style="width:15%;">
                        <input name="Time_End" class="mini-datepicker" data-oper="LE" style="width:100%"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:center;padding-top:5px;padding-right:20px;" colspan="8">
                        <button class="mini-button mini-button-success OrderList_Query" style="width:120px"
                                onclick="doHightSearch('highQueryForm')">搜索
                        </button>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div class="b2" style="float: left;width: 100%;margin-top: -18px">
                <div class="b2item">
                    <h3 class="box-title">订单状态</h3>
                    <Div class="b2itembox">
                        <div class="chartbox">
                            <div class="charboxL">
                                <div id="main" style="width:100%;height:280px; "></div>
                            </div>
                            <div class="charboxR" id="charboxC" style="float: right;margin-top: -170px;margin-right: 220px;">
                            </div>
                        </div>
                    </Div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/script" type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="textjavascript" src="/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="/js/custom.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/echarts.min.js"></script>
    <script type="text/javascript">
        mini.parse();

        $(function () {
            $("#tdTimeEndOne").hide();
            $("#tdTimeEndTwo").hide();
            $("#tdTimeEndThree").hide();
        });

        function onTypeChange(e) {
            if (e.value == "1") {
                $("#tdOrderTimeOne").show();
                $("#tdOrderTimeTwo").show();
                $("#tdOrderTimeThree").show();
                $("#tdTimeEndOne").hide();
                $("#tdTimeEndTwo").hide();
                $("#tdTimeEndThree").hide();
            }else if (e.value == "2") {
                $("#tdOrderTimeOne").hide();
                $("#tdOrderTimeTwo").hide();
                $("#tdOrderTimeThree").hide();
                $("#tdTimeEndOne").show();
                $("#tdTimeEndTwo").show();
                $("#tdTimeEndThree").show();
            }
        }

        function doHightSearch(highQueryForm) {
            var arg = {};
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
            if (result.length >= 3) {
                arg["High"] = mini.encode(result);
                var url = "/WeChatOrder/getStatistic";
                $.post(url, arg, function (text) {
                    var res = mini.decode(text);
                    if (res.success) {
                        getStatistic(res.data);
                    }
                })
            }else mini.alert("请选择统计内容");
        }

        function getStatistic(sta) {
            var myChart = echarts.init(document.getElementById('main'));
            var scale = 1;
            var echartData = sta;
            var rich = {
                yellow: {
                    color: "#ffc72b",
                    fontSize: 20 * scale,
                    padding: [5, 4],
                    align: 'center'
                },
                total: {
                    color: "#000",
                    fontSize: 20 * scale,
                    align: 'center'
                },
                white: {
                    color: "#fff",
                    align: 'center',
                    fontSize: 14 * scale,
                    padding: [10, 0]
                },
                blue: {
                    color: '#49dff0',
                    fontSize: 16 * scale,
                    align: 'center'
                },
                hr: {
                    borderColor: '#0b5263',
                    width: '100%',
                    borderWidth: 1,
                    height: 0,
                }
            }
            var option = {
                title: {
                    text: '全部专利',
                    left: 'center',
                    top: '53%',
                    padding: [1, 0],
                    textStyle: {
                        color: '#666',
                        fontSize: 20 * scale,
                        align: 'center'
                    }
                },
                legend: {
                    selectedMode: false,
                    formatter: function (name) {
                        var total = 0;
                        echartData.forEach(function (value, index, array) {
                            total += value.value;
                        });
                        return '{total|' + total + '}';
                    },
                    data: [echartData[0].name],
                    left: 'center',
                    top: 'center',
                    icon: 'none',
                    align: 'center',
                    textStyle: {
                        color: "#fff",
                        fontSize: 12 * scale,
                        rich: rich
                    }
                },
                series: [{
                    type: 'pie',
                    radius: ['52%', '60%'],
                    hoverAnimation: false,
                    color: ['#1b9cee', '#ff8651', '#ffc35a'],
                    label: {
                        show: true
                    },
                    data: echartData
                }]
            };

            var echartCon = "";
            for (var i=0;i<echartData.length;i++) {
                var name = echartData[i].name;
                var value = echartData[i].value;
                echartCon += '<div class="charboxRitem"><p class="charnamenum">' + value + '</p><p class="charname">' + name + '</p></div>';
            }
            $("#charboxC").html(echartCon);
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            window.addEventListener('resize', function () {
                myChart.resize();
            });
        }
    </script>
</@layout>