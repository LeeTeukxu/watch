<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" href="/js/miniui/themes/default/miniui.css"/>
    <link rel="stylesheet" href="/css/iconfont.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/js/boot.js"></script>
    <link rel="stylesheet" href="/css/tongyong.css"/>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <style>
        .IPInfo-topeno {
            padding: 30px 0 0 70px;
            font-size: 18px;
            font-weight: bold;
            color: #ababab;
            clear: both;
            font-family: auto;
        }
        .IPInfo-title {
            padding: 16px 0 0 70px;
            font-size: 16px;
            color: #333;
            clear: both;
            font-family: auto;
        }
        .nav li a{color: black;}
        .IPinfo-zlxmdiv {
            width: 49%;
            float: left;
        }
        .IPinfo-zlxmdiv1 {
            padding: 0 10px;
        }
        .IPinfo-zlxm p.lmt {
            width: 100%;
            line-height: 50px;
            white-space: nowrap;
            text-indent: 20px;
            background: url(/img/info_ico1.png) no-repeat left center;
            font-size: 14px;
            color: #333;
        }
        .IPinfo-zlxminfo {
            width: 100%;
        }
        .IPinfo-zlxminfo td {
            padding: 5px;
            vertical-align: top;
            line-height: 150%;
            border-bottom: solid 1px #f0f0f0;
            font-size: 14px;
            color: #544e4e;
            font-family: auto
        }
        .nowarp {
            white-space: nowrap;
        }
        .IPinfo-zlxmdiv {
            width: 49%;
            float: left;
        }
        .IPinfo-zlxmdiv1 {
            padding: 0 10px;
        }
      .lmt {
            width: 100%;
            line-height: 39px;
            white-space: nowrap;
            text-indent: 20px;
            background: url(/img/info_ico1.png) no-repeat left center;
            font-size: 14px;
            color: #333;
            font-family: auto;
        }
       .zhaiyao {
            font-size: 14px;
            color: balck;
            font-family: auto;
            color: #544e4e;
        }
        .IPInfo-absimg {
            width: 90%;
            margin: 10px 0;
            text-align: center;
            padding: 20px 0;
            max-width: 270px;
        }
        .IPInfo-absimg img {
            max-width: 100%;
            margin: auto;
        }
        .IPinfo-legal {
            width: 62%;
            margin: 60px auto;
        }
        .IPinfo-legal .flzt {
            border-collapse: collapse;
            color: #212121;
            font-family: sans-serif;
            width: 95%;
            background: #FFF;
            margin: 10px auto;
            font-size: 13px;
        }
        .IPinfo-legal .flzt {
            border-collapse: collapse;
            color: #444;
            width: 90%;
            background: #FFF;
            margin: 10px auto;
        }
        .IPinfo-legal .flzt td {
            border: solid 1px #bdbcbc;
            padding: 7px 5px 4px 5px;
            word-wrap: break-word;
        }
        .nowarp {
            white-space: nowrap;
        }
        .py{text-align: center;line-height: 54px;font-size: 24px;}
        .contrast{float: right;}
        .contrast .IPinfo-duibitxt {
            width: 180px;
            height: 28px;
            font-size: 14px;
            color: #a8a8a8;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            text-indent: 10px;
            border: 1px solid #a1a1a1;
            margin-right: 15px;
        }
        .contrast .IPinfo-duibibtn {
            width: 60px;
            height: 28px;
            font-size: 14px;
            color: #FFF;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            border: 1px solid #f36b21;
            text-align: center;
            background: #f36b21;
            font-weight: normal;
            line-height: 28px;
            cursor: pointer;
            float: right;
            margin-top: 0px;
        }
        .zljk-sipo-t {
            width: 100%;
            text-align: center;
            font-size: 20px;
            color: #333;
            padding: 20px 0 0 0;
        }
        .zljk-gk-list2 {
            width: 100%;
            border-left: 15px solid #FFF;
            border-right: 15px solid #FFF;
        }
        .zljk-gk-list2 th {
            border-bottom: solid 1px #e7e7e7;
            padding: 0px 0px;
            font-size: 18px;
        }
        .zljk-sipo-t {
            width: 100%;
            text-align: center;
            font-size: 20px;
            color: #333;
            padding: 20px 0 0 0;
        }
        .zljk-gk-list2 td {
            border-bottom: solid 1px #e7e7e7;
            padding: 0px 5px;
            font-size: 15px;
        }
    </style>

</head>
<body style="overflow: hidden;background: white;">
<div class="IPInfo-topeno">${datas.shenqingh}</div>
<div class="IPInfo-title">${datas.famingmc}</div>

