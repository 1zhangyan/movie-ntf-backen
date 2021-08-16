/**
 * @(#)Movie.java; 8月 13; 2021.
 * <p>
 * Copyright 2021.
 **/
package com.pku.ss.movienftserver.data;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author zhangyan
 */
@Data
public class Movie {
    int movieId;
    String recordNumber;
    String chineseName;
    String englishName;
    String director;
    String region;
    String producer;
    String publishCompany;
    String preview;
    String post;
    String publishTime;
    Timestamp uploadTime;
    String plot;
    String intro;
}