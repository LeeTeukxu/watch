<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>订单支付</title>
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
    <script type="text/javascript" src="/js/jquery.cityselect.js"></script> <!--省市区js-->

    <style>
        .regitem{text-align: center;margin-top: 49px;}
        #ssq{display: inline;}
        .tite{float: left;margin-left: 36px;font-family: auto;}
        #ssq select{width: 19%;margin-top: -5px;height: 48px;border: 1px solid #c5c2c2a6;}
        .regitem .witm{width: 58%;margin-top: 20px;height: 32px;border: 1px solid #c5c2c2a6;margin-left: -16px;height: 48px;}
        .anniu{margin-top: 54px; text-align: center;}
        .mini-textbox-border{height: 30px;padding-top: 4px}
        .titlenr{margin-left: 37px;padding-top: 18px;}
        .titlenr .form__headline{font-family: auto;font-size: 23px;display: inline;}
        .titlenr .form__required-label{background-color: #8e8e8e4a;
            border-radius: 3px;
            font-size: 10px;
            font-weight: normal;
            letter-spacing: 0.08em;
            padding: 0.35em 0.6em;
            font-family: auto;}
        .titlenr .form__description p{font-family: auto;margin-top: 33px;}
        .po{margin-top: 10px;}
        .button {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: var(--gray-color-tertiary);
            border: 0;
            border-radius: 3px;
            box-shadow: 2px 2px 3px 0 var(--gray-color-primary);
            color: #fff;
            cursor: pointer;
            display: inline-flex;
            letter-spacing: 0.08em;
            line-height: inherit;
            padding: 1em 4em;
            transition: background-color 0.3s;
            background: #0000008c;
        }
        .po:focus {
            outline:none;
            border: 1px solid red;
        }
        input::-webkit-input-placeholder{color: #c5c2c2a6;font-weight: bold}
        input:-moz-placeholder{color: #c5c2c2a6;font-weight:bold}
        input::-moz-placeholder{color: #c5c2c2a6;font-weight:bold}
        input::-ms-input-placeholder{color:#c5c2c2a6;font-weight:bold}
        .mini-buttonedit-border{height: 33px;padding-top: 13px;}
        .mini-buttonedit-icon{padding-top: 8px;}
        .witm:focus{border: 2px solid #2c79afa6;}
        .mini-panel-header{    background: rgba(27, 26, 26, 0.85);
            color: rgb(255, 255, 255);
            height: 34px;
            padding: 5px;}
    </style>
</head>
<body style="overflow-y: auto;overflow-x: hidden; background: #fff;">
<div class="titlenr">
<h1 class="form__headline" >${Famingmc!}</h1><p style="display: inline"> - ${Prishenqingrxm!}</p>
<p style="margin-top: 6px;"><strong class="form__required-label">${SHENQINGH!}</strong></p>
<#--<div class="form__description">

</div>-->
</div>
      <div class="regitem">
          <div id="ssq">
             <div class="tite" style="margin-top: 8px;">省市区</div>
             <select id="prov" class="prov ao"></select>
             <select id="city" class="city ao" disabled="disabled"></select>
             <select id="dist" class="dist ao" disabled="disabled"></select>
          </div>
          <div class="">
              <div class="daili po"><div class="tite" style="margin-top: 28px;">代理机构</div><input id="Dailijgmc" class="witm" type="text" value="${Dailijgmc!}" placeholder=" 请正确填写代理机构"  ></div>
              <div class="clli po"><div class="tite" style="margin-top:36px;">客户</div><input id="ClientName" emptyText="选择客户" class="mini-buttonedit witm" name="ClientName" onbuttonclick="onButtonEdit" onclick="showAllCliName(this)" style="width:58%;height: 50px;margin-left: 14px;margin-top: 24px;"  value="${ClientName!}"/></div>
              <div class="clli po"><div class="tite" style="margin-top: 32px;">申请人地址</div><input id="Address" style="margin-top: 21px;margin-left: -36px;" class="witm" type="text" value="${Address!}" placeholder=" 请输入申请人地址"></div>
          </div>
      </div>

      <div class="anniu">
          <button class="button" type="submit" onclick="insertinfo()">保存修改</button>
          <#--<a class="mini-button" style="width:60px;" onclick="insertinfo()">保存修改</a> -->
          <#--<button type="button" class="btn btn-success" onclick="insertinfo()">确认</button>-->
      </div>

    <script>
        mini.parse();
        $(document).ready(function() {
            //默认
            var pro="${ProvinceName!}";
            pro = pro.replace("省","");
            var ctiy="${CityName!}";
            ctiy = ctiy.replace("市","");
            var dist="${CountyName!}";//


            $("#ssq").citySelect({
                prov:""+pro+"",
                city:""+ctiy+"",
                dist:""+dist+"",
                nodata: "none"
            });

           var ClientName= mini.get("ClientName");
           ClientName.setValue("${ClientId!}");
           ClientName.setText("${ClientName!}");
        })

        function CloseWindow(action){
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }



        function insertinfo() {
            if(mini.get("ClientName").getValue()!="" && document.getElementById("prov").value!=""){
                var prov=$("#prov option:selected").val()+"省";
                var city=$("#city option:selected").val()+"市";
                var dist=$("#dist option:selected").val();
                var Dailijgmc=$("#Dailijgmc").val();
                var ClientName=mini.get("ClientName").getText();
                var CID=mini.get("ClientName").getValue().toString();
                var ClientID=CID.replace(",","");
                var Address=$("#Address").val();
                var SHENQINGH="${SHENQINGH!}";//
                var arg={ProvinceName:prov,CityName:city,CountyName:dist,DAILIJGMC:Dailijgmc,ClientName:ClientName,ADDRESS:Address,SHENQINGH:SHENQINGH,ClientID:ClientID};
                var url="/main/patent/updateinfopate";
                $.getJSON(url, arg, function (result) {});
                CloseWindow("ok");
            }else if(mini.get("ClientName").getValue()==""){
                mini.alert("客户未输入！");
            }else if(document.getElementById("prov").value==""){
                mini.alert("请选择地区！");
            }
       }


       function showAllCliName(con) {
            mini.open({
                url: '/clientInfo/query?multiselect=false',
                showModal: true,
                width: 800,
                height: 300,
                title: '选择客户资料',
                ondestroy: function (action) {
                    if (action == 'ok') {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);
                        con.setValue(data.ClientID);
                        con.setText(data.Name);
                    }
                }
            });
        }
</script>
</body>
</html>