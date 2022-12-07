package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "view_govpay")
public class view_goypay1 implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer GovID;
    @Column(name = "SHENQINGH")
    private String SHENQINGH;
    @Column(name = "CreateTime")
    private Date createTime;
    public Integer getGovID() {
        return GovID;
    }

    public void setGovID(Integer govID) {
        GovID = govID;
    }

    public String getSHENQINGH() {
        return SHENQINGH;
    }

    public void setSHENQINGH(String SHENQINGH) {
        this.SHENQINGH = SHENQINGH;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
