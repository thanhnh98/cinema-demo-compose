package com.thanhnguyen.cinemaclonecompose.ui.screen.search_detail

import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor(): BaseViewModel<SearchDetailState, SearchDetailEvent>() {
    override fun initState(): SearchDetailState = SearchDetailState()

    override fun onTriggeredEvent(event: SearchDetailEvent) {
        when(event){
            is SearchDetailEvent.Loading -> {
                setState(
                    uiState.value.copy(
                        isLoading = event.isLoading
                    )
                )
            }

            is SearchDetailEvent.NoResult -> {
                setState(
                    uiState.value.copy(
                        keyword = event.keyword,
                        isLoading = false
                    )
                )
            }

            is SearchDetailEvent.HasResult -> {
                setState(
                    state = uiState.value.copy(
                        keyword = event.keyword,
                        data = event.data,
                        isLoading = false
                    )
                )
            }
        }
    }

    fun submitTextSearch(content: String){
        call(
            SearchDetailEvent.NoResult(
                content
            )
        )
    }
}