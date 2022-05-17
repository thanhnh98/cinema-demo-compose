package com.thanhnguyen.cinemaclonecompose.app.data.local.preferences

import kotlinx.coroutines.flow.Flow

interface IPrefsClient {
    suspend fun isShownOnboard(): Flow<Boolean?>
    suspend fun setShownOnboard(isShown: Boolean)
    suspend fun isLoggedIn(): Flow<Boolean?>
    suspend fun setLoggedIn(isLoggedIn: Boolean)
}