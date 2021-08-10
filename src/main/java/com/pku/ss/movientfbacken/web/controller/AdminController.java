/**
 * @(#)AdminController.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.web.controller;

import com.pku.ss.movientfbacken.constant.MovieNtfBackenConstant;
import com.pku.ss.movientfbacken.dao.TestUserInfoDAO;
import com.pku.ss.movientfbacken.dao.data.UserInfoDO;
import com.pku.ss.movientfbacken.proxy.TestJsonRpcProxy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangyan
 */

@Slf4j
@RestController
@RequestMapping(MovieNtfBackenConstant.API + "/admin")
@Api(value = "Admin接口", tags = "Admin接口")
public class AdminController {

    @Autowired
    private TestUserInfoDAO testUSerInfoDAO;

    @ApiOperation("testAPI")
    @GetMapping("/test")
    public List<UserInfoDO> test(int userId , String userName , String password){
        testUSerInfoDAO.addUserInfo(userId , userName ,password);
        List<UserInfoDO> result = testUSerInfoDAO.getUserInfoById(userId);
        return result;
    }
    @GetMapping("/log-test")
    public String logTest(String logInfo){
        log.info("This is an {}" , "info");
        log.warn("This is an {}" , "warn");
        log.error("This is an {}" , "error");
        return logInfo;
    }

}