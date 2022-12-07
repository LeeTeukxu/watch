package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.iFunctionPermissionListService;
import com.zhide.govwatch.mapper.FunctionPermissionListMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.functionPermissionList;
import com.zhide.govwatch.model.sqlParameter;
import com.zhide.govwatch.repository.FunctionPermissionListRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;

@Service
public class FunctionPermissionListImpl implements iFunctionPermissionListService {

    @Autowired
    FunctionPermissionListMapper functionPermissionListMapper;

    @Autowired
    FunctionPermissionListRepository functionPermissionListRepository;

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject result = new pageObject();
        Map<String, Object> params = getParameters(request);
        List<Map<String, Object>> rows = functionPermissionListMapper.getData(params);
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
        if (sortField.isEmpty()) sortField = "FID";
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
    public boolean SaveAll(List<functionPermissionList> items) {
        for (int i = 0; i < items.size(); i++) {
            functionPermissionList item = items.get(i);
            String SN = functionPermissionListRepository.getMax().toString();
            item.setSn(SN);
            functionPermissionListRepository.save(item);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean Remove(Integer FID) {
        Optional<functionPermissionList> findOnes = functionPermissionListRepository.findFirstByFid(FID);
        if (findOnes.isPresent()) {
            functionPermissionListRepository.delete(findOnes.get());
            return true;
        } else return false;
    }
}
