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

import com.groceteria.dto.CartDTO;
import com.groceteria.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for Cart management operations.
 * Provides endpoints for shopping cart management and operations.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Cart Management", description = "APIs for shopping cart management and operations")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    /**
     * Add item to cart.
     */
    @PostMapping
    @Operation(summary = "Add item to cart", description = "Adds an item to the user's shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Item added to cart successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Item or user not found")
    })
    public ResponseEntity<CartDTO> addToCart(
            @Valid @RequestBody CartDTO cartDTO,
            @Parameter(description = "Item ID") @RequestParam Long itemId,
            @Parameter(description = "User ID") @RequestParam Integer userId) {
        CartDTO addedCart = cartService.addToCart(cartDTO, itemId, userId);
        return new ResponseEntity<>(addedCart, HttpStatus.CREATED);
    }
    
    /**
     * Get all cart items.
     */
    @GetMapping
    @Operation(summary = "Get all cart items", description = "Retrieves all cart items in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class)))
    })
    public ResponseEntity<List<CartDTO>> getAllCartItems() {
        List<CartDTO> cartItems = cartService.getAllCartItems();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    
    /**
     * Get cart item by ID.
     */
    @GetMapping("/{cartId}")
    @Operation(summary = "Get cart item by ID", description = "Retrieves cart item information by cart ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    public ResponseEntity<CartDTO> getCartById(
            @Parameter(description = "Cart ID") @PathVariable("cartId") Long cartId) {
        CartDTO cartItem = cartService.getCartById(cartId);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
    
    /**
     * Update cart item.
     */
    @PutMapping("/{cartId}")
    @Operation(summary = "Update cart item", description = "Updates cart item information by cart ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item updated successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cart item not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<CartDTO> updateCart(
            @Parameter(description = "Cart ID") @PathVariable("cartId") Long cartId,
            @Valid @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(cartDTO, cartId);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
    
    /**
     * Delete cart item by ID.
     */
    @DeleteMapping("/{cartId}")
    @Operation(summary = "Delete cart item", description = "Deletes cart item by cart ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    public ResponseEntity<Void> deleteCart(
            @Parameter(description = "Cart ID") @PathVariable("cartId") Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Get cart items by user ID.
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get cart items by user", description = "Retrieves all cart items for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class)))
    })
    public ResponseEntity<List<CartDTO>> getCartByUserId(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        List<CartDTO> cartItems = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }
    
    /**
     * Update cart item quantity.
     */
    @PutMapping("/{cartId}/quantity")
    @Operation(summary = "Update cart item quantity", description = "Updates the quantity of a cart item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quantity updated successfully",
                    content = @Content(schema = @Schema(implementation = CartDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    public ResponseEntity<CartDTO> updateCartQuantity(
            @Parameter(description = "Cart ID") @PathVariable("cartId") Long cartId,
            @Parameter(description = "New quantity") @RequestParam Long quantity) {
        CartDTO updatedCart = cartService.updateCartQuantity(cartId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
    
    /**
     * Get cart item count by user.
     */
    @GetMapping("/user/{userId}/count")
    @Operation(summary = "Get cart item count", description = "Retrieves the number of items in user's cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item count retrieved successfully")
    })
    public ResponseEntity<Long> getCartItemCountByUser(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        long count = cartService.getCartItemCountByUser(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    /**
     * Clear user's cart.
     */
    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Clear user's cart", description = "Removes all items from user's cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart cleared successfully")
    })
    public ResponseEntity<Void> clearUserCart(
            @Parameter(description = "User ID") @PathVariable("userId") Integer userId) {
        cartService.clearUserCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
