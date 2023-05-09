package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.ResponseHomeUserDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface HomeUserService {
    @GET("users?page=2")
    fun GetHomeUserData(): Call<ResponseHomeUserDto>
}