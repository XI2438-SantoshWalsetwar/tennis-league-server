package com.xebia.tennisleagueserver.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/league/api/**"))
                .apis(RequestHandlerSelectors.basePackage("com.xebia"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Tennis League Championship API Guide",
                "API can be consumed to schedule match league for Single players",
                "Version 1.0",
                "Terms of service", //terms of service URL
                new Contact("Santosh Walsetwar", "xebia.com", "myeaddress@company.com"),
                "License of API", "API license URL"); // contact info
    }
}