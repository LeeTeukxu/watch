package com.zhide.govwatch.common;

public class FtpPath {

    String companyId;

    public FtpPath(String companyId) {
        this.companyId = companyId;
    }

    public String getAttachment() {
        return companyId + "/Attachment/";
    }
}
