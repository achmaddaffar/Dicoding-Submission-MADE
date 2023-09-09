package com.daffa.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod")
data class ApodEntity(
    @PrimaryKey
    @ColumnInfo(name = "apodId")
    var apodId: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "copyright")
    var copyright: String,

    @ColumnInfo(name = "mediaType")
    var mediaType: String,

    @ColumnInfo(name = "hdurl")
    var hdurl: String,

    @ColumnInfo(name = "serviceVersion")
    var serviceVersion: String,

    @ColumnInfo(name = "explanation")
    var explanation: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)
