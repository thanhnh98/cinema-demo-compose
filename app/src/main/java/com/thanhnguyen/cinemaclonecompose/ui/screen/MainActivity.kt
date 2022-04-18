package com.thanhnguyen.cinemaclonecompose.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.ui.navigator.NavigatorSetup

@ExperimentalPagerApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigatorSetup()
        }
    }
}
