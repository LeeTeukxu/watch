package com.zhide.govwatch.imple;


import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.IClientInfoService;
import com.zhide.govwatch.mapper.ClientInfoMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClientInfoService implements IClientInfoService {

    @Autowired
    ClientInfoMapper clientMapper;

    @Autowired
    tbClientRepository clientRepository;

    @Autowired
    tbClientLinkersRepository clientLinkersRepository;

    @Autowired
    followRecordRepository followRecordRepository;

    @Autowired
    tbLinkersUpdateRecordRepository linkersUpdateRecordRep;
    @Autowired
    tbClientLinkersRepository linkerRep;
    @Autowired
    tbLoginUserRepository loginUserRep;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    NBBHCreator nbCreator;
    @Autowired
    patentInfoPermissionRepository patentPerRep;
    @Autowired
    tbAttachmentRepository attRep;
    @Autowired
    tbImageMemoRepository memoRep;

    Logger logger= LoggerFactory.getLogger(ClientInfoService.class);
    @Override
    @Cacheable(value = "getAllClientByNameAndID",keyGenerator = "CompanyKeyGenerator")
    public Map<String, String> getAllByNameAndID() throws Exception {
        Map<String, String> result = new HashMap<>();
        List<Map<String, Object>> rows = clientMapper.getAllByNameAndID();
        rows.stream().forEach(f -> {
            Object OX = f.get("Name");
            if (ObjectUtils.isEmpty(OX) == false) {
                String Name = OX.toString();
                String  ID =f.get("ID").toString();
                if (result.containsKey(Name) == false) {
                    result.put(Name, ID);
                }
            }
        });
        return result;

    }

    @Override
    @Cacheable(value = "getAllClientByIDAndName",keyGenerator = "CompanyKeyGenerator")
    public Map<String , String> getAllByIDAndName() throws Exception {
        Map<String , String> result = new HashMap<>();
        List<Map<String, Object>> rows = clientMapper.getAllByNameAndID();
        rows.stream().forEach(f -> {
            String  ID =f.get("ID").toString();
            if (result.containsKey(ID) == false) {
                String Name = f.get("Name").toString();
                result.put(ID, Name);
            }
        });
        return result;
    }

    @Override
    public pageObject getPageData(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = clientMapper.getPageData(params);
        int total = 0;
        if (rows.size() > 0) {
            total = Integer.parseInt(rows.get(0).get("_TotalNum").toString());
            rows.forEach(f -> {
                if (f.containsKey("_TotalNum")) f.remove("_TotalNum");
            });
        }
        result.setData(rows);
        result.setTotal(total);
        return result;
    }

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = clientMapper.getData(params);
        int total = 0;
        if (rows.size() > 0) {
            total = Integer.parseInt(rows.get(0).get("_TotalNum").toString());
            rows.forEach(f -> {
                if (f.containsKey("_TotalNum")) f.remove("_TotalNum");
            });
        }
        result.setData(rows);
        result.setTotal(total);
        return result;
    }

    public List<Map<String, Object>> getAllClientInfoExistLinkMan(HttpServletRequest request) throws Exception {
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = clientMapper.getAllClientInfoExistLinkMan(params);
        return rows;
    }

    private Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
