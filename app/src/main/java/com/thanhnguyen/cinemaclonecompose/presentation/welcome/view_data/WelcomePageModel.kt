package com.thanhnguyen.cinemaclonecompose.presentation.welcome.view_data

import androidx.compose.ui.graphics.painter.Painter

data class WelcomePageModel(
    val image: Painter,
    val title: String,
    val content: String
)