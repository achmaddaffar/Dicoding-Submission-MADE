package com.daffa.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.daffa.core.data.source.local.entity.ApodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ApodDao {

    @Query("SELECT * FROM apod")
    fun getAllApod(): Flow<List<ApodEntity>>

    @Query("SELECT * FROM apod WHERE isFavorite = 1")
    fun getFavoriteApod(): Flow<List<ApodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apodList: List<ApodEntity>)

    @Update
    fun updateFavoriteApod(apod: ApodEntity)
}