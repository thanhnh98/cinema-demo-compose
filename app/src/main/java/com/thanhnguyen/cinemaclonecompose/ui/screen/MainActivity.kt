package com.thanhnguyen.cinemaclonecompose.ui.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.thanhnguyen.cinemaclonecompose.ui.components.BottomNavigation
import com.thanhnguyen.cinemaclonecompose.ui.navigator.NavigatorSetup
import com.thanhnguyen.cinemaclonecompose.ui.screen.home.*
import com.thanhnguyen.cinemaclonecompose.ui.screen.search.SearchPage
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimaryDark

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaCloneComposeTheme {
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
                            userScrollEnabled = false
                        ) { pos ->
                            when(pos){
                                0 -> HomePage()
                                1 -> SearchPage()
                                3 -> HomePage()
                                4 -> SearchPage()
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
        }
    }
}
