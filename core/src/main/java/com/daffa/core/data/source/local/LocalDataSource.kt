package com.daffa.core.data.source.local

import com.daffa.core.data.source.local.entity.ApodEntity
import com.daffa.core.data.source.local.room.ApodDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val apodDao: ApodDao) {

    fun getAllApod(): Flow<List<ApodEntity>> = apodDao.getAllApod()

    fun getFavoriteApod(): Flow<List<ApodEntity>> = apodDao.getFavoriteApod()

    suspend fun insertApod(apodList: List<ApodEntity>) = apodDao.insertApod(apodList)

    fun setFavoriteApod(apod: ApodEntity, newState: Boolean) {
        apod.isFavorite = newState
        apodDao.updateFavoriteApod(apod)
    }
}