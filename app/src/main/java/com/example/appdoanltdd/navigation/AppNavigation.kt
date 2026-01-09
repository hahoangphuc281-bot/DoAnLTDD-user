package com.example.appdoanltdd.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appdoanltdd.screen.AccountScreen
import com.example.appdoanltdd.screen.ChangeInfoScreen
import com.example.appdoanltdd.screen.TrackOrderScreen

// Định nghĩa các tên đường dẫn (route)
object ScreenRoutes {
    const val ACCOUNT = "account"
    const val TRACK_ORDER = "track_order"
    const val CHANGE_INFO = "change_info"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.ACCOUNT) {
        
        composable(ScreenRoutes.ACCOUNT) {
            AccountScreen(
                // Truyền hành động: Khi nhấn "Track order" -> điều hướng sang trang TrackOrder
                onTrackOrderClick = { navController.navigate(ScreenRoutes.TRACK_ORDER) },

                // Truyền hành động: Khi nhấn "Change information" -> điều hướng sang trang Form
                onChangeInfoClick = { navController.navigate(ScreenRoutes.CHANGE_INFO) }
            )
        }
        
        composable(ScreenRoutes.TRACK_ORDER) {
            TrackOrderScreen(
                // Xử lý nút Back (nếu có) để quay lại màn hình trước
                onBackClick = { navController.popBackStack() }
            )
        }
        
        composable(ScreenRoutes.CHANGE_INFO) {
            ChangeInfoScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}