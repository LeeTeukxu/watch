package com.zhide.govwatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "patentupdatemain")
public class patentupdatemain implements Serializable {
  @Id
  @Column(name = "No")
  private String no;
  @Column(name = "Num")
  private Integer num;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "CreateTime")
  private Date createTime;

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }


  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
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

}
