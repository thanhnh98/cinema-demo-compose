package com.thanhnguyen.cinemaclonecompose.app.data.local.preferences

import com.thanhnguyen.cinemaclonecompose.base.data.Prefs
import kotlinx.coroutines.flow.Flow
class PrefsClient(private val prefs: Prefs) : IPrefsClient {
    override suspend fun isShownOnboard(): Flow<Boolean?> {
        return prefs.getByKey(PrefsKeys.isShownOnboard)
    }

    override suspend fun setShownOnboard(isShown: Boolean) {
        prefs.setKey(PrefsKeys.isShownOnboard, value = isShown)
    }

    override suspend fun isLoggedIn(): Flow<Boolean?> {
        return prefs.getByKey(PrefsKeys.isLoggedIn)
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.setKey(PrefsKeys.isLoggedIn, value = isLoggedIn)
    }
}