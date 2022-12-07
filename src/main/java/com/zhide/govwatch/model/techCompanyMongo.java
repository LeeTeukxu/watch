package com.zhide.govwatch.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @ClassName: TechCompanyInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2022年08月24日 9:35
 **/
@Document
public class techCompanyMongo {
    @Field(name = "ID")
    private Integer id;
    @Field(name = "Name")
    private String name;
    @Field(name = "Code")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
