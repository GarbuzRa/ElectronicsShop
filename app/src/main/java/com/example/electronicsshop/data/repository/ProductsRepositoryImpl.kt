package com.example.electronicsshop.data.repository

import com.example.electronicsshop.data.remote.ShopApi
import com.example.electronicsshop.domain.model.ProductDomain
import com.example.electronicsshop.domain.model.toDomainModel
import com.example.electronicsshop.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductsRepositoryImpl(val api: ShopApi): ProductsRepository {
    override suspend fun getProducts(): Flow<Result<List<ProductDomain>>> = flow {
        try {
            val responce = api.getProducts()
            val result =  responce.map {it.toDomainModel()}
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun searchProduct(name: String): Flow<Result<List<ProductDomain>>> = flow {
        try {
            val searhResponce = api.getProducts()
            val searchResult = searhResponce.map { it.toDomainModel() }
            emit(Result.success(searchResult))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}