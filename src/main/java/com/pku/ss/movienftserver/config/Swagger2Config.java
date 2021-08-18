/**
 * @(#)Swagger2Config.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangyan
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 配置到项目的controller包
                .apis(RequestHandlerSelectors.basePackage("com.pku.ss.movienftserver.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MovieNftServer")
                .description("MovieNftServer Swagger Interface Comments")
                .contact(new Contact("zhangyan1", "https://github.com/1zhangyan/movie-ntf-backen", "zhangyan1dev@163.com"))
                .version("0.0.0").build();
    }

}