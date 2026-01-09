// File: AccountScreen.kt
package com.example.appdoanltdd.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appdoanltdd.ui.component.BottomNavBar

// Màu sắc
private val MintGreenColor = Color(0xFFE3F6FA)
private val ButtonRedColor = Color(0xFFA5665D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    // Thêm các callback để xử lý điều hướng
    onTrackOrderClick: () -> Unit,
    onChangeInfoClick: () -> Unit // Dành cho mục "Change information" chuyển sang màn hình Form
) {
    Scaffold(
        containerColor = MintGreenColor,
        topBar = {
            TopAppBar(
                title = { Text("Account", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Outlined.Settings, contentDescription = "Settings", tint = Color.Black) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = Color.Black) }
                    IconButton(onClick = {}) { Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat", tint = Color.Black) }
                },
            )
        },
        bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Avatar & Name
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(100.dp).clip(CircleShape),
                tint = Color(0xFF64B5F6)
            )
            Text("Nguyễn Văn A", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))

            Spacer(modifier = Modifier.height(30.dp))

            // --- MENU LIST ---

            // 1. Track Order -> Gọi callback onTrackOrderClick khi nhấn
            MenuItem(
                icon = Icons.Outlined.ShoppingBag,
                text = "Track order",
                onClick = onTrackOrderClick
            )

            // 2. Change Information -> Gọi callback onEditProfileClick
            MenuItem(
                icon = Icons.Outlined.Person,
                text = "Change information",
                onClick = onChangeInfoClick
            )

            MenuItem(icon = Icons.Outlined.Notifications, text = "Notifications", onClick = {})
            MenuItem(icon = Icons.Outlined.Security, text = "Privacy", onClick = {})
            MenuItem(icon = Icons.Outlined.HelpOutline, text = "Help", onClick = {})
            MenuItem(icon = Icons.Outlined.Language, text = "Language",trailingText = "English", onClick = {})
            MenuItem(icon = Icons.Outlined.Notifications, text = "Notifications", onClick = {})
            MenuItem(icon = Icons.Outlined.Info, text = "About", onClick = {})

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = ButtonRedColor),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Log out", color = Color.White)
            }
        }
    }
}

@Composable
fun MenuItem(icon: ImageVector, text: String, trailingText: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onClick() }, // Xử lý sự kiện click tại đây
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, modifier = Modifier.weight(1f))
        if (trailingText != null) {
            Text(text = trailingText, color = Color.Gray, fontSize = 14.sp)
        } else {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }
}