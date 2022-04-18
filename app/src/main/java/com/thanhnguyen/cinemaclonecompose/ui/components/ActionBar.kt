package com.thanhnguyen.cinemaclonecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.thanhnguyen.cinemaclonecompose.ui.theme.bold

@Preview
@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    iconBack: Painter,
    iconRight: Painter? = null,
    title: String
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

            Image(
                modifier = Modifier
                    .constrainAs(icBack){
                        linkTo(
                            top = parent.top,
                            bottom = parent.bottom,
                        )
                        start.linkTo(parent.start)
                        height = Dimension.fillToConstraints
                    },
                painter = iconBack,
                contentDescription = ""
            )

            if (iconRight != null){
                Image(
                    modifier = Modifier
                        .constrainAs(icRight){
                            linkTo(
                                top = parent.top,
                                bottom = parent.bottom,
                            )
                            end.linkTo(parent.end)
                            height = Dimension.fillToConstraints
                        },
                    painter = iconBack,
                    contentDescription = ""
                )
            }

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    )
                    .constrainAs(tvTitle){
                        start.linkTo(icBack.end)
                        end.linkTo(if (iconRight != null) icRight.start else parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                text = title,
                style = TextStyle().bold(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}