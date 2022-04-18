package com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimaryDark

@Composable
fun MovieDetailScreen(nav: NavController, movie: Movie) {
    CinemaCloneComposeTheme {
        Box(
            modifier = Modifier
                .background(color = ColorPrimaryDark)
                .fillMaxSize()
        ){
            Text(
                text = movie.name
            )
        }

    }
}