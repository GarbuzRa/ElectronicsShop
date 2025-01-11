package com.example.electronicsshop.domain.interactor

import com.example.electronicsshop.domain.model.ProductDomain
import com.example.electronicsshop.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsInteractor(val productsRepository: ProductsRepository) {

    suspend fun getProducts(): Flow<Result<List<ProductDomain>>> {
       return productsRepository.getProducts()
    }

    suspend fun searchProduct(name: String): Flow<Result<List<ProductDomain>>> {
        return productsRepository.searchProduct(name)
    }
}