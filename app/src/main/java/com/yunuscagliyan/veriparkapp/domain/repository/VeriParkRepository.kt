package com.yunuscagliyan.veriparkapp.domain.repository

import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestBody
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel

interface VeriParkRepository {
    suspend fun startHandShake(body: HandShakeStartRequestBody?):HandShakeModel
}