package com.example.developpez_le_back_end_en_utilisant_java_et_spring.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:3002");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Krisandra ADAMS");
        myContact.setEmail("fake.email@gmail.com");

        Info information = new Info()
                .title("Test title")
                .version("1.0")
                .description("This API exposes endpoints.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
