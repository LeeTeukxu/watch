package com.zhide.govwatch.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbwxscanresult")
public class tbWxScanResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScanExceptionID")
    private Integer scanExceptionId;
    @Column(name = "OrderNo")
    private String orderNo;
    @Column(name = "RequestCodeXML")
    private String requestCodeXML;
    @Column(name = "ScanResultXML")
    private String scanResultXML;

    public Integer getScanExceptionId() {
        return scanExceptionId;
    }

    public void setScanExceptionId(Integer scanExceptionId) {
        this.scanExceptionId = scanExceptionId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRequestCodeXML() {
        return requestCodeXML;
    }

    public void setRequestCodeXML(String requestCodeXML) {
        this.requestCodeXML = requestCodeXML;
    }

    public String getScanResultXML() {
        return scanResultXML;
    }

    public void setScanResultXML(String scanResultXML) {
        this.scanResultXML = scanResultXML;
    }
}
