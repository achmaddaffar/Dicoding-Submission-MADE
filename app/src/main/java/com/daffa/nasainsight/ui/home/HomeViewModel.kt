package com.daffa.nasainsight.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.daffa.core.domain.usecase.ApodUseCase

class HomeViewModel(apodUseCase: ApodUseCase) : ViewModel() {

    val apod = apodUseCase.getAllApod().asLiveData()
}