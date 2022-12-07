package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.config.CacheableTtl;
import com.zhide.govwatch.define.IGovFeeService;
import com.zhide.govwatch.define.IPayForService;
import com.zhide.govwatch.mapper.GovFeeMapper;
import com.zhide.govwatch.mapper.GovMemoMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.YearFeeBaseRepository;
import com.zhide.govwatch.repository.patentareaRepository;
import com.zhide.govwatch.repository.view_OrderDetailsRepository;
import com.zhide.govwatch.repository.view_orderlistRepository;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: GovFeeServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年02月09日 8:31
 **/
@Service
public class GovFeeServiceImpl implements IGovFeeService {
    @Autowired
    GovFeeMapper govMapper;
    @Autowired
    GovMemoMapper memoMapper;

    @Autowired
    IPayForService payService;
    @Autowired
    patentareaRepository areaRep;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    GovFeeUtils govFeeUtils;
    @Autowired
    view_OrderDetailsRepository orderDetailsRepository;
    @Autowired
    YearFeeBaseRepository yearFeeBaseRepository;

    Logger logger = LoggerFactory.getLogger(GovFeeServiceImpl.class);

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        String CollectionName = "govwait";
        Query query = new Query();
        Criteria criteria = new Criteria();
        govFeeUtils.generatorCriteria(criteria, request, govFeeWaitMongo.class);
        query.addCriteria(criteria);

        Long Total = mongoTemplate.count(query, CollectionName);

        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));

        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageIndex);
        query.with(pageable);
        String sortField = request.getParameter("sortField");
        if (StringUtils.isEmpty(sortField) == false) {
            String sortOrder = request.getParameter("sortOrder");
            if (StringUtils.isEmpty(sortOrder)) sortOrder = "asc";
            sortOrder = sortOrder.toLowerCase();
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
            query.with(sort);
        }
        logger.info("getData's Query:" + query.toString());
        List<govFeeWaitMongo> datas = mongoTemplate.find(query, govFeeWaitMongo.class, CollectionName);
        List<govFeeWaitMongo> PP = new ArrayList<>();
        if (datas.size() > 0) {
            List<String> SHENQINGH = datas.stream().map(f -> f.getSHENQINGH()).distinct().collect(Collectors.toList());
            List<String> FEENAME = datas.stream().map(f -> f.getFEENAME()).distinct().collect(Collectors.toList());
            List<v_GovFeeInfoMemo> memos = memoMapper.getAllBySHENQINGHAndFEENAME(SHENQINGH, FEENAME);
            List<view_OrderDetails> orderDetails = orderDetailsRepository.findAll();
            List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
            for (int i = 0; i < datas.size(); i++) {
                govFeeWaitMongo row = datas.get(i);
                govFeeWaitMongo newRow = eachSingleRow(row, memos, orderDetails, yearFeeBases);
                PP.add(newRow);
            }
            object.setTotal(Total);
            object.setData(PP);
        }
        return object;
    }

    @Override
    @CacheableTtl(value = "getGovWaitCount", ttl = 600)
    public List<Map<String, Object>> getGovCount(Integer RoleID, Integer UserID) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        String RoleName = Info.getRoleName();
        Map<String, Object> params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("RoleID", RoleID);
        List<Map<String, Object>> OO = govMapper.getGovCount(params);
        String CollectionName = "govpay";
        List<Criteria> listCriteria = new ArrayList<>();
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (RoleName.equals("企业用户") == false) {
            if (!RoleName.equals("系统管理员")) {
                List<patentarea> listPatentarea = areaRep.findAllByUserId(Info.getUserId());
                List<Integer> listAreaID =
                        listPatentarea.stream().map(f -> f.getAreaId()).distinct().collect(Collectors.toList());
                if (listAreaID.size() > 0) {
                    Criteria c1 = Criteria.where("GOVID").gt(0)
                            .orOperator(
                                    Criteria.where("PROVINCEID").in(listAreaID),
                                    Criteria.where("CITYID").in(listAreaID),
                                    Criteria.where("COUNTYID").in(listAreaID)
                            );
                    listCriteria.add(c1);
                }
            }
        } else {
            listCriteria.add(Criteria.where("CLIENTID").is(UserID));
        }
        if (listCriteria.size() > 0) criteria.andOperator(listCriteria);
        query.addCriteria(criteria);
        logger.info("getGovCount's Query:" + query.toString());
        long Count = mongoTemplate.count(query, govFeeWaitMongo.class, CollectionName);
        Map<String, Object> map = new HashMap<>();
        map.put("num", Count);
        map.put("name", "x5");
        OO.add(map);
        return OO;
    }

    private Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "JIAOFEIR";
        String State = request.getParameter("State");
        if (State == null || State.isEmpty()) State = "ALL";
        String Code = request.getParameter("Code");
        if (Code == null || Code.isEmpty()) Code = "";
        String ClientId = request.getParameter("clientId");
        if (ClientId == null || ClientId.isEmpty()) ClientId = "";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        params.put("State", State);
        params.put("Code", Code);
        params.put("ClientId", ClientId);
        String AppNo = request.getParameter("AppNo");
        if (StringUtils.isEmpty(AppNo) == false) {
            params.put("AppNo", AppNo);
        }
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
            params.put("RoleID", Info.getRoleId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    private Map<String, Object> getParameter(HttpServletRequest request) throws Exception {
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder == null || sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField == null || sortField.isEmpty()) sortField = "JIAOFEIR";
        String State = request.getParameter("State");
        if (State == null || State.isEmpty()) State = "";
        String Code = request.getParameter("Code");
        if (Code == null || Code.isEmpty()) Code = "";
        Map<String, Object> params = new HashMap<>();
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        params.put("State", State);
        params.put("Code", Code);
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
            params.put("RoleID", Info.getRoleId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }

    private govFeeWaitMongo eachSingleRow(
            govFeeWaitMongo row,
            List<v_GovFeeInfoMemo> memos, List<view_OrderDetails> orderDetails, List<YearFeeBase> yearFeeBases) {
        String SHENQINGH = row.getSHENQINGH();
        String FEENAME = row.getFEENAME();
        String SHENQINGLX = row.getSHENQINGLX();
        GovMemoCreator creator = new GovMemoCreator(memos);
        List<String> words = creator.Build(SHENQINGH, FEENAME);
        row.setEDITMEMO(words.size() > 0 ? 1 : 0);
        if (words.size() > 0) {
            row.setMEMO(String.join("<br/><br/>", words));
        } else row.setMEMO("");

        view_orderlistCreator orderDetailsCreator = new view_orderlistCreator(orderDetails);
        List<String> DjState = orderDetailsCreator.DjBuild(SHENQINGH, FEENAME);
        if (DjState.size() > 0) {
            row.setDJSTATE(String.join("<br/><br/>", DjState));
        }else row.setDJSTATE("");
        List<String> PState = orderDetailsCreator.PayBuild(SHENQINGH, FEENAME);
        if (PState.size() > 0) {
            row.setPSTATE(String.join("<br/><br/>", PState));
        }else row.setPSTATE("");

        YearFeeBaseCreator yearFeeBaseCreator = new YearFeeBaseCreator(yearFeeBases);
        List<Integer> FEEPRICE = yearFeeBaseCreator.Build(SHENQINGLX, FEENAME);
        if (FEEPRICE.size() > 0) {
            row.setFEEPRICE(FEEPRICE.get(0));
        }else row.setFEEPRICE(0);
        return row;
    }
}
