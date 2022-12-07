package com.zhide.govwatch.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patentarea")
public class patentarea   implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "UserID")
  private Integer userId;
  @Column(name = "AreaID")
  private Integer areaId;
  @Transient
  private String areaIds;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Transient
  private String sn;
  @Transient
  private String userName;
  @Transient
  Integer pid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public String getAreaIds() {
    return areaIds;
  }

  public void setAreaIds(String areaIds) {
    this.areaIds = areaIds;
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

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }
}
