package com.zhide.govwatch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;
import java.util.Date;

@Document
public class govFeeWaitMongo {

    @Field(value = "GOVID")
    private Integer GOVID;

    @Indexed
    @Field
    @NotNull(message = "专利申请号不能为空")
    private String SHENQINGH;


    @Field(value = "FAMINGMC")
    private String FAMINGMC;


    @Field(value = "SHENQINGLX")
    private String  SHENQINGLX;


    @Field(value = "FEENAME")
    private String FEENAME;

    @Indexed
    @Field(value = "JIAOFEIR")
    private Date JIAOFEIR;


    @Field(value = "RECEIPTCODE")
    private String RECEIPTCODE;


    @Field(value = "RECEIPTNO")
    private String RECEIPTNO;


    @Field(value = "MONEY")
    private float MONEY;


    @Field(value = "PAYSTATE")
    private String PAYSTATE;


    @Field(value = "CREATETIME")
    private Date CREATETIME;



    @Field(value = "LAWSTATUS")
    private String LAWSTATUS;


    @Field(value = "SHENQINGR")
    private Date SHENQINGR;


    @Field(value = "FAMINGRXM")
    private String FAMINGRXM;

    @Field(value = "SHENQINGRXM")
    private String SHENQINGRXM;


    @Field(value = "CLIENTID")
    private Integer CLIENTID;


    @Field(value = "CITYID")
    private Integer CITYID;


    @Field(value = "PROVINCEID")
    private Integer PROVINCEID;

    @Field(value = "COUNTYID")
    private Integer COUNTYID;

    @Field(value = "CLIENTNAME")
    private String CLIENTNAME;

    @Field(value = "GENERATE")
    private Integer GENERATE;

    @Field(value = "DIFFDATE")
    private Integer DIFFDATE;

    @Field(value = "EDITMEMO")
    private Integer EDITMEMO;

    @Field(value = "MEMO")
    private String MEMO;

    @Field(value = "DJSTATE")
    private String DJSTATE;

    @Field(value = "PSTATE")
    private String PSTATE;

    @Field(value = "FEEPRICE")
    private Integer FEEPRICE;

    @JsonProperty(value = "GOVID")
    public Integer getGOVID() {
        return GOVID;
    }

    public void setGOVID(Integer GOVID) {
        this.GOVID = GOVID;
    }
    @JsonProperty(value="SHENQINGH")
    public String getSHENQINGH() {
        return SHENQINGH;
    }

    public void setSHENQINGH(String SHENQINGH) {
        this.SHENQINGH = SHENQINGH;
    }
    @JsonProperty(value="FAMINGMC")
    public String getFAMINGMC() {
        return FAMINGMC;
    }

    public void setFAMINGMC(String FAMINGMC) {
        this.FAMINGMC = FAMINGMC;
    }
    @JsonProperty(value="SHENQINGLX")
    public String getSHENQINGLX() {
        return SHENQINGLX;
    }

    public void setSHENQINGLX(String SHENQINGLX) {
        this.SHENQINGLX = SHENQINGLX;
    }
    @JsonProperty(value="FEENAME")
    public String getFEENAME() {
        return FEENAME;
    }

    public void setFEENAME(String FEENAME) {
        this.FEENAME = FEENAME;
    }
    @JsonProperty(value="JIAOFEIR")
    public Date getJIAOFEIR() {
        return JIAOFEIR;
    }

    public void setJIAOFEIR(Date JIAOFEIR) {
        this.JIAOFEIR = JIAOFEIR;
    }
    @JsonProperty(value="RECEIPTCODE")
    public String getRECEIPTCODE() {
        return RECEIPTCODE;
    }

    public void setRECEIPTCODE(String RECEIPTCODE) {
        this.RECEIPTCODE = RECEIPTCODE;
    }
    @JsonProperty(value="RECEIPTNO")
    public String getRECEIPTNO() {
        return RECEIPTNO;
    }

    public void setRECEIPTNO(String RECEIPTNO) {
        this.RECEIPTNO = RECEIPTNO;
    }
    @JsonProperty(value="MONEY")
    public float getMONEY() {
        return MONEY;
    }

