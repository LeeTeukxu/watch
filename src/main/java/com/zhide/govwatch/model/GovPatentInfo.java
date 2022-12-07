package com.zhide.govwatch.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: GovPatentInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年02月04日 13:54
 **/
public class GovPatentInfo implements Serializable {
    @NotEmpty(message = "专利编号不能为空!")
    private String AppNo;
    private Date AppDate;
    @NotEmpty(message = "专利名称不能为空!")
    private String Title;
    private String Applicant;
    private String Inventors;
    private String CpqueryStatus;
    private Date UpdateTime;
    private String Agency;
    private String Agent;
    @Digits(integer = 1,fraction = 0,message = "专利类型不符合要求")
    private Integer AppType;
    private Date ProcessTime;

    public String getAppNo() {
        return AppNo;
    }

    public void setAppNo(String appNo) {
        AppNo = appNo;
    }

    public Date getAppDate() {
        return AppDate;
    }

    public void setAppDate(Date appDate) {
        AppDate = appDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String applicant) {
        Applicant = applicant;
    }

    public String getInventors() {
        return Inventors;
    }

    public void setInventors(String inventors) {
        Inventors = inventors;
    }

    public String getCpqueryStatus() {
        return CpqueryStatus;
    }

    public void setCpqueryStatus(String cpqueryStatus) {
        CpqueryStatus = cpqueryStatus;
    }

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date updateTime) {
        UpdateTime = updateTime;
    }

    public String getAgency() {
        return Agency;
    }

    public void setAgency(String agency) {
        Agency = agency;
    }

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public Integer getAppType() {
        return AppType;
    }

    public void setAppType(Integer appType) {
        AppType = appType;
    }

    public Date getProcessTime() {
        return ProcessTime;
    }

    public void setProcessTime(Date processTime) {
        ProcessTime = processTime;
    }
}
