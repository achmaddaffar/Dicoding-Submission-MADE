package com.daffa.nasainsight.ui.detail

import androidx.lifecycle.ViewModel
import com.daffa.core.domain.model.Apod
import com.daffa.core.domain.usecase.ApodUseCase

class DetailApodViewModel(private val apodUseCase: ApodUseCase) : ViewModel() {

    fun setFavoriteApod(apod: Apod, state: Boolean) {
        apodUseCase.setFavoriteApod(apod, state)
    }
}