package com.example.appdoanltdd.data.network

import com.example.doan.data.model.LoginResponse
import com.example.doan.data.model.User
import com.example.appdoanltdd.data.model.ProductResponse
import com.example.doan.data.model.RegisterRequest
import com.example.appdoanltdd.data.model.ChangePasswordRequest
import com.example.appdoanltdd.data.model.BasicResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import com.example.doan.data.model.RegisterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @POST("api/users/login")
    suspend fun login(@Body request: User): Response<LoginResponse>

    @POST("api/users/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("api/users/change-password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): Response<BasicResponse>

    @GET("api/products/all")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 100
    ): Response<ProductResponse>

    /**
     * Tìm kiếm sản phẩm
     */
    @GET("api/products/search")
    suspend fun searchProducts(
        @Query("keyword") keyword: String
    ): Response<ProductResponse>

    /**
     * Lấy sản phẩm theo danh mục
     */
    @GET("api/products/category")
    suspend fun getProductsByCategory(
        @Query("category_id") categoryId: Int
    ): Response<ProductResponse>

    /**
     * Lấy sản phẩm theo thương hiệu
     */
    @GET("api/products/brand")
    suspend fun getProductsByBrand(
        @Query("brand_id") brandId: Int
    ): Response<ProductResponse>

    /**
     * Lấy chi tiết sản phẩm
     */
    @GET("api/products/detail")
    suspend fun getProductDetail(
        @Query("id") id: Int
    ): Response<ProductResponse>


}
