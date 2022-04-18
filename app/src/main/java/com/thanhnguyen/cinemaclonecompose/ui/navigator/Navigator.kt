package com.thanhnguyen.cinemaclonecompose.ui.navigator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.ui.common.highLightMovie
import com.thanhnguyen.cinemaclonecompose.ui.screen.MainScreen
import com.thanhnguyen.cinemaclonecompose.ui.screen.home.HomeScreen
import com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail.MovieDetailScreen
import com.thanhnguyen.cinemaclonecompose.ui.screen.search.SearchScreen

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun NavigatorSetup() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.MAIN) {
        composable(Route.MAIN) {
            MainScreen(navController)
        }
        composable(Route.MOVIE_DETAIL){
            MovieDetailScreen(movie = highLightMovie)
        }
    }
}

object Route {
    const val MAIN =  "main"
    const val MOVIE_DETAIL = "movie_detail"
}