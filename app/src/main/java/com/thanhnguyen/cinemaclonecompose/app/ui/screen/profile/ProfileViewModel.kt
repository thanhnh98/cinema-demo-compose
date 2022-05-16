package com.thanhnguyen.cinemaclonecompose.app.ui.screen.profile

import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): BaseViewModel<ProfileState, ProfileEvent>() {
    override fun initState(): ProfileState = ProfileState.Loading()

    override fun onTriggeredEvent(event: ProfileEvent) {

    }
}