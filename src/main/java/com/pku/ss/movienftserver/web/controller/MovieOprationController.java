/**
 * @(#)MovieOprationController.java, 8月 13, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.constant.MovieNftServerConstant;
import com.pku.ss.movienftserver.dao.CopyrightInfoStorage;
import com.pku.ss.movienftserver.dao.MovieInfoStorage;
import com.pku.ss.movienftserver.dao.MovieTicketStorage;
import com.pku.ss.movienftserver.data.Movie;
import com.pku.ss.movienftserver.data.MovieTicket;
import com.pku.ss.movienftserver.data.Page;
import com.pku.ss.movienftserver.data.enums.Copyright;
import com.pku.ss.movienftserver.data.enums.CopyrightType;
import com.pku.ss.movienftserver.service.CopyrightService;
import com.pku.ss.movienftserver.service.MovieService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftServerConstant.API + "movie")
@Api(value = "电影相关操作接口" , tags = "")
@CrossOrigin
public class MovieOprationController {

    @Autowired
    MovieInfoStorage movieInfoStorage;

    @Autowired
    CopyrightInfoStorage copyrightInfoStorage;

    @Autowired
    MovieService movieService;

    @Autowired
    CopyrightService copyrightService;

    @Autowired
    MovieTicketStorage movieTicketStorage;

    @PostMapping("/upload-movie")
    public int uploadMovieInfo(@RequestParam String recordNumber,
                          @RequestParam String chineseName,
                          @RequestParam String englishName,
                          @RequestParam String director,
                          @RequestParam String region,
                          @RequestParam String producer,
                          @RequestParam String publishCompany,
                          @RequestParam String publishTime,
                          @RequestParam String plot,
                          @RequestParam String intro,
                          @RequestParam String post,
                          @RequestParam String preview
    ){
        Movie movie = new Movie();
        movie.setRecordNumber(recordNumber);
        movie.setChineseName(chineseName);
        movie.setEnglishName(englishName);
        movie.setDirector(director);
        movie.setRegion(region);
        movie.setPublishTime(publishTime);
        movie.setPublishCompany(publishCompany);
        movie.setPlot(plot);
        movie.setIntro(intro);
        movie.setProducer(producer);
        movie.setPost(post);
        movie.setPreview(preview);
        return movieInfoStorage.addMovieInfo(movie);
    }

    @GetMapping("/movie-brief-info")
    public Movie getMovieBriefInfo(@RequestParam int movieId){
        return movieInfoStorage.getPartMovieInfoById(movieId);
    }

    @GetMapping("/movie-info")
    public Movie getMovieInfo(@RequestParam int movieId){
        return movieInfoStorage.getAllMovieInfoById(movieId);
    }

    @PostMapping("/upload-copyright")
    public int uploadCopyright(@RequestParam int movieId,
                                   @RequestParam int copyrightType,
                                   @RequestParam double price,
                                   @RequestParam int quantity,
                                   @RequestParam double share,
                                    @RequestParam String recordNumber
    ){
     Copyright copyright = new Copyright();
        copyright.setMovieId(movieId);
        copyright.setShare(String.valueOf(share));
        copyright.setRecordNumber(recordNumber);
        copyright.setCopyrightType(CopyrightType.findByInt(copyrightType).orElse(null));
        copyright.setPrice(String.valueOf(price));
        copyright.setQuantity(quantity);
        copyright.setRemainQuantity(quantity);//TODO:初次设置为满
     return copyrightInfoStorage.addCopyrightInfo(copyright);
    }

    @GetMapping("/copyright-info")
    public Copyright getCopyrightInfo(@RequestParam int copyrightId) {
        return copyrightInfoStorage.getCopyrightInfoById(copyrightId);
    }

    @GetMapping("/search")
    public Page<Movie> search(@RequestParam (defaultValue = " ")String keyWord  ) {
        return movieService.searchMovieInfo(keyWord.trim());
    }

    @GetMapping("/batch-get-movies")
    public Page<Movie> batchGetMovies(int currentPage , int pageSize) {
        return movieService.batchGetMovieBreifInfo(currentPage , pageSize);
    }

    @PostMapping("/upload-movie-ticket")
    public int uploadMovieTicket(int movieId,
                                 int quantity,
                                 int type,
                                 String cover
                                ){
        MovieTicket movieTicket   = new MovieTicket();
        movieTicket.setMovieId(movieId);
        movieTicket.setCover(cover);
        movieTicket.setQuantity(quantity);
        movieTicket.setType(type);
        return movieTicketStorage.addMovieTicket(movieTicket);
    }

    @GetMapping("/movie-ticket")
    public List<MovieTicket> getMovieTicket(int movieId){
        return movieTicketStorage.getMovieTicketByMovieId(movieId);
    }

}
