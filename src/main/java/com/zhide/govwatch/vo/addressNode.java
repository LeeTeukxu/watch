package com.zhide.govwatch.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: addressNode
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月08日 16:06
 **/
public class addressNode implements Serializable {
    String name;
    String code;
    List<addressNode> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<addressNode> getChildren() {
        return children;
    }

    public void setChildren(List<addressNode> children) {
        this.children = children;
    }
}
