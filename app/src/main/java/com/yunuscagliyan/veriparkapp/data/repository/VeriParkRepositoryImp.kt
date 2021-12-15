package com.yunuscagliyan.veriparkapp.data.repository

import com.yunuscagliyan.veriparkapp.data.remote.api.VeriParkService
import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestBody
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository

class VeriParkRepositoryImp(
    private val api:VeriParkService,
): VeriParkRepository {
    override suspend fun startHandShake(body: HandShakeStartRequestBody?): HandShakeModel {
        return api.startHandShake(body)
    }
}