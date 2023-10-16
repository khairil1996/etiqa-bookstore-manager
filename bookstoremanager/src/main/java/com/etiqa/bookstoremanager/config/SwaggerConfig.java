package com.etiqa.bookstoremanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot REST API",
        version = "1.0.0",
        description = "This API manages customers and products.",
        contact = @io.swagger.v3.oas.annotations.info.Contact(name = "Khairil", url = "https://github.com/khairil1996", email = "khairilrajaie@gmail.com")))
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components());
    }
}


