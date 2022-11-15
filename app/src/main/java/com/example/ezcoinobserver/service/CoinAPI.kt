package com.example.ezcoinobserver.service

import com.example.ezcoinobserver.model.ResponseCoinModel
import retrofit2.http.GET

interface CoinAPI {
    companion object{
        const val ENDPOINT = "https://api.coindesk.com"
    }

    @GET("/v1/bpi/currentprice.json")
    suspend fun getCoinInfo():ResponseCoinModel
}