package com.zhide.govwatch.common;

import com.zhide.govwatch.model.YearFeeBase;

import java.util.ArrayList;
import java.util.List;

public class YearFeeBaseCreator {
    List<YearFeeBase> rows;

    public YearFeeBaseCreator(List<YearFeeBase> ms) {
        rows = ms;
    }

    public List<Integer> Build(String SHENQINGLX, String FEENAME) {
        List<Integer> SS = new ArrayList<Integer>();
        if (rows.size() == 0) return SS;
        if (SHENQINGLX.equals("发明公布") || SHENQINGLX.equals("发明")) {
            rows.stream().filter(f -> f.getType() == 0).forEach(f -> {
                if (FEENAME.equals("发明专利第" + f.getYear() + "年年费")) {
                    SS.add(f.getMoney());
                }
            });
        }else if (SHENQINGLX.equals("实用新型")) {
            rows.stream().filter(f -> f.getType() == 1).forEach(f -> {
                if (FEENAME.equals("实用新型专利第" + f.getYear() + "年年费")) {
                    SS.add(f.getMoney());
                }
            });
        }else if (SHENQINGLX.equals("外观设计")) {
            rows.stream().filter(f -> f.getType() == 2).forEach(f -> {
                if (FEENAME.equals("外观设计专利第" + f.getYear() + "年年费")) {
                    SS.add(f.getMoney());
                }
            });
        }
        return SS;
    }
}
