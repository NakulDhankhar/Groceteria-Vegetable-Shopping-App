# Groceteria Backend - Postman Ready

## What Was Cleaned Up

### 1. Test Structure Reorganization
- **Fixed Package Mismatch**: Moved test files from `com.groceryStore` to `com.groceteria` to match main application
- **Updated Test Class Names**: Renamed `OnlineGroceryStoreApplicationTests` to `GroceteriaApplicationTests`
- **Corrected Test Endpoints**: Updated test endpoints to match actual controller endpoints
- **Removed Duplicate Files**: Deleted old test files in wrong packages

### 2. Test File Updates
- **CartControllerTest**: Updated to use correct service methods and endpoints
- **UserControllerTest**: Updated to match actual controller endpoints
- **Added Proper Mocking**: Used `any()` matchers for better test flexibility

### 3. Documentation & Testing Tools
- **Postman Collection**: Created comprehensive `Groceteria_API_Postman_Collection.json`
- **Testing Guide**: Added detailed `POSTMAN_TESTING_GUIDE.md`
- **Startup Scripts**: Created `start.bat` and `start.sh` for easy application startup

## Current Backend Structure

```
groceteria_backend/
├── src/
│   ├── main/
│   │   ├── java/com/groceteria/
│   │   │   ├── config/           # OpenAPI configuration
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── exception/       # Custom exceptions
│   │   │   ├── repository/      # Data access layer
│   │   │   ├── service/         # Service interfaces
│   │   │   ├── serviceImpl/     # Service implementations
│   │   │   ├── validation/      # Validation handlers
│   │   │   └── groceteriaApplication.java
│   │   └── resources/
│   │       └── application.yml  # Configuration
│   └── test/
│       └── java/com/groceteria/
│           ├── controllerTest/  # Controller tests
│           └── GroceteriaApplicationTests.java
├── pom.xml                      # Maven dependencies
├── Groceteria_API_Postman_Collection.json
├── POSTMAN_TESTING_GUIDE.md
├── README_POSTMAN_READY.md
├── start.bat                    # Windows startup script
└── start.sh                     # Unix/Linux startup script
```

## API Endpoints Available

### User Management (`/api/v1/users`)
- `POST /register` - User registration
- `POST /login` - User authentication
- `GET /` - Get all users
- `GET /{userId}` - Get user by ID
- `PUT /{userId}` - Update user
- `POST /forgot-password` - Password reset
- `DELETE /{userId}` - Delete user

### Cart Management (`/api/v1/cart`)
- `POST /` - Add item to cart
- `GET /` - Get all cart items
- `GET /{cartId}` - Get cart item by ID
- `PUT /{cartId}` - Update cart item
- `PUT /{cartId}/quantity` - Update quantity
- `GET /user/{userId}` - Get user's cart
- `GET /user/{userId}/count` - Get cart count
- `DELETE /{cartId}` - Delete cart item
- `DELETE /user/{userId}` - Clear user cart

### Item Management (`/api/v1/items`)
- `GET /` - Get all items
- `GET /{itemId}` - Get item by ID
- `GET /category/{category}` - Get by category
- `GET /search` - Search items

### Order Management (`/api/v1/orders`)
- `POST /` - Create order
- `GET /` - Get all orders
- `GET /{orderId}` - Get order by ID
- `GET /user/{userId}` - Get user orders
- `PUT /{orderId}/status` - Update status

### Payment Management (`/api/v1/payments`)
- `POST /` - Process payment
- `GET /{paymentId}` - Get payment by ID
- `GET /order/{orderId}` - Get order payments

## Quick Start for Testing

### 1. Database Setup
```sql
-- Ensure MySQL is running
-- Database will be created automatically if it doesn't exist
-- Default credentials in application.yml:
-- Database: groceteria
-- Username: root
-- Password: Nakul123
```

### 2. Start Application
**Windows:**
```cmd
start.bat
```

**Unix/Linux:**
```bash
chmod +x start.sh
./start.sh
```

**Manual:**
```bash
./mvnw spring-boot:run
```

### 3. Import Postman Collection
1. Open Postman
2. Import `Groceteria_API_Postman_Collection.json`
3. Set environment variable `base_url` to `http://localhost:8080`
4. Start testing!

### 4. Access Swagger Documentation
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## Testing Workflow

1. **Register a user** using `POST /api/v1/users/register`
2. **Login** using `POST /api/v1/users/login`
3. **Browse items** using `GET /api/v1/items`
4. **Add items to cart** using `POST /api/v1/cart`
5. **Create an order** using `POST /api/v1/orders`
6. **Process payment** using `POST /api/v1/payments`

## Key Features

- ✅ **RESTful API Design**: Proper HTTP methods and status codes
- ✅ **Input Validation**: Bean validation with custom error handling
- ✅ **Exception Handling**: Comprehensive exception management
- ✅ **API Documentation**: OpenAPI/Swagger integration
- ✅ **Database Integration**: MySQL with JPA/Hibernate
- ✅ **CORS Support**: Cross-origin requests enabled
- ✅ **Logging**: Debug and SQL logging configured
- ✅ **Testing**: Unit tests for controllers
- ✅ **Postman Ready**: Complete collection for testing

## Dependencies

- **Spring Boot 3.2.2**: Core framework
- **Spring Data JPA**: Database access
- **MySQL Connector**: Database driver
- **SpringDoc OpenAPI**: API documentation
- **Lombok**: Code generation
- **H2 Database**: Test database
- **Spring Boot Test**: Testing framework

## Configuration

Key configuration in `application.yml`:
- **Server Port**: 8080
- **Database**: MySQL with auto-creation
- **JPA**: Hibernate with SQL logging
- **Swagger**: Interactive API documentation
- **Logging**: Debug level for development

## Next Steps

1. **Test all endpoints** using the Postman collection
2. **Review Swagger documentation** for detailed API specs
3. **Add more test data** to the database
4. **Implement frontend integration** with Angular
5. **Add authentication/authorization** if needed
6. **Deploy to production** environment

---

**Your Groceteria backend is now ready for Postman testing! 🚀** 