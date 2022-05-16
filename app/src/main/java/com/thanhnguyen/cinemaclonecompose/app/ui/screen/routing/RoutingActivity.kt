package com.thanhnguyen.cinemaclonecompose.app.ui.screen.routing

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.app.data.local.preferences.IPrefsClient
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.main.MainActivity
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.WelcomeActivity
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class RoutingActivity(): ComponentActivity() {
    @Inject lateinit var prefsClient: IPrefsClient
    @Inject lateinit var routingViewModel: RoutingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WTF("open routing")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        GlobalScope.launch {
            routingViewModel.shouldShowOnboard.collect{
                if (it == true){
                    startActivity(Intent(this@RoutingActivity, MainActivity::class.java))
                }
                else {
                    startActivity(Intent(this@RoutingActivity, WelcomeActivity::class.java))
                }
                finish()
                this.cancel()
            }
        }
    }
}