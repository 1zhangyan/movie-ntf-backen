/**
 * @(#)TestUserInfoDAO.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao;

/**
 * @author zhangyan
 */

import com.pku.ss.movientfbacken.dao.data.UserInfoDO;

import java.util.List;

public interface TestUserInfoDAO {
    void addUserInfo(int userId , String userName , String password);
    List<UserInfoDO> getUserInfoById(int Id);
}