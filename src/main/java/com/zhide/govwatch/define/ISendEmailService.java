package com.zhide.govwatch.define;

import com.zhide.govwatch.model.EmailContent;

import java.io.InputStream;

public interface ISendEmailService {
    public void sendEmailByContent(EmailContent emailContent, InputStream inputStream, Integer UserID) throws Exception;
    public void sendTickEmailByContent(EmailContent content) throws Exception;
}
