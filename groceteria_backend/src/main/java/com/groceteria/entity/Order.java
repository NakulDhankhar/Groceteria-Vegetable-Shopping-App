package com.groceteria.entity;

import java.sql.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Order entity representing an order in the Groceteria system.
 * Aligned with the simplified, production-ready style and supporting USER and VENDOR roles.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Schema(description = "Order entity representing an order in the Groceteria system")
public class Order {
    /**
     * Unique identifier for the order.
     */
    @Id
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", initialValue = 300, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @Column(name = "order_id", nullable = false, updatable = false)
    @Schema(description = "Unique identifier for the order", example = "301", accessMode = Schema.AccessMode.READ_ONLY)
    private Long orderId;

    /**
     * Total price for the order.
     */
    @Column(name = "total_price", nullable = false)
    @Min(value = 0, message = "Total price must be non-negative")
    @Schema(description = "Total price for the order", example = "500.00", required = true)
    private Double totalPrice;

    /**
     * Order status (e.g., PENDING, COMPLETED, CANCELLED).
     */
    @Column(name = "order_status", nullable = false, length = 20)
    @NotBlank(message = "Order status is required")
    @Schema(description = "Order status", example = "PENDING", required = true)
    private String orderStatus;

    /**
     * Payment status (e.g., PAID, UNPAID).
     */
    @Column(name = "payment_status", nullable = false, length = 20)
    @NotBlank(message = "Payment status is required")
    @Schema(description = "Payment status", example = "PAID", required = true)
    private String paymentStatus;

    /**
     * Date when the order was placed.
     */
    @Column(name = "order_date", nullable = false)
    @NotNull(message = "Order date is required")
    @Schema(description = "Date when the order was placed", example = "2024-06-01", format = "date", required = true)
    private Date orderDate;

    /**
     * User who placed the order.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "User who placed the order", hidden = true)
    private User user;

    /**
     * Items included in the order.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @Schema(description = "List of items in the order")
    private List<Item> items;
}
