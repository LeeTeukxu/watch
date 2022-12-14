package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.IFeeMemoService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.feeMemoRepository;
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
public class FeeMemoServiceImpl implements IFeeMemoService {
    @Autowired
    feeMemoRepository feeMemoRep;
    @Autowired
    tbAttachmentRepository attRep;
    @Autowired
    tbImageMemoRepository memoRep;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean SaveAll(List<feeMemo> memoList) {
        LoginUserInfo Info = CompanyContext.get();
        for (int i = 0; i < memoList.size(); i++) {
            feeMemo fee = memoList.get(i);
            if (fee.getId() == null) {
                fee.setCreateMan(Info.getUserId());
                fee.setCreateTime(new Date());
            } else {
                fee.setUpdateMan(Info.getUserId());
                fee.setUpdateTime(new Date());
            }
            feeMemoRep.save(fee);
        }
        return true;
    }

    @Override
    public List<feeMemo> GetData(String SHENQINGH, String Type) {
        List<feeMemo> items = feeMemoRep.getAllByShenqinghAndFeename(SHENQINGH, Type);
        for (int i = 0; i < items.size(); i++) {
            feeMemo item = items.get(i);
            int Days = DateTimeUtils.getDays(item.getCreateTime(), new Date());
            if (Days > 2) {
                item.setEdit(0);
            } else item.setEdit(1);
        }
        return items;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean DeleteByID(int ID) {
        feeMemoRep.deleteById(ID);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void SaveImageFollowRecord(feeMemo record) throws Exception {
        LoginUserInfo Info=CompanyContext.get();
        String ImageData=record.getImageData();
        if(ImageData.length()>200) {
            UploadUtils uploadUtils = UploadUtils.getInstance(Info.getCompanyId().toString());
            byte[] Bs = Base64Utils.decodeFromString(ImageData.substring(ImageData.indexOf("base64,") + 7));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Bs);
            uploadFileResult result = uploadUtils.uploadAttachment(UUID.randomUUID().toString() + ".jpg", inputStream);
            if (result.isSuccess()) {
                tbAttachment newOne = new tbAttachment();
                newOne.setName(Long.toString(System.currentTimeMillis()) + ".jpg");
                newOne.setType(1);
                newOne.setGuid(UUID.randomUUID().toString());
                newOne.setPath(result.getFullPath());
                newOne.setUploadMan(Info.getUserId());
                newOne.setUploadManName(Info.getUserName());
                newOne.setUploadTime(new Date());
                newOne.setSize(ImageData.length());
                attRep.save(newOne);

                String Mx=record.getMemo();

                feeMemo targetMemo = null;
                String sss = record.getShenqingh();
                List<String>IDS= Arrays.stream(sss.split(",")).collect(Collectors.toList());
                for(String Shenqingh:IDS){
                    feeMemo memo=ListUtils.clone(Arrays.asList(record)).get(0);
                    String SubId=memo.getSubId();
                    if(StringUtils.isEmpty(SubId)){
                        memo.setSubId(UUID.randomUUID().toString());
                        memo.setImageData("1");
                        memo.setMemo("");
                        memo.setShenqingh(Shenqingh);
                        memo.setCreateTime(new Date());
                        memo.setCreateMan(Info.getUserId());
                        feeMemoRep.save(memo);
                        targetMemo=memo;
                    }else {
                        Optional<feeMemo> findMemos=feeMemoRep.findFirstBySubId(SubId);
                        if (findMemos.isPresent()){
                            feeMemo memo1=findMemos.get();
                            memo1.setMemo("");
                            memo1.setImageData("1");
                            memo1.setShenqingh(Shenqingh);
                            memo1.setUpdateTime(new Date());
                            memo1.setUpdateMan(Info.getUserId());
                            feeMemoRep.save(memo1);
                            targetMemo=memo1;
                        }
                    }
                    tbImageMemo newImage=new tbImageMemo();
                    newImage.setPid(targetMemo.getSubId());
                    newImage.setAttId(newOne.getGuid());
                    newImage.setMemo(Mx);
                    newImage.setCreateManName(Info.getUserName());
                    newImage.setCreateTime(new Date());
                    memoRep.save(newImage);

                    List<tbImageMemo> images=memoRep.findAllByPid(memo.getSubId());
                    Integer Index=1;
                    List<String> XS=new ArrayList<>();
                    for(tbImageMemo image:images){
                        String mText=image.getMemo();
                        if(StringUtils.isEmpty(mText)==false){
                            XS.add(Integer.toString(Index)+"???"+mText);
                            Index++;
                        }
                    }
                    targetMemo.setMemo(String.join("<br/>",XS));
                    feeMemoRep.save(targetMemo);
                }
            }
        }
    }

    @Override
    public List<String> getImages(String MID) throws Exception {
        List<String> res=new ArrayList<>();
        List<tbImageMemo> images= memoRep.findAllByPid(MID);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy???MM???dd???");
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
                        if(org.apache.commons.lang.StringUtils.isEmpty(Memo))Memo="?????????"; else Memo="??????:"+Memo;
                        Memo=Memo.replace("|","");
                        Memo=att.getUploadManName()+"???:"+simpleDateFormat.format(att.getUploadTime())+"??????,"+Memo;
                        res.add("/images/"+uId+"/"+tempFile.getName()+"|"+Memo);
                    } else  throw new Exception("???????????????????????????????????????,????????????!");
                } else throw new Exception("??????Ftp???????????????!");
            } else   throw new Exception("???????????????????????????????????????,????????????!");
        }
        return res;
    }
}
