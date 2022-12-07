package com.zhide.govwatch.model;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "discode")
public class discode   implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "Code")
  private String code;
  @Column(name = "IP")
  private String ip;
  @Column(name = "CreateTim")
  private Date createTim;

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }


  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }


  public Date getCreateTim() {
    return createTim;
  }
  public void setCreateTim(Date createTim) {
    this.createTim = createTim;
  }

}
