package com.yunuscagliyan.veriparkapp.data.remote.model.response.graphic

import com.google.gson.annotations.SerializedName

data class StockGraphicDetailModel(
    @SerializedName("day")
    val day:Int?,
    @SerializedName("value")
    val value:Double?
)
