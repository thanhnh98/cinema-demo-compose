package com.thanhnguyen.cinemaclonecompose.utils

import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.fromJson() : T {
    return Json.decodeFromString(this)
}