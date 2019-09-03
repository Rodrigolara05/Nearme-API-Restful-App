package com.resasoftware.nearme.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
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
	      //.apis(RequestHandlerSelectors.basePackage("com.phengtola.spring.controllers.rest"))
	      .apis(RequestHandlerSelectors.basePackage("com.resasoftware.nearme"))
	      .paths(PathSelectors.any())
	      //.paths(PathSelectors.ant("/v2/api/article"))
	      .build()
	      .apiInfo(apiInfo());
	}
	 
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
	    ApiInfo apiInfo = new ApiInfo(
	      "Spring Boot RESTFul API Nearme",
	      "API for the course of mobile applications",
	      "1.0",
	      "Terms of service",
	      "RESA Software - resasoftware@gmail.com",
	      "License of API",
	      "PREMIUM");
	    return apiInfo;
	}
}
	