package com.example.appdoanltdd.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.appdoanltdd.R
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.draw.clip

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

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
fun homescreen(navController: NavController){

    Box(modifier = Modifier.fillMaxSize().fillMaxWidth().background(Color(0xFFF9D1B7))){
        Column(modifier = Modifier.fillMaxWidth().fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo N",
                modifier = Modifier.fillMaxWidth().
                height(240.dp)


            )

            // 🔹 GIỮ NGUYÊN THÔNG SỐ CỦA BẠN — CHỈ BỌC BOX
            Box(
                modifier = Modifier
                    .fillMaxWidth()

                    .height(500.dp)
                    .padding(top = 15.dp),
                contentAlignment = Alignment.Center
            ) {

                // 🌸 Hoa trái trên
                Image(
                    painter = painterResource(R.drawable.hoa1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(380.dp)
                        .offset(x = (-65).dp, y = (-100).dp)
                )

                // 🌸 Hoa phải dưới
                Image(
                    painter = painterResource(R.drawable.hoa1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(260.dp)
                        .offset(x = (70).dp, y = (90).dp)
                        .graphicsLayer(
                            scaleX = -1f, scaleY = 1f, rotationZ = 90f)
                )

                // 🧴 ẢNH CHAI — NGUYÊN Y CHỈNH CỦA BẠN
                Image(
                    painter = painterResource(id = R.drawable.chance),
                    contentDescription = "Mô tả hình ảnh",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(top = 15.dp),
                    contentScale = ContentScale.Fit
                )
            }


            Text("Experience the Essence of", textAlign = TextAlign.Center,fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            Text("Luxury Perfumes", textAlign = TextAlign.Center, fontSize = 20.sp, modifier = Modifier.fillMaxWidth())

            Button(
                onClick = { navController.navigate("login")},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp).padding(top = 20.dp, bottom = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2A2A29),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text("Get Started", fontSize = 24.sp)
            }

        }

    }

}