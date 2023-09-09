package com.daffa.core.data.source.remote.response

import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getList()
}