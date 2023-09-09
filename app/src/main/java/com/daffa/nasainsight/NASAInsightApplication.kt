package com.daffa.nasainsight

import android.app.Application
import com.daffa.core.di.databaseModule
import com.daffa.core.di.networkModule
import com.daffa.core.di.repositoryModule
import com.daffa.nasainsight.di.useCaseModule
import com.daffa.nasainsight.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NASAInsightApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val logger = if (BuildConfig.DEBUG) Level.INFO else Level.NONE
            androidLogger(logger)
            androidContext(this@NASAInsightApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}