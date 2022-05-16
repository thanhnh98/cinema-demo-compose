@file:OptIn(ExperimentalMaterial3Api::class)

package com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.components

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.main.MainActivity
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.home.DotsIndicator
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.WelcomeActivity
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.view_data.WelcomePageModel
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalAnimationApi::class)
@ExperimentalPagerApi
@Composable
fun WelcomeSlider(activity: WelcomeActivity) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = ColorPrimaryDark
            )
    ) {
        val (bottomView, viewPager) = createRefs()
        Box(
            modifier = Modifier
                .background(
                    color = ColorPrimaryDark
                )
                .height(128.dp)
                .fillMaxWidth()
                .padding(
                    start = 48.dp,
                    end = 48.dp
                )
                .constrainAs(bottomView) {
                    bottom.linkTo(parent.bottom)
                }
        ){
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (dots, imgButton) = createRefs()

                DotsIndicator(
                    modifier = Modifier.constrainAs(dots){
                        top.linkTo(imgButton.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(imgButton.bottom)
                    },
                    totalDots = 3,
                    selectedIndex = pagerState.currentPage
                )
                Image(
                    painter = painterResource(
                        when(pagerState.currentPage){
                            0 -> R.drawable.img_next_1
                            1 -> R.drawable.img_next_2
                            else -> R.drawable.img_next_3
                        }
                    ),
                    contentDescription ="",
                    modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                        .constrainAs(imgButton) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        }
                        .clickable {
                            coroutineScope.launch {
                                if (pagerState.currentPage >= 0 && pagerState.currentPage < pagerState.pageCount - 1) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                } else {
                                    withContext(Dispatchers.Main) {
                                        activity.startActivity(
                                            Intent(activity, MainActivity::class.java)
                                        )
                                    }
                                }
                            }

                        }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier
                .constrainAs(viewPager){
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomView.top)
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                },
            count = 3,
            state = pagerState
        ) { pos ->
            when(pos){
                0 -> WelcomeSinglePage(
                    WelcomePageModel(
                        painterResource(id = R.drawable.img_onboard_1),
                        "Tận hưởng những khoảnh khắc tuyệt vời",
                        "Vừa coi phim vừa ngủ, cảm giác thật tuyệt phải không nào, thử vài lần cho quen <3"
                    )
                )
                1 -> WelcomeSinglePage(
                    WelcomePageModel(
                        painterResource(id = R.drawable.img_onboard_2),
                        "Ngoài kho phim đồ sộ thì từng tập phim còn chán ngấy không hay",
                        "Coi phim chán có thể mở quang cáo, vừa xem quảng cáo vừa kiếm thêm thu nhập cho người khác, thật tuyệt vời."
                    ),
                    contentScale = ContentScale.FillWidth
                )
                2 -> WelcomeSinglePage(
                    WelcomePageModel(
                        painterResource(id = R.drawable.img_obboard_3),
                        "Phim được đánh giá cao, vừa dài vừa dai nhưng dỡ",
                        "Đem hộp bắp từ rạp phim về để tự trãi nghiệm cảm giác rạp phim ở nhà sống động như thật nhé."
                    ),
                    showExtra = true
                )
            }
        }
    }
}

@Preview
@Composable
private fun WelcomeSinglePage(
    welcomeModel: WelcomePageModel,
    contentScale: ContentScale = ContentScale.Fit,
    showExtra: Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Black
            )
    ) {
        val (imgCover, bottomBox, leftItem, rightItem) = createRefs()
        val guideLine1 = createGuidelineFromTop(0.7f)

        Box(modifier = Modifier
            .padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp
            )
            .constrainAs(imgCover) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(guideLine1)

                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            },){
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = welcomeModel.image,
                contentDescription = "",
                contentScale = contentScale
            )
        }

        if (showExtra){
            ExtraInfo(modifier = Modifier
                .padding(
                    top = 48.dp,
                    start = 24.dp
                )
                .size(80.dp)
                .border(
                    1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .constrainAs(leftItem) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                icon = painterResource(id = R.drawable.ic_star),
                textCenter = "Rating",
                textBottom = "9 / 10"
            )

            ExtraInfo(modifier = Modifier
                .padding(
                    top = 48.dp,
                    end = 24.dp
                )
                .size(80.dp)
                .border(
                    1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .constrainAs(rightItem) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                icon = painterResource(id = R.drawable.ic_clock),
                textCenter = "Duration",
                textBottom = "1h 69m"
            )
        }

        Box(modifier = Modifier
            .background(
                color = ColorPrimaryDark
            )
            .constrainAs(bottomBox) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = guideLine1,
                    bottom = parent.bottom
                )
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 32.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 32.dp
                    )

            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = welcomeModel.title,
                    style = CommonStyle.custom(
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = welcomeModel.content,
                    style = CommonStyle.custom(
                        color = Grey
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}