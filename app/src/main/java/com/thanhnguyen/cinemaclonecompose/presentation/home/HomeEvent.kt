package com.thanhnguyen.cinemaclonecompose.presentation.home

import com.thanhnguyen.cinemaclonecompose.model.Banner
import com.thanhnguyen.cinemaclonecompose.model.Movie
import com.thanhnguyen.cinemaclonecompose.model.User

sealed class HomeEvent {
    object Loading : HomeEvent()
    class Success(
        val user: User?,
        val banners: List<Banner>?,
        val listCategories: List<String>?,
        val favouriteMovies: List<Movie>?,
    ): HomeEvent()
}