package com.daffa.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.core.domain.usecase.ApodUseCase

class FavoriteViewModel(apodUseCase: ApodUseCase) : ViewModel() {

    val apodFavorite = apodUseCase.getFavoriteApod().asLiveData()
}