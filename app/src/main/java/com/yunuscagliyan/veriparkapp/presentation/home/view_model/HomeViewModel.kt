package com.yunuscagliyan.veriparkapp.presentation.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockModel
import com.yunuscagliyan.veriparkapp.domain.use_case.GetStockListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetStockListUseCase
) : ViewModel() {

    private val _stocks: MutableLiveData<Resource<List<StockModel?>>> = MutableLiveData()
    val stocks: LiveData<Resource<List<StockModel?>>> = _stocks


    fun getStock(periodEncrypted:String?)=viewModelScope.launch(Dispatchers.IO){
        useCase(periodEncrypted).onEach {
            _stocks.postValue(it)
        }.launchIn(viewModelScope)
    }
}