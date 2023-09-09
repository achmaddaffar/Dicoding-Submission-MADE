package com.daffa.core.di

import androidx.room.Room
import com.daffa.core.data.source.ApodRepository
import com.daffa.core.data.source.local.LocalDataSource
import com.daffa.core.data.source.local.room.ApodDatabase
import com.daffa.core.data.source.remote.RemoteDataSource
import com.daffa.core.data.source.remote.network.ApiService
import com.daffa.core.domain.repository.IApodRepository
import com.daffa.core.utils.AppExecutors
import com.daffa.core.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ApodDatabase>().apodDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ApodDatabase::class.java,
            "Apod.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IApodRepository> {
        ApodRepository(
            get(),
            get(),
            get()
        )
    }
}