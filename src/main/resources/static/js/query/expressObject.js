//子表达式。
function childExpress(field,oper,value){
    var g={field:field,oper:oper,value:value};
    this.field=field;
    this.oper=oper;
    this.value=value;
    this.id='';
    this.pid='';
    this.isChild=function(){
        return true;
    }
    this.isParent=function(){
        return false;
    }
}
//父表式。主要是包含子表达式。并确立子表达式这间的逻辑关系。
function parentExpress(){
    var g=this;
    this.children=[];
    this.relation='AND';
    this.id='';
    this.pid='';
    this.addChild=function(node){
        node.pid=g.id;
        g.children.push(node);
    }
    this.addChildren=function(ns){
        for(var i=0;i<ns.length;i++){
            var n=ns[i];
            n.pid=g.id;
            g.children.push(n);
        }
    }
    this.isChild=function(){
        return false;
    }
    this.isParent=function(){
        return true;
    }
}