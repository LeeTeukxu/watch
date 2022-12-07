package com.zhide.govwatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbdepList")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class tbDepList implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DepID")
    private Integer depId;
    @Column(name = "PID")
    private Integer pid;
    @Column(name = "Name")
    private String name;
    @Column(name = "CompanyID")
    private Integer companyId;
    @Column(name = "CanUse")
    private boolean canUse;
    @Column(name = "Memo")
    private String memo;
    @Column(name = "Sort")
    private String sort;
    @Column(name = "CreateMan")
    private Integer createMan;
    @Column(name = "CreateTime")
    private Date createTime;

    @Transient
    private Integer num;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getCompanyId(){return companyId;}

    public void setCompanyId(Integer companyId){this.companyId = companyId;}

    public Integer getCreateMan(){return createMan;}

    public void setCreateMan(Integer createMan){this.createMan = createMan;}

    public Date getCreateTime(){return createTime;}

    public void setCreateTime(Date createTime){this.createTime = createTime;}

    public Integer getNum() {
        if(num==null)num=0;
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
