package com.thanhnguyen.cinemaclonecompose.ui.screen.main

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thanhnguyen.cinemaclonecompose.common.listBannersData
import com.thanhnguyen.cinemaclonecompose.common.listMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.ui.screen.NavGraphs
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            CinemaCloneComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
