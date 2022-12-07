package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patentpermission")
public class patentInfoPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SHENQINGH")
    private String shenqingh;
    @Column(name = "USERID")
    private Integer userid;
    @Column(name = "USERTYPE")
    private String usertype;

    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

    public String getShenqingh() {
        return shenqingh;
    }

    public void setShenqingh(String shenqingh) {
        this.shenqingh = shenqingh;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

}
