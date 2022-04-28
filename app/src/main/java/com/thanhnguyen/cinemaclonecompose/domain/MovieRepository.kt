package com.thanhnguyen.cinemaclonecompose.domain

import com.thanhnguyen.cinemaclonecompose.model.BaseResponse
import com.thanhnguyen.cinemaclonecompose.model.Movie
import com.thanhnguyen.cinemaclonecompose.model.Result

interface MovieRepository {
    suspend fun getPopularMovies(): Result<BaseResponse<List<Movie>>>
}