
package com.example.appdoanltdd.navigation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
// Hoặc nếu dùng Material 3
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.appdoanltdd.R
import com.example.appdoanltdd.screen.ProductScreen
import com.example.appdoanltdd.screen.loginscreen
import com.example.appdoanltdd.screen.reiseger
import com.example.appdoanltdd.screen.homescreen
import com.example.appdoanltdd.screen.forgotpass
import com.example.appdoanltdd.screen.ProductDetailScreen
import com.example.appdoanltdd.viewmodel.AuthViewModel

@ExperimentalMaterial3Api
@Composable
fun myapp(){
    val navController = rememberNavController()
    NavHost(
        navController= navController,
        startDestination = "home"

    ){
        composable("home"){homescreen(navController)}
        composable("login"){loginscreen(navController)}
        composable("Pass"){forgotpass(navController)}
        composable("resei"){reiseger(navController)}
        composable("Pro"){ ProductScreen(navController) }
        composable(
            route = "detail/{productId}", // CHỈ VIẾT THẾ NÀY THÔI
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            // 1. Lấy cái ID từ trên đường dẫn xuống
            val id = backStackEntry.arguments?.getInt("productId") ?: 0

            // 2. Truyền nó vào ProductDetailScreen (Chỗ này nãy bạn bị thiếu nè)
            ProductDetailScreen(
                navController = navController,
                productId = id // <--- Truyền productId vào đây là hết lỗi!
            )
        }
    }
}