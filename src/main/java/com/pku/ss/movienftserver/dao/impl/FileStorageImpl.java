/**
 * @(#)FileStorageImpl.java, 8月 27, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pku.ss.movienftserver.dao.impl;

import com.pku.ss.movienftserver.dao.FileStorage;
import com.pku.ss.movienftserver.utils.FileHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.util.ResourceUtils;

/**
 * @author zhangyan
 */
@Repository
@Slf4j
public class FileStorageImpl implements FileStorage {

    @Value("${fileStorage.path}")
    String filePath;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String fileName  = FileHelper.getFileName(file.getOriginalFilename());
            String pathName = ResourceUtils.getURL("classpath:").getPath() + "/static/"+fileName;
            File dest = new File(pathName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return fileName;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    public String downloadFile (HttpServletResponse response, String fileName){
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