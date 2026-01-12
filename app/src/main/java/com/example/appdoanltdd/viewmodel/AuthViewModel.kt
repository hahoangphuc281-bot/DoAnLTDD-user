package com.example.appdoanltdd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdoanltdd.data.model.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.appdoanltdd.data.model.ChangePasswordRequest

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String, val token: String?) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.login(username, password)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded) {

                        // 🔴 CHẶN ADMIN
                        if (body.is_admin == 0) {
                            _authState.value = AuthState.Success(
                                message = body.message,
                                token = body.token
                            )
                        } else {
                            _authState.value = AuthState.Error(
                                "Tài khoản admin không được phép đăng nhập ứng dụng này"
                            )
                        }

                    } else {
                        _authState.value = AuthState.Error(body.message)
                    }

                } else {
                    _authState.value =
                        AuthState.Error("Đăng nhập thất bại: ${response.message()}")
                }

            } catch (e: Exception) {
                _authState.value =
                    AuthState.Error("Lỗi: ${e.message ?: "Không xác định"}")
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
    // Trong file AuthViewModel.kt
    fun register(username: String, email: String, password: String) { // Thêm email vào tham số
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                // Truyền thêm email vào hàm repository.register
                val response = repository.register(username, email, password)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    if (body.succeeded) {
                        _authState.value = AuthState.Success(body.message, null)
                    } else {
                        _authState.value = AuthState.Error(body.message)
                    }
                } else {
                    _authState.value = AuthState.Error("Đăng ký thất bại")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Lỗi: ${e.message}")
            }
        }
    }
    fun changePassword(email: String, newPass: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val res = repository.changePassword(
                    ChangePasswordRequest(email, newPass)
                )

                if (res.isSuccessful && res.body()?.succeeded == true) {
                    _authState.value = AuthState.Success(res.body()!!.message, null)
                } else {
                    _authState.value = AuthState.Error(
                        res.body()?.message ?: "Đổi mật khẩu thất bại"
                    )
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Lỗi")
            }
        }
    }



}
