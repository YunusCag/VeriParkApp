package com.yunuscagliyan.veriparkapp.data.remote.model.response.stock

import com.google.gson.annotations.SerializedName

data class StockModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("isDown")
    val isDown: Boolean?,
    @SerializedName("isUp")
    val isUp: Boolean?,
    @SerializedName("bid")
    val bid: Double?,
    @SerializedName("difference")
    val difference: Double?,
    @SerializedName("offer")
    val offer: Double?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("volume")
    val volume: Double?,
    @SerializedName("symbol")
    var symbol: String?,

    )
