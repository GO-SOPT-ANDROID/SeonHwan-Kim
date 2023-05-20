package org.android.go.sopt.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseKakaoSearchDto(
    @SerialName("documents")
    val documents: List<Document>,
    @SerialName("meta")
    val meta: Meta
) {
    @Serializable
    data class Document(
        @SerialName("author")
        val author: String,
        @SerialName("datetime")
        val datetime: String,
        @SerialName("play_time")
        val play_time: Int,
        @SerialName("thumbnail")
        val thumbnail: String,
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String
    )

    @Serializable
    data class Meta(
        @SerialName("is_end")
        val is_end: Boolean,
        @SerialName("pageable_count")
        val pageable_count: Int,
        @SerialName("total_count")
        val total_count: Int
    )
}