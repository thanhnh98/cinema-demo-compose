package com.thanhnguyen.cinemaclonecompose.ui.screen.search_detail

import com.thanhnguyen.cinemaclonecompose.model.Movie

sealed class SearchDetailEvent {
    class Loading(
        val isLoading: Boolean = false,
        val isLoadingMore: Boolean = false
    ): SearchDetailEvent()

    class LoadingMoreCompleted(
        val keyword: String,
        val data: List<Movie>
    ): SearchDetailEvent()

    class NoResult(
        val keyword: String
    ): SearchDetailEvent()

    class HasResult(
        val keyword: String,
        val data: List<Movie>
    ): SearchDetailEvent()
}