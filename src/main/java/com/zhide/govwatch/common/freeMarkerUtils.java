package com.zhide.govwatch.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.util.ResourceUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: freeMarkerUtils
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月22日 13:47
 **/
public class freeMarkerUtils {
    /**
     * create by: mmzs
     * description: TODO
     * create time:
     * 生成templates下的代码片段。

     * @return
     */
    public static  String compile(String name,Map<String,Object> values){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("utf-8");
        try {
            String filePath=String.format("classpath:templates/%s.ftl",name);
            String Content= FileUtils.readFileToString(ResourceUtils.getFile(filePath),"utf-8");
            StringWriter writer=new StringWriter();
            Template template=new Template(name,Content,configuration);
            template.process(values,writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
