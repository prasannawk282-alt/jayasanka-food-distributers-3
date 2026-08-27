package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.CinnamonBrown
import com.example.ui.theme.CinnamonBrownDark
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.FacebookBlue
import com.example.ui.theme.GoldenHoney
import com.example.ui.theme.SriLankaGreen
import com.example.ui.theme.WhatsAppGreen

@Composable
fun ContactScreen(
    onCallNumber1: () -> Unit,
    onCallNumber2: () -> Unit,
    onWhatsAppNumber1: () -> Unit,
    onWhatsAppNumber2: () -> Unit,
    onFacebookClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("contact_socials_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Business Profile Header Card
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = CinnamonBrown),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_app_icon),
                            contentDescription = "Jayasanka Logo",
                            modifier = Modifier.size(72.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Jayasanka Food Distributors",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Text(
                        text = "ජයසංක ආහාර බෙදාහරින්නෝ",
                        style = MaterialTheme.typography.titleSmall,
                        color = AmberSoft,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = AmberWarm
                    ) {
                        Text(
                            text = "Fresh Bakery • Traditional Sweetmeats • Event Catering",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        // Direct Phone Hotlines Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Direct Customer & Order Hotlines",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = CinnamonBrown
                    )
                    Text(
                        text = "Contact us for custom orders, bulk wholesale, and inquiries",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkTextSecondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Hotline 1: 0713656883
                    ContactRowItem(
                        title = "Hotline 1 (Primary WhatsApp / Call)",
                        phoneNumber = "0713656883",
                        onCall = onCallNumber1,
                        onWhatsApp = onWhatsAppNumber1,
                        testTagPrefix = "hotline_1"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Hotline 2: 0783368792
                    ContactRowItem(
                        title = "Hotline 2 (Secondary / Catering Inquiries)",
                        phoneNumber = "0783368792",
                        onCall = onCallNumber2,
                        onWhatsApp = onWhatsAppNumber2,
                        testTagPrefix = "hotline_2"
                    )
                }
            }
        }

        // Official Social Media & Facebook Card
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Official Facebook Page",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = CinnamonBrown
                    )
                    Text(
                        text = "Follow us for daily fresh bakes, new festive items, and updates",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkTextSecondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Surface(
                        onClick = onFacebookClick,
                        shape = RoundedCornerShape(16.dp),
                        color = FacebookBlue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("facebook_page_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    shape = CircleShape,
                                    color = Color.White,
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = "f",
                                            color = FacebookBlue,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 22.sp
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Visit Facebook Page",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                    Text(
                                        text = "facebook.com/share/18UbFLE9hL",
                                        color = Color.White.copy(alpha = 0.85f),
                                        fontSize = 11.sp
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.Default.Public,
                                contentDescription = "Open Facebook",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        // Business Service Info & Delivery Details
        item {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "About Jayasanka Food Distribution",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DarkTextPrimary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoBulletRow(
                        icon = Icons.Default.DeliveryDining,
                        title = "Island-Wide / Local Distribution",
                        description = "Daily fresh deliveries for shops, family celebrations, weddings, and pirith ceremonies."
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoBulletRow(
                        icon = Icons.Default.AccessTime,
                        title = "Operating Hours",
                        description = "Open 7 Days a Week: 7:00 AM – 9:00 PM for orders and customer service."
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoBulletRow(
                        icon = Icons.Default.Verified,
                        title = "Quality Guarantee",
                        description = "100% genuine ingredients, traditional kithul treacle, pure butter, and hygienic preparation."
                    )
                }
            }
        }
    }
}

@Composable
fun ContactRowItem(
    title: String,
    phoneNumber: String,
    onCall: () -> Unit,
    onWhatsApp: () -> Unit,
    testTagPrefix: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = DarkTextSecondary
                )
                Text(
                    text = phoneNumber,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black,
                    color = CinnamonBrown
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Call button
                Surface(
                    onClick = onCall,
                    shape = CircleShape,
                    color = GoldenHoney,
                    modifier = Modifier.size(40.dp).testTag("${testTagPrefix}_call_button")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call $phoneNumber",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // WhatsApp button
                Surface(
                    onClick = onWhatsApp,
                    shape = CircleShape,
                    color = WhatsAppGreen,
                    modifier = Modifier.size(40.dp).testTag("${testTagPrefix}_whatsapp_button")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "WhatsApp $phoneNumber",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun InfoBulletRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(verticalAlignment = Alignment.Top) {
        Surface(
            shape = CircleShape,
            color = CinnamonBrown.copy(alpha = 0.12f),
            modifier = Modifier.size(32.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = CinnamonBrown,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = DarkTextPrimary
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = DarkTextSecondary
            )
        }
    }
}
