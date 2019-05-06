package com.example.demo.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .useDefaultResponseMessages(false)                                   
                .globalResponseMessage(RequestMethod.GET,                     
                  Arrays.asList(new ResponseMessageBuilder()   
                    .code(500)
                    .message("500 message")
                    .responseModel(new ModelRef("Error"))
                    .build(),
                    new ResponseMessageBuilder() 
                      .code(403)
                      .message("Forbidden!")
                      .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("My API")
                .description("My API Description")
                .contact(new springfox.documentation.service.Contact("John Doe", "www.example.com", "john@example.com"))
                .build();
    }
}
