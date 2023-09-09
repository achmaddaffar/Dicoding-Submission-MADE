package com.daffa.core.domain.usecase

import com.daffa.core.data.source.Resource
import com.daffa.core.domain.model.Apod
import kotlinx.coroutines.flow.Flow

interface ApodUseCase {
    fun getAllApod(): Flow<Resource<List<Apod>>>
    fun getFavoriteApod(): Flow<List<Apod>>
    fun setFavoriteApod(apod: Apod, state: Boolean)
}