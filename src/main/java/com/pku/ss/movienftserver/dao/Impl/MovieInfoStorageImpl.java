/**
 * @(#)MovieInfoStorageImpl.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pku.ss.movienftserver.dao.Impl;

import com.pku.ss.movienftserver.dao.MovieInfoStorage;
import com.pku.ss.movienftserver.data.Movie;
import com.pku.ss.movienftserver.data.enums.Copyright;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyan
 */
@Repository
@Slf4j
public class MovieInfoStorageImpl  implements MovieInfoStorage {

    @Autowired
    private NamedParameterJdbcOperations db;

    private static final RowMapper<Movie> ROW_MAPPER_ALL = (rs, rowNum) -> {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setDirector(rs.getString("director"));
        movie.setChineseName(rs.getString("chinese_name"));
        movie.setEnglishName(rs.getString("english_name"));
        movie.setRecordNumber(rs.getString("record_number"));
        movie.setRegion(rs.getString("region"));
        movie.setPublishCompany(rs.getString("publish_company"));
        movie.setPublishTime(rs.getString("publish_time"));
        movie.setPlot(rs.getString("plot"));
        movie.setIntro(rs.getString("intro"));
        movie.setPost(rs.getString("post"));
        movie.setPreview(rs.getString("preview"));
        movie.setProducer(rs.getString("producer"));
        movie.setUploadTime(rs.getTimestamp("upload_time"));
        return movie;
    };

    private static final RowMapper<Movie> ROW_MAPPER_PART = (rs, rowNum) -> {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movie_id"));
        movie.setDirector(rs.getString("director"));
        movie.setChineseName(rs.getString("chinese_name"));
        movie.setEnglishName(rs.getString("english_name"));
        movie.setRecordNumber(rs.getString("record_number"));
        movie.setRegion(rs.getString("region"));
        movie.setPreview(rs.getString("preview"));
        movie.setPublishTime(rs.getString("publish_time"));
        return movie;
    };

    @Override
    public int addMovieInfo(Movie movie) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("director", movie.getDirector())
                    .addValue("chinese_name", movie.getChineseName())
                    .addValue("english_name", movie.getEnglishName())
                    .addValue("record_number", movie.getRecordNumber())
                    .addValue("region", movie.getRegion())
                    .addValue("publish_company", movie.getPublishCompany())
                    .addValue("publish_time", movie.getPublishTime())
                    .addValue("plot", movie.getPlot())
                    .addValue("intro", movie.getIntro())
                    .addValue("post", movie.getPost())
                    .addValue("preview", movie.getPreview())
                    .addValue("producer", movie.getProducer());

            String sql = "INSERT INTO `movie_info` ( `director`, `chinese_name`,`english_name`,`record_number`,`region`,`publish_company`,`publish_time`,`plot`,`intro`,`post`,`preview`,`producer`) " +
                    "VALUES( :director, :chinese_name,:english_name,:record_number,:region,:publish_company,:publish_time,:plot,:intro,:post,:preview,:producer)";
            KeyHolder keyHolder  = new GeneratedKeyHolder();
            db.update(sql, source , keyHolder);
            return keyHolder.getKey().intValue();
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }

    @Override
    public Movie getPartMovieInfoById(int movieId) {
        try {
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("movie_id", movieId);
            String sql = "SELECT movie_id , director , chinese_name , english_name , record_number , region , preview ,publish_time "
                    + "FROM `movie_info` "
                    + "WHERE `movie_id`=:movie_id ";
            List<Movie> result = db.query(sql, source, ROW_MAPPER_PART);
            return result.isEmpty()?null:result.get(0);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public Movie getAllMovieInfoById(int movieId) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("movie_id", movieId);
            String sql = "SELECT *"  + "FROM `movie_info` " +
                    "WHERE `movie_id`=:movie_id ";
            List<Movie> result = db.query(sql, source, ROW_MAPPER_ALL);
            return result.isEmpty()?null:result.get(0);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<Movie> searchMovieInfo(String keyWord){
        try {
            String sql = "SELECT movie_id , director , chinese_name , english_name , record_number , region , preview ,publish_time "
                    + "FROM `movie_info` " +
                    "WHERE chinese_name like \"%" +keyWord+"%\" ";
            List<Movie> result = db.query(sql, ROW_MAPPER_PART);
            return result;
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<Movie> batchGetPartMovieInfo(int currentPage , int pageSize){
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("start",(currentPage-1)*pageSize  )
                    .addValue("end" ,currentPage*pageSize );
            String sql = "SELECT movie_id , director , chinese_name , english_name , record_number , region , preview ,publish_time "
                    + "FROM `movie_info`"
                    + " LIMIT :start , :end " ;
            return db.query(sql, source, ROW_MAPPER_PART);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public int getMovieCount(){
        try{
            String sql = "SELECT COUNT(*) "  + "FROM `movie_info` " ;
            return db.queryForObject(sql , new MapSqlParameterSource(), Integer.class);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }
}