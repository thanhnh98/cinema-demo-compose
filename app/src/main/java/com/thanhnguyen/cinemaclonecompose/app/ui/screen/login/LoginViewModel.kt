package com.thanhnguyen.cinemaclonecompose.app.ui.screen.login

import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val prefs: IPrefsClient
): BaseViewModel<LoginState, LoginEvent>() {
    override fun initState(): LoginState {
        return LoginState()
    }

    override fun onTriggeredEvent(event: LoginEvent) {

    }

    fun markAsLoggedIn(){
        viewModelScope.launch {
            prefs.setLoggedIn(true)
        }
    }

}