    public void setMONEY(float MONEY) {
        this.MONEY = MONEY;
    }
    @JsonProperty(value="PAYSTATE")
    public String getPAYSTATE() {
        return PAYSTATE;
    }

    public void setPAYSTATE(String PAYSTATE) {
        this.PAYSTATE = PAYSTATE;
    }
    @JsonProperty(value="CREATETIME")
    public Date getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(Date CREATETIME) {
        this.CREATETIME = CREATETIME;
    }
    @JsonProperty(value="LAWSTATUS")
    public String getLAWSTATUS() {
        return LAWSTATUS;
    }

    public void setLAWSTATUS(String LAWSTATUS) {
        this.LAWSTATUS = LAWSTATUS;
    }
    @JsonProperty(value="SHENQINGR")
    public Date getSHENQINGR() {
        return SHENQINGR;
    }

    public void setSHENQINGR(Date SHENQINGR) {
        this.SHENQINGR = SHENQINGR;
    }
    @JsonProperty(value="FAMINGRXM")
    public String getFAMINGRXM() {
        return FAMINGRXM;
    }

    public void setFAMINGRXM(String FAMINGRXM) {
        this.FAMINGRXM = FAMINGRXM;
    }
    @JsonProperty(value="SHENQINGRXM")
    public String getSHENQINGRXM() {
        return SHENQINGRXM;
    }

    public void setSHENQINGRXM(String SHENQINGRXM) {
        this.SHENQINGRXM = SHENQINGRXM;
    }
    @JsonProperty(value="CLIENTID")
    public Integer getCLIENTID() {
        return CLIENTID;
    }

    public void setCLIENTID(Integer CLIENTID) {
        this.CLIENTID = CLIENTID;
    }
    @JsonProperty(value="CITYID")
    public Integer getCITYID() {
        return CITYID;
    }

    public void setCITYID(Integer CITYID) {
        this.CITYID = CITYID;
    }
    @JsonProperty(value="PROVINCEID")
    public Integer getPROVINCEID() {
        return PROVINCEID;
    }

    public void setPROVINCEID(Integer PROVINCEID) {
        this.PROVINCEID = PROVINCEID;
    }
    @JsonProperty(value="COUNTYID")
    public Integer getCOUNTYID() {
        return COUNTYID;
    }

    public void setCOUNTYID(Integer COUNTYID) {
        this.COUNTYID = COUNTYID;
    }
    @JsonProperty(value="CLIENTNAME")
    public String getCLIENTNAME() {
        return CLIENTNAME;
    }

    public void setCLIENTNAME(String CLIENTNAME) {
        this.CLIENTNAME = CLIENTNAME;
    }
    @JsonProperty(value="GENERATE")
    public Integer getGENERATE() {
        return GENERATE;
    }

    public void setGENERATE(Integer GENERATE) {
        this.GENERATE = GENERATE;
    }
    @JsonProperty(value="DIFFDATE")
    public Integer getDIFFDATE() {
        return DIFFDATE;
    }

    public void setDIFFDATE(Integer DIFFDATE) {
        this.DIFFDATE = DIFFDATE;
    }
    @JsonProperty(value="EDITMEMO")
    public Integer getEDITMEMO() {
        return EDITMEMO;
    }

    public void setEDITMEMO(Integer EDITMEMO) {
        this.EDITMEMO = EDITMEMO;
    }
    @JsonProperty(value="MEMO")
    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    @JsonProperty(value = "DJSTATE")
    public String getDJSTATE() {
        return DJSTATE;
    }

    public void setDJSTATE(String DJSTATE) {
        this.DJSTATE = DJSTATE;
    }

    @JsonProperty(value = "PSTATE")
    public String getPSTATE() {
        return PSTATE;
    }

    public void setPSTATE(String PSTATE) {
        this.PSTATE = PSTATE;
    }

    @JsonProperty(value = "FEEPRICE")
    public Integer getFEEPRICE() { return FEEPRICE; }

    public void setFEEPRICE(Integer FEEPRICE) {
        this.FEEPRICE = FEEPRICE;
    }
}
