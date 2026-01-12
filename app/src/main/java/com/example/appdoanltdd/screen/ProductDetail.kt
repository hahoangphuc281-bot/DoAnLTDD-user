package com.example.appdoanltdd.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
fun ProductDetailScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel = viewModel()
) {
    val productState by viewModel.productState.collectAsState()
    var quantity by remember { mutableIntStateOf(1) }

    // Logic: Gọi API lấy dữ liệu chi tiết
    LaunchedEffect(productId) {
        viewModel.fetchProductDetail(productId)
    }

    Scaffold(
        bottomBar = {
            // Nút Thêm vào giỏ hàng đen tuyền đổ bóng nhẹ giống hình
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Button(
                    onClick = { /* Logic thêm vào giỏ hàng */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF262626)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Thêm vào giỏ hàng", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize().background(Color.White)) {
            when (productState) {
                is ProductState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is ProductState.Success -> {
                    // 1. Lấy danh sách ra giống hệt bên HomeScreen của bạn
                    val products = (productState as ProductState.Success).products

                    // 2. Kiểm tra nếu rỗng thì báo lỗi/trống
                    if (products.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Không tìm thấy thông tin sản phẩm")
                        }
                    } else {
                        // 3. Nếu có dữ liệu, lấy thằng đầu tiên để hiển thị chi tiết
                        val product = products.first()

                        DetailContentFixed(
                            product = product,
                            quantity = quantity,
                            onInc = { quantity++ },
                            onDec = { if (quantity > 1) quantity-- },
                            onBack = { navController.popBackStack() } // Sửa thành popBackStack cho chuẩn
                        )
                    }
                }
                is ProductState.DetailSuccess -> {
                    val product = (productState as ProductState.DetailSuccess).product
                    DetailContentFixed(
                        product = product,
                        quantity = quantity,
                        onInc = { quantity++ },
                        onDec = { if (quantity > 1) quantity-- },
                        onBack = { navController.popBackStack() }
                    )
                }

                // Nhánh Success cũ có thể giữ lại hoặc xóa tùy bạn,
                // nhưng DetailSuccess mới là cái quan trọng nhất lúc này.
                is ProductState.Success -> {
                    /* logic cũ của bạn */
                }

                is ProductState.Error -> Text(
                    text = (productState as ProductState.Error).message,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
                is ProductState.Error -> Text(
                    text = (productState as ProductState.Error).message,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
                else -> {}
            }
        }
    }
}

@Composable
fun DetailContentFixed(
    product: Product,
    quantity: Int,
    onInc: () -> Unit,
    onDec: () -> Unit,
    onBack: () -> Unit
) {
    val formatter = NumberFormat.getInstance(Locale("vi", "VN"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // PHẦN ĐỈNH: Ảnh tràn viền và các nút bấm đè lên
        Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Thanh Toolbar giả đè lên ảnh
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Black)
                }
                Row {
                    IconButton(onClick = { }) { Icon(Icons.Default.FavoriteBorder, null, tint = Color.Black) }
                    IconButton(onClick = { }) { Icon(Icons.Default.Share, null, tint = Color.Black) }
                }
            }
        }

        // PHẦN THÔNG TIN BÊN DƯỚI
        Column(modifier = Modifier.padding(20.dp)) {
            // Tên và Tag dung tích
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )
                Surface(
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    color = Color.White
                ) {
                    Text(
                        text = "50ml",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                repeat(5) {
                    Icon(Icons.Filled.Star, null, tint = Color(0xFFFFD700), modifier = Modifier.size(18.dp))
                }
                Text(" 5 (126 đánh giá)", fontSize = 13.sp, color = Color.Black)
            }

            // Mô tả
            Text(
                text = product.description,
                color = Color(0xFF888888),
                fontSize = 14.sp,
                lineHeight = 22.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Giá tiền màu đỏ hồng y hệt hình
            Text(
                text = "${formatter.format(product.getDiscountedPrice())}₫",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF0000), // Hoặc Color(0xFFE91E63) cho tone hồng
                modifier = Modifier.padding(vertical = 12.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp, color = Color.LightGray)

            // BỘ TĂNG GIẢM SỐ LƯỢNG GIỐNG HÌNH
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDec) {
                    Icon(Icons.Default.Remove, contentDescription = null, modifier = Modifier.size(20.dp))
                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color(0xFFDDDDDD)),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.White
                ) {
                    Text(
                        text = "$quantity",
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                IconButton(onClick = onInc) {
                    Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp))
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}