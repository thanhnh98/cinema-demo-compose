package com.thanhnguyen.cinemaclonecompose.app.ui.screen.login

import androidx.lifecycle.ViewModel
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel<LoginState, LoginEvent>() {
    override fun initState(): LoginState {
        return LoginState()
    }

    override fun onTriggeredEvent(event: LoginEvent) {
        TODO("Not yet implemented")
    }

}