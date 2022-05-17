package com.thanhnguyen.cinemaclonecompose.app.ui.screen.routing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutingViewModel @Inject constructor(
    private val prefsClient: IPrefsClient
): ViewModel() {

    private var _shouldShowOnboard = MutableSharedFlow<Boolean?>()
    val shouldShowOnboard: Flow<Boolean?> get() = _shouldShowOnboard

    init {

    }

    fun checkShouldShowOnboard() {
        viewModelScope.launch {
            prefsClient
                .isShownOnboard()
                .catch { e ->
                    WTF("ERR ${e.message}")
                }
                .collect {
                    WTF("emiting $it")
                    _shouldShowOnboard.emit(it)

                    if (it  != true){
                        markShowedOnboard()
                    }
                }
        }
    }

    fun markShowedOnboard(){
        viewModelScope.launch {
            prefsClient.setShownOnboard(true)
        }
    }
}