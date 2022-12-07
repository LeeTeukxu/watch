package com.zhide.govwatch.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class LoginUserInfo implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int  depId;
    private int  userId;
    private String userName;
    private String account;
    private String depName;
    private String password;
    private String roleName;
    private Integer roleId;
    private Integer  companyId;
    private String companyName;
    private boolean canLogin;
    private String cityName;
    public LoginUserInfo(){}
    public LoginUserInfo(v_LoginUser userInfo){
        this.depId=userInfo.getDepId();
        this.depName=userInfo.getDepName();
        this.userId=userInfo.getUserId();
        this.userName=userInfo.getUserName();
        this.roleId=userInfo.getRoleId();
        this.roleName=userInfo.getRoleName();
        this.account=userInfo.getLoginCode();
        this.canLogin=userInfo.isCanLogin();
        this.password=userInfo.getPassword();
        this.companyName=userInfo.getCompanyName();
        this.cityName=userInfo.getCityName();
        this.companyId=userInfo.getCompanyId();
    }
    public LoginUserInfo(tbClient client){
        this.depId=client.getClientId();
        this.depName="办公室";
        this.userId=client.getClientId();
        this.userName="企业人员";
        this.roleId=6;
        this.companyName=client.getName();
        this.roleName="企业用户";
        this.account=client.getAccount();
        this.canLogin=true;
        this.password=client.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> collection=new ArrayList<>();
        String account=this.getAccount();
        if(account!=null){
            SimpleGrantedAuthority simple=new SimpleGrantedAuthority(account);
            collection.add(simple);
        }
        return collection;
    }
    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getUserId() {
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer  getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer  roleId) {
        this.roleId = roleId;
    }

    public boolean isCanLogin() {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin) {
        this.canLogin = canLogin;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
