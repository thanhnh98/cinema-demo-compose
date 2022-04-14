package com.thanhnguyen.cinemaclonecompose.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thanhnguyen.cinemaclonecompose.getScreenWidth

@Composable
fun BottomNavigation(
    tabs: List<NavTab>
){
    val tabSelected = remember {
        mutableStateOf(NavTab.HOME)
    }

    LazyRow{
        items(tabs.size){ pos ->
            if (tabs[pos] == tabSelected.value){
                NavTabView()
            }
            else {
                NavTabView()
            }
        }
    }
}

@Composable
fun NavTabView(
    isSelected: Boolean
) {
   //TODO: implement width depend on screen size
}

enum class TabType {
    HOME,
    SEARCH,
    DOWNLOAD,
    PROFILE
}

data class NavTab(
    val type: TabType,
    val name: String
){
    companion object {
        val HOME = NavTab(
            TabType.HOME,
            "Home"
        )
        val SEARCH = NavTab(
            TabType.SEARCH,
            "Search"
        )
        val DOWNLOAD = NavTab(
            TabType.    DOWNLOAD,
            ,
            "Download"
        )
        val PROFILE = NavTab(
            TabType.PROFILE,
            "Profile"
        )
    }
}