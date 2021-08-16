/**
 * @(#)Copyright.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.data.enums;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author zhangyan
 */
@Data
public class Copyright {
    int copyrightId;
    int movieId;
    String recordNumber;
    CopyrightType copyrightType;
    String price;
    int quantity;
    int remainQuantity;
    String share;
    Timestamp publishTime;
}