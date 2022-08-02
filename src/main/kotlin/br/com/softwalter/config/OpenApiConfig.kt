package br.com.softwalter.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    //swagger-ui

    @Bean
    fun custonOpenApi(): OpenAPI {

        return OpenAPI()
            .info(
                Info()
                    .title("RestFull API with Kotlin 1.6.21 and Spring 2.7.1 ")
                    .version("v1")
                    .description("Api de cadastro de pessoas")
                    .termsOfService("http://localhost:8080/cadastro/v1/pessoas")
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("http://localhost:8080/cadastro/v1/pessoas")
                    )
            )
    }
}