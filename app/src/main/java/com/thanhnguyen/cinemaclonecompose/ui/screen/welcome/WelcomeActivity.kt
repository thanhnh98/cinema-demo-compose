package com.thanhnguyen.cinemaclonecompose.ui.screen.welcome

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.ui.screen.welcome.components.WelcomeSlider
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
class WelcomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            CinemaCloneComposeTheme {
                WelcomeSlider(this)
            }
        }
    }
}