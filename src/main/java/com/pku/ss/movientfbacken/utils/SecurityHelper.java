/**
 * @(#)SecurityHelper.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movientfbacken.utils;

import com.pku.ss.movientfbacken.data.User;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author zhangyan
 */
public class SecurityHelper {

    private static final ThreadLocal<User> userContextHolder = new ThreadLocal<User>();

    public static void setUserContext(User user){
        userContextHolder.set(user);
    }

    public static User getUserInfo(){
        User user = userContextHolder.get();
        return user;
    }

    public static void removeUserContext(){
        if (userContextHolder != null) {
            userContextHolder.remove();
        }
    }

    public static String getEncryptPassword(String password){
        return encrypt(password);
    }

    public static boolean verifyPassword(String password , String encryptPassword ){
        String ciphertext = encrypt(password);
        return encryptPassword.equals(ciphertext);
    }

    private static String encrypt(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

}