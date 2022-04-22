package com.thanhnguyen.cinemaclonecompose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EVENT>: ViewModel() {
    private var _uiState = MutableStateFlow(initState())
    val uiState get() = _uiState.asStateFlow()

    fun setState(state: STATE){
        viewModelScope.launch {
            _uiState.emit(state)
        }
    }

    fun call(event: EVENT){
        onTriggeredEvent(event)
    }

    abstract fun initState(): STATE
    abstract fun onTriggeredEvent(event: EVENT)
}