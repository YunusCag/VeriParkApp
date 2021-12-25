package com.yunuscagliyan.veriparkapp.data.remote.model.response.detail

import com.google.gson.annotations.SerializedName
import com.yunuscagliyan.veriparkapp.data.remote.model.response.graphic.StockGraphicDetailModel

data class StockDetailModel(
    @SerializedName("isDown")
    val isDown: Boolean?,
    @SerializedName("isUp")
    val isUp: Boolean?,
    @SerializedName("bid")
    val bid: Double?,
    @SerializedName("channge")
    val change: Double?,
    @SerializedName("count")
    val count:Int?,
    @SerializedName("difference")
    val difference: Double?,
    @SerializedName("offer")
    val offer: Double?,
    @SerializedName("highest")
    val highest: Double?,
    @SerializedName("lowest")
    val lowest: Double?,
    @SerializedName("maximum")
    val maximum: Double?,
    @SerializedName("minimum")
    val minimum: Double?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("volume")
    val volume: Double?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("graphicData")
    val graphicData: List<StockGraphicDetailModel?>?,
)