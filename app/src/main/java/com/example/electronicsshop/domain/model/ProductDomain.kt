package com.example.electronicsshop.domain.model

import com.example.electronicsshop.data.model.Product
import com.google.gson.annotations.SerializedName

data class ProductDomain(
@SerializedName("product_id") val productId: Int,
val description: String,
val name: String,
val price: String,
val status: String,
@SerializedName("stock_quantity") val stockQuantity: Int,
@SerializedName("category_id") val categoryId: Int
)

fun Product.toDomainModel() = ProductDomain(
    productId = this.productId,
    description = this.description,
    name = this.name,
    price = this.price,
    status = this.status,
    stockQuantity = this.stockQuantity,
    categoryId = this.categoryId
)

fun ProductDomain.toDataModel() = Product(
    productId = this.productId,
    description = this.description,
    name = this.name,
    price = this.price,
    status = this.status,
    stockQuantity = this.stockQuantity,
    categoryId = this.categoryId
)
