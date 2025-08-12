package com.groceteria.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.groceteria.entity.Category;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;

/**
 * Repository interface for Item entity operations.
 * Provides methods for item management with support for vendor relationships.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, PagingAndSortingRepository<Item, Long> {
    
    /**
     * Find items by category.
     */
    List<Item> findByCategory(Category category);
    
    /**
     * Find items by category with pagination.
     */
    Page<Item> findByCategory(Category category, Pageable page);
    
    /**
     * Find items by MRP price.
     */
    @Query("SELECT i FROM Item i WHERE i.mrpPrice = :mrpPrice")
    List<Item> findByMrpPrice(@Param("mrpPrice") Double mrpPrice);
    
    /**
     * Find items by name containing keyword with pagination.
     */
    Page<Item> findByItemNameContainingIgnoreCase(String keyword, Pageable paging);
    
    /**
     * Find items by vendor (User with VENDOR role).
     */
    List<Item> findByVendor(User vendor);
    
    /**
     * Find items by vendor with pagination.
     */
    Page<Item> findByVendor(User vendor, Pageable page);
    
    /**
     * Find items by vendor ID.
     */
    @Query("SELECT i FROM Item i WHERE i.vendor.userId = :vendorId")
    List<Item> findByVendorId(@Param("vendorId") Integer vendorId);
    
    /**
     * Find items by category and vendor.
     */
    List<Item> findByCategoryAndVendor(Category category, User vendor);
    
    /**
     * Find items with quantity greater than zero (available items).
     */
    List<Item> findByQuantityGreaterThan(Long quantity);
    
    /**
     * Find items with quantity greater than zero (available items) with pagination.
     */
    Page<Item> findByQuantityGreaterThan(Long quantity, Pageable page);
    
    /**
     * Find items by price range.
     */
    @Query("SELECT i FROM Item i WHERE i.mrpPrice BETWEEN :minPrice AND :maxPrice")
    List<Item> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
    
    /**
     * Find items by category and price range.
     */
    @Query("SELECT i FROM Item i WHERE i.category = :category AND i.mrpPrice BETWEEN :minPrice AND :maxPrice")
    List<Item> findByCategoryAndPriceRange(@Param("category") Category category, 
                                         @Param("minPrice") Double minPrice, 
                                         @Param("maxPrice") Double maxPrice);
}
