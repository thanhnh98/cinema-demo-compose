package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.ui.model.Banner
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.model.User

sealed class HomeEvent {
    class Loading(): HomeEvent()
    class Success(
        val user: User?,
        val banners: List<Banner>?,
        val listCategories: List<String>?,
        val favouriteMovies: List<Movie>?,
    ): HomeEvent()
}