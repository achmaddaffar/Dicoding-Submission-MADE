package com.daffa.core.domain.usecase

import com.daffa.core.data.source.ApodRepository
import com.daffa.core.data.source.Resource
import com.daffa.core.domain.model.Apod
import com.daffa.core.domain.repository.IApodRepository
import kotlinx.coroutines.flow.Flow

class ApodInteractor(private val apodRepository: IApodRepository): ApodUseCase {
    override fun getAllApod(): Flow<Resource<List<Apod>>> = apodRepository.getAllApod()

    override fun getFavoriteApod(): Flow<List<Apod>> = apodRepository.getFavoriteApod()

    override fun setFavoriteApod(apod: Apod, state: Boolean) = apodRepository.setFavoriteApod(apod, state)
}