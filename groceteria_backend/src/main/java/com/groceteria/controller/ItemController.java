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

import com.groceteria.dto.ItemDTO;
import com.groceteria.dto.ItemPagingDTO;
import com.groceteria.entity.Category;
import com.groceteria.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * REST Controller for Item management operations.
 * Provides endpoints for item CRUD operations, search, and vendor management.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Item Management", description = "APIs for item management, search, and vendor operations")
public class ItemController {
    
	@Autowired
	private ItemService itemService;
	
    /**
     * Add a new item (for vendors only).
     */
    @PostMapping
    @Operation(summary = "Add new item", description = "Creates a new item (vendors only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Item created successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "403", description = "Only vendors can add items")
    })
    public ResponseEntity<ItemDTO> addItem(
            @Valid @RequestBody ItemDTO itemDTO,
            @Parameter(description = "Vendor ID") @RequestParam Integer vendorId) {
        ItemDTO createdItem = itemService.addItem(itemDTO, vendorId);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
    
    /**
     * Get all items.
     */
    @GetMapping
    @Operation(summary = "Get all items", description = "Retrieves all items in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get item by ID.
     */
    @GetMapping("/{itemId}")
    @Operation(summary = "Get item by ID", description = "Retrieves item information by item ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class))),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public ResponseEntity<ItemDTO> getItemById(
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId) {
        ItemDTO item = itemService.getItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    
    /**
     * Update item information.
     */
    @PutMapping("/{itemId}")
    @Operation(summary = "Update item", description = "Updates item information by item ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item updated successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class))),
        @ApiResponse(responseCode = "404", description = "Item not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ItemDTO> updateItem(
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId,
            @Valid @RequestBody ItemDTO itemDTO) {
        ItemDTO updatedItem = itemService.updateItem(itemDTO, itemId);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    
    /**
     * Update item quantity.
     */
    @PutMapping("/{itemId}/quantity")
    @Operation(summary = "Update item quantity", description = "Updates the available quantity of an item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Quantity updated successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class))),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public ResponseEntity<ItemDTO> updateItemQuantity(
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId,
            @Parameter(description = "Available quantity") @RequestParam Long quantity) {
        ItemDTO updatedItem = itemService.updateItemQuantity(itemId, quantity);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
    
    /**
     * Delete item by ID.
     */
    @DeleteMapping("/{itemId}")
    @Operation(summary = "Delete item", description = "Deletes item by item ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Item not found")
    })
    public ResponseEntity<Void> deleteItem(
            @Parameter(description = "Item ID") @PathVariable("itemId") Long itemId) {
		itemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * Get items by category.
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Get items by category", description = "Retrieves all items in a specific category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getItemsByCategory(
            @Parameter(description = "Category") @PathVariable("category") Category category) {
        List<ItemDTO> items = itemService.findItemsByCategory(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get items by category with pagination.
     */
    @GetMapping("/category/{category}/paged")
    @Operation(summary = "Get items by category with pagination", description = "Retrieves items in a category with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemPagingDTO.class)))
    })
    public ResponseEntity<ItemPagingDTO> getItemsByCategoryPaged(
            @Parameter(description = "Category") @PathVariable("category") Category category,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
        ItemPagingDTO items = itemService.findItemsByCategory(category, pageNo, pageSize);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get all items with pagination.
     */
    @GetMapping("/paged")
    @Operation(summary = "Get all items with pagination", description = "Retrieves all items with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemPagingDTO.class)))
    })
    public ResponseEntity<ItemPagingDTO> getAllItemsPaged(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
        ItemPagingDTO items = itemService.getAllItems(pageNo, pageSize);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get items by MRP price.
     */
    @GetMapping("/price/{mrpPrice}")
    @Operation(summary = "Get items by MRP price", description = "Retrieves items with specific MRP price")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getItemsByMrpPrice(
            @Parameter(description = "MRP price") @PathVariable("mrpPrice") Double mrpPrice) {
        List<ItemDTO> items = itemService.findItemsByMrpPrice(mrpPrice);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Search items by name with pagination.
     */
    @GetMapping("/search")
    @Operation(summary = "Search items by name", description = "Searches items by name with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemPagingDTO.class)))
    })
    public ResponseEntity<ItemPagingDTO> searchItemsByName(
            @Parameter(description = "Search keyword") @RequestParam String keyword,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
        ItemPagingDTO items = itemService.findItemsByName(keyword, pageNo, pageSize);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get items by vendor.
     */
    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "Get items by vendor", description = "Retrieves all items from a specific vendor")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getItemsByVendor(
            @Parameter(description = "Vendor ID") @PathVariable("vendorId") Integer vendorId) {
        List<ItemDTO> items = itemService.getItemsByVendor(vendorId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get items by vendor with pagination.
     */
    @GetMapping("/vendor/{vendorId}/paged")
    @Operation(summary = "Get items by vendor with pagination", description = "Retrieves items from a vendor with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemPagingDTO.class)))
    })
    public ResponseEntity<ItemPagingDTO> getItemsByVendorPaged(
            @Parameter(description = "Vendor ID") @PathVariable("vendorId") Integer vendorId,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
        ItemPagingDTO items = itemService.getItemsByVendor(vendorId, pageNo, pageSize);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get items by price range.
     */
    @GetMapping("/price-range")
    @Operation(summary = "Get items by price range", description = "Retrieves items within a price range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getItemsByPriceRange(
            @Parameter(description = "Minimum price") @RequestParam Double minPrice,
            @Parameter(description = "Maximum price") @RequestParam Double maxPrice) {
        List<ItemDTO> items = itemService.findItemsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get available items (quantity > 0).
     */
    @GetMapping("/available")
    @Operation(summary = "Get available items", description = "Retrieves items with quantity greater than 0")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemDTO.class)))
    })
    public ResponseEntity<List<ItemDTO>> getAvailableItems() {
        List<ItemDTO> items = itemService.getAvailableItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    /**
     * Get available items with pagination.
     */
    @GetMapping("/available/paged")
    @Operation(summary = "Get available items with pagination", description = "Retrieves available items with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Items retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ItemPagingDTO.class)))
    })
    public ResponseEntity<ItemPagingDTO> getAvailableItemsPaged(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") Integer pageNo,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer pageSize) {
        ItemPagingDTO items = itemService.getAvailableItems(pageNo, pageSize);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
