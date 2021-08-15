/**
 * @(#)LoginController.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.web.controller;

import com.pku.ss.movientfbacken.annotation.PassToken;
import com.pku.ss.movientfbacken.annotation.UserLoginToken;
import com.pku.ss.movientfbacken.constant.MovieNtfBackenConstant;
import com.pku.ss.movientfbacken.data.enums.UserType;
import com.pku.ss.movientfbacken.web.logic.LoginLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNtfBackenConstant.API)
@Api(value = "用户登陆注册API" , tags = "")
public class LoginController {

    @Autowired
    private LoginLogic loginLogic;

    @PassToken
    @GetMapping("/login")
    @ApiOperation("用户登陆接口")
    public String login(String account, String password , int userType){
        return loginLogic.login(account, password, UserType.findByInt(userType).orElse(null));
    }

    @PassToken
    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public boolean register(String account, String userName, String password, int userType){
        return loginLogic.register(account,userName,password,UserType.findByInt(userType).orElse(null));
    }

    @UserLoginToken
    @GetMapping("/test-token")
    @ApiOperation("测试Token接口")
    public String testToken(){
        return "OJBK";
    }
}