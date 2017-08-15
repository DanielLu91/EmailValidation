package com.signIn.service;
import database.pojo.UserInfo;

/**
 * Created by yilu on 15/8/17.
 */
public interface SignInService {
    void insertNewUser(UserInfo userInfo);

    void sendEmail(UserInfo userInfo);

    int isUserInDatabaseNotActive(String code);

    void active(UserInfo userInfo);
}
