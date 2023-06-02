package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.entity.HomeUser

interface HomeUserReponsitory {
    suspend fun getHomeUser(): Result<List<HomeUser>?>
}
