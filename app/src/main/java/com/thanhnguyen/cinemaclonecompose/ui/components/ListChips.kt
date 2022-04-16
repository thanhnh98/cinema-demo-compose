package com.thanhnguyen.cinemaclonecompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.thanhnguyen.cinemaclonecompose.ui.screen.home.ItemCategory

@Composable
fun ListChips(listCategories: List<String>){
    val posSelected = remember{
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        userScrollEnabled = true
    ) {
        items(listCategories.size){ pos ->
            if (pos == posSelected.value){
                ItemCategory(
                    pos = pos,
                    isSelected = true,
                    content = listCategories[pos]
                ){
                    posSelected.value = it
                }
            }
            else {
                ItemCategory(
                    pos = pos,
                    isSelected = false,
                    content = listCategories[pos]
                ){
                    posSelected.value = it
                }
            }
        }
    }
}