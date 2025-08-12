# Groceteria Personal E-Commerce Project

A personal e-commerce project for grocery shopping built with Spring Boot, featuring a production-ready User entity with comprehensive OpenAPI documentation.

## 🚀 Features

- **User Management**: Complete user registration, authentication, and profile management
- **Product Catalog**: Browse and search grocery items by category
- **Shopping Cart**: Add, remove, and manage items in cart
- **Order Management**: Place orders and track order status
- **Payment Processing**: Secure payment handling
- **Admin Panel**: Administrative functions for store management
- **OpenAPI Documentation**: Comprehensive API documentation with Swagger UI

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+ or H2 Database
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.2.2
- **Database**: MySQL 8.0 / H2 (for development)
- **ORM**: Hibernate with JPA
- **Validation**: Bean Validation (Jakarta Validation)
- **Documentation**: OpenAPI 3.0 with Swagger UI
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code

## 🏗️ Project Structure

```
groceteria_backend/
├── src/
│   ├── main/
│   │   ├── java/com/groceteria/
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java          # OpenAPI configuration
│   │   │   ├── controller/                      # REST controllers
│   │   │   ├── entity/                          # JPA entities
│   │   │   │   └── User.java                   # Production-ready User entity
│   │   │   ├── repository/                      # Data access layer
│   │   │   ├── service/                         # Business logic layer
│   │   │   ├── serviceImpl/                     # Service implementations
│   │   │   ├── exception/                       # Custom exceptions
│   │   │   └── validation/                      # Custom validators
│   │   └── resources/
│   │       └── application.properties           # Application configuration
│   └── test/                                    # Test cases
├── pom.xml                                      # Maven dependencies
└── README.md                                    # This file
```

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd groceteria_backend
```

### 2. Configure Database
Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost/groceteria?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run
```bash
# Clean and compile
mvn clean compile

# Run the application
mvn spring-boot:run
```

### 4. Access the Application
- **API Base URL**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 📚 User Entity Documentation

The `User` entity has been completely rewritten to be production-ready with the following improvements:

### 🔐 Security Features
- **Password Validation**: Strong password requirements with regex validation
- **Email Validation**: Proper email format validation
- **Phone Number Validation**: International phone number format support
- **Input Sanitization**: Pattern validation for names and addresses

### 📊 Database Design
- **Proper Naming**: Consistent snake_case column names
- **Constraints**: Appropriate nullable, unique, and length constraints
- **Indexing**: Optimized for common query patterns
- **Audit Fields**: Created/updated timestamps and soft delete support

### 🎯 Validation Rules

#### Personal Information
- **First/Last Name**: 2-50 characters, letters and spaces only
- **Gender**: Predefined values (MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY)
- **Date of Birth**: Optional field for profile information

#### Contact Information
- **Email**: Valid email format, unique across all users
- **Phone Number**: International format (+1234567890)
- **Address**: 10-200 characters with proper validation

#### Security
- **Password**: Minimum 8 characters with complexity requirements
  - At least one uppercase letter
  - At least one lowercase letter
  - At least one digit
  - At least one special character

#### Location
- **District/City**: 2-50 characters
- **State/Province**: 2-50 characters
- **Zipcode**: 5-10 characters, alphanumeric with hyphens

#### System Fields
- **Role**: CUSTOMER, ADMIN, MANAGER
- **Status**: Active/inactive account management
- **Audit Fields**: Created/updated timestamps

## 📖 API Documentation

### OpenAPI Integration
The application includes comprehensive OpenAPI 3.0 documentation:

- **Interactive Documentation**: Swagger UI for testing endpoints
- **Detailed Schemas**: Complete entity documentation with examples
- **Security Schemes**: JWT and API key authentication documentation
- **Error Responses**: Standardized error handling documentation

### Key Documentation Features
- **Schema Examples**: Real-world examples for all fields
- **Validation Rules**: Clear documentation of validation requirements
- **Security**: Password field marked as write-only
- **Access Modes**: Read-only fields properly marked
- **Allowable Values**: Enumerated values for constrained fields

## 🔧 Configuration

### Application Properties
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost/groceteria
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

### Maven Dependencies
Key dependencies for the production-ready setup:

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<!-- OpenAPI Documentation -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>

<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.30</version>
</dependency>
```

## 🧪 Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserControllerTest

# Run with coverage
mvn test jacoco:report
```

### Test Coverage
The application includes comprehensive test coverage for:
- Entity validation
- Controller endpoints
- Service layer business logic
- Repository data access

## 🔒 Security Considerations

### Password Security
- Strong password requirements enforced
- Passwords should be hashed before storage (implement in service layer)
- Password field marked as write-only in API documentation

### Input Validation
- All user inputs validated at multiple layers
- SQL injection prevention through JPA
- XSS prevention through proper input sanitization

### API Security
- JWT token authentication
- Rate limiting implementation recommended
- CORS configuration for frontend integration

## 🚀 Deployment

### Local Development
1. **Environment Variables**: Use environment variables for sensitive data
2. **Database**: MySQL or H2 for development
3. **Security**: Basic authentication and validation
4. **Logging**: Standard Spring Boot logging

### Docker Deployment (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/groceteria-backend-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📝 API Endpoints

### User Management
- `POST /api/v1/users` - Register new user
- `GET /api/v1/users/{id}` - Get user profile
- `PUT /api/v1/users/{id}` - Update user profile
- `DELETE /api/v1/users/{id}` - Delete user account

### Authentication
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/logout` - User logout
- `POST /api/v1/auth/refresh` - Refresh token

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For questions about this personal project:
- **Documentation**: http://localhost:8080/swagger-ui.html
- **Issues**: Create an issue in the repository

## 🔄 Version History

- **v1.0.0** - Initial production-ready release with comprehensive User entity
- **v0.9.0** - OpenAPI documentation integration
- **v0.8.0** - Enhanced validation and security features

---

**Built with ❤️ as a personal learning project** 
