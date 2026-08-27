package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCartCheckout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.CartItemEntity
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.CinnamonBrown
import com.example.ui.theme.CinnamonBrownDark
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.GoldenHoney
import com.example.ui.theme.SriLankaGreen
import com.example.ui.theme.WhatsAppGreen
import com.example.ui.viewmodel.CheckoutFormState

@Composable
fun CartCheckoutScreen(
    cartItems: List<CartItemEntity>,
    cartTotal: Double,
    checkoutForm: CheckoutFormState,
    onQuantityChange: (CartItemEntity, Int) -> Unit,
    onRemoveItem: (CartItemEntity) -> Unit,
    onClearCart: () -> Unit,
    onNameChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onTimeChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
    onPaymentChange: (String) -> Unit,
    onContactNumberChange: (String) -> Unit,
    onSubmitWhatsApp: (String) -> Unit,
    onDirectCall: (String) -> Unit,
    onBrowseCatalogClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (cartItems.isEmpty()) {
        EmptyCartView(onBrowseCatalogClick = onBrowseCatalogClick, modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("cart_checkout_screen"),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cart Header with Clear All button
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Your Order Cart (ඔබේ ඇණවුම)",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            color = CinnamonBrown
                        )
                        Text(
                            text = "${cartItems.sumOf { it.quantity }} items selected",
                            style = MaterialTheme.typography.bodySmall,
                            color = DarkTextSecondary
                        )
                    }

                    TextButton(
                        onClick = onClearCart,
                        modifier = Modifier.testTag("clear_cart_button")
                    ) {
                        Text("Clear All", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Cart Items List
            items(cartItems, key = { it.id }) { item ->
                CartItemRow(
                    item = item,
                    onIncrement = { onQuantityChange(item, 1) },
                    onDecrement = { onQuantityChange(item, -1) },
                    onRemove = { onRemoveItem(item) }
                )
            }

            // Custom Order & Delivery Form Section
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth().testTag("checkout_details_form")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.EditNote,
                                contentDescription = null,
                                tint = CinnamonBrown,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Delivery & Customer Details",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CinnamonBrown
                            )
                        }

                        Text(
                            text = "Please enter delivery location and preferred timing for direct confirmation",
                            style = MaterialTheme.typography.bodySmall,
                            color = DarkTextSecondary,
                            modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                        )

                        // Customer Name Field
                        OutlinedTextField(
                            value = checkoutForm.customerName,
                            onValueChange = onNameChange,
                            label = { Text("Your Name (ඔබේ නම)") },
                            placeholder = { Text("e.g. Kasun Perera") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = CinnamonBrown) },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth().testTag("customer_name_input")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Customer Phone Field
                        OutlinedTextField(
                            value = checkoutForm.customerPhone,
                            onValueChange = onPhoneChange,
                            label = { Text("Your Phone / WhatsApp Number (දුරකථන අංකය)") },
                            placeholder = { Text("e.g. 0771234567") },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = CinnamonBrown) },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth().testTag("customer_phone_input")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Delivery Address Field
                        OutlinedTextField(
                            value = checkoutForm.deliveryAddress,
                            onValueChange = onAddressChange,
                            label = { Text("Delivery Address / Area (ලිපිනය)") },
                            placeholder = { Text("e.g. No. 45, Temple Road, Colombo / Gampaha") },
                            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = CinnamonBrown) },
                            maxLines = 2,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth().testTag("delivery_address_input")
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Delivery Date Selector Chips
                        Text(
                            text = "Preferred Date (අවශ්‍ය දිනය):",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = DarkTextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        val dateOptions = listOf("Today", "Tomorrow", "In 2 Days", "This Weekend", "Custom Date")
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(dateOptions) { dateOpt ->
                                val isSelected = checkoutForm.deliveryDate == dateOpt
                                Surface(
                                    onClick = { onDateChange(dateOpt) },
                                    shape = RoundedCornerShape(20.dp),
                                    color = if (isSelected) CinnamonBrown else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.testTag("date_chip_$dateOpt")
                                ) {
                                    Text(
                                        text = dateOpt,
                                        color = if (isSelected) Color.White else DarkTextPrimary,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Delivery Time Slot Chips
                        Text(
                            text = "Preferred Time Slot (අවශ්‍ය වේලාව):",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = DarkTextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        val timeOptions = listOf(
                            "Morning (8:00 AM - 11:00 AM)",
                            "Noon (12:00 PM - 3:00 PM)",
                            "Evening (4:00 PM - 7:00 PM)",
                            "Urgent / As soon as ready"
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            timeOptions.forEach { timeOpt ->
                                val isSelected = checkoutForm.deliveryTime == timeOpt
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onTimeChange(timeOpt) }
                                        .padding(vertical = 2.dp)
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { onTimeChange(timeOpt) },
                                        colors = RadioButtonDefaults.colors(selectedColor = CinnamonBrown)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = timeOpt,
                                        style = MaterialTheme.typography.bodySmall,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Special Customization Notes
                        OutlinedTextField(
                            value = checkoutForm.specialNotes,
                            onValueChange = onNotesChange,
                            label = { Text("Special Customization / Cake Message (විශේෂ සටහන්)") },
                            placeholder = { Text("e.g. Birthday lettering, no egg, gift packing, etc.") },
                            maxLines = 3,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth().testTag("special_notes_input")
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Send Order To Distributor Number Selection
                        Text(
                            text = "Submit Order to Jayasanka Hotline:",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = CinnamonBrown
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val numbers = listOf("0713656883", "0783368792")
                            numbers.forEach { num ->
                                val isSelected = checkoutForm.selectedContactNumber == num
                                Surface(
                                    onClick = { onContactNumberChange(num) },
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) AmberSoft else MaterialTheme.colorScheme.surfaceVariant,
                                    border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, CinnamonBrown) else null,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Chat,
                                            contentDescription = null,
                                            tint = if (isSelected) CinnamonBrown else DarkTextSecondary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = num,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = if (isSelected) CinnamonBrown else DarkTextPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Grand Total and Action Buttons
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CinnamonBrownDark),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Estimated Total (ඇස්තමේන්තු මුදල):",
                                color = AmberSoft,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Rs. %,.2f".format(cartTotal),
                                color = Color.White,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Text(
                            text = "Payment on Delivery or Direct Bank Confirmation upon order acceptance",
                            color = Color.White.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
                        )

                        // Primary One-Click WhatsApp Submission Button
                        Button(
                            onClick = { onSubmitWhatsApp(checkoutForm.selectedContactNumber) },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("submit_whatsapp_order_button")
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Chat,
                                    contentDescription = "WhatsApp",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Send Order via WhatsApp (${checkoutForm.selectedContactNumber})",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Secondary Call Direct Button
                        OutlinedButton(
                            onClick = { onDirectCall(checkoutForm.selectedContactNumber) },
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, AmberWarm),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("call_to_order_button")
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "Call",
                                    tint = AmberWarm,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Call ${checkoutForm.selectedContactNumber} Directly to Confirm",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItemEntity,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier.fillMaxWidth().testTag("cart_item_${item.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.productNameEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextPrimary
                )
                Text(
                    text = "${item.productNameSi} • ${item.optionNameEn}",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkTextSecondary
                )

                if (item.specialNotes.isNotBlank()) {
                    Text(
                        text = "📝 Note: ${item.specialNotes}",
                        style = MaterialTheme.typography.labelSmall,
                        color = CinnamonBrown,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Text(
                    text = "Rs. %,.0f each".format(item.unitPrice),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = GoldenHoney,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Quantity controls
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(2.dp)
                ) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier.size(28.dp).testTag("cart_item_minus_${item.id}")
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(14.dp))
                    }
                    Text(
                        text = "${item.quantity}",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(
                        onClick = onIncrement,
                        modifier = Modifier.size(28.dp).testTag("cart_item_plus_${item.id}")
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(14.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Line Total & Remove
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Rs. %,.0f".format(item.unitPrice * item.quantity),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Black,
                    color = CinnamonBrown
                )
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(28.dp).testTag("cart_item_delete_${item.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyCartView(
    onBrowseCatalogClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = CircleShape,
                color = AmberSoft,
                modifier = Modifier.size(96.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        tint = CinnamonBrown,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Your Cart is Empty",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = CinnamonBrown
            )

            Text(
                text = "Add delicious Butter Cakes, Kokis, Konda Kavum, or Cutlets to start your custom order.",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkTextSecondary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBrowseCatalogClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CinnamonBrown),
                modifier = Modifier.testTag("browse_catalog_empty_cart_btn")
            ) {
                Text("Browse Jayasanka Menu", fontWeight = FontWeight.Bold)
            }
        }
    }
}
