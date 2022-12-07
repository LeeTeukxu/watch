package com.zhide.govwatch.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtils {
    private String companyId;
    FtpPath ftpPath;
    FTPUtil ftpUtil;

    public static UploadUtils getInstance(String companyId) {
        return new UploadUtils(companyId);
    }

    private UploadUtils(String companyId) {
        this.companyId = companyId;
        ftpPath = new FtpPath(companyId);
        ftpUtil = new FTPUtil();
    }
    public static String approvalFile(MultipartFile filecontent){
        OutputStream os = null;
        InputStream inputStream = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmss");
        String fileName = simpleDateFormat.format(new Date()) + ".xlsx";
//        String fileName = filecontent.getOriginalFilename();
        String path = null;
        try {
            inputStream = filecontent.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            path ="/Excel/SourceFile/";

            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath()+ "/" + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path +fileName;
    }

    public String SetSaveExcelFilePath(){
        String FilePath = "D:/Excel/UploadAfter";
        File file = new File(FilePath);
        if (!file.exists()){
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmss");
        String saveExcelFilePath = FilePath + "/" + simpleDateFormat.format(new Date()) + ".xlsx";
        return saveExcelFilePath;
    }

    public uploadFileResult uploadAttachment(String fileName, InputStream stream) {
        uploadFileResult result = new uploadFileResult();
        String path = ftpPath.getAttachment();
        boolean oo = false;
        try {
            oo = ftpUtil.upload(stream, fileName, path);
        } catch (Exception ax) {
            ax.printStackTrace();
            result.setMessage(ax.getMessage());
            oo = false;
        }
        result.setFullPath(path + fileName);
        result.setSuccess(oo);
        return result;
    }
}
