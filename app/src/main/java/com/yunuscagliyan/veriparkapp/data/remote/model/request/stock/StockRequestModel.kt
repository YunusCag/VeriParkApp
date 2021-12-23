package com.yunuscagliyan.veriparkapp.data.remote.model.request.stock

import com.google.gson.annotations.SerializedName

data class StockRequestModel(
    @SerializedName("period")
    val period:String?,
)