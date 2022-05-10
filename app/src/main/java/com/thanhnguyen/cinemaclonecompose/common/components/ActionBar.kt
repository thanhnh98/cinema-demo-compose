package com.thanhnguyen.cinemaclonecompose.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thanhnguyen.cinemaclonecompose.ui.theme.ColorPrimarySoft79
import com.thanhnguyen.cinemaclonecompose.ui.theme.CommonStyle

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    iconBack: Painter? = null,
    iconRight: Painter? = null,
    title: String,
    onBackPressed: (() -> Unit)? = null,
    onActionPressed: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier = modifier
            .padding(
                horizontal = 16.dp
            )
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.Transparent
            )
    ){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val (icBack, tvTitle, icRight) = createRefs()

            if (iconBack != null) {
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .constrainAs(icBack) {
                            linkTo(
                                top = parent.top,
                                bottom = parent.bottom,
                            )
                            start.linkTo(parent.start)
                        }
                        .background(
                            color = ColorPrimarySoft79,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            onBackPressed?.invoke()
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(24.dp)
                            .height(24.dp),
                        painter = iconBack,
                        contentDescription = ""
                    )
                }
            }


            if (iconRight != null){
                Box(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp)
                        .constrainAs(icRight){
                            linkTo(
                                top = parent.top,
                                bottom = parent.bottom,
                            )
                            end.linkTo(parent.end)
                        }
                        .background(
                            color = ColorPrimarySoft79,
                            shape = RoundedCornerShape(16.dp)
                        )
                    ,
                ){
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(16.dp)
                            .height(16.dp),
                        painter = iconRight,
                        contentDescription = ""
                    )
                }
            }

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        start = 20.dp,
                        end = 20.dp
                    )
                    .constrainAs(tvTitle) {
                        start.linkTo(if(iconBack != null) icBack.end else parent.start)
                        end.linkTo(if (iconRight != null) icRight.start else parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                text = title,
                style = CommonStyle.bold(),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}