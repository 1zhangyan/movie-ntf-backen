/**
 * @(#)FileStorage.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.dao;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangyan
 */
public interface FileStorage {
    String uploadFile(MultipartFile file);

    String downloadFile (HttpServletResponse response, String fileName);

}