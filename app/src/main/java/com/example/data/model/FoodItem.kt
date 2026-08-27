package com.example.data.model

import androidx.annotation.DrawableRes

enum class FoodCategory(val displayName: String, val sinhalaName: String, val icon: String) {
    ALL("All Menu", "සියල්ල", "🍽️"),
    CAKES("Cakes", "කේක් වර්ග", "🎂"),
    TRADITIONAL_SWEETS("Traditional Sweets", "කැවිලි වර්ග", "🍯"),
    SAVORIES_SNACKS("Snacks & Savories", "කෙටි කෑම", "🥟"),
    SPECIALS("Featured & Best Sellers", "විශේෂ", "⭐")
}

data class ProductOption(
    val optionName: String,
    val sinhalaOptionName: String,
    val price: Double,
    val unitLabel: String = "" // e.g. "1kg", "Cup", "Pot", "pc"
)

data class FoodItem(
    val id: String,
    val nameEn: String,
    val nameSi: String,
    val category: FoodCategory,
    val basePrice: Double,
    val unit: String,
    val description: String,
    val sinhalaDescription: String,
    val isFeatured: Boolean = false,
    val isBestSeller: Boolean = false,
    val options: List<ProductOption> = emptyList(),
    val isCustomizable: Boolean = true, // custom notes, icing, message
    val imageDrawableName: String = "img_hero_banner"
)
