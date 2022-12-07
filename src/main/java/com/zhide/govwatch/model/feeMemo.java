package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "feememo")
public class feeMemo implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "SHENQINGH")
  private String shenqingh;
  @Column(name = "FEENAME")
  private String feename;
  @Column(name = "Memo")
  private String memo;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "UpdateMan")
  private Integer updateMan;
  @Column(name = "UpdateTime")
  private Date updateTime;
  @Column(name = "SubID")
  private String subId;
  @Column(name = "ImageData")
  private String imageData;

  @Transient
  private Integer Edit;
  @Transient
  private String IDS;

  public Integer getEdit() {
    return Edit;
  }

  public void setEdit(Integer edit) {
    Edit = edit;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public String getShenqingh() {
    return shenqingh;
  }
  public void setShenqingh(String shenqingh) {
    this.shenqingh = shenqingh;
  }


  public String getFeename() {
    return feename;
  }
  public void setFeename(String feename) {
    this.feename = feename;
  }


  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
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


  public Integer getUpdateMan() {
    return updateMan;
  }
  public void setUpdateMan(Integer updateMan) {
    this.updateMan = updateMan;
  }


  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getIDS() {
    return IDS;
  }

  public void setIDS(String IDS) {
    this.IDS = IDS;
  }

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }

  public String getImageData() {
    return imageData;
  }

  public void setImageData(String imageData) {
    this.imageData = imageData;
  }
}
