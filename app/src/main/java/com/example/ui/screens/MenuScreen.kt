package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.FoodCategory
import com.example.data.model.FoodItem
import com.example.ui.components.CategoryFilterRow
import com.example.ui.components.FoodSearchBar
import com.example.ui.components.ProductCard
import com.example.ui.theme.AmberSoft
import com.example.ui.theme.AmberWarm
import com.example.ui.theme.CinnamonBrown
import com.example.ui.theme.CinnamonBrownDark
import com.example.ui.theme.DarkTextPrimary
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.GoldenHoney
import com.example.ui.theme.SriLankaGreen
import com.example.ui.theme.WhatsAppGreen

@Composable
fun MenuScreen(
    catalogItems: List<FoodItem>,
    selectedCategory: FoodCategory,
    searchQuery: String,
    onCategorySelected: (FoodCategory) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onProductClick: (FoodItem) -> Unit,
    onQuickAdd: (FoodItem) -> Unit,
    onWhatsAppDirectClick: () -> Unit,
    onCallDirectClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val featuredList = catalogItems.filter { it.isFeatured || it.isBestSeller }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 165.dp),
        modifier = modifier
            .fillMaxSize()
            .testTag("menu_screen_grid"),
        contentPadding = PaddingValues(bottom = 90.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Hero Header Banner
        item(span = { GridItemSpan(maxLineSpan) }) {
            HeroDistributorBanner(
                onWhatsAppDirectClick = onWhatsAppDirectClick,
                onCallDirectClick = onCallDirectClick
            )
        }

        // Search Bar & Filter Section
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                FoodSearchBar(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChanged
                )
            }
        }

        // Category Chips
        item(span = { GridItemSpan(maxLineSpan) }) {
            CategoryFilterRow(
                selectedCategory = selectedCategory,
                onCategorySelected = onCategorySelected
            )
        }

        // Featured Best Sellers carousel (only if on ALL or SPECIALS category and no active search)
        if ((selectedCategory == FoodCategory.ALL || selectedCategory == FoodCategory.SPECIALS) && searchQuery.isBlank() && featuredList.isNotEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "⭐ Popular & Best Sellers",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = CinnamonBrown
                            )
                        }
                        Text(
                            text = "${featuredList.size} items",
                            style = MaterialTheme.typography.labelMedium,
                            color = DarkTextSecondary
                        )
                    }

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(featuredList) { item ->
                            FeaturedCompactCard(
                                product = item,
                                onClick = { onProductClick(item) },
                                onQuickAdd = { onQuickAdd(item) }
                            )
                        }
                    }
                }
            }
        }

        // Catalog Header Title
        item(span = { GridItemSpan(maxLineSpan) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = when {
                        searchQuery.isNotBlank() -> "Search Results for \"$searchQuery\""
                        selectedCategory == FoodCategory.ALL -> "Full Food Catalog (සියලුම වර්ග)"
                        else -> "${selectedCategory.displayName} (${selectedCategory.sinhalaName})"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextPrimary
                )
                Text(
                    text = "${catalogItems.size} items",
                    style = MaterialTheme.typography.labelMedium,
                    color = DarkTextSecondary
                )
            }
        }

        // Empty state when search has no results
        if (catalogItems.isEmpty()) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = null,
                            tint = CinnamonBrown,
                            modifier = Modifier.size(54.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No items matched your search",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Try searching for Butter Cake, Kokis, Dodol, or Roll",
                            style = MaterialTheme.typography.bodySmall,
                            color = DarkTextSecondary
                        )
                    }
                }
            }
        } else {
            // Main Product Grid
            items(catalogItems, key = { it.id }) { product ->
                Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                    ProductCard(
                        product = product,
                        onCardClick = { onProductClick(product) },
                        onQuickAdd = { onQuickAdd(product) }
                    )
                }
            }
        }
    }
}

@Composable
fun HeroDistributorBanner(
    onWhatsAppDirectClick: () -> Unit,
    onCallDirectClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp),
        colors = CardDefaults.cardColors(containerColor = CinnamonBrown),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner),
                    contentDescription = "Fresh Cakes & Sri Lankan Sweets",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark gradient overlay for text readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black.copy(alpha = 0.85f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AmberWarm
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Direct Distributor & Baker",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Jayasanka Food Distributors",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Text(
                        text = "Fresh Homemade Cakes, Authentic Sweets & Savory Catering",
                        style = MaterialTheme.typography.bodySmall,
                        color = AmberSoft,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Quick Order Action Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CinnamonBrownDark)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Need Fast Delivery or Custom Cake?",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                    Text(
                        text = "📞 0713656883 • 0783368792",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = AmberWarm
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        onClick = onWhatsAppDirectClick,
                        shape = RoundedCornerShape(16.dp),
                        color = WhatsAppGreen,
                        modifier = Modifier.testTag("banner_whatsapp_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "WhatsApp",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "WhatsApp",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Surface(
                        onClick = onCallDirectClick,
                        shape = RoundedCornerShape(16.dp),
                        color = GoldenHoney,
                        modifier = Modifier.testTag("banner_call_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = "Call",
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Call",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedCompactCard(
    product: FoodItem,
    onClick: () -> Unit,
    onQuickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .width(180.dp)
            .testTag("featured_card_${product.id}")
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                val imageRes = when (product.imageDrawableName) {
                    "img_cake_category" -> R.drawable.img_cake_category
                    "img_sweets_category" -> R.drawable.img_sweets_category
                    else -> R.drawable.img_hero_banner
                }
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = product.nameEn,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Surface(
                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                    color = CinnamonBrown,
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = product.nameSi,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = product.nameEn,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rs. %,.0f".format(product.basePrice),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Black,
                        color = CinnamonBrown
                    )
                    Surface(
                        onClick = onQuickAdd,
                        shape = CircleShape,
                        color = AmberWarm,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "+",
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
