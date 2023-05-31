package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto

interface AuthRepository {
    suspend fun signIn(requestSignInDto: RequestSignInDto): Result<ResponseSignInDto?>

    suspend fun signUp(requestSignUpDto: RequestSignUpDto): Result<ResponseSignUpDto?>
}
