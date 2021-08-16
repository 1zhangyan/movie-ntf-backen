/**
 * @(#)FileController.java, 8月 16, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.constant.MovieNftServerConstant;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author zhangyan
 */
@Slf4j
@RestController
@RequestMapping(MovieNftServerConstant.API + "file")
@Api(value = "文件相关的接口" , tags = "")
public class FileController {

    @Value("${fileStorage.path}")
    String filePath;

    @PostMapping("/uopload")
    public String uploadFile(@RequestParam("file") MultipartFile file){

            String fileName = file.getOriginalFilename();  //TODO：此处的filename应该使用艺术品的name和艺术品的作者name做一个hash，保证文件的唯一性
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                return null;
            }
        return fileName;
    }

    @GetMapping("/download")
    public String downloadFile (HttpServletResponse response, @RequestParam("fileName") String fileName){
        File file = new File(filePath +'/'+ fileName);
        if(!file.exists()){
            return null;
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
        return "下载成功";
    }
}