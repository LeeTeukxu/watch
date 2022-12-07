package com.zhide.govwatch.config;

import com.alibaba.excel.converters.date.DateStringConverter;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: QQCEmpyDateConvert
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年04月01日 8:55
 **/
public class QQCEmpyDateConvert extends com.alibaba.excel.converters.AutoConverter {
    SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Object convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
            GlobalConfiguration globalConfiguration) {
        String dateValue=cellData.getStringValue();
        if(StringUtils.isEmpty(dateValue)) return null;
        if(dateValue.equals("-")) return null;
        if(dateValue.length()<5) return null;
        try {
            return simple.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
