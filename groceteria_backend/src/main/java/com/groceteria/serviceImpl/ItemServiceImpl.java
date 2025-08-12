package com.groceteria.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.groceteria.dto.ItemDTO;
import com.groceteria.dto.ItemPagingDTO;
import com.groceteria.entity.Category;
import com.groceteria.entity.Item;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.ItemRepository;
import com.groceteria.service.ItemService;
import com.groceteria.service.UserService;

/**
 * Service implementation for Item operations.
 * Provides item management functionality with support for vendor relationships.
 */
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public ItemDTO addItem(ItemDTO itemDTO, Integer vendorId) {
        // Verify vendor exists and has VENDOR role
        User vendor = userService.getUserEntityById(vendorId);
        if (!"VENDOR".equals(vendor.getRole())) {
            throw new IllegalArgumentException("User must have VENDOR role to add items");
        }
        
        Item item = mapToEntity(itemDTO);
        item.setVendor(vendor);
        
        Item savedItem = itemRepository.save(item);
        return mapToDTO(savedItem);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
        return mapToDTO(item);
    }

    @Override
    public ItemDTO updateItemQuantity(Long itemId, Long availableQuantity) {
        Item existingItem = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "itemId", itemId));
        
        existingItem.setQuantity(availableQuantity);
        Item updatedItem = itemRepository.save(existingItem);
        return mapToDTO(updatedItem);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO, Long itemId) {
        Item existingItem = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "itemId", itemId));
        
        existingItem.setItemName(itemDTO.getItemName());
        existingItem.setMrpPrice(itemDTO.getMrpPrice());
        existingItem.setImage(itemDTO.getImage());
        existingItem.setDescription(itemDTO.getDescription());
        existingItem.setQuantity(itemDTO.getQuantity());
        existingItem.setCategory(itemDTO.getCategory());
        
        Item updatedItem = itemRepository.save(existingItem);
        return mapToDTO(updatedItem);
    }

    @Override
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
            .orElseThrow(() -> new ResourceNotFoundException("Item", "Id", itemId));
        itemRepository.deleteById(itemId);
    }

    @Override
    public List<ItemDTO> findItemsByCategory(Category category) {
        List<Item> items = itemRepository.findByCategory(category);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ItemPagingDTO findItemsByCategory(Category category, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findByCategory(category, paging);
        
        ItemPagingDTO ir = new ItemPagingDTO();
        ir.setTotalItems(itemResult.getTotalElements());
        
        if (itemResult.hasContent()) {
            List<ItemDTO> itemDTOs = itemResult.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
            ir.setItems(itemDTOs);
        } else {
            ir.setItems(new ArrayList<>());
        }
        return ir;
    }

    @Override
    public ItemPagingDTO getAllItems(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findAll(paging);
        
        ItemPagingDTO ir = new ItemPagingDTO();
        ir.setTotalItems(itemResult.getTotalElements());
        
        if (itemResult.hasContent()) {
            List<ItemDTO> itemDTOs = itemResult.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
            ir.setItems(itemDTOs);
        } else {
            ir.setItems(new ArrayList<>());
        }
        return ir;
    }

    @Override
    public List<ItemDTO> findItemsByMrpPrice(Double mrpPrice) {
        List<Item> items = itemRepository.findByMrpPrice(mrpPrice);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ItemPagingDTO findItemsByName(String keyword, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findByItemNameContainingIgnoreCase(keyword, paging);
        
        ItemPagingDTO ir = new ItemPagingDTO();
        ir.setTotalItems(itemResult.getTotalElements());
        
        if (itemResult.hasContent()) {
            List<ItemDTO> itemDTOs = itemResult.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
            ir.setItems(itemDTOs);
        } else {
            ir.setItems(new ArrayList<>());
        }
        return ir;
    }
    
    @Override
    public List<ItemDTO> getItemsByVendor(Integer vendorId) {
        List<Item> items = itemRepository.findByVendorId(vendorId);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public ItemPagingDTO getItemsByVendor(Integer vendorId, Integer pageNo, Integer pageSize) {
        User vendor = userService.getUserEntityById(vendorId);
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findByVendor(vendor, paging);
        
        ItemPagingDTO ir = new ItemPagingDTO();
        ir.setTotalItems(itemResult.getTotalElements());
        
        if (itemResult.hasContent()) {
            List<ItemDTO> itemDTOs = itemResult.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
            ir.setItems(itemDTOs);
        } else {
            ir.setItems(new ArrayList<>());
        }
        return ir;
    }
    
    @Override
    public List<ItemDTO> findItemsByPriceRange(Double minPrice, Double maxPrice) {
        List<Item> items = itemRepository.findByPriceRange(minPrice, maxPrice);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<ItemDTO> findItemsByCategoryAndPriceRange(Category category, Double minPrice, Double maxPrice) {
        List<Item> items = itemRepository.findByCategoryAndPriceRange(category, minPrice, maxPrice);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public List<ItemDTO> getAvailableItems() {
        List<Item> items = itemRepository.findByQuantityGreaterThan(0L);
        return items.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    public ItemPagingDTO getAvailableItems(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Item> itemResult = itemRepository.findByQuantityGreaterThan(0L, paging);
        
        ItemPagingDTO ir = new ItemPagingDTO();
        ir.setTotalItems(itemResult.getTotalElements());
        
        if (itemResult.hasContent()) {
            List<ItemDTO> itemDTOs = itemResult.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
            ir.setItems(itemDTOs);
        } else {
            ir.setItems(new ArrayList<>());
        }
        return ir;
    }
    
    // Helper methods for entity-DTO mapping
    private Item mapToEntity(ItemDTO itemDTO) {
        return Item.builder()
            .itemId(itemDTO.getItemId())
            .itemName(itemDTO.getItemName())
            .image(itemDTO.getImage())
            .description(itemDTO.getDescription())
            .mrpPrice(itemDTO.getMrpPrice())
            .quantity(itemDTO.getQuantity())
            .category(itemDTO.getCategory())
            .build();
    }
    
    private ItemDTO mapToDTO(Item item) {
        return ItemDTO.builder()
            .itemId(item.getItemId())
            .itemName(item.getItemName())
            .image(item.getImage())
            .description(item.getDescription())
            .mrpPrice(item.getMrpPrice())
            .quantity(item.getQuantity())
            .category(item.getCategory())
            .vendorId(item.getVendor() != null ? item.getVendor().getUserId() : null)
            .build();
    }
}
