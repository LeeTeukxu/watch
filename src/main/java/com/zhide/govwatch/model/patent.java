package com.zhide.govwatch.model;

import com.zhide.govwatch.listener.PatentInfoChangeListener;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Patent")
@EntityListeners({PatentInfoChangeListener.class})
public class patent implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "SHENQINGH")
  private String shenqingh;
  @Column(name = "SHENQINGR")
  private Date shenqingr;
  @Column(name = "FAMINGMC")
  private String famingmc;
  @Column(name = "SHENQINGLX")
  private String  shenqinglx;
  @Column(name = "GONKAIHAO")
  private String gonkaihao;
  @Column(name = "GONKAIR")
  private Date gonkair;
  @Column(name = "PRISHENQINGRXM")
  private String prishenqingrxm;
  @Column(name = "SECSHENQINGRXM")
  private String secshenqingrxm;
  @Column(name = "SHENQINGRXM")
  private String shenqingrxm;
  @Column(name = "PRIADDRESS")
  private String priaddress;
  @Column(name = "POSTCODE")
  private String postcode;
  @Column(name = "ADDRESS")
  private String address;
  @Column(name = "PRIFAMINGRXM")
  private String prifamingrxm;
  @Column(name = "FAMINGRXM")
  private String famingrxm;
  @Column(name = "DAILIJGDM")
  private String dailijgdm;
  @Column(name = "DAILIJGMC")
  private String dailijgmc;
  @Column(name = "DAILIRXM")
  private String dailirxm;
  @Column(name = "PIPC")
  private String pipc;
  @Column(name = "IPC")
  private String ipc;
  @Column(name = "YOUXIANQ")
  private String youxianq;
  @Column(name = "LAWSTATUS")
  private String lawstatus;
  @Column(name = "SECLAWSTATUS")
  private String seclawstatus;
  @Column(name = "MEMO")
  private String memo;
  @Column(name = "ProvinceID")
  private Integer provinceId;
  @Column(name = "ProvinceName")
  private String provinceName;
  @Column(name = "CityID")
  private Integer cityId;
  @Column(name = "CityName")
  private String cityName;
  @Column(name = "CountyID")
  private Integer countyId;
  @Column(name = "CountyName")
  private String countyName;
  @Column(name = "ClientID")
  private Integer clientId;
  @Column(name = "ClientName")
  private String clientName;
  @Column(name = "CREATEMAN")
  private Integer createman;
  @Column(name = "CREATETIME")
  private Date createtime;
  @Column(name = "LASTUPDATETIME")
  private Date lastupdatetime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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
