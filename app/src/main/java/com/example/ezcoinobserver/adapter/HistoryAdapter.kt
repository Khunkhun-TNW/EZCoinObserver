package com.example.ezcoinobserver.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezcoinobserver.databinding.ItemViewHistoryPriceBinding
import com.example.ezcoinobserver.model.ResponseCoinModel
import kotlinx.android.synthetic.main.item_view_currency_type.view.tvCurrencyCurrentRate
import kotlinx.android.synthetic.main.item_view_currency_type.view.tvCurrencyTitle
import kotlinx.android.synthetic.main.item_view_history_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var response: ArrayList<ResponseCoinModel> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinItemViewHolder(
            ItemViewHistoryPriceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinItemViewHolder) {
            response.also { holder.setItem(response[position]) }
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }

    fun setInitialData(initData: ArrayList<ResponseCoinModel>) {
        response = initData
    }


    class CoinItemViewHolder(val itemBinding: ItemViewHistoryPriceBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun setItem(responseCoinModel: ResponseCoinModel) {
            val response = responseCoinModel.bpi
            itemBinding.flUSD.tvCurrencyTitle.text = "BTC/${response.usd.code}"
            itemBinding.flUSD.tvCurrencyCurrentRate.text =
                "${response.usd.rateFloat.toInt()} " + Html.fromHtml(response.usd.symbol)
            itemBinding.flUSD.tvTimeStamp.text =
                validateTimeFormat(responseCoinModel.time.updatedISO)

            itemBinding.flGBP.tvCurrencyTitle.text = "BTC/${response.gbp.code}"
            itemBinding.flGBP.tvCurrencyCurrentRate.text =
                "${response.gbp.rateFloat.toInt()} " + Html.fromHtml(response.gbp.symbol)
            itemBinding.flGBP.tvTimeStamp.text =
                validateTimeFormat(responseCoinModel.time.updatedISO)

            itemBinding.flEUR.tvCurrencyTitle.text = "BTC/${response.eur.code}"
            itemBinding.flEUR.tvCurrencyCurrentRate.text =
                "${response.eur.rateFloat.toInt()} " + Html.fromHtml(response.eur.symbol)
            itemBinding.flEUR.tvTimeStamp.text =
                validateTimeFormat(responseCoinModel.time.updatedISO)
        }

        private fun validateTimeFormat(inputTime: String): String {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss",
                Locale.ENGLISH
            )
            sdf.timeZone = TimeZone.getTimeZone("UTC")

            val parsedDate = sdf.parse(inputTime)
            val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            output.timeZone = TimeZone.getDefault()

            return output.format(parsedDate)
        }
    }
}