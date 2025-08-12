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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groceteria.dto.PaymentDTO;
import com.groceteria.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for Payment management operations.
 * Provides endpoints for payment processing and management.
 * Note: Payment gateway integration is planned for future implementation.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payment Management", description = "APIs for payment processing and management (placeholder for future payment gateway)")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    /**
     * Add a new payment record.
     * Note: This is a placeholder for future payment gateway integration.
     */
    @PostMapping
    @Operation(summary = "Add payment record", description = "Creates a new payment record (placeholder for future payment gateway)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment record created successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Order or user not found")
    })
    public ResponseEntity<PaymentDTO> addPayment(
            @Valid @RequestBody PaymentDTO paymentDTO,
            @Parameter(description = "Order ID") @RequestParam Long orderId,
            @Parameter(description = "User ID") @RequestParam Integer userId) {
        PaymentDTO createdPayment = paymentService.addPayment(paymentDTO, orderId, userId);
        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
    }
    
    /**
     * Get all payments.
     */
    @GetMapping
    @Operation(summary = "Get all payments", description = "Retrieves all payment records in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class)))
    })
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    /**
     * Get payment by ID.
     */
    @GetMapping("/{paymentId}")
    @Operation(summary = "Get payment by ID", description = "Retrieves payment information by payment ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class))),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<PaymentDTO> getPaymentById(
            @Parameter(description = "Payment ID") @PathVariable("paymentId") Long paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    
    /**
     * Delete payment by ID.
     */
    @DeleteMapping("/{paymentId}")
    @Operation(summary = "Delete payment", description = "Deletes payment record by payment ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<Void> deletePayment(
            @Parameter(description = "Payment ID") @PathVariable("paymentId") Long paymentId) {
        paymentService.deletePayment(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Get payments by user ID.
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get payments by user", description = "Retrieves all payments for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class)))
    })
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    /**
     * Get payment by order ID.
     */
    @GetMapping("/order/{orderId}")
    @Operation(summary = "Get payment by order", description = "Retrieves payment information for a specific order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class))),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<PaymentDTO> getPaymentByOrderId(
            @Parameter(description = "Order ID") @PathVariable("orderId") Long orderId) {
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }
    
    /**
     * Get payments by amount range.
     */
    @GetMapping("/amount-range")
    @Operation(summary = "Get payments by amount range", description = "Retrieves payments within a specific amount range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class)))
    })
    public ResponseEntity<List<PaymentDTO>> getPaymentsByAmountRange(
            @Parameter(description = "Minimum amount") @RequestParam Double minAmount,
            @Parameter(description = "Maximum amount") @RequestParam Double maxAmount) {
        List<PaymentDTO> payments = paymentService.getPaymentsByAmountRange(minAmount, maxAmount);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    /**
     * Get payments with amount greater than specified value.
     */
    @GetMapping("/amount-greater-than")
    @Operation(summary = "Get payments by minimum amount", description = "Retrieves payments with amount greater than specified value")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PaymentDTO.class)))
    })
    public ResponseEntity<List<PaymentDTO>> getPaymentsByAmountGreaterThan(
            @Parameter(description = "Minimum amount") @RequestParam Double amount) {
        List<PaymentDTO> payments = paymentService.getPaymentsByAmountGreaterThan(amount);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
    
    /**
     * Process payment (placeholder for future payment gateway).
     * Currently does nothing - will be implemented with actual payment gateway.
     */
    @PostMapping("/process")
    @Operation(summary = "Process payment", description = "Processes a payment (placeholder for future payment gateway)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid payment data"),
        @ApiResponse(responseCode = "500", description = "Payment processing failed")
    })
    public ResponseEntity<Boolean> processPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        boolean success = paymentService.processPayment(paymentDTO);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
