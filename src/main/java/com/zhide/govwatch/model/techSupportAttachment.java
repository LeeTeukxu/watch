package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "techsupportattachment")
public class techSupportAttachment implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "DocSN")
  private String docSn;
  @Column(name = "CasesID")
  private String casesId;
  @Column(name = "AttID")
  private String attId;
  @Column(name = "RefID")
  private String refId;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "Account")
  private Boolean account;
  @Column(name = "AccountMan")
  private Integer accountMan;
  @Column(name = "AccountTime")
  private Date accountTime;
  @Column(name = "TechMan")
  private Integer techMan;
  @Column(name = "ClientID")
  private Integer clientId;
  @Column(name = "TechManName")
  private String techManName;
  @Column(name = "ClientName")
  private String clientName;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public String getDocSn() {
    return docSn;
  }
  public void setDocSn(String docSn) {
    this.docSn = docSn;
  }


  public String getCasesId() {
    return casesId;
  }
  public void setCasesId(String casesId) {
    this.casesId = casesId;
  }


  public String getAttId() {
    return attId;
  }
  public void setAttId(String attId) {
    this.attId = attId;
  }


  public String getRefId() {
    return refId;
  }
  public void setRefId(String refId) {
    this.refId = refId;
  }


  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Boolean getAccount() {
    return account;
  }
  public void setAccount(Boolean account) {
    this.account = account;
  }


  public Integer getAccountMan() {
    return accountMan;
  }
  public void setAccountMan(Integer accountMan) {
    this.accountMan = accountMan;
  }


  public Date getAccountTime() {
    return accountTime;
  }
  public void setAccountTime(Date accountTime) {
    this.accountTime = accountTime;
  }


  public Integer getTechMan() {
    return techMan;
  }
  public void setTechMan(Integer techMan) {
    this.techMan = techMan;
  }


  public Integer getClientId() {
    return clientId;
  }
  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }


  public String getTechManName() {
    return techManName;
  }
  public void setTechManName(String techManName) {
    this.techManName = techManName;
  }


  public String getClientName() {
    return clientName;
  }
  public void setClientName(String clientName) {
    this.clientName = clientName;
  }

}
