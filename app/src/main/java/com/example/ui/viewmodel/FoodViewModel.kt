package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.CartItemEntity
import com.example.data.local.OrderHistoryEntity
import com.example.data.model.FoodCatalog
import com.example.data.model.FoodCategory
import com.example.data.model.FoodItem
import com.example.data.model.ProductOption
import com.example.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CheckoutFormState(
    val customerName: String = "",
    val customerPhone: String = "",
    val deliveryAddress: String = "",
    val deliveryDate: String = "Tomorrow",
    val deliveryTime: String = "Morning (9:00 AM - 12:00 PM)",
    val specialNotes: String = "",
    val paymentMethod: String = "Cash on Delivery / Direct Confirmation",
    val selectedContactNumber: String = "0713656883"
)

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FoodRepository

    init {
        val database = AppDatabase.getDatabase(application)
        repository = FoodRepository(database.cartDao(), database.orderDao())
    }

    // Filter and Search State
    private val _selectedCategory = MutableStateFlow(FoodCategory.ALL)
    val selectedCategory: StateFlow<FoodCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Filtered Food Catalog
    val catalogItems: StateFlow<List<FoodItem>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        repository.searchCatalog(query, category)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FoodCatalog.items
    )

    // Product Modal / Bottomsheet State
    private val _selectedProduct = MutableStateFlow<FoodItem?>(null)
    val selectedProduct: StateFlow<FoodItem?> = _selectedProduct.asStateFlow()

    private val _selectedOption = MutableStateFlow<ProductOption?>(null)
    val selectedOption: StateFlow<ProductOption?> = _selectedOption.asStateFlow()

    private val _productQuantity = MutableStateFlow(1)
    val productQuantity: StateFlow<Int> = _productQuantity.asStateFlow()

    private val _productCustomNotes = MutableStateFlow("")
    val productCustomNotes: StateFlow<String> = _productCustomNotes.asStateFlow()

    // Cart Items and Totals
    val cartItems: StateFlow<List<CartItemEntity>> = repository.cartItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val cartCount: StateFlow<Int> = repository.cartCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val cartTotal: StateFlow<Double> = cartItems.combine(cartItems) { items, _ ->
        items.sumOf { it.unitPrice * it.quantity }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0.0
    )

    // Checkout Form
    private val _checkoutForm = MutableStateFlow(CheckoutFormState())
    val checkoutForm: StateFlow<CheckoutFormState> = _checkoutForm.asStateFlow()

    // Order History
    val orderHistory: StateFlow<List<OrderHistoryEntity>> = repository.orderHistory
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Order Success Dialog State
    private val _lastSubmittedOrder = MutableStateFlow<OrderHistoryEntity?>(null)
    val lastSubmittedOrder: StateFlow<OrderHistoryEntity?> = _lastSubmittedOrder.asStateFlow()

    fun selectCategory(category: FoodCategory) {
        _selectedCategory.value = category
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun openProductDetail(product: FoodItem) {
        _selectedProduct.value = product
        _selectedOption.value = product.options.firstOrNull() ?: ProductOption(
            optionName = product.unit,
            sinhalaOptionName = product.unit,
            price = product.basePrice,
            unitLabel = product.unit
        )
        _productQuantity.value = 1
        _productCustomNotes.value = ""
    }

    fun closeProductDetail() {
        _selectedProduct.value = null
        _selectedOption.value = null
        _productQuantity.value = 1
        _productCustomNotes.value = ""
    }

    fun selectOption(option: ProductOption) {
        _selectedOption.value = option
    }

    fun incrementQuantity() {
        _productQuantity.value = (_productQuantity.value + 1).coerceAtMost(99)
    }

    fun decrementQuantity() {
        _productQuantity.value = (_productQuantity.value - 1).coerceAtLeast(1)
    }

    fun updateProductCustomNotes(notes: String) {
        _productCustomNotes.value = notes
    }

    fun addToCartCurrentProduct() {
        val product = _selectedProduct.value ?: return
        val option = _selectedOption.value ?: ProductOption(
            optionName = product.unit,
            sinhalaOptionName = product.unit,
            price = product.basePrice,
            unitLabel = product.unit
        )

        viewModelScope.launch {
            repository.addToCart(
                foodItem = product,
                selectedOptionNameEn = option.optionName,
                selectedOptionNameSi = option.sinhalaOptionName,
                unitPrice = option.price,
                quantity = _productQuantity.value,
                specialNotes = _productCustomNotes.value
            )
            closeProductDetail()
        }
    }

    fun quickAddToCart(product: FoodItem) {
        if (product.options.size > 1) {
            openProductDetail(product)
        } else {
            val defaultOption = product.options.firstOrNull() ?: ProductOption(
                optionName = product.unit,
                sinhalaOptionName = product.unit,
                price = product.basePrice,
                unitLabel = product.unit
            )
            viewModelScope.launch {
                repository.addToCart(
                    foodItem = product,
                    selectedOptionNameEn = defaultOption.optionName,
                    selectedOptionNameSi = defaultOption.sinhalaOptionName,
                    unitPrice = defaultOption.price,
                    quantity = 1,
                    specialNotes = ""
                )
            }
        }
    }

    fun updateCartItemQuantity(item: CartItemEntity, delta: Int) {
        viewModelScope.launch {
            val newQty = item.quantity + delta
            if (newQty <= 0) {
                repository.deleteCartItem(item.id)
            } else {
                repository.updateCartItem(item.copy(quantity = newQty))
            }
        }
    }

    fun removeCartItem(item: CartItemEntity) {
        viewModelScope.launch {
            repository.deleteCartItem(item.id)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    // Checkout Form Field Updates
    fun updateCustomerName(name: String) {
        _checkoutForm.value = _checkoutForm.value.copy(customerName = name)
    }

    fun updateCustomerPhone(phone: String) {
        _checkoutForm.value = _checkoutForm.value.copy(customerPhone = phone)
    }

    fun updateDeliveryAddress(address: String) {
        _checkoutForm.value = _checkoutForm.value.copy(deliveryAddress = address)
    }

    fun updateDeliveryDate(date: String) {
        _checkoutForm.value = _checkoutForm.value.copy(deliveryDate = date)
    }

    fun updateDeliveryTime(time: String) {
        _checkoutForm.value = _checkoutForm.value.copy(deliveryTime = time)
    }

    fun updateSpecialNotes(notes: String) {
        _checkoutForm.value = _checkoutForm.value.copy(specialNotes = notes)
    }

    fun updatePaymentMethod(method: String) {
        _checkoutForm.value = _checkoutForm.value.copy(paymentMethod = method)
    }

    fun updateSelectedContactNumber(number: String) {
        _checkoutForm.value = _checkoutForm.value.copy(selectedContactNumber = number)
    }

    fun dismissOrderConfirmation() {
        _lastSubmittedOrder.value = null
    }

    // Format WhatsApp & SMS Order Message
    fun buildOrderMessage(
        orderNumber: String,
        form: CheckoutFormState,
        items: List<CartItemEntity>,
        total: Double
    ): String {
        val dateStr = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date())
        val sb = StringBuilder()

        sb.append("🎂 *NEW FOOD ORDER - JAYASANKA FOOD DISTRIBUTORS*\n")
        sb.append("━━━━━━━━━━━━━━━━━━━━\n")
        sb.append("📋 *Order Ref:* #$orderNumber\n")
        sb.append("📅 *Order Date:* $dateStr\n\n")

        sb.append("👤 *CUSTOMER DETAILS:*\n")
        sb.append("• *Name:* ${form.customerName.ifBlank { "Valued Customer" }}\n")
        sb.append("• *Phone:* ${form.customerPhone.ifBlank { "Not specified" }}\n")
        sb.append("• *Delivery Address:* ${form.deliveryAddress.ifBlank { "Pickup / Direct Handover" }}\n")
        sb.append("• *Preferred Date:* ${form.deliveryDate}\n")
        sb.append("• *Preferred Time:* ${form.deliveryTime}\n")
        if (form.specialNotes.isNotBlank()) {
            sb.append("• *Special Instructions / Custom Icing:* ${form.specialNotes}\n")
        }
        sb.append("• *Payment:* ${form.paymentMethod}\n\n")

        sb.append("🛒 *ORDER ITEMS (${items.size}):*\n")
        items.forEachIndexed { index, item ->
            val itemTotal = item.unitPrice * item.quantity
            sb.append("${index + 1}. *${item.productNameEn}* (${item.productNameSi})\n")
            sb.append("   ▸ Option: ${item.optionNameEn}\n")
            sb.append("   ▸ Qty: ${item.quantity} x Rs. ${"%,.2f".format(item.unitPrice)} = *Rs. ${"%,.2f".format(itemTotal)}*\n")
            if (item.specialNotes.isNotBlank()) {
                sb.append("   ▸ Note: ${item.specialNotes}\n")
            }
        }

        sb.append("\n━━━━━━━━━━━━━━━━━━━━\n")
        sb.append("💰 *GRAND TOTAL:* *Rs. ${"%,.2f".format(total)}*\n")
        sb.append("━━━━━━━━━━━━━━━━━━━━\n")
        sb.append("🙏 *Please confirm order availability and delivery schedule.*")

        return sb.toString()
    }

    fun submitOrderViaWhatsApp(context: Context, targetPhone: String? = null) {
        val items = cartItems.value
        if (items.isEmpty()) {
            Toast.makeText(context, "Your cart is empty!", Toast.LENGTH_SHORT).show()
            return
        }

        val form = checkoutForm.value
        val total = items.sumOf { it.unitPrice * it.quantity }
        val orderRef = "JFD-" + (1000..9999).random()

        val itemsSummary = items.joinToString("\n") {
            "${it.quantity}x ${it.productNameEn} (${it.optionNameEn}) - Rs. ${it.unitPrice * it.quantity}"
        }

        val orderEntity = OrderHistoryEntity(
            orderNumber = orderRef,
            customerName = form.customerName.ifBlank { "Customer" },
            customerPhone = form.customerPhone,
            deliveryAddress = form.deliveryAddress,
            deliveryDate = form.deliveryDate,
            deliveryTime = form.deliveryTime,
            specialInstructions = form.specialNotes,
            itemsSummary = itemsSummary,
            totalAmount = total,
            paymentMethod = form.paymentMethod,
            orderStatus = "Sent via WhatsApp"
        )

        val message = buildOrderMessage(orderRef, form, items, total)
        val contactNumber = targetPhone ?: form.selectedContactNumber

        // Save order and clear cart
        viewModelScope.launch {
            repository.saveOrder(orderEntity)
            repository.clearCart()
            _lastSubmittedOrder.value = orderEntity
        }

        // Launch WhatsApp with pre-filled message to selected phone
        launchWhatsAppMessage(context, contactNumber, message)
    }

    fun reorderOrder(order: OrderHistoryEntity, context: Context) {
        val form = checkoutForm.value.copy(
            customerName = order.customerName,
            customerPhone = order.customerPhone,
            deliveryAddress = order.deliveryAddress,
            specialNotes = "Re-order of #${order.orderNumber}. " + order.specialInstructions
        )
        _checkoutForm.value = form
        Toast.makeText(context, "Reorder details loaded into checkout!", Toast.LENGTH_SHORT).show()
    }

    fun launchWhatsAppMessage(context: Context, phoneNumber: String, message: String) {
        try {
            // Clean phone number (e.g. 0713656883 -> +94713656883)
            val cleaned = phoneNumber.replace(Regex("[^0-9+]"), "")
            val international = if (cleaned.startsWith("0")) {
                "94" + cleaned.substring(1)
            } else if (cleaned.startsWith("+94")) {
                cleaned.substring(1)
            } else if (cleaned.startsWith("94")) {
                cleaned
            } else {
                "94" + cleaned
            }

            val encodedMessage = URLEncoder.encode(message, "UTF-8")
            val url = "https://api.whatsapp.com/send?phone=$international&text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to generic share
            try {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, message)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(Intent.createChooser(shareIntent, "Send Order Details"))
            } catch (ex: Exception) {
                Toast.makeText(context, "Could not open messaging app: ${ex.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun launchDirectPhoneCall(context: Context, phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to launch dialer", Toast.LENGTH_SHORT).show()
        }
    }

    fun launchFacebookPage(context: Context) {
        val fbUrl = "https://www.facebook.com/share/18UbFLE9hL/"
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to open Facebook page", Toast.LENGTH_SHORT).show()
        }
    }
}
