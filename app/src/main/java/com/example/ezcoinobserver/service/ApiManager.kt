package com.example.ezcoinobserver.service

import android.content.Context
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager(context: Context) :KoinComponent {
    private var mclient:OkHttpClient = OkHttpClient()
    private lateinit var service:CoinAPI
    private val serviceUrl:String = CoinAPI.ENDPOINT

    private val converter:Converter.Factory
    get() = GsonConverterFactory.create()

    init {
        initService(getRetrofit(mclient,converter))
    }

    private fun initService(retrofit: Retrofit){
        service = retrofit.create(CoinAPI::class.java)
    }

    protected fun getRetrofit(client:OkHttpClient?,factory: Converter.Factory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(factory)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getCoinAPI():CoinAPI {
        return service
    }
}