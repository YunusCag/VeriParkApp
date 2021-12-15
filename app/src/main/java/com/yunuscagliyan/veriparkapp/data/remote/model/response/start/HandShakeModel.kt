package com.yunuscagliyan.veriparkapp.data.remote.model.response.start

import com.google.gson.annotations.SerializedName
import com.yunuscagliyan.veriparkapp.data.remote.model.response.status.StatusModel

data class HandShakeModel(
    @SerializedName("aesKey")
    val aesKey: String?,
    @SerializedName("aesIV")
    val aesIV: String?,
    @SerializedName("authorization")
    val authorization: String?,
    @SerializedName("lifeTime")
    val lifeTime: String?,
    @SerializedName("status")
    val status:StatusModel?,
)
