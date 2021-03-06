package com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.components.WelcomeSlider
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.CinemaCloneComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class WelcomeActivity: ComponentActivity() {
    @Inject
    lateinit var prefs: IPrefsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaCloneComposeTheme {
                WelcomeSlider(prefs, this)
            }
        }
    }
}