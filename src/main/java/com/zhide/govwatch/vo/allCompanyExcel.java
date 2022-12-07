package com.zhide.govwatch.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zhide.govwatch.config.QQCEmpyDateConvert;

import java.util.Date;

/**
 * @ClassName: allCompanyExcel
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年03月30日 11:28
 **/
public class allCompanyExcel {
    @ExcelProperty(value = "信用代码",index=13)
    private String creditCode;
    @ExcelProperty(value = "公司名称",index = 0)
    private String name;
    @ExcelProperty(value = "登记状态",index=1)
    private String status;
    @ExcelProperty(value = "企业法人",index=2)
    private String legalMan;
    @ExcelProperty(value = "注册金额",index=3)
    private String  registerMoney;
    @ExcelProperty(value = "成立日期",index=4,converter = QQCEmpyDateConvert.class)
    private Date registerDate;
    @ExcelProperty(value = "核准日期",index=5,converter = QQCEmpyDateConvert.class)
    private Date confirmDate;
    @ExcelProperty(value = "所属省份",index=6)
    private String provinceName;
    @ExcelProperty(value = "所属城市",index=7)
    private String cityName;
    @ExcelProperty(value = "所属区县",index=8)
    private String countyName;
    @ExcelProperty(value = "电话号码",index=9)
    private String priPhone;
    @ExcelProperty(value = "更多电话",index=10)
    private String secPhone;
    @ExcelProperty(value = "电子邮箱",index=11)
    private String priEmail;
    @ExcelProperty(value = "更多邮箱",index=12)
    private String secEmail;
    @ExcelProperty(value = "纳税人识别码",index=14)
    private String taxCode;
    @ExcelProperty(value = "注册号",index=15)
    private String registerCode;
    @ExcelProperty(value = "组织机构码",index=16)
    private String orgCode;
    @ExcelProperty(value = "参保人数",index=17)
    private String  inSuranceNum;
    @ExcelProperty(value = "企业类型",index=18)
    private String companyType;
    @ExcelProperty(value = "所属行业",index=19)
    private String businessType;
    @ExcelProperty(value = "曾用名",index=20)
    private String oldName;
    @ExcelProperty(value = "英文名称",index=21)
    private String eName;
    @ExcelProperty(value = "企业官网",index=22)
    private String url;
    @ExcelProperty(value = "公司地址",index=23)
    private String address;
    @ExcelProperty(value = "年报地址",index=24)
    private String reportAddress;
    @ExcelProperty(value = "经营范围",index=25)
    private String rangeText;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Integer createMan;


    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLegalMan() {
        return legalMan;
    }

    public void setLegalMan(String legalMan) {
        this.legalMan = legalMan;
    }

    public String  getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String  registerMoney) {
        this.registerMoney = registerMoney;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getPriPhone() {
        return priPhone;
    }

    public void setPriPhone(String priPhone) {
        this.priPhone = priPhone;
    }

    public String getSecPhone() {
        return secPhone;
    }

    public void setSecPhone(String secPhone) {
        this.secPhone = secPhone;
    }

    public String getPriEmail() {
        return priEmail;
    }

    public void setPriEmail(String priEmail) {
        this.priEmail = priEmail;
    }

    public String getSecEmail() {
        return secEmail;
    }

    public void setSecEmail(String secEmail) {
        this.secEmail = secEmail;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String  getInSuranceNum() {
        return inSuranceNum;
    }

    public void setInSuranceNum(String  inSuranceNum) {
        this.inSuranceNum = inSuranceNum;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReportAddress() {
        return reportAddress;
    }

    public void setReportAddress(String reportAddress) {
        this.reportAddress = reportAddress;
    }

    public String getRangeText() {
        return rangeText;
    }

    public void setRangeText(String rangeText) {
        this.rangeText = rangeText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateMan() {
        return createMan;
    }

    public void setCreateMan(Integer createMan) {
        this.createMan = createMan;
    }

}
