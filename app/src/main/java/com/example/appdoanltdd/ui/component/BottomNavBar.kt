package com.example.appdoanltdd.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavBar(
    selectedItemIndex: Int = 0 // Thêm tham số này (0: Home, 1: Wishlist, 2: Cart, 3: Account)
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        // Item 0: Home
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home", tint = if(selectedItemIndex == 0) Color.Blue else Color.Black) },
            selected = selectedItemIndex == 0,
            onClick = { /* Navigate to Home */ }
        )
        // Item 1: Wishlist
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Wishlist", tint = if(selectedItemIndex == 1) Color.Blue else Color.Black) },
            selected = selectedItemIndex == 1,
            onClick = { /* Navigate to Wishlist */ }
        )
        // Item 2: Cart
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = if(selectedItemIndex == 2) Color.Blue else Color.Black) },
            selected = selectedItemIndex == 2,
            onClick = { /* Navigate to Cart */ }
        )
        // Item 3: Account
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Account", tint = if(selectedItemIndex == 3) Color.Blue else Color.Black) },
            selected = selectedItemIndex == 3,
            onClick = { /* Navigate to Account */ }
        )
    }
}