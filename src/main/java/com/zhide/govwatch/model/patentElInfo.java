package com.zhide.govwatch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: patentElInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月20日 14:25
 **/
@Document(indexName = "watch")
public class patentElInfo implements Serializable {
    @Id
    @Field(type = FieldType.Keyword)
    String shenqingh;
    @Field(type = FieldType.Wildcard)
    String famingmc;
    @Field(type = FieldType.Wildcard)
    String memo;
    @Field(type=FieldType.Keyword)
    String shenqinglx;
    @Field(type = FieldType.Wildcard)
    String address;
    @Field(type = FieldType.Wildcard)
    String countyname;
    @Field(type = FieldType.Date)
    Date shenqingr;
    @Field(type = FieldType.Wildcard)
    String shenqingrxm;
    @Field(type = FieldType.Wildcard)
    String pipc;
    @Field(type = FieldType.Wildcard)
    private String dailijgdm;
    @Field(type = FieldType.Wildcard)
    private String dailijgmc;
    @Field(type = FieldType.Wildcard)
    private String dailirxm;
    @Field(type = FieldType.Wildcard)
    private String lawstatus;
    @Field(type = FieldType.Wildcard)
    private String seclawstatus;
    @Field(type = FieldType.Wildcard)
    private String famingrxm;

    private String jkstatus;

    //是否是分词字段。
    public static boolean isSegmentFields(String field) {
        List<String> words = Arrays.asList("");
        return words.contains(field);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShenqingh() {
        return shenqingh;
    }

    public void setShenqingh(String shenqingh) {
        this.shenqingh = shenqingh;
    }

    public String getFamingmc() {
        return famingmc;
    }

    public void setFamingmc(String famingmc) {
        this.famingmc = famingmc;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public Date getShenqingr() {
        return shenqingr;
    }

    public void setShenqingr(Date shenqingr) {
        this.shenqingr = shenqingr;
    }

    public String getShenqingrxm() {
        return shenqingrxm;
    }

    public void setShenqingrxm(String shenqingrxm) {
        this.shenqingrxm = shenqingrxm;
    }

    public String getPipc() {
        return pipc;
    }

    public void setPipc(String pipc) {
        this.pipc = pipc;
    }

    public String getDailijgdm() {
        return dailijgdm;
    }

    public void setDailijgdm(String dailijgdm) {
        this.dailijgdm = dailijgdm;
    }

    public String getDailijgmc() {
        return dailijgmc;
    }

    public void setDailijgmc(String dailijgmc) {
        this.dailijgmc = dailijgmc;
    }

    public String getDailirxm() {
        return dailirxm;
    }

    public void setDailirxm(String dailirxm) {
        this.dailirxm = dailirxm;
    }

    public String getLawstatus() {
        return lawstatus;
    }

    public void setLawstatus(String lawstatus) {
        this.lawstatus = lawstatus;
    }

    public String getSeclawstatus() {
        return seclawstatus;
    }

    public void setSeclawstatus(String seclawstatus) {
        this.seclawstatus = seclawstatus;
    }

    public String getShenqinglx() {
        return shenqinglx;
    }

    public void setShenqinglx(String shenqinglx) {
        this.shenqinglx = shenqinglx;
    }

    public String getJkstatus(){return jkstatus;}

    public void setJkstatus(String jkstatus){this.jkstatus = jkstatus;}

    public String getFamingrxm() {
        return famingrxm;
    }

    public void setFamingrxm(String famingrxm) {
        this.famingrxm = famingrxm;
    }
}
