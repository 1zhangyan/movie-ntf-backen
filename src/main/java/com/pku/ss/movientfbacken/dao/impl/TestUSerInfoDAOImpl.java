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

    private static final String ALL_SELECT_COLS = " `user_id`, `user_name`, `password` ";
    @Autowired
    private NamedParameterJdbcOperations db;


    private static final RowMapper<UserInfoDO> ROW_MAPPER = (rs, rowNum) -> {
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setUserId(rs.getInt("user_id"));
        userInfo.setUserName(rs.getString("user_name"));
        userInfo.setPassword(rs.getString("password"));
        return userInfo;
    };


    @Override
    public List<UserInfoDO> getUserInfoById(int userId){
        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", userId);

        String sql = "SELECT" + ALL_SELECT_COLS + "FROM `user_info` " +
                "WHERE `user_id`=:user_id ";

        return db.query(sql, source, ROW_MAPPER);
    }
    @Override
    public void addUserInfo(int userId ,String userName ,String password){

        SqlParameterSource source = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("user_name", userName)
                .addValue("password", password);

        String sql = "INSERT INTO `user_info` (`user_id`, `user_name`, `password`) " +
                "VALUES(:user_id, :user_name, :password)";

        db.update(sql, source);
    }
}