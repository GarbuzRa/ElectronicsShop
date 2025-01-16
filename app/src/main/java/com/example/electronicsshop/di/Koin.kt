package com.example.electronicsshop.di

import com.example.electronicsshop.data.remote.ShopApi
import com.example.electronicsshop.data.repository.ProductsRepositoryImpl
import com.example.electronicsshop.domain.interactor.ProductsInteractor
import com.example.electronicsshop.domain.repository.ProductsRepository
import com.example.electronicsshop.presentation.viewmodels.ProductViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val appModule = module {
    single {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single <ShopApi> {
        Retrofit.Builder()
            .baseUrl("http://91.221.246.123:8083/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }

    single <ProductsRepository> { ProductsRepositoryImpl(get()) }
    factory { ProductsInteractor(get()) }
    viewModel { ProductViewModel(get()) }
}
