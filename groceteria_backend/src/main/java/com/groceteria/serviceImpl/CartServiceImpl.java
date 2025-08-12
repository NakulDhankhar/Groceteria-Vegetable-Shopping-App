package com.groceteria.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.dto.CartDTO;
import com.groceteria.entity.Cart;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.CartRepository;
import com.groceteria.repository.ItemRepository;
import com.groceteria.service.CartService;
import com.groceteria.service.ItemService;
import com.groceteria.service.UserService;

/**
 * Service implementation for Cart operations.
 * Provides shopping cart management functionality.
 */
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private ItemRepository itemRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDTO addToCart(CartDTO cartDTO, Long itemId, Integer userId) {
        // Get item entity from repository directly
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
        User user = userService.getUserEntityById(userId);
        
        // Check if item already exists in user's cart
        Optional<Cart> existingCart = cartRepository.findByUserIdAndItemId(userId, itemId);
        
        if (existingCart.isPresent()) {
            // Update existing cart item
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + cartDTO.getQuantity());
            cart.setMrpPrice(item.getMrpPrice());
            
            Cart updatedCart = cartRepository.save(cart);
            return mapToDTO(updatedCart);
        } else {
            // Create new cart item
            Cart cart = mapToEntity(cartDTO);
            cart.setItem(item);
            cart.setMrpPrice(item.getMrpPrice());
            cart.setUser(user);
            
            Cart savedCart = cartRepository.save(cart);
            return mapToDTO(savedCart);
        }
    }

    @Override
    public List<CartDTO> getAllCartItems() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
        return mapToDTO(cart);
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO, Long cartId) {
        Cart existingCart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
        
        existingCart.setQuantity(cartDTO.getQuantity());
        existingCart.setMrpPrice(cartDTO.getMrpPrice());
        
        Cart updatedCart = cartRepository.save(existingCart);
        return mapToDTO(updatedCart);
    }
    
    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
        cartRepository.deleteById(cartId);
    }

    @Override
    public void deleteCartByUser(User user) {
        cartRepository.deleteByUser(user);
    }
    
    @Override
    public void deleteCartByUserId(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
    
    @Override
    public List<CartDTO> getCartByUser(User user) {
        List<Cart> carts = cartRepository.findByUser(user);
        return carts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<CartDTO> getCartByUserId(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        return carts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public CartDTO updateCartQuantity(Long cartId, Long quantity) {
        Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
        
        cart.setQuantity(quantity);
        Cart updatedCart = cartRepository.save(cart);
        return mapToDTO(updatedCart);
    }
    
    @Override
    public long getCartItemCountByUser(Integer userId) {
        return cartRepository.countByUserId(userId);
    }
    
    @Override
    public void clearUserCart(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
    
    // Helper methods for entity-DTO mapping
    private Cart mapToEntity(CartDTO cartDTO) {
        return Cart.builder()
            .cartId(cartDTO.getCartId())
            .quantity(cartDTO.getQuantity())
            .mrpPrice(cartDTO.getMrpPrice())
            .build();
    }
    
    private CartDTO mapToDTO(Cart cart) {
        return CartDTO.builder()
            .cartId(cart.getCartId())
            .quantity(cart.getQuantity())
            .mrpPrice(cart.getMrpPrice())
            .itemId(cart.getItem() != null ? cart.getItem().getItemId() : null)
            .userId(cart.getUser() != null ? cart.getUser().getUserId() : null)
            .build();
    }
}