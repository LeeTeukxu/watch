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
        </tr>
    </table>
</div>
<div class="mini-fit">
    <div class="mini-datagrid" style="width:99%;height:99%;margin:0;padding:0" id="grid1"
         ondrawcell="onDraw"
         pageSize="5"
         url="/main/patent/getUpdateMain" autoload="true" sortField="CreateTime" sortOrder="desc">
        <div property="columns">
            <div type="indexcolumn" style="width:80px"></div>
            <div field="No" headerAlign="center" align="center" width="200">任务编号</div>
            <div field="Num" width="100" headeralign="center" allowsort="true" align="center">待采集数量</div>
            <div field="Per" width="100" headeralign="center" allowsort="true" align="center">采集进度</div>
            <div field="CreateTime" width="150" headerAlign="center" Align="center" dataType="date"
                 dateFormat="yyyy-MM-dd HH:mm" allowsort="true">创建日期
            </div>
            <div field="CreateManName" width="100" headeralign="center" allowsort="true" align="center">创建人</div>
            <div field="Action" width="150" width="100" headeralign="center" allowsort="true" align="center"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var grid1 = mini.get('#grid1');
    var txtQuery = mini.get('#queryText');

    function onDraw(e) {
        var field = e.field;
        if (field == 'Action') {
            var no = e.record["No"];
            var dd = '<a  href="javascript:void(0)" style="text-decoration:underline" ' +
                'onclick="show(' + "'" + no + "'" + ')' + '">查看明细</a>' + '&nbsp;&nbsp;';
            dd += '<a  href="javascript:void(0)"  style="text-decoration:underline"  onclick="remove(' + "'" + no + "'" + ')">删除任务</a>';
            e.cellHtml = dd;
        }
    }

    function query() {
        var arg = {};
        var cs = [];
        var word = txtQuery.getValue();
        var fields = ['No', 'CreateManName'];
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

    function show(no) {
        mini.open({
            url: '/main/patent/showTask?No=' + no,
            width: '90%',
            height: '80%',
            title: '查看采集进度',
            showModal: true
        })
    }

    function remove(no) {
        mini.confirm('确认要删除采集任务和采集记录？', '系统提示', function (act) {
            if (act == 'ok') {
                var url = '/main/patent/removeTask';
                $.post(url, {No: no}, function (result) {
                    if (result.success) {
                        mini.alert('指定的任务删除成功!', '系统提示', function () {
                            grid1.reload();
                        });
                    } else {
                        mini.alert(result.message || "删除失败，请稍候重试!");
                    }
                })
            }
        });
    }
</script>
</body>
</html>
