package com.groceteria.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI Configuration for Groceteria Application
 * 
 * This configuration sets up comprehensive API documentation using OpenAPI 3.0
 * specification with detailed information about the Groceteria e-commerce platform.
 * 
 * @author Groceteria Team
 * @version 1.0
 * @since 2024
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configures the OpenAPI specification for the Groceteria application.
     * 
     * @return OpenAPI configuration with detailed information
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Groceteria API")
                        .description("""
                                # Groceteria Personal E-Commerce Project API
                                
                                ## Overview
                                Groceteria is a personal e-commerce project for grocery shopping built with Spring Boot. 
                                This API provides endpoints for managing users, products, orders, payments, and shopping carts.
                                
                                ## Features
                                - **User Management**: Registration, authentication, and profile management
                                - **Product Catalog**: Browse and search grocery items by category
                                - **Shopping Cart**: Add, remove, and manage items in cart
                                - **Order Management**: Place orders and track order status
                                - **Payment Processing**: Secure payment handling
                                - **Admin Panel**: Administrative functions for store management
                                
                                ## Authentication
                                The API uses JWT (JSON Web Tokens) for authentication. Include the token 
                                in the Authorization header: `Authorization: Bearer <your-token>`
                                
                                ## Error Handling
                                The API returns standardized error responses with appropriate HTTP status codes:
                                - 400: Bad Request (validation errors)
                                - 401: Unauthorized (authentication required)
                                - 403: Forbidden (insufficient permissions)
                                - 404: Not Found (resource not found)
                                - 500: Internal Server Error
                                
                                ## Data Validation
                                All input data is validated using Bean Validation annotations:
                                - Email addresses must be in valid format
                                - Passwords must meet security requirements
                                - Phone numbers must be in international format
                                - Required fields are marked appropriately
                                
                                ## Versioning
                                API versioning is handled through URL paths: `/api/v1/`
                                
                                ## Personal Project
                                This is a personal learning project demonstrating Spring Boot best practices,
                                REST API design, and production-ready code structure.
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Personal Project")
                                .email("developer@example.com")
                                .url("https://github.com/yourusername/groceteria"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Development Server")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT token for API authentication"))
                        .addSecuritySchemes("apiKey", new SecurityScheme()
                                .name("X-API-Key")
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .description("API key for external integrations")));
    }
} 