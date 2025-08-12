package com.groceteria.service;

import java.util.List;

import com.groceteria.dto.PaymentDTO;

/**
 * Service interface for Payment operations.
 * Provides payment management functionality.
 * Note: Payment gateway integration is planned for future implementation.
 */
public interface PaymentService {
    
    /**
     * Add a new payment record.
     * Note: This is a placeholder for future payment gateway integration.
     */
    PaymentDTO addPayment(PaymentDTO paymentDTO, Long orderId, Integer userId);
    
    /**
     * Get all payments.
     */
    List<PaymentDTO> getAllPayments();
    
    /**
     * Get payment by ID.
     */
    PaymentDTO getPaymentById(Long paymentId);
    
    /**
     * Delete payment by ID.
     */
    void deletePayment(Long paymentId);
    
    /**
     * Get all payments by user ID.
     */
    List<PaymentDTO> getPaymentsByUserId(Integer userId);
    
    /**
     * Get payment by order ID.
     */
    PaymentDTO getPaymentByOrderId(Long orderId);
    
    /**
     * Get payments by amount range.
     */
    List<PaymentDTO> getPaymentsByAmountRange(Double minAmount, Double maxAmount);
    
    /**
     * Get payments with amount greater than specified value.
     */
    List<PaymentDTO> getPaymentsByAmountGreaterThan(Double amount);
    
    /**
     * Process payment (placeholder for future payment gateway).
     * Currently does nothing - will be implemented with actual payment gateway.
     */
    boolean processPayment(PaymentDTO paymentDTO);
}
