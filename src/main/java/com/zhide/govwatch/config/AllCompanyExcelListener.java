package com.zhide.govwatch.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhide.govwatch.vo.allCompanyExcel;
import com.zhide.govwatch.vo.guanFamingExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AllCompanyExcelListener
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月30日 15:38
 **/
public class AllCompanyExcelListener extends AnalysisEventListener<allCompanyExcel> {

    List<allCompanyExcel> result=new ArrayList<>();
    /**
     * When analysis one row trigger invoke function.
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(allCompanyExcel data, AnalysisContext context) {
        if(data.getStatus().equals("存续")) result.add(data);
    }

    /**
     * if have something to do after all analysis
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
    public List<allCompanyExcel> getResult(){
        return result;
    }
}
