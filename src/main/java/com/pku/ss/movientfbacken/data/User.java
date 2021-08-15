/**
 * @(#)User.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.data;

import com.pku.ss.movientfbacken.data.enums.UserType;
import lombok.Data;

/**
 * @author zhangyan
 */

@Data
public class User {
    int userId;
    String userName;
    String password;
    String account;
    UserType userType;
}