/**
 * @(#)TestUSerInfoDAOImpl.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao.impl;

import com.pku.ss.movientfbacken.dao.TestUserInfoDAO;
import com.pku.ss.movientfbacken.dao.data.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyan
 */

@Repository
public class TestUSerInfoDAOImpl implements TestUserInfoDAO {

    private static final String ALL_SELECT_COLS = " `id`, `name`, `password` ";
    @Autowired
    private NamedParameterJdbcOperations db;


    private static final RowMapper<UserInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserId(rs.getInt("id"));
        userInfo.setUserName(rs.getString("name"));
        userInfo.setPassword(rs.getString("password"));
        return userInfo;
    };


    @Override
    public List<UserInfoDO> getUserInfoById(int userId){
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("id", userId);

        String sql = "SELECT" + ALL_SELECT_COLS + "FROM `test` " +
                "WHERE `id`=:id ";

        return db.query(sql, source, ROW_MAPPER);
    }
    @Override
    public void addUserInfo(int userId ,String userName ,String password){

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("id", userId)
                .addValue("name", userName)
                .addValue("password", password);

        String sql = "INSERT INTO `test` (`id`, `name`, `password`) " +
                "VALUES(:id, :name, :password)";

        db.update(sql, source);
    }
}