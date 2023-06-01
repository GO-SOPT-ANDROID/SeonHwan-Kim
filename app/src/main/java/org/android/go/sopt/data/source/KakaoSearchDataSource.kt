package org.android.go.sopt.data.source

import org.android.go.sopt.data.model.response.ResponseKakaoSearchDto
import org.android.go.sopt.data.service.KakaoSearchService
import javax.inject.Inject

class KakaoSearchDataSource @Inject constructor(
    private val kakaoSearchService: KakaoSearchService,
) {
    suspend fun kakaoSearch(query: String): ResponseKakaoSearchDto =
        kakaoSearchService.kakaoSearchVideo(
            query = query,
            sort = "accuracy",
            page = 1,
            size = 30,
        )
}
