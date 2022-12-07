package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "view_orderdetails")
public class view_OrderDetails implements Serializable {
    @Id
    @Column(name = "OrderDetailID")
    private String orderDetailId;
    @Column(name = "FEENAME")
    private String FEENAME;
    @Column(name = "FAMINGMC")
    private String FAMINGMC;
    @Column(name = "PayState")
    private Integer payState;
    @Column(name = "Amount")
    private float amount;
    @Column(name = "OrderNo")
    private String orderNo;
    @Column(name = "DjState")
    private Integer djState;
    @Column(name = "AppNo")
    private String appNo;
    @Column(name = "LimitDate")
    private Date limitDate;
    @Column(name = "ClientName")
    private String clientName;
    @Column(name = "Time_End")
    private Date time_End;
    @Column(name = "OpenID")
    private String openId;
    @Column(name = "Bank_Type")
    private String bank_Type;
    @Column(name = "Transaction_ID")
    private String transaction_Id;
    @Transient
    private String FEEPRICE;
    @Transient
    private float AFTERPRICE;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getFEENAME() {
        return FEENAME;
    }

    public void setFEENAME(String FEENAME) {
        this.FEENAME = FEENAME;
    }

    public String getFAMINGMC() {
        return FAMINGMC;
    }

    public void setFAMINGMC(String FAMINGMC) {
        this.FAMINGMC = FAMINGMC;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getDjState() {
        return djState;
    }

    public void setDjState(Integer djState) {
        this.djState = djState;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getTime_End() {
        return time_End;
    }

    public void setTime_End(Date time_End) {
        this.time_End = time_End;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getBank_Type() {
        return bank_Type;
    }

    public void setBank_Type(String bank_Type) {
        this.bank_Type = bank_Type;
    }

    public String getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(String transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public String getFEEPRICE() {
        return FEEPRICE;
    }

    public void setFEEPRICE(String FEEPRICE) {
        this.FEEPRICE = FEEPRICE;
    }

    public float getAFTERPRICE() {
        return AFTERPRICE;
    }

    public void setAFTERPRICE(float AFTERPRICE) {
        this.AFTERPRICE = AFTERPRICE;
    }
}
