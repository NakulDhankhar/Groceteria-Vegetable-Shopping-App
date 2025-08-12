package com.groceteria.service;

import java.util.List;

import com.groceteria.dto.OrderDTO;
import com.groceteria.entity.Order;

/**
 * Service interface for Order operations.
 * Provides order management functionality.
 */
public interface OrderService {
    
    /**
     * Add a new order.
     */
    OrderDTO addOrder(OrderDTO orderDTO, Integer userId);
    
    /**
     * Get order by ID.
     */
    OrderDTO getOrderById(Long orderId);
    
    /**
     * Update order information.
     */
    OrderDTO updateOrder(OrderDTO orderDTO, Long orderId);
    
    /**
     * Get orders by user ID.
     */
    List<OrderDTO> getOrdersByUserId(Integer userId);
    
    /**
     * Delete order by ID.
     */
    void deleteOrder(Long orderId);
    
    /**
     * Get all orders.
     */
    List<OrderDTO> getAllOrders();
    
    /**
     * Get orders by status.
     */
    List<OrderDTO> getOrdersByStatus(String orderStatus);
    
    /**
     * Get orders by payment status.
     */
    List<OrderDTO> getOrdersByPaymentStatus(String paymentStatus);
    
    /**
     * Get orders by user and status.
     */
    List<OrderDTO> getOrdersByUserAndStatus(Integer userId, String orderStatus);
    
    /**
     * Update order status.
     */
    OrderDTO updateOrderStatus(Long orderId, String orderStatus);
    
    /**
     * Update payment status.
     */
    OrderDTO updatePaymentStatus(Long orderId, String paymentStatus);
    
    /**
     * Get orders with total price greater than specified amount.
     */
    List<OrderDTO> getOrdersByTotalPriceGreaterThan(Double minPrice);
}
