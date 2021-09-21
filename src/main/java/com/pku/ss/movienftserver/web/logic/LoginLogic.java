/**
 * @(#)LoginLogic.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.logic;

import com.pku.ss.movienftserver.dao.UserInfoStorage;
import com.pku.ss.movienftserver.data.User;
import com.pku.ss.movienftserver.data.enums.UserType;
import com.pku.ss.movienftserver.utils.SecurityHelper;
import com.pku.ss.movienftserver.utils.TokenUtils;
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

    public int register(String account, String userName, String password, UserType userType){
        if(userType == null)
            return -1;
        return userInfoStorage.addUserInfo(account,userName,password,userType);
    }
}