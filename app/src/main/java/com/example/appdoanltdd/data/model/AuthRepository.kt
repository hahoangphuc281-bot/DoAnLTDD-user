package com.example.appdoanltdd.data.model

import com.example.appdoanltdd.data.network.RetrofitInstance
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
    // Sửa hàm này trong Repository của bạn
    suspend fun register(
        username: String,
        email: String,      // <-- Thêm email vào đây
        password: String
    ): Response<RegisterResponse> {
        return RetrofitInstance.api.register(
            RegisterRequest(username, email, password) // Truyền đủ 3 món
        )
    }
    suspend fun changePassword(
        request: ChangePasswordRequest
    ): Response<BasicResponse> {
        return RetrofitInstance.api.changePassword(request)
    }



}
