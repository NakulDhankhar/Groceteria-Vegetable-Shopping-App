package com.groceteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.Cart;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;

/**
 * Repository interface for Cart entity operations.
 * Provides methods for shopping cart management.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
   
    /**
     * Find cart items by user.
     */
    List<Cart> findByUser(User user);
    
    /**
     * Find cart items by user ID.
     */
    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    List<Cart> findByUserId(@Param("userId") Integer userId);
    
    /**
     * Find cart item by user and item.
     */
    Optional<Cart> findByUserAndItem(User user, Item item);
    
    /**
     * Find cart item by user ID and item ID.
     */
    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId AND c.item.itemId = :itemId")
    Optional<Cart> findByUserIdAndItemId(@Param("userId") Integer userId, @Param("itemId") Long itemId);
    
    /**
     * Delete cart items by user.
     */
    void deleteByUser(User user);
    
    /**
     * Delete cart items by user ID.
     */
    @Query("DELETE FROM Cart c WHERE c.user.userId = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
    
    /**
     * Count cart items by user.
     */
    long countByUser(User user);
    
    /**
     * Count cart items by user ID.
     */
    @Query("SELECT COUNT(c) FROM Cart c WHERE c.user.userId = :userId")
    long countByUserId(@Param("userId") Integer userId);
}
