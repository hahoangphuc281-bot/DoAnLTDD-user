// data/model/Product.kt
package com.example.appdoanltdd.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Original_Price")
    val originalPrice: Double,
    @SerializedName("Quantity")
    val quantity: Int,
    @SerializedName("Image")
    val image: String,
    @SerializedName("Brand_id")
    val brandId: Int,
    @SerializedName("Category_id")
    val categoryId: Int,
    @SerializedName("Discount_percent")
    val discountPercent: Int,
    @SerializedName("Active")
    val active: Int
) {
    /**
     * Tính giá sau khi giảm
     */
    fun getDiscountedPrice(): Double {
        return originalPrice * (1 - discountPercent / 100.0)
    }
}