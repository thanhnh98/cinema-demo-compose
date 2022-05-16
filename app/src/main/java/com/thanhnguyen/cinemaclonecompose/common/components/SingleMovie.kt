package com.thanhnguyen.cinemaclonecompose.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.app.model.AccountType
import com.thanhnguyen.cinemaclonecompose.app.model.Movie
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.*

@ExperimentalMaterial3Api
@Composable
fun ToDayMovie(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (title, movieInfo) = createRefs()

        Text(
            text = "Today",
            style = CommonStyle.bold(),
            modifier = Modifier
                .wrapContentWidth()
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        SingleMovie(
            modifier = Modifier.constrainAs(movieInfo){
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(parent.start)
            },
            movie
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleMovie(
    modifier: Modifier = Modifier,
    movie: Movie
) {

    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val (imgThumb, rating, infoContainer) = createRefs()

        AsyncImage(
            modifier = Modifier
                .width(112.dp)
                .height(148.dp)
                .constrainAs(imgThumb) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, 10.dp)
                }
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            model = movie.thumbnail,
            contentDescription = ""
        )

        RatingBox(
            modifier = Modifier
                .constrainAs(rating){
                    start.linkTo(imgThumb.start, 12.dp)
                    top.linkTo(imgThumb.top, 12.dp)
                },
            rating = movie.rating
        )

        Column(
            modifier = Modifier
                .constrainAs(infoContainer){
                    start.linkTo(imgThumb.end, 12.dp)
                    end.linkTo(parent.end, 12.dp)
                    top.linkTo(imgThumb.top)
                    bottom.linkTo(imgThumb.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            verticalArrangement = Arrangement.SpaceAround
        ) {
            AccountTypeRequire(movie.getAccountType())
            MovieName(movie.name)
            ReleaseYear(movie.releaseYear)
            MovieDuration(movie.durationMinute)
            MovieType(movie.type)
        }
    }
}

@Composable
fun ReleaseYear(year: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Grey),
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = year,
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

@Composable
fun MovieType(type: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_movies),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Grey),
            modifier = Modifier.size(12.dp)
        )
        Text(
            modifier = Modifier.padding(
                start = 4.dp
            ),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Grey, fontSize = 12.sp)) {
                    append("$type | ")
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 12.sp)) {
                    append("Movie")
                }
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun MovieDuration(durationMinute: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Grey),
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = "$durationMinute Minutes",
            style = CommonStyle.custom(
                fontSize = 12.sp,
                color = Grey
            ),
            modifier = Modifier.padding(
                start = 4.dp
            )
        )

        Card(
            border = BorderStroke(
                1.dp,
                color =  ColorBlueAccent
            ),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(
                start = 6.dp
            )
        ){
            Text(
                text = "PG-13",
                style = CommonStyle.custom(
                    fontSize = 12.sp,
                    color = ColorBlueAccent
                ),
                modifier = Modifier
                    .background(
                        color = ColorPrimaryDark
                    )
                    .padding(
                        vertical = 1.dp,
                        horizontal = 2.dp
                    )
            )
        }
    }
}

@Composable
fun MovieName(name: String) {
    Text(
        text = name,
        style = CommonStyle.custom(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun AccountTypeRequire(accountType: AccountType) {
    AccountType(accountType)
}

@Composable
fun RatingBox(
    modifier: Modifier = Modifier,
    rating: Double
){
    Box(modifier = modifier
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
                text = rating.toString(),
                style = TextStyle(
                    color = ColorOrangeDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}