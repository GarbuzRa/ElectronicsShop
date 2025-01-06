package com.example.electronicsshop.data.remote

import com.example.electronicsshop.data.model.ProductResponce
import retrofit2.http.GET

interface ShopApi {
    @GET("products")
    suspend fun getProducts(): ProductResponce
}