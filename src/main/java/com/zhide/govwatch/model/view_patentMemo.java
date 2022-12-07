package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "v_PatentMemo")
public class view_patentMemo implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "mid")
    private String mid;
    @Column(name = "shenqingh")
    private String shenqingh;
    @Column(name = "memo")
    private String memo;
    @Column(name = "CreateMan")
    private String createMan;
    @Column(name = "createManName")
    private String createManName;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "updateManName")
    private String updateManName;
    @Column(name = "updateDate")
    private Date updateDate;
    @Column(name = "Edit")
    private Integer edit;
    @Column(name="ImageData")
    private String imageData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getShenqingh() {
        return shenqingh;
    }

    public void setShenqingh(String shenqingh) {
        this.shenqingh = shenqingh;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getCreateManName() {
        return createManName;
    }

    public void setCreateManName(String createManName) {
        this.createManName = createManName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateManName() {
        return updateManName;
    }

    public void setUpdateManName(String updateManName) {
        this.updateManName = updateManName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
