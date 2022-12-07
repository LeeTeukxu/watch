package com.zhide.govwatch.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.zhide.govwatch.common.addressParsor;
import com.zhide.govwatch.model.addressInfo;
import com.zhide.govwatch.vo.autoFamingExcel;
import com.zhide.govwatch.vo.autoShiYongExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YiShiyongExcelListener extends AnalysisEventListener<autoShiYongExcel> {
    List<autoShiYongExcel> res = new ArrayList<>();
    addressParsor addressParsor=new addressParsor();
    Logger logger= LoggerFactory.getLogger(YiShiyongExcelListener.class);
    @Override
    public void invoke(autoShiYongExcel allPatentInfo, AnalysisContext analysisContext) {
        if (allPatentInfo != null) {
            String famingmc= allPatentInfo.getFamingmc();
            if(StringUtils.isEmpty(famingmc)==false){
                famingmc=famingmc.trim();
                if(famingmc.indexOf("[")>-1 && famingmc.indexOf("]")>-1){
                    String[] fas=famingmc.split("]");
                    if(fas.length==2) {
                        allPatentInfo.setFamingmc(fas[1]);
                        allPatentInfo.setShenqinglx(fas[0].replace("[","").substring(0,2));
                    }
                }
            }
            String Shenqingh= allPatentInfo.getShenqingh();
            //logger.info("Shenqingh is:%s,famingmc is:%s",Shenqingh,famingmc);

            Shenqingh=Shenqingh.replace("CN","");
            Shenqingh=Shenqingh.replace(".","");
            allPatentInfo.setShenqingh(Shenqingh);

            String GonKaiHao=allPatentInfo.getGonkaihao();
            GonKaiHao=GonKaiHao.replace("CN","");
            allPatentInfo.setGonkaihao(GonKaiHao);

            String Address= allPatentInfo.getAddress();
            //拆分地址中的邮编和地址
            if(StringUtils.isEmpty(Address)==false){
                String postpatten="^\\d{6}";
                Pattern postR=Pattern.compile(postpatten);
                Matcher pMather=postR.matcher(Address);
                if(pMather.find()){
                    String postCode= pMather.group(0);
                    allPatentInfo.setPostcode(postCode);

                    allPatentInfo.setAddress(Address.replace(postCode,""));
                    allPatentInfo.setPriaddress(allPatentInfo.getAddress());
                }

                //拆分地址中的省市县
                String realAddress=allPatentInfo.getAddress();
                String yealAddress=realAddress.replace("邵东县","邵东市");
                addressInfo addInfo= addressParsor.getDetail(yealAddress);
                allPatentInfo.setProvinceName(addInfo.getProvince());
                allPatentInfo.setCityName(addInfo.getCity());
                allPatentInfo.setCountyName(addInfo.getCounty());
                allPatentInfo.setAddress(realAddress);
                allPatentInfo.setPriaddress(realAddress);

                //logger.info("function has processed address!");
            }

            //处理代理机构名称及代码
            String Dailijgmc=allPatentInfo.getDailijgmc();
            if(StringUtils.isEmpty(Dailijgmc)==false){

                String daiPatten="\\d{5}$";
                Pattern daiR=Pattern.compile(daiPatten);
                Matcher dMather=daiR.matcher(Dailijgmc);

                if(dMather.find()){
                    String daiCode= dMather.group(0);
                    allPatentInfo.setDailijgdm(daiCode);
                    allPatentInfo.setDailijgmc(Dailijgmc.replace(daiCode,""));
                } else allPatentInfo.setDailijgmc(Dailijgmc);

                //logger.info("function has processed Dailijgmc！");
            }


            String realShenqingrxm= allPatentInfo.getShenqingrxm();
            if(StringUtils.isEmpty(realShenqingrxm)==false){
                if(realShenqingrxm.startsWith("全部"))realShenqingrxm=realShenqingrxm.substring(2);
                String[] rs=realShenqingrxm.trim().split(";");
                allPatentInfo.setClientName(rs[0]);

                allPatentInfo.setPrishenqingrxm(rs[0]);
                allPatentInfo.setShenqingrxm(realShenqingrxm);

                //logger.info("function has processed shenqingrxm！");
            }
            String realFamingrxm= allPatentInfo.getFamingrxm();
            if(StringUtils.isEmpty(realFamingrxm)==false){
                //if(realFamingrxm.equals("不公告发明人")) realFamingrxm="";
                if(StringUtils.isEmpty(realFamingrxm)) {
                    if (realFamingrxm.startsWith("全部")) realFamingrxm = realFamingrxm.substring(2);
                    String[] rs = realFamingrxm.trim().split(";");
                    allPatentInfo.setPrifamingrxm(rs[0]);
                    allPatentInfo.setFamingrxm(realFamingrxm);
                } else {
                    allPatentInfo.setFamingrxm("");
                    allPatentInfo.setPrifamingrxm("");
                }
               // logger.info("function has processed realFamingrxm");
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
            //logger.info("function has add allPatentInfo to list！");
        } else {
            logger.info("allPatentInfo is null!");
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<autoShiYongExcel> getResult() {
        return res;
    }
}
