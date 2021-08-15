/**
 * @(#)MovieOprationController.java, 8月 13, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.web.controller;

import com.pku.ss.movientfbacken.constant.MovieNtfBackenConstant;
import com.pku.ss.movientfbacken.dao.ArtworkInfoStorage;
import com.pku.ss.movientfbacken.data.Artwork;
import com.pku.ss.movientfbacken.data.enums.ArtworkStatus;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNtfBackenConstant.API + "artwork")
@Api(value = "艺术品相关操作接口" , tags = "")
public class ArtworkController {
    @Autowired
    ArtworkInfoStorage artworkInfoStorage;

    @PostMapping("/upload")
    public boolean upload(String artworkName,
                          int quantity,
                          double price,
                          String publishTime,
                          String intro,
                          int artworkStatus
    ){
        Artwork artwork = new Artwork();
        artwork.setArtworkName(artworkName);
        artwork.setQuantity(quantity);
        artwork.setRemainQuantity(quantity);//TODO:初次设置为全部
        artwork.setPrice(String.valueOf(price));
        artwork.setPublishTime(publishTime);
        artwork.setIntro(intro);
        artwork.setArtworkStatuss(ArtworkStatus.findByInt(artworkStatus).orElse(null));
        artwork.setCover("Cover");//TODO：
        artwork.setFileLink("FileLink");//TODO：
        //artwork.setHashCode();//TODO：
        return artworkInfoStorage.addArtworkInfo(artwork);
    }

    @GetMapping("/get-artwork-info")
    public Artwork getArtworkInfo(int artworkId){
        return artworkInfoStorage.getArtworkInfoById(artworkId);
    }


}
