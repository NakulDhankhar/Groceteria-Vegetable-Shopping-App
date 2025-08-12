package com.groceteria.service;

import java.util.List;

import com.groceteria.dto.ItemDTO;
import com.groceteria.dto.ItemPagingDTO;
import com.groceteria.entity.Category;
import com.groceteria.entity.Item;

/**
 * Service interface for Item operations.
 * Provides item management functionality with support for vendor relationships.
 */
public interface ItemService {
    
    /**
     * Add a new item (for vendors).
     */
    ItemDTO addItem(ItemDTO itemDTO, Integer vendorId);
    
    /**
     * Get all items.
     */
    List<ItemDTO> getAllItems();
    
    /**
     * Get item by ID.
     */
    ItemDTO getItemById(Long itemId);
    
    /**
     * Update item quantity.
     */
    ItemDTO updateItemQuantity(Long itemId, Long availableQuantity);
    
    /**
     * Update item information.
     */
    ItemDTO updateItem(ItemDTO itemDTO, Long itemId);
    
    /**
     * Delete item by ID.
     */
    void deleteItem(Long itemId);
    
    /**
     * Find items by category.
     */
    List<ItemDTO> findItemsByCategory(Category category);
    
    /**
     * Find items by category with pagination.
     */
    ItemPagingDTO findItemsByCategory(Category category, Integer pageNo, Integer pageSize);
    
    /**
     * Get all items with pagination.
     */
    ItemPagingDTO getAllItems(Integer pageNo, Integer pageSize);
    
    /**
     * Find items by MRP price.
     */
    List<ItemDTO> findItemsByMrpPrice(Double mrpPrice);
    
    /**
     * Find items by name with pagination.
     */
    ItemPagingDTO findItemsByName(String keyword, Integer pageNo, Integer pageSize);
    
    /**
     * Get items by vendor.
     */
    List<ItemDTO> getItemsByVendor(Integer vendorId);
    
    /**
     * Get items by vendor with pagination.
     */
    ItemPagingDTO getItemsByVendor(Integer vendorId, Integer pageNo, Integer pageSize);
    
    /**
     * Find items by price range.
     */
    List<ItemDTO> findItemsByPriceRange(Double minPrice, Double maxPrice);
    
    /**
     * Find items by category and price range.
     */
    List<ItemDTO> findItemsByCategoryAndPriceRange(Category category, Double minPrice, Double maxPrice);
    
    /**
     * Get available items (quantity > 0).
     */
    List<ItemDTO> getAvailableItems();
    
    /**
     * Get available items with pagination.
     */
    ItemPagingDTO getAvailableItems(Integer pageNo, Integer pageSize);
}
