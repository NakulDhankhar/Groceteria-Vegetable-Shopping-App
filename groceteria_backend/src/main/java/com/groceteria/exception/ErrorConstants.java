package com.groceteria.exception;

/**
 * Constants for error messages and codes used throughout the application.
 * Ensures consistency in error handling and messaging.
 */
public class ErrorConstants {

    // User-related errors
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists with this email";
    public static final String INVALID_CREDENTIALS = "Invalid email or password";
    public static final String ACCOUNT_DEACTIVATED = "Account is deactivated";
    public static final String EMAIL_ALREADY_EXISTS = "Email address is already registered";
    public static final String INVALID_ROLE = "Invalid role specified";
    public static final String ONLY_VENDORS_CAN_ADD_ITEMS = "Only vendors can add items";
    public static final String ONLY_VENDORS_CAN_UPDATE_ITEMS = "Only vendors can update items";
    public static final String ONLY_VENDORS_CAN_DELETE_ITEMS = "Only vendors can delete items";

    // Item-related errors
    public static final String ITEM_NOT_FOUND = "Item not found";
    public static final String ITEM_OUT_OF_STOCK = "Item is out of stock";
    public static final String INSUFFICIENT_QUANTITY = "Insufficient quantity available";
    public static final String INVALID_ITEM_PRICE = "Invalid item price";
    public static final String INVALID_ITEM_QUANTITY = "Invalid item quantity";
    public static final String ITEM_NAME_REQUIRED = "Item name is required";
    public static final String ITEM_PRICE_REQUIRED = "Item price is required";
    public static final String ITEM_QUANTITY_REQUIRED = "Item quantity is required";
    public static final String INVALID_CATEGORY = "Invalid category specified";

    // Order-related errors
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ORDER_ALREADY_PROCESSED = "Order is already processed";
    public static final String ORDER_CANNOT_BE_CANCELLED = "Order cannot be cancelled";
    public static final String INVALID_ORDER_STATUS = "Invalid order status";
    public static final String INVALID_PAYMENT_STATUS = "Invalid payment status";
    public static final String ORDER_TOTAL_REQUIRED = "Order total is required";
    public static final String ORDER_ITEMS_REQUIRED = "Order must contain at least one item";

    // Cart-related errors
    public static final String CART_ITEM_NOT_FOUND = "Cart item not found";
    public static final String CART_EMPTY = "Cart is empty";
    public static final String INVALID_CART_QUANTITY = "Invalid cart quantity";
    public static final String ITEM_ALREADY_IN_CART = "Item is already in cart";
    public static final String CART_QUANTITY_EXCEEDS_STOCK = "Cart quantity exceeds available stock";

    // Payment-related errors
    public static final String PAYMENT_NOT_FOUND = "Payment not found";
    public static final String PAYMENT_ALREADY_PROCESSED = "Payment is already processed";
    public static final String PAYMENT_PROCESSING_FAILED = "Payment processing failed";
    public static final String INVALID_PAYMENT_AMOUNT = "Invalid payment amount";
    public static final String PAYMENT_AMOUNT_REQUIRED = "Payment amount is required";
    public static final String PAYMENT_METHOD_REQUIRED = "Payment method is required";
    public static final String INVALID_PAYMENT_METHOD = "Invalid payment method";

    // Validation errors
    public static final String VALIDATION_ERROR = "Validation error";
    public static final String REQUIRED_FIELD_MISSING = "Required field is missing";
    public static final String INVALID_EMAIL_FORMAT = "Invalid email format";
    public static final String INVALID_PHONE_FORMAT = "Invalid phone number format";
    public static final String PASSWORD_TOO_SHORT = "Password must be at least 8 characters";
    public static final String PASSWORD_TOO_WEAK = "Password must contain at least one uppercase letter, one lowercase letter, and one number";
    public static final String INVALID_NAME_FORMAT = "Name can only contain letters and spaces";
    public static final String INVALID_ADDRESS_FORMAT = "Invalid address format";

    // Authentication and Authorization errors
    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    public static final String AUTHORIZATION_FAILED = "Authorization failed";
    public static final String INSUFFICIENT_PERMISSIONS = "Insufficient permissions";
    public static final String TOKEN_EXPIRED = "Authentication token has expired";
    public static final String INVALID_TOKEN = "Invalid authentication token";

    // General errors
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String BAD_REQUEST = "Bad request";
    public static final String CONFLICT = "Resource conflict";
    public static final String UNAUTHORIZED = "Unauthorized access";
    public static final String FORBIDDEN = "Access forbidden";
    public static final String METHOD_NOT_ALLOWED = "Method not allowed";
    public static final String REQUEST_TIMEOUT = "Request timeout";

    // Error codes
    public static final String ERROR_CODE_USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String ERROR_CODE_USER_EXISTS = "USER_EXISTS";
    public static final String ERROR_CODE_INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    public static final String ERROR_CODE_ACCOUNT_DEACTIVATED = "ACCOUNT_DEACTIVATED";
    public static final String ERROR_CODE_EMAIL_EXISTS = "EMAIL_EXISTS";
    public static final String ERROR_CODE_INVALID_ROLE = "INVALID_ROLE";
    public static final String ERROR_CODE_VENDOR_ONLY = "VENDOR_ONLY";
    public static final String ERROR_CODE_ITEM_NOT_FOUND = "ITEM_NOT_FOUND";
    public static final String ERROR_CODE_OUT_OF_STOCK = "OUT_OF_STOCK";
    public static final String ERROR_CODE_INSUFFICIENT_QUANTITY = "INSUFFICIENT_QUANTITY";
    public static final String ERROR_CODE_ORDER_NOT_FOUND = "ORDER_NOT_FOUND";
    public static final String ERROR_CODE_CART_EMPTY = "CART_EMPTY";
    public static final String ERROR_CODE_PAYMENT_FAILED = "PAYMENT_FAILED";
    public static final String ERROR_CODE_VALIDATION_ERROR = "VALIDATION_ERROR";
    public static final String ERROR_CODE_AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
    public static final String ERROR_CODE_AUTHORIZATION_FAILED = "AUTHORIZATION_FAILED";
    public static final String ERROR_CODE_INTERNAL_ERROR = "INTERNAL_ERROR";
    public static final String ERROR_CODE_RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    public static final String ERROR_CODE_BAD_REQUEST = "BAD_REQUEST";
    public static final String ERROR_CODE_CONFLICT = "CONFLICT";
    public static final String ERROR_CODE_UNAUTHORIZED = "UNAUTHORIZED";
    public static final String ERROR_CODE_FORBIDDEN = "FORBIDDEN";

    private ErrorConstants() {
        // Utility class - prevent instantiation
    }
} 