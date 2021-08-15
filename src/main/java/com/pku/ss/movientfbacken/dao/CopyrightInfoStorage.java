/**
 * @(#)CopyrightInfoStorage.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao;

import com.pku.ss.movientfbacken.data.Artwork;
import com.pku.ss.movientfbacken.data.enums.Copyright;

/**
 * @author zhangyan
 */
public interface CopyrightInfoStorage {

    boolean addCopyrightInfo(Copyright copyright);

    Copyright getCopyrightInfoById(int copyrightId);

}