package com.thanhnguyen.cinemaclonecompose.app.domain

import com.thanhnguyen.cinemaclonecompose.app.model.BaseResponse
import com.thanhnguyen.cinemaclonecompose.app.model.Movie
import com.thanhnguyen.cinemaclonecompose.app.model.Result

interface MovieRepository {
    suspend fun getPopularMovies(): Result<BaseResponse<List<Movie>>>
}