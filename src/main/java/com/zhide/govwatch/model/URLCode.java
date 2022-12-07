package com.zhide.govwatch.model;

public class URLCode {
    private String urlCode;
    private String Erorr;
    private String prepayId;
    private String nonce_str;
    private String sign;

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    public String getErorr() {
        return Erorr;
    }

    public void setErorr(String erorr) {
        Erorr = erorr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
