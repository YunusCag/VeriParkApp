package com.yunuscagliyan.veriparkapp.presentation.login.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.domain.use_case.StartHandShakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val startHandleShake: StartHandShakeUseCase,
) : ViewModel() {


    private val _handleShake=MutableLiveData<Resource<HandShakeModel?>>()
    val handShake:LiveData<Resource<HandShakeModel?>> =_handleShake

    fun startHandleShake(
        deviceId: String?,
        systemVersion: String?,
        deviceModel:String?,
        manifacturer: String?,
    ) = viewModelScope.launch(Dispatchers.IO) {
        startHandleShake.invoke(
            deviceId = deviceId,
            systemVersion = systemVersion,
            manifacturer = manifacturer,
            deviceModel = deviceModel,
        ).onEach {
            _handleShake.postValue(it)
        }.launchIn(viewModelScope)
    }
}