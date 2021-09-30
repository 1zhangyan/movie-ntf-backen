/**
 * @(#)MovieOprationController.java, 8月 13, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.constant.MovieNftServerConstant;
import com.pku.ss.movienftserver.dao.ArtworkInfoStorage;
import com.pku.ss.movienftserver.data.Artwork;
import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.data.enums.ArtworkStatus;
import com.pku.ss.movienftserver.service.ArtWorkService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftServerConstant.API + "artwork")
@Api(value = "艺术品相关操作接口" , tags = "")
@CrossOrigin
public class ArtworkController {
    @Autowired
    ArtworkInfoStorage artworkInfoStorage;

    @Autowired
    ArtWorkService artWorkService;

    @PostMapping("/upload")
    public int upload(String artworkName,
                          int quantity,
                          double price,
                          String publishTime,
                          String intro,
                          int artworkStatus,
                      String cover

    ){
        Artwork artwork = new Artwork();
        artwork.setArtworkName(artworkName);
        artwork.setQuantity(quantity);
        artwork.setRemainQuantity(quantity);//TODO:初次设置为全部的quantity
        artwork.setPrice(String.valueOf(price));
        artwork.setPublishTime(publishTime);
        artwork.setIntro(intro);
        artwork.setArtworkStatuss(ArtworkStatus.findByInt(artworkStatus).orElse(null));
        artwork.setCover(cover);
        artwork.setFileLink("FileLink");//TODO：转储的文件链接
        return artworkInfoStorage.addArtworkInfo(artwork);
    }

    @GetMapping("/get-artwork-info")
    public Artwork getArtworkInfo(int artworkId){
        return artworkInfoStorage.getArtworkInfoById(artworkId);
    }

    @GetMapping("/batch-get-artworks")
    public Page<Artwork> batchGetArtworks(int currentPage , int pageSize){
        return artWorkService.batchGetArtworks(currentPage , pageSize);
    }

}
