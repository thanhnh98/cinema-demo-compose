package com.thanhnguyen.cinemaclonecompose.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thanhnguyen.cinemaclonecompose.*
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.theme.*

@Composable
fun BottomNavigation(
    tabs: List<NavTab> = listOf(
        NavTab.HOME,
        NavTab.SEARCH,
        NavTab.DOWNLOAD,
        NavTab.PROFILE
    )
){
    val tabSelected = remember {
        mutableStateOf(NavTab.HOME)
    }

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp)
    ){
        items(tabs.size){ pos ->
            if (tabs[pos] == tabSelected.value){
                NavTabView(isSelected = true, tabs[pos]){
                    tabSelected.value = it
                }
            }
            else {
                NavTabView(isSelected = false, tabs[pos]){
                    tabSelected.value = it
                }
            }
        }
    }
}

@Preview
@Composable
fun NavTabView(
    isSelected: Boolean,
    tab: NavTab,
    onClick: (NavTab) -> Unit
) {
    val context = LocalContext.current
    val screenWidthPx = getScreenWidth().toFloat()
    val itemMaxWidth = (convertPixelsToDp(screenWidthPx, context) - 32)/4

    val selectedColor = ColorBlueAccent
    val unSelectedColor = Grey

    val backgroundSelected  = ColorBlueAccentBlur20
    val backgroundUnselected  = ColorPrimaryDark

    Box(modifier = Modifier
        .background(
            color = if (isSelected) backgroundSelected else backgroundUnselected,
            shape = RoundedCornerShape(12.dp)
        )
        .height(32.dp)
        .width(itemMaxWidth.dp)
        .wrapContentHeight()
        .clickable {
            onClick.invoke(tab)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    bottom = 4.dp,
                    end = 6.dp,
                    start = 6.dp
                )
        ) {
            Image(
                painter = painterResource(id = tab.icon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(
                    if (isSelected) selectedColor else unSelectedColor,
                ),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = tab.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = if (isSelected) selectedColor else unSelectedColor
                    )
                )
            }
        }
    }

}

enum class TabType {
    HOME,
    SEARCH,
    DOWNLOAD,
    PROFILE
}

data class NavTab(
    val type: TabType,
    val name: String,
    @DrawableRes val icon: Int
){
    companion object {
        val HOME = NavTab(
            TabType.HOME,
            "Home",
            R.drawable.ic_home
        )
        val SEARCH = NavTab(
            TabType.SEARCH,
            "Search",
            R.drawable.ic_search
        )
        val DOWNLOAD = NavTab(
            TabType.DOWNLOAD,
            "Saved",
            R.drawable.ic_download
        )
        val PROFILE = NavTab(
            TabType.PROFILE,
            "Profile",
            R.drawable.ic_profile
        )
    }
}