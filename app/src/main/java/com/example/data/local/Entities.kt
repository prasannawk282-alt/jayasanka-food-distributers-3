package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: String,
    val productNameEn: String,
    val productNameSi: String,
    val optionNameEn: String,
    val optionNameSi: String,
    val unitPrice: Double,
    val quantity: Int,
    val specialNotes: String = "",
    val categoryName: String,
    val imageDrawableName: String,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "orders_history")
data class OrderHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderNumber: String,
    val customerName: String,
    val customerPhone: String,
    val deliveryAddress: String,
    val deliveryDate: String,
    val deliveryTime: String,
    val specialInstructions: String,
    val itemsSummary: String,
    val totalAmount: Double,
    val paymentMethod: String,
    val orderStatus: String, // e.g. "Sent via WhatsApp", "Confirmed", "Completed"
    val timestamp: Long = System.currentTimeMillis()
)
