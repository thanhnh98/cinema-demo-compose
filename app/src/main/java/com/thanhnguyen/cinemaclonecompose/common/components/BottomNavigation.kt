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
import androidx.compose.runtime.*
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
import com.thanhnguyen.cinemaclonecompose.ui.theme.*
import com.thanhnguyen.cinemaclonecompose.utils.fromJson
import com.thanhnguyen.cinemaclonecompose.utils.toJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

val tabs = listOf(
    NavTab.HOME,
    NavTab.SEARCH,
    NavTab.NOTIFICATION,
    NavTab.PROFILE,
)

@ExperimentalPagerApi
@Composable
fun BottomNavigation(
    pagerState: PagerState
){
    val coroutineScope = rememberCoroutineScope()

    val tabSelectedState = rememberSaveable(stateSaver = BottomNavigationState.Saver) {
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
            tabs[0],
            tabSelectedState,
            ){
                onTabSelectedByUser(it, tabSelectedState, coroutineScope, pagerState)
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
            tabs[1],
            tabSelectedState
        ){
            onTabSelectedByUser(it, tabSelectedState, coroutineScope, pagerState)
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
            tabs[2],
            tabSelectedState
        ){
            onTabSelectedByUser(it, tabSelectedState, coroutineScope, pagerState)
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
            tabs[3],
            tabSelectedState
        ){
            onTabSelectedByUser(it, tabSelectedState, coroutineScope, pagerState)
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect{ currentPage ->
                onTabSelectedByScrolling(pagerState, tabSelectedState, currentPage, coroutineScope, false)
            }
    }
}

@OptIn(ExperimentalPagerApi::class)
fun onTabSelectedByUser(
    tab: NavTab,
    tabSelected: MutableState<BottomNavigationState>,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    tabSelected.value = BottomNavigationState(tab)
    coroutineScope.launch {
        pagerState.scrollToPage(
            tabs.indexOf(tab)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
fun onTabSelectedByScrolling(
    pagerState: PagerState,
    tabSelected: MutableState<BottomNavigationState>,
    pos: Int,
    coroutineScope: CoroutineScope,
    autoscroll: Boolean = true
) {
    when(pos){
        0 -> tabSelected.value = BottomNavigationState(NavTab.HOME)
        1 -> tabSelected.value = BottomNavigationState(NavTab.SEARCH)
        2 -> tabSelected.value = BottomNavigationState(NavTab.NOTIFICATION)
        3 -> tabSelected.value = BottomNavigationState(NavTab.PROFILE)
    }
}

@Composable
fun NavTabView(
    modifier: Modifier = Modifier,
    tab: NavTab,
    tabSelectedState: MutableState<BottomNavigationState>,
    onClick: (NavTab) -> Unit
) {
    val isSelected = tabSelectedState.value.tabSelected.type == tab.type    
    
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
    NOTIFICATION,
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
        val NOTIFICATION = NavTab(
            TabType.NOTIFICATION,
            "News",
            R.drawable.ic_news
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