package com.groceteria.entity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Category enum representing product categories in the Groceteria system.
 * Aligned with the simplified, production-ready style.
 */
@Schema(description = "Category of the item")
public enum Category {
    @Schema(description = "Vegetables")
    VEGETABLES(0),
    @Schema(description = "Fruits")
    FRUITS(1),
    @Schema(description = "Dairy Products")
    DAIRYPRODUCTS(2),
    @Schema(description = "Meat")
    MEAT(3),
    @Schema(description = "Grains and Oils")
    GRAINSANDOILS(4),
    @Schema(description = "Spices and Seasonings")
    SPICESANDSEASONINGS(5),
    @Schema(description = "Baking Ingredients")
    BAKINGINGREDIENTS(6),
    @Schema(description = "Condiments")
    CONDIMENTS(7),
    @Schema(description = "Snacks")
    SNACKS(8),
    @Schema(description = "Skincare")
    SKINCARE(9);

    private final int value;
    Category(int value) { this.value = value; }
    public int getValue() { return value; }
}
