package org.android.go.sopt.data.remote.service

import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.model.ResponseKakaoSearchDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoSearchService {
    @GET("v2/search/vclip")
    fun kakaoSearchVideo(
        @Header("Authorization") apiKey: String = BuildConfig.KAKAO_AUTH_HEADER,
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<ResponseKakaoSearchDto>
}