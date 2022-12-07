package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbloginuser")
public class tbLoginUser implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "UserID")
  private Integer userId;
  @Column(name = "CompanyID")
  private Integer companyId;
  @Column(name = "UserName")
  private String userName;
  @Column(name = "DepID")
  private Integer depId;
  @Column(name = "RoleID")
  private Integer roleId;
  @Column(name = "LoginCode")
  private String loginCode;
  @Column(name = "Password")
  private String password;
  @Column(name = "CanLogin")
  private Boolean  canLogin;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "LastUpdateTime")
  private Date lastUpdateTime;
  @Column(name = "LastUpdateMan")
  private Integer lastUpdateMan;
  @Column(name = "LastLoginTime")
  private Date lastLoginTime;
  @Column(name = "LoginCount")
  private Integer loginCount;
  @Column(name = "Memo")
  private String memo;
    @Column(name = "XPass")
    private String xPass;
    @Column(name = "Email")
    private String email;
    @Column(name = "MyCode")
    String myCode;
    @Column(name = "MyAddress")
    String myAddress;
    @Column(name = "PID")
    private Integer pid;
    @Transient
    String areaId;
    @Transient
    String areaName;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }


    public Integer getUserId() {
    return userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
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


  public Boolean  getCanLogin() {
    return canLogin;
  }
  public void setCanLogin(Boolean  canLogin) {
    this.canLogin = canLogin;
  }


  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }


  public Integer getCreateMan() {
    return createMan;
  }
  public void setCreateMan(Integer createMan) {
    this.createMan = createMan;
  }


  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }
  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }


  public Integer getLastUpdateMan() {
    return lastUpdateMan;
  }
  public void setLastUpdateMan(Integer lastUpdateMan) {
    this.lastUpdateMan = lastUpdateMan;
  }


  public Date getLastLoginTime() {
    return lastLoginTime;
  }
  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public Integer getLoginCount() {
    return loginCount;
  }
  public void setLoginCount(Integer loginCount) {
    this.loginCount = loginCount;
  }


  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }


  public String getXPass() {
    return xPass;
  }
  public void setXPass(String xPass) {
    this.xPass = xPass;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

  public String getMyCode() {
    return myCode;
  }

  public void setMyCode(String myCode) {
      this.myCode = myCode;
  }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
