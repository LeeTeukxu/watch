<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/plupload/plupload.full.min.js"></script>
    <script type="text/javascript" src="/js/common/fileUploaderCompany.js"></script>
    <script type="text/javascript" src="/js/common/excelExportOther.js"></script>
    <script type="text/javascript" src="/js/common/jquery.fileDownload.js"></script>
    <script type="text/javascript">
    </script>
    <div id="ReqFile" style="width:100%;height:100%"></div>
</@layout>
<@js>
    <script type="text/javascript">
        function init() {
            var k = $('#ReqFile').fileUploader({
                mode: 'Add',
                showConfig: false,
                uploadUrl: '/excelImport/selectCompanyXls',
            });
            k.loadFiles({});
        }
        init();
    </script>
</@js>