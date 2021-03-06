package com.thanhnguyen.cinemaclonecompose.app.ui.screen.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.common.highLightMovie
import com.thanhnguyen.cinemaclonecompose.common.listMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.common.components.ListChips
import com.thanhnguyen.cinemaclonecompose.common.components.ListMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.common.components.ToDayMovie
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.destinations.MovieDetailScreenDestination
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.destinations.SearchDetailScreenDestination
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.ColorPrimaryDark
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.ColorPrimarySoft
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.Grey
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.TextColor

@ExperimentalMaterial3Api
@Composable
@Destination
fun SearchScreen(
    nav: DestinationsNavigator
) {
    val listChips = listOf(
        "All",
        "Comedy",
        "Animation",
        "Hentai",
        "Document",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 16.dp
            )
            .background(
                color = ColorPrimaryDark
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            SearchBar(nav)
            ListChips(listChips)
            ToDayMovie(
                modifier = Modifier.padding(
                    top = 16.dp
                ),
                highLightMovie
            )
            RecommendedMovies(nav)
        }
    }
}


@Composable
fun RecommendedMovies(nav: DestinationsNavigator) {
    ListMovieHorizontal(
        modifier = Modifier.padding(
            top = 32.dp,
        ),
        title = "Recommended for you",
        listMovies = listMovieHorizontal
    ){
        nav.navigate(
            MovieDetailScreenDestination(
                it
            )
        )
    }
}

@Composable
fun SearchBar(nav: DestinationsNavigator) {
    val textValue = remember { mutableStateOf(TextFieldValue("")) }
    AnimatedVisibility(visible = true, enter = fadeIn()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .wrapContentHeight()
            .background(
                color = ColorPrimarySoft,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                nav.navigate(SearchDetailScreenDestination())
            }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
            ) {
                val (icSearch, edtSearch, vLine, icFilter)  = createRefs()

                Image(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier
                        .constrainAs(icSearch) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .width(20.dp)
                        .height(20.dp)
                )

                Box(modifier = Modifier
                    .constrainAs(edtSearch){
                        start.linkTo(icSearch.end, margin = 8.dp)
                        top.linkTo(icSearch.top)
                        bottom.linkTo(icSearch.bottom)
                        end.linkTo(vLine.start, margin = 4.dp)
                        width = Dimension.fillToConstraints
                    }
                ){
//               BasicTextField(
//                   value = textValue.value,
//                   onValueChange = {
//                       textValue.value = it
//                   },
//                   modifier = Modifier
//                       .fillMaxWidth(),
//                   textStyle = TextStyle(
//                       color = TextColor.White,
//                       fontSize = 16.sp
//                   ),
//                   singleLine = true
//               )
//               if (textValue.value.text.isEmpty())
                    Text(text = "Search a movie...", style = TextStyle(
                        color = TextColor.Grey,
                        fontSize = 16.sp
                    ),
                        modifier = Modifier.align(alignment = Alignment.CenterStart))
                }

                Spacer(modifier = Modifier
                    .constrainAs(vLine) {
                        end.linkTo(icFilter.start, margin = 4.dp)
                        top.linkTo(icFilter.top)
                        bottom.linkTo(icFilter.bottom)
                    }
                    .width(1.dp)
                    .height(20.dp)
                    .background(
                        color = Grey
                    )
                )

                Image(
                    painterResource(id = R.drawable.ic_filter),
                    contentDescription = "",
                    modifier = Modifier
                        .constrainAs(icFilter) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .width(20.dp)
                        .height(20.dp)
                )
            }
        }
    }
}