package com.groceteria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Cart data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for Cart data transfer")
public class CartDTO {
    @Schema(description = "Cart ID", example = "5551")
    private Long cartId;

    @Schema(description = "Quantity of the item in the cart", example = "2")
    private Long quantity;

    @Schema(description = "MRP price of the item in the cart", example = "30.0")
    private Double mrpPrice;

    @Schema(description = "Item ID", example = "501")
    private Long itemId;

    @Schema(description = "User ID", example = "1001")
    private Integer userId;
} 