package com.yunuscagliyan.veriparkapp.presentation.detail.view_model

import androidx.lifecycle.*
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.domain.use_case.GetStockDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetStockDetailUseCase,
    private val state: SavedStateHandle
):ViewModel() {

    private val  stockId=state.get<String?>("id")

    init {
        getStockDetail()
    }


    private val _stockDetail:MutableLiveData<Resource<StockDetailModel?>> = MutableLiveData()
    val stockDetail:LiveData<Resource<StockDetailModel?>> =_stockDetail


    private fun getStockDetail()=viewModelScope.launch(Dispatchers.IO){
        useCase.invoke(stockId).onEach {
            _stockDetail.postValue(it)
        }.launchIn(viewModelScope)
    }
}