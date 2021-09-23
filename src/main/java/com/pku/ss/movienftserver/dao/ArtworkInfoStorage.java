/**
 * @(#)ArtworkInfoStorage.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.Artwork;
import com.pku.ss.movienftserver.data.Page;

import java.util.List;

/**
 * @author zhangyan
 */
public interface ArtworkInfoStorage {

    int addArtworkInfo(Artwork artwork);

    Artwork getArtworkInfoById(int artworkId);

    List<Artwork> batchGetArtworkInfo(int currentPage , int pageSize);

    int getArtWorkCount();
}