package com.zhide.govwatch.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document()
public class patentMongo {
  @Indexed
  @Field
  @NotNull(message = "专利申请号不能为空")
  private String shenqingh;
  @Field(value = "SHENQINGR")
  private Date shenqingr;
  @Field(value = "FAMINGMC")
  private String famingmc;
  @Field(value = "SHENQINGLX")
  private String  shenqinglx;
  @Field(value = "GONKAIHAO")
  private String gonkaihao;
  @Field(value = "GONKAIR")
  private Date gonkair;
  @Field(value = "PRISHENQINGRXM")
  private String prishenqingrxm;
  @Field(value = "SECSHENQINGRXM")
  private String secshenqingrxm;
  @Field(value = "SHENQINGRXM")
  private String shenqingrxm;
  @Field(value = "PRIADDRESS")
  private String priaddress;
  @Field(value = "POSTCODE")
  private String postcode;
  @Field(value = "ADDRESS")
  private String address;
  @Field(value = "PRIFAMINGRXM")
  private String prifamingrxm;
  @Field(value = "FAMINGRXM")
  private String famingrxm;
  @Field(value = "DAILIJGDM")
  private String dailijgdm;
  @Field(value = "DAILIJGMC")
  private String dailijgmc;
  @Field(value = "DAILIRXM")
  private String dailirxm;
  @Field(value = "PIPC")
  private String pipc;
  @Field(value = "IPC")
  private String ipc;
  @Field(value = "YOUXIANQ")
  private String youxianq;
  @Field(value = "LAWSTATUS")
  private String lawstatus;
  @Field(value = "SECLAWSTATUS")
  private String seclawstatus;
  @Field(value = "MEMO")
  private String memo;
  @Field(value = "ProvinceID")
  private Integer provinceId;
  @Field(value = "ProvinceName")
  private String provinceName;
  @Field(value = "CityID")
  private Integer cityId;
  @Field(value = "CityName")
  private String cityName;
  @Field(value = "CountyID")
  private Integer countyId;
  @Field(value = "CountyName")
  private String countyName;
  @Field(value = "ClientID")
  private Integer clientId;
  @Field(value = "ClientName")
  private String clientName;
  @Field(value = "CREATEMAN")
  private Integer createman;
  @Field(value = "CREATETIME")
  private Date createtime;
  @Field(value = "LASTUPDATETIME")
  private Date lastupdatetime;
  @Field(value="ERRORMESSAGE")
  String errorMessage;
  @Field(value="ISOK")
  Boolean  isOK;

  public String getShenqingh() {
    return shenqingh;
  }

  public void setShenqingh(String shenqingh) {
    this.shenqingh = shenqingh;
  }

  public Date getShenqingr() {
    return shenqingr;
  }

  public void setShenqingr(Date shenqingr) {
    this.shenqingr = shenqingr;
  }


  public String getFamingmc() {
    return famingmc;
  }

  public void setFamingmc(String famingmc) {
    this.famingmc = famingmc;
  }

  public String getShenqinglx() {
    return shenqinglx;
  }

  public void setShenqinglx(String shenqinglx) {
    this.shenqinglx = shenqinglx;
  }

  public String getGonkaihao() {
    return gonkaihao;
  }

  public void setGonkaihao(String gonkaihao) {
    this.gonkaihao = gonkaihao;
  }

  public Date getGonkair() {
    return gonkair;
  }

  public void setGonkair(Date gonkair) {
    this.gonkair = gonkair;
  }

  public String getPrishenqingrxm() {
    return prishenqingrxm;
  }

  public void setPrishenqingrxm(String prishenqingrxm) {
    this.prishenqingrxm = prishenqingrxm;
  }

  public String getSecshenqingrxm() {
    return secshenqingrxm;
  }

  public void setSecshenqingrxm(String secshenqingrxm) {
    this.secshenqingrxm = secshenqingrxm;
  }

  public String getShenqingrxm() {
    return shenqingrxm;
  }

  public void setShenqingrxm(String shenqingrxm) {
    this.shenqingrxm = shenqingrxm;
  }

  public String getPriaddress() {
    return priaddress;
  }

  public void setPriaddress(String priaddress) {
    this.priaddress = priaddress;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPrifamingrxm() {
    return prifamingrxm;
  }

  public void setPrifamingrxm(String prifamingrxm) {
    this.prifamingrxm = prifamingrxm;
  }

  public String getFamingrxm() {
    return famingrxm;
  }

  public void setFamingrxm(String famingrxm) {
    this.famingrxm = famingrxm;
  }

  public String getDailijgdm() {
    return dailijgdm;
  }

  public void setDailijgdm(String dailijgdm) {
    this.dailijgdm = dailijgdm;
  }

  public String getDailijgmc() {
    return dailijgmc;
  }

  public void setDailijgmc(String dailijgmc) {
    this.dailijgmc = dailijgmc;
  }

  public String getDailirxm() {
    return dailirxm;
  }

  public void setDailirxm(String dailirxm) {
    this.dailirxm = dailirxm;
  }
  public String getPipc() {
    return pipc;
  }

  public void setPipc(String pipc) {
    this.pipc = pipc;
  }

  public String getIpc() {
    return ipc;
  }

  public void setIpc(String ipc) {
    this.ipc = ipc;
  }

  public String getYouxianq() {
    return youxianq;
  }

  public void setYouxianq(String youxianq) {
    this.youxianq = youxianq;
  }

  public String getLawstatus() {
    return lawstatus;
  }

  public void setLawstatus(String lawstatus) {
    this.lawstatus = lawstatus;
  }

  public String getSeclawstatus() {
    return seclawstatus;
  }

  public void setSeclawstatus(String seclawstatus) {
    this.seclawstatus = seclawstatus;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Integer getCountyId() {
    return countyId;
  }

  public void setCountyId(Integer countyId) {
    this.countyId = countyId;
  }

  public String getCountyName() {
    return countyName;
  }

  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

  public Integer getCreateman() {
    return createman;
  }

  public void setCreateman(Integer createman) {
    this.createman = createman;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public Date getLastupdatetime() {
    return lastupdatetime;
  }

  public void setLastupdatetime(Date lastupdatetime) {
    this.lastupdatetime = lastupdatetime;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Boolean isOK() {
    if(isOK==null)isOK=true;
    return isOK;
  }
  public void setOK(Boolean OK) {
    isOK = OK;
  }

  public Integer getProvinceId() {
    return provinceId;
  }

  public void setProvinceId(Integer provinceId) {
    this.provinceId = provinceId;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }
}
