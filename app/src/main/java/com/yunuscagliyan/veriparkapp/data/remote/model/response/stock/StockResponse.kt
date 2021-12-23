package com.yunuscagliyan.veriparkapp.data.remote.model.response.stock

import com.google.gson.annotations.SerializedName
import com.yunuscagliyan.veriparkapp.data.remote.model.response.status.StatusModel

data  class StockResponse(
    @SerializedName("stocks")
    val stocks:List<StockModel?>?,
    @SerializedName("status")
    val status:StatusModel?
)