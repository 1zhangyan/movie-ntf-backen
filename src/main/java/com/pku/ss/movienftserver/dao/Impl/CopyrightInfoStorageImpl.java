/**
 * @(#)CopyrightInfoStorageImpl.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao.Impl;

import com.pku.ss.movienftserver.dao.CopyrightInfoStorage;
import com.pku.ss.movienftserver.data.enums.Copyright;
import com.pku.ss.movienftserver.data.enums.CopyrightType;
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
public class CopyrightInfoStorageImpl implements CopyrightInfoStorage {

    @Autowired
    private NamedParameterJdbcOperations db;

    private static final RowMapper<Copyright> ROW_MAPPER = (rs, rowNum) -> {
        Copyright copyright = new Copyright();
        copyright.setCopyrightId(rs.getInt("copyright_id"));
        copyright.setQuantity(rs.getInt("quantity"));
        copyright.setRemainQuantity(rs.getInt("remain_quantity"));
        copyright.setMovieId(rs.getInt("movie_id"));
        copyright.setPrice(rs.getString("price"));
        copyright.setShare(rs.getString("share"));
        copyright.setRecordNumber(rs.getString("record_number"));
        copyright.setCopyrightType(CopyrightType.findByInt(rs.getInt("copyright_type")).orElse(null));
        copyright.setPublishTime(rs.getTimestamp("publish_time"));
        return copyright;
    };



    @Override
    public int addCopyrightInfo(Copyright copyright) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("movie_id",copyright.getMovieId())
                    .addValue("record_number",copyright.getRecordNumber())
                    .addValue("share",copyright.getShare())
                    .addValue("copyright_type",copyright.getCopyrightType().toInt())
                    .addValue("quantity", copyright.getQuantity())
                    .addValue("remain_quantity" , copyright.getRemainQuantity())
                    .addValue("price", copyright.getPrice());

            String sql = "INSERT INTO `copyright_info` ( `movie_id`,`record_number`, `share`,`copyright_type`,`quantity`,`remain_quantity`,`price`) " +
                    "VALUES( :movie_id,:record_number, :share,:copyright_type,:quantity,:remain_quantity,:price)";
            KeyHolder keyHolder  = new GeneratedKeyHolder();
            db.update(sql, source , keyHolder);
            return keyHolder.getKey().intValue();
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }

    @Override
    public Copyright getCopyrightInfoById(int copyrightId) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("copyright_id", copyrightId);
            String sql = "SELECT *"  + "FROM `copyright_info` " +
                    "WHERE `copyright_id`=:copyright_id ";
            List<Copyright> result = db.query(sql, source, ROW_MAPPER);
            return result.isEmpty()?null:result.get(0);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<Copyright> batchGetCopyrightInfo(int currentPage , int pageSize){
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("start",(currentPage-1)*pageSize  )
                    .addValue("end" ,currentPage*pageSize );
            String sql = "SELECT * "  + "FROM `copyright_info` LIMIT :start , :end " ;
            return db.query(sql, source, ROW_MAPPER);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public int getCopyrightCount(){
        try{
            String sql = "SELECT COUNT(*) "  + "FROM `copyright_info` " ;
            return db.queryForObject(sql , new MapSqlParameterSource(), Integer.class);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }
}