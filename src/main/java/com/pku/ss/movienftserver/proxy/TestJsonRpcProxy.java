/**
 * @(#)TestJsonRpcProxy.java, 8月 10, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.proxy;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * @author zhangyan
 */

@Slf4j
@Component
public class TestJsonRpcProxy {

    private String clientUrl;

    @Value("${qtumClient.url}")
    public void setClientUrl(String clientUrl){
        this.clientUrl = clientUrl;
    }

    private static JsonRpcHttpClient client;

    public Object test(){
        try{
            String user = "test";
            String password = "test1234";
            String cred = Base64.getEncoder().encodeToString((user + ":" + password).getBytes());
            Map<String, String> headers = new HashMap<String, String>(1);
            headers.put("Authorization", "Basic " + cred);
            client = new JsonRpcHttpClient(new URL(clientUrl),headers);
            return  client.invoke("getblockchaininfo", new Object[]{} , Object.class);
        } catch (Throwable throwable) {
            log.error(throwable.getLocalizedMessage());
            return null;
        }
    }


}