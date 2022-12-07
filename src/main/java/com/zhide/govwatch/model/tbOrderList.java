package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tborderlist")
public class tbOrderList implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "OrderListID")
  private Integer orderListId;
  @Column(name = "OrderNo")
  private String orderNo;
  @Column(name = "OrderTime")
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
  @Column(name = "Device_Info")
  private String device_Info;
  @Column(name = "Result_Code")
  private String result_Code;
  @Column(name = "Err_Code_Des")
  private String err_Code_Des;
  @Column(name = "OpenID")
  private String openId;
  @Column(name = "Bank_Type")
  private String bank_Type;
  @Column(name = "Time_End")
  private Date time_End;
  @Column(name = "Transaction_ID")
  private String transaction_Id;
  @Column(name = "UserID")
  private Integer userId;

  public Integer getOrderListId() {
    return orderListId;
  }

  public void setOrderListId(Integer orderListId) {
    this.orderListId = orderListId;
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

  public String getDevice_Info() {
    return device_Info;
  }

  public void setDevice_Info(String device_Info) {
    this.device_Info = device_Info;
  }

  public String getResult_Code() {
    return result_Code;
  }

  public void setResult_Code(String result_Code) {
    this.result_Code = result_Code;
  }

  public String getErr_Code_Des() {
    return err_Code_Des;
  }

  public void setErr_Code_Des(String err_Code_Des) {
    this.err_Code_Des = err_Code_Des;
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

  public Date getTime_End() {
    return time_End;
  }

  public void setTime_End(Date time_End) {
    this.time_End = time_End;
  }

  public String getTransaction_Id() {
    return transaction_Id;
  }

  public void setTransaction_Id(String transaction_Id) {
    this.transaction_Id = transaction_Id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
