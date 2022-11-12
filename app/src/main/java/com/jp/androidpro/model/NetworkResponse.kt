package com.jp.androidpro.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    var pageResponse: PageResponse<T>,
    var errorCode: Int,
    var errorMsg: String
)