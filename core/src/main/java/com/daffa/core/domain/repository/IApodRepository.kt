package com.daffa.core.domain.repository

import com.daffa.core.data.source.Resource
import com.daffa.core.domain.model.Apod
import kotlinx.coroutines.flow.Flow

interface IApodRepository {

    fun getAllApod(): Flow<Resource<List<Apod>>>

    fun getFavoriteApod(): Flow<List<Apod>>

    fun setFavoriteApod(apod: Apod, state: Boolean)
}