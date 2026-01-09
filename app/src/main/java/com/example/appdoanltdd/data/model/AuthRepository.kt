package com.example.appdoanltdd.data.model

import com.example.doan.data.api.RetrofitInstance
import com.example.doan.data.model.LoginResponse
import com.example.doan.data.model.User
import retrofit2.Response
import com.example.doan.data.model.RegisterResponse
import com.example.doan.data.model.RegisterRequest

class AuthRepository {

    suspend fun login(
        username: String,
        password: String
    ): Response<LoginResponse> {

        return RetrofitInstance.api.login(
            User(
                username = username,
                password = password
            )
        )
    }
    suspend fun register(
        username: String,
        password: String
    ): Response<RegisterResponse> {
        return RetrofitInstance.api.register(
            RegisterRequest(username, password)
        )
    }

}
