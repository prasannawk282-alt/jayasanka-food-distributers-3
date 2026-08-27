package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.ProductCustomizationDialog
import com.example.ui.components.TopBrandHeader
import com.example.ui.screens.CartCheckoutScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.MenuScreen
import com.example.ui.screens.OrderConfirmationDialog
import com.example.ui.screens.OrderHistoryScreen
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.CinnamonBrown
import com.example.ui.theme.CinnamonBrownDark
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.GoldenHoney
import com.example.ui.viewmodel.FoodViewModel

enum class AppTab(val title: String, val testTag: String) {
    MENU("Menu", "tab_menu"),
    CART("Cart", "tab_cart"),
    HISTORY("Orders", "tab_orders"),
    CONTACT("Contact", "tab_contact")
}

@Composable
fun MainApp(
    viewModel: FoodViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentTab by remember { mutableStateOf(AppTab.MENU) }

    val catalogItems by viewModel.catalogItems.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val selectedOption by viewModel.selectedOption.collectAsStateWithLifecycle()
    val productQuantity by viewModel.productQuantity.collectAsStateWithLifecycle()
    val productCustomNotes by viewModel.productCustomNotes.collectAsStateWithLifecycle()

    val cartItems by viewModel.cartItems.collectAsStateWithLifecycle()
    val cartCount by viewModel.cartCount.collectAsStateWithLifecycle()
    val cartTotal by viewModel.cartTotal.collectAsStateWithLifecycle()
    val checkoutForm by viewModel.checkoutForm.collectAsStateWithLifecycle()

    val orderHistory by viewModel.orderHistory.collectAsStateWithLifecycle()
    val lastSubmittedOrder by viewModel.lastSubmittedOrder.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopBrandHeader(
                onCallClick = { viewModel.launchDirectPhoneCall(context, "0713656883") }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = Modifier.testTag("main_bottom_nav_bar")
            ) {
                // Tab 1: Menu
                NavigationBarItem(
                    selected = currentTab == AppTab.MENU,
                    onClick = { currentTab = AppTab.MENU },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.MENU) Icons.Filled.Restaurant else Icons.Outlined.Restaurant,
                            contentDescription = "Menu"
                        )
                    },
                    label = { Text("Menu", fontWeight = if (currentTab == AppTab.MENU) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CinnamonBrown,
                        selectedTextColor = CinnamonBrown,
                        indicatorColor = AmberSoft
                    ),
                    modifier = Modifier.testTag("nav_item_menu")
                )

                // Tab 2: Cart
                NavigationBarItem(
                    selected = currentTab == AppTab.CART,
                    onClick = { currentTab = AppTab.CART },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (cartItems.isNotEmpty()) {
                                    Badge(
                                        containerColor = CinnamonBrown,
                                        contentColor = Color.White
                                    ) {
                                        Text("${cartItems.sumOf { it.quantity }}")
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (currentTab == AppTab.CART) Icons.Filled.ShoppingBag else Icons.Outlined.ShoppingBag,
                                contentDescription = "Cart"
                            )
                        }
                    },
                    label = { Text("Cart", fontWeight = if (currentTab == AppTab.CART) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CinnamonBrown,
                        selectedTextColor = CinnamonBrown,
                        indicatorColor = AmberSoft
                    ),
                    modifier = Modifier.testTag("nav_item_cart")
                )

                // Tab 3: Order History
                NavigationBarItem(
                    selected = currentTab == AppTab.HISTORY,
                    onClick = { currentTab = AppTab.HISTORY },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.HISTORY) Icons.Filled.History else Icons.Outlined.History,
                            contentDescription = "Orders"
                        )
                    },
                    label = { Text("Orders", fontWeight = if (currentTab == AppTab.HISTORY) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CinnamonBrown,
                        selectedTextColor = CinnamonBrown,
                        indicatorColor = AmberSoft
                    ),
                    modifier = Modifier.testTag("nav_item_orders")
                )

                // Tab 4: Contact & Socials
                NavigationBarItem(
                    selected = currentTab == AppTab.CONTACT,
                    onClick = { currentTab = AppTab.CONTACT },
                    icon = {
                        Icon(
                            imageVector = if (currentTab == AppTab.CONTACT) Icons.Filled.Call else Icons.Outlined.Call,
                            contentDescription = "Contact"
                        )
                    },
                    label = { Text("Contact", fontWeight = if (currentTab == AppTab.CONTACT) FontWeight.Bold else FontWeight.Normal) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = CinnamonBrown,
                        selectedTextColor = CinnamonBrown,
                        indicatorColor = AmberSoft
                    ),
                    modifier = Modifier.testTag("nav_item_contact")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                AppTab.MENU -> {
                    MenuScreen(
                        catalogItems = catalogItems,
                        selectedCategory = selectedCategory,
                        searchQuery = searchQuery,
                        onCategorySelected = { viewModel.selectCategory(it) },
                        onSearchQueryChanged = { viewModel.updateSearchQuery(it) },
                        onProductClick = { viewModel.openProductDetail(it) },
                        onQuickAdd = { viewModel.quickAddToCart(it) },
                        onWhatsAppDirectClick = {
                            viewModel.launchWhatsAppMessage(
                                context,
                                "0713656883",
                                "Hello Jayasanka Food Distributors! I would like to inquire about ordering delicious cakes / traditional sweets."
                            )
                        },
                        onCallDirectClick = { viewModel.launchDirectPhoneCall(context, "0713656883") }
                    )
                }

                AppTab.CART -> {
                    CartCheckoutScreen(
                        cartItems = cartItems,
                        cartTotal = cartTotal,
                        checkoutForm = checkoutForm,
                        onQuantityChange = { item, delta -> viewModel.updateCartItemQuantity(item, delta) },
                        onRemoveItem = { viewModel.removeCartItem(it) },
                        onClearCart = { viewModel.clearCart() },
                        onNameChange = { viewModel.updateCustomerName(it) },
                        onPhoneChange = { viewModel.updateCustomerPhone(it) },
                        onAddressChange = { viewModel.updateDeliveryAddress(it) },
                        onDateChange = { viewModel.updateDeliveryDate(it) },
                        onTimeChange = { viewModel.updateDeliveryTime(it) },
                        onNotesChange = { viewModel.updateSpecialNotes(it) },
                        onPaymentChange = { viewModel.updatePaymentMethod(it) },
                        onContactNumberChange = { viewModel.updateSelectedContactNumber(it) },
                        onSubmitWhatsApp = { number -> viewModel.submitOrderViaWhatsApp(context, number) },
                        onDirectCall = { number -> viewModel.launchDirectPhoneCall(context, number) },
                        onBrowseCatalogClick = { currentTab = AppTab.MENU }
                    )
                }

                AppTab.HISTORY -> {
                    OrderHistoryScreen(
                        orders = orderHistory,
                        onReorderClick = { order ->
                            viewModel.reorderOrder(order, context)
                            currentTab = AppTab.CART
                        },
                        onWhatsAppInquiryClick = { order ->
                            val msg = "Hello Jayasanka Food Distributors, I'm following up on my previous order #${order.orderNumber} placed for ${order.deliveryDate} (Rs. %,.2f).".format(order.totalAmount)
                            viewModel.launchWhatsAppMessage(context, "0713656883", msg)
                        },
                        onBrowseCatalogClick = { currentTab = AppTab.MENU }
                    )
                }

                AppTab.CONTACT -> {
                    ContactScreen(
                        onCallNumber1 = { viewModel.launchDirectPhoneCall(context, "0713656883") },
                        onCallNumber2 = { viewModel.launchDirectPhoneCall(context, "0783368792") },
                        onWhatsAppNumber1 = {
                            viewModel.launchWhatsAppMessage(
                                context,
                                "0713656883",
                                "Hello Jayasanka Food Distributors! I would like to inquire about food orders and pricing."
                            )
                        },
                        onWhatsAppNumber2 = {
                            viewModel.launchWhatsAppMessage(
                                context,
                                "0783368792",
                                "Hello Jayasanka Food Distributors! I would like to inquire about food orders and pricing."
                            )
                        },
                        onFacebookClick = { viewModel.launchFacebookPage(context) }
                    )
                }
            }

            // Customization Modal Dialog
            selectedProduct?.let { product ->
                ProductCustomizationDialog(
                    product = product,
                    selectedOption = selectedOption,
                    quantity = productQuantity,
                    customNotes = productCustomNotes,
                    onOptionSelected = { viewModel.selectOption(it) },
                    onIncrement = { viewModel.incrementQuantity() },
                    onDecrement = { viewModel.decrementQuantity() },
                    onNotesChanged = { viewModel.updateProductCustomNotes(it) },
                    onAddToCart = { viewModel.addToCartCurrentProduct() },
                    onDismiss = { viewModel.closeProductDetail() }
                )
            }

            // Order Submitted Confirmation Dialog
            lastSubmittedOrder?.let { order ->
                OrderConfirmationDialog(
                    order = order,
                    onDismiss = { viewModel.dismissOrderConfirmation() },
                    onOpenWhatsAppAgain = {
                        viewModel.launchWhatsAppMessage(
                            context,
                            order.customerPhone.ifBlank { "0713656883" },
                            "Order Reference #${order.orderNumber}"
                        )
                    }
                )
            }
        }
    }
}
