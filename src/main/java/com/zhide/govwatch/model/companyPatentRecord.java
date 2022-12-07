package com.zhide.govwatch.model;

import java.util.List;

/**
 * @ClassName: companyPatentRecord
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年09月21日 16:02
 **/
public class companyPatentRecord {
    private String companyName;
    private String address;
    private String linkMan;
    private String linkPhone;
    private Integer patentNum;
    private List<patent> items;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(Integer patentNum) {
        this.patentNum = patentNum;
    }

    public List<patent> getItems() {
        return items;
    }

    public void setItems(List<patent> items) {
        this.items = items;
    }
}
