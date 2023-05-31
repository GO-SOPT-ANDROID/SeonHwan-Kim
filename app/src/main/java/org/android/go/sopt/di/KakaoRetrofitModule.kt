package org.android.go.sopt.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.util.type.BaseUrlType
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoRetrofitModule {
    private const val CONTENT_TYPE = "Content-Type"
    private const val AUTHORIZATION = "Authorization"
    private const val APPLICATION_JSON = "application/json"

    @Provides
    @Singleton
    @Retrofit2(BaseUrlType.KAKAO)
    fun providesKakaoInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(AUTHORIZATION, BuildConfig.KAKAO_AUTH_HEADER)
                        .build(),
                )
            }
        }

    @Provides
    @Singleton
    @Retrofit2(BaseUrlType.KAKAO)
    fun providesOkHttpClient(
        @Retrofit2(BaseUrlType.KAKAO) interceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .build()

    @Provides
    @Singleton
    @RetrofitModule.Retrofit2(BaseUrlType.KAKAO)
    fun providesKakaoRetrofit(
        @Retrofit2(BaseUrlType.KAKAO) client: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.KAKAO_BASE_URL)
            .addConverterFactory(Json.asConverterFactory(APPLICATION_JSON.toMediaType()))
            .client(client)
            .build()

    @Qualifier
    annotation class Retrofit2(val baseUrlType: BaseUrlType)
}
