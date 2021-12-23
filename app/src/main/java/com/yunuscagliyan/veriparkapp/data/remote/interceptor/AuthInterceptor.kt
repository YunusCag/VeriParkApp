package com.yunuscagliyan.veriparkapp.data.remote.interceptor

import com.yunuscagliyan.veriparkapp.domain.event.NavigateLoginEvent
import com.yunuscagliyan.veriparkapp.domain.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus

class AuthInterceptor(
    private val preferenceManager: PreferenceManager
):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req=chain.request().newBuilder()
        req.addHeader("X-VP-Authorization",preferenceManager.authorization?:"")
        val response=chain.proceed(req.build())

        if(response.code==401){
            EventBus.getDefault().post(NavigateLoginEvent)
        }
        return response
    }

}