package com.zhide.govwatch.model;

import java.util.Date;

public class gov {
    private float MONEY;
    private Date CreateTime;
    private Date SHENQINGR;
    private String LAWSTATUS;
    private String SHENQINGRXM;
    private String FEENAME;
    private String FAMINGMC;
    private String PayState;
    private String FAMINGRXM;
    private Date JIAOFEIR;
    private String ReceiptNo;
    private String SHENQINGLX;
    private String SHENQINGH;
    private String ReceiptCode;
    private String DIFFDATE;

    public float getMONEY(){return MONEY;}

    public void setMONEY(float MONEY){this.MONEY=MONEY;}

    public Date getCreateTime(){return CreateTime;}

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public Date getSHENQINGR(){return SHENQINGR;}

    public void setSHENQINGR(Date SHENQINGR) {
        this.SHENQINGR = SHENQINGR;
    }

    public String getLAWSTATUS() {
        return LAWSTATUS;
    }

    public void setLAWSTATUS(String LAWSTATUS) {
        this.LAWSTATUS = LAWSTATUS;
    }

    public String getSHENQINGRXM() {
        return SHENQINGRXM;
    }

    public void setSHENQINGRXM(String SHENQINGRXM) {
        this.SHENQINGRXM = SHENQINGRXM;
    }

    public String getFEENAME() {
        return FEENAME;
    }

    public void setFEENAME(String FEENAME) {
        this.FEENAME = FEENAME;
    }

    public String getFAMINGMC(){return FAMINGMC;}

    public void setFAMINGMC(String FAMINGMC){this.FAMINGMC=FAMINGMC;}

    public String getPayState() {
        return PayState;
    }

    public void setPayState(String payState) {
        PayState = payState;
    }

    public String getFAMINGRXM() {
        return FAMINGRXM;
    }

    public void setFAMINGRXM(String FAMINGRXM){this.FAMINGRXM=FAMINGRXM;}

    public Date getJIAOFEIR() {
        return JIAOFEIR;
    }

    public void setJIAOFEIR(Date JIAOFEIR) {
        this.JIAOFEIR = JIAOFEIR;
    }

    public String getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        ReceiptNo = receiptNo;
    }

    public String getSHENQINGLX() {
        return SHENQINGLX;
    }

    public void setSHENQINGLX(String SHENQINGLX) {
        this.SHENQINGLX = SHENQINGLX;
    }

    public String getSHENQINGH() {
        return SHENQINGH;
    }

    public void setSHENQINGH(String SHENQINGH) {
        this.SHENQINGH = SHENQINGH;
    }

    public String getReceiptCode() {
        return ReceiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        ReceiptCode = receiptCode;
    }

    public String getDIFFDATE() {
        return DIFFDATE;
    }

    public void setDIFFDATE(String DIFFDATE) {
        this.DIFFDATE = DIFFDATE;
    }
}
