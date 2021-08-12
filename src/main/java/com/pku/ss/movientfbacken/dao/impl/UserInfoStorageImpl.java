/**
 * @(#)UserInfoStorageImpl.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.dao.impl;

import com.pku.ss.movientfbacken.dao.UserInfoStorage;
import com.pku.ss.movientfbacken.data.User;
import com.pku.ss.movientfbacken.data.UserType;
import com.pku.ss.movientfbacken.utils.SecurityHelper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Repository
public class UserInfoStorageImpl implements UserInfoStorage {

    private static final String ALL_SELECT_COLS = " `user_id`, `account`, `password`,`user_name` ,`user_type` ";
    @Autowired
    private NamedParameterJdbcOperations db;

    private static final RowMapper<User> ROW_MAPPER = (rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setAccount(rs.getString("account"));
        user.setUserType(UserType.findByInt(rs.getInt("user_type")).orElse(null));
        return user;
    };

    @Override
    public boolean addUserInfo(String account, String rowPassword, String userName,  UserType userType){
        try{
            String password = SecurityHelper.getEncryptPassword(rowPassword);
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("account", account)
                    .addValue("password", password)
                    .addValue("user_name", userName)
                    .addValue("user_type" , userType.toInt());

            String sql = "INSERT INTO `user_info_test` ( `account`, `password`,`user_name`,`user_type`) " +
                    "VALUES(:account, :password, :user_name, :user_type)";
            db.update(sql, source);
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            log.error("Fail to add User info!");
            return false;
        }
        return true;
    }

    @Override
    public User getUserInfoById(int userId){
        try {
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("user_id", userId);

            String sql = "SELECT" + ALL_SELECT_COLS + "FROM `user_info_test` " +
                    "WHERE `user_id`=:user_id ";

            List<User> result =  db.query(sql, source, ROW_MAPPER);
            return result.isEmpty()?null:result.get(0);
        } catch (Throwable t){
            log.error("Fail to get User info by userId: userId is {}" , userId);
            return null;
        }
    }

    @Override
    public User getUserInfoByAccount(String account){
        try{
            SqlParameterSource source = new MapSqlParameterSource()
                    .addValue("account", account);

            String sql = "SELECT" + ALL_SELECT_COLS + "FROM `user_info_test` " +
                    "WHERE `account`=:account ";

            List<User> result =  db.query(sql, source, ROW_MAPPER);
            return result.isEmpty()?null:result.get(0);
        } catch (Throwable t){
            log.error("Fail to get User info by account: account is {}" , account);
            return null;
        }
    }

}