package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.config.CacheableTtl;
import com.zhide.govwatch.define.IPatentService;
import com.zhide.govwatch.mapper.*;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import com.zhide.govwatch.service.AreaCodeService;
import com.zhide.govwatch.vo.PatentChangedInfo;
import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class PatentServiceImpl implements IPatentService {

    @Autowired
    tbareaRepository areaRep;
    @Autowired
    patentMemoMapper infoMemoMapper;
    @Autowired
    SysLoginUserMapper loginUserMapper;
    @Autowired
    PatentRepository pantentInfoRep;
    @Autowired
    patentInfoPermissionRepository pInfo;
    @Autowired
    NBBHCode NBBHCode;
    @Autowired
    tbDictDataRepository tbDictDataRepository;
    @Autowired
    tbDepListRepository tbDepListRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    tbClientRepository clientRep;
    @Autowired
    DepListMapper depListMapper;
    @Autowired
    MongoTemplate mongoRep;
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ViewPatentMapper patentMapper;
    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;
    @Autowired
    PatentMapper batchMapper;
    @Autowired
    ClientInfoMapper clientMapper;
    @Autowired
    RabbitUtil rabbitUtil;
    @Autowired
    allRabbitMQQueueNames allQueues;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    AreaCodeService areaService;
    @Autowired
    patentupdateRepository pUpRep;
    @Autowired
    allRabbitMQQueueNames allMessage;
    @Autowired
    machineRepository machineRep;
    @Autowired
    patentupdatemainRepository patentMainRep;
    Logger logger = LoggerFactory.getLogger(PatentServiceImpl.class);
    LoginUserInfo Info = null;


    @Override
    public pageObject getData(Map<String, Object> parameters) {
        int Total = 0;
        pageObject object = new pageObject();
//        Long B1=System.currentTimeMillis();
//        List<Map<String, Object>> datas = patentMapper.getData(parameters);
//        Long E1=System.currentTimeMillis();

        Long B2=System.currentTimeMillis();
        List<Map<String, Object>> datas=patentMapper.getQuickData(parameters);
        Total=patentMapper.getQuickCount(parameters);
        Long E2=System.currentTimeMillis();

        //logger.info("getData:{},getQuickData:{}",(E1-B1),(E2-B2));

        List<Map<String, Object>> PP = new ArrayList<>();
        if (datas.size() > 0) {
            //Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            List<String> SIDS = datas.stream().map(f -> f.get("SHENQINGH").toString()).collect(toList());
            List<view_patentMemo> memos = infoMemoMapper.getAllByIds(SIDS);
            datas.stream().forEach(f -> {
                Map<String, Object> row = eachSingleRow(f, memos);
                PP.add(row);
            });
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
    }

    public List<String> getQuickCode(Map<String, Object> params) {
        List<String> Codes = patentMapper.getQuickCode(params);
        return Codes;
    }

    @Override
    public Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "CREATETIME";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex == 0 ? pageIndex * pageSize : pageIndex * pageSize + 1);
        params.put("End", pageSize);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importAll(String Mode, String CollectionName) throws Exception {
        int pageSize = 500;
        Info = CompanyContext.get();
        Long Nums = mongoRep.count(new Query(), CollectionName);

        int pages = Integer.parseInt(Long.toString(Nums / pageSize)) + 1;
        Integer TotalNum = 0;
        Long CodeNum = 0L;

        for (int i = 0; i < pages; i++) {
            Query query = new Query();
            query.skip(i * pageSize).limit(pageSize);
            query.with(Sort.by("shenqingh"));
            List<patentMongo> ps = mongoRep.find(query, patentMongo.class, CollectionName);
            if (ps.size() > 0) {
                List<patentMongo> Ms = new ArrayList<>();
                for (int n = 0; n < ps.size(); n++) {
                    patentMongo p = ps.get(n);
                    if (p.isOK() == true) {
                        Ms.add(p);
                    }
                }
                if (Ms.size() > 0) {
                    String VX = JSON.toJSONString(Ms);
                    List<patent> data = JSON.parseArray(VX, patent.class).stream().distinct().collect(toList());
                    CodeNum += data.stream().map(f -> f.getShenqingh()).distinct().count();
                    Long X1 = System.currentTimeMillis();
                    Integer ZZ = importOne(Mode, data);
                    Long X2 = System.currentTimeMillis();
                    //logger.info("保存" + Integer.toString(pageSize) + "条记录用时:" + Long.toString(X2 - X1));
                    TotalNum += ZZ;
                }
            }
        }
        logger.info("一共保存了" + Integer.toString(TotalNum) + "条记录");
        logger.info("CodeNum:" + Long.toString(CodeNum));
        mongoRep.dropCollection(CollectionName);
        redisUtils.clearAll("getPatentTotal");
    }

    @Override
    @CacheableTtl(value = "getPatentTotal",ttl = 600)
    public List<Map<String, Object>> getPatentTotal(int UserID,String RoleName) {
        return patentMapper.getPatentTotal(RoleName,UserID);
    }

    @Override
    public patent getsqh(String SHENQINGH) {
        patent getsqh=pantentInfoRep.findAllByShenqingh(SHENQINGH);
        return getsqh;
    }

    @Override
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    public patent uppateninfo(String SHENQINGH,String ProvinceName,String CityName,String CountyName,String DAILIJGMC,String ADDRESS,Integer ClientID,String ClientName) {
        if(CountyName==null) {
           String proname= ProvinceName.replace("省","市");
            String City= CityName.replace("市","");
            List<tbarea>getarea=areaRep.getareainfo(proname,City,"null");
            Integer CityID=Integer.parseInt(getarea.get(0).getId().toString());
            Integer CountyID=Integer.parseInt(getarea.get(1).getId().toString());
            patent update=pantentInfoRep.findAllByShenqingh(SHENQINGH);
            update.setProvinceId(null);
            update.setProvinceName("");
            update.setCityId(CityID);
            update.setCountyId(CountyID);
            update.setCityName(proname);
            update.setCountyName(City);
            update.setShenqingh(SHENQINGH);
            update.setDailijgmc(DAILIJGMC);
            update.setAddress(ADDRESS);
            update.setCityId(ClientID);
            update.setClientName(ClientName);
            patent spo= pantentInfoRep.save(update);
            return spo;
        }
        else
        {
            List<tbarea>getarea=areaRep.getareainfo(ProvinceName,CityName,CountyName);
            Integer ProvinceID=Integer.parseInt(getarea.get(0).getId().toString());
            Integer CityID=Integer.parseInt(getarea.get(1).getId().toString());
            Integer CountyID=Integer.parseInt(getarea.get(2).getId().toString());
            patent update=pantentInfoRep.findAllByShenqingh(SHENQINGH);
            update.setProvinceId(ProvinceID);
            update.setProvinceName(ProvinceName);
            update.setCityId(CityID);
            update.setCountyId(CountyID);
            update.setCityName(CityName);
            update.setCountyName(CountyName);
            update.setShenqingh(SHENQINGH);
            update.setDailijgmc(DAILIJGMC);
            update.setAddress(ADDRESS);
            update.setCityId(ClientID);
            update.setClientName(ClientName);
            patent spo = pantentInfoRep.save(update);
            return spo;
        }

    }

    @Override
    @Transactional
    public void SaveAll(String[] Machines, List<String> Codes) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
        String No = simple.format(new Date());
        LoginUserInfo Info = CompanyContext.get();
        int Num = Codes.size() / Machines.length;
        logger.info("Num is {}", Num);
        for (int i = 0; i < Machines.length; i++) {
            String Machine = Machines[i];
            Optional<machine> Macs = machineRep.findById(Machine);
            if (Macs.isPresent() == false) continue;
            logger.info("Process {}'s Codes.", Machine);
            machine m = Macs.get();
            if (i + 1 == Machines.length) {
                Num += 10;
            }
            List<String> TinyCodes = Codes.stream().skip(i * Num).limit(Num).collect(toList());
            List<patentupdate> ups = new ArrayList<>();
            for (int a = 0; a < TinyCodes.size(); a++) {
                String Code = TinyCodes.get(a);
                patentupdate up = new patentupdate();
                up.setShenqingh(Code);
                up.setMachine(Machine);
                up.setNo(No);
                up.setCreateTime(new Date());
                ups.add(up);
            }
            if (ups.size() > 0) {
                BabySitterCommand Cmd = new BabySitterCommand();
                Cmd.setID(1);
                Cmd.setArgs(String.join(",", TinyCodes));
                Cmd.setCode("GetMessage");
                Cmd.setNo(No);
                Cmd.setMac(m.getMac());
                rabbitUtil.publish(allMessage.babysitter(), Cmd);
                pUpRep.saveAll(ups);
            }
        }
        if (Num > 0) {
            patentupdatemain main = new patentupdatemain();
            main.setNo(No);
            main.setNum(Codes.size());
            main.setCreateTime(new Date());
            main.setCreateMan(Info.getUserId());
            patentMainRep.save(main);
        }
    }

    @Transactional
    public void RemoveTask(String No) throws Exception {
        List<patentupdate> ups = pUpRep.findAllByNo(No);
        Optional<patentupdatemain> mains = patentMainRep.findFirstByNo(No);
        if (ups.size() > 0 && mains.isPresent()) {
            patentupdate up = ups.get(0);
            String MID = up.getMachine();
            Optional<machine> findMachines = machineRep.findById(MID);
            if (findMachines.isPresent()) {
                machine mx = findMachines.get();

                patentupdatemain m = mains.get();
                BabySitterCommand Cmd = new BabySitterCommand();
                Cmd.setID(1);
                Cmd.setArgs("");
                Cmd.setCode("RemoveTask");
                Cmd.setNo(No);
                Cmd.setMac(mx.getMac());
                rabbitUtil.publish(allMessage.babysitter(), Cmd);

                patentMainRep.deleteByNo(No);
                pUpRep.deleteByNo(No);
                patentMainRep.deleteByNo(No);
            }
        }
    }

    public pageObject getShowTask(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> ps = getParameters1(request);
        List<Map<String, Object>> datas = patentMapper.getShowTask(ps);
        Integer Total = 0;
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
        }
        object.setData(datas);
        object.setTotal(Total);
        return object;
    }

    public pageObject getUpdateMain(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> ps = getParameters1(request);
        List<Map<String, Object>> datas = patentMapper.getUpdateMain(ps);
        Integer Total = 0;
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
        }
        object.setData(datas);
        object.setTotal(Total);
        return object;
    }

    private Map<String, Object> getParameters1(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "GUPDATETIME";
        Map<String, Object> params = new HashMap<>();
        Integer Begin = pageIndex * pageSize;
        params.put("Begin", Begin);
        params.put("End", pageSize + Begin);
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
        String No = request.getParameter("No");
        if (Strings.isEmpty(No) == false) {
            params.put("No", No);
        }
        return params;
    }

    @Override
    @Cacheable(value = "getLawStatus")
    public List<String> getLawStatus() {
        return patentMapper.getLawStatus();
    }

    @Modifying
    public Integer importOne(String Mode, List<patent> infos) throws Exception {
        List<patent> ress = new ArrayList<>();
        int aNum = 0;
        List<String> Names =
                infos.stream().filter(f -> StringUtils.isEmpty(f.getShenqingrxm()) == false)
                        .map(f -> f.getShenqingrxm().trim()).distinct()
                        .collect(toList());
        List<tbClient> Clients = clientRep.findAllByNameIn(Names);
        List<String> Codes = infos.stream().map(f -> f.getShenqingh()).distinct().collect(toList());
        List<patent> pantents = pantentInfoRep.findAllByShenqinghIn(Codes);
        List<patent> Insert = new ArrayList<>();
        List<tbClient> newClients = new ArrayList<>();
        for (patent info : infos) {
            Optional<patent> findPs =
                    pantents.stream().filter(f -> f.getShenqingh().equals(info.getShenqingh())).findFirst();
            if (findPs.isPresent()) {
                patent p = findPs.get();
                info.setId(p.getId());
                info.setCreatetime(p.getCreatetime());
                info.setCreateman(p.getCreateman());
                info.setLastupdatetime(p.getLastupdatetime());
                info.setMemo(p.getMemo());
                info.setLastupdatetime(new Date());
                if (p.getCountyId() != null) {
                    info.setCountyId(p.getCountyId());
                    info.setCountyName(p.getCountyName());
                }
                if (p.getProvinceId() != null) {
                    info.setProvinceId(p.getProvinceId());
                    info.setProvinceName(p.getProvinceName());
                }
                if (p.getCityId() != null) {
                    info.setCityId(p.getCityId());
                    info.setCityName(p.getCityName());
                }
                if (StringUtils.isEmpty(p.getFamingmc()) == false) {
                    info.setFamingmc(p.getFamingmc());
                }
                if (StringUtils.isEmpty(p.getFamingrxm()) == false) {
                    info.setFamingrxm(p.getFamingrxm());
                }
                if (StringUtils.isEmpty(p.getDailijgdm()) == false) {
                    info.setDailijgdm(p.getDailijgdm());
                }
                if(StringUtils.isEmpty(p.getDailirxm())==false){
                    info.setDailirxm(p.getDailirxm());
                }
                if(StringUtils.isEmpty(p.getLawstatus())==false){
                    info.setLawstatus(p.getLawstatus());
                    info.setSeclawstatus(p.getSeclawstatus());
                }
            } else {
                info.setCreateman(Info.getUserId());
                info.setCreatetime(new Date());
            }
            if (info.getCityId() == null) {
                info.setCityId(areaService.getCodeByName(info.getCityName()));
            }
            if (info.getProvinceId() == null) {
                info.setProvinceId(areaService.getCodeByName(info.getProvinceName()));
            }
            if (info.getCountyId() == null) {
                info.setCountyId(areaService.getCodeByName(info.getCountyName()));
            }
            if (StringUtils.isEmpty(info.getShenqingrxm()) == false) {
                String shenqingrxm = info.getShenqingrxm().trim();
                String[] ClientNames = shenqingrxm.split(";");
                for (int i = 0; i < ClientNames.length; i++) {
                    String ClientName = ClientNames[i];
                    Optional<tbClient> findClients =
                            Clients.stream().filter(f -> f.getName().equals(ClientName)).findFirst();
                    tbClient newOne = null;
                    if (findClients.isPresent() == false) {
                        tbClient newClient = new tbClient();
                        newClient.setName(ClientName);
                        newClient.setCreateMan(Info.getUserId());
                        newClient.setCreateTime(new Date());
                        newClient.setAddress(info.getAddress());
                        newClient.setCanUse(true);
                        Optional<tbClient> findCCs =
                                newClients.stream().filter(f -> f.getName().equals(ClientName)).findFirst();
                        if (findCCs.isPresent() == false) {
                            newClients.add(newClient);
                        }
                    } else {
                        newOne = findClients.get();
                        if (info.getClientId() == null) {
                            info.setClientId(newOne.getClientId());
                            info.setClientName(newOne.getName());
                        }
                    }
                }
            }
            String shq = info.getShenqingh();
            Optional<patent> findPss = Insert.stream().filter(f -> f.getShenqingh().equals(shq)).findFirst();
            if (findPss.isPresent() == false) Insert.add(info);
        }
        if (newClients.size() > 0) {
            List<tbClient> saveClients = clientRep.saveAllAndFlush(newClients);
            for (int i = 0; i < saveClients.size(); i++) {
                tbClient one = saveClients.get(i);
                String name = one.getName();
                Optional<patent> findOne =
                        Insert.stream()
                                .filter(f -> StringUtils.isEmpty(f.getShenqingrxm()) == false)
                                .filter(f -> f.getShenqingrxm().equals(name)).findFirst();
                if (findOne.isPresent()) {
                    patent find = findOne.get();
                    find.setClientId(one.getClientId());
                    find.setClientName(one.getName());
                }
                Clients.add(one);
            }
        }
        if (Insert.size() > 0) {
            List<String> CCodes = Insert.stream().map(f -> f.getShenqingh()).collect(toList());
            logger.info("传入:" + Integer.toString(infos.size()) + ",参与保存的:" + Integer.toString(Insert.size()));
            batchMapper.deleteBatch(CCodes);
            aNum = batchMapper.insertBatch(Insert);
            //ress=pantentInfoRep.saveAll(Insert);
            List<PatentChangedInfo> Changed = new ArrayList<>();
            for (int i = 0; i < Insert.size(); i++) {
                PatentChangedInfo item = new PatentChangedInfo();
                item.setUserName(Info.getUserName());
                item.setType("ADD");
                item.setTime(new Date());
                item.setContent(Insert.get(i));
                Changed.add(item);
            }
            rabbitUtil.publish(allQueues.patentInfoChanged(), Changed);
        }
        return aNum;
    }

    private Map<String, Object> eachSingleRow(
            Map<String, Object> row, List<view_patentMemo> memos) {
        row.remove("_TotalNum");
        if(memos.size()>0) {
            String SHENQINGH = row.get("SHENQINGH").toString();
            PantentInfoMemoCreator creator = new PantentInfoMemoCreator(memos);
            List<String> words = creator.Build(SHENQINGH);
            row.put("EDITMEMO", words.size() > 0 ? 1 : 0);
            if (words.size() > 0) {
                row.put("MEMO", String.join("<br/><br/>", words));
            } else {
                row.put("MEMO", "");
            }
        }
        return row;
    }

    @Override
    @Cacheable(key = "GetLoginUserHash")
    public Map<String, String> GetLoginUserHash() {
        Map<String, String> result = new HashMap<>();
        List<Map<String, Object>> rows = loginUserMapper.getAllByIDAndName();
        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> row = rows.get(i);
            result.put(row.get("Name").toString(), row.get("ID").toString());
        }
        return result;
    }

    @Override
    /**
     * create by: mmzs
     * description: TODO
     * create time:
     *
     * @return
     */
    @Cacheable(key = "GetDepListHash")
    public Map<String, String> GetDepListHash() {
        Map<String, String> result = new HashMap<>();
        List<Map<String, Object>> rows = depListMapper.getAllByNameAndID();
        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> row = rows.get(i);
            result.put(row.get("Name").toString(), row.get("ID").toString());
        }
        return result;
    }

    @Override
    public pageObject getByShenqinghsIn(Map<String, Object> parameters) {
        pageObject object = new pageObject();
        List<Map<String, Object>> datas = patentMapper.getByShenqinghsIn(parameters);
        List<patentInfoPermission> listPat = patentInfoPermissionRepository.findAll();
        int Total = 0;
        List<Map<String, Object>> PP = new ArrayList<>();
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            List<String> SIDS = datas.stream().map(f -> f.get("SHENQINGH").toString()).collect(toList());
            datas.stream().forEach(f -> {
                //Map<String, Object> row = eachSingleRow(f,listPat);
                PP.add(f);
            });
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
    }
}
