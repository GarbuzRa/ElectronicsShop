package com.example.electronicsshop.di

import com.example.electronicsshop.data.remote.ShopApi
import com.example.electronicsshop.data.repository.ProductsRepositoryImpl
import com.example.electronicsshop.domain.interactor.ProductsInteractor
import com.example.electronicsshop.domain.repository.ProductsRepository
import com.example.electronicsshop.presentation.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    val appModule = module {
        single <ShopApi> {
            Retrofit.Builder()
                .baseUrl("http://91.221.246.123:8083/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopApi::class.java)
        }
        single <ProductsRepository> {ProductsRepositoryImpl(get())}

        factory {ProductsInteractor(get())}

        viewModel {
            ProductViewModel(get())
        }
    }
