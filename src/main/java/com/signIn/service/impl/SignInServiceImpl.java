package com.signIn.service.impl;

import database.dao.UserInfoMapper;
import database.pojo.UserInfo;
import com.signIn.service.SignInService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by yilu on 15/8/17.
 */
@Service("signInService")
public class SignInServiceImpl implements SignInService{
    @Resource
    private UserInfoMapper userInfoMapper;

    public final static String FROM="daniellu19911115@gmail.com";
    public final static String HOST="smtp.gmail.com";
    public final static String USERNAME="daniellu19911115@gmail.com";
    public final static String PASSWORD="luyi456852yucai";
    public final static String SUBJECT="E-mail validation";

    public void insertNewUser(UserInfo userInfo) {
        this.userInfoMapper.insertSelective(userInfo);
    }


    public int isUserInDatabaseNotActive(String code){
        Integer res=userInfoMapper.selectPrimaryKeyByCode(code);
        if(res==null)
            return 0;
        else
            return res;
    }

    public void sendEmail(UserInfo userInfo) {
        String to="345982895@qq.com";
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,PASSWORD);
            }
        });
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(SUBJECT);
            String text="<!DOCTYPE HTML>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<title></title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "  <h1>Please validate you account</h1>\n" +
                    "  <a href=\"http://localhost:8080/test/validate?code="+userInfo.getCode()+"\">http://localhost:8080/test/validate?code="+userInfo.getCode()+"</a>\n" +
                    "</body>";
            message.setContent(text,"text/html");

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void active(UserInfo userInfo) {
        this.userInfoMapper.updateByPrimaryKey(userInfo);
    }
}
