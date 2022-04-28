package com.thanhnguyen.cinemaclonecompose.presentation.home

import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import com.thanhnguyen.cinemaclonecompose.common.globalUser
import com.thanhnguyen.cinemaclonecompose.common.listBannersData
import com.thanhnguyen.cinemaclonecompose.common.listCategories
import com.thanhnguyen.cinemaclonecompose.common.listMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.domain.MovieRepository
import com.thanhnguyen.cinemaclonecompose.model.Result
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.toJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): BaseViewModel<HomeState, HomeEvent>() {
    init {
        loadData()
    }

    override fun onTriggeredEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Loading -> {

            }

            is HomeEvent.Success -> {
                setState(
                    uiState.value.copy(
                        user = event.user,
                        listMovies = event.favouriteMovies,
                        listCategories = event.listCategories,
                        banners = event.banners
                    )
                )
            }
        }
    }

    fun loadData(){
        viewModelScope.launch {
            movieRepository.getPopularMovies().apply {
                when(this){
                    is Result.Success ->{
                        call(
                            HomeEvent.Success(
                                globalUser,
                                listBannersData,
                                listCategories,
                                this.data?.data?: listOf()
                            )
                        )
                    }
                }
            }


        }
    }

    override fun initState(): HomeState {
        return HomeState()
    }
}