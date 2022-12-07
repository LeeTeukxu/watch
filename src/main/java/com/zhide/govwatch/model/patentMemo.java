package com.zhide.govwatch.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patentMemo")
public class patentMemo   implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name="MID")
  private String mid;
  @Column(name = "SHENQINGH")
  private String shenqingh;
  @Column(name = "Memo")
  private String memo;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "CreateDate")
  private Date createDate;
  @Column(name = "UpdateMan")
  private Integer updateMan;
  @Column(name = "UpdateDate")
  private Date updateDate;
  @Column(name = "ImageData")
  private String imageData;



  public String getShenqingh() {
    return shenqingh;
  }
  public void setShenqingh(String shenqingh) {
    this.shenqingh = shenqingh;
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


  public Date getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public Integer getUpdateMan() {
    return updateMan;
  }
  public void setUpdateMan(Integer updateMan) {
    this.updateMan = updateMan;
  }


  public Date getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }


  public String getImageData() {
    return imageData;
  }
  public void setImageData(String imageData) {
    this.imageData = imageData;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }
}
