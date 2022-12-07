<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="/js/boot.js"></script>
</head>
<body style="width:100%;height:100%;overflow: hidden">
<div class="mini-toolbar">
    <table style="width:100%">
        <tr>
            <td style="width:100%">
                关键字：<input class="mini-textbox" id="queryText" style="width:300px"/>&nbsp;&nbsp;
                <a class="mini-button mini-button-primary" onclick="query()">模糊搜索</a>
            </td>
            <td style="width:100px;white-space:nowrap">采集进度:</td>
            <td style="white-space:nowrap">
                <div id="p1" class="mini-progressbar" value="${Percent}" style="width:300px"></div>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</div>
<div class="mini-fit">
    <div class="mini-datagrid" style="width:99%;height:99%;margin:0;padding:0" id="grid1" ondrawcell="onDraw"
         url="/main/patent/getShowTask?No=${No}" autoload="true" sortField="GUPDATETIME" sortOrder="desc">
        <div property="columns">
            <div type="indexcolumn" style="width:80px"></div>
            <div field="SHENQINGH" headerAlign="center" align="center">专利编号</div>
            <div field="FAMINGMC" width="200" headeralign="center" allowsort="true" align="center">专利名称</div>
            <div field="SHENQINGLX" width="70" headerAlign="center" allowsort="true"
                 align="center">专利类型
            </div>
            <div field="SHENQINGR" width="100" headerAlign="center" Align="center" dataType="date"
                 dateFormat="yyyy-MM-dd" allowsort="true">申请日期
            </div>
            <div field="LAWSTATUS" width="100" headerAlign="center" allowsort="true" align="center">专利状态
            </div>
            <div field="PUpdate" width="80" headerAlign="center" allowsort="true" align="center">专利是否更新</div>
            <div field="PUpdateTime" width="100" headerAlign="center" Align="center" dataType="date"
                 dateFormat="yyyy-MM-dd HH:mm" allowsort="true">专利更新时间
            </div>
            <div field="GUpdate" width="80" headerAlign="center" allowsort="true" align="center">官费是否更新</div>
            <div field="GUpdateTime" width="100" headerAlign="center" Align="center" dataType="date"
                 dateFormat="yyyy-MM-dd HH:mm" allowsort="true">官费更新时间
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid1 = mini.get('#grid1');
    var txtQuery = mini.get('#queryText');

    function onDraw(e) {
        var field = e.field;
        if (field == 'PUpdateTime' || field == "GUpdateTime") {
            var val = e.value;
            var dd = mini.parseDate(val, 'yyyy-MM-dd');
            if (dd.getFullYear() == 1900) e.cellHtml = '';
        }
    }

    function query() {
        var arg = {};
        var cs = [];
        var word = txtQuery.getValue();
        var fields = ['SHENQINGH', 'FAMINGMC', 'SHENQINGLX'];
        if (word) {
            for (var i = 0; i < fields.length; i++) {
                var field = fields[i];
                var op = {field: field, oper: 'LIKE', value: word};
                cs.push(op);
            }
        }
        if (cs.length > 0) arg["Query"] = mini.encode(cs);
        grid1.load(arg);
    }
</script>
</body>
</html>
