package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.ui.model.Banner
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.model.User


sealed class HomeState {
    object Initial : HomeState()
    object Loading : HomeState()
    data class Data(
        val user: User? = null,
        val banners: List<Banner>? = null,
        val listCategories: List<String>? = null,
        val listMovies: List<Movie>? = null
    ) : HomeState()
    object Error : HomeState()
}