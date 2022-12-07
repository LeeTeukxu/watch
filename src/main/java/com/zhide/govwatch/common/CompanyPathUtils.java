package com.zhide.govwatch.common;

import com.zhide.govwatch.model.LoginUserInfo;

import java.io.File;

public class CompanyPathUtils {
    private static String baseDir = "C:\\Upload\\";

    public static String getFullPath(String... paths) throws Exception {
        LoginUserInfo info = CompanyContext.get();
        if (info == null) {
            throw new Exception("登录信息异常，请重新登录 。");
        }
        String fullPath = baseDir;
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            path = path.replace('\\', ' ').trim();
            fullPath += "\\" + path;
            createPath(fullPath);
        }
        return fullPath;
    }
    private static  void createPath(String sPath){
        File f = new File(sPath);
        if (f.exists() == false) {
            if (FileNameUtils.isDirectory(sPath) == true) f.mkdirs();
        }
    }

    public static String getTempPath(String... paths) throws Exception {
        LoginUserInfo info = CompanyContext.get();
        if (info == null) {
            throw new Exception("登录信息异常，请重新登录。");
        }
        String TempPath = baseDir + "Temp\\";
        createPath(TempPath);
        for (int i = 0; i < paths.length; i ++) {
            String path = paths[i];
            path = path.replace('\\', ' ').trim();
            TempPath += "\\" + path;
            createPath(TempPath);
        }
        return TempPath;
    }

    public static String getImages() throws Exception {
        LoginUserInfo info = CompanyContext.get();
        if (info == null) {
            throw new Exception("登录信息异常，请重新登录 。");
        }
        String companyId = info.getCompanyId().toString();
        String fullPath = baseDir + "\\Images\\";
        File f = new File(fullPath);
        if (f.isDirectory()) {
            if (f.exists() == false) {
                f.mkdirs();
            }
        }
        return fullPath;
    }
}
