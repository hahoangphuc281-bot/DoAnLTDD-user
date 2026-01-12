// viewmodel/ProductViewModel.kt
package com.example.appdoanltdd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdoanltdd.data.model.Product
import com.example.appdoanltdd.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductState {
    object Idle : ProductState()
    object Loading : ProductState()
    data class Success(val products: List<Product>) : ProductState()

    // Thêm đúng dòng này là xong, không ảnh hưởng gì đến trang danh sách cũ
    data class DetailSuccess(val product: Product) : ProductState()

    data class Error(val message: String) : ProductState()
}

class ProductViewModel : ViewModel() {

    private val _productState = MutableStateFlow<ProductState>(ProductState.Idle)
    val productState: StateFlow<ProductState> = _productState

    /**
     * Lấy tất cả sản phẩm từ API
     */
    fun fetchProducts() {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                val response = RetrofitInstance.api.getProducts()

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded) {
                        _productState.value = ProductState.Success(body.data)
                    } else {
                        _productState.value = ProductState.Error(body.message)
                    }

                } else {
                    _productState.value = ProductState.Error(
                        "Lỗi: ${response.code()} - ${response.message()}"
                    )
                }

            } catch (e: Exception) {
                _productState.value = ProductState.Error(
                    "Lỗi: ${e.message ?: "Không xác định"}"
                )
            }
        }
    }

    /**
     * Tìm kiếm sản phẩm theo từ khóa
     */
    fun searchProducts(keyword: String) {
        if (keyword.isEmpty()) {
            fetchProducts()
            return
        }

        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                val response = RetrofitInstance.api.searchProducts(keyword)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded) {
                        _productState.value = ProductState.Success(body.data)
                    } else {
                        _productState.value = ProductState.Error(body.message)
                    }

                } else {
                    _productState.value = ProductState.Error(
                        "Tìm kiếm thất bại: ${response.message()}"
                    )
                }

            } catch (e: Exception) {
                _productState.value = ProductState.Error(
                    "Lỗi tìm kiếm: ${e.message ?: "Không xác định"}"
                )
            }
        }
    }

    /**
     * Lấy sản phẩm theo danh mục
     */
    fun getProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                val response = RetrofitInstance.api.getProductsByCategory(categoryId)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded) {
                        _productState.value = ProductState.Success(body.data)
                    } else {
                        _productState.value = ProductState.Error(body.message)
                    }

                } else {
                    _productState.value = ProductState.Error(
                        "Lấy danh mục thất bại: ${response.message()}"
                    )
                }

            } catch (e: Exception) {
                _productState.value = ProductState.Error(
                    "Lỗi: ${e.message ?: "Không xác định"}"
                )
            }
        }
    }

    /**
     * Lấy sản phẩm theo thương hiệu
     */
    fun getProductsByBrand(brandId: Int) {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                val response = RetrofitInstance.api.getProductsByBrand(brandId)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded) {
                        _productState.value = ProductState.Success(body.data)
                    } else {
                        _productState.value = ProductState.Error(body.message)
                    }

                } else {
                    _productState.value = ProductState.Error(
                        "Lấy thương hiệu thất bại: ${response.message()}"
                    )
                }

            } catch (e: Exception) {
                _productState.value = ProductState.Error(
                    "Lỗi: ${e.message ?: "Không xác định"}"
                )
            }
        }
    }
    /**
     * Lấy chi tiết một sản phẩm theo ID
     */
    fun fetchProductDetail(productId: Int) {
        viewModelScope.launch {
            _productState.value = ProductState.Loading
            try {
                // Sử dụng hàm getProductDetail mà bạn đã ghi trong ApiService
                val response = RetrofitInstance.api.getProductDetail(productId)

                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!

                    if (body.succeeded && body.data.isNotEmpty()) {
                        // Tái chế class cũ: lấy phần tử đầu tiên của mảng data
                        _productState.value = ProductState.DetailSuccess(body.data[0])
                    } else {
                        _productState.value = ProductState.Error(body.message)
                    }
                } else {
                    _productState.value = ProductState.Error("Lỗi: ${response.code()}")
                }
            } catch (e: Exception) {
                _productState.value = ProductState.Error("Lỗi: ${e.message ?: "Không xác định"}")
            }
        }
    }
    /**
     * Reset state về Idle
     */
    fun resetState() {
        _productState.value = ProductState.Idle
    }

    /**
     * Thử lại khi có lỗi
     */
    fun retry() {
        fetchProducts()
    }
}