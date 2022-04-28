package com.thanhnguyen.cinemaclonecompose.model

import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<DATA>: BaseModel() {
    val data: DATA? = null
}