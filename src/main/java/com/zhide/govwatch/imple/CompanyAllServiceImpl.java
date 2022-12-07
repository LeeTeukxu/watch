package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.RedisUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.ICompanyAllService;
import com.zhide.govwatch.mapper.CompanyAllMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.companyallRepository;
import com.zhide.govwatch.repository.tbClientRepository;
import com.zhide.govwatch.repository.tbclienttechrecordsRepository;
import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: CompanyAllServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月31日 13:39
 **/
@Service
public class CompanyAllServiceImpl implements ICompanyAllService {
    @Autowired
    MongoTemplate mongoRep;
    @Autowired
    tbClientRepository clientRep;
    @Autowired
    companyallRepository companyAllRep;
    @Autowired
    tbclienttechrecordsRepository recordRep;
    @Autowired
    CompanyAllMapper allMapper;
    Logger logger = LoggerFactory.getLogger(CompanyAllServiceImpl.class);
    @Autowired
    RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> ImportAll(String CollectionName) throws Exception {
        int pageSize = 100;
        Map<String, Object> res = new HashMap<>();
        Criteria Where= Criteria.where("CreditCode").ne(null);
        Long Nums = mongoRep.count(new Query().addCriteria(Where),CollectionName);
        int pages = Integer.parseInt(Long.toString(Nums / pageSize)) + 1;
        Integer TotalNum = 0;
        Long X1 = System.currentTimeMillis();
        for (int i = 0; i < pages; i++) {
            Query query = new Query();
            query.addCriteria(Where);
            query.skip(i * pageSize).limit(pageSize);
            query.with(Sort.by("CreditCode"));
            List<companyAllMongo> Ms = mongoRep.find(query, companyAllMongo.class, CollectionName);
            if (Ms.size() > 0) {
                if (Ms.size() > 0) {
                    String VX = JSON.toJSONString(Ms);
                    List<companyall> data = JSON.parseArray(VX, companyall.class).stream().distinct().collect(toList());
                    Integer ZZ = importOne(data);
                    TotalNum += ZZ;
                }
            }
        }
        Long X2 = System.currentTimeMillis();
        logger.info("一共保存了" + Integer.toString(TotalNum) + "条记录,共用时:" + Long.toString(X2 - X1));
        mongoRep.dropCollection(CollectionName);
        res.put("Time", (X2 - X1));
        res.put("Num", TotalNum);
        return res;
    }

