package com.zhide.govwatch.model;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @ClassName: TechCompanyInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年08月24日 9:35
 **/
public class techCompanyExcel {
    @ExcelProperty(index = 0, value = "序号")
    private Integer id;
    @ExcelProperty(index = 1, value = "企业名称")
    private String name;
    @ExcelProperty(index = 2, value = "证书编号")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
