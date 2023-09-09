package com.daffa.core.data.source

import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.data.source.remote.network.ApiResponse
import com.daffa.core.data.source.remote.response.ApodResponseItem
import com.daffa.core.domain.model.Apod
import com.daffa.core.domain.repository.IApodRepository
import com.daffa.core.utils.AppExecutors
import com.daffa.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApodRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IApodRepository {
    override fun getAllApod(): Flow<Resource<List<Apod>>> =
        object : NetworkBoundResource<List<Apod>, List<ApodResponseItem>>() {
            override fun loadFromDB(): Flow<List<Apod>> = localDataSource.getAllApod().map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ApodResponseItem>>> = remoteDataSource.getAllApod()

            override suspend fun saveCallResult(data: List<ApodResponseItem>) {
                val apodList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertApod(apodList)
            }

            override fun shouldFetch(data: List<Apod>?): Boolean = data.isNullOrEmpty()

        }.asFlow()

    override fun getFavoriteApod(): Flow<List<Apod>> = localDataSource.getFavoriteApod().map {
        DataMapper.mapEntitiesToDomain(it)
    }

    override fun setFavoriteApod(apod: Apod, state: Boolean) {
        val apodEntity = DataMapper.mapDomainToEntity(apod)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteApod(apodEntity, state)
        }
    }
}