package com.zhide.govwatch.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tborderdetail")
public class tbOrderDetail implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "OrderDetailID")
  private Integer orderDetailId;
  @Column(name = "OrderNo")
  private String orderNo;
  @Column(name = "AppNo")
  private String appNo;
  @Column(name = "CostName")
  private String costName;
  @Column(name = "Amount")
  private float amount;
  @Column(name = "LimitDate")
  private Date limitDate;
  @Column(name = "ClientID")
  private Integer clientId;
  @Column(name = "UserID")
  private Integer userId;

  public Integer getOrderDetailId() {
    return orderDetailId;
  }

  public void setOrderDetailId(Integer orderDetailId) {
    this.orderDetailId = orderDetailId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getAppNo() {
    return appNo;
  }

  public void setAppNo(String appNo) {
    this.appNo = appNo;
  }

  public String getCostName() {
    return costName;
  }

  public void setCostName(String costName) {
    this.costName = costName;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public Date getLimitDate() {
    return limitDate;
  }

  public void setLimitDate(Date limitDate) {
    this.limitDate = limitDate;
  }

  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
