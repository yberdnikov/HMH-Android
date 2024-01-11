package com.hmh.hamyeonham.core.network.model

data class BaseResponse<T>(
    val status: Int,
    val message: String,
    val data: T
)
