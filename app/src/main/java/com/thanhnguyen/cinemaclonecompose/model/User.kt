package com.thanhnguyen.cinemaclonecompose.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String? = null,
    val thumbnail: String? = null,
    val email: String? = null
): BaseModel()