package com.zhide.govwatch.model;

/**
 * @ClassName: addressInfo
 * @Author: 肖新民
 * @*TODO:
 * @CreateTime: 2021年12月08日 9:44
 **/
public class addressInfo {
    String province;
    String city;
    String county;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
