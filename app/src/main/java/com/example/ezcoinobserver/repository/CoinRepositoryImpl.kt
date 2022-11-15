package com.example.ezcoinobserver.repository

import com.example.ezcoinobserver.model.ResponseCoinModel
import com.example.ezcoinobserver.service.CoinAPI

class CoinRepositoryImpl(private val service: CoinAPI): CoinRepository {
    override suspend fun getCoinInfo(): ResponseCoinModel {
        return service.getCoinInfo()
    }
}

interface CoinRepository {
    suspend fun getCoinInfo():ResponseCoinModel
}