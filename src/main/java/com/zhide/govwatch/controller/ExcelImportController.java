package com.zhide.govwatch.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.UploadUtils;
import com.zhide.govwatch.common.successResult;
import com.zhide.govwatch.config.*;
import com.zhide.govwatch.mapper.ClientInfoMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.tbClientRepository;
import com.zhide.govwatch.repository.tbDepListRepository;
import com.zhide.govwatch.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/excelImport")
public class ExcelImportController {

    @Autowired
    tbDepListRepository depListRepository;
    @Autowired
    tbClientRepository clientRepository;
    @Autowired
    ClientInfoMapper clientMapper;
    @Autowired
    MongoTemplate mongoRep;
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    Validator validator;

    Logger logger= LoggerFactory.getLogger(ExcelImportController.class);
    @RequestMapping("/selectPantentXls")
    @ResponseBody
    public successResult selectPatentXls(String  type,String mode,MultipartFile file) throws IOException {
        LoginUserInfo Info = CompanyContext.get();
        successResult result = new successResult();
        try {
            String CollectionName=UUID.randomUUID().toString();
            String filePath = UploadUtils.approvalFile(file);
            //开始解析EXCEL
            long a1 = System.currentTimeMillis();
            AnalysisEventListener Patent=null;
            Object listPatent=new ArrayList<>();
            if(mode.equals("Run")) {
                Patent = new RenPatentExcelListener();
                EasyExcel.read(filePath, pantentExcel.class,Patent).build().readAll();
                listPatent = ((RenPatentExcelListener)Patent).getResult();
            }
            else if(mode.equals("Yi")) {
                if(type.equals("发明公布")) {
                    Patent = new YiFamingExcelListener();
                    EasyExcel.read(filePath, autoFamingExcel.class, Patent).build().readAll();
                    listPatent=((YiFamingExcelListener)Patent).getResult();
                } else {
                    Patent = new YiShiyongExcelListener();
                    EasyExcel.read(filePath, autoShiYongExcel.class, Patent).build().readAll();
                    listPatent=((YiShiyongExcelListener)Patent).getResult();
                }
            }
            else if(mode.equals("Guan")){
                Patent=new GuanFamingExcelListener();
                EasyExcel.read(filePath, guanFamingExcel.class,Patent).build().readAll();
                listPatent=((GuanFamingExcelListener)Patent).getResult();
            }
            long a2 = System.currentTimeMillis();
            logger.info("解析EXCEL时间:"+(Long.toString(a2-a1)));
            boolean exist= mongoRep.collectionExists(CollectionName);
            if(exist){
                mongoRep.dropCollection(CollectionName);
            }
            BasicDBObject OO=new BasicDBObject();
            OO.put("shenqingh",1);

            mongoRep.createCollection(CollectionName).createIndex(OO);
            List<patentMongo> PP= JSON.parseArray(JSON.toJSONString(listPatent), patentMongo.class);
            Long K1=System.currentTimeMillis();
            for(int i=0;i<PP.size();i++) {
                patentMongo V=PP.get(i);
                Set<ConstraintViolation<patentMongo>> Ps = validator.validate(V);
                if(Ps.size()>0) {
                    String K="";
                    for (ConstraintViolation<patentMongo> m : Ps) {
                        K+=m.getMessage()+";";
                    }
                    V.setErrorMessage(K);
                }
            }
            Long K2=System.currentTimeMillis();
            logger.info("Used:"+Long.toString(K2-K1));
            int pages=(PP.size()%5000)+1;
            for(int i=0;i<pages;i++) {
                List<patentMongo> ms=PP.stream().skip(i*5000).limit(5000).collect(Collectors.toList());
                mongoRep.insert(ms, CollectionName);
            }
            logger.info("将Excel记录写入到Mongodb成功!");
            result.setData(CollectionName);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
    @RequestMapping("/selectCompanyXls")
    @ResponseBody
    public successResult selectCompanyXls(MultipartFile file) throws IOException {
        LoginUserInfo Info = CompanyContext.get();
        successResult result = new successResult();
        try {
            String CollectionName=UUID.randomUUID().toString();
            String filePath = UploadUtils.approvalFile(file);
            //开始解析EXCEL
            long a1 = System.currentTimeMillis();
            AllCompanyExcelListener   companyObject = new AllCompanyExcelListener();
            EasyExcel.read(filePath, allCompanyExcel.class,companyObject).build().readAll();
            List<allCompanyExcel>listPatent =companyObject.getResult();
            long a2 = System.currentTimeMillis();
            logger.info("解析EXCEL时间:"+(Long.toString(a2-a1)));
            boolean exist= mongoRep.collectionExists(CollectionName);
            if(exist){
                mongoRep.dropCollection(CollectionName);
            }
            BasicDBObject OO=new BasicDBObject();
            OO.put("CreditCode",1);

            mongoRep.createCollection(CollectionName).createIndex(OO);
            List<companyAllMongo> PP = JSON.parseArray(JSON.toJSONString(listPatent), companyAllMongo.class);
            Long K1 = System.currentTimeMillis();
            int pages = (PP.size() % 5000) + 1;
            for (int i = 0; i < pages; i++) {
                List<companyAllMongo> ms = PP.stream().skip(i * 5000).limit(5000).collect(Collectors.toList());
                mongoRep.insert(ms, CollectionName);
            }
            Long K2 = System.currentTimeMillis();
            logger.info("将Excel记录写入到Mongodb成功,累计用时:" + Long.toString(K2 - K1));
            result.setData(CollectionName);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }

    @RequestMapping("/selectTechXls")
    @ResponseBody
    public successResult selectTechXls(MultipartFile file) throws IOException {
        LoginUserInfo Info = CompanyContext.get();
        successResult result = new successResult();
        try {
            String CollectionName = UUID.randomUUID().toString();
            String filePath = UploadUtils.approvalFile(file);
            //开始解析EXCEL
            long a1 = System.currentTimeMillis();
            TechCompanyExcelListener companyObject = new TechCompanyExcelListener();
            EasyExcel.read(filePath, techCompanyExcel.class, companyObject).headRowNumber(2).build().readAll();
            List<techCompanyExcel> listPatent = companyObject.getResult();
            long a2 = System.currentTimeMillis();
            logger.info("解析EXCEL时间:" + (Long.toString(a2 - a1)));
            boolean exist = mongoRep.collectionExists(CollectionName);
            if (exist) {
                mongoRep.dropCollection(CollectionName);
            }
            BasicDBObject OO = new BasicDBObject();
            OO.put("ID", 1);

            mongoRep.createCollection(CollectionName).createIndex(OO);
            List<techCompanyMongo> PP = JSON.parseArray(JSON.toJSONString(listPatent), techCompanyMongo.class);
            Long K1 = System.currentTimeMillis();
            int pages = (PP.size() % 5000) + 1;
            for (int i = 0; i < pages; i++) {
                List<techCompanyMongo> ms = PP.stream().skip(i * 5000).limit(5000).collect(Collectors.toList());
                mongoRep.insert(ms, CollectionName);
            }
            Long K2 = System.currentTimeMillis();
            logger.info("将Excel记录写入到Mongodb成功,累计用时:" + Long.toString(K2 - K1));
            result.setData(CollectionName);
        } catch (Exception ax) {
            result.raiseException(ax);
        }
        return result;
    }
}
