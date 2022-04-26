package com.thanhnguyen.cinemaclonecompose.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.thanhnguyen.cinemaclonecompose.presentation.home.ItemCategory

@Composable
fun ListChips(
    listCategories: List<String>
){
    val posSelected = rememberSaveable(stateSaver = ListChipsState.Saver) {
        mutableStateOf(ListChipsState(0))
    }

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        userScrollEnabled = true
    ) {
        items(listCategories.size){ pos ->
            if (pos == posSelected.value.indexSelected){
                ItemCategory(
                    pos = pos,
                    isSelected = true,
                    content = listCategories[pos]
                ){
                    posSelected.value = ListChipsState(it)
                }
            }
            else {
                ItemCategory(
                    pos = pos,
                    isSelected = false,
                    content = listCategories[pos]
                ){
                    posSelected.value = ListChipsState(it)
                }
            }
        }
    }
}

data class ListChipsState(
    val indexSelected: Int
){
    companion object {
        val Saver = run {
            val indexSelectedKey = "indexSelected"
            mapSaver(
                save = {
                    mapOf(
                        indexSelectedKey to it.indexSelected
                    )
                },
                restore = {
                    ListChipsState(it[indexSelectedKey] as Int)
                }
            )
        }
    }
}