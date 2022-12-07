package com.zhide.govwatch.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.zhide.govwatch.common.IntegerUtils;
import com.zhide.govwatch.common.addressParsor;
import com.zhide.govwatch.model.addressInfo;
import com.zhide.govwatch.vo.pantentExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenPatentExcelListener extends AnalysisEventListener<pantentExcel> {
    List<pantentExcel> res = new ArrayList<>();
    addressParsor addressParsor=new addressParsor();
    @Override
    public void invoke(pantentExcel allPatentInfo, AnalysisContext analysisContext) {
        if (allPatentInfo != null) {
            String Shenqingh= allPatentInfo.getShenqingh();
            Shenqingh=Shenqingh.replace("CN","");
            Shenqingh=Shenqingh.replace(".","");
            allPatentInfo.setShenqingh(Shenqingh);

            String GonKaiHao=allPatentInfo.getGonkaihao();
            GonKaiHao=GonKaiHao.replace("CN","");
            allPatentInfo.setGonkaihao(GonKaiHao);

            String Address= allPatentInfo.getAddress();
            String PriAddress= allPatentInfo.getPriaddress();
            //拆分地址中的邮编和地址
            if(StringUtils.isEmpty(Address)==false && StringUtils.isEmpty(PriAddress)==false){
                String[] Adds=Address.split(" ");
                String[] Pris=PriAddress.split(" ");
                if(Adds.length==2){
                    allPatentInfo.setPostcode(Adds[0]);
                    allPatentInfo.setAddress(Adds[1]);
                }
                if(Pris.length==2){
                    allPatentInfo.setPriaddress(Pris[1]);
                }

                //拆分地址中的省市县
                String realAddress=allPatentInfo.getAddress();
                String yealAddress=realAddress.replace("邵东县","邵东市");
                addressInfo addInfo= addressParsor.getDetail(yealAddress);
                String Pre=realAddress.substring(0,6);
                String daiPatten="\\d{6}$";
                Pattern daiR=Pattern.compile(daiPatten);
                Matcher dMather=daiR.matcher(Pre);
                if(dMather.find()){
                    allPatentInfo.setPostcode(Pre);
                    realAddress=realAddress.replace(Pre,"");
                }


                //addressInfo addInfo= addressParsor.getDetail(realAddress);
                allPatentInfo.setProvinceName(addInfo.getProvince());
                allPatentInfo.setCityName(addInfo.getCity());
                allPatentInfo.setCountyName(addInfo.getCounty());
                allPatentInfo.setAddress(realAddress);
                allPatentInfo.setPriaddress(realAddress);
            }

            //处理代理机构名称及代码
            String Dailijgmc=allPatentInfo.getDailijgmc();
            if(StringUtils.isEmpty(Dailijgmc)==false){
                String[] Ds=Dailijgmc.split(" ");
                String jgPatten="\\d{5}$";
                Pattern jgR=Pattern.compile(jgPatten);
                if(Ds.length==2){
                    Matcher jMather=jgR.matcher(Ds[0]);
                    if(jMather.find()){
                        allPatentInfo.setDailijgdm(Ds[0]);
                        allPatentInfo.setDailijgmc(Ds[1]);
                    } else {
                        allPatentInfo.setDailijgdm(Ds[1]);
                        allPatentInfo.setDailijgmc(Ds[0]);
                    }
                }  else {
                    Matcher mather=jgR.matcher(Dailijgmc);
                    if(mather.find()){
                        allPatentInfo.setDailijgdm(mather.group(0));
                        allPatentInfo.setDailijgmc(Dailijgmc.replace(allPatentInfo.getDailijgdm(),""));
                    } else allPatentInfo.setDailijgmc(Dailijgmc);
                }
            }
            String realShenqingrxm= allPatentInfo.getShenqingrxm();
            if(StringUtils.isEmpty(realShenqingrxm)==false){
                String shenqinrxm=realShenqingrxm.trim().split(";")[0];
                allPatentInfo.setClientName(shenqinrxm);
            }
            allPatentInfo.setCreatetime(new Date());
            String shenqinglx=allPatentInfo.getShenqinglx();
            if(StringUtils.isEmpty(shenqinglx)==false){
                if(shenqinglx.equals("发明"))shenqinglx="发明公布";
                if(shenqinglx.equals("外观"))shenqinglx="外观设计";
                if(shenqinglx.equals("实用"))shenqinglx="实用新型";
                allPatentInfo.setShenqinglx(shenqinglx);
            }
            res.add(allPatentInfo);
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<pantentExcel> getResult() {
        return res;
    }
}
