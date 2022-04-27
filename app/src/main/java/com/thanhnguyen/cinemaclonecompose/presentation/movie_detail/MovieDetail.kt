package com.thanhnguyen.cinemaclonecompose.presentation.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.getScreenWidth
import com.thanhnguyen.cinemaclonecompose.pxToDp
import com.thanhnguyen.cinemaclonecompose.common.components.ActionBar
import com.thanhnguyen.cinemaclonecompose.common.components.RatingBox
import com.thanhnguyen.cinemaclonecompose.model.Movie
import com.thanhnguyen.cinemaclonecompose.theme.*

@Composable
@Destination
fun MovieDetailScreen(nav: NavController, movie: Movie?) {
    val context = LocalContext.current
    val imageWidth = (getScreenWidth().pxToDp(context) * 0.5f).dp
    val imageHeight = imageWidth * 1.7f
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

        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ColorPrimaryDark50,
                        ColorPrimaryDark,
                    )
                )
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = movie?.thumbnail,
                contentDescription = "",
                modifier = Modifier
                    .size(
                        width = imageWidth,
                        height = imageHeight
                    )
                    .padding(
                        top = 32.dp
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .align(CenterHorizontally)
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            ExtraInfo(movie?:return)
            RatingBox(
                modifier = Modifier.align(CenterHorizontally),
                rating = movie.rating
            )
            PlayPlace(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
            )
            StoryLine(
                modifier = Modifier.padding(
                    top = 16.dp
                ),
                movie
            )
        }

        ActionBar(
            modifier = Modifier
                .padding(
                    top = 32.dp
                ),
            title = movie?.name?:"null",
            iconBack = painterResource(id = R.drawable.ic_round_left_32),
            iconRight = painterResource(id = R.drawable.ic_heart),
            onBackPressed = {
                nav.navigateUp()
            }
        )
    }
}

@Composable
fun StoryLine(
    modifier: Modifier,
    movie: Movie
) {
    Column(
        modifier.padding(
            16.dp
        ),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Story Line", style = CommonStyle.bold())
        Spacer(modifier = Modifier.padding(
            top = 10.dp
        ))
        Text(text = movie.storyLine, style = CommonStyle.normal())
    }
}

@Composable
fun PlayPlace(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        PlayButton(
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        )
        DownloadButton(
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        )
        ShareButton(
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        )
    }
}

@Composable
fun ShareButton( modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = ColorPrimarySoft,
                shape = RoundedCornerShape(24.dp)
            )
    ){
        Image(
            modifier = Modifier.padding(14.dp),
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "",
            colorFilter = ColorFilter.tint(ColorBlueAccent)
        )
    }
}

@Composable
fun DownloadButton( modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = ColorPrimarySoft,
                shape = RoundedCornerShape(26.dp)
            )
    ){
        Image(
            modifier = Modifier.padding(14.dp),
            painter = painterResource(id = R.drawable.ic_download),
            contentDescription = "",
            colorFilter = ColorFilter.tint(ColorOrangeDark)
        )
    }
}

@Composable
fun PlayButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = ColorOrangeDark,
                shape = RoundedCornerShape(26.dp)
            )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(
                vertical = 14.dp,
                horizontal = 20.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp
                ),
                text = "Play",
                style = CommonStyle.normal()
            )
        }
    }
}

@Composable
fun ExtraInfo(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 24.dp,
                bottom = 18.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Grey),
                modifier = Modifier
                    .size(12.dp)
            )
            Text(
                text = movie.releaseYear,
                style = CommonStyle.custom(
                    fontSize = 12.sp,
                    color = Grey
                ),
                modifier = Modifier.padding(
                    start = 4.dp
                )
            )
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
                .width(1.dp)
                .background(
                    color = Grey
                )
        )

        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Grey),
                modifier = Modifier
                    .size(12.dp)
            )
            Text(
                text = "${movie.durationMinute} Minutes",
                style = CommonStyle.custom(
                    fontSize = 12.sp,
                    color = Grey
                ),
                modifier = Modifier.padding(
                    start = 4.dp
                )
            )
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
                .width(1.dp)
                .background(
                    color = Grey
                )
        )
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_movies),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Grey),
                modifier = Modifier
                    .size(12.dp)
            )
            Text(
                text = movie.type,
                style = CommonStyle.custom(
                    fontSize = 12.sp,
                    color = Grey
                ),
                modifier = Modifier.padding(
                    start = 4.dp
                )
            )
        }

    }
}
