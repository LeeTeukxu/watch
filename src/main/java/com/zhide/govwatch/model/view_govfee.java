package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "view_govfee")
public class view_govfee implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SHENQINGH")
    private String SHENQINGH;
    @Column(name = "FAMINGMC")
    private String famingmc;
    @Column(name = "SHENQINGLX")
    private String  shenqinglx;
    @Column(name = "FEENAME")
    private String FEENAME;
    @Column(name = "JIAOFEIR")
    private Date jiaofeir;
    @Column(name = "ReceiptCode")
    private String receiptCode;
    @Column(name = "ReceiptNo")
    private String receiptNo;
    @Column(name = "MONEY")
    private float money;
    @Column(name = "PayState")
    private String payState;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "GovID")
    private Integer govId;
    @Column(name = "LAWSTATUS")
    private String lawStatus;
    @Column(name = "SHENQINGR")
    private Date shenqingr;
    @Column(name = "FAMINGRXM")
    private String famingrxm;
    @Column(name = "SHENQINGRXM")
    private String shenqingrxm;
    @Column(name = "ClientID")
    private Integer clientId;
    @Column(name = "CityID")
    private Integer cityId;
    @Column(name = "ProvinceID")
    private Integer provinceId;
    @Column(name = "CountyID")
    private Integer countyId;
    @Column(name = "ClientName")
    private String clientName;
    @Column(name = "DIFFDATE")
    private Integer diffDate;

    public String getSHENQINGH() {
        return SHENQINGH;
    }

    public void setSHENQINGH(String SHENQINGH) {
        this.SHENQINGH = SHENQINGH;
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

    public String getFEENAME() {
        return FEENAME;
    }

    public void setFEENAME(String FEENAME) {
        this.FEENAME = FEENAME;
    }

    public Date getJiaofeir() {
        return jiaofeir;
    }

    public void setJiaofeir(Date jiaofeir) {
        this.jiaofeir = jiaofeir;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGovId() {
        return govId;
    }

    public void setGovId(Integer govId) {
        this.govId = govId;
    }

    public String getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(String lawStatus) {
        this.lawStatus = lawStatus;
    }

    public Date getShenqingr() {
        return shenqingr;
    }

    public void setShenqingr(Date shenqingr) {
        this.shenqingr = shenqingr;
    }

    public String getFamingrxm() {
        return famingrxm;
    }

    public void setFamingrxm(String famingrxm) {
        this.famingrxm = famingrxm;
    }

    public String getShenqingrxm() {
        return shenqingrxm;
    }

    public void setShenqingrxm(String shenqingrxm) {
        this.shenqingrxm = shenqingrxm;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getDiffDate() {
        return diffDate;
    }

    public void setDiffDate(Integer diffDate) {
        this.diffDate = diffDate;
    }
}
