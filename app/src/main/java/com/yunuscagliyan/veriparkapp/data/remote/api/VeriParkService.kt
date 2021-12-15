package com.yunuscagliyan.veriparkapp.data.remote.api

import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestBody
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.data.remote.url.VeriParkUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface VeriParkService {
    @POST(VeriParkUrl.HAND_SHAKE_START_PATH)
    suspend fun startHandShake(@Body body: HandShakeStartRequestBody?):HandShakeModel
}