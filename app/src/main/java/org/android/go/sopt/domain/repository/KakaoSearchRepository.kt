package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.entity.KakaoSearch

interface KakaoSearchRepository {
    suspend fun kakaoSearch(query: String): Result<List<KakaoSearch>>
}