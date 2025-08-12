package com.groceteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.Order;
import com.groceteria.entity.User;

/**
 * Repository interface for Order entity operations.
 * Provides methods for order management and tracking.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find orders by user.
     */
    List<Order> findByUser(User user);
    
    /**
     * Find orders by user ID.
     */
    @Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
    List<Order> findByUserId(@Param("userId") Integer userId);

    /**
     * Find orders by order status.
     */
    List<Order> findByOrderStatus(String orderStatus);
    
    /**
     * Find orders by payment status.
     */
    List<Order> findByPaymentStatus(String paymentStatus);
    
    /**
     * Find orders by user and order status.
     */
    List<Order> findByUserAndOrderStatus(User user, String orderStatus);
    
    /**
     * Find orders by user and payment status.
     */
    List<Order> findByUserAndPaymentStatus(User user, String paymentStatus);
    
    /**
     * Find order by order ID.
     */
    Optional<Order> findByOrderId(Long orderId);
    
    /**
     * Delete order by order ID.
     */
    void deleteByOrderId(Long orderId);
    
    /**
     * Find orders with total price greater than specified amount.
     */
    @Query("SELECT o FROM Order o WHERE o.totalPrice > :minPrice")
    List<Order> findByTotalPriceGreaterThan(@Param("minPrice") Double minPrice);
}
