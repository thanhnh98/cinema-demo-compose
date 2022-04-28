package com.thanhnguyen.cinemaclonecompose.di

import com.google.gson.Gson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thanhnguyen.cinemaclonecompose.data.service.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAppService(okHttpClient: OkHttpClient): AppService {
        return Retrofit.Builder()
            .baseUrl(AppService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AppService::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor {
//                val token = "thanhnh98"
//                val modifiedRequest = it.request().newBuilder()
//                    .addHeader("Authorization", "Bearer $token")
//                    .build()
//                it.proceed(modifiedRequest)
//            }
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}