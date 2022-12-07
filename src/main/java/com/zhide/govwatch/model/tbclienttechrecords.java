package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbclienttechrecords")
public class tbclienttechrecords implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "ClientID")
  private Integer clientId;
  @Column(name = "Name")
  private String name;
  @Column(name = "No")
  private String no;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "CreateMan")
  private Integer createMan;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getClientId() {
    return clientId;
  }

  public void setClientId(Integer clientId) {
    this.clientId = clientId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
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
