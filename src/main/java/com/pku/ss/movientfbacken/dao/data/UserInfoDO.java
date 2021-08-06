/**
 * @(#)UserInfoDO.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao.data;

import lombok.Data;

/**
 * @author zhangyan
 */


@Data
public class UserInfoDO {
    private int userId;
    private String userName;
    private String password;
}