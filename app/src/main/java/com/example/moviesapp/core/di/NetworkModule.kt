package com.example.moviesapp.core.di

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.core.utils.ApiConstants
import com.example.moviesapp.data.sources.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideApiService(@Named("movieApi") retrofit: Retrofit): ApiService{
        return   retrofit.create(ApiService::class.java)
    }

    @Provides
    @Named("movieApi")
    @Reusable
    @JvmStatic
    fun provideMovieApi(okHttpClient: OkHttpClient.Builder): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttp(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            okHttpClient.addInterceptor(logging)
        }
        return okHttpClient
    }
}