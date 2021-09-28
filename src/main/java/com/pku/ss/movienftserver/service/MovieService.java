package com.pku.ss.movienftserver.service;

import com.pku.ss.movienftserver.data.Movie;
import com.pku.ss.movienftserver.data.MovieTicket;
import com.pku.ss.movienftserver.data.Page;

public interface MovieService {
    Page<Movie> batchGetMovieBreifInfo(int currentPage , int pageSize);

    Page<Movie> searchMovieInfo(String Keyword);

}
