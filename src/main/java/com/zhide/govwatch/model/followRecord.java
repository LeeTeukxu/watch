package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FollowRecord")
public class followRecord implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "FID")
    private String fid;
    @Column(name = "Record")
    private String record;
    @Column(name = "FollowUserName")
    private String followUserName;
    @Column(name = "CreateTime")
    private Date createTime;
    @Column(name = "UpdateTime")
    private Date updateTime;
    @Column(name = "ClientID")
    private Integer clientId;
    @Column(name = "UserID")
    private Integer userId;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "RoleID")
    private Integer roleId;
    @Column(name = "RoleName")
    private String roleName;
    @Column(name = "DepID")
    private Integer depId;
    @Column(name = "CustMail")
    private String custMail;
    @Column(name = "ImageData")
    private String imageData;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }


    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public Integer getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }


    public String getCustMail() {
        return custMail;
    }

    public void setCustMail(String custMail) {
        this.custMail = custMail;
    }

    public String getImageData() {
        return imageData;
    }
    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
