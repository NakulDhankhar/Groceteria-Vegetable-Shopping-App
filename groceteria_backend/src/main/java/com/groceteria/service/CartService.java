package com.groceteria.service;

import java.util.List;

import com.groceteria.dto.CartDTO;
import com.groceteria.entity.Cart;
import com.groceteria.entity.User;

/**
 * Service interface for Cart operations.
 * Provides shopping cart management functionality.
 */
public interface CartService {
    
    /**
     * Add item to cart.
     */
    CartDTO addToCart(CartDTO cartDTO, Long itemId, Integer userId);
    
    /**
     * Get all cart items.
     */
    List<CartDTO> getAllCartItems();
    
    /**
     * Get cart item by ID.
     */
    CartDTO getCartById(Long cartId);
    
    /**
     * Update cart item.
     */
    CartDTO updateCart(CartDTO cartDTO, Long cartId);
    
    /**
     * Delete cart item by ID.
     */
    void deleteCart(Long cartId);
    
    /**
     * Delete all cart items by user.
     */
    void deleteCartByUser(User user);
    
    /**
     * Delete all cart items by user ID.
     */
    void deleteCartByUserId(Integer userId);
    
    /**
     * Get cart items by user.
     */
    List<CartDTO> getCartByUser(User user);
    
    /**
     * Get cart items by user ID.
     */
    List<CartDTO> getCartByUserId(Integer userId);
    
    /**
     * Update cart item quantity.
     */
    CartDTO updateCartQuantity(Long cartId, Long quantity);
    
    /**
     * Get cart item count by user.
     */
    long getCartItemCountByUser(Integer userId);
    
    /**
     * Clear user's cart.
     */
    void clearUserCart(Integer userId);
}
