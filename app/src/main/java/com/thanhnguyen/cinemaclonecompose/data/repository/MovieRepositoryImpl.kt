package com.thanhnguyen.cinemaclonecompose.data.repository

import com.thanhnguyen.cinemaclonecompose.data.service.AppService
import com.thanhnguyen.cinemaclonecompose.domain.MovieRepository
import com.thanhnguyen.cinemaclonecompose.model.BaseResponse
import com.thanhnguyen.cinemaclonecompose.model.Movie
import com.thanhnguyen.cinemaclonecompose.model.Result
import com.thanhnguyen.cinemaclonecompose.model.getResult

class MovieRepositoryImpl(
    private val service: AppService
): MovieRepository {
    override suspend fun getPopularMovies():  Result<BaseResponse<List<Movie>>> {
        return getResult {
            service.getPopularMovies()
        }
    }
}