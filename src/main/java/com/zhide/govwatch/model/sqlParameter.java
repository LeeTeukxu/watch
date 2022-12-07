package com.zhide.govwatch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sqlParameter implements Serializable {
    String id;
    String field;
    String oper;
    String value;
    String relation;
    List<sqlParameter> children;
    public sqlParameter(){
        children=new ArrayList<>();
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<sqlParameter> getChildren() {
        return children;
    }

    public void setChildren(List<sqlParameter> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
