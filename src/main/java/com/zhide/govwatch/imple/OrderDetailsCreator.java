package com.zhide.govwatch.imple;

import com.zhide.govwatch.model.view_OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsCreator {
    List<view_OrderDetails> rows;

    public OrderDetailsCreator(List<view_OrderDetails> ms) { rows = ms; }

    public String Build(String OrderNo, String file) {
        String VK = "";
        if (rows.size() == 0) return VK;
        for (int i = 0; i < rows.size(); i++) {
            view_OrderDetails row = rows.get(i);
            if (row.getOrderNo().equals(OrderNo)) {
                if (VK.indexOf(createSingle(row, file)) < 0) {
                    VK += createSingle(row, file) + ";";
                }
            }
        }
        if (!VK.equals("")) {
            VK = VK.substring(0, VK.length() - 1);
        }
        return VK;
    }

    private String createSingle(view_OrderDetails row, String file) {
        if (file.equals("FAMINGMC")) {
            return row.getFAMINGMC();
        }else if (file.equals("FEENAME")) {
            return row.getFEENAME();
        }else if (file.equals("CLIENTNAME")) {
            if (row.getClientName() != null) {
                return row.getClientName();
            }
        }
        return "";
    }
}
