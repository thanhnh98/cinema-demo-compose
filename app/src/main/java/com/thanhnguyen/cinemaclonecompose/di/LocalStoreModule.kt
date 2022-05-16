package com.thanhnguyen.cinemaclonecompose.di

import android.content.Context
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.PrefsClient
import com.thanhnguyen.cinemaclonecompose.base.data.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalStoreModule {
    @Provides
    @Singleton
    fun providePresClient(prefs: Prefs): IPrefsClient{
        return PrefsClient(prefs)
    }

    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs{
        return Prefs(context = context)
    }

}