package com.example.doan.data.api

import com.example.doan.data.model.LoginResponse
import com.example.doan.data.model.User
import com.example.doan.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import com.example.doan.data.model.RegisterResponse

interface ApiService {
    @POST("api/users/login")
    suspend fun login(@Body request: User): Response<LoginResponse>

    @POST("api/users/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

}
