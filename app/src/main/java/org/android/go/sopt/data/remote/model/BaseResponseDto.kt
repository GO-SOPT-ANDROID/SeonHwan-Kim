package org.android.go.sopt.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseResponseDto<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T? = null
)