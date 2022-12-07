package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbcompany")
public class tbCompany implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "Name")
  private String name;
  @Column(name = "Type")
  private Integer type;
  @Column(name = "Address")
  private String address;
  @Column(name = "LinkPhone")
  private String linkPhone;
  @Column(name = "LinkMan")
  private String linkMan;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "Memo")
  private String memo;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }


  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }


  public String getLinkPhone() {
    return linkPhone;
  }
  public void setLinkPhone(String linkPhone) {
    this.linkPhone = linkPhone;
  }


  public String getLinkMan() {
    return linkMan;
  }
  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }


  public Integer getCreateMan() {
    return createMan;
  }
  public void setCreateMan(Integer createMan) {
    this.createMan = createMan;
  }


  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }

}
