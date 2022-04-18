package com.thanhnguyen.cinemaclonecompose.ui.navigator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.screen.MainScreen
import com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail.MovieDetailScreen
import com.thanhnguyen.cinemaclonecompose.utils.fromJson

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun NavigatorSetup() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            MainScreen(navController)
        }

        composable(
            Screen.Movie.route_detail
        ){
            val movieDataJson = it.arguments?.getString("movie_json_data")
            val movieData: Movie? = movieDataJson?.fromJson()

            MovieDetailScreen(
                nav = navController,
                movie = movieData
            )
        }
    }
}

sealed class Screen {
    object Main: Screen() {
        const val route = "main"
    }

    object Movie: Screen() {
        const val route = "movie"
        const val route_detail = "$route?movie_json_data={movie_json_data}"
    }
}