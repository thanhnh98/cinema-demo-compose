package com.thanhnguyen.cinemaclonecompose.app.model

import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<DATA>: BaseModel() {
    val data: DATA? = null
}