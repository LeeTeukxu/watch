function expressBuilder(config) {
    var nodesHash = {};
    var container = config.container;
    var clickFunc = config.click || function (type, cons) {};
    var root=null;
    var $div = $(container);
    var ggg = this;
    this.addChild = function (child, parentEl) {
        if (!parentEl) parentEl = $div;
        var parent = $(parentEl);
        var field = child.field;
        var oper = child.oper || "=";
        var value = child.value || "";
        var id = new Date().getTime();
        child.id = id;
        var $span = $('<span class="childQuery" id="' + id + '" style="' + childRootCss + '">' +
            '<span style="' + childInnerCss + '">' + field + oper + '</span>' +
            '<input type="text" style="width: 48px;height: 20px;border: 0px;line-height: 19px;color: #f34319d4;font-family: none;" title="点击修改" value="' + value + '"/>' +
            '</span>');
        $span.data(child);
        parent.append($span);
        $span.on('click', childClick);
    }
    this.addChildren = function (nodes, parent) {
        if(!parent)parent=root;
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];
            parent.addChild(node);
        }
        var pp=new parentNode(parent);
        var pId= pp.render();
        var parentDom=$('#'+pId);
        parentDom.on('click', parentClick);
    }
    this.changeRelation = function (nodes, newRelation, newParent) {
        for (var i = 0; i < nodes.length; i++) {
            var node = nodes[i];
        }
    }

    function parentClick(e) {
        var con = $(e.target || e.srcElement);
        if (con.length > 0) {
            var cons = triggerClickEvent(e.ctrlKey, con);
            if (clickFunc) clickFunc('parent', cons);
        }
    }

    function childClick(e) {
        var con = $(e.target || e.srcElement);
        if (con.length > 0) {
            var cons = triggerClickEvent(e.ctrlKey, con);
            if (clickFunc) clickFunc('child', cons);
        }
        e.stopPropagation();
    }

    function triggerClickEvent(isCtrl, con) {
        var oos = [];
        var cName = con.prop('class');
        if (!cName) cName = con.parent().prop('class');
        if (isCtrl) {
            $('.selected').each(function (index, dom) {
                if ($(dom).hasClass(cName)) oos.push(dom);
            })
        }
        $('.childQuery').removeClass('selected').css({
            'background-color': 'transparent'
        });
        $('.parentQuery').removeClass('selected').css({
            'background-color': 'transparent'
        });
        oos.push(con);
        for (var i = 0; i < oos.length; i++) {
            var c = $(oos[i]);
            if (c.hasClass('childQuery') || c.hasClass('parentQuery')) {
                c.addClass('selected').css({
                    'background-color': 'rgba(35, 145, 255, 0.12)'
                });
            } else {
                c.parent().addClass('selected').css({
                    'background-color': 'rgba(35, 145, 255, 0.12)'
                });
            }
        }
        return oos;

    }

    function addRoot(){
        var node=new  parentExpress();
        var rr={};
        rr.id=$div.attr('id');
        rr.children=[node];
        rr.relation='AND';
        node.pid=rr.id;
        var pp=new parentNode(rr);
        var pId=pp.render();
        $('#'+pId).hide();
        root=node;
    }
    addRoot();
}

function parentNode(parent) {
    var parentRootCss = 'text-align:center;display:inline-block;margin:8px;border: 2px dashed rgb(214, 214, 214);';
    var pDom = null;
    if (parent.id) {
        pDom = $('#' + parent.id);
    }
    this.render = function () {
        var nodes=parent.children ||[];
        var node=nodes[0];
        var pId=node.pid;
        if(!node.field)pId=node.id;
        var nodeDom=null;
        if(!pId){
            pId= new Date().getTime();
            var parentDiv = '<span style="' + parentRootCss + '" class="parentQuery" id="' + pId + '"></span>';
            nodeDom = $(parentDiv);
            pDom.append(nodeDom);
            node.id=pId;
        } else nodeDom=$('#'+pId);
        if(node.field){
            nodeDom.empty();
            for (var i = 0; i < nodes.length; i++) {
                var n=nodes[i];
                var nodeid=n.id;
                var child=new childNode(parent);
                var id=child.render(n);
                n.id=id;
                n.pid=pId;
                if(nodes.length>1){
                    if(i>=0 && i<nodes.length-1){
                        var relation=parent.relation || "AND";
                        var $re = $('<span style="font-family: 宋体;color: #159ac3a6;">' + relation + '</span>');
                        nodeDom.append($re);
                    }
                }
            }
            nodeDom.show();
        }
        return pId;
    }
}

function childNode(parent) {
    var childRootCss = 'text-align:center;display:inline-block;margin:8px;width: 91px;height:28px;line-height:28px;border-radius: 3px;';
    var childInnerCss = 'text-align: center;ine-height: 28px;font-size: 15px;font-family: monospace;cursor: default;';
    this.render = function (node) {
        var field = node.field;
        var oper = node.oper || "=";
        var value = node.value || "";
        var id = new Date().getTime();
        node.id = id;
        var pDom = $('#'+parent.id);
        var $span = $('<span class="childQuery" id="' + id + '" style="' + childRootCss + '">' +
            '<span style="' + childInnerCss + '">' + field + oper + '</span>' +
            '<input type="text" style="width: 48px;height: 20px;border: 0px;line-height: 19px;color: #f34319d4;font-family: none;" title="点击修改" value="' + value + '"/>' +
            '</span>');
        pDom.append($span);
        return id;
    }
}
