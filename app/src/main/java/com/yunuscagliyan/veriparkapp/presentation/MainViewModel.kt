package com.yunuscagliyan.veriparkapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yunuscagliyan.veriparkapp.common.extension.toDecrypted
import com.yunuscagliyan.veriparkapp.common.extension.toEncrypted
import com.yunuscagliyan.veriparkapp.data.remote.model.request.stock.StockPeriodRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    var aesKey: String = ""
    var aesIV: String = ""

    private val _period:MutableLiveData<StockPeriodRequest> = MutableLiveData(StockPeriodRequest.All)
    val period:LiveData<StockPeriodRequest> =_period


    fun changePeriod(stockPeriod:StockPeriodRequest){
        _period.postValue(stockPeriod)
    }

    fun  getDecryptedText(text:String):String{
        return text.toDecrypted(
            aesKey,
            aesIV,
        )
    }

    fun getEncryptedText(text:String):String{
        return text.toEncrypted(
            aesKey,
            aesIV,
        )
    }
}