package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbMailRecord")
public class tbMailRecord implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "GovFeeID")
  private Integer govFeeId;
  @Column(name = "SHENQINGH")
  private String shenqingh;
  @Column(name = "SendContent")
  private String sendContent;
  @Column(name = "Day")
  private String day;
  @Column(name = "UserID")
  private Integer userId;
  @Column(name = "CreateTime")
  private Date createTime;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getGovFeeId(){return govFeeId;}
  public void setGovFeeId(Integer govFeeId){this.govFeeId=govFeeId;}

  public String getShenqingh() {
    return shenqingh;
  }
  public void setShenqingh(String shenqingh) {
    this.shenqingh = shenqingh;
  }

  public String getSendContent() {
    return sendContent;
  }
  public void setSendContent(String sendContent) {
    this.sendContent = sendContent;
  }


  public String getDay() {
    return day;
  }
  public void setDay(String day) {
    this.day = day;
  }


  public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}
