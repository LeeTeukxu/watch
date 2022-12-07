<#include "/shared/layout.ftl">
<@layout>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/query/expressObject.js"></script>
    <script type="text/javascript" src="/js/query/expressBuilder.js"></script>
    <style>
        .validity3{    color: #FFF;
            background: #009ae8;
            font-size: 14px;
            padding: 7px;
            margin: auto 10px;
        }
    </style>
    <br/>
    <button onclick="deleteIndex()">删除索引</button>
    <button onclick="rebuildIndex()">导入数据</button>
    <span class="validity3" onclick="add();">添加子条件</span>
    <#--<button >添加子条件</button>-->
    <div style="/*border-top: 1px solid #cecece;border-bottom: 1px solid #cecece;*/width:800px;font-family: 黑体;
    font-size: 12px;margin-top: 15px;margin-left: 10px;/*height: 30%;overflow-x: hidden;overflow-y: auto;*/" id="aaaa"></div>
    <script type="text/javascript">
        function rebuildIndex() {
            var url = '/elasticsearch/rebuildIndex';
            var isOK = window.confirm('确认要重建索引吗？');
            if (isOK == true) {
                $.post(url, {}, function (result) {
                    alert(result.data || "");
                })
            }
        }
        function deleteIndex() {
            var url = '/elasticsearch/deleteIndex';
            var isOK = window.confirm('确认要删除索引吗？');
            if (isOK == true) {
                $.post(url, {}, function (result) {
                    alert(result.data || "");
                })
            }
        }
        var builder = new expressBuilder({
            container:'#aaaa',
            click:function(type,cons){
                //alert(type);
            }
        });
        function add() {
            var child = new childExpress('姓名', '', '肖新民');
            builder.addChild(child);
            var child1 = new childExpress('电话', '', '肖新民1');
            var child2 = new childExpress('号码', '', '肖新民2');
            builder.addChild(child1);
            builder.addChild(child2);
            var parent=new parentExpress();
            parent.addChildren([child,child1]);
            builder.addParent(parent.get());
        }
    </script>
</@layout>