package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbpatentcontact")
public class tbPatentContact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MailAddress")
    private String mailAddress;
    @Column(name = "UserId")
    private Integer userId;
    @Column(name = "CreateTime")
    private Date createTime;

    public Integer getId(){return id;}
    public void setId(Integer id){this.id=id;}

    public String getMailAddress(){return mailAddress;}
    public void setMailAddress(String mailAddress){this.mailAddress=mailAddress;}

    public Integer getUserId(){return userId;}
    public void setUserId(Integer userId){this.userId=userId;}

    public Date getCreateTime(){return createTime;}
    public void setCreateTime(Date createTime){this.createTime=createTime;}
}
