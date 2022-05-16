package com.thanhnguyen.cinemaclonecompose.app.data.local.preferences

import com.thanhnguyen.cinemaclonecompose.base.data.Prefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.lastOrNull

class PrefsClient(private val prefs: Prefs): IPrefsClient {
    override suspend fun isShownOnboard(): Flow<Boolean?> {
        val result = prefs.getByKey(PrefsKeys.isShownOnboard)
        return result
    }

    override suspend fun setShownOnboard(isShown: Boolean){
        prefs.setKey(PrefsKeys.isShownOnboard, value = isShown)
    }
}