package com.thanhnguyen.cinemaclonecompose.ui.screen.search_detail

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.viewModelScope
import com.thanhnguyen.cinemaclonecompose.base.BaseViewModel
import com.thanhnguyen.cinemaclonecompose.common.listMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.addAll
import com.thanhnguyen.cinemaclonecompose.utils.toJson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor(): BaseViewModel<SearchDetailState, SearchDetailEvent>() {
    override fun initState(): SearchDetailState = SearchDetailState()

    override fun onTriggeredEvent(event: SearchDetailEvent) {
        when(event){
            is SearchDetailEvent.Loading -> {
                setState(
                    uiState.value.copy(
                        isLoading = event.isLoading,
                        isLoadingMore = event.isLoadingMore
                    )
                )
            }

            is SearchDetailEvent.NoResult -> {
                setState(
                    uiState.value.copy(
                        keyword = event.keyword,
                        isLoading = false,
                        data = null
                    )
                )
            }

            is SearchDetailEvent.HasResult -> {
                setState(
                    state = uiState.value.copy(
                        keyword = event.keyword,
                        data = event.data,
                        isLoading = false,
                        isLoadingMore = false
                    )
                )
            }

            is SearchDetailEvent.LoadingMoreCompleted -> {
                WTF("LOAD MỎE COMPLETE")
                setState(
                    state = uiState.value.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        data = uiState.value.data?.addAll(event.data)
                    )
                )
            }
        }
    }

    fun submitTextSearch(content: String){
        viewModelScope.launch {
            call(SearchDetailEvent.Loading(true))
            delay(500)
            if (content.lowercase(Locale.getDefault()).trim() == "one piece"){
                call(
                    SearchDetailEvent.HasResult(
                        keyword = content,
                        data = listMovieHorizontal
                    )
                )
            }
            else
                call(
                    SearchDetailEvent.NoResult(
                        keyword = content,
                    )
                )
        }
    }

    fun loadMoreResults(content: String){
        viewModelScope.launch {
            call(SearchDetailEvent.Loading(isLoading = false, isLoadingMore = true))
            delay(500)
            call(
                SearchDetailEvent.LoadingMoreCompleted(
                    keyword = content,
                    data = listMovieHorizontal
                )
            )
        }
    }
}