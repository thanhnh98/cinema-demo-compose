package com.thanhnguyen.cinemaclonecompose.ui.navigator

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.screen.MainScreen
import com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail.MovieDetailScreen
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.fromJson
import com.thanhnguyen.cinemaclonecompose.utils.toJson

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun NavigatorSetup() {

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

fun NavController.navigateToMovieDetailScreen(movie: Movie){
    navigate(
        Screen.Movie.route_detail.replace(
            "{movie_json_data}", movie.toJson()
        )
    )
}