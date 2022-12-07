package com.zhide.govwatch.controller.common;

import com.zhide.govwatch.common.*;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.tbAttachment;
import com.zhide.govwatch.model.techSupportAttachment;
import com.zhide.govwatch.repository.tbAttachmentRepository;
import com.zhide.govwatch.repository.techSupportAttachmentRepository;
import io.netty.util.internal.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    techSupportAttachmentRepository techRep;


    @RequestMapping("/addFile")
    public String AddFile(String IDS, String Mode, String ShowHis, String UploadUrl, String ManyFile,
            String FileType,String SubID, Map<String,Object> model) {
        if (StringUtils.isEmpty(Mode)) Mode = "Add";
        model.put("Mode", Mode);
        if (StringUtils.isEmpty(IDS)) IDS = "";
        model.put("IDS", IDS);
        if (StringUtil.isNullOrEmpty(ShowHis)) ShowHis = "0";
        model.put("ShowHis", ShowHis);
        if (StringUtils.isEmpty(UploadUrl) == true) UploadUrl = "/attachment/upload";
        model.put("UploadUrl", UploadUrl);//自定义上传方法
        if (StringUtils.isEmpty(ManyFile)) {
            model.put("ManyFile", "true");//是否可以选择多个文件并多次上传。
        } else model.put("ManyFile", ManyFile);
        if(StringUtils.isEmpty(FileType))FileType="";
        model.put("FileType",FileType);
        model.put("HasFile",0);
        if(StringUtils.isEmpty(SubID))SubID=""; else {
            Optional<techSupportAttachment> findAtt= techRep.findFirstByRefId(SubID);
            if(findAtt.isPresent())model.replace("HasFile",1);
        }
        model.put("SubID",SubID);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.put("Now", simpleDateFormat.format(new Date()));
        return "/common/addFile";
    }

    @Autowired
    tbAttachmentRepository attachmentRep;

    private UploadUtils getUtils() throws Exception {
        LoginUserInfo info = CompanyContext.get();
        if (info == null) throw new Exception("登录失败，请重新登录!");
        Integer companyId = info.getCompanyId();
        return UploadUtils.getInstance(companyId.toString());
    }

    @RequestMapping("/upload")
    public @ResponseBody
    @Transactional(rollbackOn = Exception.class)
    successResult Upload(MultipartFile file) {
        successResult result = new successResult();
        FileInputStream fileInputStream;
        try {
            LoginUserInfo loginInfo = CompanyContext.get();
            String fileName = file.getOriginalFilename();
            String[] exts = fileName.split("\\.");
            String extName = exts[exts.length - 1];
            String uuId = UUID.randomUUID().toString();
            String uploadFileName = uuId + "." + extName;
            String targetFile = CompanyPathUtils.getFullPath("Temp", uploadFileName);
            File fx = new File(targetFile);
            FileUtils.writeByteArrayToFile(fx, file.getBytes());
            if (fx.exists()) {
                try {
                    fileInputStream = new FileInputStream(fx);
                    UploadUtils uploadUtils = getUtils();
                    uploadFileResult rr = uploadUtils.uploadAttachment(uploadFileName, fileInputStream);
                    if (rr.isSuccess() == true) {
                        tbAttachment tb = new tbAttachment();
                        tb.setGuid(uuId);
                        tb.setName(fileName);
                        tb.setPath(rr.getFullPath());
                        tb.setType(1);
                        tb.setSize(Integer.parseInt(Long.toString(fx.length())));
                        tb.setUploadMan(loginInfo.getUserId());
                        tb.setUploadManName(loginInfo.getUserName());
                        tb.setUploadTime(new Date());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Map<String, Object> data = new HashMap<>();
                        data.put("AttType", 1);
                        data.put("AttID", uuId);
                        data.put("CreateTime", simpleDateFormat.format(new Date()));
                        data.put("CreateMan", loginInfo.getUserName());
                        data.put("Name", fileName);
                        attachmentRep.save(tb);
                        result.setData(data);
                    }
                    fileInputStream.close();
                } catch (Exception ax) {
                    result.raiseException(ax);
                } finally {
                    FileUtils.forceDeleteOnExit(fx);
                }
            } else throw new Exception("保存上传文件失败!");
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/getAttachmentByIDS")
    public @ResponseBody
    successResult GetAttachmentByIDS(String IDS) {
        successResult result = new successResult();
        try {
            List<Map<String, Object>> rr = new ArrayList<>();
            if (StringUtils.isEmpty(IDS) == false) {
                String[] IDX = IDS.split(",");
                SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<tbAttachment> atts = attachmentRep.findAllByGuidInOrderByUploadTime(Arrays.asList(IDX));
                for (int i = 0; i < atts.size(); i++) {
                    tbAttachment att = atts.get(i);
                    Integer Type = att.getType();
                    if (Type == null) Type = 1;
                    Map<String, Object> r = new HashMap<>();
                    r.put("ATTID", att.getGuid());
                    r.put("FILENAME", att.getName());
                    r.put("FILESIZE", att.getSize());
                    r.put("FTYPE", Type);
                    r.put("UPLOADTIME", simple.format(att.getUploadTime()));
                    r.put("UPLOADMAN", att.getUploadManName());
                    rr.add(r);
                }
                result.setData(rr);
            }
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/download")
    public void Download(String AttID, HttpServletResponse response) {
        try {
            Optional<tbAttachment> findOne = attachmentRep.findFirstByGuid(AttID);
            if (findOne.isPresent()) {
                tbAttachment tb = findOne.get();
                String Path = tb.getPath();
                FTPUtil ftpUtil = new FTPUtil();
                String SavePath = CompanyPathUtils.getFullPath("Temp", tb.getName());
                if (ftpUtil.connect() == true) {
                    ftpUtil.download(Path, SavePath);
                    WebFileUtils.download(tb.getName(), new File(SavePath), response);
                } else response.getWriter().write("FTP登录失败!");
            } else response.getWriter().write("下载的文件不存在!");
        } catch (Exception ax) {
            try {
                response.getWriter().write(ax.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    @Transactional
    public successResult DeleteByID(String AttID) {
        successResult result = new successResult();
        try {
            attachmentRep.deleteAllByGuid(AttID);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
