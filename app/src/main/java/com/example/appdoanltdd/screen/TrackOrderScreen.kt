package com.example.appdoanltdd.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoanltdd.ui.component.BottomNavBar

val TrackOrderScreenBackgroundColor = Color(0xFFE3F6FA)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOrderScreen(onBackClick: () -> Unit) {
    Scaffold(
        containerColor = TrackOrderScreenBackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Track order",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                },
                // --- BỔ SUNG: Nút Back (Mũi tên quay lại) ---
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                // ---------------------------------------------
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Outlined.Settings, contentDescription = "Settings", tint = Color.Black) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = Color.Black) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat", tint = Color.Black) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = TrackOrderScreenBackgroundColor)
            )
        },
        bottomBar = {
            BottomNavBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- 1. Order Status Tabs (Thanh trạng thái) ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OrderStatusBar()
            }

            // --- 2. Empty State Content (Phần giữa) ---
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFE68A8A))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Assignment,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp),
                            tint = Color(0xFF2C3E50)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "You don't have any\norders yet.",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp
                    )
                }
            }
        }
    }
}

@Composable
fun OrderStatusBar(
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {
    val icons = listOf(
        Icons.Outlined.AccountBalanceWallet,
        Icons.Outlined.LocalShipping,
        Icons.Outlined.Inventory2,
        Icons.Outlined.RateReview
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEachIndexed { index, icon ->
            val selected = selectedIndex == index

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        if (selected) Color(0xFFE3F2FD) else Color.Transparent
                    )
                    .clickable { onItemSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selected) Color(0xFF1976D2) else Color.Black
                )
            }
        }
    }
}

