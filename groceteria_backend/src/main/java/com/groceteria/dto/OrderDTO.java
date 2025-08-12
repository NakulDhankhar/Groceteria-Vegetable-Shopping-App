package com.groceteria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * DTO for Order data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for Order data transfer")
public class OrderDTO {
    @Schema(description = "Order ID", example = "301")
    private Long orderId;

    @Schema(description = "Total price", example = "500.00")
    private Double totalPrice;

    @Schema(description = "Order status", example = "PENDING")
    private String orderStatus;

    @Schema(description = "Payment status", example = "PAID")
    private String paymentStatus;

    @Schema(description = "Order date", example = "2024-06-01")
    private Date orderDate;

    @Schema(description = "User ID", example = "1001")
    private Integer userId;

    @Schema(description = "List of item IDs in the order")
    private List<Long> itemIds;
} 