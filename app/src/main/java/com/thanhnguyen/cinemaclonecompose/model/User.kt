package com.thanhnguyen.cinemaclonecompose.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val thumbnail: String
): BaseModel()