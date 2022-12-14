package com.zhide.govwatch.imple;

import com.google.common.base.Strings;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.IPatentMemoService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.patentMemoRepository;
import com.zhide.govwatch.repository.tbAttachmentRepository;
import com.zhide.govwatch.repository.tbImageMemoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PatentMemoServiceImpl implements IPatentMemoService {
    @Autowired
    tbAttachmentRepository attRep;
    @Autowired
    tbImageMemoRepository imageRep;

    @Override
    public List<String> getImages(String MID) throws Exception {
        List<String> res=new ArrayList<>();
        List<tbImageMemo> images= imageRep.findAllByPid(MID);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        for(tbImageMemo image :images){
            String attId=image.getAttId();
            Optional<tbAttachment> findAtts=attRep.findAllByGuid(attId);
            if(findAtts.isPresent()){
                tbAttachment att = findAtts.get();
                String fullPath=att.getPath();
                String fileName= UUID.randomUUID().toString()+".jpg";
                String uId= UUID.randomUUID().toString();
                String tempDir= Paths.get(CompanyPathUtils.getImages(),uId).toString();
                String tempFileName = Paths.get(tempDir, fileName).toString();

                DirectoryUtils.createNotExists(tempDir);
                String filePath = att.getPath();
                FTPUtil Ftp = new FTPUtil();
                if (Ftp.connect() == true) {
                    Ftp.download(filePath, tempFileName);
                    File tempFile = new File(tempFileName);
                    if (tempFile.exists()) {
                        String Memo=image.getMemo();
                        if(StringUtils.isEmpty(Memo))Memo="无备注"; else Memo="备注:"+Memo;
                        Memo=Memo.replace("|","");
                        Memo=att.getUploadManName()+"于:"+simpleDateFormat.format(att.getUploadTime())+"上传,"+Memo;
                        res.add("/images/"+uId+"/"+tempFile.getName()+"|"+Memo);
                    } else  throw new Exception("指定的文件在服务器上不存在,下载失败!");
                } else throw new Exception("连接Ftp服务器出错!");
            } else   throw new Exception("指定的文件在服务器上不存在,下载失败!");
        }
        return res;
    }

    @Autowired
    patentMemoRepository memoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SaveAll(Object menuName, List<patentMemo> Infos) throws Exception {
        LoginUserInfo LoginInfo = CompanyContext.get();
        String Shenqinghs = Infos.get(0).getShenqingh();
        List<String> IDS = Arrays.stream(Shenqinghs.split(",")).collect(Collectors.toList());
        List<patentMemo> ms=new ArrayList<>();
        for (String Shenqingh : IDS) {
            List<patentMemo> Infoss = ListUtils.clone(Infos);
            for (int i = 0; i < Infoss.size(); i++) {
                patentMemo Info = Infoss.get(i);
                if (Strings.isNullOrEmpty(Info.getMid()) == true) {
                    Info.setCreateMan(LoginInfo.getUserId());
                    Info.setCreateDate(new Date());
                    Info.setMid(UUID.randomUUID().toString());
                } else {
                    Info.setUpdateDate(new Date());
                    Info.setUpdateMan(LoginInfo.getUserId());
                }
                Info.setShenqingh(Shenqingh);
                ms.add(Info);
            }
        }
        if(ms.size()>0)memoRepository.saveAll(ms);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @MethodWatch(name = "删除专利备注记录")
    public boolean RemoveAll(List<String> IDS) throws Exception {
        for (int i = 0; i < IDS.size(); i++) {
            String  ID = IDS.get(i);
            Optional<patentMemo> fInfo = memoRepository.findFirstByMid(ID);
            if (fInfo.isPresent()) {
                patentMemo memo=fInfo.get();
                String MID=memo.getMid();
                List<tbImageMemo> images=imageRep.findAllByPid(MID);
                List<String>AttIDS=images.stream().map(f->f.getAttId()).collect(Collectors.toList());
                attRep.deleteAllByGuidIn(AttIDS);
                imageRep.deleteAll(images);
                memoRepository.delete(fInfo.get());
            } else throw new Exception(ID + "所指向的数据已不存在。");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void SaveImage(patentMemo memo) throws Exception {
        LoginUserInfo Info=CompanyContext.get();
        String ImageData=memo.getImageData();
        if(ImageData.length()>200){
            UploadUtils uploadUtils= UploadUtils.getInstance(Integer.toString(Info.getCompanyId()));
            byte[] Bs = Base64Utils.decodeFromString(ImageData.substring(ImageData.indexOf("base64,")+7));
            ByteArrayInputStream inputStream=new ByteArrayInputStream(Bs);
            uploadFileResult result= uploadUtils.uploadAttachment(UUID.randomUUID().toString()+".jpg",inputStream);
            if(result.isSuccess()){
                tbAttachment newOne=new tbAttachment();
                newOne.setName(Long.toString(System.currentTimeMillis())+".jpg");
                newOne.setType(1);
                newOne.setGuid(UUID.randomUUID().toString());
                newOne.setPath(result.getFullPath());
                newOne.setUploadMan(Info.getUserId());
                newOne.setUploadManName(Info.getUserName());
                newOne.setUploadTime(new Date());
                newOne.setSize(ImageData.length());
                attRep.save(newOne);

                patentMemo targetMemo=SaveImageObjects(Info,newOne.getGuid(),memo);
            } else throw new Exception("上传图片文件失败，操作被中止!");
        }
    }
    private patentMemo SaveImageObjects(LoginUserInfo Info,String AttID,patentMemo oneMemo) throws Exception{
        String Mx=oneMemo.getMemo();

        patentMemo targetMemo=null;
        String sss=oneMemo.getShenqingh();
        List<String>IDS= Arrays.stream(sss.split(",")).collect(Collectors.toList());
        for(String Shenqingh:IDS){
            patentMemo memo=ListUtils.clone(Arrays.asList(oneMemo)).get(0);
            String MID=memo.getMid();
            if(StringUtils.isEmpty(MID)){
                memo.setMid(UUID.randomUUID().toString());
                memo.setImageData("1");
                memo.setMemo("");
                memo.setShenqingh(Shenqingh);
                memo.setCreateDate(new Date());
                memo.setCreateMan(Info.getUserId());
                memoRepository.save(memo);
                targetMemo=memo;
            } else {
                Optional<patentMemo> findMemos=memoRepository.findFirstByMid(MID);
                if(findMemos.isPresent()){
                    patentMemo memo1=findMemos.get();
                    memo1.setMemo("");
                    memo1.setImageData("1");
                    memo1.setShenqingh(Shenqingh);
                    memo1.setUpdateDate(new Date());
                    memo1.setUpdateMan(Info.getUserId());
                    memoRepository.save(memo1);
                    targetMemo=memo1;
                }
            }
            tbImageMemo newImage=new tbImageMemo();
            newImage.setPid(targetMemo.getMid());
            newImage.setAttId(AttID);
            newImage.setMemo(Mx);
            newImage.setCreateManName(Info.getUserName());
            newImage.setCreateTime(new Date());
            imageRep.save(newImage);

            List<tbImageMemo> images=imageRep.findAllByPid(memo.getMid());
            Integer Index=1;
            List<String> XS=new ArrayList<>();
            for(tbImageMemo image:images){
                String mText=image.getMemo();
                if(StringUtils.isEmpty(mText)==false){
                    XS.add(Integer.toString(Index)+"、"+mText);
                    Index++;
                }
            }
            targetMemo.setMemo(String.join("<br/>",XS));
            memoRepository.save(targetMemo);
        }
        oneMemo.setShenqingh(IDS.get(0));
        return oneMemo;
    }

}
