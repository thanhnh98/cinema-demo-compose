package com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.components.ActionBar
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimaryDark
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.toJson

@Composable
fun MovieDetailScreen(nav: NavController, movie: Movie?) {
    Box(
        modifier = Modifier
            .background(color = ColorPrimaryDark)
            .fillMaxSize()
    ){
        ActionBar(
            title = movie?.name?:"null",
            iconBack = painterResource(id = R.drawable.ic_round_left),
        )
    }
}