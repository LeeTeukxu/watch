package com.zhide.govwatch.imple;

import com.alibaba.fastjson.JSON;
import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.SuperUtils;
import com.zhide.govwatch.common.pageObject;
import com.zhide.govwatch.common.sqlParameterCreator;
import com.zhide.govwatch.define.IFeeListNewService;
import com.zhide.govwatch.mapper.FeeItemMapper;
import com.zhide.govwatch.mapper.FeeItemMemoMapper;
import com.zhide.govwatch.mapper.FeeListNewMapper;
import com.zhide.govwatch.model.*;
import com.zhide.govwatch.repository.yearFeeListRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeeListNewServiceImpl implements IFeeListNewService {

    @Autowired
    FeeItemMemoMapper infoMemoMapper;

    @Autowired
    FeeListNewMapper newMapper;

    @Autowired
    FeeItemMapper feeMapper;

    @Autowired
    yearFeeListRepository yearFeeListRep;

    @Override
    public pageObject getData(HttpServletRequest request) throws Exception {
        pageObject object = new pageObject();
//        Map<String, Object> params = getParams(request);
//        List<Map<String, Object>> datas = newMapper.getData(params);
//        int Total = 0;
//        List<Map<String, Object>> PP = new ArrayList<>();
//        if (datas.size() > 0) {
//            Total = Integer.parseInt(datas.get(0).get("_TotalNum").toString());
//            List<String> SIDS = datas.stream().map(f -> f.get("id").toString()).collect(Collectors.toList());
//            List<v_PantentInfoMemo> memos = infoMemoMapper.getAllByIds("Year", SIDS);
//            datas.stream().forEach(f -> {
//                Map<String, Object> row = eachSingleRow(f, memos);
//                PP.add(f);
//            });
//            object.setTotal(Total);
//            object.setData(PP);
//        }
        return object;
    }

    private Map<String, Object> getParams(HttpServletRequest request) throws Exception {
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder.isEmpty()) sortOrder = "Desc";
        String sortField = request.getParameter("sortField");
        if (sortField.isEmpty()) sortField = "SHENQINGR";
        Map<String, Object> params = new HashMap<>();
        params.put("Begin", pageSize * pageIndex + 1);
        params.put("End", pageSize * (pageIndex + 1));
        params.put("sortOrder", sortOrder);
        params.put("sortField", sortField);
        LoginUserInfo Info = CompanyContext.get();
        if (Info != null) {
            params.put("DepID", Info.getDepId());
            params.put("RoleName", Info.getRoleName());
            params.put("UserID", Info.getUserId());
        } else throw new RuntimeException("登录信息失效，请重新登录！");

        String queryText = request.getParameter("Query");
        String queryTexts = request.getParameter("Querys");
        if (Strings.isNotEmpty(queryText)) {
            List<sqlParameter> Vs = JSON.parseArray(queryText, sqlParameter.class);
            List<sqlParameter> OrItems = sqlParameterCreator.convert(Vs);
            params.put("orItems", OrItems);
        } else params.put("orItems", new ArrayList<>());
        if (queryText != null) {
            if (Strings.isNotEmpty(queryTexts)) {
                List<sqlParameter> Vs = JSON.parseArray(queryTexts, sqlParameter.class);
                List<sqlParameter> YearItems = sqlParameterCreator.convert(Vs);
                params.put("YearItems", YearItems);
            } else params.put("YearItems", new ArrayList<>());
        }
        String highText = request.getParameter("High");
        if (Strings.isNotEmpty(highText)) {
            List<sqlParameter> Ps = JSON.parseArray(highText, sqlParameter.class);
            List<sqlParameter> AndItems = sqlParameterCreator.convert(Ps);
            params.put("andItems", AndItems);
        } else params.put("andItems", new ArrayList<>());
        return params;
    }

//    private Map<String, Object> eachSingleRow(
//            Map<String, Object> row,
//            List<v_PantentInfoMemo> memos) {
//        row.remove("_TotalNum");
//        String SHENQINGH = row.get("id").toString();
//        String NEIBUBH = SuperUtils.toString(row.get("NEIBUBH"));
//        PantentInfoMemoCreator creator = new PantentInfoMemoCreator(memos);
//        List<String> words = creator.Build(SHENQINGH);
//        row.put("EDITMEMO", words.size() > 0 ? 1 : 0);
//        if (words.size() > 0) {
//            row.put("MEMO", String.join("<br/><br/>", words));
//        } else {
//            row.put("MEMO", "");
//        }
//        return row;
//    }

    @Override
    public List<ComboboxItem> getFeeItems() {
        List<ComboboxItem> items = new ArrayList<>();
        feeMapper.getFeeItems().stream().forEach(f -> {
            ComboboxItem b = new ComboboxItem();
            b.setText(f);
            b.setId(f);
            items.add(b);
        });
        return items;
    }

    @Override
    public List<ComboboxItem> getZLItems() {
        List<ComboboxItem> items = new ArrayList<>();
        feeMapper.getZLItems().stream().forEach(f -> {
            ComboboxItem b = new ComboboxItem();
            b.setText(f);
            b.setId(f);
            items.add(b);
        });
        return items;
    }

    @Override
    public boolean addPayForList(List<Integer> feeItems) {
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ChangeXMoney(List<Integer> IDS, double Money) {
        for (int i = 0; i < IDS.size(); i++) {
            Integer ID = IDS.get(i);
            Optional<yearFeeList> find = yearFeeListRep.findById(ID);
            if (find.isPresent()) {
                yearFeeList One = find.get();
                One.setXmoney(Money);
                yearFeeListRep.save(One);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ChangePayForStatus(List<Integer> IDS, String Status) {
        LoginUserInfo Info=CompanyContext.get();
        for (int i = 0; i < IDS.size(); i++) {
            Integer ID = IDS.get(i);
            Optional<yearFeeList> find = yearFeeListRep.findById(ID);
            if (find.isPresent()) {
                yearFeeList one = find.get();
                boolean OK = (Status.equals("1") ? true : false);
                one.setNeedPayFor(OK);
                if(OK==false){
                    one.setJkStatus(3);
                    one.setCancelMan(Info.getUserId());
                    one.setCancelTime(new Date());
                } else one.setJkStatus(0);
                one.setUpMan(Info.getUserId());
                one.setUpTime(new Date());
                yearFeeListRep.save(one);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ChangeSXMoney(List<Integer> IDS, double Money) {
        for (int i = 0; i < IDS.size(); i++) {
            Integer ID = IDS.get(i);
            Optional<yearFeeList> find = yearFeeListRep.findById(ID);
            if (find.isPresent()) {
                yearFeeList One = find.get();
                One.setSxmoney(Money);
                yearFeeListRep.save(One);
            }
        }
    }
}
