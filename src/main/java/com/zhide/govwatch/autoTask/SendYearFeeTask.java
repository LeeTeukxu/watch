package com.zhide.govwatch.autoTask;

import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.ISendEmailService;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendYearFeeTask {

    @Autowired
    PatentRepository patentRepository;

    @Autowired
    tbGovFeeRepository govFeeRepository;

    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;

    @Autowired
    tbMailRecordRepository mailRecordRepository;

    @Autowired
    tbExcelTemplateRepository excelRep;

    @Autowired
    ISendEmailService sendEmailService;

    @Autowired
    PatentContactRepository patentContactRepository;

//    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行一次
    public void SendDJYearFee() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> listNineMap = new ArrayList<>();
        List<Map<String, Object>> listThreeMap = new ArrayList<>();

        List<patentInfoPermission> listPatentInfoPermission = patentInfoPermissionRepository.findAll();
        List<tbGovFee> listGovFee = govFeeRepository.findAll();
        listPatentInfoPermission.stream().forEach(f -> {
            listGovFee.stream().filter(x ->x.getAppNo().equals(f.getShenqingh())).filter(x ->x.getPayState().equals("未缴费")).forEach(n ->{
                try {
                    Date date1 = sdf.parse(n.getLimitDate().toString());
                    String newDate = checkDate(new Date().toString());
                    Date date2 = sdf.parse(newDate);
                    int DateDiff = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
                    tbMailRecord mailRecordNine = mailRecordRepository.findAllByGovFeeIdAndDay(n.getId(), "<=90");
                    tbMailRecord mailRecodeThree = mailRecordRepository.findAllByGovFeeIdAndDay(n.getId(), "<=30");
                    if (DateDiff >= 30 && DateDiff <= 90 && mailRecordNine == null){
                        Map<String, Object> map = new HashMap<>();
                        patent patent = patentRepository.findAllByShenqingh(n.getAppNo());
                        map.put("GovFeeID",n.getId());
                        map.put("UserID",f.getUserid());
                        map.put("PAYSTATE",n.getPayState());
                        map.put("MONEY",n.getAmount());
                        map.put("FAMINGRXM",patent.getShenqingrxm());
                        map.put("SHENQINGR",sdf.format(patent.getShenqingr()));
                        map.put("JIAOFEIR",sdf.format(n.getLimitDate()));
                        map.put("ZHUANLIMC",patent.getFamingmc());
                        map.put("COSTNAME",n.getCostName());
                        map.put("SHENQINGRXM",patent.getShenqingrxm());
                        map.put("SHENQINGH",patent.getShenqingh());
                        listNineMap.add(map);
                    }else if (DateDiff <= 30 && mailRecodeThree == null){
                        Map<String, Object> map = new HashMap<>();
                        patent patent = patentRepository.findAllByShenqingh(n.getAppNo());
                        map.put("GovFeeID",n.getId());
                        map.put("UserID",f.getUserid());
                        map.put("PAYSTATE",n.getPayState());
                        map.put("MONEY",n.getAmount());
                        map.put("FAMINGRXM",patent.getShenqingrxm());
                        map.put("SHENQINGR",sdf.format(patent.getShenqingr()));
                        map.put("JIAOFEIR",sdf.format(n.getLimitDate()));
                        map.put("ZHUANLIMC",patent.getFamingmc());
                        map.put("COSTNAME",n.getCostName());
                        map.put("SHENQINGRXM",patent.getShenqingrxm());
                        map.put("SHENQINGH",patent.getShenqingh());
                        listThreeMap.add(map);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        });
        if (listNineMap.size() > 0) {
            downladSendMailFile(listNineMap,90);
        }
        else if (listThreeMap.size() > 0){
            downladSendMailFile(listThreeMap,30);
        }
    }

    private static String checkDate(String str){
        String format1 = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(str);
            format1 = format.format(date);
        } catch (Exception e) {

        }
        return format1;
    }

    private void downladSendMailFile(List<Map<String, Object>> listMap, Integer days) throws Exception{
        String path = "";
        complexExcelBuilder exB=null;
        Optional<tbExcelTemplate> findOnes=excelRep.findFirstByCode("OneYear");
        if(findOnes.isPresent()) {
            tbExcelTemplate one=findOnes.get();
            String X=one.getTemplatePath();
            if(StringUtils.isEmpty(X)==true) {
                exB = new complexExcelBuilder("OneYear","Sheet0");
            } else exB=new complexExcelBuilder(X,"Sheet0");
        } else {
            exB= new complexExcelBuilder("OneYear","Sheet0");
        }
        if(exB!=null){
            exB.setSheetName("Sheet0");
            exB.setNumberCell("");
            exB.setAutoCreateNew(Boolean.parseBoolean("true"));
        }
        byte[] Bs = exB.SendYearFeeGetContent(listMap,"TaskSend");
        InputStream sbs = new ByteArrayInputStream(Bs);

        if (listMap.size() > 0){
            Optional<Map<String, Object>> find = listMap.stream().findFirst();
            Integer UserID = Integer.parseInt(find.get().get("UserID").toString());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String rad = sdf.format(date);

            EmailContent content = new EmailContent();
            List<TextAndValue> listAtt = new ArrayList<>();
            TextAndValue TxtAtt = new TextAndValue();
            TxtAtt.setText("代缴费清单.xls");
            TxtAtt.setValue(path);
            listAtt.add(TxtAtt);
            content.setAttachments(listAtt);

            List<TextAndValue> listReceive = new ArrayList<>();
            Optional<tbPatentContact> findOne = patentContactRepository.findAllByUserId(UserID);
            if (findOne.isPresent()){
                String MailAddress = findOne.get().getMailAddress();
                String[] Address = MailAddress.split(";");
                for (int i=0;i<Address.length;i++){
                    TextAndValue TxtReceive = new TextAndValue();
                    TxtReceive.setText(Address[i]);
                    TxtReceive.setValue(Address[i]);
                    listReceive.add(TxtReceive);
                }
            }
            content.setReceAddress(listReceive);

            content.setContent("【知服帮】您有专利" + listMap.size() + "件，距离缴纳年费截止日只有" + days + "天了，请登录知服帮系统及时缴纳，以免产生滞纳金或专利失效！");
            content.setSubject(rad + "-代缴费清单");
            sendEmailService.sendEmailByContent(content, sbs, UserID);

            for (int i=0;i<listMap.size();i++){
                Map<String, Object> map = listMap.get(i);
                tbMailRecord record = new tbMailRecord();
                record.setGovFeeId(Integer.parseInt(map.get("GovFeeID").toString()));
                record.setShenqingh(map.get("SHENQINGH").toString());
                record.setDay("<=" + days);
                record.setUserId(Integer.parseInt(map.get("UserID").toString()));
                record.setCreateTime(new Date());
                mailRecordRepository.save(record);
            }
        }
    }
}
