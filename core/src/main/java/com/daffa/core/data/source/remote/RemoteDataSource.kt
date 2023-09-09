package com.daffa.core.data.source.remote

import android.util.Log
import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.data.source.remote.response.ApodResponseItem
import com.daffa.core.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllApod(): Flow<ApiResponse<List<ApodResponseItem>>> = flow {
        try {
            val response = apiService.getList(
                apiKey = Constants.API_KEY,
                count = Constants.APOD_COUNT
            )
            val dataArray = response.apodResponse
            if (dataArray.isNotEmpty()) {
                emit(ApiResponse.Success(response.apodResponse))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e("RemoteDataSource", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}