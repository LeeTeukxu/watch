package com.zhide.govwatch.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Document
public class companyAllMongo implements Serializable {
  @Field(name = "CreditCode")
  private String creditCode;
  @Field(name = "Name")
  private String name;
  @Field(name = "Status")
  private String status;
  @Field(name = "LegalMan")
  private String legalMan;
  @Field(name = "RegisterMoney")
  private String  registerMoney;
  @Field(name = "RegisterDate")
  private Date registerDate;
  @Field(name = "ConfirmDate")
  private Date confirmDate;
  @Field(name = "ProvinceName")
  private String provinceName;
  @Field(name = "CityName")
  private String cityName;
  @Field(name = "CountyName")
  private String countyName;
  @Field(name = "PriPhone")
  private String priPhone;
  @Field(name = "SecPhone")
  private String secPhone;
  @Field(name = "PriEmail")
  private String priEmail;
  @Field(name = "SecEmail")
  private String secEmail;
  @Field(name = "TaxCode")
  private String taxCode;
  @Field(name = "RegisterCode")
  private String registerCode;
  @Field(name = "OrgCode")
  private String orgCode;
  @Field(name = "InSuranceNum")
  private String  inSuranceNum;
  @Field(name = "CompanyType")
  private String companyType;
  @Field(name = "BusinessType")
  private String businessType;
  @Field(name = "OldName")
  private String oldName;
  @Field(name = "EName")
  private String eName;
  @Field(name = "Url")
  private String url;
  @Field(name = "Address")
  private String address;
  @Field(name = "ReportAddress")
  private String reportAddress;
  @Field(name = "RangeText")
  private String rangeText;
  @Field(name = "CreateTime")
  private Date createTime;
  @Field(name = "CreateMan")
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


  public String getEName() {
    return eName;
  }
  public void setEName(String eName) {
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
