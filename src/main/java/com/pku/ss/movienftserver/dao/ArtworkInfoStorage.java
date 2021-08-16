/**
 * @(#)ArtworkInfoStorage.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.Artwork;

/**
 * @author zhangyan
 */
public interface ArtworkInfoStorage {

    boolean addArtworkInfo(Artwork artwork);

    Artwork getArtworkInfoById(int artworkId);

}