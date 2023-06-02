package org.android.go.sopt.data.source

import org.android.go.sopt.data.model.response.ResponseHomeUserDto
import org.android.go.sopt.data.service.HomeUserService
import javax.inject.Inject

class HomeUserDataSource @Inject constructor(
    private val homeUserService: HomeUserService,
) {
    suspend fun getHomeUser(): ResponseHomeUserDto =
        homeUserService.getHomeUserData()
}
