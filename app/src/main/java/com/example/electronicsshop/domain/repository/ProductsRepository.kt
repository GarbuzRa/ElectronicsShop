package com.example.electronicsshop.domain.repository

import com.example.electronicsshop.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow


interface ProductsRepository {
    suspend fun getProducts(): Flow<Result<List<ProductDomain>>>
    //suspend fun sortedProduct(price: Int)
    suspend fun searchProduct(name: String): Flow<Result<List<ProductDomain>>>
}