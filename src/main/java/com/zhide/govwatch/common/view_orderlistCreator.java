package com.zhide.govwatch.common;

import com.zhide.govwatch.model.v_GovFeeInfoMemo;
import com.zhide.govwatch.model.view_OrderDetails;
import com.zhide.govwatch.model.view_orderlist;
import org.apache.logging.log4j.util.Strings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class view_orderlistCreator {
    List<view_OrderDetails> rows;

    public view_orderlistCreator(List<view_OrderDetails> ms) {
        rows = ms;
    }

    public List<String> DjBuild(String SHENQINGH, String FEENAME) {
        List<String> SS = new ArrayList<String>();
        if (rows.size()  == 0) return SS;
        for (int i = 0;i < rows.size();i++) {
            view_OrderDetails orderDetails = rows.get(i);
            if (orderDetails.getAppNo().equals(SHENQINGH) && orderDetails.getFEENAME().equals(FEENAME)) {
                String VK = createDjSingle(orderDetails.getDjState());
                SS.add(VK);
            }
        }
        return SS;
    }

    public List<String> PayBuild(String SHENQINGH, String FEENAME) {
        List<String> SS = new ArrayList<>();
        if (rows.size() == 0) return SS;
        for (int i = 0;i < rows.size();i++) {
            view_OrderDetails orderDetails = rows.get(i);
            if (orderDetails.getAppNo().equals(SHENQINGH) && orderDetails.getFEENAME().equals(FEENAME)) {
                System.out.println();
                String VK = createPaySingle(orderDetails.getPayState());
                SS.add(VK);
            }
        }
        return SS;
    }

    private String createDjSingle(Integer DjState) {
        String All = "";
        String Part = "%s";
        Map<String, Object> map = DjStateMap();
        All = String.format(Part, map.get(DjState.toString()));
        return All;
    }

    private String createPaySingle(Integer PayState) {
        String All = "";
        String Part = "%s";
        Map<String, Object> map = PayStateMap();
        All = String.format(Part, map.get(PayState.toString()));
        return All;
    }

    private Map<String, Object> PayStateMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "待支付");
        map.put("2", "支付成功");
        map.put("3", "支付失败");
        return map;
    }

    private Map<String, Object> DjStateMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "未支付");
        map.put("2", "代缴中");
        map.put("3", "代缴完成");
        return map;
    }
}
