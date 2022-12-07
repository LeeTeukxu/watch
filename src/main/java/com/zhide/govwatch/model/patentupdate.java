package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "patentupdate")
public class patentupdate implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "No")
  private String no;
  @Column(name = "Machine")
  private String machine;
  @Column(name = "Shenqingh")
  private String shenqingh;
  @Column(name = "PUpdate")
  private Integer pUpdate;
  @Column(name = "PUpdateTime")
  private Date pUpdateTime;
  @Column(name = "GUpdate")
  private Integer gUpdate;
  @Column(name = "GUpdateTime")
  private Date gUpdateTime;
  @Column(name = "CreateTime")
  private Date createTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }


  public String getMachine() {
    return machine;
  }

  public void setMachine(String machine) {
    this.machine = machine;
  }


  public String getShenqingh() {
    return shenqingh;
  }

  public void setShenqingh(String shenqingh) {
    this.shenqingh = shenqingh;
  }


  public Integer getPUpdate() {
    return pUpdate;
  }

  public void setPUpdate(Integer pUpdate) {
    this.pUpdate = pUpdate;
  }


  public Date getPUpdateTime() {
    return pUpdateTime;
  }

  public void setPUpdateTime(Date pUpdateTime) {
    this.pUpdateTime = pUpdateTime;
  }


  public Integer getGUpdate() {
    return gUpdate;
  }

  public void setGUpdate(Integer gUpdate) {
    this.gUpdate = gUpdate;
  }


  public Date getGUpdateTime() {
    return gUpdateTime;
  }

  public void setGUpdateTime(Date gUpdateTime) {
    this.gUpdateTime = gUpdateTime;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
