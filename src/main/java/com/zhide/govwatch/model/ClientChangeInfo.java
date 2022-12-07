package com.zhide.govwatch.model;


import java.util.Date;

/**
 * @ClassName: ClientInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年03月25日 10:25
 **/
public class ClientChangeInfo extends tbClient {
    String mode;
    Date updateTime;
    Integer updateMan;
    Date removeTime;
    Integer removeMan;
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateMan() {
        return updateMan;
    }

    public void setUpdateMan(Integer updateMan) {
        this.updateMan = updateMan;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    public Integer getRemoveMan() {
        return removeMan;
    }

    public void setRemoveMan(Integer removeMan) {
        this.removeMan = removeMan;
    }
}
