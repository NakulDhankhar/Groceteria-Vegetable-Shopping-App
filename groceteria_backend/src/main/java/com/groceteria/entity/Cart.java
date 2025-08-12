package com.groceteria.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cart entity representing a shopping cart in the Groceteria system.
 * Aligned with the simplified, production-ready style and supporting USER and VENDOR roles.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
@Schema(description = "Cart entity representing a shopping cart in the Groceteria system")
public class Cart {
    /**
     * Unique identifier for the cart.
     */
    @Id
    @SequenceGenerator(name = "cart_id_generator", sequenceName = "cart_id_seq", initialValue = 5550, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_generator")
    @Column(name = "cart_id", nullable = false, updatable = false)
    @Schema(description = "Unique identifier for the cart", example = "5551", accessMode = Schema.AccessMode.READ_ONLY)
    private Long cartId;

    /**
     * Quantity of the item in the cart.
     */
    @Column(name = "quantity", nullable = false)
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity of the item in the cart", example = "2", required = true)
    private Long quantity;

    /**
     * MRP price of the item in the cart.
     */
    @Column(name = "mrp_price", nullable = false)
    @Min(value = 0, message = "MRP price must be non-negative")
    @Schema(description = "MRP price of the item in the cart", example = "30.0", required = true)
    private Double mrpPrice;

    /**
     * Item in the cart.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @Schema(description = "Item in the cart", hidden = true)
    private Item item;

    /**
     * User who owns the cart.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "User who owns the cart", hidden = true)
    private User user;
}
