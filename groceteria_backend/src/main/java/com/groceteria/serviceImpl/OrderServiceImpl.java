package com.groceteria.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.dto.OrderDTO;
import com.groceteria.entity.Cart;
import com.groceteria.entity.Item;
import com.groceteria.entity.Order;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.CartRepository;
import com.groceteria.repository.ItemRepository;
import com.groceteria.repository.OrderRepository;
import com.groceteria.service.CartService;
import com.groceteria.service.ItemService;
import com.groceteria.service.OrderService;
import com.groceteria.service.UserService;

import jakarta.transaction.Transactional;

/**
 * Service implementation for Order operations.
 * Provides order management functionality.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ItemService itemService, CartService cartService,
            UserService userService, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
        this.cartService = cartService;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO, Integer userId) {
        User user = userService.getUserEntityById(userId);
        
        Order order = mapToEntity(orderDTO);
        order.setUser(user);
        order.setOrderDate(new Date(System.currentTimeMillis()));
        order.setOrderStatus("PENDING");
        order.setPaymentStatus("PENDING");
        
        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        return mapToDTO(order);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO, Long orderId) {
        Order existingOrder = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        
        existingOrder.setTotalPrice(orderDTO.getTotalPrice());
        existingOrder.setPaymentStatus(orderDTO.getPaymentStatus());
        existingOrder.setOrderStatus(orderDTO.getOrderStatus());
        
        Order updatedOrder = orderRepository.save(existingOrder);
        return mapToDTO(updatedOrder);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<OrderDTO> getOrdersByStatus(String orderStatus) {
        List<Order> orders = orderRepository.findByOrderStatus(orderStatus);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<OrderDTO> getOrdersByPaymentStatus(String paymentStatus) {
        List<Order> orders = orderRepository.findByPaymentStatus(paymentStatus);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<OrderDTO> getOrdersByUserAndStatus(Integer userId, String orderStatus) {
        User user = userService.getUserEntityById(userId);
        List<Order> orders = orderRepository.findByUserAndOrderStatus(user, orderStatus);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public OrderDTO updateOrderStatus(Long orderId, String orderStatus) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        
        order.setOrderStatus(orderStatus);
        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }
    
    @Override
    public OrderDTO updatePaymentStatus(Long orderId, String paymentStatus) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
        
        order.setPaymentStatus(paymentStatus);
        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }
    
    @Override
    public List<OrderDTO> getOrdersByTotalPriceGreaterThan(Double minPrice) {
        List<Order> orders = orderRepository.findByTotalPriceGreaterThan(minPrice);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    // Helper methods for entity-DTO mapping
    private Order mapToEntity(OrderDTO orderDTO) {
        return Order.builder()
            .orderId(orderDTO.getOrderId())
            .totalPrice(orderDTO.getTotalPrice())
            .orderStatus(orderDTO.getOrderStatus())
            .paymentStatus(orderDTO.getPaymentStatus())
            .orderDate(orderDTO.getOrderDate())
            .build();
    }
    
    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
            .orderId(order.getOrderId())
            .totalPrice(order.getTotalPrice())
            .orderStatus(order.getOrderStatus())
            .paymentStatus(order.getPaymentStatus())
            .orderDate(order.getOrderDate())
            .userId(order.getUser() != null ? order.getUser().getUserId() : null)
            .build();
    }
}
