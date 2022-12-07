package com.zhide.govwatch.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbarea")
public class tbarea   implements Serializable {
  @Id
  @Column(name = "ID")
  private Integer id;
  @Column(name = "PID")
  private Integer pid;
  @Column(name = "Name")
  private String name;
  @Column(name="SN")
  private Integer sn;
  @Column(name="Memo")
  private String memo;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getPid() {
    return pid;
  }
  public void setPid(Integer pid) {
    this.pid = pid;
  }


  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public Integer getSn() {
    return sn;
  }

  public void setSn(Integer sn) {
    this.sn = sn;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }
}
