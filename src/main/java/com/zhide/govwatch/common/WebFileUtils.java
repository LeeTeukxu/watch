package com.zhide.govwatch.common;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class WebFileUtils {
    public static void download(String fileName, byte[] bytes, HttpServletResponse response) {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/force-download");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,
                    "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            os.write(bytes);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String download(String fileName, File file, HttpServletResponse response) throws Exception {
        if (file.exists() == true) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
                throw e;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else throw new Exception(file.getPath() + "不存在。");
    }
}
