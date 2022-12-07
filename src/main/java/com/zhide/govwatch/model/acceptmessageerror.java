package com.zhide.govwatch.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "acceptmessageerror")
public class acceptmessageerror   implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "Type")
  private String type;
  @Column(name = "Text")
  private String text;
  @Column(name = "Error")
  private String error;
  @Column(name = "CreateTime")
  private Date createTime;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }


  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }


  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }


  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
