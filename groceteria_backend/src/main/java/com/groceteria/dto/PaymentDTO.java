package com.groceteria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for Payment data transfer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for Payment data transfer")
public class PaymentDTO {
    @Schema(description = "Payment ID", example = "101")
    private Long paymentId;

    @Schema(description = "Total price of the order", example = "250.75")
    private Double totalPrice;

    @Schema(description = "Order ID", example = "5001")
    private Long orderId;

    @Schema(description = "Paid date", example = "2024-06-01")
    private LocalDate paidDate;

    @Schema(description = "Paid amount", example = "250.75")
    private Double paidAmount;

    @Schema(description = "User ID", example = "1001")
    private Integer userId;
} 