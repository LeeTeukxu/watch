package com.zhide.govwatch.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

@ColumnWidth(30)
public class pantentExcel{
  @ExcelProperty(index = 0,value = "专利编号")
  private String shenqingh;
  @ExcelProperty(index=1,value = "申请日",format = "yyyy.MM.dd")
  @DateTimeFormat(value = "yyyy.MM.dd")
  private Date shenqingr;
  @ExcelProperty(index=2,value = "发明名称")
  private String famingmc;
  @ExcelProperty(index=3,value="申请类型")
  private String  shenqinglx;
  @ExcelProperty(index=5,value="公开公告号")
  private String gonkaihao;
  @ExcelProperty(index=6,value="公开公告日",format="yyyy.MM.dd")
  @DateTimeFormat(value = "yyyy.MM.dd")
  private Date gonkair;
  @ExcelProperty(index=7,value = "主申请人")
  private String prishenqingrxm;
  @ExcelProperty(index=8,value = "申请人")
  private String secshenqingrxm;
  @ExcelProperty(index=10,value="当前申请人")
  private String shenqingrxm;
  @ExcelProperty(index=9,value="地址")
  private String priaddress;
  @ExcelIgnore
  private String postcode;
  @ExcelProperty(index=11,value="当前地址")
  private String address;
  @ExcelProperty(index=12,value="主发明人")
  private String prifamingrxm;
  @ExcelProperty(index=13,value="发明人")
  private String famingrxm;
  @ExcelIgnore
  private String dailijgdm;
  @ExcelProperty(index=14,value="代理机构名称")
  private String dailijgmc;
  @ExcelProperty(index=15,value="代理人")
  private String dailirxm;
  @ExcelProperty(index=16,value="主IPC")
  private String pipc;
  @ExcelProperty(index=17,value="IPC")
  private String ipc;
  @ExcelProperty(index=18,value="优先权")
  private String youxianq;
  @ExcelProperty(index=19,value="主法律状态")
  private String lawstatus;
  @ExcelProperty(index=20,value="辅法律状态")
  private String seclawstatus;
  @ExcelProperty(index=21,value="摘要")
  @Column(name = "MEMO")
  private String memo;

  @ExcelIgnore
  private Integer provinceId;
  @ExcelIgnore
  private String provinceName;

  @ExcelIgnore
  private Integer cityId;
  @ExcelIgnore
  private String cityName;
  @ExcelIgnore
  private Integer countyId;
  @ExcelIgnore
  private String countyName;
  @ExcelIgnore
  private Integer clientId;
  @ExcelIgnore
  private String clientName;
  @ExcelIgnore
  private Integer createman;
  @ExcelIgnore
  private Date createtime;
  @ExcelIgnore
  private Date lastupdatetime;

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
