package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.EntityHelper;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.config.HashMapUtils;
import com.zhide.govwatch.define.ItbCompanyService;
import com.zhide.govwatch.mapper.CompanyMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.tbCompanyRepository;
import com.zhide.govwatch.repository.tbDepListRepository;
import freemarker.template.utility.StringUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class tbCompanyServiceImpl implements ItbCompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    tbCompanyRepository companyRepository;

    @Autowired
    tbDepListRepository depListRepository;

    Logger logger= LoggerFactory.getLogger(tbCompanyServiceImpl.class);
    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = companyMapper.getData(params);
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

    private Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "ID";
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
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("UserID", Info.getUserId());
            params.put("RoleName", Info.getRoleName());
            params.put("DepID", Info.getDepId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbCompany Save(tbCompany company, String mode) throws Exception {
        tbCompany result = new tbCompany();
        LoginUserInfo Info = CompanyContext.get();
        if (company == null) throw new Exception("提交的公司数据为空!");
        LoginUserInfo loginUserInfo = CompanyContext.get();
        if (loginUserInfo == null) throw new Exception("登录失效，请重新登录!");
        String CompanyName=company.getName();
        if(StringUtils.isEmpty(CompanyName)) throw new Exception("公司名称不能为空!");
        CompanyName=CompanyName.trim();
        if (company.getId()!=null) {
            Optional<tbCompany> fCompany = companyRepository.findById(company.getId());
            if (fCompany.isPresent()) {
                EntityHelper.copyObject(company, fCompany.get());
            }
        } else {
            company.setCreateTime(new Date());
            company.setCreateMan(loginUserInfo.getUserId());
        }
        company.setName(CompanyName);
        result = companyRepository.save(company);

        //保存公司时同时默认保存办公室
        if (mode.equals("Add")) {
            SaveDep(result.getId(), Info.getUserId());
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(List<String> ids) {
        LoginUserInfo Info=CompanyContext.get();
        for (int i = 0; i < ids.size(); i++) {
            Integer id = Integer.parseInt(ids.get(i));
            Optional<tbCompany> findOnes=companyRepository.findById(id);
            if(findOnes.isPresent()){
                logger.info(Info.getUserName()+"试图删除公司:"+findOnes.get().getName());
            }
            companyRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<TreeNode> getCompanyList() throws  Exception{
       List<TreeNode> res=new ArrayList<>();
       Map<Integer,String> TypeHash= HashMapUtils.of(1,"企业",2,"机关",3,"院校",4,"个人",5,"其他");
       List<tbCompany> companys=companyRepository.findAll();
       List<Integer> Types=companys.stream().mapToInt(f->f.getType()).boxed().distinct().collect(Collectors.toList());
       Types.forEach(f->{
           String typeName=TypeHash.get(f);
           TreeNode pNode=new TreeNode();
           String pId="P_"+Integer.toString(f);
           pNode.setFID(pId);
           pNode.setName(typeName);
           pNode.setPID("0");
           res.add(pNode);
           List<tbCompany> cs=companys.stream().filter(x->x.getType().equals(f)).collect(Collectors.toList());
           cs.forEach(y->{
               TreeNode node=new TreeNode();
               node.setPID(pId);
               node.setFID(Integer.toString(y.getId()));
               node.setName(y.getName());
               res.add(node);
           });
       });
       return res;
    }

    @Transactional
    public void SaveDep(int CompanyID, int CreteMan){
        tbDepList depList = new tbDepList();
        Optional<tbDepList> findOne = depListRepository.findByCompanyId(CompanyID);
        if (!findOne.isPresent()){
            depList.setPid(0);
            depList.setName("办公室");
            depList.setCompanyId(CompanyID);
            depList.setCanUse(true);
            depList.setCreateMan(CreteMan);
            depList.setCreateTime(new Date());
            depList.setSort("000001");
            depListRepository.save(depList);
        }
    }
}
