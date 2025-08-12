package com.groceteria.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payment entity representing a payment record in the Groceteria system.
 * Aligned with the simplified, production-ready style of the User entity.
 *
 * @author Personal Project
 * @version 1.0
 * @since 2024
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@Schema(description = "Payment entity representing a payment record in the Groceteria system")
public class Payment {

    /**
     * Unique identifier for the payment.
     */
    @Id
    @SequenceGenerator(name = "payment_id_generator", sequenceName = "payment_id_seq", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_generator")
    @Column(name = "payment_id", nullable = false, updatable = false)
    @Schema(description = "Unique identifier for the payment", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Long paymentId;

    /**
     * Total price of the order associated with this payment.
     */
    @Column(name = "total_price", nullable = false)
    @Min(value = 0, message = "Total price must be non-negative")
    @Schema(description = "Total price of the order", example = "250.75", required = true)
    private Double totalPrice;

    /**
     * Order ID associated with this payment.
     */
    @Column(name = "order_id", nullable = false, unique = true)
    @NotNull(message = "Order ID is required")
    @Schema(description = "Order ID associated with this payment", example = "5001", required = true)
    private Long orderId;

    /**
     * Date when the payment was made.
     */
    @Column(name = "paid_date", nullable = false)
    @NotNull(message = "Paid date is required")
    @Schema(description = "Date when the payment was made", example = "2024-06-01", format = "date", required = true)
    private LocalDate paidDate;

    /**
     * Amount paid by the user.
     */
    @Column(name = "paid_amount", nullable = false)
    @Min(value = 0, message = "Paid amount must be non-negative")
    @Schema(description = "Amount paid by the user", example = "250.75", required = true)
    private Double paidAmount;

    /**
     * User who made the payment.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @Schema(description = "User who made the payment", hidden = true)
    private User user;
}
