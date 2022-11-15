package com.example.ezcoinobserver.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcoinobserver.adapter.BoardAdapter.Companion.FIRST_POSITION
import com.example.ezcoinobserver.adapter.BoardAdapter.Companion.SECOND_POSITION
import com.example.ezcoinobserver.adapter.BoardAdapter.Companion.THIRD_POSITION
import com.example.ezcoinobserver.databinding.ItemViewCurrencyTypeBinding
import com.example.ezcoinobserver.model.*

class BoardAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var response:ResponseCoinModel? = null

    companion object{
        const val FIRST_POSITION = 0
        const val SECOND_POSITION = 1
        const val THIRD_POSITION = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryItemViewHolder(ItemViewCurrencyTypeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HistoryItemViewHolder){
            response?.also { holder.setItem(it.bpi.usd,it.bpi.gbp,it.bpi.eur,it.time.updated,position) }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun setInitialData(initData:ResponseCoinModel){
        response = initData
    }
}

class HistoryItemViewHolder(val itemBinding: ItemViewCurrencyTypeBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun setItem(usd: USD, gbp: GBP, eur: EUR, time: String, position: Int) {
        when(position){
            FIRST_POSITION -> {
                itemBinding.tvCurrencyTitle.text = "BTC/${usd.code}"
                itemBinding.tvCurrencyCurrentRate.text = "${usd.rateFloat.toInt()} " + Html.fromHtml(usd.symbol)
            }
            SECOND_POSITION -> {
                itemBinding.tvCurrencyTitle.text = "BTC/${gbp.code}"
                itemBinding.tvCurrencyCurrentRate.text = "${gbp.rateFloat.toInt()} " + Html.fromHtml(gbp.symbol)
            }
            THIRD_POSITION -> {
                itemBinding.tvCurrencyTitle.text = "BTC/${eur.code}"
                itemBinding.tvCurrencyCurrentRate.text = "${eur.rateFloat.toInt()} " + Html.fromHtml(eur.symbol)
            }
        }
    }
}