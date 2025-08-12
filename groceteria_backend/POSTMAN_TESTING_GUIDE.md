# Groceteria API - Postman Testing Guide

## Overview
This guide provides instructions for testing the Groceteria E-Commerce API using Postman.

## Prerequisites
1. **Database Setup**: Ensure MySQL is running and the database is configured
2. **Application Running**: Start the Spring Boot application
3. **Postman**: Install Postman or use the web version

## Quick Start

### 1. Import the Collection
1. Open Postman
2. Click "Import" button
3. Select the `Groceteria_API_Postman_Collection.json` file
4. The collection will be imported with all endpoints

### 2. Set Environment Variables
1. Create a new environment in Postman
2. Add the following variable:
   - **Variable Name**: `base_url`
   - **Initial Value**: `http://localhost:8080`
   - **Current Value**: `http://localhost:8080`

### 3. Start the Application
```bash
cd groceteria_backend
./mvnw spring-boot:run
```

## API Endpoints Overview

### User Management (`/api/v1/users`)
- **POST** `/register` - Register a new user
- **POST** `/login` - User authentication
- **GET** `/` - Get all users
- **GET** `/{userId}` - Get user by ID
- **PUT** `/{userId}` - Update user profile
- **POST** `/forgot-password` - Password reset request
- **DELETE** `/{userId}` - Delete user account

### Cart Management (`/api/v1/cart`)
- **POST** `/` - Add item to cart
- **GET** `/` - Get all cart items
- **GET** `/{cartId}` - Get cart item by ID
- **PUT** `/{cartId}` - Update cart item
- **PUT** `/{cartId}/quantity` - Update cart quantity
- **GET** `/user/{userId}` - Get cart by user
- **GET** `/user/{userId}/count` - Get cart item count
- **DELETE** `/{cartId}` - Delete cart item
- **DELETE** `/user/{userId}` - Clear user cart

### Item Management (`/api/v1/items`)
- **GET** `/` - Get all items
- **GET** `/{itemId}` - Get item by ID
- **GET** `/category/{category}` - Get items by category
- **GET** `/search` - Search items by keyword

### Order Management (`/api/v1/orders`)
- **POST** `/` - Create new order
- **GET** `/` - Get all orders
- **GET** `/{orderId}` - Get order by ID
- **GET** `/user/{userId}` - Get orders by user
- **PUT** `/{orderId}/status` - Update order status

### Payment Management (`/api/v1/payments`)
- **POST** `/` - Process payment
- **GET** `/{paymentId}` - Get payment by ID
- **GET** `/order/{orderId}` - Get payments by order

## Testing Workflow

### 1. User Registration & Authentication
1. **Register User**: Use the "Register User" request
2. **Login**: Use the "User Login" request
3. **Note the User ID**: You'll need this for other requests

### 2. Browse Items
1. **Get All Items**: View available products
2. **Search Items**: Find specific products
3. **Get Items by Category**: Browse by category

### 3. Cart Operations
1. **Add Item to Cart**: Add products to cart
2. **View Cart**: Check cart contents
3. **Update Quantities**: Modify item quantities
4. **Remove Items**: Delete items from cart

### 4. Order Processing
1. **Create Order**: Place order from cart
2. **View Order**: Check order details
3. **Track Status**: Monitor order progress

### 5. Payment Processing
1. **Process Payment**: Complete payment for order
2. **View Payment**: Check payment details

## Sample Test Data

### User Registration
```json
{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "phone": "+1234567890",
    "address": "123 Main St, City, State 12345"
}
```

### User Login
```json
{
    "email": "john.doe@example.com",
    "password": "password123"
}
```

### Add to Cart
```json
{
    "quantity": 2
}
```

### Create Order
```json
{
    "userId": 1,
    "items": [
        {
            "itemId": 1,
            "quantity": 2
        }
    ],
    "shippingAddress": "123 Main St, City, State 12345",
    "paymentMethod": "CREDIT_CARD"
}
```

## Common HTTP Status Codes

- **200 OK**: Request successful
- **201 Created**: Resource created successfully
- **400 Bad Request**: Invalid input data
- **401 Unauthorized**: Authentication required
- **403 Forbidden**: Insufficient permissions
- **404 Not Found**: Resource not found
- **409 Conflict**: Resource already exists
- **500 Internal Server Error**: Server error

## Swagger Documentation

Access the interactive API documentation at:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## Troubleshooting

### Common Issues

1. **Connection Refused**
   - Ensure the application is running on port 8080
   - Check if the port is not occupied by another service

2. **Database Connection Error**
   - Verify MySQL is running
   - Check database credentials in `application.yml`
   - Ensure database `groceteria` exists

3. **Validation Errors**
   - Check request body format
   - Ensure all required fields are provided
   - Verify data types match expected format

4. **404 Not Found**
   - Verify the endpoint URL is correct
   - Check if the resource ID exists in the database

### Debug Mode

Enable debug logging by setting in `application.yml`:
```yaml
logging:
  level:
    com.groceteria: DEBUG
```

## Performance Testing

For load testing, consider using:
- **Postman Runner**: Run collections with multiple iterations
- **Newman**: Command-line tool for Postman collections
- **JMeter**: For more advanced load testing

## Security Notes

- This is a development environment
- Passwords are stored in plain text (for demo purposes)
- No real payment processing is implemented
- CORS is enabled for all origins

## Support

For issues or questions:
1. Check the application logs
2. Review the Swagger documentation
3. Verify database connectivity
4. Test individual endpoints

---

**Happy Testing! 🛒** 