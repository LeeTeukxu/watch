package com.zhide.govwatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "v_LoginUser")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class v_LoginUser implements Serializable {
    @Id
    @Column(name = "UserID")
    private Integer userId;
    @Column(name = "DepID")
    private Integer depId;
    @Column(name = "RoleID")
    private Integer roleId;
    @Column(name="CompanyID")
    private Integer companyId;
    @Column(name = "LoginCode")
    private String loginCode;
    @Column(name = "Password")
    private String password;
    @Column(name = "CanLogin")
    private boolean canLogin;
    @Column(name = "RoleName")
    private String roleName;
    @Column(name = "DepName")
    private String depName;
    @Column(name="UserName")
    private String userName;
    @Column(name="CompanyName")
    private String companyName;
    @Column(name = "CityName")
    private String cityName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCanLogin() {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin) {
        this.canLogin = canLogin;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityName(){return cityName;}

    public void setCityName(String cityName){this.cityName=cityName;}
}
