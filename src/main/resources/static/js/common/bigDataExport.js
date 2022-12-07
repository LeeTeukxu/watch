function bigDataExport(config) {
    var grid = config.grid;
    var postUrl = config.postUrl;
    var getNumberUrl = config.getNumberUrl;
    var columns = [];
    var exceptHeader = ["备注信息", "邮件通知"];
    var msgId = null;

    function init() {
        var cols = grid.getColumns();
        for (var i = 0; i < cols.length; i++) {
            var col = cols[i];
            var field = col["field"];
            if (!field) continue;
            var visible = col.visible;
            if (visible == false) continue;
            var header = col["header"] || "";
            if (!header) continue;
            if (exceptHeader.indexOf(header) > -1) continue;
            columns.push(col);
        }
        $.getScript('/js/common/jquery.fileDownload.js');
    }

    init();
    var downError = false;
    this.export = function (fileName, downloadError) {
        downError = downloadError;
        funs.updateConfig(columns);
        var pUrl=getNumberUrl;
        var arg={};
        var param = grid.getLoadParams();
        var high = param["High"];
        var query = param["Query"];
        if (high) {
            arg.High=high;
        } else if (query) {
            arg.Query=query;
        }
        $.getJSON(pUrl, arg, function (result) {
            if (result.success) {
                msgId = createChoiceDialog(fileName, parseInt(result.data || 0));
            } else msgId = createChoiceDialog(fileName, 0);
        })
    }

    function createChoiceDialog(fileName, totalNumber) {
        var rows = grid.getSelecteds();
        var ns = "(";
        if (rows.length > 0) ns += rows.length + "条记录)"; else ns = "";
        var ps = "(" + grid.getData().length + "条记录)";
        var ttd = '<lable style="font-size:larger"><input type="radio" name="a111" value="0" checked="checked"/>当前页记录' + ps + '</lable><br />' +
            '<hr />' +
            '<span id="SelectOne">'+
            '<lable style="font-size:larger"><input type="radio" name="a111" value="1"/>已选择的记录' + ns + '</lable><br />' +
            '<hr />' +
            '</span>'+
            '<lable style="font-size:larger"><input type="radio" name="a111" value="2" total="' + totalNumber + '"/>所有记录(' + totalNumber + '条记录)</lable><br/>' +
            '<hr />' +
            '<label style="font-size:larger"><input type="radio" name="a111" value="3"/>从:' +
            '<input class="mini-spinner" width="60" value="1" id="minNum" maxValue="' + (totalNumber - 1) + '"/>到:' +
            '<input class="mini-spinner" width="60" miniValue="1" maxValue="' + totalNumber + '" value="' + totalNumber + '" id="maxNum"/>的记录</label><br/><hr/>';
        var ttx = '<table style="width:100%;table-layout: auto;"><tbody><tr><td>' + ttd + '</td></tr>';
        var ttg = '<input labelField="true" id="postFileName" labelStyle="width:80px" label="文件名称:"' +
            ' class="mini-textbox" value="' + fileName + '" style="width:50%;height:30px" required="true"/>';
        ttx += '<tr><td style="height:40px">' + ttg + '</td></tr>';
        ttx += '<tr><td style="height:80px;text-align:center;vertical-align:top">' +
            '<hr />' +
            '<a class="mini-button" iconCls="icon-download" id="downloadExcel">下载Excel</a>' +
            '<a class="mini-button" iconCls="icon-cancel" style="margin-left:100px"  id="closeDialog">取消关闭</a>' +
            '</td></tr>';
        ttx += '</tbody></table>';
        var msgId = mini.showMessageBox({
            html: ttx,
            title: '批量导出数据至Excel文件中',
            width: 600,
            height: 300,
            iconCls: "mini-messagebox-question"
        });
        mini.parse();
        mini.get('downloadExcel').on('click', downloadExcel);
        mini.get('closeDialog').on('click', closeDialog);
        if (rows.length == 0) {
            $("input[name='a111'][value='0']").attr('checked', true);
            $("#SelectOne").hide();
        } else {
            $("input[name='a111'][value='1']").attr('checked', true);
        }

        return msgId;
    }

    function downloadExcel() {
        var curCon=$("input[name='a111']:checked");
        var type = parseInt(curCon.val());
        if (type == 0 || type == 1) {
            down1();
        } else if (type == 2) {
            var total=parseInt(curCon.attr('total'));
            down2(1,total);
        }
        else if(type==3){
            var min=mini.get('#minNum').getValue();
            var max=mini.get('#maxNum').getValue();
            down2(min,max);
        }
    }

    function down1() {
        var loadingMsgId = null;

        function getData() {
            var rows = grid.getSelecteds() || [];
            if (rows.length == 0) rows = grid.getData();
            rows = funs.beforeGetData(grid, rows);
            var colHash = getColumnHash();
            var fields = getFields();
            var datas = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var singleData = {};
                for (var n = 0; n < fields.length; n++) {
                    var field = fields[n];
                    var col = colHash[field];
                    var val = getSingleValue(field, col, row);
                    singleData[field] = val;
                }
                datas.push(singleData);
            }
            return datas;
        }

        function getColumns() {
            var res = [];
            for (var i = 0; i < columns.length; i++) {
                var col = columns[i];
                var o = {field: col.field, header: $.trim(col.header), width: parseInt(col.width || 80)};
                var type = col["dataType"] || "string";
                if (downError == true) type = "string";
                o.type = type;
                res.push(o);
            }
            return res;
        }

        function getColumnHash() {
            var res = {};
            for (var i = 0; i < columns.length; i++) {
                var col = columns[i];
                var field = col.field;
                col.header = $.trim(col.header);
                res[field] = col;
            }
            return res;
        }

        function getFields() {
            var res = [];
            for (var i = 0; i < columns.length; i++) {
                var col = columns[i];
                var field = col.field;
                res.push(field);
            }
            return res;
        }

        function getSingleValue(field, col, row) {
            var val = row[field];
            if (val == null || val == undefined) return '';
            if ($.trim(val.toString()) == '') return '';
            var type = col['type'];
            if (type == "comboboxcolumn") {
                return getTextFromCombobox(val, col._data);
            }
            if (type == "treeselectcolumn") {
                return getTextFromCombobox(val, col._data);
            } else {
                var dataType = col["dataType"] || "string";
                if (dataType == "string") return val;
                else if (dataType == "date") {
                    var delValue = mini.formatDate(val, col.dateformat || "yyyy-MM-dd");
                    if (!delValue) delValue = val;
                    return delValue;
                } else if (dataType == "float" || dataType == "int") {
                    return val;
                } else return val;
            }

            function getTextFromCombobox(value, datas) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    var id = data.id;
                    if (id == value) return data.text;
                }
                return '';
            }
        }

        var fileName = encodeURI(mini.get('#postFileName').getValue());
        var url = '/excel/download';
        //url='/work/patentInfo/export';
        $.fileDownload(url, {
            httpMethod: 'POST',
            data: 'data=' + mini.encode(getData()) + '&columns=' + mini.encode(getColumns()) + '&filename=' + fileName,
            //data:'sortField='+grid.getSortField()+'&sortOrder='+grid.getSortOrder()+'&High='+(grid.getLoadParams()["High"]||"[]")+'&columns='+mini.encode(getColumns())+'&filename='+fileName,
            prepareCallback: function (url) {
                if (msgId) mini.hideMessageBox(msgId);
            },
            failCallback: function (html, url) {
                mini.alert('下载错误:' + html, '系统提示');
            }
        });
    }

    function down2(min,max) {
        var fileName = encodeURI(mini.get('#postFileName').getValue());
        var url = postUrl;
        var pUrl = 'sortField=' + grid.getSortField() + '&sortOrder=' + grid.getSortOrder() + '&filename=' + fileName+'&min='+min+'&max='+max;
        var param = grid.getLoadParams();
        var high = param["High"];
        var query = param["Query"];
        if (high) {
            pUrl += "&High=" + high;
        } else if (query) {
            pUrl += "&Query=" + query;
        } else {
            pUrl += "&High=[]";
        }
        $.fileDownload(url, {
            httpMethod: 'POST',
            data:pUrl,
            prepareCallback: function (url) {
                if (msgId) mini.hideMessageBox(msgId);
            },
            failCallback: function (html, url) {
                mini.alert('下载错误:' + html, '系统提示');
            }
        });
    }

    function closeDialog() {
        if (msgId) mini.hideMessageBox(msgId);
    }

    var funs = {
        beforeGetData: function (grid, data) {
            return data;
        },
        updateConfig: function (columns) {

        }
    };
    this.addEvent = function (evt, func) {
        if (funs[evt]) funs[evt] = func;
    }
}