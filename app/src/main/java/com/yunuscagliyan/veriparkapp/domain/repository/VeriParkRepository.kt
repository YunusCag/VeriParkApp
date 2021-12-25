package com.yunuscagliyan.veriparkapp.domain.repository

import com.yunuscagliyan.veriparkapp.data.remote.model.request.detail.StockDetailRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockResponse

interface VeriParkRepository {
    suspend fun startHandShake(body: HandShakeStartRequestModel?): HandShakeModel
    suspend fun getStockList(body: StockRequestModel?): StockResponse
    suspend fun getStockDetail(body: StockDetailRequestModel?): StockDetailModel?
}