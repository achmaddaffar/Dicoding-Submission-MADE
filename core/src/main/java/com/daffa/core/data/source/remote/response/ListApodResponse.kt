package com.daffa.core.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ListApodResponse(
	val apodResponse: List<ApodResponseItem>
) : Parcelable