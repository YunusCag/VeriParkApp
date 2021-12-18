package com.yunuscagliyan.veriparkapp.di

import android.content.Context
import com.yunuscagliyan.veriparkapp.data.remote.api.VeriParkService
import com.yunuscagliyan.veriparkapp.data.remote.interceptor.AuthInterceptor
import com.yunuscagliyan.veriparkapp.data.remote.url.VeriParkUrl
import com.yunuscagliyan.veriparkapp.data.repository.VeriParkRepositoryImp
import com.yunuscagliyan.veriparkapp.domain.preference.PreferenceManager
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceManager(
        @ApplicationContext context:Context
    )=PreferenceManager(context)

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        preference:PreferenceManager
    )=AuthInterceptor(preference)


    @Provides
    @Singleton
    fun provideVeriParkService(
        auth:AuthInterceptor
    ):VeriParkService{
        val interceptor= HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(auth)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(VeriParkUrl.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VeriParkService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeriParkRepository(api:VeriParkService):VeriParkRepository{
        return VeriParkRepositoryImp(api)
    }
}