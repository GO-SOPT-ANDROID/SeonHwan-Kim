package org.android.go.sopt.data.remote.service

import org.android.go.sopt.data.remote.model.BaseResponseDto
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// suspend 함수?
interface SignUpService {
    @POST("sign-up")
    fun signUp(
        @Body
        request: RequestSignUpDto,
    ): Call<BaseResponseDto<ResponseSignUpDto>>
}