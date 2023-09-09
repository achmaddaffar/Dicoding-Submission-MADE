package com.daffa.nasainsight.di

import com.daffa.core.domain.usecase.ApodInteractor
import com.daffa.core.domain.usecase.ApodUseCase
import com.daffa.nasainsight.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ApodUseCase> { ApodInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}