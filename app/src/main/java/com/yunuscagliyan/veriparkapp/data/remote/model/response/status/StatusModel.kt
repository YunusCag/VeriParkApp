package com.yunuscagliyan.veriparkapp.data.remote.model.response.status

import com.google.gson.annotations.SerializedName

data class StatusModel(
    @SerializedName("isSuccess")
    val isSuccess:Boolean?,
    @SerializedName("error")
    val errorModel: ErrorModel?,
)
