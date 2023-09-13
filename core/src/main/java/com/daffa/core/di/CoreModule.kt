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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("daffa".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            ApodDatabase::class.java,
            "Apod.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE

    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(Constants.HOST_NAME, "sha256/glI+B+hi4788ubQBCsdtgg0ljZEFx1UvBColxDnsec8=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(loggingInterceptor))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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