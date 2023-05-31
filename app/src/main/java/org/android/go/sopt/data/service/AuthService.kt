package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.BaseResponseDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("sign-in")
    suspend fun signIn(
        @Body requestPostSignIn: RequestSignInDto,
    ): BaseResponseDto<ResponseSignInDto>

    @POST("sign-up")
    suspend fun signUp(
        @Body requestPostSignUp: RequestSignUpDto,
    ): BaseResponseDto<ResponseSignUpDto>
}
