package com.thanhnguyen.cinemaclonecompose.data.service

import com.thanhnguyen.cinemaclonecompose.model.BaseResponse
import com.thanhnguyen.cinemaclonecompose.model.Movie
import retrofit2.Response
import retrofit2.http.GET

interface AppService {

    @GET("main/movies")
    suspend fun getPopularMovies(): Response<BaseResponse<List<Movie>>>

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/thanhnh98/cinema-clone-api/"
    }
}