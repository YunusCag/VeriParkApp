package com.yunuscagliyan.veriparkapp.domain.use_case

import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.request.start.HandShakeStartRequestBody
import com.yunuscagliyan.veriparkapp.data.remote.model.response.start.HandShakeModel
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class StartHandShakeUseCase @Inject constructor(
    private val repository: VeriParkRepository
) {
    operator fun invoke(
        deviceId: String?,
        systemVersion: String?,
        platformName: String="Android",
        deviceModel:String?,
        manifacturer: String?,
    ): Flow<Resource<HandShakeModel?>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.startHandShake(
                body = HandShakeStartRequestBody(
                    deviceId = deviceId,
                    systemVersion = systemVersion,
                    platformName = platformName,
                    deviceModel = deviceModel,
                    manifacturer = manifacturer,
                ),
            )
            if (response.status?.isSuccess == true) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(response.status?.errorModel?.message))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<HandShakeModel?>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<HandShakeModel?>(e.message))

        }
    }
}