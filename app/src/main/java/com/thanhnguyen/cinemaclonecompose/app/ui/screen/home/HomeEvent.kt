package com.thanhnguyen.cinemaclonecompose.app.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.app.model.Banner
import com.thanhnguyen.cinemaclonecompose.app.model.Movie
import com.thanhnguyen.cinemaclonecompose.app.model.User

sealed class HomeEvent {
    class Loading(
        val isLoading: Boolean
    ) : HomeEvent()
    class ProfileUserLoaded(
        val user: User?,
    ): HomeEvent()
    class BannersLoaded(
        val banners: List<Banner>?,
    ): HomeEvent()
    class ListCategoriesLoaded(
        val listCategories: List<String>?,
    ): HomeEvent()
    class FavouriteMovieLoaded(
        val favouriteMovies: List<Movie>?,
    ): HomeEvent()
}