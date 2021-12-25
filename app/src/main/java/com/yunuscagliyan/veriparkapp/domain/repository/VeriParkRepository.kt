package com.yunuscagliyan.veriparkapp.domain.repository

import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestBody
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockResponse

interface VeriParkRepository {
    suspend fun startHandShake(body: HandShakeStartRequestBody?):HandShakeModel
    suspend fun getStockList(body: StockRequestModel?):StockResponse
}