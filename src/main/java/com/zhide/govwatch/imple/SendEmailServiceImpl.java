package com.zhide.govwatch.imple;

import com.zhide.govwatch.common.CompanyContext;
import com.zhide.govwatch.common.CompanyPathUtils;
import com.zhide.govwatch.common.FTPUtil;
import com.zhide.govwatch.define.ISendEmailService;
import com.zhide.govwatch.model.EmailContent;
import com.zhide.govwatch.model.LoginUserInfo;
import com.zhide.govwatch.model.TextAndValue;
import com.zhide.govwatch.model.smtpAccount;
import com.zhide.govwatch.repository.smtpAccountRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.xml.soap.Text;
import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SendEmailServiceImpl implements ISendEmailService {
    @Autowired
    JavaMailSender mailSender;
    JavaMailSenderImpl instance;
    @Autowired
    smtpAccountRepository smtpRep;

    LoginUserInfo currentUser;
    smtpAccount currentSmtp;

    @Override
    public void sendEmailByContent(EmailContent emailContent, InputStream inputStream, Integer UserID) throws Exception {
        System.getProperties().setProperty("mail.mime.splitlongparameters","false");
        changeToCurrentUserAccount(UserID);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper hepler = new MimeMessageHelper(message, true, "utf-8");
        addSenderToInfo(hepler, emailContent);
        addReceiveInfo(hepler, emailContent);
        addAttachment(hepler, emailContent, inputStream);
        String htmlText = URLDecoder.decode(emailContent.getContent(), "utf-8");
        hepler.setText(htmlText);
        hepler.setSubject(emailContent.getSubject());

        mailSender.send(message);
    }

    @Override
    public void sendTickEmailByContent(EmailContent content) throws Exception {
        System.getProperties().setProperty("mail.mime.splitlongparameters","false");
        changeTicketToCurrentUserAccount();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
        addSenderToInfo(helper, content);
        addReceiveInfo(helper, content);
        addTicketAttachment(helper, content);
        String htmlText = URLDecoder.decode(content.getContent(), "utf-8");
        helper.setText(htmlText, true);
        helper.setSubject(content.getSubject());

        mailSender.send(message);
    }

    private void changeToCurrentUserAccount(Integer UserID) throws Exception{
        currentSmtp = smtpRep.getByUserId(UserID);
        if (currentSmtp == null){
            Optional<smtpAccount> findOnes = smtpRep.findFirstByCompanyDefaultIsTrue();
            if (findOnes.isPresent()){
                currentSmtp = findOnes.get();
            }
        }
        instance = (JavaMailSenderImpl) mailSender;
        if (currentSmtp != null){
            instance.setUsername(currentSmtp.getUserName());
            instance.setPassword(currentSmtp.getPassword());
            instance.setHost(currentSmtp.getServer());
            instance.setPort(currentSmtp.getPort());
        }else throw new Exception("请设置个人的邮箱信息，否则无法发送邮件!");
    }

    private void changeTicketToCurrentUserAccount() throws Exception {
        currentUser = CompanyContext.get();
        int userId = currentUser.getUserId();
        currentSmtp = smtpRep.getByUserId(userId);
        if (currentSmtp == null) {
            Optional<smtpAccount> findOnes=smtpRep.findFirstByCompanyDefaultIsTrue();
            if(findOnes.isPresent()){
                currentSmtp=findOnes.get();
            }
        }
        instance = (JavaMailSenderImpl) mailSender;
        if (currentSmtp != null) {
            instance.setUsername(currentSmtp.getUserName());
            instance.setPassword(currentSmtp.getPassword());
            instance.setHost(currentSmtp.getServer());
            instance.setPort(currentSmtp.getPort());
        } else throw new Exception("请设置个人的邮箱信息，否则无法发送邮件!");
    }

    private void addSenderToInfo(MimeMessageHelper message, EmailContent content) throws Exception {
        if (currentSmtp != null && Strings.isEmpty(currentSmtp.getUserName()) == false){
            message.setFrom(currentSmtp.getUserName(), currentSmtp.getNickName());
        }else message.setFrom(instance.getUsername(), currentSmtp.getUserName());
    }

    private void addReceiveInfo(MimeMessageHelper message, EmailContent content) throws Exception {
        List<TextAndValue> vs = content.getReceAddress();
        for (int i=0;i<vs.size();i++){
            TextAndValue V = vs.get(i);
            InternetAddress address = new InternetAddress();
            address.setAddress(V.getValue());
            address.setPersonal(V.getText());
            message.addCc(address);
        }
    }

    private void addAttachment(MimeMessageHelper message, EmailContent content, InputStream inputStream) throws Exception {
        List<TextAndValue> atts = content.getAttachments();
        if (atts.size() > 0){
            for (int i=0;i<atts.size();i++){
                TextAndValue value = atts.get(i);
                String fileName = value.getText();
                message.addAttachment(fileName,new ByteArrayResource(IOUtils.toByteArray(inputStream)));
            }
        }
    }
    private void addTicketAttachment(MimeMessageHelper message, EmailContent content) throws Exception {
        List<TextAndValue> atts = content.getAttachments();
        if (atts.size() > 0) {
            FTPUtil F = new FTPUtil();
            if (F.connect() == true) {
                for (int i = 0; i < atts.size(); i++) {
                    TextAndValue value = atts.get(i);
                    String fileName = value.getText();
                    String filePath = value.getValue();

                    if (F.existFile(filePath) == true) {
                        String newFileName = CompanyPathUtils.getFullPath("Temp", UUID.randomUUID().toString()+".zip");
                        F.download(filePath, newFileName);
                        File fx = new File(newFileName);
                        if (fx.exists() == true) {
                            message.addAttachment(MimeUtility.encodeText(StringUtils.trim(fileName),"utf-8","B"), fx);
                        }
                    }
                }
            }
        }
    }

}
