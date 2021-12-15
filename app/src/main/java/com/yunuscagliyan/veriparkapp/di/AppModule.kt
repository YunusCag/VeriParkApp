package com.yunuscagliyan.veriparkapp.di

import com.yunuscagliyan.veriparkapp.data.remote.api.VeriParkService
import com.yunuscagliyan.veriparkapp.data.remote.url.VeriParkUrl
import com.yunuscagliyan.veriparkapp.data.repository.VeriParkRepositoryImp
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideVeriParkService():VeriParkService{
        val interceptor= HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

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