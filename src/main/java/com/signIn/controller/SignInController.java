package com.signIn.controller;

import database.pojo.UserInfo;
import com.signIn.service.SignInService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by yilu on 15/8/17.
 */
@Controller
@RequestMapping("/test")
public class SignInController {
    @Resource
    private SignInService signInService;


    @RequestMapping("/signIn")
    public String recordBasicInfo(@RequestParam("userName") String userName, @RequestParam("password") String password,
                                  @RequestParam("email") String email, HttpServletRequest request, Model model){
        //insert new account
        final UserInfo userInfo=new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setStatus((byte) 0);
        String code= UUID.randomUUID().toString().replace("-","");
        userInfo.setCode(code);
        this.signInService.insertNewUser(userInfo);
        //create a thread for sending email
        Thread thread=new Thread(()->this.signInService.sendEmail(userInfo));
        //send email
        thread.start();
        return "temp";
    }

    @RequestMapping("/validate")
    public String validateEmail(String code){
        int id=this.signInService.isUserInDatabaseNotActive(code);
        if(id==0){
            return "validateError";
        }
        else{
            UserInfo userInfo=new UserInfo();
            userInfo.setUserid(id);
            userInfo.setCode("");
            userInfo.setStatus((byte) 1);
            this.signInService.active(userInfo);
            return "validateSuccess";
        }
    }
}
