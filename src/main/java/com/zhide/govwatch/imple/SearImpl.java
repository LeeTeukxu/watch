package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.ISearService;
import com.zhide.govwatch.define.ItbGovFeeService;
import com.zhide.govwatch.mapper.SearMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.patentElInfo;
import com.zhide.govwatch.model.patentInfoPermission;
import com.zhide.govwatch.model.sqlParameter;
import com.zhide.govwatch.repository.patentElInfoRepository;
import com.zhide.govwatch.repository.patentInfoPermissionRepository;
import com.zhide.govwatch.repository.tbGovFeeRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearImpl implements ISearService {
    @Autowired
    SearMapper searMapper;

    @Autowired
    patentElInfoRepository patentElInfoRepository;

    @Autowired
    patentInfoPermissionRepository patentInfoPermissionRepository;

    @Override
    public pageObject GetTopMonitor(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = searMapper.GetAllMonitor(params);
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
    public pageObject GetAllMonitor(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = searMapper.GetAllMonitor(params);
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
    public pageObject GetByShenqinghsIn(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        String word = request.getParameter("word");
        List<String> listWord = new ArrayList<>();
        if (word.length() > 0){
            String[] words = word.split(",");
            for (int i=0;i<words.length;i++){
                listWord.add(words[i]);
            }
        }
        List<patentElInfo> listPate = patentElInfoRepository.findAllByShenqinghIn(listWord);
        List<Map<String,Object>> datas = new ArrayList<>();
        if (listPate.size() > 0){
            for (patentElInfo patentElInfo : listPate){
                Map<String,Object> row = transBean2Map(patentElInfo);
                row.put("_TotalNum",listPate.size());
                datas.add(row);
            }
        }
        List<patentInfoPermission> listPat = patentInfoPermissionRepository.findAll();
        int Total = 0;
        List<Map<String,Object>> PP = new ArrayList<>();
        if (datas.size() > 0){
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            datas.stream().forEach(f -> {
                Map<String, Object> row = eachSingleAnnualSearchRow(f, listPat);
                PP.add(row);
            });
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
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

    private Map<String, Object> eachSingleAnnualSearchRow(
            Map<String, Object> row, List<patentInfoPermission> listPat){
        row.remove("_TotalNum");
        String SHENQINGH = row.get("shenqingh").toString();
        if (listPat.size() > 0){
            Optional<patentInfoPermission> findOne = listPat.stream().filter(f -> f.getShenqingh().equals(SHENQINGH)).findFirst();
            if (findOne.isPresent()){
                row.put("jkstatus", "已监控");
            } else row.put("jkstatus", "未监控");
        }else row.put("jkstatus", "未监控");
        return row;
    }

    public static Map<String, Object> transBean2Map(Object obj){

        if (obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors){
                String key = property.getName();

                if (!key.equals("class")){
                    Method getter= property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key,value);
                }
            }
        }catch (Exception ax){
            System.out.println("transBean2Map Error " + ax);
        }
        return map;
    }
}
