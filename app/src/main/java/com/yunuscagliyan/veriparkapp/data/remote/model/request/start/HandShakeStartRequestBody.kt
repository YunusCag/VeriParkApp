package com.yunuscagliyan.veriparkapp.data.remote.model.request.start

import com.google.gson.annotations.SerializedName

data class HandShakeStartRequestBody(
    @SerializedName("deviceId")
    val deviceId:String?,
    @SerializedName("systemVersion")
    val systemVersion:String?,
    @SerializedName("platformName")
    val platformName:String?,
    @SerializedName("deviceModel")
    val deviceModel:String?,
    @SerializedName("manifacturer")
    val manifacturer:String?,
)
