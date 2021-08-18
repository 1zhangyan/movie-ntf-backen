/**
 * @(#)QtumProxy.java, 8月 17, 2021.
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
@Component
@Slf4j
public class QtumProxy {

    @Value("${qtumClient.url}")
    private String clientUrl;
    @Value("${qtumClient.username}")
    private String username;
    @Value("${qtumClient.password}")
    private String password;

    private JsonRpcHttpClient getClient(){
        JsonRpcHttpClient client;
        String cred = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", "Basic " + cred);
        try{
            client = new JsonRpcHttpClient(new URL(clientUrl),headers);
            return client;
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    public Object getBlockchainInfo(){
        JsonRpcHttpClient client = getClient();
        try{

            return  client.invoke("getblockchaininfo", new Object[]{} , Object.class);
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

    public Object autoMethod(String methodName , Object argument){
        JsonRpcHttpClient client = getClient();
        try{

            return  client.invoke(methodName, argument, Object.class);
        } catch (Throwable t){
            log.error(t.getLocalizedMessage());
            return null;
        }
    }

}