package org.android.go.sopt.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.domain.entity.HomeUser

@Serializable
data class ResponseHomeUserDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<UserData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class UserData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String,
        @SerialName("avatar")
        val avatar: String,
    ) {
        fun toHomeUserEntity() = HomeUser(
            email = email,
            first_name = first_name,
            last_name = last_name,
            avatar = avatar,
        )
    }

    @Serializable
    data class Support(
        @SerialName("text")
        val text: String,
        @SerialName("url")
        val url: String,
    )
}
