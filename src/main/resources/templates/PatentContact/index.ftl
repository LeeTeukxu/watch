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
                <p><span></span>&nbsp; </p>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div style="float: right;width: 98%;margin-top: -785px;background: white;margin-right: 19px;border-radius: 5px;">
            <div class="zljk-tjzl-ins"></div>
            <form>
                <div class="tab-content">
                    <div class="tab-pane active" id="shenqxiang">
                        <div class="sqx">
                        <textarea id="Mail" name="Mail" type="text" class="AddAppnoTA"></textarea>
                        <p>注意：一行一个邮箱，可以添加多行。请勿一行多个邮箱！</p>
                        <p></p>
                        <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" onclick="save()">添加</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    function save () {
        var data = $("#Mail").val().split(/[\s\n]/);
        var url = "/PatentContact/save";
        $.post(url,{Mail:mini.encode(data)},function(r){
            if (r['success']) {
                mini.alert("保存成功！",'删除提示',function () {
                });
            } else mini.alert("保存失败，" + r.message + "！");
        });
    }
</script>
</body>
</html>
