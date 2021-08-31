/**
 * @(#)WebConfig.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangyan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("").addResourceLocations("");
    }

}