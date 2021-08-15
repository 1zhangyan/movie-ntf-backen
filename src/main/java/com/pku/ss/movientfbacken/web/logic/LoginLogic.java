/**
 * @(#)LoginLogic.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.web.logic;

import com.pku.ss.movientfbacken.dao.UserInfoStorage;
import com.pku.ss.movientfbacken.data.User;
import com.pku.ss.movientfbacken.data.enums.UserType;
import com.pku.ss.movientfbacken.utils.SecurityHelper;
import com.pku.ss.movientfbacken.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhangyan
 */
@Service
public class LoginLogic {

    @Autowired
    UserInfoStorage userInfoStorage;

    public String login(String account, String password , UserType userType) {

        User user = userInfoStorage.getUserInfoByAccount(account);

        if(user == null || !SecurityHelper.verifyPassword(password ,user.getPassword()) || userType != user.getUserType()){
            throw new RuntimeException("登陆失败");
        }

        return TokenUtils.getToken(user.getUserId() , user.getUserName());
    }

    public Boolean register(String account, String userName, String password, UserType userType){
        if(userType == null)
            return false;
        return userInfoStorage.addUserInfo(account,userName,password,userType);
    }
}