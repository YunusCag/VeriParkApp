package com.yunuscagliyan.veriparkapp.data.remote.model.request.stock

sealed class StockPeriodRequest(val period:String){

    object All:StockPeriodRequest("all")
    object Increasing:StockPeriodRequest("increasing")
    object Decreasing:StockPeriodRequest("decreasing")
    object Volume30:StockPeriodRequest("volume30")
    object Volume50:StockPeriodRequest("volume50")
    object Volume100:StockPeriodRequest("volume100")
}
