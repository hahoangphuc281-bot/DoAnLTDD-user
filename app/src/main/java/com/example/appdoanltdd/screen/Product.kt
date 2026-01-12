// screen/HomeScreen.kt
package com.example.appdoanltdd.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appdoanltdd.data.model.Product

import com.example.appdoanltdd.viewmodel.ProductState
import com.example.appdoanltdd.viewmodel.ProductViewModel
import java.text.NumberFormat
import java.util.*


@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = viewModel()
) {
    val productState = viewModel.productState.collectAsState().value
    var selectedFilter by remember { mutableStateOf("TẤT CẢ") }
    var currentTab by remember { mutableStateOf("home") }

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ... (Phần HEADER giữ nguyên)


        // FILTER CHIPS - CẬP NHẬT LOGIC TẠI ĐÂY
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Định nghĩa danh sách Filter đi kèm với ID trong Database
            val filterOptions = listOf(
                "TẤT CẢ" to null,
                "Nam" to 1,
                "Nữ" to 2,
                "UNISEX" to 3
            )

            filterOptions.forEach { (label, id) ->
                FilterChip(
                    selected = selectedFilter == label,
                    onClick = {
                        selectedFilter = label
                        // Nếu là TẤT CẢ thì fetch toàn bộ, ngược lại fetch theo Category ID
                        if (id == null) {
                            viewModel.fetchProducts()
                        } else {
                            viewModel.getProductsByCategory(id)
                        }
                    },
                    label = { Text(label, fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFFE91E63),
                        selectedLabelColor = Color.White,
                        containerColor = Color.White,
                        labelColor = Color.Black
                    ),
                    modifier = Modifier.height(36.dp),
                    shape = RoundedCornerShape(18.dp)
                )
            }
            IconButton(
                onClick = {
                    /* Viết lệnh chuyển sang màn hình tìm kiếm ở đây */
                    // ví dụ: navController.navigate("search")
                },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart, // Tên Icon (viết hoa chữ cái đầu)
                    contentDescription = "Tìm kiếm",    // Mô tả cho người khiếm thị (hoặc để null)
                    tint = Color.Black,
                    modifier = Modifier.offset(x = (9.5).dp)// Màu sắc của Icon
                )
            }

        }

        // TITLE AND COUNT
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sản Phẩm",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            when (productState) {
                is ProductState.Success -> {
                    Text(
                        text = "${productState.products.size} sản phẩm",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                else -> {
                    Text(
                        text = "0 sản phẩm",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        // PRODUCTS GRID
        when (productState) {
            is ProductState.Idle -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Khởi động...")
                }
            }
            is ProductState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProductState.Success -> {
                val products = (productState as ProductState.Success).products
                if (products.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Không có sản phẩm")
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(products) { product ->
                            ProductCard(product = product, onClick = {navController.navigate("detail/${product.id}")})
                        }
                    }
                }
            }
            is ProductState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = (productState as ProductState.Error).message,
                            color = Color.Red,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.retry() }
                        ) {
                            Text("Thử lại")
                        }
                    }
                }
            }
            // CHỈ THÊM ĐÚNG DÒNG NÀY Ở ĐÂY ĐỂ HẾT LỖI
            else -> {}
        }

        // BOTTOM NAVIGATION
        BottomNavigationBar(
            currentTab = currentTab,
            onTabChange = { currentTab = it }
        )
    }
}

@Composable
fun ProductCard(product: Product,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        // IMAGE
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BRAND
        Text(
            text = "Brand ID: ${product.brandId}",
            fontSize = 10.sp,
            color = Color.Gray
        )

        // NAME
        Text(
            text = product.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // PRICE AND BUTTON
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val formatter = NumberFormat.getInstance(Locale("vi", "VN"))
            val discountedPrice = product.getDiscountedPrice()

            Column(modifier = Modifier.weight(1f)) {
                if (product.discountPercent > 0) {
                    Text(
                        text = "${formatter.format(product.originalPrice.toLong())}₫",
                        fontSize = 9.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "${formatter.format(discountedPrice.toLong())}₫",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                } else {
                    Text(
                        text = "${formatter.format(product.originalPrice.toLong())}₫",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .height(32.dp)
                    .width(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE91E63)
                ),
                contentPadding = PaddingValues(4.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    text = "Mua",
                    fontSize = 10.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentTab: String,
    onTabChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // HOME
        IconButton(
            onClick = { onTabChange("home") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = if (currentTab == "home") Color(0xFF2196F3) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // WISHLIST
        IconButton(
            onClick = { onTabChange("wishlist") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Wishlist",
                tint = if (currentTab == "wishlist") Color(0xFF2196F3) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // CART
        IconButton(
            onClick = { onTabChange("cart") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = if (currentTab == "cart") Color(0xFF2196F3) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        // PROFILE
        IconButton(
            onClick = { onTabChange("profile") },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = if (currentTab == "profile") Color(0xFF2196F3) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}