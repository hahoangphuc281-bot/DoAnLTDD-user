package com.example.appdoanltdd.screen

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
import com.example.appdoanltdd.viewmodel.AuthState


@ExperimentalMaterial3Api
@Composable
fun reiseger(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var cpass by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }
    var emails by remember { mutableStateOf("") }
    val viewModel: AuthViewModel = viewModel()
    val authState by viewModel.authState.collectAsState()




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

            Spacer(modifier = Modifier.height(230.dp)) // Thêm khoảng cách nếu cần
            OutlinedTextField(
                value = emails,
                onValueChange = { emails = it },
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

            Spacer(modifier = Modifier.height(30.dp)) // Thêm khoảng cách nếu cần

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

            // Thêm khoảng cách nếu cần

            Spacer(modifier = Modifier.height(30.dp))

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
            Spacer(modifier = Modifier.height(30.dp)) // Thêm khoảng cách nếu cần
            OutlinedTextField(
                value = cpass,
                onValueChange = { cpass = it },
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
                onClick = { if (pass != cpass) {
                    // show error
                    return@Button
                }
                    viewModel.register(user, emails,cpass)},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                    .padding(top = 20.dp, bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF8A5252),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("Create Account", fontSize = 20.sp)
            }
            when (authState) {
                is AuthState.Loading -> {
                    // show loading
                }
                is AuthState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("Login")
                    }
                }
                is AuthState.Error -> {
                    Text(
                        text = (authState as AuthState.Error).message,
                        color = Color.Red
                    )
                }
                else -> {}
            }

            Row(modifier = Modifier.fillMaxWidth().padding(start = 50.dp)) {
                Text(
                    "Already have an account?",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color(0xFF8A5252)
                )
                Text(
                    "  Login",
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
}