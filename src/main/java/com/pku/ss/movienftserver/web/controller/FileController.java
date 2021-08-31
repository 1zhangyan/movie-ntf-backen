/**
 * @(#)FileController.java, 8月 16, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.constant.MovieNftServerConstant;
import com.pku.ss.movienftserver.dao.impl.FileStorageImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;



/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftServerConstant.API + "file")
@Api(value = "文件相关的接口" , tags = "")
public class FileController {

    @Autowired
    FileStorageImpl fileStorage;

    @PostMapping("/uopload-image")
    public String uploadFile(@RequestParam("file") MultipartFile file){
        return fileStorage.uploadFile(file);
    }

    @GetMapping("/download")
    public String downloadFile (HttpServletResponse response, @RequestParam("fileName") String fileName){
        return fileStorage.downloadFile(response,fileName);
    }

}