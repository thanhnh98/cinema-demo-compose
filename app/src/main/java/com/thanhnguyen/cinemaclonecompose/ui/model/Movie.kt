package com.thanhnguyen.cinemaclonecompose.ui.model

import kotlinx.serialization.Serializable

@Serializable
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