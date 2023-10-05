package com.ucatolica.springrestapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ecommerce API Documentation", version = "1.0"))
public class SpringrestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrestapiApplication.class, args);
    }

}
