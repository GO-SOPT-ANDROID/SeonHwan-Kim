package org.android.go.sopt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.interceptor.AuthInterceptor
import org.android.go.sopt.data.remote.service.HomeUserService
import org.android.go.sopt.data.remote.service.KakaoSearchService
import org.android.go.sopt.data.remote.service.SignInService
import org.android.go.sopt.data.remote.service.SignUpService
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                },
            ).build()
    }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // inline에 대해 알아보기
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ReqresApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object KakaoApiFactory {
    val retrofit: Retrofit by lazy {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(BuildConfig.KAKAO_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val signInService = ApiFactory.create<SignInService>()
    val getHomeUserService = ReqresApiFactory.create<HomeUserService>()
    val kakaoSearchService = KakaoApiFactory.create<KakaoSearchService>()
}
