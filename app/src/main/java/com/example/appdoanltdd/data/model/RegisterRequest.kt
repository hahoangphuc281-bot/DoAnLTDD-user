package com.example.doan.data.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
data class RegisterResponse(
    val succeeded: Boolean,
    val email: String,
    val message: String
)