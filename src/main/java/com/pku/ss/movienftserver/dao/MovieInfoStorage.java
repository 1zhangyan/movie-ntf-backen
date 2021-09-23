/**
 * @(#)MovieInfoStorage.java, 8月 13, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import com.pku.ss.movienftserver.data.Movie;
import com.pku.ss.movienftserver.data.enums.Copyright;

import java.util.List;

/**
 * @author zhangyan
 */

public interface MovieInfoStorage {

    int addMovieInfo(Movie movie);

    Movie getPartMovieInfoById(int movieId);

    Movie getAllMovieInfoById(int movieId);

    List<Movie> searchMovieInfo(String keyWord);

    List<Movie> batchGetPartMovieInfo(int currentPage , int pageSize);

    int getMovieCount();
}