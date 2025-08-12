package com.groceteria.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.dto.PaymentDTO;
import com.groceteria.entity.Order;
import com.groceteria.entity.Payment;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.OrderRepository;
import com.groceteria.repository.PaymentRepository;
import com.groceteria.service.OrderService;
import com.groceteria.service.PaymentService;
import com.groceteria.service.UserService;

/**
 * Service implementation for Payment operations.
 * Provides payment management functionality with placeholder for future payment gateway integration.
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository,
            UserService userService, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public PaymentDTO addPayment(PaymentDTO paymentDTO, Long orderId, Integer userId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "orderId", orderId));
        
        User user = userService.getUserEntityById(userId);
        
        Payment payment = mapToEntity(paymentDTO);
        payment.setOrderId(orderId);
        payment.setTotalPrice(order.getTotalPrice());
        payment.setPaidDate(LocalDate.now());
        payment.setPaidAmount(order.getTotalPrice());
        payment.setUser(user);
        
        // Placeholder payment processing - does nothing for now
        boolean paymentProcessed = processPayment(paymentDTO);
        
        if (paymentProcessed) {
            order.setPaymentStatus("PAID");
            order.setOrderStatus("CONFIRMED");
        } else {
            order.setPaymentStatus("PENDING");
            order.setOrderStatus("PENDING");
        }
        
        orderRepository.save(order);
        Payment savedPayment = paymentRepository.save(payment);
        
        return mapToDTO(savedPayment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new ResourceNotFoundException("Payment", "Id", paymentId));
        return mapToDTO(payment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new ResourceNotFoundException("Payment", "Id", paymentId));
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(Integer userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return payments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findFirstByOrderId(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Payment", "orderId", orderId));
        return mapToDTO(payment);
    }
    
    @Override
    public List<PaymentDTO> getPaymentsByAmountRange(Double minAmount, Double maxAmount) {
        List<Payment> payments = paymentRepository.findByPaidAmountBetween(minAmount, maxAmount);
        return payments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<PaymentDTO> getPaymentsByAmountGreaterThan(Double amount) {
        List<Payment> payments = paymentRepository.findByPaidAmountGreaterThan(amount);
        return payments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public boolean processPayment(PaymentDTO paymentDTO) {
        // TODO: Implement actual payment gateway integration
        // For now, this is a placeholder that always returns true
        // In the future, this will integrate with payment gateways like Stripe, PayPal, etc.
        
        System.out.println("Payment processing placeholder - no actual payment gateway integration yet");
        System.out.println("Payment details: " + paymentDTO.toString());
        
        // Simulate payment processing delay
        try {
            Thread.sleep(1000); // Simulate 1 second processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // For now, always return true (payment successful)
        // In real implementation, this would depend on payment gateway response
        return true;
    }
    
    // Helper methods for entity-DTO mapping
    private Payment mapToEntity(PaymentDTO paymentDTO) {
        return Payment.builder()
            .paymentId(paymentDTO.getPaymentId())
            .totalPrice(paymentDTO.getTotalPrice())
            .orderId(paymentDTO.getOrderId())
            .paidDate(paymentDTO.getPaidDate())
            .paidAmount(paymentDTO.getPaidAmount())
            .build();
    }
    
    private PaymentDTO mapToDTO(Payment payment) {
        return PaymentDTO.builder()
            .paymentId(payment.getPaymentId())
            .totalPrice(payment.getTotalPrice())
            .orderId(payment.getOrderId())
            .paidDate(payment.getPaidDate())
            .paidAmount(payment.getPaidAmount())
            .userId(payment.getUser().getUserId())
            .build();
    }
}
