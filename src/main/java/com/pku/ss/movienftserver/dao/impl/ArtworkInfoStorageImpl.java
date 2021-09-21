/**
 * @(#)ArtworkInfoStorageImpl.java, 8月 15, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao.impl;

import com.pku.ss.movienftserver.dao.ArtworkInfoStorage;
import com.pku.ss.movienftserver.data.Artwork;
import com.pku.ss.movienftserver.data.enums.ArtworkStatus;
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
public class ArtworkInfoStorageImpl implements ArtworkInfoStorage {

    @Autowired
    private NamedParameterJdbcOperations db;

    private static final RowMapper<Artwork> ROW_MAPPER = (rs, rowNum) -> {
        Artwork artwork = new Artwork();
        artwork.setArtworkId(rs.getInt("artwork_id"));
        artwork.setQuantity(rs.getInt("quantity"));
        artwork.setRemainQuantity(rs.getInt("remain_quantity"));
        artwork.setArtworkName(rs.getString("artwork_name"));
        artwork.setPrice(rs.getString("price"));
        artwork.setPublishTime(rs.getString("publish_time"));
        artwork.setCover(rs.getString("cover"));
        artwork.setFileLink(rs.getString("file_link"));
        artwork.setIntro(rs.getString("intro"));
        artwork.setArtworkStatuss(ArtworkStatus.findByInt(rs.getInt("artwork_status")).orElse(null));
       return artwork;
    };

    @Override
    public int addArtworkInfo(Artwork artwork) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("quantity", artwork.getQuantity())
                    .addValue("remain_quantity" , artwork.getRemainQuantity())
                    .addValue("price", artwork.getPrice())
                    .addValue("publish_time", artwork.getPublishTime())
                    .addValue("artwork_name", artwork.getArtworkName())
                    .addValue("cover", artwork.getCover())
                    .addValue("artwork_status", artwork.getArtworkStatuss().toInt())
                    .addValue("intro", artwork.getIntro())
                    .addValue("file_link", artwork.getFileLink());

            String sql = "INSERT INTO `artwork_info` ( `quantity`,`remain_quantity`, `price`,`publish_time`,`artwork_name`,`cover`,`artwork_status`,`intro`,`file_link`) " +
                    "VALUES( :quantity,:remain_quantity, :price,:publish_time,:artwork_name,:cover,:artwork_status,:intro,:file_link)";
            KeyHolder keyHolder  = new GeneratedKeyHolder();
            db.update(sql, source , keyHolder);
            return keyHolder.getKey().intValue();

        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }

    @Override
    public Artwork getArtworkInfoById(int artworkId) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("artwork_id", artworkId);
            String sql = "SELECT *"  + "FROM `artwork_info` " +
                    "WHERE `artwork_id`=:artwork_id ";
            List<Artwork> result = db.query(sql, source, ROW_MAPPER);
            return result.isEmpty()?null:result.get(0);
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

}