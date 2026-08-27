package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.OrderHistoryEntity
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.CinnamonBrown
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.GoldenHoney
import com.example.ui.theme.SriLankaGreen
import com.example.ui.theme.WhatsAppGreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun OrderHistoryScreen(
    orders: List<OrderHistoryEntity>,
    onReorderClick: (OrderHistoryEntity) -> Unit,
    onWhatsAppInquiryClick: (OrderHistoryEntity) -> Unit,
    onBrowseCatalogClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (orders.isEmpty()) {
        EmptyOrdersView(onBrowseCatalogClick = onBrowseCatalogClick, modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("order_history_screen"),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column {
                    Text(
                        text = "Order History (පෙර ඇණවුම්)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = CinnamonBrown
                    )
                    Text(
                        text = "Review your past food orders or quickly reorder for upcoming events",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkTextSecondary
                    )
                }
            }

            items(orders, key = { it.id }) { order ->
                OrderHistoryCard(
                    order = order,
                    onReorderClick = { onReorderClick(order) },
                    onWhatsAppInquiryClick = { onWhatsAppInquiryClick(order) }
                )
            }
        }
    }
}

@Composable
fun OrderHistoryCard(
    order: OrderHistoryEntity,
    onReorderClick: () -> Unit,
    onWhatsAppInquiryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateString = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(order.timestamp))

    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.fillMaxWidth().testTag("order_card_${order.orderNumber}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Ref & Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AmberSoft
                    ) {
                        Text(
                            text = "#${order.orderNumber}",
                            color = CinnamonBrown,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFDCFCE7)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = SriLankaGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = order.orderStatus,
                            color = SriLankaGreen,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dateString,
                style = MaterialTheme.typography.labelSmall,
                color = DarkTextSecondary
            )

            if (order.customerName.isNotBlank()) {
                Text(
                    text = "Customer: ${order.customerName} • ${order.customerPhone}",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkTextPrimary,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            if (order.deliveryAddress.isNotBlank()) {
                Text(
                    text = "Delivery to: ${order.deliveryAddress}",
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkTextSecondary
                )
            }

            Text(
                text = "Target Date: ${order.deliveryDate} (${order.deliveryTime})",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = CinnamonBrown,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )

            // Items Summary
            Text(
                text = "Ordered Items:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = DarkTextSecondary
            )
            Text(
                text = order.itemsSummary,
                style = MaterialTheme.typography.bodySmall,
                color = DarkTextPrimary,
                modifier = Modifier.padding(vertical = 2.dp)
            )

            if (order.specialInstructions.isNotBlank()) {
                Text(
                    text = "Special Note: ${order.specialInstructions}",
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldenHoney,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Total and Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Amount",
                        style = MaterialTheme.typography.labelSmall,
                        color = DarkTextSecondary
                    )
                    Text(
                        text = "Rs. %,.2f".format(order.totalAmount),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = CinnamonBrown
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = onReorderClick,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("reorder_button_${order.orderNumber}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay,
                            contentDescription = "Reorder",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reorder", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onWhatsAppInquiryClick,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen),
                        modifier = Modifier.testTag("inquire_order_whatsapp_${order.orderNumber}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "Inquire on WhatsApp",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Inquire", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyOrdersView(
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
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = CinnamonBrown,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "No Past Orders Yet",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = CinnamonBrown
            )

            Text(
                text = "When you place an order with Jayasanka Food Distributors, it will be safely recorded here.",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkTextSecondary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBrowseCatalogClick,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CinnamonBrown)
            ) {
                Text("Browse Menu Now", fontWeight = FontWeight.Bold)
            }
        }
    }
}
