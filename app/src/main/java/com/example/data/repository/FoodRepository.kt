package com.example.data.repository

import com.example.data.local.CartDao
import com.example.data.local.CartItemEntity
import com.example.data.local.OrderDao
import com.example.data.local.OrderHistoryEntity
import com.example.data.model.FoodCatalog
import com.example.data.model.FoodCategory
import com.example.data.model.FoodItem
import kotlinx.coroutines.flow.Flow

class FoodRepository(
    private val cartDao: CartDao,
    private val orderDao: OrderDao
) {
    fun getAllCatalogItems(): List<FoodItem> = FoodCatalog.items

    fun getItemsByCategory(category: FoodCategory): List<FoodItem> {
        return if (category == FoodCategory.ALL) {
            FoodCatalog.items
        } else if (category == FoodCategory.SPECIALS) {
            FoodCatalog.items.filter { it.isFeatured || it.isBestSeller }
        } else {
            FoodCatalog.items.filter { it.category == category }
        }
    }

    fun searchCatalog(query: String, category: FoodCategory): List<FoodItem> {
        val baseList = getItemsByCategory(category)
        if (query.isBlank()) return baseList
        val clean = query.trim().lowercase()
        return baseList.filter {
            it.nameEn.lowercase().contains(clean) ||
            it.nameSi.lowercase().contains(clean) ||
            it.description.lowercase().contains(clean) ||
            it.sinhalaDescription.lowercase().contains(clean)
        }
    }

    val cartItems: Flow<List<CartItemEntity>> = cartDao.getAllCartItems()
    val cartCount: Flow<Int> = cartDao.getCartCount()
    val orderHistory: Flow<List<OrderHistoryEntity>> = orderDao.getAllOrders()

    suspend fun addToCart(
        foodItem: FoodItem,
        selectedOptionNameEn: String,
        selectedOptionNameSi: String,
        unitPrice: Double,
        quantity: Int,
        specialNotes: String
    ) {
        val existing = cartDao.findExistingItem(foodItem.id, selectedOptionNameEn)
        if (existing != null) {
            val updated = existing.copy(
                quantity = existing.quantity + quantity,
                specialNotes = if (specialNotes.isNotBlank()) specialNotes else existing.specialNotes,
                unitPrice = unitPrice
            )
            cartDao.updateItem(updated)
        } else {
            val newItem = CartItemEntity(
                productId = foodItem.id,
                productNameEn = foodItem.nameEn,
                productNameSi = foodItem.nameSi,
                optionNameEn = selectedOptionNameEn,
                optionNameSi = selectedOptionNameSi,
                unitPrice = unitPrice,
                quantity = quantity,
                specialNotes = specialNotes,
                categoryName = foodItem.category.displayName,
                imageDrawableName = foodItem.imageDrawableName
            )
            cartDao.insertItem(newItem)
        }
    }

    suspend fun updateCartQuantity(cartItemId: Long, newQuantity: Int) {
        if (newQuantity <= 0) {
            cartDao.deleteItemById(cartItemId)
        } else {
            // we can retrieve and update
        }
    }

    suspend fun updateCartItem(item: CartItemEntity) {
        cartDao.updateItem(item)
    }

    suspend fun deleteCartItem(id: Long) {
        cartDao.deleteItemById(id)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    suspend fun saveOrder(order: OrderHistoryEntity): Long {
        return orderDao.insertOrder(order)
    }

    suspend fun deleteOrder(orderId: Long) {
        orderDao.deleteOrderById(orderId)
    }
}
