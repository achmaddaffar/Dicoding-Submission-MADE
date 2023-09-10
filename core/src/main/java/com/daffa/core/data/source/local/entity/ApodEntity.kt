package com.daffa.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod")
data class ApodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "apodId")
    var apodId: Int? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "copyright")
    var copyright: String? = null,

    @ColumnInfo(name = "mediaType")
    var mediaType: String? = null,

    @ColumnInfo(name = "hdurl")
    var hdurl: String? = null,

    @ColumnInfo(name = "serviceVersion")
    var serviceVersion: String? = null,

    @ColumnInfo(name = "explanation")
    var explanation: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)
