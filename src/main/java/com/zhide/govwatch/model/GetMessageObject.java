package com.zhide.govwatch.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: GetMessageObject
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年08月02日 14:46
 **/
public class GetMessageObject implements Serializable {
    private String ID;
    private String No;
    private String Shenqingh;
    private Date CreateTime;
    private Boolean PatentUpdate;
    private Date PatentUpdateTime;
    private Boolean GovFeeUpdate;
    private Date GovFeeUpdateTime;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getShenqingh() {
        return Shenqingh;
    }

    public void setShenqingh(String shenqingh) {
        Shenqingh = shenqingh;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Boolean getPatentUpdate() {
        return PatentUpdate;
    }

    public void setPatentUpdate(Boolean patentUpdate) {
        PatentUpdate = patentUpdate;
    }

    public Date getPatentUpdateTime() {
        return PatentUpdateTime;
    }

    public void setPatentUpdateTime(Date patentUpdateTime) {
        PatentUpdateTime = patentUpdateTime;
    }

    public Boolean getGovFeeUpdate() {
        return GovFeeUpdate;
    }

    public void setGovFeeUpdate(Boolean govFeeUpdate) {
        GovFeeUpdate = govFeeUpdate;
    }

    public Date getGovFeeUpdateTime() {
        return GovFeeUpdateTime;
    }

    public void setGovFeeUpdateTime(Date govFeeUpdateTime) {
        GovFeeUpdateTime = govFeeUpdateTime;
    }
}
