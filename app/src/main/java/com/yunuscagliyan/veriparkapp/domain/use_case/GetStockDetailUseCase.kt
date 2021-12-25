package com.yunuscagliyan.veriparkapp.domain.use_case

import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.request.detail.StockDetailRequestModel
import com.yunuscagliyan.veriparkapp.data.remote.model.response.detail.StockDetailModel
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetStockDetailUseCase @Inject constructor(
    private val repository: VeriParkRepository
) {
    operator fun invoke(id: String?): Flow<Resource<StockDetailModel?>> = flow {
        emit(Resource.Loading())
        try {
            val response = repository.getStockDetail(
                StockDetailRequestModel(id = id)
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(e.message))
        }
    }
}