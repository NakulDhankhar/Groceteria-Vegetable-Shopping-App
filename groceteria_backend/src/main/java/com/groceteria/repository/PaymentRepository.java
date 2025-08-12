package com.groceteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.Payment;
import com.groceteria.entity.User;

/**
 * Repository interface for Payment entity operations.
 * Provides methods for payment management and tracking.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    /**
     * Find payments by order ID.
     */
    List<Payment> findByOrderId(Long orderId);
    
    /**
     * Find payment by order ID (assuming one payment per order).
     */
    Optional<Payment> findFirstByOrderId(Long orderId);
    
    /**
     * Find payments by user.
     */
    List<Payment> findByUser(User user);
    
    /**
     * Find payments by user ID.
     */
    @Query("SELECT p FROM Payment p WHERE p.user.userId = :userId")
    List<Payment> findByUserId(@Param("userId") Integer userId);
    
    /**
     * Find payments by paid amount range.
     */
    @Query("SELECT p FROM Payment p WHERE p.paidAmount BETWEEN :minAmount AND :maxAmount")
    List<Payment> findByPaidAmountBetween(@Param("minAmount") Double minAmount, @Param("maxAmount") Double maxAmount);
    
    /**
     * Find payments with paid amount greater than specified amount.
     */
    @Query("SELECT p FROM Payment p WHERE p.paidAmount > :amount")
    List<Payment> findByPaidAmountGreaterThan(@Param("amount") Double amount);
    
    /**
     * Count payments by user.
     */
    long countByUser(User user);
    
    /**
     * Count payments by user ID.
     */
    @Query("SELECT COUNT(p) FROM Payment p WHERE p.user.userId = :userId")
    long countByUserId(@Param("userId") Integer userId);
}
