package com.thanhnguyen.cinemaclonecompose.ui.screen.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.common.globalUser
import com.thanhnguyen.cinemaclonecompose.model.User
import com.thanhnguyen.cinemaclonecompose.ui.screen.home.Greeting
import com.thanhnguyen.cinemaclonecompose.ui.theme.*

@Composable
@Destination
fun ProfileScreen(
    nav: DestinationsNavigator,
    profileViewModel: ProfileViewModel = hiltViewModel()
){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Profile",
            style = CommonStyle.bold(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = 48.dp,
                bottom = 16.dp
            ).fillMaxWidth()
        )
        HeaderProfile(globalUser)
        AccountTypeBadge()
        GroupAccount()
        GroupGeneral()
        GroupMore()
    }
}

@Composable
fun GroupMore() {

}

@Composable
fun GroupGeneral() {

}

@Composable
fun GroupAccount() {

}

@Composable
fun AccountTypeBadge() {

}

@Composable
fun HeaderProfile(user: User?) {
    val name =  user?.name?:""
    val email = user?.email?:""
    val thumb = user?.thumbnail?:""

    AnimatedVisibility(visible = true, enter = fadeIn()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
                .border(
                    color = ColorPrimarySoft,
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                )
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp,
                        top = 12.dp
                    )
                    .fillMaxWidth()
            ) {
                val (imgLeft, imgRight, textCenter) = createRefs()

                AsyncImage(
                    thumb,
                    modifier = Modifier
                        .constrainAs(imgLeft) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .height(54.dp)
                        .width(54.dp)
                        .clip(RoundedCornerShape(27.dp)),
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
                    Text(text = name, style = TextStyle(
                        color = TextColor.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = email, style = TextStyle(
                        color = TextColor.Grey,
                        fontSize = 16.sp,
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
                    ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Center),
                        contentScale = ContentScale.Inside
                    )
                }
            }
        }
    }
}
