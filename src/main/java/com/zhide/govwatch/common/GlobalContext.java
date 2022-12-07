package com.zhide.govwatch.common;

import org.springframework.util.ResourceUtils;

/**
 * @ClassName: GlobalContext
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年09月21日 15:50
 **/
public class GlobalContext {
    public static String getStaticUrl() {
        String path = null;
        try {
            String serverpath = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ").replace('/',
                    '\\');
            path = serverpath.substring(1);//从路径字符串中取出工程路径
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getTemplateUrl() {
        String path = null;
        try {
            String serverpath = ResourceUtils.getURL("classpath:templates").getPath().replace("%20", " ").replace
                    ('/', '\\');
            path = serverpath.substring(1);//从路径字符串中取出工程路径
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
