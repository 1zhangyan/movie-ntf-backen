/**
 * @(#)Artwork.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.data;

import com.pku.ss.movientfbacken.data.enums.ArtworkStatus;
import lombok.Data;

/**
 * @author zhangyan
 */

@Data
public class Artwork {

    //需要hash的信息
    int artworkId;
    int quantity;
    int remainQuantity;
    String price;
    String artworkName;
    String publishTime;
    String hashCode;

    //附加信息
    String cover;
    String fileLink;
    String uploadTime;
    String intro;
    ArtworkStatus artworkStatuss;
}