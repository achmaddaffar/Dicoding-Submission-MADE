package com.daffa.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodResponseItem(
    val date: String? = null,
    val copyright: String? = null,
    val mediaType: String? = null,
    val hdurl: String? = null,
    val serviceVersion: String? = null,
    val explanation: String? = null,
    val title: String? = null,
    val url: String? = null
) : Parcelable