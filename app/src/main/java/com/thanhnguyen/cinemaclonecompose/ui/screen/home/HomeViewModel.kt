package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import com.thanhnguyen.cinemaclonecompose.utils.WTF

class HomeViewModel: BaseViewModel<HomeState, HomeEvent>() {
    override fun onTriggeredEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.Loading -> {
                setState(HomeState.Loading)
            }

            is HomeEvent.Success -> {
                setState(HomeState.Data)
            }
        }
    }

    override fun initState(): HomeState{
        WTF("init state")
        return HomeState.Loading
    }
}