package com.example.appdoanltdd.screen

import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.appdoanltdd.viewmodel.AuthViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.appdoanltdd.viewmodel.AuthState
@ExperimentalMaterial3Api
@Composable
fun forgotpass(navController: NavController){

    var pass by remember { mutableStateOf("") }
    var cpass by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current
    var oldPass by remember { mutableStateOf("") }      // mật khẩu hiện tại
    var newPass by remember { mutableStateOf("") }      // mật khẩu mới
    var confirmPass by remember { mutableStateOf("") }  // xác nhận mật khẩu mới
    when (authState) {
        is AuthState.Loading -> {
            // nếu muốn hiện loading thì làm ở đây
        }

        is AuthState.Success -> {
            Toast.makeText(
                context,
                (authState as AuthState.Success).message,
                Toast.LENGTH_SHORT
            ).show()

            authViewModel.resetState()

            navController.navigate("Login") {
                popUpTo("forgotpass") { inclusive = true }
            }
        }

        is AuthState.Error -> {
            Toast.makeText(
                context,
                (authState as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            authViewModel.resetState()
        }

        else -> {}
    }





    Box(modifier = Modifier.fillMaxSize().fillMaxWidth().background(Color(0xFFC2D2D1))) {
        Box(
            modifier = Modifier

                .fillMaxWidth()
                .height(500.dp),
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
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxSize().padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(150.dp)) // Thêm khoảng cách nếu cần
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.AssignmentInd, contentDescription = null) },
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
            // Thêm khoảng cách nếu cần

// Password
            Spacer(modifier = Modifier.height(30.dp))
             // Thêm khoảng cách nếu cần
            OutlinedTextField(
                value = newPass,
                onValueChange = { newPass = it },
                placeholder = { Text(" Comfrim Password") },
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
            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = {

                    if (email.isBlank() || newPass.isBlank()) {
                        Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                        return@Button
                    }



                    authViewModel.changePassword(
                        email = email,

                        newPass = newPass
                    )

                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                    .padding(top = 20.dp, bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A5252),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("Confirm", fontSize = 20.sp)
            }
            Text(
                "Cancel",
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                modifier = Modifier.clickable {
                    navController.navigate("Login")
                },
                color = Color(0xFF8A5252),
                fontWeight = FontWeight.Bold
            )





        }
    }
}