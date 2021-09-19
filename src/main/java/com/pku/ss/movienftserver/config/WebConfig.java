/**
 * @(#)WebConfig.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangyan
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${fileStorage.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")  // 映射swagger2，如果你继承了swagger2，那么就需要为他也做一个映射，不然无法访问到swagger文档
                .addResourceLocations("file:"+path);
    }

}