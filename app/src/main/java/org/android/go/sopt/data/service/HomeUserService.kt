package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseHomeUserDto
import retrofit2.http.GET

interface HomeUserService {
    @GET("users?page=2")
    suspend fun getHomeUserData(): ResponseHomeUserDto
}
