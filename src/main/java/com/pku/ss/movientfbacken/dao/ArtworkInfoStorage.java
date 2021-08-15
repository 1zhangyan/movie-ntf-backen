/**
 * @(#)ArtworkInfoStorage.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao;

import com.pku.ss.movientfbacken.data.Artwork;
import com.pku.ss.movientfbacken.data.Movie;

/**
 * @author zhangyan
 */
public interface ArtworkInfoStorage {

    boolean addArtworkInfo(Artwork artwork);

    Artwork getArtworkInfoById(int artworkId);

}