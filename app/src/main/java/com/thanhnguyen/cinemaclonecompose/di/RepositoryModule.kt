package com.thanhnguyen.cinemaclonecompose.di

import com.thanhnguyen.cinemaclonecompose.data.repository.MovieRepositoryImpl
import com.thanhnguyen.cinemaclonecompose.data.service.AppService
import com.thanhnguyen.cinemaclonecompose.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(service: AppService): MovieRepository{
        return MovieRepositoryImpl(service)
    }
}