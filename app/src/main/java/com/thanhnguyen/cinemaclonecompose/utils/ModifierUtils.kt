package com.thanhnguyen.cinemaclonecompose

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.wrapContent() = this.wrapContentWidth().wrapContentHeight()

fun Modifier.size(
    size: Dp
) = this.width(size).height(size)