package com.thanhnguyen.cinemaclonecompose.app.model

sealed class Result<out T>(val data: T? = null, val exception: Exception? = null): BaseModel() {
    class Success<T>(data: T?): Result<T>(data = data, null)
    class Error<T>(e: java.lang.Exception): Result<T>(data = null, e)
    class Loading<T>: Result<T>(data = null, null)
}

suspend fun <T> getResult(call: suspend () -> retrofit2.Response<T>): Result<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                return Result.Success(body)
        }
        return Result.Error(java.lang.Exception(response.message()))
    } catch (e: Exception) {
        e.printStackTrace()
        return Result.Error(e)
    }
}