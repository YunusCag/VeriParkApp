package com.yunuscagliyan.veriparkapp.data.remote.api

import com.yunuscagliyan.veriparkapp.data.remote.model.request.detail.StockDetailRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockResponse
import com.yunuscagliyan.veriparkapp.data.remote.url.VeriParkUrl
import retrofit2.http.Body
import retrofit2.http.POST

interface VeriParkService {
    @POST(VeriParkUrl.HAND_SHAKE_START_PATH)
    suspend fun startHandShake(@Body body: HandShakeStartRequestModel?):HandShakeModel

    @POST(VeriParkUrl.GET_STOCK_LIST)
    suspend fun getStockList(@Body body: StockRequestModel?):StockResponse

    @POST(VeriParkUrl.GET_STOCK_DETAIL)
    suspend fun getStockDetail(@Body body:StockDetailRequestModel?):StockDetailModel?
}