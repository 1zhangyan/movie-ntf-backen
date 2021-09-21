/**
 * @(#)UserInfoStorage.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.User;
import com.pku.ss.movienftserver.data.enums.UserType;
import org.springframework.stereotype.Repository;


/**
 * @author zhangyan
 */
@Repository
public interface UserInfoStorage {

    int addUserInfo(String account, String userName, String password , UserType userType);

    User getUserInfoById(int userId);

    User getUserInfoByAccount(String account);
}