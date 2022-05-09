package com.thanhnguyen.cinemaclonecompose.ui.screen.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (actionBar, body) = createRefs()
        Text(
            text = "Profile",
            style = CommonStyle.bold(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(actionBar){
                    top.linkTo(parent.top)
                    width = Dimension.matchParent
                }
                .padding(
                    top = 48.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
        )


        LazyColumn(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(body){
                    top.linkTo(actionBar.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                },
            userScrollEnabled = true,
            state = rememberLazyListState()
        ) {
            item {
                HeaderProfile(globalUser)
                AccountTypeBadge()
                GroupAccount()
                GroupGeneral()
                GroupMore()
            }
        }
    }
}

@Composable
fun GroupMore() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .border(
                color = ColorPrimarySoft,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Title(title = "More")
            Item(iconPainter = painterResource(id = R.drawable.ic_shield), text = "Legal and Policies")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_question), text = "Help and Feedback")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_info), text = "About Us")
        }
    }
}

@Composable
fun GroupGeneral() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .border(
                color = ColorPrimarySoft,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Title(title = "General")
            Item(iconPainter = painterResource(id = R.drawable.ic_notifications), text = "Notification")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_language), text = "Language")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_flag), text = "Country")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_delete), text = "Clear Cache")
        }
    }
}

@Composable
fun GroupAccount() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .border(
                color = ColorPrimarySoft,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp),
            )
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Title(title = "Account")
            Item(iconPainter = painterResource(id = R.drawable.ic_profile), text = "Member")
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(1.dp)
                    .background(
                        color = ColorPrimarySoft
                    )
            )
            Item(iconPainter = painterResource(id = R.drawable.ic_padlock), text = "Change Password")
        }
    }
}

@Composable
fun AccountTypeBadge() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.img_bg_profile),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentScale = ContentScale.Crop
        )
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .align(Alignment.Center)
        ) {
            val (imgBadge, tvTitle, tvDes) = createRefs()

            Box(
                modifier = Modifier
                    .background(
                        color = White20,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(40.dp)
                    .constrainAs(imgBadge) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_premium_badge),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                )
            }

            Text(
                "Premium member",
                    style = CommonStyle.bold(),
                modifier = Modifier.constrainAs(tvTitle){
                    start.linkTo(imgBadge.end, 10.dp)
                    top.linkTo(imgBadge.top)
                }
            )

            Text(
                "New movies are coming for you,\nDownload now!",
                style = CommonStyle.custom(
                    fontWeight = FontWeight.W300
                ),
                modifier = Modifier.constrainAs(tvDes){
                    start.linkTo(imgBadge.end, 8.dp)
                    top.linkTo(tvTitle.bottom, 8.dp)
                }
            )
        }
    }
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

@Composable
fun GroupProfile(
    vararg items: Unit
){

}

@Composable
fun Title(title: String){
    Text(
        text = title,
        style = CommonStyle.bold(),
        modifier = Modifier.padding(
            bottom = 8.dp
        )
    )
}

@Composable
fun Item(iconPainter: Painter, text: String){
    ConstraintLayout(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (iconLeft, iconRight, tvTitle) = createRefs()

        Box(
            modifier = Modifier
                .background(
                    color = ColorPrimarySoft79,
                    shape = RoundedCornerShape(16.dp)
                )
                .size(32.dp)
                .constrainAs(iconLeft) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
        ){
            Image(
                painter = iconPainter,
                colorFilter = ColorFilter.tint(Color.Gray),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center)
                    .fillMaxSize()
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(iconRight) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(tvTitle) {
                    start.linkTo(iconLeft.end, 12.dp)
                    end.linkTo(iconRight.start, 12.dp)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            text = text,
            style = CommonStyle.normal())
    }
}