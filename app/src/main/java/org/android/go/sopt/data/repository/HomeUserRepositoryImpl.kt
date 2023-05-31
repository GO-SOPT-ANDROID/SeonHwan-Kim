package org.android.go.sopt.data.repository

import org.android.go.sopt.data.source.HomeUserDataSource
import org.android.go.sopt.domain.entity.HomeUser
import org.android.go.sopt.domain.repository.HomeUserReponsitory
import javax.inject.Inject

class HomeUserRepositoryImpl @Inject constructor(
    private val homeUserDataSource: HomeUserDataSource,
) : HomeUserReponsitory {
    override suspend fun getHomeUser(): Result<List<HomeUser>?> =
        runCatching {
            homeUserDataSource.getHomeUser().data.map { userData ->
                userData.toHomeUserEntity()
            }
        }
}
