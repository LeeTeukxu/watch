package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.ListUtils;
import com.zhide.govwatch.common.StringUtilTool;
import com.zhide.govwatch.define.ItbDepListService;
import com.zhide.govwatch.mapper.DepListMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.TreeNode;
import com.zhide.govwatch.model.tbDepList;
import com.zhide.govwatch.repository.tbDepListRepository;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class tbDepListServiceImpl implements ItbDepListService {
    @Autowired
    private tbDepListRepository tbDepListRepository;

    @Autowired
    StringRedisTemplate redisRep;

   @Autowired
    DepListMapper depListMapper;

    @Override
    public List<tbDepList> getAll(String CompanyID) {
//        return tbDepListRepository.findAll();
        return tbDepListRepository.findAllByCompanyId(Integer.parseInt(CompanyID));
    }


    @Override
    public List<Map<String,Object>> getDepPantPermissionXinxi(int DepId){
        return tbDepListRepository.getDepPantPermissionXinxi(DepId);
    }

    @Override
    public List<tbDepList>findAllByDepId(int DepId){
        return tbDepListRepository.findAllByDepId(DepId);
    }

    @Override
    public List<Map<String, Object>> Chengshixiaqiye() {
        return null;
    }

    @Override
   public List<tbDepList> getAllDep(int DepId){
       List<tbDepList>findAllByDepId=tbDepListRepository.findAllByDepId(DepId);
       List<tbDepList> Zuizhon = null;
//       if (findAllByDepId.get(0).getDeptype()==1){//省
//           Zuizhon= tbDepListRepository.getAllByCanUse(true);
//       }
//       else if(findAllByDepId.get(0).getDeptype()==2)//市
//       {
//           Zuizhon= tbDepListRepository.getAllShiDep(DepId,true);
//       }else if(findAllByDepId.get(0).getDeptype()==3){//园区
//           Zuizhon=tbDepListRepository.findAllByDepId(DepId);
//       }
       return Zuizhon;
   }

    @Override
    @CacheEvict(value = "getAllCanuDepList")
    public List<tbDepList> getAllCanUse() {
       return tbDepListRepository.getAllByCanUse(true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAll(List<Map<String, Object>> datas, String CompanyID) {
        LoginUserInfo Info = CompanyContext.get();
        Map<Integer, Integer> KeyHash = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            tbDepList depInfo = new tbDepList();
            Map<String, Object> data = datas.get(i);
            int depId = 0;
            if (data.containsKey("depId") == false) {
                int Id = Integer.parseInt(data.get("_id").toString());
                depId = tbDepListRepository.getMax();
                KeyHash.put(Id, depId);
            } else {
                depId = Integer.parseInt(data.get("depId").toString());
            }
            depInfo.setDepId(depId);
            int pid = 0;
            if (data.containsKey("pid")) {
                pid = Integer.parseInt(data.get("pid").toString());
            } else {
                int ppId = Integer.parseInt(data.get("_pid").toString());
                if (KeyHash.containsKey(ppId)) pid = KeyHash.get(ppId);
            }
            depInfo.setPid(pid);
            depInfo.setCanUse(Boolean.parseBoolean(data.get("canUse").toString()));
            depInfo.setName(StringUtilTool.getValue(data.get("name")));
            depInfo.setMemo(StringUtilTool.getValue(data.get("memo")));

            String pSort = StringUtil.leftPad(Integer.toString(pid), 6, "0");
            List<tbDepList> parentInfos = tbDepListRepository.findAllByDepId(pid);
            if (parentInfos.size() > 0) {
                tbDepList parent = parentInfos.get(0);
                pSort = parent.getSort();
            }
            String dSort = StringUtil.leftPad(Integer.toString(depId), 6, "0");
            depInfo.setSort(pSort + dSort);
            depInfo.setCreateMan(Info.getUserId());
            depInfo.setCreateTime(new Date());
            depInfo.setCompanyId(Integer.parseInt(CompanyID));
            tbDepListRepository.save(depInfo);
        }
        RemoveAllKeys();
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAll(List<Integer> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Integer id = ids.get(i);
            List<tbDepList> deps = tbDepListRepository.findAllByDepId(id);
            if (deps.size() == 1) {
                tbDepList depInfo = deps.get(0);
                tbDepListRepository.delete(depInfo);
            }
        }
        RemoveAllKeys();
        return true;
    }


    @Override
   public List<tbDepList> getfindAllByName(String Name) throws Exception{
        List<tbDepList> tbDepList= tbDepListRepository.findAllByName(Name);
        return tbDepList;
    }
    @Override
    public List<tbDepList> findAllByDeptype(){
//        List<tbDepList> getFindshizhou=tbDepListRepository.findAllByDeptype(2);
//        return getFindshizhou;
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbDepList shengSave() {
        LoginUserInfo info = CompanyContext.get();
        int pid=0;
        int Did=1;
        String zhen="true";
        String Name="湖南省";
        tbDepList InsettbDepList=new tbDepList();
//        InsettbDepList.setSn("000001");
        InsettbDepList.setPid(pid);
        InsettbDepList.setCanUse(Boolean.parseBoolean(zhen.toString()));
        InsettbDepList.setName(StringUtilTool.getValue(Name));
        InsettbDepList.setDepId(Did);
//        InsettbDepList.setDeptype(Did);
        InsettbDepList.setSort("000001");
//        InsettbDepList.setUserId(info.getUserId());
//        InsettbDepList.setUploadtime(new Date());
        tbDepList dgbe=tbDepListRepository.save(InsettbDepList);
        return dgbe;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbDepList ShiSave(String Name){
     LoginUserInfo info = CompanyContext.get();
     List<tbDepList> findOderdescShizhouName = tbDepListRepository.findOderdescShizhouName();
     int SNANDSort = 0;
     String PingjieSort="";
     if (findOderdescShizhouName.size()==0){
         PingjieSort="000001000001";
     }
     else
     {
      SNANDSort=Integer.parseInt(findOderdescShizhouName.get(0).getSort().toString()) +1;
      PingjieSort="00000"+StringUtilTool.getValue(SNANDSort);
     }

     int DepID = tbDepListRepository.getMax();
     int pid=1;
     String zhen="true";
        tbDepList InsettbDepList = new tbDepList();
//        InsettbDepList.setSn(StringUtilTool.getValue(PingjieSort));
        InsettbDepList.setPid(pid);
        InsettbDepList.setCanUse(Boolean.parseBoolean(zhen.toString()));
        InsettbDepList.setName(StringUtilTool.getValue(Name));
        InsettbDepList.setDepId(DepID);
//        InsettbDepList.setDeptype(2);
        InsettbDepList.setSort(StringUtilTool.getValue(PingjieSort));
//        InsettbDepList.setUserId(info.getUserId());
//        InsettbDepList.setUploadtime(new Date());
        tbDepList dgbe=tbDepListRepository.save(InsettbDepList);
        return dgbe;
    }





    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbDepList YuanquSave(String Name,int ShidepId,String ShiSort){
        LoginUserInfo info = CompanyContext.get();
        List<tbDepList> findOderdescYuanquPID= tbDepListRepository.findOderdescYuanquPID(ShidepId);
        int SNASort = 0;
        String YuanquSort="";
        if (findOderdescYuanquPID.size()!=0){
            YuanquSort=findOderdescYuanquPID.get(0).getSort().toString(); //查询园区最后一条sort
        }
        String PingjieSort="";
        String zhen="true";
        int DepID = tbDepListRepository.getMax();
        if (findOderdescYuanquPID.size()==0){
            PingjieSort=ShiSort+"000001";
        }
        else
        {
            String QIanshiyiwei = YuanquSort.substring(0,11);
            String HouShiyiwei = YuanquSort.substring(11);
            SNASort=Integer.parseInt(HouShiyiwei.toString()) +1;
            PingjieSort=QIanshiyiwei+StringUtilTool.getValue(SNASort);
        }
        tbDepList InsettbDepList = new tbDepList();
//        InsettbDepList.setSn(StringUtilTool.getValue(PingjieSort));
        InsettbDepList.setPid(ShidepId);
        InsettbDepList.setCanUse(Boolean.parseBoolean(zhen.toString()));
        InsettbDepList.setName(StringUtilTool.getValue(Name));
        InsettbDepList.setDepId(DepID);
//        InsettbDepList.setDeptype(3);
        InsettbDepList.setSort(StringUtilTool.getValue(PingjieSort));
//        InsettbDepList.setUserId(info.getUserId());
//        InsettbDepList.setUploadtime(new Date());
        tbDepList dgbe=tbDepListRepository.save(InsettbDepList);
        return dgbe;
    }


    @Cacheable(value = "getAllUsersByDep")
    @Override
    public List<TreeNode> getAllUsersByDep() {
        List<Map<String, Object>> OO = tbDepListRepository.getAllUsersInDep();
        List<TreeNode> Nodes = JSON.parseArray(JSON.toJSONString(OO), TreeNode.class);
        return Nodes;
    }

    @Cacheable(value = "getAllLoginUserInDep")
    @Override
    public List<TreeNode> getAllLoginUserInDep() {
        List<Map<String, Object>> OO = tbDepListRepository.getAllLoginUserInDep();
        List<TreeNode> Nodes = JSON.parseArray(JSON.toJSONString(OO), TreeNode.class);
        return Nodes;
    }

    @Override
    public List<Map<String, Object>> getAllByCanUseAndDepNum() {
        return tbDepListRepository.getAllByCanUseAndDepNum();
    }

    private void RemoveAllKeys() {
        List<String> Keys = Arrays.asList("getAllLoginUserInDep", "getAllUsersByDep", "getAllCanuDepList");
        LoginUserInfo Info = CompanyContext.get();
        for (int i = 0; i < Keys.size(); i++) {
            String Key = Keys.get(i);
            String RKey = Key;
            redisRep.delete(RKey);
        }
    }
    private Optional<tbDepList> FindNodeByDepID(List<tbDepList> Nodes,Integer ID){
        return  Nodes.stream().filter(f->f.getDepId()==ID).findFirst();
    }
    public Map<Integer, Integer> GetEmployeeNumbers() throws Exception  {
        Map<Integer, Integer> Res = new HashMap<>();
        List<tbDepList> Deps = tbDepListRepository.getAllByCanUse(true);
        List<Map<String, Object>> AllNums = tbDepListRepository.getAllByCanUseAndDepNum();
        List<Map<String, Object>> Nums = AllNums.stream().filter(f -> Integer.parseInt(f.get("Num").toString()) > 0).collect(Collectors.toList());
        List<tbDepList> Parents = new ArrayList<>();
        Nums.stream().forEach(f -> {
            Integer DepID = Integer.parseInt(f.get("DepID").toString());
            Optional<tbDepList> findSingle =FindNodeByDepID(Deps,DepID);// Deps.stream().filter(x -> x.getDepId() == DepID).findFirst();
            if (findSingle.isPresent()) {
                tbDepList tb = findSingle.get();
                tb.setNum(Integer.parseInt(f.get("Num").toString()));
                Optional<tbDepList>PNodes=FindNodeByDepID(Deps,tb.getPid());
                if(PNodes.isPresent()) {
                    Optional<tbDepList> findOne =FindNodeByDepID(Parents,tb.getPid());// Parents.stream().filter(y -> y.getDepId() == ParentID).findFirst();
                    if (findOne.isPresent() == false) Parents.add(PNodes.get());
                }
            }
        });
        List<tbDepList> NS = ListUtils.clone(Parents).stream()
                .sorted((a,b)->-Integer.compare(a.getSort().length(),b.getSort().length()))
                .collect(Collectors.toList());
        while (true) {
            Parents.clear();
            for (int i = 0; i < NS.size(); i++) {
                tbDepList Parent = NS.get(i);
                Integer DepID=Parent.getDepId();
                tbDepList RealOne=FindNodeByDepID(Deps,DepID).get();
                Integer Total =
                        Deps.stream().filter(f -> f.getPid() == DepID).mapToInt(f -> f.getNum()).sum()+RealOne.getNum();
                RealOne.setNum(Total);
                Optional<tbDepList> findOne =FindNodeByDepID(Deps,Parent.getPid()); //Deps.stream().filter(y -> y.getDepId() == Parent.getPid()).findFirst();
                if (findOne.isPresent() == true) {
                    tbDepList PS=findOne.get();
                    if(FindNodeByDepID(Parents,PS.getDepId()).isPresent()==false)Parents.add(PS);
                }
            }
            break;
            //if(Parents.size()>0) NS= ListUtils.clone(Parents);else break;
        }
        Deps.stream().filter(f->f.getNum()>0).forEach(f->{
            Res.put(f.getDepId(),f.getNum());
        });
        return Res ;
    }
    public List<Map<String,Object>> getAllLoginUserByFun(String FunName){
      return   tbDepListRepository.getAllLoginUserByFun(FunName);
    }

    @Override
    public Map<String, Integer> getAllByNameAndID() throws Exception {
        Map<String,Integer> result = new HashMap<>();
        List<Map<String,Object>> rows = depListMapper.getAllByNameAndID();
        rows.stream().forEach(f -> {
            Object OX = f.get("Name");
            if (ObjectUtils.isEmpty(OX) == false){
                String Name = OX.toString();
                Integer ID = Integer.parseInt(f.get("ID").toString());
                if (result.containsKey(Name) == false){
                    result.put(Name,ID);
                }
            }
        });
        return result;
    }
}
