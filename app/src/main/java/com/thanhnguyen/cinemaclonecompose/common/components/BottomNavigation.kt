package com.thanhnguyen.cinemaclonecompose.common.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.theme.*
import com.thanhnguyen.cinemaclonecompose.utils.fromJson
import com.thanhnguyen.cinemaclonecompose.utils.toJson
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@ExperimentalPagerApi
@Composable
fun BottomNavigation(
    pagerState: PagerState
){
    val coroutineScope = rememberCoroutineScope()

    val tabSelected = rememberSaveable(stateSaver = BottomNavigationState.Saver) {
        mutableStateOf(BottomNavigationState(NavTab.HOME))
    }

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .padding(
            vertical = 14.dp,
            horizontal = 16.dp
        )
        .wrapContentHeight()
    ){
        val guideLine1 = createGuidelineFromStart(0.25f)
        val guideLine2 = createGuidelineFromStart(0.5f)
        val guideLine3 = createGuidelineFromStart(0.75f)

        val (tabHome, tabSearch, tabSaved, tabProfile) = createRefs()

        NavTabView(
            modifier = Modifier
                .constrainAs(tabHome){
                     linkTo(
                         start = parent.start,
                         end = guideLine1
                     )
                    width = Dimension.fillToConstraints
                },
            isSelected = tabSelected.value.tabSelected.type == NavTab.HOME.type,
            NavTab.HOME
        ){
            tabSelected.value = BottomNavigationState(it)
            coroutineScope.launch {
                pagerState.animateScrollToPage(0)
            }
        }

        NavTabView(
            modifier = Modifier
                .constrainAs(tabSearch){
                     linkTo(
                         start = guideLine1,
                         end = guideLine2
                     )
                    width = Dimension.fillToConstraints
                },
            isSelected = tabSelected.value.tabSelected.type == NavTab.SEARCH.type,
            NavTab.SEARCH
        ){
            tabSelected.value = BottomNavigationState(it)
            coroutineScope.launch {
                pagerState.animateScrollToPage(1)
            }
        }

        NavTabView(
            modifier = Modifier
                .constrainAs(tabSaved){
                     linkTo(
                         start = guideLine2,
                         end = guideLine3
                     )
                    width = Dimension.fillToConstraints
                },
            isSelected = tabSelected.value.tabSelected.type == NavTab.DOWNLOAD.type,
            NavTab.DOWNLOAD
        ){
            tabSelected.value = BottomNavigationState(it)
            coroutineScope.launch {
                pagerState.animateScrollToPage(2)
            }
        }

        NavTabView(
            modifier = Modifier
                .constrainAs(tabProfile){
                     linkTo(
                         start = guideLine3,
                         end = parent.end
                     )
                    width = Dimension.fillToConstraints
                },
            isSelected = tabSelected.value.tabSelected.type == NavTab.PROFILE.type,
            NavTab.PROFILE
        ){
            tabSelected.value = BottomNavigationState(it)
            coroutineScope.launch {
                pagerState.animateScrollToPage(3)
            }
        }
    }
}

@Composable
fun NavTabView(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    tab: NavTab,
    onClick: (NavTab) -> Unit
) {

    val selectedColor = ColorBlueAccent
    val unSelectedColor = Grey

    val backgroundSelected  = ColorPrimarySoft
    val backgroundUnselected  = ColorPrimaryDark

    Box(modifier = modifier
        .background(
            color = if (isSelected) backgroundSelected else backgroundUnselected,
            shape = RoundedCornerShape(12.dp)
        )
        .height(40.dp)
        .focusable(true)
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
            AnimatedVisibility(visible = isSelected) {
                Text(
                    modifier = Modifier.padding(
                        start = 4.dp
                    ),
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

@Serializable
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

data class BottomNavigationState(
    val tabSelected: NavTab
){
    companion object {
        val Saver = run {
            val tabSelectedKey = "tab"
            mapSaver(
                save = {
                    mapOf(
                        tabSelectedKey to (it.tabSelected).toJson()
                    )
                },
                restore = {
                    BottomNavigationState((it[tabSelectedKey] as String).fromJson())
                }
            )
        }
    }
}