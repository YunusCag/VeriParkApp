package com.yunuscagliyan.veriparkapp.data.repository

import com.yunuscagliyan.veriparkapp.data.remote.api.VeriParkService
import com.yunuscagliyan.veriparkapp.data.remote.model.request.detail.StockDetailRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockResponse
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository

class VeriParkRepositoryImp(
    private val api: VeriParkService,
) : VeriParkRepository {
    override suspend fun startHandShake(body: HandShakeStartRequestModel?): HandShakeModel {
        return api.startHandShake(body)
    }

    override suspend fun getStockList(body: StockRequestModel?): StockResponse {
        return api.getStockList(body)
    }

    override suspend fun getStockDetail(body: StockDetailRequestModel?): StockDetailModel? {
        return api.getStockDetail(body)
    }
}