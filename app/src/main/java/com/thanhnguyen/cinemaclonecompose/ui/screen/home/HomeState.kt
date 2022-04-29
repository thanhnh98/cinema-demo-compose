package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.model.Banner
import com.thanhnguyen.cinemaclonecompose.model.Movie
import com.thanhnguyen.cinemaclonecompose.model.User


data class HomeState (
    val user: User? = null,
    val banners: List<Banner>? = null,
    val listCategories: List<String>? = null,
    val listMovies: List<Movie>? = null,
    val isLoading: Boolean = false
)