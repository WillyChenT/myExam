package com.java.exam.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private String description = "Basic CRUD APIs for Company and Client data.\r\n" + 
			"\r\n" + 
			"H2 DataBase URL: http://localhost:8080/h2-console\r\n" + 
			"\r\n" + 
			"User Account\r\n" + 
			"\r\n" +
			"SuperUser Client: Adam Password: 12345\r\n" +
			"\r\n" +
			"Manager Client: Jerry Password: abced\r\n" + 
			"\r\n" +
			"Operator Client: 服部平次  Password: 3254";
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .tags(new Tag("1.新增", "PostController"),new Tag("2.更新", "PutController"),new Tag("3.查詢", "GetController"),new Tag("4.刪除", "DeleteController"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.java.exam.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 For SpringBoot APIs")
                .description(description)
                .version("1.0.0")
                .build();
    }
}
