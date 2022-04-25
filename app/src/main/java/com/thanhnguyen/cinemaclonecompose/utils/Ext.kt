package com.thanhnguyen.cinemaclonecompose.utils

import android.util.Log
import kotlinx.coroutines.delay

fun WTF(msg: String?){
    Log.e("WTF", "$msg")
}

suspend fun doOnDelay(
    delayTime: Long,
    action: () -> Unit
){
    delay(delayTime)
    action.invoke()
}

inline fun <reified T> Any.cast(): T {
    return this as T
}