//        int ClientID=Integer.parseInt(request.getParameter("ClientID"));
//        int SignMan=Integer.parseInt(request.getParameter("SignMan"));
        if (sortField.isEmpty()) sortField = "SN";
        String key = request.getParameter("key");
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        if (Strings.isEmpty(key) == false) {
            key = URLDecoder.decode(key, "utf-8");
            params.put("key", "%" + key + "%");

        }

        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            queryText = URLDecoder.decode(queryText, "utf-8");
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            highText = URLDecoder.decode(highText);
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        } else params.put("andItems", new ArrayList<>());
//        if (ClientID!=0){
//            params.put("ClientID",ClientID);
//        }
//        if (SignMan!=0){
//            params.put("SignMan",SignMan);
//        }
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("UserID", Info.getUserId());
            params.put("RoleName", Info.getRoleName());
            params.put("DepID", Info.getDepId());
            params.put("RoleID", Info.getRoleId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }
    @Cacheable(value="getClientJSR",keyGenerator = "CompanyKeyGenerator")
    public List<Map<String, Object>> getJSR(int ClientID,int UserID,int DepID,String RoleName) {
        Map<String, Object> params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("RoleName",RoleName);
        params.put("DepID",DepID);
        params.put("ID", ClientID);
        return clientMapper.getJSR(params);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClientAndClientLinkers Save(tbClient client, tbClientLinkers clientLinkers, String mode) throws Exception {
        ClientAndClientLinkers result = new ClientAndClientLinkers();
        if (client == null) throw new Exception("提交的客户数据为空!");
        LoginUserInfo loginUserInfo = CompanyContext.get();
        if (loginUserInfo == null) throw new Exception("登录失效，请重新登录!");
        String ClientName=client.getName();
        if(StringUtils.isEmpty(ClientName)) throw new Exception("客户名称不能为空!");
        ClientName=ClientName.trim();
        if (client.getClientId()!=null) {
            Optional<tbClient> fClient = clientRepository.findById(client.getClientId());
            if (fClient.isPresent()) {
                EntityHelper.copyObject(client, fClient.get());
            }
        } else {
            Optional<tbClient> nameClients=clientRepository.findFirstByName(ClientName);
            if(nameClients.isPresent()){
                throw new Exception("【"+ClientName+"】已经建立了客户档案，如有需要请联系系统管理员进行客户信息转移!");
            }
            client.setCreateTime(new Date());
            client.setCreateMan(loginUserInfo.getUserId());
            client.setCanUse(true);
            client.setCanLogin(false);
        }
        if(StringUtils.isEmpty(client.getPassword())){
            //  client.setPassword(MD5Util.EnCode("123456"));
        }
        client.setName(ClientName);
        tbClient lastClient = clientRepository.save(client);
        if (mode.indexOf("Add") != -1) {
            clientLinkers.setClientID(lastClient.getClientId());
            SaveLinkers(clientLinkers);
        }
        result.setClient(lastClient);
        result.setClientLinkers(clientLinkers);
        redisUtils.clearAll("Client");
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public followRecord SaveFollowRecord(followRecord followRecord) throws Exception {
        followRecord result = null;
        LoginUserInfo info = CompanyContext.get();
        if (info == null) throw new Exception("登录失效，请重新登录！");
        if (followRecord.getId() == null) {
            followRecord.setCreateTime(new Date());
//            followRecord.setFid(UUID.randomUUID().toString());
            followRecord.setDepId(info.getDepId());
            followRecord.setFollowUserName(info.getUserName());
            followRecord.setRoleId(info.getRoleId());
            followRecord.setRoleName(info.getRoleName());
            result = followRecord;
        } else {
            Optional<followRecord> find = followRecordRepository.findById(followRecord.getId());
            if (find.isPresent()) {
                followRecord fx = find.get();
                fx.setRecord(followRecord.getRecord());
                fx.setUpdateTime(new Date());
                result = fx;
            } else throw new Exception("数据异常:" + Integer.toString(followRecord.getId()) + "无法获取到数据!");
        }
        return followRecordRepository.save(result);
    }

    @Override
    public Page<followRecord> getFollowRecords(int ClientID, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("createTime").descending());
        Page<followRecord> recordPage = followRecordRepository.findAllByClientId(ClientID, pageable);
        return recordPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbClientLinkers SaveLinkers(tbClientLinkers linkers) throws Exception {
        tbClientLinkers obj = null;
        LoginUserInfo loginInfo = CompanyContext.get();
        tbLinkersUpdateRecord updateRecord = new tbLinkersUpdateRecord();
        updateRecord.setUpID(UUID.randomUUID().toString());
        updateRecord.setActionTime(new Date());
        updateRecord.setActionMan(loginInfo.getUserId());
        updateRecord.setClientID(linkers.getClientID());
        if (linkers.getLinkID() == null) {
            linkers.setCreateTime(new Date());
            updateRecord.setActionType("增加");
            updateRecord.setAObj("{}");
            updateRecord.setBObj(JSON.toJSONString(linkers));
            obj = linkers;
        } else {
            Optional<tbClientLinkers> finds = linkerRep.findById(linkers.getLinkID());
            if (finds.isPresent()) {
                obj = linkers;
                updateRecord.setActionType("更新");
                updateRecord.setAObj(JSON.toJSONString(finds.get()));
                updateRecord.setBObj(JSON.toJSONString(linkers));
            } else throw new Exception(linkers.getLinkMan() + "的数据已不存在。操作被中止。");
        }
        linkersUpdateRecordRep.save(updateRecord);
        return linkerRep.save(obj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void SaveImageFollowRecord(followRecord record) throws Exception {
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

                String XMemo=record.getRecord();
                if(StringUtils.isEmpty(record.getFid())){
                    record.setFid(UUID.randomUUID().toString());
                    record.setImageData("1");
                    record.setRoleId(Info.getRoleId());
                    record.setRoleName(Info.getRoleName());
                    record.setFollowUserName(Info.getUserName());
                    record.setDepId(Info.getDepId());
                    record.setCreateTime(new Date());
                    record.setUserId(Info.getUserId());
                    record.setUserName(Info.getUserName());
                } else {
                    Optional<followRecord> finds=followRecordRepository.findFirstByFid(record.getFid());
                    if(finds.isPresent()){
                        followRecord re=finds.get();
                        re.setUpdateTime(new Date());
                        record=re;
                    } else throw new Exception("数据发生了改变，请刷新后重试!");
                }
                followRecordRepository.save(record);

                tbImageMemo memo=new tbImageMemo();
                memo.setMemo(XMemo);
                memo.setPid(record.getFid());
                memo.setCreateManName(Info.getUserName());
                memo.setAttId(newOne.getGuid());
                memo.setCreateTime(new Date());
                memoRep.save(memo);


                List<tbImageMemo> ims= memoRep.findAllByPid(record.getFid());
                List<String> XIDS=new ArrayList<>();
                Integer Z=1;
                for(int n=0;n<ims.size();n++){
                    tbImageMemo m=ims.get(n);
                    String X=m.getMemo();
                    if(StringUtils.isEmpty(X)) continue;
                    X=Integer.toString(Z)+":"+X;
                    XIDS.add(X);
                    Z++;
                }
                String Y = String.join("<Br/>", XIDS);
                record.setRecord(Y);
                followRecordRepository.save(record);
            }
        }
    }

    @Override
    @Transactional
    @Modifying
    public void UpdatePID(String Code) throws Exception {
        Optional<tbLoginUser> findUsers = loginUserRep.findFirstByMyCode(Code);
        if (findUsers.isPresent()) {
            Integer PID = findUsers.get().getUserId();
            LoginUserInfo Info = CompanyContext.get();
            Integer UserID = Info.getUserId();
            Optional<tbClient> findClients = clientRepository.findById(UserID);
            if (findClients.isPresent()) {
                tbClient client = findClients.get();
                if (client.getPid() == null) {
                    client.setPid(PID);
                    client.setUseTime(new Date());
                    clientRepository.save(client);
                }
            }
        }
    }

    @Override
    public Page<tbClientLinkers> getLinkers(int ClientID, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("createTime").descending());
        Page<tbClientLinkers> recordPage = linkerRep.findAllByClientID(ClientID, pageable);
        return recordPage;
    }

    @Override
    public Page<tbLinkersUpdateRecord> getLinkersUpdateRecord(int ClientID, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("ActionTime").descending());
        Page<tbLinkersUpdateRecord> recordPage = linkersUpdateRecordRep.findAllByClientID(ClientID, pageable);
        return recordPage;
    }

    @Override
    public pageObject GetLoginClientReords(int ClientID, HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> params = getParameters(request);
        params.put("ClientID", ClientID);
        List<Map<String, Object>> datas = clientMapper.getLoginClientReords(params);
        object.setData(datas);
        return object;
    }

    public followRecord AddFollowRecord(int ClientID, int OLDXS, int NOWXS, String ClientName, List<v_tbEmployee>
            employees) {
        followRecord record = new followRecord();
        if (ClientID != 0) {
            LoginUserInfo info = CompanyContext.get();
            Date nowTime = new Date();
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            String Text = "";
            if (employees.size() > 1) {
                Text = String.format(ClientName + "的跟进人员在" + dt.format(nowTime) + "由" + employees.get(1)
                        .getEmpName() + "变更为" + employees.get(0).getEmpName());
            } else {
                Text = String.format(ClientName + "的跟进人员在" + dt.format(nowTime) + "添加为" + employees.get(0).getEmpName());
            }
            record.setRecord(Text);
            record.setFollowUserName(info.getUserName());
            record.setCreateTime(nowTime);
            record.setClientId(ClientID);
            record = followRecordRepository.save(record);
        }
        return record;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    @MethodWatch(name = "删除客户信息")
    public boolean remove(List<String> ids) {
        LoginUserInfo Info=CompanyContext.get();
        for (int i = 0; i < ids.size(); i++) {
            Integer id = Integer.parseInt(ids.get(i));
            Optional<tbClient> findOnes=clientRepository.findById(id);
            if(findOnes.isPresent()){
                logger.info(Info.getUserName()+"试图删除客户:"+findOnes.get().getName());
            }
            clientMapper.delClient(id);
            clientMapper.delClientLinkers(id);
            clientMapper.delFollowRecord(id);
        }
        return true;
    }

    @Override
    public tbClient findNameByName(String Name) {
        return clientMapper.findNameByName(Name);
    }

}
