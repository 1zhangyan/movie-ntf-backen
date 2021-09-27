/**
 * @(#)FileHelper.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangyan
 */
@Slf4j
public class FileHelper {

    public static String getFielSignature(byte[] input){
        // 通过调用MessageDigest（数据摘要类）的getInstance()静态方法，传入加密算法的名称，获取数据摘要对象。
        //MessageDigest MessageDigest.getInstance(algorithm);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
        // 获取摘要（加密），结果是字节数组
        // byte[] java.security.MessageDigest.digest(byte[] input)
        byte[] ciphertext = messageDigest.digest(input);
        // 利用apache的commons-codec，将字节数组转换为十六进制。
        return Hex.encodeHexString(ciphertext);
    }

    public static String getFileName(String imageName){
        return System.currentTimeMillis() + imageName;
    }

    public static String getNewFileName(String imageName) throws Exception{

            String[] fileArray = imageName.split(".");
            if(fileArray.length < 1 ) throw new Exception("Bad File name.");
            return String.valueOf(System.currentTimeMillis() + fileArray[1]);
    }

}