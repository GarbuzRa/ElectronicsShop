package com.example.electronicsshop.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponce(
    val products: List<Product>
)

data class Product(
    @SerializedName("product_id") val productId: Int,
    val description: String,
    val name: String,
    val price: String,
    val status: String,
    @SerializedName("stock_quantity") val stockQuantity: Int,
    @SerializedName("category_id") val categoryId: Int
)
