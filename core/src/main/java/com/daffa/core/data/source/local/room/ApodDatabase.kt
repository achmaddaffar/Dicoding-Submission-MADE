package com.daffa.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daffa.core.data.source.local.entity.ApodEntity

@Database(
    entities = [ApodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}