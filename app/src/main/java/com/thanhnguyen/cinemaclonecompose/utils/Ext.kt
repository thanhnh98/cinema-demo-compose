package com.thanhnguyen.cinemaclonecompose.utils

import android.util.Log
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

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
