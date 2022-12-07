package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FunctionPermissionList")
public class functionPermissionList implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FID")
    private int fid;
    @Column(name = "SN")
    private String sn;
    @Column(name = "Name")
    private String name;
    @Column(name = "Title")
    private String title;
    @Column(name = "CanUse")
    private boolean canUse;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

}
