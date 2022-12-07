package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.SuperUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.IApplyListService;
import com.zhide.govwatch.mapper.ApplyListMapper;
import com.zhide.govwatch.mapper.FeeItemMemoMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.sqlParameter;
import com.zhide.govwatch.model.view_patentMemo;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApplyListServiceImpl implements IApplyListService {
    @Autowired
    ApplyListMapper newMapper;
    @Autowired
    FeeItemMemoMapper infoMemoMapper;

    @Override
    public pageObject clientFYJK(Map<String, Object> parameters) {
        pageObject object = new pageObject();
        List<Map<String, Object>> datas = newMapper.clientFYJK(parameters);
        int Total = 0;
        List<Map<String, Object>> PP = new ArrayList<>();
        if (datas.size() > 0) {
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            List<String> SIDS = datas.stream().map(f -> f.get("id").toString()).collect(Collectors.toList());
            List<view_patentMemo> memos = infoMemoMapper.getAllByIds("Apply", SIDS);
            datas.stream().forEach(f -> {
                Map<String, Object> row = eachSingleRow(f, memos);
                PP.add(f);
            });
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
    }

    private Map<String, Object> eachSingleRow(
            Map<String, Object> row,
            List<view_patentMemo> memos) {
        row.remove("_TotalNum");
        String SHENQINGH = row.get("id").toString();
        String NEIBUBH = SuperUtils.toString(row.get("NEIBUBH"));
        PantentInfoMemoCreator creator = new PantentInfoMemoCreator(memos);
        List<String> words = creator.Build(SHENQINGH);
        row.put("EDITMEMO", words.size() > 0 ? 1 : 0);
        if (words.size() > 0) {
            row.put("MEMO", String.join("<br/><br/>", words));
        } else {
            row.put("MEMO", "");
        }
        return row;
    }

    @Override
    public Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "QX";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        String queryText = request.getParameter("Query");
        String queryTexts = request.getParameter("Querys");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        if (Strings.isNotEmpty(queryTexts)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("ApplyItems", OrItems);
        } else params.put("ApplyItems", new ArrayList<>());
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
        return params;
    }
}
