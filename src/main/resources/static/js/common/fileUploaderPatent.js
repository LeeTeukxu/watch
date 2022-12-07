(function ($) {
    $.fn.fileUploader = function (options) {
        var fileGrid = null, addButton = null, removeButton = null, uploadButton = null, downButton = null,
            configButton = null, saveButton = null, exportButton = null, downloadErrorButton = null,
            configCombobox = null, uploader = null;
        var ds = this;
        var IsShowErrorButton = "";
        var ErrorExcelPath = "";
        var buttons = [];
        var listResult = {};
        var container = $(this), defaults = {
            mode: 'Browse',
            configName: 'Default',
            ///上传地址
            uploadUrl: '/attachment/upload',
            ///下载地址
            downloadUrl: '/common/excelImport/downLoadClientTemplate',
            ///保存数据地址
            saveUrl: '/common/excelImport/save',
            ///删除文件地址
            removeUrl: '/attachment/deleteById',
            ///设置附件类型
            setAttTypeUrl: '/Common/Upload/SetAttachmentType',
            addReferenceUrl: '/Common/Upload/AddReferenceFile',
            //getFileUrl: '/attachment/getAttachmentByIDS',
            getConfigUrl: '',
            browseId: 'UploadFile',
            Filters: [
                {title: 'Office文档', extensions: 'doc,docx,xls,xlsx'},
                {title: 'pdf文档', extensions: 'pdf'},
                {title: '图片', extensions: 'jpg,bmp,gif,png'},
                {title: '压缩文件', extensions: 'zip,rar'}
            ],
            ///是否显示设置附件类型
            showConfig: false,
            afterAddFile: null,
            afterLoad: null,
            afterInit: null,
            eachFileUpload: null
        };
        var instance = this;
        var settings = $.extend({}, defaults, options);
        var mode = settings.mode || "Browse";
        var collectionName = "";

        function createToolbar() {
            var ds = [{id: 0, text: "不能下载附件"}, {id: 1, text: "普通附件"}];
            var op = [
                '<div class="mini-toolbar" id="UploadFileToolbar">',
                '<a  class="mini-button"  iconcls="icon-add" id="UploadFile" style="margin-left:5px" plain="true">选择上传文件</a>',
                '<a class="mini-button" iconcls="icon-ok" id="BeginUpload" visible="false" style="margin-left:5px" plain="true">开始上传</a>',
                '<a class="mini-button" iconcls="icon-download" id="DownloadError" visible="false" style="margin-left:5px" plain="true">导入错误信息下载</a>',
                '</div>'
            ];
            container.append(op.join(''));
        }

        function createGrid() {
            var op = [
                '<div class="mini-fit">',
                '<div id="PatentList" class="mini-datagrid" style="width: 100%; height: 100%" autoload="true"' +
                ' sortfield="shenqingh" sortorder="asc" pagesize="20">',
                '<div property="columns">',
                '<div field="shenqingh"  width="80" headeralign="center" allowsort="true">申请号</div>',
                '<div field="famingmc" width="180" headeralign="center" allowsort="true">专利名称</div>',
                '<div field="shenqingr"  width="120" headeralign="center" allowsort="true" dataType="date"' +
                ' dateFormat="yyyy-MM-dd">申请日</div>',
                '<div field="shenqinglx"  width="60" headeralign="center"allowsort="true">专利类型</div>',
                '<div field="gonkaihao"  width="80" headeralign="center"allowsort="true">公开公告号</div>',
                '<div field="gonkair"  width="120" headeralign="center"allowsort="true" dataType="date"' +
                ' dateFormat="yyyy-MM-dd">公开公告日</div>',
                '<div field="shenqingrxm"  width="150" headeralign="center"allowsort="true">申请人</div>',
                '<div field="famingrxm"  width="150" headeralign="center"allowsort="true">发明人</div>',
                '<div field="ipc"  width="100" headeralign="center"allowsort="true">IPC</div>',
                '<div field="youxianq"  width="60" headeralign="center"allowsort="true">优先权</div>',
                '<div field="lawstatus"  width="80" headeralign="center"allowsort="true">法律状态</div>',
                '<div field="address"  width="200" headeralign="center"allowsort="true">地址</div>',
                '<div field="dailijgmc"  width="150" headeralign="center" allowsort="true">代理机构</div>',
                '<div field="memo"  width="150" headeralign="center" allowsort="true">摘要</div>',
                '</div>',
                '</div>',
                '</div>',
            ];
            container.append(op.join(''));
        }

        function getUploadConfig(cnfName, callback) {
            var arg = {Name: cnfName};
            var url = settings.getConfigUrl;
            $.getJSON(url, arg, function (r) {
                if (typeof (r) == "string") {
                    r = mini.decode(r);
                    if (r.success) {
                        if (callback != null) callback(r);
                    } else {
                        throw '获取设置失败，创建组件被中止。';
                    }
                }
            });
        }

        function initUploader(cnf) {
            var filters = cnf['Filters'] || "{}";
            var maxLength = cnf['MaxLength'] || "100M";
            if (filters) {
                if (typeof (filters) == "string") filters = mini.decode(filters);
            }
            uploader = new plupload.Uploader({
                browse_button: settings.browseId,
                url: settings.uploadUrl,
                flash_swf_url: '/js/plupload/Moxie.swf',
                silverlight_xap_url: '/js/plupload/Moxie.xap',
                filters: filters,
                multipart: true,
                max_file_size: maxLength,
                prevent_duplicates: true,
                multipart_params: {
                    Code: settings.configName
                },
                init: {
                    Error: function (a, b) {
                        var errorCode = parseInt(b.code);
                        var file = b.file;
                        if (errorCode == -600) {
                            mini.alert(file.name + '太大，不能上传。');
                        } else if (errorCode == -601) {
                            mini.alert('服务器不允许上传此文件。');
                        }
                    },
                    FilesAdded: function (up, files) {
                        var form = new mini.Form('#PatentList');
                        form.loading("加载中......");
                        uploader.start();
                    },
                    UploadProgress: function (up, file) {
                        var percent = file.percent;
                        $("#percent").css('width', percent + "%");
                        $("#percent_").html(percent + "%");

                    },
                    UploadComplete: function (up, file) {
                        if (uploadButton) uploadButton.show();
                    },
                    FileUploaded: function (up, file, res) {
                        var result = mini.decode(res.response);
                        if (result.success) {
                            collectionName = result.data;
                            var url = '/patent/getImportResult?collectionName=' + collectionName;
                            fileGrid.setUrl(url);
                        } else mini.alert(result.message || "文件上传失败，请稍候重试!");
                    }
                }
            });
            uploader.init();
        }

        function CloseWindow(action) {
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();
        }

        function changeByMode(mode) {
            function changeToolbar() {
                if (mode == "Browse") {
                    uploadButton.destroy();
                    addButton.destroy();
                }
            }

            changeToolbar();
        }

        function createDownloadForm(downloadUrl) {
            var text = '<form id="DownloadFileForm" style="display:none" method="POST" action="' + downloadUrl + '" >' +
                '</form>';
            container.append(text);
            return $('#DownloadFileForm');
        }

        function bindEventByMode(mode) {
            function bindToolbar() {
                if (mode == "Add") {
                    uploadButton.on('click', function () {
                        SaveToSqlServer();
                    });
                }
                downloadErrorButton.on('click', function () {
                    var url = '/work/patentInfo/download?Path=' + ErrorExcelPath + '&FileName=' + encodeURI("专利信息导入异常下载.xls");
                    $.fileDownload(url, {
                        httpMethod: 'POST',
                        failCallback: function (html, xurl) {
                            mini.alert('下载错误:' + html, '系统提示')
                        }
                    });
                    return false;
                });
            }

            bindToolbar();
        }

        function SaveToSqlServer() {
            var form = new mini.Form('#PatentList');
            var iid=mini.loading("正在导入专利数据,请稍候......",'系统提示');
            $.post("/patent/importAll", {collectionName: collectionName, Mode: mode}, function (result) {
                mini.hideMessageBox(iid);
                if (result['success']) {
                    mini.alert('数据导入成功!', '系统提示', function () {
                        CloseOwnerWindow('ok');
                    });
                } else {
                    mini.alert("保存数据失败");
                }
            });
        }

        function init() {
            createToolbar();
            createGrid();
            mini.parse();
            fileGrid = mini.get('PatentList'), addButton = mini.get('UploadFile'),
                removeButton = mini.get('RemoveFile'), uploadButton = mini.get('BeginUpload'),
                downButton = mini.get('DownloadFile'),
                configButton = mini.get('ConfigAttType'), saveButton = mini.get('Save'), exportButton = mini.get('Export'), downloadErrorButton = mini.get('DownloadError'), configCombobox = mini.get('#AttTypeCombobox');
            buttons = [
                addButton, removeButton, uploadButton, downButton, configButton, saveButton, exportButton, downloadErrorButton, configCombobox
            ];
            if (mode == "Add") {
                if (settings.getConfigUrl) {
                    getUploadConfig(options.configName, initUploader);
                } else initUploader(settings);
            }
            bindEventByMode(mode);
            changeByMode(mode);
            if (options.afterInit) {
                options.afterInit(ds);
            }
            if (fileGrid) {
                fileGrid.doLayout();
            }
        }

        this.setOption = function (options) {
            if (uploader) uploader.setOption(options);
        }
        this.getPostFile = function () {
            var rows = mini.clone(fileGrid.getData());
            var res = [];
            for (var i = 0; i < rows.length; i++) {
                var row = rows[i];
                var attId = row['ATTID'];
                if (attId) {
                    res.push(row);
                } else throw "有没有上传的附件，如不需要请删除后执行操作。";
            }
            return res;
        }
        this.loadFiles = function (params, callback) {
            if (settings.getFileUrl) {
                $.getJSON(settings.getFileUrl, params, function (r) {
                    if (typeof (r) == "string") r = mini.decode(r);
                    if (r.success) {
                        var ds = r.data || [];
                        if (ds.length > 0) {
                            fileGrid.setData(ds);
                        }
                    }
                });
            }
        }
        this.disable = function () {
            for (var i = 0; i < buttons.length; i++) {
                var button = buttons[i];
                if (button) button.disable();
            }
            if (addButton) {
                uploadButton.hide();
                downloadErrorButton.hide();
                if (uploader) uploader.disableBrowse(true);
            }
            return instance;
        }
        this.clear = function () {
            fileGrid.setData([]);
        }
        this.getToolbar = function () {
            return mini.get('#UploadFileToolbar');
        }
        this.getGrid = function () {
            return fileGrid;
        }
        this.enable = function () {
            for (var i = 0; i < buttons.length; i++) {
                var button = buttons[i];
                if (button) {
                    button.enable();
                }
            }
            if (addButton) {
                addButton.show();
                if (uploader) uploader.disableBrowse(false);
            }
            return instance;
        }
        init();
        return this;
    }
})(jQuery);