package com.thanhnguyen.cinemaclonecompose.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun TextStyle.bold() = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold,
    color = TextColor.White
)

fun TextStyle.normal() = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    color = TextColor.White
)

fun TextStyle.custom(
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = TextColor.White
) = TextStyle(
    fontSize = fontSize,
    fontWeight = fontWeight,
    color = color
)