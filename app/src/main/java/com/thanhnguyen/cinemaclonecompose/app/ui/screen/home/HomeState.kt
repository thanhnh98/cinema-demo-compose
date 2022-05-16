package com.thanhnguyen.cinemaclonecompose.app.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.app.model.Banner
import com.thanhnguyen.cinemaclonecompose.app.model.Movie
import com.thanhnguyen.cinemaclonecompose.app.model.User


data class HomeState (
    val user: User? = null,
    val banners: List<Banner>? = null,
    val listCategories: List<String>? = null,
    val listMovies: List<Movie>? = null,
    val isLoading: Boolean = false
)