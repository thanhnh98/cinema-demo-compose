package com.thanhnguyen.cinemaclonecompose.app.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.NavGraphs
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.CinemaCloneComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaCloneComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

