package com.zhide.govwatch.model;

import java.io.Serializable;

public class clientAndDep implements Serializable {
    private Integer id;
    private String code;
    private String name;
    private Integer depId;

    public clientAndDep(){}
    public clientAndDep(Integer id,String code,String name,Integer depId){
        this.id=id;
        this.code=code;
        this.name=name;
        this.depId=depId;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }
}
