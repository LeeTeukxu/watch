package com.zhide.govwatch.imple;

import com.alibaba.excel.event.Order;
import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.*;
import com.zhide.govwatch.define.IWeChatOrderService;
import com.zhide.govwatch.mapper.OrderDetailsMapper;
import com.zhide.govwatch.mapper.OrderListMapper;
import com.zhide.govwatch.mapper.tbGovFeeMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.*;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.weaver.ast.And;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeChatOrderServiceImpl implements IWeChatOrderService {
    @Autowired
    tbOrderListRepository orderListRepository;
    @Autowired
    tbOrderDetailRepository orderDetailRepository;
    @Autowired
    OrderListMapper orderListMapper;
    @Autowired
    OrderDetailsMapper orderDetailsMapper;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    tbGovFeeRepository govFeeRepository;
    @Autowired
    tbgovpayRepository govpayRepository;
    @Autowired
    PatentRepository patentRepository;
    @Autowired
    tbGovFeeMapper govFeeMapper;
    @Autowired
    view_OrderDetailsRepository orderDetailsRepository;
    @Autowired
    YearFeeBaseRepository yearFeeBaseRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    private static Object lock = new Object();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public tbOrderList Save(String SHENQINGHS, String Amounts, String FEENAMES, String JIAOFEIRS) throws Exception {
        LoginUserInfo Info = CompanyContext.get();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String OrderNo = sdf.format(date);
//        String OrderNo = UUID.randomUUID().toString().replaceAll("-","");
        float Amount = 0;
        List<String> listAmount = new ArrayList<>();
        String[] amos = Amounts.split(",");
        for (int i=0;i<amos.length;i++){
            listAmount.add(amos[i]);
        }
        for (int i=0;i<listAmount.size();i++){
            Amount += Float.parseFloat(listAmount.get(i));
        }
        tbOrderList orderList = new tbOrderList();
        orderList.setOrderNo(OrderNo);
        orderList.setOrderTime(new Date());
        orderList.setAmount(Amount);
        orderList.setServiceCharge(listAmount.size() * 100);
        orderList.setOrderAmountTotal(orderList.getAmount() + orderList.getServiceCharge());
        orderList.setPayState(1); //初始化订单支付状态为待支付
        orderList.setDjState(1); //初始化订单缴费状态为待缴费
//        orderList.setUserId(Info.getUserId());
        orderListRepository.save(orderList);

        String[] SHENQINGH = SHENQINGHS.split(",");
        String[] FEENAME = FEENAMES.split(",");
        String[] JIAOFEIR = JIAOFEIRS.split(",");
        List<tbOrderDetail> listOrderDetail = new ArrayList<>();
        for (int i=0;i<SHENQINGH.length;i++){
            String JIAOFEI = DateTimeUtils.convert(JIAOFEIR[i]);
            Optional<view_govfee> findGovFee = govFeeMapper.getGovFeeBySHENQINGHMONEYFEENAMEJIAOFEIR(SHENQINGH[i], amos[i], FEENAME[i],JIAOFEI);
            view_govfee govFee = new view_govfee();
            tbgovpay govPay = new tbgovpay();
            if (findGovFee.isPresent()){
                govFee = findGovFee.get();
            }
//            else{
//                Optional<tbgovpay> findGovPay = govpayRepository.findById(Integer.parseInt(ID[i]));
//                if (findGovPay.isPresent()){
//                    govPay = findGovPay.get();
//                }
//            }
            tbOrderDetail orderDetail = new tbOrderDetail();
            orderDetail.setOrderNo(OrderNo);
            if (govFee != null) {
                patent p = patentRepository.findAllByShenqingh(govFee.getSHENQINGH());
                orderDetail.setAppNo(govFee.getSHENQINGH());
                orderDetail.setCostName(govFee.getFEENAME());
                orderDetail.setAmount(govFee.getMoney());
                orderDetail.setLimitDate(govFee.getJiaofeir());
                orderDetail.setClientId(p.getClientId());
            }
//            else if (govPay != null){
//                patent p = patentRepository.findAllByShenqingh(govPay.getAppNo());
//                orderDetail.setAppNo(govPay.getAppNo());
//                orderDetail.setCostName(govPay.getCostName());
//                orderDetail.setAmount(govPay.getAmount());
//                orderDetail.setLimitDate(govPay.getLimitDate());
//                orderDetail.setClientId(p.getClientId());
//            }
            orderDetail.setUserId(Info.getUserId());
            listOrderDetail.add(orderDetail);
        }
        if (listOrderDetail.size() > 0) {
            orderDetailRepository.saveAll(listOrderDetail);
        }
        return orderList;
    }

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception  {
        pageObject object=new pageObject();
        Map<String,Object> Parameters=getParameters(request);
        List<Map<String,Object>> datas=orderListMapper.getData(Parameters);
        int Total = 0;
        List<Map<String, Object>> PP = new ArrayList<>();
        if(datas.size()>0){
            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
            List<String> OrderNos = datas.stream().map(f -> f.get("OrderNo").toString()).collect(Collectors.toList());
            List<view_OrderDetails> details = orderDetailsRepository.findAllByOrderNoIn(OrderNos);
            List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
            String SHENQINGLX = "";
            String FEENAME = "";
            for (Map<String, Object> map : datas) {
                float AfterPriceTotal = 0;
                List<String> listDetails = new ArrayList<>();
                List<Map<String, Object>> listMap = new ArrayList<>();
                List<tbOrderDetail> view_orderDetails = orderDetailRepository.getAllByOrderNo(map.get("OrderNo").toString());
                for (tbOrderDetail orderDetail : view_orderDetails) {
                    List<govFeeWaitMongo> wait = getGovFeeWait(orderDetail.getAppNo(), orderDetail.getCostName());
                    if (wait.size() > 0) {
                        for (govFeeWaitMongo mongo : wait) {
                            SHENQINGLX = mongo.getSHENQINGLX();
                            FEENAME = mongo.getFEENAME();
                        }
                    }else {
                        List<govFeeWaitMongo> pay = getGovFeePay(orderDetail.getAppNo(), orderDetail.getCostName());
                        if (pay.size() > 0) {
                            for (govFeeWaitMongo mongo : pay) {
                                SHENQINGLX = mongo.getSHENQINGLX();
                                FEENAME = mongo.getFEENAME();
                            }
                        }
                    }
                    AfterPriceTotal += getAfterPriceTotal(yearFeeBases, SHENQINGLX, FEENAME, orderDetail.getAmount());
                    Map<String, Object> maps = new HashMap<>();
                    patent p = patentRepository.findAllByShenqingh(orderDetail.getAppNo());
                    maps.put("FAMINGMC", p.getFamingmc());
                    maps.put("SHENQINGLX", SHENQINGLX);
                    maps.put("FEENAME", FEENAME);
                    maps.put("Amount", orderDetail.getAmount());
                    listMap.add(maps);
                }
                listDetails = getListDetail(yearFeeBases, listMap);
                Map<String, Object> row = eachSingleRow(map, details, AfterPriceTotal, listDetails);
                PP.add(row);
            }
            object.setTotal(Total);
            object.setData(datas);
        }
        return object;
    }

    @Override
    public List<Map<String, Object>> getBasics(HttpServletRequest request) throws Exception {
        Map<String, Object> Parameters = setParameters(request);
        List<Map<String, Object>> datas = orderListMapper.getBasicsData(Parameters);
        return datas;
    }

    private Map<String, Object> eachSingleRow(
            Map<String, Object> row,
            List<view_OrderDetails> details, float AfterPriceTotal, List<String> listDetail) {
        row.remove("_TotalNum");
        String OrderNo = row.get("OrderNo").toString();
        if (details.size() > 0) {
            OrderDetailsCreator creator = new OrderDetailsCreator(details);
            String famingmcs = creator.Build(OrderNo, "FAMINGMC");
            if (famingmcs.length() > 0) {
                row.put("AllFMMC", famingmcs);
            }
            String feenames = creator.Build(OrderNo, "FEENAME");
            if (feenames.length() > 0) {
                row.put("AllFEENAME", feenames);
            }
            String clientnames = creator.Build(OrderNo, "CLIENTNAME");
            if (clientnames.length() > 0) {
                row.put("AllCLIENTNAME", clientnames);
            }
        }
        row.put("AfterPriceTotal", AfterPriceTotal);
        if (listDetail.size() > 0) {
            row.put("Depict", String.join("<br/><br/>", listDetail));
        } else {
            row.put("Depict", "");
        }
        return row;
    }

    private float getAfterPriceTotal(List<YearFeeBase> yearFeeBases, String SHENQINGLX, String FEENAME, float Amount) {
        float AfterPriceTotal = 0;
        if (yearFeeBases.size() > 0) {
            YearFeeBaseCreator yearFeeBaseCreator = new YearFeeBaseCreator(yearFeeBases);
            List<Integer> FEEPRICE = yearFeeBaseCreator.Build(SHENQINGLX, FEENAME);
            if (FEEPRICE.size() > 0) {
                AfterPriceTotal = Integer.parseInt(FEEPRICE.get(0).toString()) - Amount;
            }
        }
        return AfterPriceTotal;
    }

    public List<String> getListDetail(List<YearFeeBase> yearFeeBases, List<Map<String, Object>> listMap) {
        List<String> SS = new ArrayList<>();
        if (yearFeeBases.size() > 0) {
            YearFeeBaseCreator yearFeeBaseCreator = new YearFeeBaseCreator(yearFeeBases);
            if (listMap.size() > 0) {
                Integer Num = 0;
                for (int i=0; i < listMap.size(); i++) {
                    Map<String, Object> map = listMap.get(i);
                    List<Integer> FEEPRICE = yearFeeBaseCreator.Build(map.get("SHENQINGLX").toString(), map.get("FEENAME").toString());
                    if (FEEPRICE.size() > 0) {
                        Num++;
                        String Part = "%s，%s，费减前金额：%s，费减后金额：%s，节省金额：%s";
                        String All = Num + "、" + String.format(Part, map.get("FAMINGMC").toString(), map.get("FEENAME").toString(), FEEPRICE.get(0), map.get("Amount").toString(), FEEPRICE.get(0) - Float.valueOf(map.get("Amount").toString()));
                        SS.add(All);
                    }
                }
            }
        }
        return SS;
    }

    @Override
    public pageObject getOrderDetailsData(String OrderNo, HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> params = getOrderDetailParameters(OrderNo, request);

        List<view_OrderDetails> datas=orderDetailsMapper.getData(params);
        List<view_OrderDetails> PP = new ArrayList<>();
        if(datas.size()>0){
            List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
            datas.stream().forEach(f -> {
                StringBuilder SHENQINGLX = new StringBuilder("");
                StringBuilder FEENAME = new StringBuilder("");
                List<govFeeWaitMongo> wait = getGovFeeWait(f.getAppNo(), f.getFEENAME());
                if (wait.size() > 0) {
                    wait.stream().forEach(x -> {
                        SHENQINGLX.append(x.getSHENQINGLX());
                        FEENAME.append(x.getFEENAME());
                    });
                }else {
                    List<govFeeWaitMongo> pay = getGovFeePay(f.getAppNo(), f.getFEENAME());
                    if (pay.size() > 0) {
                        pay.stream().forEach(x -> {
                            SHENQINGLX.append(x.getSHENQINGLX());
                            FEENAME.append(x.getFEENAME());
                        });
                    }
                }
                view_OrderDetails orderDetails = eachOrderDetailsSingle(f, SHENQINGLX.toString(), FEENAME.toString(), yearFeeBases);
                PP.add(orderDetails);
            });
            object.setData(PP);
        }
        return object;
    }

    @Override
    public pageObject getPaymentOrderDetailsData(String OrderNo, HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
        Map<String, Object> params = new HashMap<>();
        params.put("OrderNo", OrderNo);
        params.put("sortOrder", "DESC");
        params.put("sortField", "LimitDate");

        List<view_OrderDetails> datas = orderDetailsMapper.getData(params);
        List<view_OrderDetails> PP = new ArrayList<>();
        if (datas.size() > 0){
            List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
            if (datas.size() > 0) {
                datas.stream().forEach(f -> {
                    StringBuilder SHENQINGLX = new StringBuilder("");
                    StringBuilder FEENAME = new StringBuilder("");
                    List<govFeeWaitMongo> wait = getGovFeeWait(f.getAppNo(), f.getFEENAME());
                    if (wait.size() > 0) {
                        wait.stream().forEach(x -> {
                            SHENQINGLX.append(x.getSHENQINGLX());
                            FEENAME.append(x.getFEENAME());
                        });
                    }else {
                        List<govFeeWaitMongo> pay = getGovFeePay(f.getAppNo(), f.getFEENAME());
                        if (pay.size() > 0) {
                            pay.stream().forEach(x -> {
                                SHENQINGLX.append(x.getSHENQINGLX());
                                FEENAME.append(x.getFEENAME());
                            });
                        }
                    }
                    view_OrderDetails tail = eachOrderDetailsSingle(f, SHENQINGLX.toString(), FEENAME.toString(), yearFeeBases);
                    PP.add(tail);
                });
            }
            object.setData(PP);
        }
        return object;
    }

    private List<govFeeWaitMongo> getGovFeeWait(String SHENQINGH, String FEENAME) {
        String CollectionName = "govwait";
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("SHENQINGH").is(SHENQINGH),
                             Criteria.where("FEENAME").is(FEENAME));
        query.addCriteria(criteria);
        List<govFeeWaitMongo> datas = mongoTemplate.find(query, govFeeWaitMongo.class, CollectionName);
        return datas;
    }

    private List<govFeeWaitMongo> getGovFeePay(String SHENQINGLH, String FEENAME) {
        String CollectionName = "govpay";
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("SHENQINGH").is(SHENQINGLH),
                             Criteria.where("FEENAME").is(FEENAME));
        query.addCriteria(criteria);
        List<govFeeWaitMongo> datas = mongoTemplate.find(query, govFeeWaitMongo.class, CollectionName);
        return datas;
    }

    private view_OrderDetails eachOrderDetailsSingle(view_OrderDetails row, String SHENQINGLX, String FEENAME, List<YearFeeBase> yearFeeBases) {
        if (yearFeeBases.size() > 0) {
            YearFeeBaseCreator yearFeeBaseCreator = new YearFeeBaseCreator(yearFeeBases);
            List<Integer> FEEPRICE = yearFeeBaseCreator.Build(SHENQINGLX, FEENAME);
            if (FEEPRICE.size() > 0) {
                row.setFEEPRICE(FEEPRICE.get(0).toString());
                row.setAFTERPRICE(Integer.parseInt(FEEPRICE.get(0).toString()) - row.getAmount());
            }else {
                row.setFEEPRICE("");
                row.setAFTERPRICE(0);
            }
        }
        return row;
    }

    private Map<String, Object> getParameters(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "PayState";
        String ListType = request.getParameter("ListType");
        if (ListType.isEmpty()) ListType = "ALL";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        params.put("ListType", ListType);
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

    private Map<String, Object> setParameters(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();
        LoginUserInfo Info = CompanyContext.get();
        params.put("RoleName", Info.getRoleName());
        params.put("UserID", Info.getUserId());
        String queryText = request.getParameter("Query");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        }else params.put("orItems", new ArrayList<>());

        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        }else params.put("andItems", new ArrayList<>());
        return params;
    }

    private Map<String, Object> getOrderDetailParameters(String OrderNo, HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageIndex * pageSize + 1);
        params.put("End", (pageIndex + 1) * pageSize);
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        params.put("OrderNo", OrderNo);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void UpdateDjStateAndPayState(String OrderNo, tbOrderList orderList) throws Exception {
        Optional<tbOrderList> findList = orderListRepository.findAllByOrderNo(OrderNo);
        if (findList.isPresent()){
            tbOrderList listOrder = findList.get();
            listOrder.setDjState(orderList.getDjState());
            listOrder.setPayState(orderList.getPayState());
            listOrder.setDevice_Info(orderList.getDevice_Info());
            listOrder.setResult_Code(orderList.getResult_Code());
            listOrder.setOpenId(orderList.getOpenId());
            listOrder.setBank_Type(orderList.getBank_Type());
            listOrder.setTime_End(orderList.getTime_End());
            listOrder.setErr_Code_Des(orderList.getErr_Code_Des());
            listOrder.setTransaction_Id(orderList.getTransaction_Id());
            orderListRepository.save(listOrder);
        }
    }

    @Override
    public tbOrderList GetOrderPayState(String OrderNo) throws Exception {
        tbOrderList orderList = new tbOrderList();
        if (redisTemplate.opsForValue().get(OrderNo).equals("2")){
            orderList.setPayState(2);
        }
        return orderList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ComplateDJ(List<Integer> IDS) throws Exception {
        try {
            LoginUserInfo Info = CompanyContext.get();
            List<tbOrderList> ListOrder = orderListRepository.findAllByOrderListIdIn(IDS);
            List<tbOrderList> OrderList = new ArrayList<>();
            List<String> listOrderNo = new ArrayList<>();
//            if (ListOrder.size() > 0) {
//                for (int i = 0; i < ListOrder.size(); i++) {
//                    tbOrderList orderList = new tbOrderList();
//                    orderList = ListOrder.get(i);
//                    orderList.setDjState(3);
//                    orderList.setUserId(Info.getUserId());
//                    OrderList.add(orderList);
//                }
//            }
            ListOrder.stream().forEach(f -> {
                f.setDjState(3);
                f.setUserId(Info.getUserId());
                OrderList.add(f);
                listOrderNo.add(f.getOrderNo());
            });
            orderListRepository.saveAll(OrderList);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean Remove(List<Integer> IDS) throws Exception {
        List<tbOrderList> ListOrder = orderListRepository.findAllByOrderListIdIn(IDS);
        List<String> ListOrderNo = new ArrayList<>();
        for (tbOrderList OrderList : ListOrder){
            ListOrderNo.add(OrderList.getOrderNo());
        }
        orderListRepository.deleteAll(ListOrder);
        orderDetailRepository.deleteAllByOrderNoIn(ListOrderNo);
        return true;
    }

    @Override
    public List<Map<String, Object>> getStatistic(HttpServletRequest request) throws Exception {
        Map<String, Object> Param = getStatisticParameters(request);
        List<Map<String, Object>> result = orderListMapper.getStatistic(Param);
        return result;
    }

    private Map<String, Object> getStatisticParameters(HttpServletRequest request) throws Exception {
        Map<String, Object> params = new HashMap<>();

        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            AndItems.remove(0);
            for (int i=0;i<AndItems.size();i++) {
                if (AndItems.get(i).getField().indexOf("Time") > -1 && AndItems.get(i).getOper().equals("<=")) {
                    AndItems.get(i).setValue(AndItems.get(i).getValue().replace("00:00:00","23:59:59"));
                }
            }
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String ResetOrderNo(String OrderNo) throws Exception {
        String No = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        No = sdf.format(date);

        List<tbOrderList> lists = new ArrayList<>();
        List<tbOrderDetail> details = new ArrayList<>();

        List<tbOrderList> listOrderList = orderListRepository.getAllByOrderNo(OrderNo);
        for (tbOrderList list : listOrderList) {
            list.setOrderNo(No);
            lists.add(list);
        }
        List<tbOrderDetail> listOrderDetail = orderDetailRepository.getAllByOrderNo(OrderNo);
        for (tbOrderDetail detail : listOrderDetail) {
            detail.setOrderNo(No);
            details.add(detail);
        }

        orderListRepository.saveAll(lists);
        orderDetailRepository.saveAll(details);
        return No;
    }

    @Override
    public float GetAfterPriceTotal(view_OrderDetails view_orderDetails, String SHENQINGH, String FEENAME) {
        StringBuilder SHENQINGLXS = new StringBuilder("");
        StringBuilder FEENAMES = new StringBuilder("");
        List<YearFeeBase> yearFeeBases = yearFeeBaseRepository.findAll();
        List<govFeeWaitMongo> wait = getGovFeeWait(SHENQINGH, FEENAME);
        if (wait.size() > 0) {
            wait.stream().forEach(x -> {
                SHENQINGLXS.append(x.getSHENQINGLX());
                FEENAMES.append(x.getFEENAME());
            });
        }else {
            List<govFeeWaitMongo> pay = getGovFeePay(SHENQINGH, FEENAME);
            if (pay.size() > 0) {
                pay.stream().forEach(x -> {
                    SHENQINGLXS.append(x.getSHENQINGLX());
                    FEENAMES.append(x.getFEENAME());
                });
            }
        }
        view_OrderDetails tail = eachOrderDetailsSingle(view_orderDetails, SHENQINGLXS.toString(), FEENAMES.toString(), yearFeeBases);
        return tail.getAFTERPRICE();
    }
}
