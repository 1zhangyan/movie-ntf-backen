/**
 * @(#)UserInfoStorage.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao;

import com.pku.ss.movientfbacken.data.User;
import com.pku.ss.movientfbacken.data.UserType;
import org.springframework.stereotype.Repository;


/**
 * @author zhangyan
 */
@Repository
public interface UserInfoStorage {

    boolean addUserInfo(String account, String userName, String password , UserType userType);

    User getUserInfoById(int userId);

    User getUserInfoByAccount(String account);
}