package com.yunuscagliyan.veriparkapp.presentation.login.view_model

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.domain.preference.PreferenceManager
import com.yunuscagliyan.veriparkapp.domain.use_case.StartHandShakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val startHandleShake: StartHandShakeUseCase,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _handleShake=MutableLiveData<Resource<HandShakeModel?>>()
    val handShake:LiveData<Resource<HandShakeModel?>> =_handleShake

    private fun startHandleShake(
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

    fun onLoginClick(){
        val deviceId:String= UUID.randomUUID().toString();
        val versionCode="${Build.VERSION.SDK_INT}"
        val deviceModel= Build.MODEL
        val manifacturer= Build.MANUFACTURER

        startHandleShake(
            deviceId = deviceId,
            systemVersion=versionCode,
            deviceModel=deviceModel,
            manifacturer=manifacturer,
        )
    }

    fun saveAuthToken(token:String){
        preferenceManager.authorization=token
    }
}