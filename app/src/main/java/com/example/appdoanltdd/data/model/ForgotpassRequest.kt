package com.example.appdoanltdd.data.model

data class ChangePasswordRequest(
    val email: String,

    val newPassword: String
)

data class BasicResponse(
    val succeeded: Boolean,
    val message: String
)

