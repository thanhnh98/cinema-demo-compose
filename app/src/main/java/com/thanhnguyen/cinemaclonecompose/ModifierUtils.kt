package com.thanhnguyen.cinemaclonecompose

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier


fun Modifier.wrapContent() = this.wrapContentWidth().wrapContentHeight()
