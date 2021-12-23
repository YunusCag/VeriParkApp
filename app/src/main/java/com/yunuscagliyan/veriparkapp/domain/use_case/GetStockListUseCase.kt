package com.yunuscagliyan.veriparkapp.domain.use_case

import com.yunuscagliyan.veriparkapp.common.Resource
import com.yunuscagliyan.veriparkapp.data.remote.model.response.stock.StockModel
import com.yunuscagliyan.veriparkapp.domain.repository.VeriParkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val repository: VeriParkRepository,
) {

    operator fun invoke(period:String?): Flow<Resource<List<StockModel?>>> = flow {
        emit(Resource.Loading())
        try{
            val response=repository.getStockList(period)
            if(response.status?.isSuccess==true){
                val stockList=response.stocks
                stockList?.let {
                    emit(Resource.Success(stockList))
                }
            }else{
                emit(Resource.Error(response.status?.errorModel?.message))
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<StockModel?>>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error<List<StockModel?>>(e.message))

        }
    }
}