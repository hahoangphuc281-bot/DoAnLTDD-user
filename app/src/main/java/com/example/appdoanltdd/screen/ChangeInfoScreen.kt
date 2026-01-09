package com.example.appdoanltdd.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Sử dụng lại bộ màu từ AccountScreen để đồng bộ ---
private val BackgroundColor = Color(0xFFE3F6FA)
private val DarkButtonColor = Color(0xFF2B2B2B)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeInfoScreen(
    onBackClick: () -> Unit = {} // Hàm callback để xử lý khi nhấn nút Back
) {
    // --- Khởi tạo dữ liệu mẫu (State) ---
    var fullName by remember { mutableStateOf("Nguyễn Văn A") }
    var email by remember { mutableStateOf("nguyenvana@gmail.com") }
    var phoneNumber by remember { mutableStateOf("0377xxxxxx") }
    var address by remember { mutableStateOf("35/5A, Bế Văn Cấm, Quận 7, TP.HCM") }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edit Profile",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()), // Cho phép cuộn nếu bàn phím che
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // --- Form Nhập liệu ---
            // Gọi hàm Component nhập liệu viết ở dưới cho gọn code
            CustomTextField(label = "Full Name", value = fullName, onValueChange = { fullName = it })
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(label = "Email", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(label = "Phone Number", value = phoneNumber, onValueChange = { phoneNumber = it })
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Address",
                value = address,
                onValueChange = { address = it },
                isSingleLine = false, // Địa chỉ có thể dài, cho phép xuống dòng
                lines = 3
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- Nút Save ---
            Button(
                onClick = {
                    // Xử lý lưu thông tin tại đây
                    // Ví dụ: viewModel.updateUserInfo(...)
                    onBackClick() // Lưu xong thì quay lại màn hình trước
                },
                colors = ButtonDefaults.buttonColors(containerColor = DarkButtonColor),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Save Changes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

// --- Component Text Field Tùy chỉnh (Nền trắng, bo góc) ---
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean = true,
    lines: Int = 1
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Label nhỏ phía trên ô nhập
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp, start = 4.dp)
        )

        // Ô nhập liệu
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp), // Bo góc 12dp giống Card ở màn hình Account
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = DarkButtonColor, // Khi bấm vào viền màu đen xám
                unfocusedBorderColor = Color.Transparent, // Bình thường không hiện viền (hoặc màu nhạt)
                cursorColor = DarkButtonColor,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = isSingleLine,
            maxLines = lines,
            minLines = lines
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChangeInfoScreenPreview() {
    ChangeInfoScreen()
}