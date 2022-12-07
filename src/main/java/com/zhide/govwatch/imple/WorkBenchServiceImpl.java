package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.IWorkBenchService;
import com.zhide.govwatch.mapper.WorkBenchMapper;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.sqlParameter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName: WorkBenchServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年02月28日 10:26
 **/
@Service
public class WorkBenchServiceImpl implements IWorkBenchService {
    @Autowired
    WorkBenchMapper workBenchMapper;
    @Override
    public List<Map<String, Object>> getAddFee(HttpServletRequest request) throws Exception {
        Map<String, Object> arguments = getParams(request);
        List<Map<String, Object>> datas = workBenchMapper.getAddFee(arguments);
        return datas;
    }

    @Override
    public List<Map<String, Object>> getPatent(HttpServletRequest request) throws Exception {
        Map<String, Object> arguments = getParams(request);
        List<Map<String, Object>> datas = workBenchMapper.getPatent(arguments);
        return datas;
    }

    @Override
    public List<Map<String, Object>> getRecentFee(HttpServletRequest request) throws Exception {
        Map<String, Object> arguments = getParams(request);
        List<Map<String, Object>> datas = workBenchMapper.getRecentFee(arguments);
        return datas;
    }

    private Map<String, Object> getParams(HttpServletRequest request) throws Exception {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (StringUtils.isEmpty(sortOrder)) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (StringUtils.isEmpty(sortField)) sortField = "TUploadTime";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        String Date = request.getParameter("Date");
        if (StringUtils.isEmpty(Date)) Date = "Now";
        if (Date.equals("Now")) {
            params.put("Date", simple.format(new Date()));
        } else if (Date.equals("Pre")) {
            Date T = new Date();
            Date Pre = DateUtils.addDays(T, -1);
            params.put("Date", simple.format(Pre));
        }

        String minDays= request.getParameter("minDays");
        if(StringUtils.isEmpty(minDays)==false){
            params.put("minDays",minDays);
        }
        String maxDays=request.getParameter("maxDays");
        if(StringUtils.isEmpty(maxDays)==false){
            params.put("maxDays",maxDays);
        }
        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            queryText = URLDecoder.decode(queryText, "utf-8");
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }
}
