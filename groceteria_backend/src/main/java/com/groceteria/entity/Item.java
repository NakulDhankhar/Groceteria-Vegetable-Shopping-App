package com.groceteria.entity;

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
 * Item entity representing a product in the Groceteria system.
 * Aligned with the simplified, production-ready style and supporting USER and VENDOR roles.
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
@Schema(description = "Item entity representing a product in the Groceteria system")
public class Item {
    /**
     * Unique identifier for the item.
     */
    @Id
    @SequenceGenerator(name = "item_id_generator", sequenceName = "item_id_seq", initialValue = 500, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_generator")
    @Column(name = "item_id", nullable = false, updatable = false)
    @Schema(description = "Unique identifier for the item", example = "501", accessMode = Schema.AccessMode.READ_ONLY)
    private Long itemId;

    /**
     * Name of the item.
     */
    @Column(name = "item_name", nullable = false, length = 50)
    @NotBlank(message = "Item name is required")
    @Schema(description = "Name of the item", example = "Tomato", required = true)
    private String itemName;

    /**
     * Image URL for the item.
     */
    @Column(name = "image", length = 255)
    @Schema(description = "Image URL for the item", example = "https://example.com/tomato.jpg")
    private String image;

    /**
     * Description of the item.
     */
    @Column(name = "description", nullable = false, length = 255)
    @NotBlank(message = "Item description is required")
    @Schema(description = "Description of the item", example = "Fresh red tomatoes", required = true)
    private String description;

    /**
     * MRP price of the item.
     */
    @Column(name = "mrp_price", nullable = false)
    @Min(value = 0, message = "MRP price must be non-negative")
    @Schema(description = "MRP price of the item", example = "30.0", required = true)
    private Double mrpPrice;

    /**
     * Quantity available for the item.
     */
    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be non-negative")
    @Schema(description = "Quantity available for the item", example = "100", required = true)
    private Long quantity;

    /**
     * Category of the item.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 30)
    @NotNull(message = "Category is required")
    @Schema(description = "Category of the item", example = "VEGETABLES", required = true)
    private Category category;

    /**
     * Vendor who owns this item (User with role VENDOR).
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    @Schema(description = "Vendor who owns this item", hidden = true)
    private User vendor;
}
