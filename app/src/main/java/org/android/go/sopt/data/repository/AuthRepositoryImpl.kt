package org.android.go.sopt.data.repository

import org.android.go.sopt.data.model.request.RequestSignInDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignInDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.data.source.AuthDataSource
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signIn(requestSignInDto: RequestSignInDto): Result<ResponseSignInDto?> =
        runCatching {
            authDataSource.signIn(requestSignInDto).data
        }

    override suspend fun signUp(requestSignUpDto: RequestSignUpDto): Result<ResponseSignUpDto?> =
        runCatching {
            authDataSource.signUp(requestSignUpDto).data
        }
}
