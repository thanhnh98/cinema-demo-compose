package com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.*

@Composable
fun ExtraInfo(
    icon: Painter,
    textCenter: String,
    textBottom: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
    ){
        Column(
            modifier = androidx.compose.ui.Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                modifier = androidx.compose.ui.Modifier.size(14.dp),
                painter = icon,
                contentDescription = "",
                tint = ColorBlueAccent
            )
            Text(
                text = textCenter,
                style = CommonStyle.custom(
                    color = Grey,
                    fontSize = 12.sp
                )
            )
            Text(
                text = textBottom,
                style = CommonStyle.custom(
                    color = White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}