<div style="width: 95%;margin: 0px auto;margin-top: 10px">
    <div class="contrast">
        <input id="number" type="text" title="请输入完整申请号：CN201120175431.3" class="IPinfo-duibitxt" placeholder="申请号CN201120175431.3">
        <h1 class="IPinfo-duibibtn" onclick="patentContrast()">对比</h1>
    </div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专利详情</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">法律状态</a></li>
        <li role="presentation"><a href="#monitoring" aria-controls="profile" role="tab" data-toggle="tab">年费监控</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <!--专利详情-->
        <div role="tabpanel" class="tab-pane active" id="home">
            <div class="IPinfo-zlxmdiv">
                <div class="IPinfo-zlxmdiv1">
                    <p class="lmt">著录项</p>
                    <table class="IPinfo-zlxminfo" border="0" cellspacing="0" cellpadding="0">
                        <tbody><tr>
                            <td class="nowarp" width="1">申请号</td>
                            <td id="sqh">${datas.shenqingh!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">申请日</td>
                            <td>${datas.shenqingr!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">公开（公告）号</td>
                            <td>${datas.gonkaihao!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">公开（公告）日</td>
                            <td>${datas.gonkair!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">主法律状态</td>
                            <td>${datas.lawstatus!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">次法律状态</td>
                            <td>${datas.seclawstatus!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">分类号</td>
                            <td>T-18</td>
                        </tr>
                        <tr>
                            <td class="nowarp">申请（专利权）人</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="nowarp">地址</td>
                            <td>${datas.priaddress!}</td>
                        </tr>
                    <tr>
                            <td class="nowarp">当前申请（专利权）人</td>
                            <td></td>
                        </tr>

                        <tr>
                            <td class="nowarp">当前地址</td>
                            <td>${datas.address!}</td>
                        </tr>
                         <tr>
                            <td class="nowarp">发明（设计）人</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="nowarp">优先权</td>
                            <td>${datas.youxianq!}</td>
                        </tr>
                     <tr>
                            <td class="nowarp">专利代理机构</td>
                            <td>${datas.dailijgmc!}</td>
                        </tr>
                        <tr>
                            <td class="nowarp">代理人</td>
                            <td>${datas.dailirxm!}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="IPinfo-zlxmdiv">
                <div class="IPinfo-zlxmdiv1">
                    <p class="lmt">摘要</p>
                    <div class="zhaiyao">
                     ${datas.memo!}
                    </div>
                    <#--
                    <div class="IPInfo-absimg">
                        <img src="http://221.194.47.218:8081/Home/AbstractImgN?filemessage=6950E3F9D5A2126999CAEFC976471B398BC41C29A82056BC6CAA9F98957E21B5D7FE840DDBF2407619992DED5AF22A05FD0248A0B8B878AE6E5860BC415A4842D7A9B9644FB92F3221CE88B999DF8AAC683E5475925CD6FC3FA411B242EABDF259ABB7FD7DC1F98E15E24D5ECAEF41CD76E69E60D28A13A1AADB472F1796FF5D">
                    </div>
                    -->
                </div>
            </div>
        </div>
        <!--法律状态-->
        <div role="tabpanel" class="tab-pane" id="profile">
            <div class="IPinfo-legal">
                <table border="0" cellspacing="0" cellpadding="0" class="flzt">
                    <tbody><tr>
                        <td align="right" valign="top" width="5%" class="nowarp">申请（专利）号</td>
                        <td width="44%">${datas.shenqingh!}</td>
                        <td align="right" valign="top" width="5%" class="nowarp">授权公告号</td>
                        <td width="45%"></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top" class="nowarp">法律状态公告日</td>
                        <td>${datas.gonkair!}</td>
                        <td align="right" valign="top" class="nowarp">法律状态类型</td>
                        <td>${datas.lawstatus!}</td>
                    </tr>
                    <tr>
                        <td colspan="4"><span>${datas.lawstatus!}<br></span></td>
                    </tr>
                    </tbody></table>
            </div>
        </div>
        <!--年费监控-->
        <div role="tabpanel" class="tab-pane py" id="monitoring">
            <iframe src="/searchResult/Paymentinformation?shenqingh=${datas.shenqingh!}" style="width: 100%;height: 623px;border: none;"/>
        </div>
</div>
</div>

<script>
   function patentContrast() {
    var number1=document.getElementById("sqh").innerHTML;
    var number2=document.getElementById("number").value;
       location.href="/searchResult/Patentcomparison?number1="+number1+"&number2="+number2;
   }
</script>
</body>
</html>
