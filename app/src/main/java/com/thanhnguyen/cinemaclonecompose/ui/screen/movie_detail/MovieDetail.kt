package com.thanhnguyen.cinemaclonecompose.ui.screen.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.components.ActionBar
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimaryDark
import com.thanhnguyen.cinemaclonecompose.utils.WTF
import com.thanhnguyen.cinemaclonecompose.utils.toJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MovieDetailScreen(nav: NavController, movie: Movie?) {
    val uiState = MutableStateFlow<String>("").collectAsState()
    Box(
        modifier = Modifier
            .background(color = ColorPrimaryDark)
            .fillMaxSize()
    ){
        AsyncImage(
            model = movie?.thumbnail,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            contentScale = ContentScale.Crop
        )
        ActionBar(
            title = movie?.name?:"null",
            iconBack = painterResource(id = R.drawable.ic_round_left_32)
        )
    }
}