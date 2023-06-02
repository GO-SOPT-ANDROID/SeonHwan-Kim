package org.android.go.sopt.data.repository

import org.android.go.sopt.data.source.KakaoSearchDataSource
import org.android.go.sopt.domain.entity.KakaoSearch
import org.android.go.sopt.domain.repository.KakaoSearchRepository
import javax.inject.Inject

class KakaoSearchRepositoryImpl @Inject constructor(
    private val kakaoSearchDataSource: KakaoSearchDataSource
): KakaoSearchRepository {
    override suspend fun kakaoSearch(query: String): Result<List<KakaoSearch>> =
        runCatching {
            kakaoSearchDataSource.kakaoSearch(query).documents.map { data ->
                data.toKakaoSearchEntity()
            }
        }
}