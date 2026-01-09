package com.example.doan.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appdoanltdd.R
import com.example.appdoanltdd.viewmodel.AuthState
import com.example.appdoanltdd.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@ExperimentalMaterial3Api
@Composable
fun loginscreen(navController: NavController){

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    var showPass by remember { mutableStateOf(false) }
    val authViewModel: AuthViewModel = viewModel()


    val authState by authViewModel.authState.collectAsState()

    val context = LocalContext.current

    // Xử lý state
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                val successState = authState as AuthState.Success
                Toast.makeText(context, successState.message, Toast.LENGTH_SHORT).show()
                // Lưu token nếu cần
                // SharedPreferences hoặc DataStore
                // Chuyển màn hình
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
                authViewModel.resetState()
            }
            is AuthState.Error -> {
                val errorState = authState as AuthState.Error
                Toast.makeText(context, errorState.message, Toast.LENGTH_LONG).show()
                authViewModel.resetState()
            }
            else -> {}
        }
    }





    Box(modifier = Modifier.fillMaxSize().fillMaxWidth().background(Color(0xFFC2D2D1))){
        Box(
            modifier = Modifier

                .fillMaxWidth()
                .height(500.dp)
            ,
            contentAlignment = Alignment.Center
        ) {



            // 🧴 ẢNH CHAI — NGUYÊN Y CHỈNH CỦA BẠN
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Mô tả hình ảnh",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(top = 15.dp),
                contentScale = ContentScale.Fit
            )
        }
        Column(modifier = Modifier.fillMaxWidth().fillMaxSize().padding(horizontal = 32.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {



            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                placeholder = { Text("Username") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(16.dp)) // Thêm khoảng cách nếu cần

// Password
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                placeholder = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { showPass = !showPass }) {
                        Icon(
                            imageVector = if (showPass) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )
            Text("Forgot password ?", textAlign = TextAlign.Center,fontSize = 15.sp, modifier = Modifier.fillMaxWidth().padding(start =200.dp,top =20.dp).clickable {
                navController.navigate("Pass")
            }, color = Color(0xFF8A5252))
            Button(
                onClick = {if (user.isNotBlank() && pass.isNotBlank()) {
                    authViewModel.login(user, pass)
                } else {
                    Toast.makeText(
                        context,
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_SHORT
                    ).show()
                }},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                    .padding(top = 20.dp, bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A5252),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("Login", fontSize = 20.sp)
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 100.dp)) {
                Text("No account yet?", textAlign = TextAlign.Center,fontSize = 15.sp,color = Color(0xFF8A5252))
                Text("Register", textAlign = TextAlign.Center,fontSize = 15.sp, modifier = Modifier.clickable {
                    navController.navigate("resei")
                }, color = Color(0xFF8A5252), fontWeight = FontWeight.Bold)

            }

        }

    }

}