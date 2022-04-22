package com.thanhnguyen.cinemaclonecompose.ui.screen.home

sealed class HomeEvent {
    class Loading(): HomeEvent()
    class Success(): HomeEvent()
}