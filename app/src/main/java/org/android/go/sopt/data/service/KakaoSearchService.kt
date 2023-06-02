package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseKakaoSearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoSearchService {
    @GET("v2/search/vclip")
    suspend fun kakaoSearchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): ResponseKakaoSearchDto
}
