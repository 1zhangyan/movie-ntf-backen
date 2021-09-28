package com.pku.ss.movienftserver.dao.Impl;

import com.pku.ss.movienftserver.dao.MovieTicketStorage;
import com.pku.ss.movienftserver.data.MovieTicket;
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

@Repository
@Slf4j
public class MovieTicketStorageImpl implements MovieTicketStorage {

    @Autowired
    private NamedParameterJdbcOperations db;

    private static final RowMapper<MovieTicket> ROW_MAPPER = (rs, rowNum) -> {
        MovieTicket movieTicket = new MovieTicket();
        movieTicket.setMovieTicketId(rs.getInt("ticket_id"));
        movieTicket.setMovieId(rs.getInt("movie_id"));
        movieTicket.setQuantity(rs.getInt("quantity"));
        movieTicket.setCover(rs.getString("cover"));
       return movieTicket;
    };

    @Override
    public int addMovieTicket(MovieTicket movieTicket){
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("movie_id", movieTicket.getMovieId())
                    .addValue("quantity" , movieTicket.getQuantity())
                    .addValue("cover", movieTicket.getCover())
                    .addValue("type", movieTicket.getType());

            String sql = "INSERT INTO `movie_ticket` ( `movie_id` , `quantity`, `cover`,`type`) " +
                    "VALUES( :movie_id, :quantity, :cover, :type)";
            KeyHolder keyHolder  = new GeneratedKeyHolder();
            db.update(sql, source , keyHolder);
            return keyHolder.getKey().intValue();

        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return -1;
        }
    }

    @Override
    public List<MovieTicket> getMovieTicketByMovieId(int movieId) {
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("movie_id", movieId);
            String sql = "SELECT * "  + "FROM `movie_ticket` " +
                    "WHERE `movie_id`=:movie_id ";
            List<MovieTicket> result = db.query(sql, source, ROW_MAPPER);
            return result;
        }catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }
}
