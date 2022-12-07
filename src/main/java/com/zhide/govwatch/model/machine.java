package com.zhide.govwatch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "machine")
public class machine implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "IP")
    private String ip;
    @Column(name = "Mac")
    private String mac;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "Name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
