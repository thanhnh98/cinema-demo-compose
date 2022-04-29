package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import com.thanhnguyen.cinemaclonecompose.common.globalUser
import com.thanhnguyen.cinemaclonecompose.common.listBannersData
import com.thanhnguyen.cinemaclonecompose.common.listCategories
import com.thanhnguyen.cinemaclonecompose.domain.MovieRepository
import com.thanhnguyen.cinemaclonecompose.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): BaseViewModel<HomeState, HomeEvent>() {
    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            call(
                HomeEvent.Loading(true)
            )
            withContext(Dispatchers.IO){
                loadProfileUser()
                loadBanners()
                loadCategories()
                loadPopularMovies()
            }
        }
    }

    override fun onTriggeredEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Loading -> {
                setState(
                    uiState.value.copy(
                        isLoading = event.isLoading
                    )
                )
            }
            is HomeEvent.ProfileUserLoaded -> {
                setState(
                    uiState.value.copy(
                        user = event.user
                    )
                )
            }
            is HomeEvent.BannersLoaded -> {
                setState(
                    uiState.value.copy(
                        banners = event.banners
                    )
                )
            }
            is HomeEvent.ListCategoriesLoaded -> {
                setState(
                    uiState.value.copy(
                        listCategories = event.listCategories
                    )
                )
            }

            is HomeEvent.FavouriteMovieLoaded -> {
                setState(
                    uiState.value.copy(
                        listMovies = event.favouriteMovies
                    )
                )
            }
        }
    }

    fun loadProfileUser(){
        viewModelScope.launch {
            movieRepository.getPopularMovies().apply {
                call(
                    HomeEvent.ProfileUserLoaded(
                        globalUser
                    )
                )
            }
        }
    }

    fun loadCategories(){
        viewModelScope.launch {
            movieRepository.getPopularMovies().apply {
                call(
                    HomeEvent.ListCategoriesLoaded(
                        listCategories
                    )
                )
            }
        }
    }

    fun loadPopularMovies(){
        viewModelScope.launch {
            movieRepository.getPopularMovies().apply {
                call(
                    HomeEvent.FavouriteMovieLoaded(
                        this.data?.data?: listOf(),
                    )
                )
                call(
                    HomeEvent.Loading(false)
                )
            }
        }
    }

    fun loadBanners(){
        viewModelScope.launch {
            call(
                HomeEvent.BannersLoaded(
                    listBannersData,
                )
            )
        }
    }

    override fun initState(): HomeState {
        return HomeState()
    }
}