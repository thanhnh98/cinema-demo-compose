package com.thanhnguyen.cinemaclonecompose.di

import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.routing.RoutingViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    fun provideRoutingViewModel(prefs: IPrefsClient): RoutingViewModel {
        return RoutingViewModel(prefs)
    }
}