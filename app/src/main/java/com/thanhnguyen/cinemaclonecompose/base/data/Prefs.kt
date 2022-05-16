package com.thanhnguyen.cinemaclonecompose.base.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("PreferenceDataStore")

class Prefs(val context: Context) {

    fun <T> getByKey(key: Preferences.Key<T>, defaultValue: T? = null) = context.dataStore.data.map { preferences ->
        return@map preferences[key] ?: defaultValue
    }

    private inline fun <reified T> getObjectByKey(key: Preferences.Key<String>) = context.dataStore.data.map { preferences ->
        var result: T? = null
        try{
            val dataStr = preferences[key]
            result = GsonBuilder().create().fromJson(dataStr, T::class.java)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        result
    }

    suspend fun <T> setKey(key: Preferences.Key<T>, value: T): T?{
        return  context.dataStore.edit { prefs ->
            prefs[key] = value
        }[key]
    }

    private suspend inline fun <reified T> setObjectKey(key: Preferences.Key<String>, value: T){
        context.dataStore.edit { prefs ->
            prefs[key] = Gson().toJson(value)
        }
    }
}


