package com.example.ezcoinobserver

import com.example.ezcoinobserver.repository.CoinRepository
import com.example.ezcoinobserver.repository.CoinRepositoryImpl
import com.example.ezcoinobserver.service.ApiManager
import com.example.ezcoinobserver.viewmodel.CurrencyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun boardModule(): Module = module {
    factory {ApiManager(androidContext()).getCoinAPI() }
    factory<CoinRepository>  { CoinRepositoryImpl(get()) }

    viewModel { CurrencyViewModel(get()) }
}