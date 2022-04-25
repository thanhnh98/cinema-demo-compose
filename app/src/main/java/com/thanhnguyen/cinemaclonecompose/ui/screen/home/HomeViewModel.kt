package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import com.thanhnguyen.cinemaclonecompose.ui.common.globalUser
import com.thanhnguyen.cinemaclonecompose.ui.common.listBannersData
import com.thanhnguyen.cinemaclonecompose.ui.common.listCategories
import com.thanhnguyen.cinemaclonecompose.ui.common.listMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.doOnDelay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: BaseViewModel<HomeState, HomeEvent>() {

    override fun onTriggeredEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Loading -> {
                setState(HomeState.Loading)
            }

            is HomeEvent.Success -> {
                setState(HomeState.Data(
                    event.user,
                    event.banners,
                    event.listCategories,
                    event.favouriteMovies
                ))
            }
        }
    }

    fun loadData(){
        viewModelScope.launch {
            doOnDelay(1000L){
                call(HomeEvent.Success(
                    globalUser,
                    listBannersData,
                    listCategories,
                    listMovieHorizontal
                ))
            }
        }
    }

    override fun initState(): HomeState{
        loadData()
        return HomeState.Loading
    }
}