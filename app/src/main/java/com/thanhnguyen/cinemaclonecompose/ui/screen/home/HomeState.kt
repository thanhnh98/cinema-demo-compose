package com.thanhnguyen.cinemaclonecompose.ui.screen.home


sealed class HomeState {
    object Initial : HomeState()
    object Loading : HomeState()
    object Data : HomeState()
    object Error : HomeState()
}