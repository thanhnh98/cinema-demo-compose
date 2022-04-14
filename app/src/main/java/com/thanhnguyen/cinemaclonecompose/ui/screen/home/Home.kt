package com.thanhnguyen.cinemaclonecompose.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.components.BottomNavigation
import com.thanhnguyen.cinemaclonecompose.ui.components.ListMovieHorizontal
import com.thanhnguyen.cinemaclonecompose.ui.components.MovieTest
import com.thanhnguyen.cinemaclonecompose.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun HomePage(){
    CinemaCloneComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ColorPrimaryDark
        ) {
            ConstraintLayout {
                val (mainPage, nav) = createRefs()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(mainPage) {
                            bottom.linkTo(nav.top)
                            top.linkTo(parent.top)
                            height = Dimension.fillToConstraints
                        }
                        .verticalScroll(rememberScrollState()),
                ) {
                    Greeting()
                    SearchBar()
                    Banners()
                    ListCategories()
                    MostPopular()
                    Spacer(modifier = Modifier.height(10.dp))
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = ColorPrimaryDark
                        )
                        .constrainAs(nav) {
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    BottomNavigation()
                }
            }
        }
    }
}

@Composable
fun MostPopular() {
    ListMovieHorizontal(
        modifier = Modifier.padding(
            top = 32.dp,
            start = 16.dp
        ),
        title = "Most popular",
        listOf(
            MovieTest,
            MovieTest,
            MovieTest,
        )
    )
}

@Composable
fun ListCategories() {
    val listCategories = mutableListOf(
        "All",
        "Comedy",
        "Animation",
        "Hentai",
        "Document",
    )
    val posSelected = remember{
        mutableStateOf(0)
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(
            top = 24.dp,
            start = 16.dp
        )
    ) {
        Text(text = "Categories", style = TextStyle().bold())

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            userScrollEnabled = true
        ) {
            items(listCategories.size){ pos ->
                if (pos == posSelected.value){
                    ItemCategory(
                        pos = pos,
                        isSelected = true,
                        content = listCategories[pos]
                    ){
                        posSelected.value = it
                    }
                }
                else {
                    ItemCategory(
                        pos = pos,
                        isSelected = false,
                        content = listCategories[pos]
                    ){
                        posSelected.value = it
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCategory(
    pos: Int,
    isSelected: Boolean,
    content: String,
    onClick: (positionSelected: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(
                color = if (isSelected) ColorPrimarySoft else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick.invoke(pos)
            }
    ){
        Text(
            text = content,
            style = TextStyle(
                color = if (isSelected) ColorBlueAccent else Color.White,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(
                    start = 20.dp,
                    top = 4.dp,
                    end = 20.dp,
                    bottom = 4.dp
                )
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalPagerApi
@Composable
fun Banners() {
    val composableScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = 3,
            contentPadding = PaddingValues(start = 40.dp, end = 40.dp),
            modifier = Modifier.padding(top = 16.dp),
            state = pagerState
        ) { page ->
            Box(
                Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f.dp,
                            stop = 1f.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale.value
                            scaleY = scale.value
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f.dp,
                            stop = 1f.dp,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).value
                    }
            ) {
                ItemBanner(pos = page)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        DotsIndicator(
            totalDots = 3,
            selectedIndex = pagerState.currentPage,
            selectedColor = ColorBlueAccent,
            unSelectedColor = ColorBlueAccentBlur50
        )
    }
    composableScope.launch {
        pagerState.scrollToPage(1)
    }
}

@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(8.dp)
                        .background(
                            color = selectedColor,
                            shape = RoundedCornerShape(4f.dp)
                        )
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun ItemBanner(pos: Int){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp)
        .background(
            color = Color.Transparent
        )
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
            model = "https://gamek.mediacdn.vn/thumb_w/600/133514250583805952/2022/3/28/photo-1-1648463807426792939448.jpg",
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            ColorBackgroundGradientStart,
                            ColorBackgroundGradientStop
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    bottom = 16.dp
                )
            ,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                "Luffy chap 1044",
                style = TextStyle().bold()
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Page $pos: Cái huân hòe gỉ đây",
                style = TextStyle().normal()
            )
        }

    }
}

@Composable
fun SearchBar() {
    val textValue = remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight()
        .background(
            color = ColorPrimarySoft,
            shape = RoundedCornerShape(24.dp)
        )
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
               BasicTextField(
                   value = textValue.value,
                   onValueChange = {
                       textValue.value = it
                   },
                   modifier = Modifier
                       .fillMaxWidth(),
                   textStyle = TextStyle(
                       color = TextColor.White,
                       fontSize = 16.sp
                   ),
                   singleLine = true
               )
               if (textValue.value.text.isEmpty())
                   Text(text = "Search a movie...", style = TextStyle(
                       color = TextColor.Grey,
                       fontSize = 16.sp
                   ),
                       modifier = Modifier.align(alignment = Alignment.CenterStart))
           }

           Spacer(modifier = Modifier
               .constrainAs(vLine){
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

@Composable
fun Greeting() {
    val image: Painter = painterResource(id = R.drawable.avatar)
    ConstraintLayout(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 16.dp
            )
            .fillMaxWidth()
    ) {
        val (imgLeft, imgRight, textCenter) = createRefs()

        Image(
            image,
            modifier = Modifier
                .constrainAs(imgLeft) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .height(40.dp)
                .width(40.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = "")
        Column(
            modifier = Modifier
                .constrainAs(textCenter){
                    top.linkTo(imgLeft.top)
                    bottom.linkTo(imgLeft.bottom)
                    start.linkTo(imgLeft.end, margin = 16.dp)
                    end.linkTo(imgRight.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.Start

        ) {
            Text(text = "Hello, Thanh Nguyen!", style = TextStyle(
                color = TextColor.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Let's stream your favourite movie", style = TextStyle(
                color = TextColor.Grey,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(imgRight) {
                    end.linkTo(parent.end)
                    top.linkTo(textCenter.top)
                    bottom.linkTo(textCenter.bottom)
                }
                .layoutId("img_right")
                .height(32.dp)
                .width(32.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(grey36),

            ){
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center),
                contentScale = ContentScale.Inside
            )
        }
    }
}