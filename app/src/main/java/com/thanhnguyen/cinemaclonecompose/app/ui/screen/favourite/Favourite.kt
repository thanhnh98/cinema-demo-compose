package com.thanhnguyen.cinemaclonecompose.app.ui.screen.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.common.components.ActionBar
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.CommonStyle


@ExperimentalMaterial3Api
@Composable
@Destination
fun FavouriteScreen(nav: DestinationsNavigator){
    Box(modifier = Modifier.fillMaxSize()){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (actionBar, body) = createRefs()

            ActionBar(
                title = "News",
                modifier = Modifier.constrainAs(actionBar){
                    top.linkTo(parent.top)
                    width = Dimension.matchParent
                }.padding(
                    top = 32.dp
                ),
            )

            Column(
                Modifier.fillMaxSize().constrainAs(body) {
                    top.linkTo(actionBar.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.ic_library), contentDescription = "")
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Text(text = "Chưa có tin mới...", style = CommonStyle.normal())
            }
        }
    }
}