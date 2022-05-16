package com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.components.WelcomeSlider
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.CinemaCloneComposeTheme

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
class WelcomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaCloneComposeTheme {
                WelcomeSlider(this)
            }
        }
    }
}