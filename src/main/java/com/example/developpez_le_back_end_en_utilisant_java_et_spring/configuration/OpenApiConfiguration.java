package com.example.developpez_le_back_end_en_utilisant_java_et_spring.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:3001");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Krisandra ADAMS");
        myContact.setEmail("fake.email@gmail.com");

        Info information = new Info()
                .title("Test title")
                .version("1.0")
                .description("This API exposes endpoints.")
                .contact(myContact);

        // For JWT Authentication
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("JWT Authentication")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(information)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .schemaRequirement("bearerAuth", securityScheme)
                .servers(List.of(server));
    }
}
