package com.thanhnguyen.cinemaclonecompose.app.data.repository

import com.thanhnguyen.cinemaclonecompose.app.data.service.AppService
import com.thanhnguyen.cinemaclonecompose.app.domain.MovieRepository
import com.thanhnguyen.cinemaclonecompose.app.model.BaseResponse
import com.thanhnguyen.cinemaclonecompose.app.model.Movie
import com.thanhnguyen.cinemaclonecompose.app.model.Result
import com.thanhnguyen.cinemaclonecompose.app.model.getResult

class MovieRepositoryImpl(
    private val service: AppService
): MovieRepository {
    override suspend fun getPopularMovies():  Result<BaseResponse<List<Movie>>> {
        return getResult {
            service.getPopularMovies()
        }
    }
}