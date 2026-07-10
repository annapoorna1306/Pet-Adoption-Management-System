package com.adoption;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot Application class for Pet Adoption Management System API
 * Starts the application on port 8080
 * API documentation available at: http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
public class PetAdoptionApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PetAdoptionApplication.class, args);
    }
    
    /**
     * Configure OpenAPI/Swagger documentation for the API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pet Adoption Management System API")
                        .version("1.0.0")
                        .description("REST API for managing pet adoption, adopters, shelters, veterinarians, and adoption records")
                        .contact(new Contact()
                                .name("Pet Adoption Team")
                                .email("support@petadoption.com")
                        )
                );
    }
}
