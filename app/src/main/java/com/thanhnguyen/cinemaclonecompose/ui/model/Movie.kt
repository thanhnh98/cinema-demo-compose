package com.thanhnguyen.cinemaclonecompose.ui.model

data class Movie(
    val id: Int,
    val thumbnail: String,
    val name: String,
    val type: String,
    val rating: Double,
    val accountType: AccountType = AccountType.Free,
    val releaseYear: String,
    val durationMinute: Int
)