package com.groceteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.dto.OrderDTO;
import com.groceteria.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for Order management operations.
 * Provides endpoints for order creation, tracking, and management.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "Order Management", description = "APIs for order creation, tracking, and management")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
    /**
     * Create a new order.
     */
    @PostMapping
    @Operation(summary = "Create new order", description = "Creates a new order for the specified user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<OrderDTO> createOrder(
            @Valid @RequestBody OrderDTO orderDTO,
            @Parameter(description = "User ID") @RequestParam Integer userId) {
        OrderDTO createdOrder = orderService.addOrder(orderDTO, userId);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    
    /**
     * Get order by ID.
     */
    @GetMapping("/{orderId}")
    @Operation(summary = "Get order by ID", description = "Retrieves order information by order ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDTO> getOrderById(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
    /**
     * Update order information.
     */
    @PutMapping("/{orderId}")
    @Operation(summary = "Update order", description = "Updates order information by order ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order updated successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))),
        @ApiResponse(responseCode = "404", description = "Order not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<OrderDTO> updateOrder(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId,
            @Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(orderDTO, orderId);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    /**
     * Get all orders.
     */
    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves all orders in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    /**
     * Get orders by user ID.
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get orders by user", description = "Retrieves all orders for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    /**
     * Delete order by ID.
     */
    @DeleteMapping("/{orderId}")
    @Operation(summary = "Delete order", description = "Deletes order by order ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId) {
		orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Get orders by status.
     */
    @GetMapping("/status/{orderStatus}")
    @Operation(summary = "Get orders by status", description = "Retrieves orders with specific status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(
            @Parameter(description = "Order status") @PathVariable("orderStatus") String orderStatus) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    /**
     * Get orders by payment status.
     */
    @GetMapping("/payment-status/{paymentStatus}")
    @Operation(summary = "Get orders by payment status", description = "Retrieves orders with specific payment status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getOrdersByPaymentStatus(
            @Parameter(description = "Payment status") @PathVariable("paymentStatus") String paymentStatus) {
        List<OrderDTO> orders = orderService.getOrdersByPaymentStatus(paymentStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    /**
     * Get orders by user and status.
     */
    @GetMapping("/user/{userId}/status/{orderStatus}")
    @Operation(summary = "Get orders by user and status", description = "Retrieves orders for a user with specific status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getOrdersByUserAndStatus(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId,
            @Parameter(description = "Order status") @PathVariable("orderStatus") String orderStatus) {
        List<OrderDTO> orders = orderService.getOrdersByUserAndStatus(userId, orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    
    /**
     * Update order status.
     */
    @PutMapping("/{orderId}/status")
    @Operation(summary = "Update order status", description = "Updates the status of an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order status updated successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId,
            @Parameter(description = "New order status") @RequestParam String orderStatus) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(orderId, orderStatus);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    /**
     * Update payment status.
     */
    @PutMapping("/{orderId}/payment-status")
    @Operation(summary = "Update payment status", description = "Updates the payment status of an order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment status updated successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class))),
        @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDTO> updatePaymentStatus(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId,
            @Parameter(description = "New payment status") @RequestParam String paymentStatus) {
        OrderDTO updatedOrder = orderService.updatePaymentStatus(orderId, paymentStatus);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    /**
     * Get orders with total price greater than specified amount.
     */
    @GetMapping("/price-greater-than")
    @Operation(summary = "Get orders by minimum price", description = "Retrieves orders with total price greater than specified amount")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Orders retrieved successfully",
                    content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    })
    public ResponseEntity<List<OrderDTO>> getOrdersByTotalPriceGreaterThan(
            @Parameter(description = "Minimum total price") @RequestParam Double minPrice) {
        List<OrderDTO> orders = orderService.getOrdersByTotalPriceGreaterThan(minPrice);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
