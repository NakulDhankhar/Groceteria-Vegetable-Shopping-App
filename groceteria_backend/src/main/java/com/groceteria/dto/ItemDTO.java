package com.groceteria.dto;

import com.groceteria.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Item data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for Item data transfer")
public class ItemDTO {
    @Schema(description = "Item ID", example = "501")
    private Long itemId;

    @Schema(description = "Name of the item", example = "Tomato")
    private String itemName;

    @Schema(description = "Image URL", example = "https://example.com/tomato.jpg")
    private String image;

    @Schema(description = "Description", example = "Fresh red tomatoes")
    private String description;

    @Schema(description = "MRP price", example = "30.0")
    private Double mrpPrice;

    @Schema(description = "Quantity available", example = "100")
    private Long quantity;

    @Schema(description = "Category", example = "VEGETABLES")
    private Category category;

    @Schema(description = "Vendor ID", example = "2001")
    private Integer vendorId;
} 