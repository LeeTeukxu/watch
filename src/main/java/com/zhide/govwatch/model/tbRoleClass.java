package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbRoleClass")
public class tbRoleClass implements Serializable {
    @Id
    @Column(name = "RoleID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    @Column(name = "SN")
    private int sn;
    @Column(name = "PID")
    private int pid;
    @Column(name = "RoleName")
    private String roleName;
    @Column(name = "CanUse")
    private boolean canUse;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }


    public int getRoleId() {
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


    public boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }


}
