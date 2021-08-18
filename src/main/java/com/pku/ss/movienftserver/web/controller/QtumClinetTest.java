/**
 * @(#)QtumClinetTest.java, 8月 17, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.web.controller;

import com.pku.ss.movienftserver.proxy.QtumProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author zhangyan
 */

@RestController
@Slf4j
public class QtumClinetTest {



    @Autowired
    QtumProxy qtumProxy;

    @GetMapping
    public Object getTest(String methodName){
       return qtumProxy.autoMethod(methodName , new Object[]{});
    }


}