package com.thanhnguyen.cinemaclonecompose.ui.screen.search_detail

import com.thanhnguyen.cinemaclonecompose.model.Movie

data class SearchDetailState(
    val keyword: String = "",
    val data: List<Movie>? = null,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false
)