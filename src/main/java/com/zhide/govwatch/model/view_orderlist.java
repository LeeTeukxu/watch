package com.zhide.govwatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "view_orderlist")
public class view_orderlist implements Serializable {
    @Id
    @Column(name = "OrderListID")
    private Integer orderListID;
    @Column(name = "OrderNo")
    private String orderNo;
    @Column(name = "OrderTIme")
    private Date orderTime;
    @Column(name = "OrderAmountTotal")
    private float orderAmountTotal;
    @Column(name = "Amount")
    private float amount;
    @Column(name = "ServiceCharge")
    private float serviceCharge;
    @Column(name = "PayState")
    private Integer payState;
    @Column(name = "DjState")
    private Integer djState;
    @Column(name = "UserID")
    private Integer userId;
    @Column(name="ProvinceID")
    private Integer provinceId;
    @Column(name = "CityID")
    private Integer cityId;
    @Column(name = "CountyID")
    private Integer countyId;
    @Column(name = "AppNo")
    private String appNo;
    @Column(name = "ClientName")
    private String clientName;
    @Column(name = "FAMINGMC")
    private String famingmc;
    @Column(name = "FEENAME")
    private String feename;
    @Column(name = "LimitDate")
    private Date limitDate;
    @Column(name = "Time_End")
    private Date time_End;
    @Column(name = "Bank_Type")
    private String bank_Type;
    @Column(name = "DetAmount")
    private Integer detAmount;

    public Integer getOrderListID() {
        return orderListID;
    }

    public void setOrderListID(Integer orderListID) {
        this.orderListID = orderListID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public float getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(float orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getDjState() {
        return djState;
    }

    public void setDjState(Integer djState) {
        this.djState = djState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFamingmc() {
        return famingmc;
    }

    public void setFamingmc(String famingmc) {
        this.famingmc = famingmc;
    }

    public String getFeename() {
        return feename;
    }

    public void setFeename(String feename) {
        this.feename = feename;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Date getTime_End() {
        return time_End;
    }

    public void setTime_End(Date time_End) {
        this.time_End = time_End;
    }

    public String getBank_Type() {
        return bank_Type;
    }

    public void setBank_Type(String bank_Type) {
        this.bank_Type = bank_Type;
    }

    public Integer getDetAmount() {
        return detAmount;
    }

    public void setDetAmount(Integer detAmount) {
        this.detAmount = detAmount;
    }
}
