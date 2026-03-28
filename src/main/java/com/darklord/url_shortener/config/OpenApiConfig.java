package com.darklord.url_shortener.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // 1. Define the Security Scheme (How we log in)
        final String securitySchemeName = "basicAuth";

        return new OpenAPI()
                // 2. Metadata about your API
                .info(new Info()
                        .title("URL Shortener API")
                        .version("1.0")
                        .description("A REST API for generating and managing short links.")
                        .contact(new Contact().name("darklord").email("swayamgaded@gmail.com")))

                // 3. Add the Security Definition (The "What")
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")));
    }

}
