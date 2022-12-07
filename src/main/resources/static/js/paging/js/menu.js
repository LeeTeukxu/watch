window.onload = function () {
    var flag = true;
    var liC = document.querySelectorAll(".navBox li h2");
    // 主导航nav点击事件
    for (var i = 0; i < liC.length; i++) {
        liC[i].onclick = function () {
            if (flag) {
                // 节流阀
                flag = false;
                setTimeout(function () {
                    flag = true;
                }, 500)
                // 自点
                if (this.className === "obFocus") {
                    this.querySelector("i").classList.remove("arrowRot");
                    getNext(this).style.height = "0";
                    this.classList.add("obtain");
                    this.classList.remove("obFocus");
                    return
                }

                parametersearchcount(this.id);
                var sec = getNext(this);
                var sib = siblings(sec.parentNode);
                var otherArr = [];
                var arrowClass = [];
                // 排他 secondary arrowRot obFocus
                for (var j = 0; j < sib.length; j++) {
                    var sibSec = sib[j].getElementsByTagName('*');
                    for (var i = 0; i < sibSec.length; i++) {
                        if (sibSec[i].className == "secondary") {
                            otherArr.push(sibSec[i])
                        }
                        if (sibSec[i].className == "arrowRot") {
                            arrowClass.push(sibSec[i])
                        }
                        if (sibSec[i].className == "obFocus") {
                            sibSec[i].classList.remove("obFocus");
                            sibSec[i].classList.add("obtain");

                        }
                    }
                }
                for (var i = 0; i < otherArr.length; i++) {
                    otherArr[i].style.height = "0";
                }
                if (arrowClass[0]) {
                    arrowClass[0].classList.remove("arrowRot");
                }

                // 留自己 2.5078 + "rem"
                sec.style.height ="100%";
                /*sec.style.cssText="background:white";*/
                this.getElementsByTagName("i")[0].classList.add("arrowRot");
                this.classList.remove("obtain");
                this.classList.add("obFocus");
            }

        }
    }

    function parametersearchcount(field) {
        $("#" + field + "con").html("");
        var _index = 1; //初始化显示条数
        var _default = 10; //每次显示多少条
        var form = new mini.Form('#Parmeterform');
        var url = "/searchResult/parametersearchcount";
        var arg = {Field:field,words:mini.get("hidPara").getValue(),searchPage:mini.get("hidSearchType").getValue()};
        $.post(url,arg,function (result) {
            var res = mini.decode(result);
            if (res['success']){
                form.loading("请稍候");
                var datas = result.data;
                var strCon = datas[0].substr(1);
                strCon = strCon.substring(0, strCon.length - 1);
                var strS = strCon.split(',');
                if (strCon != "") {
                    fun(strS,field,_index,_default);
                }
                form.unmask();
            }
        })
    }

    function fun(strS,field,_index,_default) {
        var strFinal = "";
        if (strS.length < _default){
            _default = strS.length;
        }
        var reg = /^\{/gi;
        var reg2 = /\}$/gi;
        for (var i=(_index - 1) * _default; i<(_index - 1) * _default + _default; i++) {
            var cons = [];
            cons = strS[i].split(':');
            cons[0] = cons[0].replace(/\"/g,"");
            cons[0] = cons[0].replace(reg,"");
            cons[1] = cons[1].replace(reg2,"");
            strFinal += '<h3><p class="secotitel"><input type="checkbox" value="'+cons[0]+'" name="'+field+'" class="secheck"><span>' + cons[0]+ '</span></p><p class="seshuliang">' + cons[1] + '</p></h3>'
        }
        strFinal += '<div class="shai" style="background: white"><p style="float: left;margin-left: 10px;" id="btn'+field+'Search">筛选</p><p style="float: right;margin-right: 10px;" id="btn'+field+'">更多</p></div>';
        $("#" + field + "con").append(strFinal);
        if (strS.length <= _default){
            $("#btn" + field).attr("style","display:none");
        }
        $("#btn" + field).click(function () {
            var len = $("#" + field + "con").children('h3').length;
            loadmore(strS,field,len,_default);
        });
        $("#btn" + field + "Search").click(function () {
            if (field == "shenqingr"){
                var para = "";
                var val = [];
                $.each($("#" + field + "con input:checkbox:checked"),function () {
                    val.push($(this).val());
                });
                if (val.length > 0){
                    for (var i=0;i<val.length;i++){
                        if (i == 0){
                            para += field + "={" + val[i] + "-01-01," + val[i] + "-12-31}";
                        }else if (i > 0){
                            para += " OR " + field + "={" + val[i] + "-01-01," + val[i] + "-12-31}";
                        }
                    }
                    para = " AND (" + para + ")";
                    para = "(" + mini.get('hidPara').getValue() + ")" + para;
                    indpa = para;
                    var pageSize = 5, pageIndex = 0, sortField = "shenqingr", sortOrder = "asc";
                    doSearch(para,pageSize,pageIndex,sortField,sortOrder);
                }
            }else{
                var para = "";
                var val = [];
                $.each($("#" + field + "con input:checkbox:checked"),function () {
                    val.push($(this).val());
                })
                if (val.length > 0){
                    for (var i=0;i<val.length;i++){
                        if (i == 0){
                            para += field + "=" + val[i];
                        }else if (i > 0){
                            para += " OR " + field + "=" + val[i];
                        }
                    }
                    para = " AND (" + para + ")";
                    para = "(" + mini.get('hidPara').getValue() + ")" + para;
                    indpa = para;
                    var pageSize = 5, pageIndex = 0, sortField = "shenqingr", sortOrder = "asc";
                    doSearch(para,pageSize,pageIndex,sortField,sortOrder);
                }
            }
        })
    }

    function loadmore(strS,field,len,_default) {
        var strFinal = "";
        var str = [];
        var reg = /^\{/gi;
        var reg2 = /\}$/gi;
        for (var i=len;i<len + _default;i++){
            str.push(strS[i]);
            if (i + 1 == strS.length){
                $("#btn" + field).attr("style","display:none");
            }
        }
        for (var nums=0;nums<str.length;nums++){
            var cons = [];
            if (str[nums] != undefined) {
                cons = str[nums].split(':');
                cons[0] = cons[0].replace(/\"/g, "");
                cons[0] = cons[0].replace(reg, "");
                cons[1] = cons[1].replace(reg2, "");
                strFinal += '<h3><p class="secotitel"><input type="checkbox" class="secheck"><span>' + cons[0] + '</span></p><p class="seshuliang">' + cons[1] + '</p></h3>';
            }
        }
        $(strFinal).insertBefore($("#" +field + "con").children("div"))
    }

    // 子导航点击事件
    var seconC = document.querySelectorAll(".secondary h3")
    for (var i = 0; i < seconC.length; i++) {
        seconC[i].onclick = function () {
            for (var i = 0; i < seconC.length; i++) {
                seconC[i].classList.remove("seconFocus");
            }
            this.classList.add("seconFocus");
        }
    }
    
    // 隐藏菜单
    var obscure = document.querySelector(".navH span");
    var open = document.querySelector("#open");
    var ensconce = document.querySelector("#ensconce");
    // obscure.onclick = function () {
    //     open.style.marginLeft = "-300px";
    //     setTimeout(function () {
    //         ensconce.style.display = "block";
    //     }, 350)
    //
    // }
    //显示菜单
    var showC = document.querySelector("#ensconce h2");
    // showC.onclick = function () {
    //     open.style.marginLeft = "0px";
    //     setTimeout(function () {
    //         ensconce.style.display = "none";
    //     }, 100)
    //
    // }
}

function getByClass(clsName, parent) {
    var oParent = parent ? document.getElementById(parent) : document,
        boxArr = new Array(),
        oElements = oParent.getElementsByTagName('*');
    for (var i = 0; i < oElements.length; i++) {
        if (oElements[i].className == clsName) {
            boxArr.push(oElements[i]);
        }
    }
    return boxArr;
}
// 获取下一个兄弟元素
function getNext(node) {
    if (!node.nextSibling) return null;
    var nextNode = node.nextSibling;
    if (nextNode.nodeType == 1) {
        return nextNode;
    }
    return getNext(node.nextSibling);
}

// 获取除了自己以外的其他亲兄弟元素
function siblings(elem) {
    var r = [];
    var n = elem.parentNode.firstChild;
    for (; n; n = n.nextSibling) {
        if (n.nodeType === 1 && n !== elem) {
            r.push(n);
        }
    }
    return r;
}