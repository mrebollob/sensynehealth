package com.dhis.store.di

import android.content.Context
import com.dhis.store.BuildConfig
import com.dhis.store.core.SensyneRepository
import com.dhis.store.data.SensyneRepositoryImp
import com.dhis.store.data.network.ApiService
import com.dhis.store.presentation.features.details.DetailsViewModelFactory
import com.dhis.store.presentation.features.list.ListViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

const val TIMEOUT_IN_SECONDS = 60L

object InjectorUtils {

    fun provideAppListViewModelFactory(context: Context): ListViewModelFactory =
        ListViewModelFactory(provideStoreRepository(context))

    fun provideDetailsViewModelFactory(context: Context, appId: Int): DetailsViewModelFactory =
        DetailsViewModelFactory(provideStoreRepository(context), appId)


    private fun provideStoreRepository(context: Context): SensyneRepository =
        SensyneRepositoryImp.getInstance(context)


    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClient(provideHttpLoggingInterceptor()))
            .build().create(ApiService::class.java)
    }

    private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
