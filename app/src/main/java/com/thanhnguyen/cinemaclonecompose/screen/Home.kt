package com.thanhnguyen.cinemaclonecompose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thanhnguyen.cinemaclonecompose.ui.theme.CinemaCloneComposeTheme
import com.thanhnguyen.cinemaclonecompose.ui.theme.TextColor
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimaryDark
import com.thanhnguyen.cinemaclonecompose.ui.theme.grey36

@Composable
fun HomePage(){
    CinemaCloneComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ColorPrimaryDark
        ) {
            Column {
                buildGreeting()
                buildSearchBar()
            }
        }
    }
}

@Composable
fun buildSearchBar() {
    Box(modifier = )
}

@Composable
fun buildGreeting() {
    val image: Painter = painterResource(id = R.drawable.avatar)
    ConstraintLayout(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
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
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Let's stream your favourite movie", style = TextStyle(
                color = TextColor.Grey,
                fontSize = 12.sp,
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