package com.daffa.core.utils

import com.daffa.core.data.source.local.entity.ApodEntity
import com.daffa.core.data.source.remote.response.ApodResponseItem
import com.daffa.core.domain.model.Apod

object DataMapper {

    fun mapResponseToEntities(input: List<ApodResponseItem>): List<ApodEntity> {
        val apodList = ArrayList<ApodEntity>()
        input.map {
            val apod = ApodEntity(
                apodId = it.hdurl,
                date = it.date,
                copyright = it.copyright,
                mediaType = it.mediaType,
                hdurl = it.hdurl,
                serviceVersion = it.serviceVersion,
                explanation = it.explanation,
                title = it.title,
                url = it.url,
                isFavorite = false
            )
            apodList.add(apod)
        }
        return apodList
    }

    fun mapEntitiesToDomain(input: List<ApodEntity>): List<Apod> =
        input.map {
            Apod(
                apodId = it.apodId,
                copyright = it.copyright,
                date = it.date,
                explanation = it.explanation,
                hdurl = it.hdurl,
                mediaType = it.mediaType,
                serviceVersion = it.serviceVersion,
                title = it.title,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Apod) = ApodEntity(
        apodId = input.apodId,
        date = input.date,
        copyright = input.copyright,
        mediaType = input.mediaType,
        hdurl = input.hdurl,
        serviceVersion = input.serviceVersion,
        explanation = input.explanation,
        title = input.title,
        url = input.url,
        isFavorite = input.isFavorite
    )
}