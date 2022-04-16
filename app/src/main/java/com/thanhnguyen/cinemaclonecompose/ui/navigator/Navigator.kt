package com.thanhnguyen.cinemaclonecompose.ui.navigator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.ui.screen.home.HomePage
import com.thanhnguyen.cinemaclonecompose.ui.screen.search.SearchPage

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun NavigatorSetup() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.MAIN) {
        composable(Route.MAIN) { HomePage() }
        composable(Route.HOME) { HomePage() }
        composable(Route.SEARCH) { SearchPage() }
        /*...*/
    }
}

object Route {
    const val MAIN =  "main"
    const val HOME = "home"
    const val SEARCH = "search"
    const val SAVED = "saved"
    const val PROFILE = "profile"
}