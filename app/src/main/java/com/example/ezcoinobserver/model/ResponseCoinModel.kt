package com.example.ezcoinobserver.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseCoinModel(
    @SerializedName("bpi")
    val bpi: Bpi,
    @SerializedName("chartName")
    val chartName: String,
    @SerializedName("disclaimer")
    val disclaimer: String,
    @SerializedName("time")
    val time: Time
):Serializable

data class Bpi(
    @SerializedName("EUR")
    val eur: EUR,
    @SerializedName("GBP")
    val gbp: GBP,
    @SerializedName("USD")
    val usd: USD
):Serializable

data class EUR(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("rate_float")
    val rateFloat: Float,
    @SerializedName("symbol")
    val symbol: String
):Serializable

data class GBP(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("rate_float")
    val rateFloat: Float,
    @SerializedName("symbol")
    val symbol: String
):Serializable

data class USD(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("rate_float")
    val rateFloat: Float,
    @SerializedName("symbol")
    val symbol: String
):Serializable

data class Time(
    @SerializedName("updated")
    val updated: String,
    @SerializedName("updatedISO")
    val updatedISO: String,
    @SerializedName("updateduk")
    val updateduk: String
):Serializable

