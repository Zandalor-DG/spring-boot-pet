package org.springboot.pet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Тестовый проект Дмитрия Геннадьевича",
                version = "0.0.1",
                description = "Мой первый тестовый проект на JAVA-SPRING-BOOT-HIBERNATE"
        )
)
public class SwaggerConfig {
}
