package com.thanhnguyen.cinemaclonecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.thanhnguyen.cinemaclonecompose.ui.theme.*
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.model.Movie

@Composable
fun ListMovieHorizontal(
    modifier: Modifier = Modifier,
    title: String,
    listMovies: List<Movie>
){
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(3f)
                    .wrapContentWidth(
                        Alignment.Start
                    ),
                text = title, style = TextStyle().bold())

            Text(modifier = Modifier
                .weight(1f)
                .wrapContentWidth(
                    Alignment.End
                ),
                text = "See All", style = TextStyle().custom(
                color = ColorBlueAccent
            ))
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier,
            userScrollEnabled = true
        ){
            items(listMovies.size){ position ->
                MovieItemHorizontal(listMovies[position])
            }
        }
    }
}

@Composable
fun MovieItemHorizontal(movie: Movie) {
    val corner = 15.dp
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .padding(end = 16.dp)){
        ConstraintLayout(
            modifier = Modifier
                .height(300.dp)
                .width(160.dp)
                .background(
                    color = ColorPrimarySoft,
                    shape = RoundedCornerShape(
                        corner
                    )
                )
        ){
            val (imageMovie, rating, name, type) = createRefs()

            Text(
                text = movie.type,
                modifier = Modifier
                    .constrainAs(type){
                        bottom.linkTo(parent.bottom, margin = 12.dp)
                        start.linkTo(parent.start, margin = 12.dp)
                    },
                style = TextStyle().custom(
                    color = TextColor.Grey
                )
            )

            Text(
                text = movie.name,
                modifier = Modifier
                    .constrainAs(name){
                        bottom.linkTo(type.top, margin = 8.dp)
                        start.linkTo(type.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                style = TextStyle().bold(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            AsyncImage(
                model = movie.thumbnail,
                contentDescription = "",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = corner,
                            topEnd = corner
                        )
                    )
                    .constrainAs(imageMovie) {
                        bottom.linkTo(name.top, margin = 12.dp)
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                contentScale = ContentScale.Crop,
            )

            Box(modifier = Modifier
                .constrainAs(rating) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
                .background(
                    color = ColorPrimarySoft79,
                    shape = RoundedCornerShape(6.dp)
                )
                .wrapContentWidth()
                .wrapContentHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(
                            start = 6.dp,
                            end = 6.dp,
                            top = 4.dp,
                            bottom = 4.dp,
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "",
                        modifier = Modifier
                            .width(14.dp)
                            .height(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = movie.rating.toString(),
                        style = TextStyle(
                            color = ColorOrange,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}