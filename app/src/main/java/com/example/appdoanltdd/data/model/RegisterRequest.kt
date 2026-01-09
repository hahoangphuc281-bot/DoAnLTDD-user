package com.example.doan.data.model

data class RegisterRequest(
    val username: String,
    val password: String
)
data class RegisterResponse(
    val succeeded: Boolean,
    val message: String
)