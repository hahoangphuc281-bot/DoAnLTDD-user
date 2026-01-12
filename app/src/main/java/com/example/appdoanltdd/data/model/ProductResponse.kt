package com.example.appdoanltdd.data.model

// data/model/ProductResponse.kt


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("succeeded")
    val succeeded: Boolean,
    @SerializedName("data")
    val data: List<Product>,
    @SerializedName("message")
    val message: String
)