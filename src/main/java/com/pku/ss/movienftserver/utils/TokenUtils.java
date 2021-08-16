/**
 * @(#)tokenUtils.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyan
 */
public class TokenUtils {

    //设置两个小时Token过期
    public static final int LOGINPERSISTENTHOURS = 2;
    //设置加密的密钥
    public static final String SECRET = "sdjhakdhajdklsl;o653632";


    public static String getToken(int userId ,String userName) {

        //设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, LOGINPERSISTENTHOURS);

        //设置JWT验证头
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        return JWT.create()
                .withHeader(map)//头
                .withClaim("userId", userId)
                .withClaim("userName",userName)
                .withExpiresAt(nowTime.getTime())//过期时间
                .sign(Algorithm.HMAC256(SECRET));//签名
    }

    public static Map<String, Claim> verifyToken(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;

        try {
            jwt = verifier.verify(token);
        } catch (Exception e){
            return Collections.emptyMap();
        }

        return jwt.getClaims();
    }

}