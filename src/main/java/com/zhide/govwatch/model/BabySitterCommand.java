package com.zhide.govwatch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @ClassName: BabySitterCommand
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年08月01日 14:27
 **/
public class BabySitterCommand implements Serializable {
    Integer ID;
    String PID;
    String Code;
    String Args;
    String No;
    String Mac;
    String Name;

    @JsonProperty(value = "ID")
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @JsonProperty(value = "PID")
    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    @JsonProperty(value = "Code")
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @JsonProperty(value = "Args")
    public String getArgs() {
        return Args;
    }

    public void setArgs(String args) {
        Args = args;
    }

    @JsonProperty(value = "No")
    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    @JsonProperty(value = "Mac")
    public String getMac() {
        return Mac;
    }

    public void setMac(String mac) {
        Mac = mac;
    }

}
