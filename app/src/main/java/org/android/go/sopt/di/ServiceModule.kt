package org.android.go.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.service.AuthService
import org.android.go.sopt.data.service.HomeUserService
import org.android.go.sopt.data.service.KakaoSearchService
import org.android.go.sopt.util.type.BaseUrlType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(@RetrofitModule.Retrofit2(BaseUrlType.SOPT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesHomeUserService(@RetrofitModule.Retrofit2(BaseUrlType.REQRES) retrofit: Retrofit): HomeUserService =
        retrofit.create(HomeUserService::class.java)

    @Provides
    @Singleton
    fun providesKakaoSearchService(@RetrofitModule.Retrofit2(BaseUrlType.KAKAO) retrofit: Retrofit): KakaoSearchService =
        retrofit.create(KakaoSearchService::class.java)
}
