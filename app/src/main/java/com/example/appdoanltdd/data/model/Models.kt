package com.example.doan.data.model

data class User(
    val username: String,

    val password: String
)

data class LoginResponse(
    val succeeded: Boolean,
    val message: String,
    val token: String?,
    val is_admin: Int
)


data class UserData(
    val id: String,
    val username: String,
    val email: String? = null
)