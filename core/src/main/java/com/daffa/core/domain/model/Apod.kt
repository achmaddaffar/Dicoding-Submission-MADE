package com.daffa.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Apod(
    val apodId: String,
    val copyright: String? = null,
    val date: String? = null,
    val explanation: String? = null,
    val hdurl: String,
    val mediaType: String? = null,
    val serviceVersion: String? = null,
    val title: String? = null,
    val url: String,
    val isFavorite: Boolean
) : Parcelable
