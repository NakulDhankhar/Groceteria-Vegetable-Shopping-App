package com.groceteria.dto;

import com.groceteria.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for paginated item results.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for paginated item results")
public class ItemPagingDTO {
    @Schema(description = "List of items on the current page")
    private List<ItemDTO> items;

    @Schema(description = "Total number of items available", example = "100")
    private long totalItems;
} 