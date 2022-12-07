package com.zhide.govwatch.common;

import com.zhide.govwatch.model.sqlParameter;
import org.springframework.jdbc.core.SqlParameter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: sqlParameterUtils
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月22日 11:19
 **/
public class sqlParameterUtils {
    public static sqlParameter addAnd(List<sqlParameter> paraList){
        sqlParameter res=new sqlParameter();
        res.setRelation("AND");
        res.setChildren(paraList);
        return  res;
    }
    public static  sqlParameter addOr(List<sqlParameter> paraList){
        sqlParameter res=new sqlParameter();
        res.setRelation("OR");
        res.setChildren(paraList);
        return  res;
    }
    public static  sqlParameter addNot(List<sqlParameter> paraList){
        sqlParameter res=new sqlParameter();
        res.setRelation("NOT");
        res.setChildren(paraList);
        return  res;
    }
    public static List<sqlParameter> expandAll(sqlParameter parameter){
        List<sqlParameter> res=new ArrayList<>();
        List<sqlParameter> children= parameter.getChildren();
        if(children.size()>0) {
           for(int i=0;i< children.size();i++){
               sqlParameter p=children.get(i);
               List<sqlParameter> pp=expandAll(p);
               if(pp.size()>0) res.addAll(pp);
           }
        } else res.add(parameter);
        return res;
    }
}
