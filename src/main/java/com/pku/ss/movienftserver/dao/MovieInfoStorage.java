/**
 * @(#)MovieInfoStorage.java, 8月 13, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.Movie;

/**
 * @author zhangyan
 */

public interface MovieInfoStorage {

    boolean addMovieInfo(Movie movie);

    Movie getPartMovieInfoById(int movieId);

    Movie getAllMovieInfoById(int movieId);
}