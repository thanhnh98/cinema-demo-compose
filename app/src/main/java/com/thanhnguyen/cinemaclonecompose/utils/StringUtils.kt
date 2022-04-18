package com.thanhnguyen.cinemaclonecompose.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String =
    Json.encodeToString(this)

inline fun <reified T> String.fromJson() : T {
    return Json.decodeFromString(this)
}