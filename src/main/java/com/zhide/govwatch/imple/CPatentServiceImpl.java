package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.ICpatentService;
import com.zhide.govwatch.mapper.*;
import com.zhide.govwatch.model.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class CPatentServiceImpl implements ICpatentService {
    @Autowired
    CPatentMapper cPatentMapper;

    @Override
    public pageObject GetData(Map<String, Object> parameters) {
        pageObject object = new pageObject();
        List<Map<String, Object>> datas = cPatentMapper.GetData(parameters);
        int Total = 0;
        List<Map<String, Object>> PP = new ArrayList<>();
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            List<String> SIDS = datas.stream().map(f -> f.get("SHENQINGH").toString()).collect(toList());
            datas.stream().forEach(f -> {
                Map<String, Object> row = eachSingleRow(f,new ArrayList<>());
                PP.add(f);
            });
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
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
        params.put("shenqinghs",shenqinghs);
        if (Info.getRoleName().equals("市级管理员")){
            params.put("CityName",Info.getCityName());
        }
        return params;
    }

    private Map<String, Object> eachSingleRow(
            Map<String, Object> row, List<patentInfoPermission> listPat) {
        row.remove("_TotalNum");
        String SHENQINGH = row.get("SHENQINGH").toString();
        listPat.stream().forEach(f ->{
            if (f.getShenqingh().equals(SHENQINGH)){
                row.put("JKStatus","已监控");
            }else row.put("JKStatus","未监控");
        });
        return row;
    }
}
