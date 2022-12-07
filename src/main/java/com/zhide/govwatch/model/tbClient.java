package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbclient")
public class tbClient implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ClientID")
  private Integer clientId;
  @Column(name = "CreditCode")
  private String creditCode;
  @Column(name = "Account")
  private String account;
  @Column(name = "Name")
  private String name;
  @Column(name = "Address")
  private String address;
  @Column(name = "LinkMan")
  private String linkMan;
  @Column(name = "LinkPhone")
  private String linkPhone;
  @Column(name = "Email")
  private String email;
  @Column(name = "QQ")
  private String qq;
  @Column(name = "WX")
  private String wx;
  @Column(name = "Tele")
  private String tele;
  @Column(name = "Mobile")
  private String mobile;
  @Column(name = "PostCode")
  private String postCode;
  @Column(name = "CreateMan")
  private Integer createMan;
  @Column(name = "CreateTime")
  private Date createTime;
  @Column(name = "SignMan")
  private Integer signMan;
  @Column(name = "CanUse")
  private Boolean canUse;
  @Column(name = "Memo")
  private String memo;
  @Column(name = "SignDate")
  private Date signDate;
  @Column(name = "FullName")
  private String fullName;
  @Column(name = "Fax")
  private String fax;
  @Column(name = "Type")
  private Integer type;
  @Column(name = "Password")
  private String password;
  @Column(name = "CanLogin")
  private Boolean canLogin;
  @Column(name = "LastLoginTime")
  private Date lastLoginTime;
    @Column(name = "LoginCount")
    private Integer loginCount;
    @Column(name = "TSource")
    private String tSource;
    @Column(name = "CompanyType")
    private Integer companyTYpe;
    @Column(name = "TechYear")
    private Integer techYear;
    @Column(name = "TechNo")
    private String techNo;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "UseTime")
    private Date useTime;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


  public String getCreditCode() {
    return creditCode;
  }
  public void setCreditCode(String creditCode) {
    this.creditCode = creditCode;
  }


  public String getAccount() {
    return account;
  }
  public void setAccount(String account) {
    this.account = account;
  }


  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }


  public String getLinkMan() {
    return linkMan;
  }
  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }


  public String getLinkPhone() {
    return linkPhone;
  }
  public void setLinkPhone(String linkPhone) {
    this.linkPhone = linkPhone;
  }


  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }


  public String getQq() {
    return qq;
  }
  public void setQq(String qq) {
    this.qq = qq;
  }


  public String getWx() {
    return wx;
  }
  public void setWx(String wx) {
    this.wx = wx;
  }


  public String getTele() {
    return tele;
  }
  public void setTele(String tele) {
    this.tele = tele;
  }


  public String getMobile() {
    return mobile;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getPostCode() {
    return postCode;
  }
  public void setPostCode(String postCode) {
    this.postCode = postCode;
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


  public Integer getSignMan() {
    return signMan;
  }
  public void setSignMan(Integer signMan) {
    this.signMan = signMan;
  }


  public Boolean getCanUse() {
    return canUse;
  }
  public void setCanUse(Boolean canUse) {
    this.canUse = canUse;
  }


  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }


  public Date getSignDate() {
    return signDate;
  }
  public void setSignDate(Date signDate) {
    this.signDate = signDate;
  }


  public String getFax() {
    return fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }


  public Integer getType() {
    return type;
  }
  public void setType(Integer type) {
    this.type = type;
  }


  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }


  public Boolean getCanLogin() {
    return canLogin;
  }
  public void setCanLogin(Boolean canLogin) {
    this.canLogin = canLogin;
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


  public String getTSource() {
    return tSource;
  }
  public void setTSource(String tSource) {
    this.tSource = tSource;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getCompanyTYpe() {
    return companyTYpe;
  }

  public void setCompanyTYpe(Integer companyTYpe) {
    this.companyTYpe = companyTYpe;
  }

  public Integer getTechYear() {
    return techYear;
  }

  public void setTechYear(Integer techYear) {
      this.techYear = techYear;
  }

    public String getTechNo() {
        return techNo;
    }

    public void setTechNo(String techNo) {
        this.techNo = techNo;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
