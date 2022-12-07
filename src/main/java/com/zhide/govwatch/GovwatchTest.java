package com.zhide.govwatch;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.patentQueryBuilder;
import com.zhide.govwatch.config.queryExpress;
import com.zhide.govwatch.model.GovPatentInfo;
import com.zhide.govwatch.model.sqlParameter;
import org.hibernate.id.GUIDGenerator;

import java.net.SocketOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName: GovwatchTest
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月30日 17:22
 **/
public class GovwatchTest {
    public static void main(String[] args){
//        String VV="{\n" +
//                "  \"AppNo\": \"2017101640579\",\n" +
//                "  \"AppDate\": \"1489680000000\",\n" +
//                "  \"Title\": \"量子点-染料共敏化太阳能电池的制备方法\",\n" +
//                "  \"Applicant\": \"中南大学\",\n" +
//                "  \"Inventors\": \"杨英;张政;郭学益;高菁;潘德群\",\n" +
//                "  \"CpqueryStatus\": \"专利权维持\",\n" +
//                "  \"UpdateTime\": \"1651202753000\",\n" +
//                "  \"Agency\": \"长沙朕扬知识产权代理事务所（普通合伙）\",\n" +
//                "  \"Agent\": \"魏龙霞\",\n" +
//                "  \"AppType\": \"1\"\n" +
//                "}";
//        GovPatentInfo DD= JSON.parseObject(VV, GovPatentInfo.class);
        SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd= null;
        try {
            dd = ss.parse("2022-01-01 00:00:00");
            System.out.println(dd.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ;
//        SimpleDateFormat ss=new SimpleDateFormat("yyyyMMddHHmmss");
//        try {
//            Date  sd=ss.parse("20220315145301");
//            int a=1;
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        queryExpress express=new queryExpress();
//        Long X1=System.currentTimeMillis();
//        try {
//           sqlParameter K= express.parse("A=B And E=G And M=N");
//           //sqlParameter K=express.parse("((A=B And C=D) Or E=F) And G=H Or X=Y");
//            patentQueryBuilder d=new patentQueryBuilder();
//            d.create(K);
//            Long Y1=System.currentTimeMillis();
//           String KK= JSON.toJSONString(K);
//           System.out.println(KK);
//           System.out.println((Y1-X1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
