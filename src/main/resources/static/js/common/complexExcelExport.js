function complexExcelData(grid){
    var sheetName="Sheet1";
    var autoCreateNew=1;
    var numberCell="";
    var g=this;
    function init(){
        var rows=grid.getSelecteds();
        if(rows.length==0){
            mini.alert('请选择要导出的记录!');
            return null;
        }
        var obj=ZLObject.parse(rows);
        return obj;
    }
    this.export=function(code,fileName,postResult){
        if(postResult==null) postResult=init();
        if(postResult){
            $.fileDownload('/excel/download1', {
                httpMethod : 'POST',
                data : 'data='+mini.encode(postResult)+'&filename='+encodeURI(fileName)+'&ConsType=download'
                    +'&code='+code+'&sheetName='+g.getSheetName()+'&autoCreateNew='+g.getAutoCreateNew()+'&numberCell='+g.getNumberCell(),
                prepareCallback : function(url) {

                },
                failCallback : function(html, url) {
                    mini.alert('下载错误:'+html,'系统提示');
                }
            });
        } else mini.alert('未发现可导出的数据。');
    }
    this.setSheetName=function(stName){
        sheetName=stName;
    }
    this.getSheetName=function(){
        return sheetName;
    }
    this.setAutoCreateNew=function(val){
        autoCreateNew=val;
    }
    this.getAutoCreateNew=function(){
        var dx=parseInt(autoCreateNew);
        if(dx==1) return "true"; else return "false";
    }
    this.setNumberCell=function(val){
        numberCell=val;
    }
    this.getNumberCell=function(){
        return numberCell;
    }
}
function singleItem(){
    this.SHENQINGH="";
    this.SHENQINGR="";
    this.PAYSTATE="";
    this.JIAOFEIR="";
    this.SHENQINGRXM="";
    this.FAMINGRXM="";
    this.MONEY=0;
    this.COSTNAME="";
}
function ZLObject(){
    this.Rows=[];
    this.TOTAL=0;
    this.TOTALSHOU=0;
    this.TOTALGUAN=0;
    // this.allMoney={'发明专利第1年年费':900,'发明专利第2年年费':900,'发明专利第3年年费':900,'发明专利第4年年费':1200,'发明专利第5年年费':1200,'发明专利第6年年费':1200,'发明专利第7年年费':2000,'发明专利第8年年费':2000,'发明专利第9年年费':2000,'发明专利第10年年费':4000,'发明专利第11年年费':4000,'发明专利第12年年费':4000,'发明专利第13年年费':6000,'发明专利第14年年费':6000,'发明专利第15年年费':6000,'发明专利第16年年费':8000,'发明专利第17年年费':8000,'发明专利第18年年费':8000,'发明专利第19年年费':8000,'发明专利第20年年费':8000,'实用新型专利第1年年费':600,'实用新型专利第2年年费':600,'实用新型专利第3年年费':600,'实用新型专利第4年年费':900,'实用新型专利第5年年费':900,'实用新型专利第6年年费':1200,'实用新型专利第7年年费':1200,'实用新型专利第8年年费':1200,'实用新型专利第9年年费':2000,'实用新型专利第10年年费':2000,'外观设计专利第1年年费':600,'外观设计专利第2年年费':600,'外观设计专利第3年年费':600,'外观设计专利第4年年费':900,'外观设计专利第5年年费':900,'外观设计专利第6年年费':1200,'外观设计专利第7年年费':1200,'外观设计专利第8年年费':1200,'外观设计专利第9年年费':2000,'外观设计专利第10年年费':2000,'实用新型专利申请费':500,'外观设计专利申请费':500,'发明专利申请费':900,'发明专利申请实质审查费':2500,'实用新型专利复审费':300,'外观设计专利复审费':300,'发明专利复审费':1000};
}
ZLObject.parse=function(rows){
    var g=new ZLObject();
    var types = {0:'发明专利',1:'新型专利',2:'外观专利'};
    function parseSingle(row,index){
        var item=new singleItem();
        item.INDEX=index;
        item.SHENQINGH=row.SHENQINGH;
        item.ZHUANLIMC=row.FAMINGMC;
        item.SHENQINGR=(mini.formatDate(row.SHENQINGR,'yyyy-MM-dd') || "").toString().split('T')[0];
        item.SHENQINGRXM=row.SHENQINGRXM;
        item.FAMINGRXM=row.FAMINGRXM || "";
        item.PAYSTATE=row.PayState;
        item.JIAOFEIR=(mini.formatDate(row.LimitDate,'yyyy-MM-dd') || "").toString().split('T')[0];
        item.MONEY=row.Amount;
        item.COSTNAME=row.CostName;
        return item;
    }
    g.SIJIAO=0;
    g.TOTALSHOU=0;
    g.YINGJIAO=0;
    g.FREE=0;
    g.TOTAL1=0;
    g.TOTAL=0;
    for(var i=0;i<rows.length;i++){
        var row=rows[i];
        var r=parseSingle(row,i+1);
        g.Rows.push(r);
        g.SIJIAO+=r.MONEY;//实际缴费金额
        g.TOTALSHOU+=r.SXMONEY;//手续费汇总
        g.YINGJIAO+=r.YINGJIAOFY;
    }
    g.TOTAL=g.SIJIAO+g.TOTALSHOU;
    g.TOTAL1=g.YINGJIAO+g.TOTALSHOU;
    g.FREE=g.YINGJIAO-g.SIJIAO;
    return g;
}