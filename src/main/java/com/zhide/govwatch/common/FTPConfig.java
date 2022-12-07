package com.zhide.govwatch.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FTPConfig {
    @Value("${spring.ftp.password}")
    private String password;
    @Value("${spring.ftp.username}")
    private String username;
    @Value("${spring.ftp.port}")
    private String port;
    @Value("${spring.ftp.host}")
    private String host;

    public String getLocalFilePath() {
        return "";
    }

    public String getFTPEncode() {
        return "utf-8";
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return username;
    }

    public String getFTPPort() {
        return port;
    }

    public String getFTPHost() {
        return host;
    }
}
