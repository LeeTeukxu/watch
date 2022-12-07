package com.zhide.govwatch.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.zhide.govwatch.common.addressParsor;
import com.zhide.govwatch.model.addressInfo;
import com.zhide.govwatch.vo.autoFamingExcel;
import com.zhide.govwatch.vo.guanFamingExcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuanFamingExcelListener extends AnalysisEventListener<guanFamingExcel> {
    List<guanFamingExcel> res = new ArrayList<>();
    addressParsor addressParsor=new addressParsor();
    @Override
    public void invoke(guanFamingExcel allPatentInfo, AnalysisContext analysisContext) {
        if (allPatentInfo != null) {
            String shenqinglx=allPatentInfo.getShenqinglx();
            if(StringUtils.isEmpty(shenqinglx)==false){
                if(shenqinglx.equals("发明"))shenqinglx="发明公布";
                if(shenqinglx.equals("外观"))shenqinglx="外观设计";
                if(shenqinglx.equals("实用"))shenqinglx="实用新型";
                allPatentInfo.setShenqinglx(shenqinglx);
            }

            String Address= allPatentInfo.getAddress();
            //拆分地址中的邮编和地址
            if(StringUtils.isEmpty(Address)==false){
                allPatentInfo.setPriaddress(allPatentInfo.getAddress());
                //拆分地址中的省市县
                String realAddress=allPatentInfo.getAddress();
                String yealAddress=realAddress.replace("邵东县","邵东市");
                addressInfo addInfo= addressParsor.getDetail(yealAddress);
                allPatentInfo.setProvinceName(addInfo.getProvince());
                allPatentInfo.setCityName(addInfo.getCity());
                allPatentInfo.setCountyName(addInfo.getCounty());
                allPatentInfo.setAddress(realAddress);
                allPatentInfo.setPriaddress(realAddress);
            }

            String realShenqingrxm= allPatentInfo.getShenqingrxm();
            if(StringUtils.isEmpty(realShenqingrxm)==false){
                if(realShenqingrxm.startsWith("全部"))realShenqingrxm=realShenqingrxm.substring(2);
                String[] rs=realShenqingrxm.trim().split(";");
                allPatentInfo.setClientName(rs[0]);

                allPatentInfo.setPrishenqingrxm(rs[0]);
                allPatentInfo.setShenqingrxm(realShenqingrxm);
            }

            allPatentInfo.setCreateTime(new Date());
            res.add(allPatentInfo);
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<guanFamingExcel> getResult() {
        return res;
    }
}
