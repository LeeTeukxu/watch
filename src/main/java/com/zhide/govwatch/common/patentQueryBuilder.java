package com.zhide.govwatch.common;

import com.zhide.govwatch.model.patentElInfo;
import com.zhide.govwatch.model.sqlParameter;
import freemarker.template.utility.DateUtil;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQueryParser;
import org.joda.time.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: patentQueryBuilder
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月22日 11:42
 **/
@Component
public class patentQueryBuilder {
    public QueryBuilder create(sqlParameter param) throws Exception{
        List<sqlParameter> nodes=param.getChildren();
        if(nodes.size()>0){
            BoolQueryBuilder root=new BoolQueryBuilder();
            for(int i=0;i<nodes.size();i++){
                sqlParameter sqlP=nodes.get(i);
                createChild(sqlP,root,param.getRelation());
            }
            return root;
        }
        else {
            return createSingle(param);
        }
    }
    private QueryBuilder createSingle(sqlParameter param) throws Exception {
        if(param.getChildren().size()==0) {
            String field = param.getField();
            String value = param.getValue();
            String oper = param.getOper();
            if(value.startsWith("{") && value.indexOf(",")>-1 && value.endsWith("}")){
                SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value=value.replace("{","").replace("}","").trim();
                String[] ds=value.split(",");
                Date d1= simple.parse(ds[0]+" 00:00:01");
                Date d2=simple.parse(ds[1]+" 23:59:59");
                return QueryBuilders.rangeQuery(field).from(d1).to(d2);
            } else {
                return QueryBuilders.wildcardQuery(field, "*" + value + "*");
            }
        } else return null;
    }
    private void  createChild(sqlParameter param,BoolQueryBuilder root,String relation) throws Exception{
        List<sqlParameter> nodes=param.getChildren();
        if(nodes.size()==0) {
            QueryBuilder builder=createSingle(param);
            if (relation.equals("AND")) root.must(builder);
            else if (relation.equals("OR")) root.should(builder);
            else if (relation.equals("NOT")) root.mustNot(builder);
        } else {
            BoolQueryBuilder parent=new BoolQueryBuilder();
            for(int i=0;i<nodes.size();i++){
                sqlParameter par=nodes.get(i);
                createChild(par,parent,param.getRelation());
            }
            if (relation.equals("AND")) root.must(parent);
            else if (relation.equals("OR")) root.should(parent);
            else if (relation.equals("NOT")) root.mustNot(parent);
        }
    }
}
