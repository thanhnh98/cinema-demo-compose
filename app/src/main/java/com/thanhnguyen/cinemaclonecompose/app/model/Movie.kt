package com.thanhnguyen.cinemaclonecompose.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val thumbnail: String,
    val name: String,
    val type: String,
    val rating: Double,
    val accountType: Int, // free/premium
    val releaseYear: String,
    val durationMinute: Int,
    val storyLine: String
): BaseModel() {
    fun getAccountType() = if(accountType == 1) AccountType.Premium else AccountType.Free
}