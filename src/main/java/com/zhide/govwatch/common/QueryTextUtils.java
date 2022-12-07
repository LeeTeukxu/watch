package com.zhide.govwatch.common;

import freemarker.template.utility.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName: QueryTextUtils
 * @Author: 肖新民
 * @*TODO:1、可能处理可能逻辑操作符之间没有空格的情况。
 * @CreateTime: 2021年12月30日 14:12
 **/
public class QueryTextUtils {
    final static List<String> keyWords= Arrays.asList("and","or","not");
    public static String  fixedKeyText(String X){
        String Y=X.toLowerCase(Locale.ROOT);
        for(int i=0;i<keyWords.size();i++){
            String f=keyWords.get(i);
            int start=Y.indexOf(f);
            if(start>-1){
                String Now=X.substring(start,start+f.length());
                X=X.replace(Now,f.toUpperCase(Locale.ROOT));
            }
        }
        return X;
    }
}
