package com.jp.androidpro.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    var name: String,
    var url: String
)
