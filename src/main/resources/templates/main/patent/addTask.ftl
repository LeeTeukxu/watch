<#include "/shared/layout.ftl">
<@layout>
    <div class="mini-toolbar">
        选择采集服务器:
        <div id="comMachine" class="mini-combobox" multiSelect="true" style="width:300px" idField="id" textField="name"
             url="/main/patent/getMachine"></div>
        <a class="mini-button mini-button-primary" iconCls="icon-add" onclick="addTask()">确认并开始采集</a>
    </div>
    <div class="mini-fit">
        <div class="mini-datagrid" id="grid1" style="width:100%;height:100%" allowcellselect="true" allowcelledit="true"
             allowCellWrap="true"
             autoload="false">
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
                <div field="LASTUPDATETIME" width="100" headerAlign="center" Align="center" dataType="date"
                     dateFormat="yyyy-MM-dd" allowsort="true">更新日期
                </div>
            </div>
        </div>
    </div>
</@layout>
<@js>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get('grid1');
        var comMachine = mini.get('comMachine');
        var rows = [];

        function setDataSource(url, param) {
            grid.setUrl(url);
            grid.load(param);
        }

        function setRows(rs) {
            rows = rs;
            grid.setData(rows);
        }

        function addTask() {
            var mcs = comMachine.getValue();
            if (!mcs) {
                mini.alert('请选择进行采集的服务器!');
                return;
            }
            mini.confirm('确认要将选择的专利添加到采集列表吗？原来未完成的采集任务会被删除，是否继续？', '', function (act) {
                if (act == 'ok') {
                    var msgId = mini.loading('正在提交任务数据，请稍候......', '系统提示');
                    if (rows.length > 0) {
                        postByRows(mcs, msgId);
                    } else {
                        postByParam(mcs, msgId);
                    }
                }
            });
        }

        function postByParam(mcs, msgId) {
            var url = '/main/patent/addOne?machines=' + mcs;
            $.ajax(url, {
                method: 'post',
                data: grid.getLoadParams(),
                dataType: 'json',
                success: function (result) {
                    mini.hideMessageBox(msgId);
                    if (result.success) {
                        mini.alert('采集任务分配成功，请静待反馈结果!', '系统提示', function () {
                            CloseOwnerWindow('ok');
                        });
                    } else {
                        mini.alert(result.message || "采集任务分配失败，请稍候重试!");
                    }
                }
            })
        }

        function postByRows(mcs, msgId) {
            var url = '/main/patent/addTwo?machines=' + mcs;
            var cs = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var code = row["SHENQINGH"];
                if (code) cs.push(code);
            }
            $.ajax(url, {
                method: 'post',
                data: {Data: cs.join(',')},
                dataType: 'json',
                success: function (result) {
                    mini.hideMessageBox(msgId);
                    if (result.success) {
                        mini.alert('采集任务分配成功，请静待反馈结果!', '系统提示', function () {
                            CloseOwnerWindow('ok');
                        });
                    } else {
                        mini.alert(result.message || "采集任务分配失败，请稍候重试!");
                    }
                }
            })
        }
    </script>
</@js>