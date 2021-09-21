/**
 * @(#)CopyrightInfoStorage.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.enums.Copyright;

/**
 * @author zhangyan
 */
public interface CopyrightInfoStorage {

    int addCopyrightInfo(Copyright copyright);

    Copyright getCopyrightInfoById(int copyrightId);

}