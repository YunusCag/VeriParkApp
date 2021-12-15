package com.yunuscagliyan.veriparkapp.data.remote.model.response.status

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @SerializedName("code")
    val code:Int?,
    @SerializedName("message")
    val message:String?,
)
