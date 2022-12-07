package com.zhide.govwatch.vo;

import com.zhide.govwatch.model.patent;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: PatentChangedInfo
 * @Author: 肖新民
 * @*TODO: 主要是用于消息传递。
 * @CreateTime: 2022年01月05日 17:19
 **/
public class PatentChangedInfo implements Serializable {
    patent content;
    String type;
    String userName;
    Date time;
    public patent getContent() {
        return content;
    }

    public void setContent(patent content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