    /**
     * create by: mmzs
     * description: TODO
     * create time:
     * 导入高企资料
     * CompanyType 1高企 2具备高企资格 3非高企
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> ImportAll1(String CollectionName) throws Exception {
        int pageSize = 100;
        Map<String, Object> res = new HashMap<>();
        Criteria Where = new Criteria();
        Long Nums = mongoRep.count(new Query().addCriteria(Where), CollectionName);
        int pages = Integer.parseInt(Long.toString(Nums / pageSize)) + 1;
        Integer TotalNum = 0;
        LoginUserInfo Info = CompanyContext.get();
        Long X1 = System.currentTimeMillis();
        for (int i = 0; i < pages; i++) {
            Query query = new Query();
            query.addCriteria(Where);
            query.skip(i * pageSize).limit(pageSize);
            query.with(Sort.by("ID"));
            List<techCompanyMongo> Ms = mongoRep.find(query, techCompanyMongo.class, CollectionName);
            List<String> Names = Ms.stream().map(f -> f.getName()).distinct().collect(toList());
            List<tbClient> findClients = clientRep.findAllByNameIn(Names);
            List<tbClient> saves = new ArrayList<>();
            List<tbclienttechrecords> saveRecords = new ArrayList<>();
            for (int n = 0; n < Ms.size(); n++) {
                techCompanyMongo M = Ms.get(n);
                String Name = M.getName();
                String Code = M.getCode();
                Integer Year = Integer.parseInt(Code.substring(2, 6));
                Optional<tbClient> fs = findClients.stream().filter(f -> f.getName().equals(Name)).findFirst();
                Integer ClientID = 0;
                if (fs.isPresent()) {
                    tbClient client = fs.get();
                    client.setCompanyTYpe(1);
                    client.setTechYear(Year);
                    client.setTechNo(Code);
                    saves.add(client);

                    ClientID = client.getClientId();
                } else {
                    tbClient newOne = new tbClient();
                    newOne.setName(Name);
                    newOne.setFullName(Name);
                    newOne.setCompanyTYpe(1);
                    newOne.setTechYear(Year);
                    newOne.setTechNo(Code);
                    newOne.setCreateMan((Info.getUserId()));
                    newOne.setCreateTime(new Date());
                    tbClient nn = clientRep.save(newOne);
                    TotalNum++;
                    ClientID = nn.getClientId();
                }

                tbclienttechrecords rs = new tbclienttechrecords();
                rs.setClientId(ClientID);
                rs.setName(Name);
                rs.setNo(Code);
                rs.setCreateMan(Info.getUserId());
                rs.setCreateTime(new Date());
                saveRecords.add(rs);
            }
            if (saves.size() > 0) {
                clientRep.saveAll(saves);
                TotalNum += saves.size();
            }
            if (saveRecords.size() > 0) {
                recordRep.saveAll(saveRecords);
            }
        }
        Long X2 = System.currentTimeMillis();
        logger.info("一共保存了" + Integer.toString(TotalNum) + "条记录,共用时:" + Long.toString(X2 - X1));
        mongoRep.dropCollection(CollectionName);
        res.put("Time", (X2 - X1));
        res.put("Num", TotalNum);
        return res;
    }

    @Modifying
    public Integer importOne(List<companyall> infos) throws Exception {
        LoginUserInfo LoginInfo = CompanyContext.get();
        infos = infos.stream()
                .filter(f -> StringUtils.isEmpty(f.getCreditCode()) == false)
                .filter(f -> f.getCreditCode().length() > 4)
        .collect(toList());

        List<companyall> ress = new ArrayList<>();
        int aNum = 0;
        List<String> Names = infos.stream().filter(f -> StringUtils.isEmpty(f.getName()) == false)
        .map(f -> f.getName().trim()).distinct()
        .collect(toList());
        List<tbClient> Clients = clientRep.findAllByNameIn(Names);
        List<String> Codes = infos.stream().map(f -> f.getCreditCode()).distinct().collect(toList());
        List<companyall> companys = companyAllRep.findAllByCreditCodeIn(Codes);
        List<companyall> updates = new ArrayList<>();
        List<tbClient> clients = new ArrayList<>();
        for (companyall info : infos) {
            Optional<companyall> findPs = companys.stream().filter(f -> f.getCreditCode().equals(info.getCreditCode())).findFirst();
            if (findPs.isPresent()) {
                companyall p = findPs.get();
                p.setName(info.getName());
                p.setOldName(info.getOldName());
                p.setAddress(info.getAddress());
                p.setCompanyType(info.getCompanyType());
                p.setBusinessType(info.getBusinessType());
                p.setCityName(info.getCityName());
                p.setProvinceName(info.getProvinceName());
                p.setCountyName(info.getCountyName());
                p.setLegalMan(info.getLegalMan());
                p.setPriPhone(info.getPriPhone());
                p.setSecPhone(info.getSecPhone());
                p.setPriEmail(info.getPriEmail());
                p.setSecEmail(info.getSecEmail());
                p.setRegisterDate(info.getRegisterDate());
                p.setRegisterMoney(info.getRegisterMoney());
                p.setStatus(info.getStatus());
                p.setConfirmDate(info.getConfirmDate());
                p.setRangeText(info.getRangeText());
                p.setUrl(info.getUrl());
                p.setEName(info.getEName());
                updates.add(p);
            } else {
                info.setCreateMan(LoginInfo.getUserId());
                info.setCreateTime(new Date());
                updates.add(info);

            }
        }
        aNum=updates.size();
        companyAllRep.saveAll(updates);
        for(tbClient client:Clients){
            if(StringUtils.isEmpty(client.getCreditCode())==false) continue;
            String Name=client.getName();
            Optional<companyall> findCs=infos.stream().filter(f->f.getName().equals(Name)).findFirst();
            if(findCs.isPresent()){
                companyall company=findCs.get();
                client.setAddress(company.getAddress());
                client.setLinkMan(company.getLegalMan());
                client.setLinkPhone(company.getPriPhone());
                String secPhone=company.getSecPhone();
                if(StringUtils.isEmpty(secPhone))secPhone="-";
                if(secPhone.length()>2){
                    client.setTele(secPhone);
                }
                client.setCreditCode(company.getCreditCode());
                client.setEmail(company.getPriEmail());
                client.setMemo(company.getRangeText());
                client.setCreateTime(new Date());
                client.setCreateMan(LoginInfo.getUserId());
                clients.add(client);
            }
        }
        clientRep.saveAll(clients);
        return aNum;
    }
    @Override
    public pageObject getData(HttpServletRequest request) throws  Exception {
        pageObject object = new pageObject();
        Map<String, Object> parameters=getParameters(request);
        List<Map<String, Object>> datas = allMapper.getData(parameters);
        int Total = 0;
        List<Map<String, Object>> PP = new ArrayList<>();
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
        }
        object.setTotal(Total);
        object.setData(datas);
        return object;
    }
    private  Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "CREATETIME";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex == 0 ? pageIndex * pageSize : pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        } else params.put("andItems", new ArrayList<>());
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        String shenqinghs = request.getParameter("word");
        params.put("shenqinghs", shenqinghs);
        return params;
    }
}
