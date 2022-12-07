package com.zhide.govwatch.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: guanFamingExcel
 * @Author: 肖新民
 * @*TODO:官方发明数据
 * @CreateTime: 2022年03月10日 10:31
 **/
public class guanFamingExcel {
    @ExcelProperty(index = 0, value = "专利编号")
    private String shenqingh;
    @ExcelProperty(index = 1, value = "发明名称")
    private String famingmc;
    @ExcelProperty(index = 2, value = "申请人")
    private String shenqingrxm;
    @ExcelProperty(index = 3, value = "申请日", format = "yyyy-MM-dd")
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date shenqingr;
    @ExcelProperty(index = 4, value = "授权日", format = "yyyy-MM-dd")
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date shouquanr;
    @ExcelProperty(index = 5, value = "申请人邮编")
    private String postcode;
    @ExcelProperty(index = 6, value = "当前地址")
    private String address;
    @ExcelProperty(index = 12, value = "专利类型")
    private String shenqinglx;
    @ExcelProperty(index = 18, value = "省份")
    private String provinceName;
    @ExcelProperty(index = 23, value = "城市")
    private String cityName;
    @ExcelProperty(index = 28, value = "IPC")
    private String ipc;
    @ExcelProperty(index = 29, value = "代理机构名称")
    private String dailijgmc;
    @ExcelIgnore
    private String priaddress;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private String countyName;
    @ExcelIgnore
    private String clientName;
    @ExcelIgnore
    private String prishenqingrxm;

    public String getShenqingh() {
        return shenqingh;
    }

    public void setShenqingh(String shenqingh) {
        this.shenqingh = shenqingh;
    }

    public String getFamingmc() {
        return famingmc;
    }

    public void setFamingmc(String famingmc) {
        this.famingmc = famingmc;
    }

    public String getShenqingrxm() {
        return shenqingrxm;
    }

    public void setShenqingrxm(String shenqingrxm) {
        this.shenqingrxm = shenqingrxm;
    }

    public Date getShenqingr() {
        return shenqingr;
    }

    public void setShenqingr(Date shenqingr) {
        this.shenqingr = shenqingr;
    }

    public Date getShouquanr() {
        return shouquanr;
    }

    public void setShouquanr(Date shouquanr) {
        this.shouquanr = shouquanr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShenqinglx() {
        return shenqinglx;
    }

    public void setShenqinglx(String shenqinglx) {
        this.shenqinglx = shenqinglx;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getIpc() {
        return ipc;
    }

    public void setIpc(String ipc) {
        this.ipc = ipc;
    }

    public String getDailijgmc() {
        return dailijgmc;
    }

    public void setDailijgmc(String dailijgmc) {
        this.dailijgmc = dailijgmc;
    }


    public String getPriaddress() {
        return priaddress;
    }

    public void setPriaddress(String priaddress) {
        this.priaddress = priaddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPrishenqingrxm() {
        return prishenqingrxm;
    }

    public void setPrishenqingrxm(String prishenqingrxm) {
        this.prishenqingrxm = prishenqingrxm;
    }

}
