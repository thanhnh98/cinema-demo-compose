package com.thanhnguyen.cinemaclonecompose.app.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.common.components.BottomNavigation
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.favourite.FavouriteScreen
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.home.HomeScreen
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.profile.ProfileScreen
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.search.SearchScreen
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.ColorPrimaryDark

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Destination(
    start = true
)
@Composable
fun MainScreen(nav: DestinationsNavigator) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ColorPrimaryDark
    ) {
        val pagerState = rememberPagerState()

        ConstraintLayout {
            val (pager, bottomNav) = createRefs()
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(pager) {
                        bottom.linkTo(bottomNav.top)
                        top.linkTo(parent.top)
                        height = Dimension.fillToConstraints
                    },
                count = 4,
                state = pagerState,
                userScrollEnabled = true
            ) { pos ->
                when(pos){
                    0 -> HomeScreen(nav)
                    1 -> SearchScreen(nav)
                    2 -> FavouriteScreen(nav)
                    3 -> ProfileScreen(nav)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = ColorPrimaryDark
                    )
                    .constrainAs(bottomNav) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                BottomNavigation(pagerState = pagerState)
            }
        }
    }
}