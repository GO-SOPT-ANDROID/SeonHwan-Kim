package org.android.go.sopt.data.source

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.BaseResponseDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun signIn(requestSignInDto: RequestSignInDto): BaseResponseDto<ResponseSignInDto> =
        authService.signIn(requestSignInDto)

    suspend fun signUp(requestSignUpDto: RequestSignUpDto): BaseResponseDto<ResponseSignUpDto> =
        authService.signUp(requestSignUpDto)
}
