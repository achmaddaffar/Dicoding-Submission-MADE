package com.daffa.core.data.source.remote.network

import com.daffa.core.data.source.remote.response.ListApodResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("planetary/apod")
    suspend fun getList(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int,
    ): ListApodResponse
}