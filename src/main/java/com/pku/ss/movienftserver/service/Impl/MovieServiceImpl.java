package com.pku.ss.movienftserver.service.Impl;

import com.pku.ss.movienftserver.dao.MovieInfoStorage;
import com.pku.ss.movienftserver.data.Movie;
import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieInfoStorage movieInfoStorage;

    @Override
    public Page<Movie> batchGetMovieBreifInfo(int currentPage, int pageSize) {
        Page<Movie> page = new Page<>();
        page.setTotalNum(movieInfoStorage.getMovieCount());
        page.setPageCount((page.getTotalNum()%pageSize==0?0:1) + page.getTotalNum()/pageSize);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setPageInfo(movieInfoStorage.batchGetPartMovieInfo(currentPage , pageSize));
        return page;
    }
    @Override
    public Page<Movie> searchMovieInfo(String keywords){
        List<Movie> movies= movieInfoStorage.searchMovieInfo(keywords);
        Page<Movie> page = new Page<>();
        page.setTotalNum(movies.size());
        page.setPageInfo(movies);
        return page;
    }
}
