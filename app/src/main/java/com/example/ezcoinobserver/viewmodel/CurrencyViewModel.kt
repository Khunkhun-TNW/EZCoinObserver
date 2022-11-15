package com.example.ezcoinobserver.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezcoinobserver.model.ResponseCoinModel
import com.example.ezcoinobserver.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel(private val coinRepository: CoinRepository):ViewModel() {

    private val _coinPrice = MutableLiveData<ResponseCoinModel>()
    val coinPrice:LiveData<ResponseCoinModel> = _coinPrice

    fun getCoinPrice(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                coinRepository.getCoinInfo()
            }
            _coinPrice.value = response
        }
    }
}