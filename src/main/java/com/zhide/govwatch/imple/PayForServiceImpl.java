package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.config.CacheableTtl;
import com.zhide.govwatch.define.IPayForService;
import com.zhide.govwatch.mapper.GovMemoMapper;
import com.zhide.govwatch.mapper.GovPayMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.YearFeeBaseRepository;
import com.zhide.govwatch.repository.patentareaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: PayForServiceImpl
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月14日 14:02
 **/
@Service
public class PayForServiceImpl implements IPayForService {

    @Autowired
   GovPayMapper govMapper;
    @Autowired
    GovMemoMapper memoMapper;
    @Autowired
    patentareaRepository patentareaRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    GovFeeUtils govFeeUtils;
    @Autowired
    YearFeeBaseRepository yearFeeBaseRepository;
    @CacheableTtl(value = "getGovPayCount",ttl=600)
    public int getCount(String RoleName,Integer UserID) throws Exception {
        LoginUserInfo Info=CompanyContext.get();
        String CollectionName = "govpay";
        Query query = new Query();
        List<String> States= Arrays.asList("已缴费","费足额");
        Criteria criteria = Criteria.where("PayState").in(States);
        query.addCriteria(criteria);

        if (!Info.getRoleId().equals(2)) {
            List<patentarea> listPatentarea = patentareaRepository.findAllByUserId(Info.getUserId());
            List<Integer> listAreaID=listPatentarea.stream().map(f->f.getAreaId()).distinct().collect(Collectors.toList());
            if (listAreaID.size() > 0) {
                criteria.andOperator(Criteria.where("ProvinceID").in(listAreaID).orOperator(Criteria.where("CityID").in(listAreaID)).orOperator(Criteria.where("CountyID").in(listAreaID)));
            }
        }
        long Count = mongoTemplate.count(query,govFeeWaitMongo.class,CollectionName);
        return Math.toIntExact(Count);
    }


    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject object=getDataForMongo(request);
        List<govFeeWaitMongo> datas=(List<govFeeWaitMongo>) object.getData();
        List<govFeeWaitMongo> PP = new ArrayList<>();
        if(datas.size()>0){
            List<String> SHENQINGH =
                    datas.stream().distinct().map(f -> f.getSHENQINGH()).distinct().collect(Collectors.toList());
            List<String> FEENAME =
                    datas.stream().map(f -> f.getFEENAME()).distinct().collect(Collectors.toList());
            List<v_GovFeeInfoMemo> memos = memoMapper.getAllBySHENQINGHAndFEENAME(SHENQINGH, FEENAME);
            List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
            for(int i=0;i<datas.size();i++) {
                govFeeWaitMongo row=datas.get(i);
                govFeeWaitMongo newRow = eachSingleRow(row, memos,yearFeeBases);
                PP.add(newRow);
            }
            object.setData(PP);
        }
        return object;
    }

    private pageObject getDataForMongo(HttpServletRequest request) throws Exception {
        pageObject object=new pageObject();
        String CollectionName = "govpay";
        Query query = new Query();
        Criteria criteria =new Criteria();
        govFeeUtils.generatorCriteria(criteria, request, govFeeWaitMongo.class);
        query.addCriteria(criteria);
        Long total=mongoTemplate.count(query,CollectionName);

        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        Pageable pageable=Pageable.ofSize(pageSize).withPage(pageIndex);
        query.with(pageable);
        String sortField= request.getParameter("sortField");
        if(StringUtils.isEmpty(sortField)==false){
            String sortOrder= request.getParameter("sortOrder");
            if(StringUtils.isEmpty(sortOrder))sortOrder="asc";
            sortOrder=sortOrder.toLowerCase();
            Sort sort=Sort.by(Sort.Direction.fromString(sortOrder),sortField);
            query.with(sort);
        }

        List<govFeeWaitMongo> datas = mongoTemplate.find(query,govFeeWaitMongo.class,CollectionName);
        object.setTotal(total);
        object.setData(datas);
        return object;
    }

    private static List<Map<String, Object>> entityToMap(List<govFeeWaitMongo> list, long Count) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (govFeeWaitMongo mongo : list) {
            Object object = mongo;
            Map<String, Object> map = new HashMap<>();
            for (Field field : object.getClass().getDeclaredFields()) {
                try {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object o = field.get(object);
                    map.put(field.getName(), o);
                    map.put("_TotalNum",Count);
                    field.setAccessible(flag);
                } catch (Exception ax) {
                    ax.printStackTrace();
                }
            }
            listMap.add(map);
        }
        return listMap;
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
        params.put("State",State);
        params.put("Code",Code);
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
            params.put("RoleID",Info.getRoleId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");
        return params;
    }
    private Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "JIAOFEIR";
        String State = request.getParameter("State");
        if (State == null || State.isEmpty()) State = "";
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
        String AppNo=request.getParameter("AppNo");
        if(StringUtils.isEmpty(AppNo)==false){
            params.put("AppNo",AppNo);
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
    private govFeeWaitMongo eachSingleRow(
            govFeeWaitMongo row,
            List<v_GovFeeInfoMemo> memos, List<YearFeeBase> yearFeeBases) {
        String SHENQINGH = row.getSHENQINGH();
        String FEENAME = row.getFEENAME();
        String SHENQINGLX = row.getSHENQINGLX();
        GovMemoCreator creator =new GovMemoCreator(memos);
        List<String> words = creator.Build(SHENQINGH, FEENAME);
        row.setEDITMEMO(words.size() > 0 ? 1 : 0);
        if (words.size() > 0){
            row.setMEMO(String.join("<br/><br/>", words));
        }else row.setMEMO("");

        YearFeeBaseCreator yearFeeBaseCreator = new YearFeeBaseCreator(yearFeeBases);
        List<Integer> FEEPRICE = yearFeeBaseCreator.Build(SHENQINGLX, FEENAME);
        if (FEEPRICE.size() > 0) {
            row.setFEEPRICE(FEEPRICE.get(0));
        }else row.setFEEPRICE(0);
        return row;
    }
}
